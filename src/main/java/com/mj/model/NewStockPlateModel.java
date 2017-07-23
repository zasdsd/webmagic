package com.mj.model;

public class NewStockPlateModel {
	 public String symbol;//:"sh601366",
	 public String code;//:"601366",
	 public String name;//"��Ⱥ�ɷ�",
	 public double trade;//���r:"13.870",
	 //pricechange:"0.000",
	 //changepercent:"0.000",
	 //buy:"13.850",
	 //sell:"13.860",
	 public double settlement;//����:"13.870",
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
	 //�Q�� turnoverratio:7.09406
	 
	 public double getRate(){
		 double amt = trade - settlement; // ���³ɽ���-�������̼�
		 double rate = amt * 100 / settlement; // (���³ɽ���-�������̼�)/�������̼�
         if (rate <= -100) {
             rate = 0;
         }
         return rate;
	 }
	 
	 public double getOpenRate(){
		 double amt = open - settlement; // ���³ɽ���-�������̼�
		 double rate = amt * 100 / settlement; // (���³ɽ���-�������̼�)/�������̼�
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
