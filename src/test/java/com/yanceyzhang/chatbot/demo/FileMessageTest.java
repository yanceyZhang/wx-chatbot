package com.yanceyzhang.chatbot.demo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.UUID;

import org.apache.http.HttpHeaders;
import org.junit.Test;

import com.yanceyzhang.commons.wx.chatbot.SendResult;
import com.yanceyzhang.commons.wx.chatbot.WxChatbotClient;
import com.yanceyzhang.commons.wx.chatbot.message.FileMessage;
import com.yanceyzhang.commons.wx.chatbot.message.ImageMessage;
import com.yanceyzhang.commons.wx.chatbot.message.NewsMessage;
import com.yanceyzhang.commons.wx.chatbot.utils.Base64Utils;
import com.yanceyzhang.commons.wx.chatbot.utils.dto.ImageBase64Md5;

/**
 */
public class FileMessageTest {


    @Test
    public void fileMessageTest() throws Exception {
    	/**
    	 * https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=a4e0943c-4bd6-4e3e-ad7f-e808c2432e05
    	 */
    	String key="a4e0943c-4bd6-4e3e-ad7f-e808c2432e05";
    	String fileUrl="http://5b0988e595225.cdn.sohucs.com/images/20190114/f8479a6259f2431d992fb24b260e1e34.jpeg";
    	URL url = new URL(fileUrl);
		byte[] by = new byte[1024];
		// 创建链接
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(20000);
		conn.setReadTimeout(20000);
		InputStream is = conn.getInputStream();
		// 将内容读取内存中
		int len = -1;
		ByteArrayOutputStream data = new ByteArrayOutputStream();
		while ((len = is.read(by)) != -1) {
			data.write(by, 0, len);
		}
    	String mediaId  = WxChatbotClient.getMediaId(data.toByteArray(), UUID.randomUUID().toString()+".jpg", key);
    	System.out.println(mediaId);
    	FileMessage message=new FileMessage(mediaId);
        SendResult result = WxChatbotClient.send(key, message);
        System.out.println(result);
    }
}