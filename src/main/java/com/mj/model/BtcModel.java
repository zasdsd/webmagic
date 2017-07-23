package com.mj.model;

public class BtcModel {
	public String type; // 比特币类型
	public double high;// 最高价 17200,
	public double low;// 最低价
	public double last;// 最后面的价格
	public double vol;// : 1141.6968, 成交量
	public double buy;// 16670.2, 买一
	public double sell;// 16747 卖一

	@Override
	public String toString() {
		return "BtcModel [type=" + type + ", high=" + high + ", low=" + low
				+ ", last=" + last + ", vol=" + vol + ", buy=" + buy
				+ ", sell=" + sell + "]";
	}
}
