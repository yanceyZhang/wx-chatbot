package com.yanceyzhang.chatbot.demo;

import org.junit.Test;

import com.yanceyzhang.commons.wx.chatbot.SendResult;
import com.yanceyzhang.commons.wx.chatbot.WxChatbotClient;
import com.yanceyzhang.commons.wx.chatbot.message.ImageMessage;
import com.yanceyzhang.commons.wx.chatbot.utils.Base64Utils;
import com.yanceyzhang.commons.wx.chatbot.utils.dto.ImageBase64Md5;

/**
 */
public class ImageMessageTest {


    @Test
    public void imageMessageTest() throws Exception {
    	
    	String string = "https://c-ssl.duitang.com/uploads/blog/202008/22/20200822114634_2b3e0.thumb.1000_0.png";
		ImageBase64Md5  image = Base64Utils.ImageToBase64ByOnline(string);
		ImageMessage imageMessage = new ImageMessage(image.getBase64(),image.getMd5());
        SendResult result = WxChatbotClient.send(TestConfig.CHATBOT_WEBHOOK, imageMessage);
        System.out.println(result);
    }
}