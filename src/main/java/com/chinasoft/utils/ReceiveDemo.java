package com.chinasoft.utils;

import com.alicom.mns.tools.DefaultAlicomMessagePuller;
import com.alicom.mns.tools.MessageListener;
import com.aliyun.mns.model.Message;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 只能用于接收云通信的消息，不能用于接收其他业务的消息
 * 短信上行消息接收demo
 */
@Component
public class ReceiveDemo {

    private static Logger logger = Logger.getLogger(ReceiveDemo.class.getName());

    static class MyMessageListener implements MessageListener {
        private Gson gson = new Gson();

        @Override
        public boolean dealMessage(Message message) {

            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            //消息的几个关键值
            System.out.println("message receiver time from mns:" + format.format(new Date()));
            System.out.println("message handle: " + message.getReceiptHandle());
            System.out.println("message body: " + message.getMessageBodyAsString());
            System.out.println("message id: " + message.getMessageId());
            System.out.println("message dequeue count:" + message.getDequeueCount());
            System.out.println("Thread:" + Thread.currentThread().getName());
            try {
                Map<String, Object> contentMap = gson.fromJson(message.getMessageBodyAsString(), HashMap.class);
                String phoneNumber = (String) contentMap.get("phone_number");
                System.out.println("============手机号========" + phoneNumber + "\n");

                String content = (String) contentMap.get("content");
                System.out.println("============短信内容========" + content + "\n");

                String destCode = (String) contentMap.get("dest_code");
                System.out.println("============扩展码========" + destCode + "\n");

                String sequenceId = (String) contentMap.get("sequence_id");
                System.out.println("============消息序列id========" + sequenceId + "\n");


            } catch (com.google.gson.JsonSyntaxException e) {
                logger.log(Level.SEVERE, "error_json_format:" + message.getMessageBodyAsString(), e);
                //理论上不会出现格式错误的情况，所以遇见格式错误的消息，只能先delete,否则重新推送也会一直报错
                return true;
            } catch (Throwable e) {
                //您自己的代码部分导致的异常，应该return false,这样消息不会被delete掉，而会根据策略进行重推
                return false;
            }

            //消息处理成功，返回true, SDK将调用MNS的delete方法将消息从队列中删除掉
            return true;
        }

    }

    public static void main(String[] args) throws Exception, ParseException {

        DefaultAlicomMessagePuller puller = new DefaultAlicomMessagePuller();

        //设置异步线程池大小及任务队列的大小，还有无数据线程休眠时间
        puller.setConsumeMinThreadSize(6);
        puller.setConsumeMaxThreadSize(16);
        puller.setThreadQueueSize(200);
        puller.setPullMsgThreadSize(1);
        //和服务端联调问题时开启,平时无需开启，消耗性能
        puller.openDebugLog(false);
        String accessKeyId = "LTAI5t7k9d1kdvy9mXaTND2q";
        String accessKeySecret = "pOxcqfaacZseCyUQtZq21lOQ22qlZG";

        String messageType = "SmsUp";//此处应该替换成相应产品的消息类型
        String queueName = "Alicom-Queue-1763481093987402-SmsUp";//在云通信页面开通相应业务消息后，就能在页面上获得对应的queueName,格式类似Alicom-Queue-xxxxxx-SmsReport

        puller.startReceiveMsg(accessKeyId, accessKeySecret, messageType, queueName, new MyMessageListener());
    }
}
