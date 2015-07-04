package com.verizon.bs.vo;

public class ReadingObject 
{
	public long minMeterReading;
	public long maxMeterReading;
	public double rate;
	
	public long getMinMeterReading() {
		return minMeterReading;
	}
	public void setMinMeterReading(long minMeterReading) {
		this.minMeterReading = minMeterReading;
	}
	public long getMaxMeterReading() {
		return maxMeterReading;
	}
	public void setMaxMeterReading(long maxMeterReading) {
		this.maxMeterReading = maxMeterReading;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
}
