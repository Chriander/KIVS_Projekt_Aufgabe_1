package kivs_Package;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.SwingUtilities;


import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.RefineryUtilities;

public class main {

	public static void main(String[] args) {

		String test = ("http://rogue-01.cs.uni-bonn.de/PA.log");
		url_accesser test2 = new url_accesser(test);
	//	test2.URL_opener();

		
		// Adress for downloadable file http://rogue-01.cs.uni-bonn.de/linux-3.9.2.tar.xz
		
		
		
		//String path = ("/builds/kivs_ws1819/gruppe_28/"+ "PA.log");
		
		
		String path = (File.separator + "Users" + File.separator + "Coon" + File.separator + "Downloads"
				+ File.separator + "PA.log");

		Logfile test3 = new Logfile(path);

		PingPack[] Liste = test3.file_reader_and_parser(test2);

		SwingUtilities.invokeLater(() -> {
			TimeRttPlot TIMEvsRTT = new TimeRttPlot(Liste);
			JFreeChart chart = TIMEvsRTT.PlotInit();
			
			save(chart);
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

		SwingUtilities.invokeLater(() -> {
			ShowInfo Info = new ShowInfo(Liste);
			Info.InitShowInfo();
		});
	}

	public static void save(JFreeChart a) {
		try {

			OutputStream out = new FileOutputStream("a.png");
			ChartUtilities.writeChartAsPNG(out, a, 800, 800);

		} catch (IOException ex) {
			
		}
	}
}
