package snmp.snmpmanager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import snmp.snmpmanager.models.SnmpMetrices;

/**
 * Snmp Manager App
 * @author Shibu Vijay
 */
public class App {
	private static final Logger log = LogManager.getLogger(App.class);
	
	public static void main(String[] args) {
		log.info("SNMP Manager App");

		// configure ThreadPool with appropriate poolsize
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

		// Read the oid.json file and get the oids for which the data to be collected
		SnmpMetrices snmpMetrices = ResourceReader.getInstance().getSnmpMetrices(); // assign this to the devices
		
		// If each device has different SnmpMetrices to be collected, get SnmpMetrices separately and assign to respective devices
		
		// Iterate the devices for which SNMP data to be collected and construct the device object with respective
		// SnmpParams and SnmpMetrices
		// Trigger the SnmpDataCollector for the devices

		/*
		 * Sample Code for Data collection for a device
		 */
		// SnmpDataCollector dataCollector = new SnmpDataCollector(scheduledExecutorService, deviceObj, pollInterval);
		// dataCollector.start();
		 
	}
}
