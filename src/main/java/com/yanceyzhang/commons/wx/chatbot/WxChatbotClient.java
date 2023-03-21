package com.yanceyzhang.commons.wx.chatbot;

import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.yanceyzhang.commons.wx.chatbot.contants.Constants;
import com.yanceyzhang.commons.wx.chatbot.message.Message;

import cn.hutool.http.HttpRequest;

/**
 */
public class WxChatbotClient {

    static HttpClient httpclient = HttpClients.createDefault();

    public static SendResult send(String key, Message message) throws IOException{
    	if(StringUtils.isBlank(key)){
    		return new SendResult();
    	}
    	String webhook = String.format(Constants.webhook_url, key);
    	
        HttpPost httppost = new HttpPost(webhook);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        StringEntity se = new StringEntity(message.toJsonString(), "utf-8");
        httppost.setEntity(se);

        SendResult sendResult = new SendResult();
        HttpResponse response = httpclient.execute(httppost);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = EntityUtils.toString(response.getEntity());
            JSONObject obj = JSONObject.parseObject(result);
            Integer errcode = obj.getInteger("errcode");
            sendResult.setErrorCode(errcode);
            sendResult.setErrorMsg(obj.getString("errmsg"));
            sendResult.setIsSuccess(errcode.equals(0));
        }
        return sendResult;
    }
    
    public static String getMediaId(byte[] bytes, String fileName, String key) throws IOException{
    	if(StringUtils.isBlank(key)){
    		return "";
    	}
    	String webhook = String.format(Constants.upload_media_url, key);
        HttpRequest request = HttpRequest.post(webhook);
        request.header("Content-Type", "multipart/form-data")
        .header("Content-Disposition",String.format("form-data; name=\"media\";filename=\"%s\"; filelength=%s", fileName, bytes.length))
        .form("file", bytes,fileName);
    	cn.hutool.http.HttpResponse response=request.execute();
    	JSONObject obj = JSONObject.parseObject(response.body());
 	    if(obj.containsKey("media_id")) {
 	    	return obj.getString("media_id");
 	    }
        return null;
    }
}


