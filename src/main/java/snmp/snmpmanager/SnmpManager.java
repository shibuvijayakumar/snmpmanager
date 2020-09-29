package snmp.snmpmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.OID;
import org.snmp4j.util.TableEvent;

import snmp.snmpmanager.models.Device;
import snmp.snmpmanager.models.SnmpParams;
import snmp.snmpmanager.models.SnmpMetrices.Metric;
import snmp.snmpmanager.session.SnmpSession;
import snmp.snmpmanager.session.SnmpV1Session;
import snmp.snmpmanager.session.SnmpV2Session;
import snmp.snmpmanager.session.SnmpV3Session;

/**
 * SnmpManager class
 * @author Shibu Vijay
 *
 */
public class SnmpManager {
	
	private static final Logger log = LogManager.getLogger(SnmpManager.class);
	
	/**
	 * Initiates and returns the Snmp session based on the Snmp version
	 * @param params SnmpParams
	 * @return SnmpSession
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
	
	/**
	 * Sample method to get the scalar Oids values and store it
	 * @param snmpSession SnmpSession
	 * @param device Device
	 * @param oids List
	 */
	public static void fetchAndPersistScalarsMetrices(SnmpSession snmpSession, Device device, List<Metric> metrices) {
		try {
			log.info("Fetching Scalar Metrices data from the device");
			List<OID> oids = new ArrayList<>();
			metrices.forEach(metric -> oids.add(metric.getOid()));
			ResponseEvent event = snmpSession.get(oids);
			// parse the response and store it
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Sample method to get the tabular Oids values and store it
	 * @param snmpSession SnmpSession
	 * @param device Device
	 * @param oids List
	 */
	public static void fetchAndPersistTabularsMetrices(SnmpSession snmpSession, Device device, Map<String, List<Metric>> tabularsMap) {
		try {
			log.info("Fetching Tabular Metrices data from the device");
			// Iterate each table Data, fetch the oids values
			tabularsMap.forEach((tableName, metricesList) -> {
				log.debug("Collecting Data of Table: "+tableName);
				List<OID> oids = new ArrayList<>();
				metricesList.forEach(metric -> oids.add(metric.getOid()));
				try {
					List<TableEvent> events = snmpSession.getTable(oids);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// parse the response and store it
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
