package kivs_Package;

import java.io.File;

import javax.swing.SwingUtilities;

import org.jfree.ui.RefineryUtilities;

public class main {

	public static void main(String[] args) {

		String test = ("http://rogue-01.cs.uni-bonn.de/PA.log");
		url_accesser test2 = new url_accesser(test);
	//	test2.URL_opener();

		
		// Adress for downloadable file http://rogue-01.cs.uni-bonn.de/linux-3.9.2.tar.xz
		
		
		String home = System.getProperty("user.home");
		String path = (home + File.separator + "PA.log");
		
		
		/*String path = (File.separator + "Users" + File.separator + "Coon" + File.separator + "Downloads"
				+ File.separator + "PA.log");
*/
		Logfile test3 = new Logfile(path);

		PingPack[] Liste = test3.file_reader_and_parser(test2);

		SwingUtilities.invokeLater(() -> {
			TimeRttPlot TIMEvsRTT = new TimeRttPlot(Liste);
			TIMEvsRTT.PlotInit();
			TIMEvsRTT.setVisible(true);
		});
		
		SwingUtilities.invokeLater(() -> {
			PingRttPlot PINGvsRTT = new PingRttPlot(Liste);
			PINGvsRTT.PlotInit();
			PINGvsRTT.setVisible(true);
		});
		int[] segs = test3.Segmenter(Liste);
		
		SwingUtilities.invokeLater(() -> {
			BoxPlot BoxPlot = new BoxPlot(Liste, segs);
			BoxPlot.InitBoxPlot();
			BoxPlot.pack();
	        RefineryUtilities.centerFrameOnScreen(BoxPlot);
	        BoxPlot.setVisible(true);
		});

		
	}

}
