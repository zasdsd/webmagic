package com.mj.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import us.codecraft.webmagic.Spider;

import com.mj.processor.BtcProcessor;
import com.mj.processor.BtcTradeProcessor;
import com.mj.processor.BterProcessor;

public class BterTradeJob implements Job {
	// BTC��http://api.btctrade.com/api/ticker?coin=btc
	// ETH��http://api.btctrade.com/api/ticker?coin=eth
	// LTC��http://api.btctrade.com/api/ticker?coin=ltc
	// DOGE��http://api.btctrade.com/api/ticker?coin=doge
	// YBC��http://api.btctrade.com/api/ticker?coin=ybc
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Spider.create(new BterProcessor())
		.addUrl("http://data.bter.com/api/1/tickers")
				// ����5���߳�ץȡ
				.thread(1)
				// ��������
				.run();
	}

}
