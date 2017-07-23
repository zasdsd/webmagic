package com.mj.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mj.job.BtcJob;
import com.mj.job.BtcTradeJob;
import com.mj.job.MiaoAJob;
import com.mj.job.NewStockPlateJob;
import com.mj.processor.BtcProcessor;
import com.mj.processor.NewStockPlateProcessor;
import com.mj.util.mail.MailUtil;
import com.mj.util.quartz.QuartzManager;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.SimpleHttpClient;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.example.GithubRepo;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;

public class BtcServer {
	private static final Logger logger = LoggerFactory
			.getLogger(BtcServer.class);

	public static void main(String[] args) {

		// MailUtil.mail("公司测试发送", "你收到了吗，如果没有请回复我，没有收到请回复我");
//		QuartzManager.addJob("比特时代", BtcJob.class.getName(),
//				"*/5 * 7-23 * * ?");
		QuartzManager.addJob("btctrade",
				BtcTradeJob.class.getName(), "*/5 * 7-23 * * ?");
		QuartzManager.addJob("次新股",
				NewStockPlateJob.class.getName(), "*/5 * 9-15 ? * MON-FRI");
		
//		QuartzManager.addJob("次新股",
//				NewStockPlateJob.class.getName(), "*/15 * * * * ?");
//		
//		QuartzManager.addJob(BtcServer.class.getName(),
//				MiaoAJob.class.getName(), "*/5 * * * * ?");
		
	}

}
