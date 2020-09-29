# Snmpmanager
SNMP manager using snmp4J - Supports Get, Set, GetBulk operations


**SnmpSession**
Acts as an abstract for the Session creation based on the SnmpVersion
Specific SnmpSession classes are added to do the specific version related operations

**SnmpManager**
Helps in getting the Snmpsession for the device and performs fetch operations and parse and storing the data

**SnmpDataCollector**
Helps in enabling the DataCollection for Each devices periodically

**Sample Usage Code:**

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