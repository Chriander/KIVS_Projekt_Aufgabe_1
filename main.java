package kivs_Package;

import java.io.File;

import javax.swing.SwingUtilities;

public class main {

	public static void main(String[] args) {

		String test = ("http://rogue-01.cs.uni-bonn.de/PA.log");
		url_accesser test2 = new url_accesser(test);
		// test2.URL_opener();

		// implement mdev if there is time
		
		// 1. line rtt 2. line stamp 3. line IP
		// Implement: open most recent path

		String path = (File.separator + "Users" + File.separator + "Coon" + File.separator + "Downloads"
				+ File.separator + "PA.log");

		Logfile test3 = new Logfile(path);

		PingPack[] Liste = test3.file_reader_and_parser(test2);

		SwingUtilities.invokeLater(() -> {
			TimeRttPlot Min = new TimeRttPlot(Liste);
			Min.PlotInit();
			Min.setVisible(true);
		});

	}

}
