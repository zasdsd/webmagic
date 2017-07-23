package com.mj.job;

import java.util.ArrayList;

import org.apache.commons.lang.ArrayUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import us.codecraft.webmagic.Spider;

import com.mj.processor.BtcProcessor;
import com.mj.processor.MiaoAProcessor;

public class MiaoAJob implements Job {
	// 2 ≈£»À
	// 5 “’»À
	// 7¥¥“µ’ﬂ
	// 8∆Û“µº“
	// https://sjs.miaoa.com/uusjs/queryStockMarket.do?currentPage=1&pageSize=15&sort=0&timeType=0&type=8
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			int j = i + 1;
			list.add("https://sjs.miaoa.com/uusjs/queryStockMarket.do?currentPage="
					+ j + "&pageSize=15&sort=0&timeType=0&type=2");
			list.add("https://sjs.miaoa.com/uusjs/queryStockMarket.do?currentPage="
					+ j + "&pageSize=15&sort=0&timeType=0&type=5");
			list.add("https://sjs.miaoa.com/uusjs/queryStockMarket.do?currentPage="
					+ j + "&pageSize=15&sort=0&timeType=0&type=7");
			list.add("https://sjs.miaoa.com/uusjs/queryStockMarket.do?currentPage="
					+ j + "&pageSize=15&sort=0&timeType=0&type=8");
		}
		String[] arrString = (String[]) list.toArray(new String[0]);  
		Spider.create(new MiaoAProcessor()).addUrl(arrString)
		// ÔøΩÔøΩÔøΩÔøΩ5ÔøΩÔøΩÔøΩﬂ≥ÔøΩ◊•»°
				.thread(1)
				// ÔøΩÔøΩÔøΩÔøΩÔøΩÔøΩÔøΩÔøΩ
				.run();
	}
	

	//

}
