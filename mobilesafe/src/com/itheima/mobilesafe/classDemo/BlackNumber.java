package com.itheima.mobilesafe.classDemo;

public class BlackNumber {
	
	String number;
	int mode;
	
	public BlackNumber(String number, int mode) {
		super();
		this.number = number;
		this.mode = mode;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return number+"/"+mode;
	}
	
}
