package snmp.snmpmanager.collector;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import snmp.snmpmanager.models.Device;

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
			// poll snmp scalars and tabulars and persist data
		}
    }
    
    public synchronized void start() {
    	pollTask = new PollTimerTask();
    	executerService.scheduleAtFixedRate(pollTask, 0, pollInterval, TimeUnit.SECONDS);
    }

}
