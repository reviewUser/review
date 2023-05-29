package com.chinasoft.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinasoft.dao.CheckReviewDao;
import com.chinasoft.dao.RepeatMessageDao;
import com.chinasoft.dao.ReviewManagementDao;
import com.chinasoft.param.ReviewParam;
import com.chinasoft.po.*;
import com.chinasoft.service.ReviewManagementService;
import com.chinasoft.utils.ImportExcelUtils;
import com.chinasoft.utils.Result;
import com.chinasoft.utils.Sample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 系统用户业务层接口实现
 *
 * @author 王鹏
 */
@Service
public class ReviewManagementServiceImpl implements ReviewManagementService {

    @Autowired
    private ReviewManagementDao reviewManagementDao;

    @Value("${aliyun.sms.access-key-id}")
    private String accessKeyId;

    @Value("${aliyun.sms.access-key-secret}")
    private String accessKeySecret;

    @Value("${aliyun.sms.sign-name}")
    private String signName;

    @Value("${aliyun.sms.template-code}")
    private String templateCode;

    @Value("${aliyun.sms.param}")
    private String param;

    @Autowired
    private RepeatMessageDao repeatMessageDao;

    @Autowired
    private CheckReviewDao checkReviewDao;

    @Override
    public List<ReviewManagement> queryReviewInfo(ReviewParam param) {
        List<ReviewManagement> infos = null;
        infos = reviewManagementDao.queryReviewInfo(param);
        infos.forEach(p -> {
            List<CheckReview> checkReviews = checkReviewDao.queryStatusByReviewId(p.getId());
            if (checkReviews.size() > 0) {
                List<String> collect = checkReviews.stream().map(CheckReview::getRepeats).collect(Collectors.toList());
                if (collect.stream().allMatch("同意"::equals)) {
                    reviewManagementDao.updateStatus("全部通知完成", p.getId());
                } else if (collect.stream().allMatch("拒绝"::equals)) {
                    reviewManagementDao.updateStatus("通知失败", p.getId());
                } else {
                    reviewManagementDao.updateStatus("部分通知完成", p.getId());
                }
            }
        });
        return infos;
    }

    @Override
    public int getReviewTotalCount(ReviewParam param) {
        if (param != null) {
            return reviewManagementDao.getReviewTotalCount(param);
        }
        return 0;
    }

