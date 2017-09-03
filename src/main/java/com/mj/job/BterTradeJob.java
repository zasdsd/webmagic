package com.mj.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import us.codecraft.webmagic.Spider;

import com.mj.processor.BtcProcessor;
import com.mj.processor.BtcTradeProcessor;
import com.mj.processor.BterProcessor;

public class BterTradeJob implements Job {
	// BTC£ºhttp://api.btctrade.com/api/ticker?coin=btc
	// ETH£ºhttp://api.btctrade.com/api/ticker?coin=eth
	// LTC£ºhttp://api.btctrade.com/api/ticker?coin=ltc
	// DOGE£ºhttp://api.btctrade.com/api/ticker?coin=doge
	// YBC£ºhttp://api.btctrade.com/api/ticker?coin=ybc
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Spider.create(new BterProcessor())
		.addUrl("http://data.bter.com/api/1/tickers")
				// ï¿½ï¿½ï¿½ï¿½5ï¿½ï¿½ï¿½ß³ï¿½×¥È¡
				.thread(1)
				// ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
				.run();
	}

}
