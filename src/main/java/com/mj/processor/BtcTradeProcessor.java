package com.mj.processor;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mj.activity.BtcServer;
import com.mj.common.cache.CacheManager;
import com.mj.model.BtcModel;
import com.mj.model.NotifyModel;
import com.mj.util.date.VeDate;
import com.mj.util.mail.MailUtil;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class BtcTradeProcessor implements PageProcessor {
	private static final Logger logger = LoggerFactory
			.getLogger(BtcTradeProcessor.class);
	// 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
	private Site site = Site.me().setRetryTimes(5).setSleepTime(1000);

	// process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
	public void process(Page page) {
		// TODO Auto-generated method stub
		// page.setSkip(true);
		if (page != null) {
			logger.debug(page.getJson().toString());
			// MailUtil.mail("数据", page.getJson().toString());
			BtcModel model = JSON.parseObject(page.getJson().toString(),
					BtcModel.class);
			if (model != null) {
				model.type = page.getRequest().getUrl().replace("http://api.btctrade.com/api/ticker?coin=", "btctrade_");
				logger.debug(model.toString());
				if (CacheManager.getInstance().getCache(model.type) == null) {
					CacheManager.getInstance().putCache(model.type,
							"20170101010101");
				}
				logger.debug("low is " + model.low + " 低于多少 "
						+ (model.low + (model.low * 0.01)));
				;
				if ((model.low + (model.low * 0.01)) >= model.last) {
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
								+ model.low + " 最高 " + model.high;
						MailUtil.mail(notifyModel.head, notifyModel.body);
					}
				}
			}
		}
	}

	public Site getSite() {
		return site;
	}

}

