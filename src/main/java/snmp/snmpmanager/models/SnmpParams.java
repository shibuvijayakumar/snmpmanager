package snmp.snmpmanager.models;

import org.snmp4j.PDU;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;

/**
 * POJO class for Device's Snmp Parameters
 * @author Shibu Vijay
 *
 */
public class SnmpParams {

	private Address address = null;
	private int requestType = PDU.GET;
	private String community = null;
	private String readCommunity = "public";
	private String writeCommunity = "private";
	private String trapCommunity = null;
	private int version = SnmpConstants.version2c;
	private int retries = 2;
	private long timeout = 30000; // 30 secs

	// Additional V2c parameters
	private int maxRepetitions = 50;
	private int nonRepeaters = 0;

	// V3 parameters
	private String userName = null;
	private int securityLevel = 0;
	private String authProtocol = null;
	private String authPassword = null;
	private String privProtocol = null;
	private String privPassword = null;
	private String contextName = null;
	private String contextId = null;

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the requestType
	 */
	public int getRequestType() {
		return requestType;
	}

	/**
	 * @param requestType the requestType to set
	 */
	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}

	/**
	 * @return the community
	 */
	public String getCommunity() {
		return community;
	}

	/**
	 * @param community the community to set
	 */
	public void setCommunity(String community) {
		this.community = community;
	}

	/**
	 * @return the readCommunity
	 */
	public String getReadCommunity() {
		return readCommunity;
	}

	/**
	 * @param readCommunity the readCommunity to set
	 */
	public void setReadCommunity(String readCommunity) {
		this.readCommunity = readCommunity;
	}

	/**
	 * @return the writeCommunity
	 */
	public String getWriteCommunity() {
		return writeCommunity;
	}

	/**
	 * @param writeCommunity the writeCommunity to set
	 */
	public void setWriteCommunity(String writeCommunity) {
		this.writeCommunity = writeCommunity;
	}

	/**
	 * @return the trapCommunity
	 */
	public String getTrapCommunity() {
		return trapCommunity;
	}

	/**
	 * @param trapCommunity the trapCommunity to set
	 */
	public void setTrapCommunity(String trapCommunity) {
		this.trapCommunity = trapCommunity;
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @return the retries
	 */
	public int getRetries() {
		return retries;
	}

	/**
	 * @param retries the retries to set
	 */
	public void setRetries(int retries) {
		this.retries = retries;
	}

	/**
	 * @return the timeout
	 */
	public long getTimeout() {
		return timeout;
	}

	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	/**
	 * @return the maxRepetitions
	 */
	public int getMaxRepetitions() {
		return maxRepetitions;
	}

	/**
	 * @param maxRepetitions the maxRepetitions to set
	 */
	public void setMaxRepetitions(int maxRepetitions) {
		this.maxRepetitions = maxRepetitions;
	}

	/**
	 * @return the nonRepeaters
	 */
	public int getNonRepeaters() {
		return nonRepeaters;
	}

	/**
	 * @param nonRepeaters the nonRepeaters to set
	 */
	public void setNonRepeaters(int nonRepeaters) {
		this.nonRepeaters = nonRepeaters;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the securityLevel
	 */
	public int getSecurityLevel() {
		return securityLevel;
	}

	/**
	 * @param securityLevel the securityLevel to set
	 */
	public void setSecurityLevel(int securityLevel) {
		this.securityLevel = securityLevel;
	}

	/**
	 * @return the authProtocol
	 */
	public String getAuthProtocol() {
		return authProtocol;
	}

	/**
	 * @param authProtocol the authProtocol to set
	 */
	public void setAuthProtocol(String authProtocol) {
		this.authProtocol = authProtocol;
	}

	/**
	 * @return the authPassword
	 */
	public String getAuthPassword() {
		return authPassword;
	}

	/**
	 * @param authPassword the authPassword to set
	 */
	public void setAuthPassword(String authPassword) {
		this.authPassword = authPassword;
	}

	/**
	 * @return the privProtocol
	 */
	public String getPrivProtocol() {
		return privProtocol;
	}

	/**
	 * @param privProtocol the privProtocol to set
	 */
	public void setPrivProtocol(String privProtocol) {
		this.privProtocol = privProtocol;
	}

	/**
	 * @return the privPassword
	 */
	public String getPrivPassword() {
		return privPassword;
	}

	/**
	 * @param privPassword the privPassword to set
	 */
	public void setPrivPassword(String privPassword) {
		this.privPassword = privPassword;
	}

	/**
	 * @return the contextName
	 */
	public String getContextName() {
		return contextName;
	}

	/**
	 * @param contextName the contextName to set
	 */
	public void setContextName(String contextName) {
		this.contextName = contextName;
	}

	/**
	 * @return the contextId
	 */
	public String getContextId() {
		return contextId;
	}

	/**
	 * @param contextId the contextId to set
	 */
	public void setContextId(String contextId) {
		this.contextId = contextId;
	}

}
