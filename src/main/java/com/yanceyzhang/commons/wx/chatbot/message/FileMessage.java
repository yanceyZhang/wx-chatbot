package com.yanceyzhang.commons.wx.chatbot.message;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;


/**
 * 文件类型
 * @author yanceyzhang
 *
 */
public class FileMessage implements Message {

    private String mediaId;

    public FileMessage(String mediaId) {
        this.mediaId = mediaId;
    }

	public String toJsonString() {
        Map<String, Object> items = new HashMap<String, Object>();
        items.put("msgtype", "file");
        Map<String, Object> file = new HashMap<String, Object>();
        file.put("media_id", mediaId);
        items.put("file", file);
        return JSON.toJSONString(items);
    }
}
