package com.mj.job;

import java.util.ArrayList;

import org.apache.commons.lang.ArrayUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import us.codecraft.webmagic.Spider;

import com.mj.processor.BtcProcessor;
import com.mj.processor.MiaoAProcessor;
import com.mj.processor.NewStockPlateProcessor;

public class NewStockPlateJob implements Job {
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			int j = i + 1;
			list.add("http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/Market_Center.getHQNodeData?page="+j+"&num=40&sort=symbol&asc=1&node=new_stock&symbol=&_s_r_a=init");
		}
		String[] arrString = (String[]) list.toArray(new String[0]);  
		Spider.create(new NewStockPlateProcessor()).addUrl(arrString)
				.thread(1)
				.run();
	}

	//

}
