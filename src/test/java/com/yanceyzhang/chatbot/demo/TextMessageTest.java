package com.yanceyzhang.chatbot.demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.yanceyzhang.commons.wx.chatbot.SendResult;
import com.yanceyzhang.commons.wx.chatbot.WxChatbotClient;
import com.yanceyzhang.commons.wx.chatbot.message.TextMessage;

/**
 */
public class TextMessageTest {


    @Test
    public void testSendTextMessageWithAtAndAtAll() throws Exception {
        TextMessage message = new TextMessage("脸给你打歪");
        List<String> mentionedMobileList=new ArrayList<String>();
        mentionedMobileList.add("1832135XXXX");//@群内成员  手机号
        message.setMentionedMobileList(mentionedMobileList);
        message.setIsAtAll(true);//@所有人
        SendResult result = WxChatbotClient.send("key", message);
        System.out.println(result);
    }
}