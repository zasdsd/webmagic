package com.mj.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import us.codecraft.webmagic.Spider;

import com.mj.processor.BtcProcessor;
import com.mj.processor.BtcTradeProcessor;

public class BtcTradeJob implements Job {
	// BTC£ºhttp://api.btctrade.com/api/ticker?coin=btc
	// ETH£ºhttp://api.btctrade.com/api/ticker?coin=eth
	// LTC£ºhttp://api.btctrade.com/api/ticker?coin=ltc
	// DOGE£ºhttp://api.btctrade.com/api/ticker?coin=doge
	// YBC£ºhttp://api.btctrade.com/api/ticker?coin=ybc
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Spider.create(new BtcTradeProcessor())
				.addUrl("http://api.btctrade.com/api/ticker?coin=btc",
						"http://api.btctrade.com/api/ticker?coin=eth",
						"http://api.btctrade.com/api/ticker?coin=ltc",
						"http://api.btctrade.com/api/ticker?coin=doge",
						"http://api.btctrade.com/api/ticker?coin=ybc")
				// ï¿½ï¿½ï¿½ï¿½5ï¿½ï¿½ï¿½ß³ï¿½×¥È¡
				.thread(2)
				// ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
				.run();
	}

}
