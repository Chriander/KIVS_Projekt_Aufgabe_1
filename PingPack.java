package kivs_Package;

import java.util.ArrayList;

public class PingPack {
	int stamp;
	double rttmin;
	double rttavg;
	double rttmax;
	String IP;
	
	
	
	boolean stampB = false;
	boolean rttminB = false;
	boolean rttavgB = false;
	boolean rttmaxB = false;
	boolean IPB = false;
	
	//empty constructor for first value
	public PingPack() {
		
	}
	//constructor for Pings without rtt dev
	public PingPack(int stamp, double rttmin, double rttavg, double rttmax, String IP  ) {
		this.stamp = stamp;
		this.rttmin = rttmin;
		this.rttavg = rttavg;
		this.rttmax = rttmax;
		this.IP = IP;
		
		this.stampB = true;
		this.rttminB = true;
		this.rttavgB = true;
		this.rttmaxB = true;
		this.IPB = true;
		
		
		
	}
	
	//function to calculate values
	public void PackCreator(int stamp, ArrayList<Double> WList, String IP) {
		//get minimum
		double min = WList.get(0);
		for(int i = 0; i<WList.size();i++) {
			if(WList.get(0)<min) {
				min = WList.get(0);
			}
		}
		
		//get average
		double avg =0;
		for(int i=0;i<WList.size();i++) {
			avg+= WList.get(i);
		}
		avg = avg/WList.size();
		
		//get maximum
		double max = WList.get(0);
		for(int i = 0; i<WList.size();i++) {
			if(WList.get(0)>max) {
				max = WList.get(0);
			}
		}
		
		
		this.stamp= stamp;
		this.rttmin = min;
		this.rttavg = avg;
		this.rttmax = max;
		this.IP = IP;
		
		this.stampB = true;
		this.rttminB = true;
		this.rttavgB = true;
		this.rttmaxB = true;
		this.IPB = true;
		
	}

}
