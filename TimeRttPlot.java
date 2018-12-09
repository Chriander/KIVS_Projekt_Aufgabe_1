package kivs_Package;


import java.awt.Color;
import java.awt.Font;
import java.awt.Shape;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.block.BlockBorder;

import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.util.ShapeUtilities;

public class TimeRttPlot extends JFrame {

	PingPack[] Liste;

	public TimeRttPlot(PingPack[] Liste) {
		this.Liste = Liste;
	}

	public void PlotInit() {

		XYDataset dataset = createDatasetTimeRttMin(this.Liste);

		JFreeChart chart = createChartRTT(dataset);

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

	// Creator of RTT Min
	private XYDataset createDatasetTimeRttMin(PingPack[] Liste) {

		TimeSeries series = new TimeSeries("Time vs RTT Min");
		TimeSeriesCollection dataset = new TimeSeriesCollection();

		for (int i = 0; i < Liste.length; i++) {

			long timeStamp = (long) Liste[i].stamp * 1000;

			series.addOrUpdate(new Millisecond(new Date(timeStamp)), Liste[i].rttmin);
		}

		dataset.addSeries(series);

		TimeSeries series1 = new TimeSeries("Time vs RTT Avg");

		for (int i = 0; i < Liste.length; i++) {

			long timeStamp = (long) Liste[i].stamp * 1000;

			series1.addOrUpdate(new Millisecond(new Date(timeStamp)), Liste[i].rttavg);
		}

		dataset.addSeries(series1);

		TimeSeries series2 = new TimeSeries("Time vs RTT Max");

		for (int i = 0; i < Liste.length; i++) {

			long timeStamp = (long) Liste[i].stamp * 1000;

			series2.addOrUpdate(new Millisecond(new Date(timeStamp)), Liste[i].rttmax);
		}

		dataset.addSeries(series2);
		return dataset;

	}

	// creator for RTT Avg

	private JFreeChart createChartRTT(XYDataset dataset) {

		JFreeChart chart = ChartFactory.createTimeSeriesChart("RTT", "Time", "Rtt per Ping", dataset, true, true,
				false);

		XYPlot plot = (XYPlot) chart.getPlot();

		DateAxis dateAxis = (DateAxis) plot.getDomainAxis();
		dateAxis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		Shape cross = ShapeUtilities.createDiagonalCross(3, 1);
		renderer.setSeriesPaint(0, Color.BLUE);
		renderer.setSeriesShape(0, cross);
		renderer.setSeriesLinesVisible(0, false);

		renderer.setSeriesPaint(1, Color.RED);

		renderer.setSeriesLinesVisible(1, false);

		renderer.setSeriesPaint(2, Color.GREEN);
		renderer.setBaseShapesVisible(true);
		renderer.setSeriesLinesVisible(2, false);
		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setRenderer(renderer);

		plot.setBackgroundPaint(Color.white);

		chart.getLegend().setFrame(BlockBorder.NONE);

		chart.setTitle(new TextTitle("Time vs RTT pro Ping", new Font("Serif", java.awt.Font.BOLD, 18)));

		return chart;

	}

}
