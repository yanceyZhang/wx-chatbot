package com.yanceyzhang.commons.wx.chatbot.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * 图文类型
 * @author yanceyzhang
 *
 */
public class NewsMessage implements Message {
	public static final int MAX_ARTICLE_CNT = 8;
	public static final int MIN_ARTICLE_CNT = 1;
	
	private List<NewsArticle> articles = new ArrayList<NewsArticle>();
	
	 public void addNewsArticle(NewsArticle newsArticle) {
        if (articles.size() > MAX_ARTICLE_CNT) {
            throw new IllegalArgumentException("number of articles can't more than " + MAX_ARTICLE_CNT);
        }
        articles.add(newsArticle);
	 }
	 
	public String toJsonString() {
        Map<String, Object> items = new HashMap<String, Object>();
        items.put("msgtype", "news");

        Map<String, Object> news = new HashMap<String, Object>();
        if (articles.size() < MIN_ARTICLE_CNT) {
            throw new IllegalArgumentException("number of articles can't less than " + MIN_ARTICLE_CNT);
        }
        news.put("articles", articles);
        items.put("news", news);
        return JSON.toJSONString(items);
    }
}
