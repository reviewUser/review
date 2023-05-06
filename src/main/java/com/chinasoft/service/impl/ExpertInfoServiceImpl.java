package com.chinasoft.service.impl;

import com.alibaba.excel.EasyExcel;
import com.chinasoft.dao.ExpertInfoDao;
import com.chinasoft.param.ExpertParam;
import com.chinasoft.po.ExpertInfo;
import com.chinasoft.po.PwdInfo;
import com.chinasoft.po.SysUser;
import com.chinasoft.service.ExpertInfoService;
import com.chinasoft.utils.ImportExcelUtils;
import com.chinasoft.utils.Md5Utils;
import com.chinasoft.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 系统用户业务层接口实现
 *
 * @author 王鹏
 */
@Service
public class ExpertInfoServiceImpl implements ExpertInfoService {

    @Autowired
    private ExpertInfoDao expertInfoDao;

    @Value("${expert.refuse-count}")
    private Integer refuseCount;

    /**
     * 新增专家信息
     *
     * @param expertInfo
     */
    @Override
    public void insert(ExpertInfo expertInfo) {
        if (expertInfo != null)
            expertInfoDao.insert(expertInfo);
    }

    @Override
    public List<ExpertInfo> queryExpertInfo(ExpertParam param) {
        List<ExpertInfo> infos = expertInfoDao.queryExpertInfo(param);
        infos.forEach(p -> {
            if (p.getRefuseCount() > refuseCount) {
                expertInfoDao.updateExpertStatus(p.getId());
            }
        });
        return infos;
    }

    @Override
    public int getTotalCount(ExpertParam param) {
        if (param != null) {
            return expertInfoDao.getTotalCount(param);
        }
        return 0;
    }

    @Override
    public Map<String, Object> importExpert(MultipartFile file) {
        Map<String, Object> resultMap = new HashMap<>();
        List<ExpertInfo> expertInfos = new ArrayList<>();
        try {
            //验证文件类型
            if (!file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).equals(".xls") && !file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).equals(".xlsx")) {
                resultMap.put("msg", "文件类型有误！请上传Excle文件");
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
                ExpertInfo expertInfo = new ExpertInfo();
                //根据下标获取每一行的每一条数据
                if (String.valueOf(list.get(0)) == null || String.valueOf(list.get(0)).equals("")) {
                    resultMap.put("mete", "工号不能为空");
                    throw new Exception("工号不能为空");
                }
                expertInfo.setWorkNumber(String.valueOf(list.get(0)));
                if (String.valueOf(list.get(1)) == null || String.valueOf(list.get(1)).equals("")) {
                    resultMap.put("mete", "姓名不能为空");
                    throw new Exception("姓名不能为空");
                }
                expertInfo.setName(String.valueOf(list.get(1)));

                if (String.valueOf(list.get(2)) == null || String.valueOf(list.get(2)).equals("")) {
                    resultMap.put("mete", "职称不能为空");
                    throw new Exception("职称不能为空");
                }
                expertInfo.setLevel(String.valueOf(list.get(2)));

                if (String.valueOf(list.get(3)) == null || String.valueOf(list.get(3)).equals("")) {
                    resultMap.put("mete", "技术职级不能为空");
                    throw new Exception("技术职级不能为空");
                }
                expertInfo.setTechnologyLevel(String.valueOf(list.get(3)));
                if (String.valueOf(list.get(4)) == null || String.valueOf(list.get(4)).equals("")) {
                    resultMap.put("mete", "擅长专业领域不能为空");
                    throw new Exception("擅长专业领域不能为空");
                }
                expertInfo.setFieldName(String.valueOf(list.get(4)));
                if (String.valueOf(list.get(5)) == null || String.valueOf(list.get(5)).equals("")) {
                    resultMap.put("mete", "手机号不能为空");
                    throw new Exception("手机号不能为空");
                }
                expertInfo.setPhone(String.valueOf(list.get(5)));
                expertInfo.setBirthday(String.valueOf(list.get(6)));
                expertInfo.setAge(Long.parseLong(String.valueOf(list.get(7))));
                expertInfo.setIntegral(Long.parseLong(String.valueOf(list.get(8))));
                expertInfo.setRefuseCount(Long.parseLong(String.valueOf(list.get(9))));
                expertInfo.setExpertStatus(String.valueOf(list.get(10)));
                expertInfos.add(expertInfo);
            }
            int i = expertInfoDao.batchInsertExperts(expertInfos);
            if (i != 0) {
                resultMap.put("导入成功", 200);
            } else {
                resultMap.put("msg", "文档内无数据，请重新导入");
                resultMap.put("status", 500);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return resultMap;
        }
    }

    @Override
    public void exportExpert(List<Long> ids, HttpServletResponse response) {
        List<ExpertInfo> expertInfos = null;
        try {
            if (ids.size() == 0) {
                expertInfos = expertInfoDao.queryAllExpert();
            } else {
                expertInfos = expertInfoDao.queryExpertByIds(ids);
            }
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String fileName = "专家信息-" + sdf.format(new Date()) + ".xlsx";
            String fileNameURL = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileNameURL + ";" + "filename*=utf-8''" + fileNameURL);
            EasyExcel.write(response.getOutputStream(), ExpertInfo.class).sheet("专家基本信息").doWrite(expertInfos);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public Result delExperts(List<Long> ids) {
        Result result = new Result();
        if (ids.size() == 0) {
            result.setCode("500");
            result.setMsg("参数异常");
            return result;
        }
        int i = expertInfoDao.batchDelExperts(ids);
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
    public Result updatePwd(PwdInfo pwdInfo) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Result result = new Result();
        if (pwdInfo == null) {
            result.setCode("500");
            result.setMsg("参数异常");
            return result;
        }
        SysUser sysUser = expertInfoDao.queryUser(pwdInfo.getUsername());
        if (sysUser.getPassword().equals(Md5Utils.md5(pwdInfo.getOldPwd()))) {
            expertInfoDao.updatePwd(Md5Utils.md5(pwdInfo.getNewPwd()), pwdInfo.getUsername());
            result.setCode("200");
            result.setMsg("密码修改成功");
        } else {
            result.setCode("500");
            result.setMsg("旧密码错误");
        }
        return result;
    }
}
