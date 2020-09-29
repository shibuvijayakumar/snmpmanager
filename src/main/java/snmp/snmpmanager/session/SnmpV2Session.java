package snmp.snmpmanager.session;

import org.snmp4j.CommunityTarget;
import org.snmp4j.Target;
import org.snmp4j.smi.OctetString;

import snmp.snmpmanager.models.SnmpParams;

/**
 * SnmpSession class for SNMP Version2
 * @author Shibu Vijay
 */
public class SnmpV2Session extends SnmpSession {

	public SnmpV2Session(SnmpParams params) {
		super(params);
	}

	@Override
	protected Target createTarget(SnmpParams params) {
		CommunityTarget target = new CommunityTarget();
		target.setCommunity(new OctetString(params.getCommunity()));
		target.setAddress(params.getAddress());
		target.setRetries(params.getRetries());
		target.setTimeout(params.getTimeout());
		target.setVersion(params.getVersion());
		return target;
	}
}
