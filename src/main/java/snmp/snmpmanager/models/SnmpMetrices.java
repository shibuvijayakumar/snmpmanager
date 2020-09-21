package snmp.snmpmanager.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.snmp4j.smi.OID;

/**
 * 
 * @author Shibu Vijay
 *
 */
public class SnmpMetrices {

	private List<Metric> scalars;
	private Map<String, List<Metric>> tabulars;
	
	public SnmpMetrices() {
		scalars = new ArrayList<> ();
		tabulars = new HashMap<> ();
	}

	/**
	 * @return the scalars
	 */
	public List<Metric> getScalars() {
		return scalars;
	}

	/**
	 * @param scalars the scalars to set
	 */
	public void setScalars(List<Metric> scalars) {
		this.scalars = scalars;
	}

	/**
	 * @return the tabulars
	 */
	public Map<String, List<Metric>> getTabulars() {
		return tabulars;
	}

	/**
	 * @param tabulars the tabulars to set
	 */
	public void setTabulars(Map<String, List<Metric>> tabulars) {
		this.tabulars = tabulars;
	}
	
	public void populateScalarMetrics(String name, OID oid) {
		Metric metric = new Metric(name, oid);
		scalars.add(metric);
	}

	class Metric {

		private String name;
		private OID oid;
		
		Metric(String name, OID oid) {
			this.name = name;
			this.oid = oid;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the oid
		 */
		public OID getOid() {
			return oid;
		}

		/**
		 * @param oid the oid to set
		 */
		public void setOid(OID oid) {
			this.oid = oid;
		}

	}

}
