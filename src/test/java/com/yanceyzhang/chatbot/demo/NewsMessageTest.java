package com.yanceyzhang.chatbot.demo;

import org.junit.Test;

import com.yanceyzhang.commons.wx.chatbot.SendResult;
import com.yanceyzhang.commons.wx.chatbot.WxChatbotClient;
import com.yanceyzhang.commons.wx.chatbot.message.NewsArticle;
import com.yanceyzhang.commons.wx.chatbot.message.NewsMessage;

/**
 * 
 */
public class NewsMessageTest {

    private WxChatbotClient client = new WxChatbotClient();

    @Test
    public void testSendNewsMessage() throws Exception {

    	NewsArticle article=new NewsArticle();
    	article.setTitle("中秋节礼品领取");
    	article.setDescription("今年中秋节公司有豪礼相送");
    	article.setUrl("https://work.weixin.qq.com/api/doc#90000/90135/91760");
    	article.setPicurl("http://res.mail.qq.com/node/ww/wwopenmng/images/independent/doc/test_pic_msg1.png");
    	
    	NewsMessage message=new NewsMessage();
    	message.addNewsArticle(article);
    	SendResult result = client.send(TestConfig.CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }

}