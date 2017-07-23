package com.mj.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import us.codecraft.webmagic.Spider;

import com.mj.processor.BtcProcessor;

public class BtcJob implements Job {
//http://www.btc38.com/httpAPI.php
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Spider.create(new BtcProcessor())
				.addUrl("http://api.btc38.com/v1/ticker.php?c=all&mk_type=cny")
				// ����5���߳�ץȡ
				.thread(1)
				// ��������
				.run();
	}

}
