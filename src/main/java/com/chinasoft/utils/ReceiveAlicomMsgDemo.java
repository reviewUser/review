//package com.chinasoft.utils;
//
//import com.alibaba.druid.support.logging.Log;
//import com.alibaba.druid.support.logging.LogFactory;
//import com.alicom.mns.tools.DefaultAlicomMessagePuller;
//import com.alicom.mns.tools.MessageListener;
//import com.aliyun.mns.model.Message;
//import com.google.gson.Gson;
//
//import java.text.ParseException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class ReceiveAlicomMsgDemo {
//    private static Log logger= LogFactory.getLog(ReceiveAlicomMsgDemo.class);
//    //以短信回执消息处理为例
//    static class MyMessageListener implements MessageListener {
//        private Gson gson=new Gson();
//        @Override
//        public boolean dealMessage(Message message) {
//            System.out.println("message handle: " + message.getReceiptHandle());
//            System.out.println("message body: " + message.getMessageBodyAsString());
//            System.out.println("message id: " + message.getMessageId());
//            System.out.println("message dequeue count:" + message.getDequeueCount());
//            //以短信回执消息处理为例
//            try{
//                Map<String,Object> contentMap=gson.fromJson(message.getMessageBodyAsString(), HashMap.class);
//                String phoneNumber=(String)contentMap.get("phone_number");
//                Boolean success=(Boolean)contentMap.get("success");
//                String bizId=(String)contentMap.get("biz_id");
//                String outId=(String)contentMap.get("out_id");
//                String sendTime=(String)contentMap.get("send_time");
//                String reportTime=(String)contentMap.get("report_time");
//                String errCode=(String)contentMap.get("err_code");
//                String errMsg=(String)contentMap.get("err_msg");
//                //TODO 这里开始写业务代码
//            }catch(com.google.gson.JsonSyntaxException e){
//                logger.error("error_json_format:"+message.getMessageBodyAsString(),e);
//            }
//            Boolean dealResult=true;
//            return dealResult;//返回true，则工具类自动删除已拉取的消息。
//        }
//    }
//    public static void main(String[] args) throws com.aliyuncs.exceptions.ClientException, ParseException {
//        DefaultAlicomMessagePuller puller=new DefaultAlicomMessagePuller();
//        String accessKeyId = "LTAI5t7k9d1kdvy9mXaTND2q";
//        String accessKeySecret = "pOxcqfaacZseCyUQtZq21lOQ22qlZG";
//        String messageType="SmsReport";//短信回执：SmsReport，短信上行：SmsUp
//        String queueName="Alicom-Queue-1763481093987402-SmsReport";//在云通信页面开通相应业务消息后，就能在页面上获得对应的queueName
//        puller.startReceiveMsg(accessKeyId,accessKeySecret ,messageType,queueName , new MyMessageListener());
//    }
//}
