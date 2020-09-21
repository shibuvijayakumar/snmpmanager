package snmp.snmpmanager;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.OID;
import org.snmp4j.util.TableEvent;

import snmp.snmpmanager.models.Device;
import snmp.snmpmanager.models.SnmpParams;
import snmp.snmpmanager.session.SnmpSession;
import snmp.snmpmanager.session.SnmpV1Session;
import snmp.snmpmanager.session.SnmpV2Session;
import snmp.snmpmanager.session.SnmpV3Session;

public class SnmpManager {
	
	private static final Logger log = LogManager.getLogger(SnmpManager.class);
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	public static SnmpSession getSnmpSession(SnmpParams params) {
		log.debug("Getting Snmp Session for : "+params.getAddress());
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
	
	
	public static void get(Device device, String[] oids) {
		SnmpSession snmpSession = getSnmpSession(device.getSnmpParams());
		try {
			ResponseEvent event = snmpSession.get(oids);
			// parse the response and store it
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getTable(Device device, OID[] oids) {
		SnmpSession snmpSession = getSnmpSession(device.getSnmpParams());
		try {
			List<TableEvent> events = snmpSession.getTable(oids);
			// parse the response and store it
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
