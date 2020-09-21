/**
 * 
 */
package snmp.snmpmanager.session;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
 * @author Shibu Vijay
 *
 */
public abstract class SnmpSession {

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
	 * 
	 * @return
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
	 * 
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
	 * 
	 * @param oids
	 * @return
	 * @throws Exception
	 */
	public ResponseEvent get(String[] oids) throws Exception {
		pdu.setType(PDU.GET);
		for (String oid : oids) {
			pdu.add(new VariableBinding(new OID(oid)));
		}

		ResponseEvent response = snmp.send(pdu, target);
		return response;
	}

	/**
	 * 
	 * @param setMap
	 * @return
	 * @throws Exception
	 */
	public ResponseEvent set(Map<String, Variable> setMap) throws Exception {
		pdu.setType(PDU.SET);

		setMap.forEach((oid, value) -> {
			pdu.add(new VariableBinding(new OID(oid), value));
		});

		ResponseEvent response = snmp.send(pdu, target);
		return response;
	}
	
	/**
	 * 
	 * @param oids
	 * @return
	 * @throws Exception
	 */
	public List<TableEvent> getTable (OID[] oids) throws Exception {
		TableUtils tUtils = new TableUtils(snmp, new DefaultPDUFactory());
	    List<TableEvent> events = tUtils.getTable(target, oids, null, null);
	    return events;
	}

	/**
	 * 
	 * @param params
	 * @return
	 */
	protected abstract Target createTarget(SnmpParams params);

}
