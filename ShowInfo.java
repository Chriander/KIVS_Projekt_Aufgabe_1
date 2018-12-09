package kivs_Package;

import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ShowInfo extends JFrame {
	PingPack[] Liste;

	public ShowInfo(PingPack[] Liste) {
		this.Liste = Liste;
	}

	public void InitShowInfo() {
		PingPack min = this.CalcMin();
		PingPack max = this.CalcMax();
		double ave = this.CalcAve();
		double StanDev = this.CalcStandDev(ave);
		
		
	}

	private double CalcStandDev(double ave) {

		double[] list = new double[this.Liste.length];
		for (int i = 0; i < this.Liste.length; i++) {
			list[i] = Math.pow(this.Liste[i].rttavg - ave, 2);
		}
		double sum = 0;

		for (int i = 0; i < list.length; i++) {
			sum = sum + list[i];
		}
		return sum / list.length;

	}

	private PingPack CalcMin() {

		PingPack min = this.Liste[0];

		for (int i = 0; i < this.Liste.length; i++) {
			if (min.rttmin > this.Liste[i].rttmin) {
				min = this.Liste[i];
			}
		}

		return min;

	}

	private double CalcAve() {

		double ave = 0;
		for (int i = 0; i < this.Liste.length; i++) {
			ave = ave + this.Liste[i].rttavg;
		}

		return ave;

	}

	private PingPack CalcMax() {

		PingPack max = this.Liste[0];

		for (int i = 0; i < this.Liste.length; i++) {
			if (max.rttmax < this.Liste[i].rttmax) {
				max = this.Liste[i];
			}
		}

		return max;

	}
	
	private void FrameCreator(PingPack Max, PingPack Min, double ave, double StanDev) {
		
		long timeStamp = (long) Max.stamp * 1000;
		Date stamp = new Date(timeStamp);
		
		JLabel LabelMaxIp = new JLabel(Max.IP);
		JLabel LabelMaxStamp = new JLabel(stamp.toString());
		JLabel LabelMax = new JLabel(Max.rttmax);
		
		JLabel LabelMinIp;
		JLabel LabelMinStamp;
		JLabel LabelMin;
		
		JLabel LabelAve;
		JLabel LabelStan;
		
		JFrame w1 = new JFrame();
	}

}
