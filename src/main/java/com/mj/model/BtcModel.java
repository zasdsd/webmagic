package com.mj.model;

public class BtcModel {
	public String type; // ���ر�����
	public double high;// ��߼� 17200,
	public double low;// ��ͼ�
	public double last;// �����ļ۸�
	public double vol;// : 1141.6968, �ɽ���
	public double buy;// 16670.2, ��һ
	public double sell;// 16747 ��һ

	@Override
	public String toString() {
		return "BtcModel [type=" + type + ", high=" + high + ", low=" + low
				+ ", last=" + last + ", vol=" + vol + ", buy=" + buy
				+ ", sell=" + sell + "]";
	}
}
