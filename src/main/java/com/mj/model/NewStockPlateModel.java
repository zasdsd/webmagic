package com.mj.model;

public class NewStockPlateModel {
	 public String symbol;//:"sh601366",
	 public String code;//:"601366",
	 public String name;//"利群股份",
	 public double trade;//最新價:"13.870",
	 //pricechange:"0.000",
	 //changepercent:"0.000",
	 //buy:"13.850",
	 //sell:"13.860",
	 public double settlement;//昨收:"13.870",
	 public double open;//:"14.050",
	 public double high;//:"14.370",
	 public double low;//:"13.850",
	 //volume:12485546,
	 //amount:175816208,
	 //ticktime:"15:00:00",
	 //per:26.17,
	 //pb:3.342,
	 //mktcap:1193514.13802,
	 //nmc:244112,
	 //換手 turnoverratio:7.09406
	 
	 public double getRate(){
		 double amt = trade - settlement; // 最新成交价-昨日收盘价
		 double rate = amt * 100 / settlement; // (最新成交价-昨日收盘价)/昨日收盘价
         if (rate <= -100) {
             rate = 0;
         }
         return rate;
	 }
	 
	 public double getOpenRate(){
		 double amt = open - settlement; // 最新成交价-昨日收盘价
		 double rate = amt * 100 / settlement; // (最新成交价-昨日收盘价)/昨日收盘价
         if (rate <= -100) {
             rate = 0;
         }
         return rate;
	 }
	 
	 
	 public boolean isNeedMail(){
		 double openRate = getOpenRate();
		 double rate = getRate();
		 if(openRate>=9 && rate < openRate){
			 return true;
		 }
		 return false;
	 }
	 
	@Override
	public String toString() {
		return "NewStockPlateModel [symbol=" + symbol + ", code=" + code
				+ ", name=" + name + ", trade=" + trade + ", settlement="
				+ settlement + ", open=" + open + "]";
	}
		
}
