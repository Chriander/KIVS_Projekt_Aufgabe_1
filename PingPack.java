package kivs_Package;

import java.util.ArrayList;

public class PingPack {
	int stamp;
	double rttmin;
	double rttavg;
	double rttmax;
	double rttmdev;
	
	//empty constructor for first value
	public PingPack() {
		
	}
	//constructor for Pings without rtt dev
	public PingPack(int stamp, double rttmin, double rttavg, double rttmax  ) {
		this.stamp = stamp;
		this.rttmin = rttmin;
		this.rttavg = rttavg;
		this.rttmax = rttmax;
		
	}
	//constructor for Pings with rtt dev
	public PingPack(int stamp, double rttmin, double rttavg, double rttmax, double rttmdev  ) {
		this.stamp = stamp;
		this.rttmin = rttmin;
		this.rttavg = rttavg;
		this.rttmax = rttmax;
		this.rttmdev = rttmdev;
	}
	//function to calculate values
	public void PackCreator(int stamp, ArrayList<Double> WList) {
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
		
	}

}
