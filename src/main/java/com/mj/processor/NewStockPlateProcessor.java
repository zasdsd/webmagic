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
import com.mj.model.NewStockPlateModel;
import com.mj.model.NotifyModel;
import com.mj.util.date.VeDate;
import com.mj.util.mail.MailUtil;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class NewStockPlateProcessor implements PageProcessor {
	private static final Logger logger = LoggerFactory
			.getLogger(NewStockPlateProcessor.class);
	private Site site = Site.me().setRetryTimes(5).setSleepTime(1000);

	// process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
	public void process(Page page) {
		if (page != null) {
			List<NewStockPlateModel> modellist = JSON.parseArray(page.getJson()
					.toString(), NewStockPlateModel.class);
			if(modellist==null)return;
			for (NewStockPlateModel item : modellist) {
				if (item != null) {
					logger.debug(item.toString());
					if (CacheManager.getInstance().getCache(item.symbol) == null) {
						CacheManager.getInstance().putCache(item.symbol,
								"20170101010101");
					}
					if (item.isNeedMail()) {
						logger.info(item.symbol+"需要發送郵件");
						String nowStr = VeDate
								.getyyyyMMddHHmmss(new Date());
						String oldDateStr = String.valueOf(CacheManager
								.getInstance().getCache(item.symbol));
						if (!VeDate.halfHour(
								VeDate.strLongToDate(oldDateStr),
								VeDate.strLongToDate(nowStr))) {
							CacheManager.getInstance().putCache(item.symbol,
									nowStr);
							NotifyModel notifyModel = new NotifyModel();
							notifyModel.head = "次新股 "+item.name +"("+item.symbol+")" + " 出现涨停松动";
							notifyModel.body = " 最新 " + item.trade + 
									" 涨幅 "+ item.getRate()
									+ " 开盘涨幅  " + item.getOpenRate()
									+ ""
									;
							MailUtil.mail(notifyModel.head,
									notifyModel.body);
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
