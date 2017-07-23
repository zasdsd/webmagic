package com.mj.processor;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mj.activity.BtcServer;
import com.mj.common.cache.CacheManager;
import com.mj.model.BtcModel;
import com.mj.model.MiaoAInfoModel;
import com.mj.model.NotifyModel;
import com.mj.util.date.VeDate;
import com.mj.util.mail.MailUtil;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class MiaoAProcessor implements PageProcessor {
	private static final Logger logger = LoggerFactory
			.getLogger(MiaoAProcessor.class);
	// 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
	private Site site = Site.me().setRetryTimes(5).setSleepTime(1000);

	// process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
	public void process(Page page) {
		// page.setSkip(true);
		if (page != null) {
			 //区分是列表页面还是信息页面  
	        if (page.getUrl().regex("publishInformation.do?pcode=").match()) {  
	        	
	        }else{
	        	JSONObject jsonObj = JSON.parseObject(page.getJson().toString());
				if (jsonObj != null) {
					if (jsonObj.containsKey("LIST")) {
						JSONArray arrayList = jsonObj.getJSONArray("LIST");
						if (arrayList != null && arrayList.size() > 0) {
							List<MiaoAInfoModel> modellist = JSON
									.parseArray(jsonObj.getString("LIST"),
											MiaoAInfoModel.class);
							for (MiaoAInfoModel item : modellist) {
								page.addTargetRequest("https://sjs.miaoa.com/uusjs/publishInformation.do?pcode="
										+ item.investorsCode);
								logger.debug(item.toString());
							}
						}
					}
				}
	        }
			
		}
	}

	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

}
