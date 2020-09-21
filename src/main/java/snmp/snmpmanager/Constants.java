package snmp.snmpmanager;

public interface Constants {
	String SNMP_AUTH_PROTOCOL_MD5 = "MD5";
	String SNMP_AUTH_PROTOCOL_SHA1 = "SHA-1";
	String SNMP_PRIV_PROTOCOL_DES = "CBC-DES";
	String SNMP_PRIV_PROTOCOL_AES128 = "AES-128";
	String SNMP_PRIV_PROTOCOL_AES192 = "AES-192";
	String SNMP_PRIV_PROTOCOL_AES256 = "AES-256";
	String SNMP_PRIV_PROTOCOL_3DES = "3-DES";
	String SNMP_AUTH_PROTOCOL_NONE = "None";
	String SNMP_PRIV_PROTOCOL_NONE = "None";
	String SNMP_DEFAULT_PRIV_PASSWORD = "privUser";
	String SNMP_DEFAULT_AUTH_PASSWORD = "authUser";
	String SNMP_DEFAULT_USERNAME = "noAuthUser";
}
