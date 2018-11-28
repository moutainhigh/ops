package com.jyblife.logic.wl.ops.common.utils;

/**
 * 根据两个位置的经纬度，来计算两地的距离（单位为KM）
 */
public class DistanceUtils {
	
	private static final double EARTH_RADIUS = 6378.137; //地球半径 (千米)

	/**
	 * 计算俩个位置的经纬度距离（单位：km）
	 * @param long1
	 * @param lat1
	 * @param long2
	 * @param lat2
	 * @return
	 */
	public static double getDistance(double long1, double lat1, double long2, double lat2) {
		double a, b, d, sa2, sb2;
		lat1 = rad(lat1);
		lat2 = rad(lat2);
		a = lat1 - lat2;
		b = rad(long1 - long2);

		sa2 = Math.sin(a / 2.0);
		sb2 = Math.sin(b / 2.0);
		d = 2 * EARTH_RADIUS * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
		d = Math.round(d * 10000d) / 10000d;
		return d;
	}
	
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	public static void main(String[] args) {
		// 根据两点间的经纬度计算距离，单位：km
		double distance = getDistance(123.00, 120.00,122, 119.8);
		System.out.println(distance);
	}

}
