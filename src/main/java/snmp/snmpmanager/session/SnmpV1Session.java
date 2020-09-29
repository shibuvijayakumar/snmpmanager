package snmp.snmpmanager.session;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.snmp4j.CommunityTarget;
import org.snmp4j.Target;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.util.TableEvent;

import snmp.snmpmanager.models.SnmpParams;

/**
 * SnmpSession class for SNMP Version1
 * @author Shibu Vijay
 */
public class SnmpV1Session extends SnmpSession {
	private static final Logger log = LogManager.getLogger(SnmpV1Session.class);

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
	
	@Override
	public List<TableEvent> getTable (List<OID> oidList) throws Exception {
		log.warn("GetTable is not supported in Snmp V1");
		return null;
	}
}
