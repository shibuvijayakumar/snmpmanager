package snmp.snmpmanager;

import java.io.FileReader;
import java.net.URL;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.snmp4j.smi.OID;

import snmp.snmpmanager.models.SnmpMetrices;

/**
 * ResourceReader - reads the resources on the start of the Application, Singleton class
 * @author Shibu Vijay
 *
 */
public final class ResourceReader {
	private static final Logger log = LogManager.getLogger(ResourceReader.class);
	
	private static ResourceReader instance = null;
	private SnmpMetrices snmpMetrices;
	private static final String oidJsonFile = "oid.json";
	private static final String OIDS_JSON_KEY = "oids";
	private static final String SCALARS_JSON_KEY = "scalars";
	private static final String TABULARS_JSON_KEY = "tabulars";
	private static final String NAME_JSON_KEY = "name";
	private static final String OID_JSON_KEY = "oid";
	
	private ResourceReader() {
		snmpMetrices = new SnmpMetrices();
		loadOids();
	}
	
	public static ResourceReader getInstance() {
		if (instance == null) {
			instance = new ResourceReader();
		}
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	private void loadOids() {
		log.info("Loading the Scalar and Tabular Snmp Metrices provided in the Oid Json file");
		ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(oidJsonFile);
        log.debug("Oid Json file Url: "+ resource);
		try (FileReader reader = new FileReader(resource.getFile())) {
            // read the json file
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            
            JSONObject oidsVals = (JSONObject) jsonObject.get(OIDS_JSON_KEY);
            
            // load scalar metrics
            JSONArray scalarsObjs = (JSONArray) oidsVals.get(SCALARS_JSON_KEY);
            scalarsObjs.forEach(item -> {
            	JSONObject obj = (JSONObject) item;
            	log.debug(("Scalar Oid-> Name: "+obj.get(NAME_JSON_KEY)+", OID: "+obj.get(OID_JSON_KEY)));
            	snmpMetrices.populateScalarMetrics((String)obj.get(NAME_JSON_KEY), 
            			new OID((String)obj.get(OID_JSON_KEY)));
            });
            
            // TODO load tabular metrics
            
		} catch (Exception e) {
			
		}
	}

	/**
	 * @return the snmpMetrices
	 */
	public SnmpMetrices getSnmpMetrices() {
		return snmpMetrices;
	}

	/**
	 * @param snmpMetrices the snmpMetrices to set
	 */
	public void setSnmpMetrices(SnmpMetrices snmpMetrices) {
		this.snmpMetrices = snmpMetrices;
	}
}
