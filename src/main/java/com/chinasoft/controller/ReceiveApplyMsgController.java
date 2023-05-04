package com.chinasoft.controller;


import com.alibaba.fastjson.JSONObject;
import com.alicom.mns.tools.DefaultAlicomMessagePuller;
import com.alicom.mns.tools.MessageListener;
import com.aliyun.mns.model.Message;
import com.aliyuncs.exceptions.ClientException;
import com.chinasoft.dao.RepeatMessageDao;
import com.chinasoft.po.RepeatMessageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Component
public class ReceiveApplyMsgController {
    @Value("${aliyun.sms.access-key-id}")
    private String accessKeyId;

    @Value("${aliyun.sms.access-key-secret}")
    private String accessKeySecret;

    @Value("${aliyun.sms.queue-Name}")
    private String smsUpQueueName;

    private static ReceiveApplyMsgController receiveApplyMsgController;

    @Autowired
    private RepeatMessageDao repeatMessageDao;

    private static RepeatMessageDao staticRepeatMessageDao;

    private final String messageType = "SmsUp";

    /**
     * 初始化
     *
     * @throws ClientException
     */
    @PostConstruct
    public void initTravelApplyMsgReciever() throws ClientException {
        System.out.println("==============启动短信监听====================");
        staticRepeatMessageDao = this.repeatMessageDao;
        // 线下必须关闭 避免阿里短信回复队列被测试环境消费
        DefaultAlicomMessagePuller puller = new DefaultAlicomMessagePuller();
        // 设置异步线程池大小及任务队列的大小，还有无数据线程休眠时间
        puller.setConsumeMinThreadSize(6);
        puller.setConsumeMaxThreadSize(16);
        puller.setThreadQueueSize(200);
        puller.setPullMsgThreadSize(1);
        // 和服务端联调问题时开启,平时无需开启，消耗性能
        puller.openDebugLog(false);
        puller.startReceiveMsg(accessKeyId, accessKeySecret, messageType, smsUpQueueName, new MyMessageListener());
    }

    static class MyMessageListener implements MessageListener {

        @Override
        public boolean dealMessage(Message message) {
            System.out.println("==============接收短信回复开始====================");
            try {
                Map<String, Object> contentMap = JSONObject.parseObject(message.getMessageBodyAsString(), HashMap.class);
                String phoneNumber = (String) contentMap.get("phone_number");
                String sendTime = (String) contentMap.get("send_time");
                String content = (String) contentMap.get("content");
                System.out.println("==============短信回复内容===================" + content);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (StringUtils.isNotBlank(phoneNumber) && StringUtils.isNotBlank(content)) {
                    RepeatMessageInfo repeatMessageInfo = new RepeatMessageInfo();
                    repeatMessageInfo.setPhone(phoneNumber);
                    repeatMessageInfo.setRepeats(content);
                    staticRepeatMessageDao.updateByPhone(repeatMessageInfo);
                }
                System.out.println("==============接收短信回复结束====================");
                return true;

            } catch (com.google.gson.JsonSyntaxException e) {
                return true;
            } catch (Throwable e) {
                return false;
            }
        }
    }
}
