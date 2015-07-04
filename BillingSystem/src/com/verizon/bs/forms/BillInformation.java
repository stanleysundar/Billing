package com.verizon.bs.forms;

public class BillInformation {
	public String cust_name = null;
	public String meter_id = null;
	public String start_date = null;
	public String end_date = null;

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getMeter_id() {
		return meter_id;
	}

	public void setMeter_id(String meter_id) {
		this.meter_id = meter_id;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getMinMeterReading() {
		return minMeterReading;
	}

	public void setMinMeterReading(String minMeterReading) {
		this.minMeterReading = minMeterReading;
	}

	public String getMaxMeterReading() {
		return maxMeterReading;
	}

	public void setMaxMeterReading(String maxMeterReading) {
		this.maxMeterReading = maxMeterReading;
	}

	public long getTotal_units() {
		return total_units;
	}

	public void setTotal_units(long total_units) {
		this.total_units = total_units;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String minMeterReading;
	public String maxMeterReading;
	public long total_units;
	public double rate;
	public double tax;
	public double total;

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
}
