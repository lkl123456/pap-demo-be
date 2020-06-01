package com.yonyou.base.support.plugin;
/**
 * 电脑实体
* @Description:  
* @author: lkl 
* @date: 2019年9月12日 下午4:10:16
 */
public class Computer {

	private String name;
	private String code;

	private Connector connector;
	private Battery battery;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	

	public Connector getConnector() {
		return connector;
	}

	public void setConnector(Connector connector) {
		this.connector = connector;
	}

	public Battery getBattery() {
		return battery;
	}

	public void setBattery(Battery battery) {
		this.battery = battery;
	}

	/**
	 * 连接器
	* @Description:  
	* @author: lkl 
	* @date: 2019年9月12日 下午4:10:34
	 */
	public static class Connector {

		private String connectorPattern;
		private String connectortype;

		public String getConnectorPattern() {
			return connectorPattern;
		}

		public void setConnectorPattern(String connectorPattern) {
			this.connectorPattern = connectorPattern;
		}

		public String getConnectortype() {
			return connectortype;
		}

		public void setConnectortype(String connectortype) {
			this.connectortype = connectortype;
		}

		public Connector(String connectorPattern, String connectortype) {
			super();
			this.connectorPattern = connectorPattern;
			this.connectortype = connectortype;
		}

		public Connector() {
			super();
		}

	}
	/**
	 * 电池
	* @Description:  
	* @author: lkl 
	* @date: 2019年9月12日 下午4:10:49
	 */
	public class Battery {

		private String batteryType;
		private String batteryPattern;

		public String getBatteryType() {
			return batteryType;
		}

		public void setBatteryType(String batteryType) {
			this.batteryType = batteryType;
		}

		public String getBatteryPattern() {
			return batteryPattern;
		}

		public void setBatteryPattern(String batteryPattern) {
			this.batteryPattern = batteryPattern;
		}

		public Battery(String batteryType, String batteryPattern) {
			super();
			this.batteryType = batteryType;
			this.batteryPattern = batteryPattern;
		}

		public Battery() {
			super();
		}

	}
}
