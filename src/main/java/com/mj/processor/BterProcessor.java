package com.mj.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mj.activity.BtcServer;
import com.mj.common.cache.CacheManager;
import com.mj.model.BtcModel;
import com.mj.model.BterEntity;
import com.mj.model.NotifyModel;
import com.mj.util.date.VeDate;
import com.mj.util.mail.MailUtil;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class BterProcessor implements PageProcessor {
	private static final Logger logger = LoggerFactory
			.getLogger(BterProcessor.class);
	// 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
	private Site site = Site.me().setRetryTimes(5).setSleepTime(1000);
//	private static ConcurrentHashMap<String, String> hashMap = new ConcurrentHashMap<String, String>();
//	static{
//		hashMap.put("", "");
//	}
	// process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
	public void process(Page page) {
		// TODO Auto-generated method stub
		// page.setSkip(true);
		if (page != null) {
			// logger.debug(page.getJson().toString());
			String jsonStr = page.getJson().toString();
			JSONObject modelJson = JSON.parseObject(jsonStr);
			// \"([A-Za-z]{2,5})_cny\"(\\:)(\\{)
			final String regex2 = "\"([A-Za-z]{2,5})_cny\"\\:\\{"; // 总
			final Pattern pa2 = Pattern.compile(regex2, Pattern.DOTALL); // 总
			final Matcher ma2 = pa2.matcher(jsonStr);
			ArrayList<BterEntity> entityList = new ArrayList<BterEntity>();
			BterEntity findItem = null;
			String findType = null;
			while (ma2.find()) {
				logger.debug("ma2.find() is " + ma2.groupCount() + " "
						+ ma2.group(0));
				findType =ma2.group(0).replace("\"", "").replace(":{", "");
				logger.debug("findType is " +findType);
				findItem = JSON.parseObject(modelJson.getString(findType), BterEntity.class);
				findItem.type = findType;
				entityList.add(findItem);
			}
			for (BterEntity model : entityList) {
				if (model != null) {
					if (CacheManager.getInstance().getCache(model.type) == null) {
						CacheManager.getInstance().putCache(model.type,
								"20170101010101");
					}
					logger.debug("low is " + model.low + " 低于多少 "
							+ (model.low + (model.low * 0.01)));
					;
					if (model.low >= model.last ) {
						String nowStr = VeDate.getyyyyMMddHHmmss(new Date());
						String oldDateStr = String.valueOf(CacheManager
								.getInstance().getCache(model.type));
						if (!VeDate.halfHour(VeDate.strLongToDate(oldDateStr),
								VeDate.strLongToDate(nowStr))) {
							CacheManager.getInstance().putCache(model.type,
									nowStr);
							NotifyModel notifyModel = new NotifyModel();
							notifyModel.head = model.type + "出现最低价";
							notifyModel.body = " 最新 " + model.last + " 最低 "
									+ model.low + " 最高 " + model.high + " 涨幅 "+model.rate_change_percentage+"%";
							MailUtil.mail(notifyModel.head, notifyModel.body);
						}
					}
				}
			}

		}
	}

	public Site getSite() {
		return site;
	}

}