    @Override
    public Map<String, Object> importReview(MultipartFile file) {
        Map<String, Object> resultMap = new HashMap<>();
        List<ReviewManagement> reviewManagements = new ArrayList<>();
        try {
            //验证文件类型
            if (!file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).equals(".xls") && !file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).equals(".xlsx")) {
                resultMap.put("mete", "文件类型有误！请上传Excle文件");
                throw new Exception("文件类型有误！请上传Excle文件");
            }
            //获取数据
            List<List<Object>> olist = ImportExcelUtils.getListByExcel(file.getInputStream(), file.getOriginalFilename());
            //封装数据
            for (int i = 0; i < olist.size(); i++) {
                List<Object> list = olist.get(i);
                if (list.get(0) == "" || ("序号").equals(list.get(0))) {
                    continue;
                }
                ReviewManagement reviewManagement = new ReviewManagement();
                //根据下标获取每一行的每一条数据
                if (String.valueOf(list.get(0)) == null || String.valueOf(list.get(0)).equals("")) {
                    resultMap.put("mete", "评审任务名");
                    throw new Exception("评审任务名不能为空");
                }
                reviewManagement.setReviewName(String.valueOf(list.get(0)));
                reviewManagement.setReviewRemark(String.valueOf(list.get(1)));
                reviewManagement.setReviewUser(String.valueOf(list.get(2)));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                reviewManagement.setReviewDate(Timestamp.valueOf(String.valueOf(list.get(3))));
                reviewManagement.setReviewStartDate(sdf.parse(String.valueOf(list.get(4))));
                reviewManagement.setReviewEndDate(sdf.parse(String.valueOf(list.get(5))));
                reviewManagement.setReviewField(String.valueOf(list.get(6)));
                reviewManagement.setReviewExperts(String.valueOf(list.get(7)));
                reviewManagement.setReviewStatus(String.valueOf(list.get(8)));
                reviewManagements.add(reviewManagement);
            }
            int i = reviewManagementDao.batchInsertReviews(reviewManagements);
            if (i != 0) {
                resultMap.put("导入成功", 200);
            } else {
                resultMap.put("mete", "文档内无数据，请重新导入");
                resultMap.put("status", 500);
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("导入失败", 500);
        } finally {
            return resultMap;
        }
    }

    @Override
    public Result delReviews(List<Long> ids) {
        Result result = new Result();
        if (ids.size() == 0) {
            result.setCode("500");
            result.setMsg("参数异常");
        }
        int i = reviewManagementDao.batchDelReviews(ids);
        if (i > 0) {
            result.setCode("200");
            result.setMsg("删除成功");
        } else {
            result.setCode("500");
            result.setMsg("删除失败");
        }
        return result;
    }

    @Override
    public Result startReview(ReviewManagement reviewManagement) throws Exception {
        Result result = new Result();
        if (StringUtils.isEmpty(reviewManagement.getReviewField())) {
            result.setCode("500");
            result.setMsg("评审所属专业领域不能为空");
            return result;
        }

        List<ExpertInfo> expertInfos = reviewManagementDao.queryExpertByFiled(reviewManagement.getReviewField());
        if (expertInfos.size() < Long.parseLong(reviewManagement.getReviewExperts())) {
            result.setCode("500");
            result.setMsg("评审所需专家数量不足");
            return result;
        }
        List<String> phones = expertInfos.stream().map(ExpertInfo::getPhone).collect(Collectors.toList());
        List<String> list = GetRandomThreeInfoList(phones, 3);
        for (String str : list) {
            String msg = sendSms(reviewManagement.getId(), str);
            if (msg.contains("短信发送成功")) {
                result.setCode("200");
            } else {
                result.setCode("500");
            }
            result.setMsg(msg);
        }
        reviewManagementDao.updateStatus("通知中", reviewManagement.getId());
        return result;
    }

    @Override
    public void exportReview(List<Long> ids, HttpServletResponse response) {
        List<ReviewManagement> reviewManagements = null;
        try {
            if (ids.size() == 0) {
                reviewManagements = reviewManagementDao.queryAllReviews();
            } else {
                reviewManagements = reviewManagementDao.queryReviewByIds(ids);
            }
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String fileName = "任务评审管理-" + sdf.format(new Date()) + ".xlsx";
            String fileNameURL = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileNameURL + ";" + "filename*=utf-8''" + fileNameURL);
            EasyExcel.write(response.getOutputStream(), ReviewManagement.class).sheet("任务评审管理").doWrite(reviewManagements);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public List<QueryDescVo> queryRepeatMsg(Long id) {
        List<QueryDescVo> queryDescVos = new ArrayList<>();
        if (id != null) {
            List<CheckReview> repeatMessageInfos = repeatMessageDao.queryRepeatByReviewId(id);
            for (CheckReview checkReview : repeatMessageInfos) {
                QueryDescVo queryDescVo = new QueryDescVo();
                queryDescVo.setStatus(checkReview.getRepeats());
                queryDescVo.setPhone(checkReview.getPhone());
                ExpertInfo expertInfo = repeatMessageDao.queryName(checkReview.getPhone());
                queryDescVo.setName(expertInfo.getName());
                queryDescVo.setWorkNum(expertInfo.getWorkNumber());
                queryDescVos.add(queryDescVo);
            }

        }
        return queryDescVos;
    }


    /**
     * 随机抽取3个手机号
     *
     * @param list
     * @return
     */
    public static List<String> GetRandomThreeInfoList(List<String> list, int count) {
        List<String> olist = new ArrayList<>();
        if (list.size() <= count) {
            return list;
        } else {
            Random random = new Random();
            for (int i = 0; i < count; i++) {
                int intRandom = random.nextInt(list.size() - 1);
                olist.add(list.get(intRandom));
                list.remove(list.get(intRandom));
            }
            return olist;
        }
    }

    private String sendSms(long reviewId, String phone) throws Exception {
        com.aliyun.dysmsapi20170525.Client client = Sample.createClient(accessKeyId, accessKeySecret);
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phone)
                .setSignName(signName)
                .setTemplateCode(templateCode);
        // 复制代码运行请自行打印 API 的返回值
        SendSmsResponse response = client.sendSms(sendSmsRequest);
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //同步插入记录短信回复
        RepeatMessageInfo repeatMessageInfo = new RepeatMessageInfo();
        repeatMessageInfo.setTime(Timestamp.valueOf(time));
        repeatMessageInfo.setPhone(phone);
        repeatMessageInfo.setReview(reviewId);

        CheckReview checkReview = new CheckReview();
        checkReview.setReview(reviewId);
        checkReview.setPhone(phone);
        checkReview.setStatus("0");
        CheckReview review = checkReviewDao.queryByReviewIdAndPhone(reviewId, phone);

        if ("ok".equalsIgnoreCase(response.getBody().getMessage())) {
            repeatMessageDao.insert(repeatMessageInfo);
            if (review == null) {
                checkReviewDao.insert(checkReview);
            }
            return "短信发送成功！";
        }
        return "短信发送失败！";
    }

    private String setTemplateParam() {
        //短信通知参数json格式
        SmsParam smsParamVo = new SmsParam();
        //设置短信通知模板里面的变量值
        smsParamVo.setHour(param);
        String smsParam = JSONObject.toJSONString(smsParamVo);
        System.out.println("新版本短信通知参数smsParam:" + smsParam);
        //模板中的变量替换JSON串,如模板内容为"亲爱的${hour},您的验证码为${code}"时,此处的值为
        return smsParam;
    }
}
