package snmp.snmpmanager.collector;

import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import snmp.snmpmanager.SnmpManager;
import snmp.snmpmanager.models.Device;
import snmp.snmpmanager.models.SnmpMetrices.Metric;
import snmp.snmpmanager.session.SnmpSession;

/**
 * SnmpDataCollector - Data collector for each device to get the snmp data periodically
 * based on the pollInterval
 * @author Shibu Vijay
 *
 */
public class SnmpDataCollector {
	
	private ScheduledExecutorService executerService;
	private final Device device;
	private int pollInterval;
    private TimerTask pollTask; 
    
    public SnmpDataCollector(ScheduledExecutorService executerService, Device device, int pollInterval) {
    	this.executerService = executerService;
    	this.device = device;
    	this.pollInterval = pollInterval;
	}
    
    private class PollTimerTask extends TimerTask {
		@Override
		public void run() {
			// Get the SnmpSession with respect to the SnmpParameters of the device
			SnmpSession snmpSession = SnmpManager.getSnmpSession(device.getSnmpParams());
			
			// iterate over the SnmpMetrics of the device 
			// do get operation for the Scalars metrices and do getTable for the Tabular metrices
			List<Metric> scalarMetrices = device.getSnmpMetrices().getScalars();
			Map<String, List<Metric>> tabularMetrices = device.getSnmpMetrices().getTabulars();
			
			if (scalarMetrices != null && !scalarMetrices.isEmpty()) {
				SnmpManager.fetchAndPersistScalarsMetrices(snmpSession, device, scalarMetrices);
			}
			
			if (tabularMetrices != null && !tabularMetrices.isEmpty()) {
				SnmpManager.fetchAndPersistTabularsMetrices(snmpSession, device, tabularMetrices);
			}
		}
    }
    
    public synchronized void start() {
    	pollTask = new PollTimerTask();
    	executerService.scheduleAtFixedRate(pollTask, 0, pollInterval, TimeUnit.SECONDS);
    }

}
