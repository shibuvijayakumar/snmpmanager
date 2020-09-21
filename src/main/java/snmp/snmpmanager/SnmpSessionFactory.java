package snmp.snmpmanager;

import org.snmp4j.mp.SnmpConstants;

import snmp.snmpmanager.models.SnmpParams;
import snmp.snmpmanager.session.SnmpSession;
import snmp.snmpmanager.session.SnmpV1Session;
import snmp.snmpmanager.session.SnmpV2Session;
import snmp.snmpmanager.session.SnmpV3Session;

public class SnmpSessionFactory {

	/**
	 * 
	 * @param params
	 * @return
	 */
	public SnmpSession getSnmpSession(SnmpParams params) {
		SnmpSession snmpSession;
		switch (params.getVersion()) {
	      case SnmpConstants.version3: {
	    	snmpSession = new SnmpV3Session(params);
	        break;
	      }
	      case SnmpConstants.version1: {
	    	snmpSession = new SnmpV1Session(params);
	        break;
	      }
	      default:
	    	snmpSession = new SnmpV2Session(params);
	    }
		return snmpSession;
	}
}
