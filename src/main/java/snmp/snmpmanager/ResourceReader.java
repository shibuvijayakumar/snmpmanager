package snmp.snmpmanager;

import java.io.FileReader;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.snmp4j.smi.OID;

import snmp.snmpmanager.models.SnmpMetrices;

public final class ResourceReader {
	
	String filePath = "C:\\Users\\Shibu Vijay\\eclipse-workspace\\snmpmanager\\src\\main\\resources\\oid.json";
	
	private static ResourceReader instance = null;
	private SnmpMetrices snmpMetrices;
	private static final String oidJsonFile = "oid.json";
	private static final String OIDS_JSON_KEY = "oids";
	private static final String SCALARS_JSON_KEY = "scalars";
	private static final String TABULARS_JSON_KEY = "tabulars";
	private static final String NAME_JSON_KEY = "name";
	private static final String OID_JSON_KEY = "oid";
	
	private ResourceReader() {
		System.out.println("Constructor ..."); 
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
		try (FileReader reader = new FileReader(filePath)) {
            // read the json file
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            
            JSONObject oidsVals = (JSONObject) jsonObject.get(OIDS_JSON_KEY);
            System.out.println(oidsVals.keySet().size());
            
            // load scalar metrics
            JSONArray scalarsObjs = (JSONArray) oidsVals.get(SCALARS_JSON_KEY);
            scalarsObjs.forEach(item -> {
            	JSONObject obj = (JSONObject) item;
            	System.out.println("Name::: "+obj.get(NAME_JSON_KEY)+"::: OID ::: "+obj.get(OID_JSON_KEY));
            	snmpMetrices.populateScalarMetrics((String)obj.get(NAME_JSON_KEY), 
            			new OID((String)obj.get(OID_JSON_KEY)));
            });
            
            // load tabular metrics
            
		} catch (Exception e) {
			
		}
	}
	
	public static void main(String a[]) throws Exception {
		ResourceReader reader = ResourceReader.getInstance();
	}
}
