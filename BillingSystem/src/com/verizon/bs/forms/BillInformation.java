package com.verizon.bs.forms;

public class BillInformation {
	public String cust_name = null;
	public String phone_num = null;
	public String currency_type = null;
	public String invoice_id = null;
	public String getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}

	public String getCurrency_type() {
		return currency_type;
	}

	public void setCurrency_type(String currency_type) {
		this.currency_type = currency_type;
	}

	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}

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

	public String getTotal_units() {
		return total_units;
	}

	public void setTotal_units(String total_units) {
		this.total_units = total_units;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String minMeterReading;
	public String maxMeterReading;
	public String total_units;
	public String rate;
	public String tax;
	public String total;

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
}
