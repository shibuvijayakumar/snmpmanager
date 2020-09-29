package snmp.snmpmanager.session;

import org.snmp4j.Target;
import org.snmp4j.UserTarget;
import org.snmp4j.mp.MPv3;
import org.snmp4j.security.AuthMD5;
import org.snmp4j.security.AuthSHA;
import org.snmp4j.security.Priv3DES;
import org.snmp4j.security.PrivAES128;
import org.snmp4j.security.PrivAES192;
import org.snmp4j.security.PrivAES256;
import org.snmp4j.security.PrivDES;
import org.snmp4j.security.SecurityLevel;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.security.UsmUser;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;

import snmp.snmpmanager.Constants;
import snmp.snmpmanager.models.SnmpParams;

/**
 * SnmpSession class for SNMP Version3
 * @author Shibu Vijay
 */
public class SnmpV3Session extends SnmpSession {

	public SnmpV3Session(SnmpParams params) {
		super(params);
		// add USM user
		addUSMUser(params);
	}

	@Override
	protected Target createTarget(SnmpParams params) {
		UserTarget target = new UserTarget();
		target.setSecurityLevel(getSnmpv3SecurityLevel(params));
		target.setSecurityName(new OctetString(params.getUserName()));
		target.setAddress(params.getAddress());
		target.setRetries(params.getRetries());
		target.setTimeout(params.getTimeout());
		target.setVersion(params.getVersion());
		return target;
	}

	private void addUSMUser(SnmpParams params) {
		OctetString authPassword = null;
		OctetString privPassword = null;
		OctetString username = null;
		OID authProtocol = null;
		OID privProtocol = null;

		if (params.getUserName() != null) {
			username = new OctetString(params.getUserName());
		}

		// Configuring Authentication protocol
		if (params.getAuthProtocol() != null
				&& !params.getAuthProtocol().equalsIgnoreCase(Constants.SNMP_AUTH_PROTOCOL_NONE)) {
			if (params.getAuthProtocol().equals(Constants.SNMP_AUTH_PROTOCOL_MD5)) {
				authProtocol = AuthMD5.ID;
			} else if (params.getAuthProtocol().equals(Constants.SNMP_AUTH_PROTOCOL_SHA1)) {
				authProtocol = AuthSHA.ID;
			}

			if (params.getAuthPassword() != null) {
				authPassword = new OctetString(params.getAuthPassword());
			}
		}

		// Configuring privacy protocol
		if (params.getPrivProtocol() != null
				&& !params.getPrivProtocol().equalsIgnoreCase(Constants.SNMP_PRIV_PROTOCOL_NONE)) {
			if (params.getPrivProtocol().equals(Constants.SNMP_PRIV_PROTOCOL_DES)) {
				privProtocol = PrivDES.ID;
			} else if (params.getPrivProtocol().equals(Constants.SNMP_PRIV_PROTOCOL_3DES)) {
				privProtocol = Priv3DES.ID;
			} else if (params.getPrivProtocol().equals(Constants.SNMP_PRIV_PROTOCOL_AES128)) {
				privProtocol = PrivAES128.ID;
			} else if (params.getPrivProtocol().equals(Constants.SNMP_PRIV_PROTOCOL_AES192)) {
				privProtocol = PrivAES192.ID;
			} else if (params.getPrivProtocol().equals(Constants.SNMP_PRIV_PROTOCOL_AES256)) {
				privProtocol = PrivAES256.ID;
			}

			if (params.getPrivPassword() != null) {
				privPassword = new OctetString(params.getPrivPassword());
			}
		}

		USM usm = new USM(SecurityProtocols.getInstance(), new OctetString(MPv3.createLocalEngineID()), 0);
		SecurityModels.getInstance().addSecurityModel(usm);
		UsmUser usmUser = new UsmUser(username, authProtocol, authPassword, privProtocol, privPassword);
		snmp.getUSM().addUser(username, usmUser);
	}

	private static int getSnmpv3SecurityLevel(final SnmpParams params) {
		int securityLevel = params.getSecurityLevel();
		if (securityLevel == SecurityLevel.NOAUTH_NOPRIV || securityLevel == SecurityLevel.AUTH_NOPRIV
				|| securityLevel == SecurityLevel.AUTH_PRIV) {
			return securityLevel;
		}

		if (params.getAuthProtocol() != null
				&& !params.getAuthProtocol().equalsIgnoreCase(Constants.SNMP_AUTH_PROTOCOL_NONE)) {
			if (params.getPrivProtocol() != null
					&& !params.getPrivProtocol().equalsIgnoreCase(Constants.SNMP_PRIV_PROTOCOL_NONE)) {
				securityLevel = SecurityLevel.AUTH_PRIV;
			} else {
				securityLevel = SecurityLevel.AUTH_NOPRIV;
			}
		} else {
			securityLevel = SecurityLevel.NOAUTH_NOPRIV;
		}
		return securityLevel;
	}
	
}
