package snmp.snmpmanager.models;

import java.net.InetAddress;

/**
 * POJO class for Device Entity
 * @author Shibu Vijay
 *
 */
public class Device {

	private String name;
	private InetAddress ipAddress;

	private SnmpParams snmpParams;
	private SnmpMetrices snmpMetrices; 

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the ipAddress
	 */
	public InetAddress getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(InetAddress ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the snmpParams
	 */
	public SnmpParams getSnmpParams() {
		return snmpParams;
	}

	/**
	 * @param snmpParams the snmpParams to set
	 */
	public void setSnmpParams(SnmpParams snmpParams) {
		this.snmpParams = snmpParams;
	}

	/**
	 * @return the snmpMetrices
	 */
	public SnmpMetrices getSnmpMetrices() {
		return snmpMetrices;
	}

	/**
	 * @param snmpMetrices the snmpMetrices to set
	 */
	public void setSnmpMetrices(SnmpMetrices snmpMetrices) {
		this.snmpMetrices = snmpMetrices;
	}

}
