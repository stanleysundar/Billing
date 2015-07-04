package com.verizon.bs.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.verizon.bs.vo.ReadingObject;

public class CalculateRate 
{
	public static void main(String arg[])
	{
		
		
		Connection conn = null;
		PreparedStatement pstmt = null,pstmt1=null;
		ResultSet rs = null,rs1=null;
		ReadingObject readingObj = null;
		ArrayList readingDetails = new ArrayList();
		String start_reading = null;
		String end_reading = null;
		try {
		conn = Database.getConnection();
		String query = "select start_reading,end_reading,rate from slab order by start_reading asc";
	
		pstmt = conn.prepareStatement(query);
		
		rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			readingObj = new ReadingObject();
			if (rs.getLong(1)!= 0)
			{
				readingObj.setMinMeterReading(rs.getLong(1)-1);
			}
			else
			{
				readingObj.setMinMeterReading(rs.getLong(1));
			}
			readingObj.setMaxMeterReading(rs.getLong(2));
			System.out.println("rs.getLong(3):"+rs.getDouble(3));
			readingObj.setRate(rs.getDouble(3));
			
			readingDetails.add(readingObj);
		}
		
		String readingQuery = "select min(start_reading), max(end_reading), max(end_reading)-min(start_reading) as totalReading from sm_usage";
		pstmt1 = conn.prepareStatement(readingQuery);
		
		rs1 = pstmt1.executeQuery();
		
		long currentMeterReading = 0;
		
		if(rs1.next())
		{
			start_reading = rs1.getString(1);
			end_reading = rs1.getString(2);
			currentMeterReading = rs1.getLong(3);
		}
		System.out.println("start_reading: " + start_reading);
		System.out.println("end_reading: " + end_reading);
		System.out.println("currentMeterReading: " + currentMeterReading);
		long minMeterReading;
		long maxMeterReading;
		long newMeterReading = currentMeterReading;
		
		double totalRate = 0;
		double rate = 0;
		for(int i=0; i<readingDetails.size();i++)
		{
			if (newMeterReading > 0)
			{
				readingObj = (ReadingObject) readingDetails.get(i);
				maxMeterReading = readingObj.getMaxMeterReading();
				minMeterReading = readingObj.getMinMeterReading();
				rate = readingObj.getRate();
				System.out.println("rate:"+rate);
				if (newMeterReading > maxMeterReading)
				{
					totalRate += (maxMeterReading - minMeterReading) * rate;
					newMeterReading = currentMeterReading - maxMeterReading;					
				}
				else
				{
					totalRate += newMeterReading*rate;
					newMeterReading=0;
				}
			}
		}
		System.out.println("Total rate =" +totalRate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				rs.close();
				pstmt.close();
				rs1.close();
				pstmt1.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
