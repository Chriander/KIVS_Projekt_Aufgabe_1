package kivs_Package;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
		 
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("text.txt"));
			writer.write("min");
			writer.write(min.IP);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	private double CalcStandDev(double ave) {

		String home = System.getProperty("user.home");
		String path = (home + File.separator + "PA.log");
		FileInputStream file;
		double sum = 0;

		try {
			file = new FileInputStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(file));
			String line = null;

			StringBuilder sb = new StringBuilder();
			String cmp1 = null;
			String cmp2 = null;
			StringBuilder sb1 = new StringBuilder();
			double tmp;
			ArrayList<Double> WList = new ArrayList<Double>();

			while ((line = br.readLine()) != null) {

				if (!(line.isEmpty())) {
					sb.append(line.charAt(0));
					sb.append(line.charAt(1));
					cmp1 = sb.toString();
					sb.setLength(0);
					String nmb = null;
					if (cmp1.contentEquals("64")) {
						for (int v = 0; v < line.length(); v++) {

							sb.setLength(0);
							sb.append(line.charAt(v));
							sb.append(line.charAt(v + 1));
							sb.append(line.charAt(v + 2));
							sb.append(line.charAt(v + 3));
							cmp2 = sb.toString();
							sb.setLength(0);

							if (cmp2.equals("time")) {

								while (!(line.charAt(v + 5) == ' ')) {
									sb1.append(line.charAt(v + 5));
									v++;
								}

								nmb = sb1.toString();
								sb1.setLength(0);
								tmp = Double.parseDouble(nmb);
								WList.add(tmp);
								v = line.length();

							}
						}

					}
				}
			}

			double[] list = new double[WList.size()];

			
			for (int i = 0; i < WList.size(); i++) {
				list[i] = Math.pow(WList.get(i) - ave, 2);
			}

			for (int i = 0; i < list.length; i++) {
				sum = sum + list[i];
			}
			sum = Math.sqrt(sum / list.length);
			file.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sum;

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

		
		return ave/Liste.length;

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

		JLabel LabelMinIp;
		JLabel LabelMinStamp;
		JLabel LabelMin;

		JLabel LabelAve;
		JLabel LabelStan;

		JFrame w1 = new JFrame();
	}

}
