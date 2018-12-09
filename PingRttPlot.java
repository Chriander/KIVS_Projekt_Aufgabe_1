package kivs_Package;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class PingRttPlot extends JFrame {
	PingPack[] Liste;

	public PingRttPlot(PingPack[] Liste) {
		this.Liste = Liste;
	}

	public void PlotInit() {
		XYDataset dataset = createDatasetPingRttMin(this.Liste);
		JFreeChart chart = createChartPing(dataset);

		ChartPanel chartPanel = new ChartPanel(chart);

		chartPanel.setFillZoomRectangle(true);
		chartPanel.setMouseWheelEnabled(true);
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);

		add(chartPanel);

		pack();
		setTitle("");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private XYDataset createDatasetPingRttMin(PingPack[] Liste) {

		XYSeries series = new XYSeries("Ping vs RTT Min");

		XYSeriesCollection dataset = new XYSeriesCollection();

		for (int i = 0; i < Liste.length; i++) {

			series.add(i + 1, Liste[i].rttmin);
		}

		dataset.addSeries(series);

		XYSeries series1 = new XYSeries("Ping vs RTT Avg");

		for (int i = 0; i < Liste.length; i++) {

			series1.add(i + 1, Liste[i].rttavg);
		}

		dataset.addSeries(series1);

		XYSeries series2 = new XYSeries("Ping vs RTT Max");

		for (int i = 0; i < Liste.length; i++) {

			series2.add(i + 1, Liste[i].rttmax);
		}

		dataset.addSeries(series2);
		return dataset;

	}

	private JFreeChart createChartPing(XYDataset dataset) {

		JFreeChart chart = ChartFactory.createXYLineChart("", "Ping", "Rtt per Ping", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		XYPlot plot = (XYPlot) chart.getPlot();

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.BLUE);
		renderer.setBaseShapesVisible(false);
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));

		renderer.setSeriesPaint(1, Color.RED);
		renderer.setBaseShapesVisible(false);
		renderer.setSeriesStroke(1, new BasicStroke(2.0f));

		renderer.setSeriesPaint(2, Color.GREEN);
		renderer.setBaseShapesVisible(false);
		renderer.setSeriesStroke(2, new BasicStroke(2.0f));
		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		

		plot.setRenderer(renderer);
		
		plot.setBackgroundPaint(Color.white);

		chart.getLegend().setFrame(BlockBorder.NONE);

		chart.setTitle(new TextTitle("Ping vs RTT", new Font("Serif", java.awt.Font.BOLD, 18)));

		return chart;

	}

}
