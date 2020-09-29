/**
 * 
 */
package snmp.snmpmanager.session;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableUtils;

import snmp.snmpmanager.models.SnmpParams;

/**
 * SnmpSession abstract class - Uses the snmp4j and perform the get, set, gettable operations
 * @author Shibu Vijay
 */
public abstract class SnmpSession {
	private static final Logger log = LogManager.getLogger(SnmpSession.class);
	Snmp snmp = null;
	PDU pdu = null;
	Target target = null;

	/**
	 * Creates the session
	 */
	public SnmpSession(SnmpParams params) {
		start();
		createPDU(params);
		createTarget(params);
	}

	/**
	 * @return the snmp
	 */
	public Snmp getSnmp() {
		return snmp;
	}

	/**
	 * @param snmp the snmp to set
	 */
	public void setSnmp(Snmp snmp) {
		this.snmp = snmp;
	}

	/**
	 * @return the pdu
	 */
	public PDU getPdu() {
		return pdu;
	}

	/**
	 * @param pdu the pdu to set
	 */
	public void setPdu(PDU pdu) {
		this.pdu = pdu;
	}

	/**
	 * @return the target
	 */
	public Target getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(Target target) {
		this.target = target;
	}

	/**
	 * Starts the snmp session and return snmp Obj
	 * @return Snmp
	 */
	protected Snmp start() {
		try {
			TransportMapping transport = new DefaultUdpTransportMapping();
			snmp = new Snmp(transport);
			snmp.listen();
		} catch (IOException e) {
			// ("Exception in creating SNMP Listener :" + e.getMessage());
		}
		return snmp;
	}

	/** 
	 * Stops the Snmp
	 */
	protected void stop() {
		try {
			if (snmp != null) {
				snmp.close();
			}
		} catch (IOException e) {
			// error
		}
	}

	/**
	 * 
	 * @param params
	 * @return
	 */
	protected PDU createPDU(SnmpParams params) {
		pdu = DefaultPDUFactory.createPDU(params.getVersion());
		return pdu;
	}

	/**
	 * Performs Snmp Get Operation for the given Oids
	 * @param oids List
	 * @return ResponseEvent
	 * @throws Exception
	 */
	public ResponseEvent get(List<OID> oids) throws Exception {
		pdu.setType(PDU.GET);
		oids.forEach(oid -> pdu.add(new VariableBinding(oid)));
		ResponseEvent response = snmp.send(pdu, target);
		return response;
	}

	/**
	 * Performs Snmp Set Operation for the given Variable Map
	 * @param setMap Map
	 * @return ResponseEvent
	 * @throws Exception
	 */
	public ResponseEvent set(Map<OID, Variable> setMap) throws Exception {
		pdu.setType(PDU.SET);
		setMap.forEach((oid, value) -> {
			pdu.add(new VariableBinding(oid, value));
		});
		ResponseEvent response = snmp.send(pdu, target);
		return response;
	}
	
	/**
	 * Perform GetTable for the given tableOids
	 * Not supported in SnmpV1
	 * @param oids List
	 * @return List of TableEvent
	 * @throws Exception
	 */
	public List<TableEvent> getTable (List<OID> oidList) throws Exception {
		OID[] oids = oidList.toArray(new OID[oidList.size()]);
		TableUtils tUtils = new TableUtils(snmp, new DefaultPDUFactory());
	    List<TableEvent> events = tUtils.getTable(target, oids, null, null);
	    return events;
	}

	/**
	 * Creates Target
	 * @param params SnmpParams
	 * @return Target
	 */
	protected abstract Target createTarget(SnmpParams params);

}
