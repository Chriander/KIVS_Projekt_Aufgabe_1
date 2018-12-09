package kivs_Package;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;

public class BoxPlot extends JFrame {
	PingPack[] Liste;
	int[] segs;

	public BoxPlot(PingPack[] Liste, int[] segs) {
		this.Liste = Liste;
		this.segs = segs;
	}

	public void InitBoxPlot() {
		BoxAndWhiskerCategoryDataset dataset = this.createDatasetBoxWhisker();
		JFreeChart chart = createChartBox(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
		chartPanel.setFillZoomRectangle(true);
		chartPanel.setMouseWheelEnabled(true);
		setContentPane(chartPanel);
		chartPanel.setBackground(Color.white);

		setContentPane(chartPanel);

		setTitle("");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private BoxAndWhiskerCategoryDataset createDatasetBoxWhisker() {
		
		
		

		DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();
		int seriesNumb = 3;
		int JahrNumb = this.segs.length-1;

		int start = 0;
		int endseg = this.segs[0];

		for (int i = 0; i < seriesNumb; i++) {
			if (i == 0) {
				
				for (int j = 0; j <= JahrNumb; j++) {

					List<Double> list = new ArrayList<Double>();

					

					for (int k = start; k <= endseg; k++) {
						list.add(this.Liste[k].rttmin);
						
					}
					// get Year on y Axis
					long timeStamp = (long) Liste[endseg].stamp * 1000;
					Date End = new Date(timeStamp);
					Calendar calendar = new GregorianCalendar();
					calendar.setTime(End);
					int Sumyear = calendar.get(Calendar.YEAR);
					int Wintyear = Sumyear - 1;
					dataset.add(list, "RTTMin", " Sem:" + Wintyear + "/" + Sumyear);

					if (j != JahrNumb) {
						start = endseg + 1;
						endseg = this.segs[j+1];
						
					}
					
				}

			}

			if (i == 1) {
				
				start = 0;
				endseg = this.segs[0];

				for (int j = 0; j <= JahrNumb; j++) {

					List<Double> list = new ArrayList<Double>();
					
					for (int k = start; k <= endseg; k++) {
						list.add(this.Liste[k].rttavg);
						
						
					}
					// get Year on y Axis
					long timeStamp = (long) Liste[endseg].stamp * 1000;
					Date End = new Date(timeStamp);
					Calendar calendar = new GregorianCalendar();
					calendar.setTime(End);
					int Sumyear = calendar.get(Calendar.YEAR);
					int Wintyear = Sumyear - 1;
					dataset.add(list, "RTTAvg", " Sem:" + Wintyear + "/" + Sumyear);

					if (j != JahrNumb) {
						start = endseg + 1;
						endseg = this.segs[j+1];
					}
				}
			}
			if (i == 2) {
				
				start = 0;
				endseg = this.segs[0];

				for (int j = 0; j <= JahrNumb; j++) {

					List<Double> list = new ArrayList<Double>();
					
					for (int k = start; k <= endseg; k++) {
						list.add(this.Liste[k].rttmax);
						
					}
					// get Year on y Axis
					long timeStamp = (long) Liste[endseg].stamp * 1000;
					Date End = new Date(timeStamp);
					Calendar calendar = new GregorianCalendar();
					calendar.setTime(End);
					int Sumyear = calendar.get(Calendar.YEAR);
					int Wintyear = Sumyear - 1;
					dataset.add(list, "RTTMax", " Sem:" + Wintyear + "/" + Sumyear);
					if (j != JahrNumb) {
						start = endseg + 1;
						endseg = this.segs[j+1];
					}
				}
			}
		}
		return dataset;
	}

	JFreeChart createChartBox(BoxAndWhiskerCategoryDataset dataset) {
		CategoryAxis xAxis = new CategoryAxis("Time");
		NumberAxis yAxis = new NumberAxis("RTT Time/Ping");
		BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
		renderer.setFillBox(false);
		CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);

		JFreeChart chart = new JFreeChart("Box-and-Whisker Demo", new Font("SansSerif", Font.BOLD, 14), plot, true);
		return chart;
	}
}
