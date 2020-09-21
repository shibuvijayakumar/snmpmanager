package snmp.snmpmanager.session;

import org.snmp4j.CommunityTarget;
import org.snmp4j.Target;
import org.snmp4j.smi.OctetString;

import snmp.snmpmanager.models.SnmpParams;


public class SnmpV1Session extends SnmpSession {

	public SnmpV1Session(SnmpParams params) {
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
