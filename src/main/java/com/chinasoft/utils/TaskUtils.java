package com.chinasoft.utils;

import com.chinasoft.dao.CheckReviewDao;
import com.chinasoft.dao.ExpertInfoDao;
import com.chinasoft.dao.RepeatMessageDao;
import com.chinasoft.po.CheckReview;
import com.chinasoft.po.ExpertInfo;
import com.chinasoft.po.RepeatMessageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskUtils {
    @Autowired
    private ExpertInfoDao expertInfoDao;

    @Autowired
    private RepeatMessageDao repeatMessageDao;

    @Autowired
    private CheckReviewDao checkReviewDao;

    // 添加定时任务
    @Scheduled(cron = "0/10 * * * * ?")
    public void doTask() throws ParseException {
        List<RepeatMessageInfo> repeatMessageInfos = repeatMessageDao.queryAllMsg();
        List<String> phones = repeatMessageInfos.stream().map(RepeatMessageInfo::getPhone).collect(Collectors.toList());
        if (phones.size() > 0) {
            for (String str : phones) {
                updateExpert(str);
            }
        }
        System.out.println("我是定时任务~");
    }

    private void updateExpert(String phone) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        RepeatMessageInfo msgInfo = repeatMessageDao.queryMsgByPhone(phone);
        ExpertInfo info = expertInfoDao.queryExpertByPhone(phone);
        String fromDate = format.format(msgInfo.getTime());
        String nowTime = format.format(new Date());
        long from = format.parse(fromDate).getTime();
        long to = format.parse(nowTime).getTime();
        int hours = (int) ((to - from) / (1000 * 60 * 60));
        CheckReview checkReview = new CheckReview();
        checkReview.setPhone(phone);
        if (StringUtils.isNotBlank(msgInfo.getRepeats()) && msgInfo.getRepeats().contains("1")) {
            info.setIntegral(info.getIntegral() + 1);
            expertInfoDao.updateExpertByPhone(info);
            checkReview.setReview(msgInfo.getReview());
            checkReview.setStatus("1");
            checkReview.setRepeats("同意");
            checkReviewDao.updateStatus(checkReview);
            repeatMessageDao.delMsgByPhone(phone);
        } else if (hours >= 12 && StringUtils.isBlank(msgInfo.getRepeats()) ||
                StringUtils.isNotBlank(msgInfo.getRepeats()) && !msgInfo.getRepeats().contains("1")) {
            info.setRefuseCount(info.getRefuseCount() + 1);
            expertInfoDao.updateExpertByPhone(info);
            checkReview.setReview(msgInfo.getReview());
            checkReview.setStatus("1");
            checkReview.setRepeats("拒绝");
            checkReviewDao.updateStatus(checkReview);
            repeatMessageDao.delMsgByPhone(phone);
        }
    }
}