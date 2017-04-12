package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import autoservis.AutoServisSim;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class PorovnavacieOkno extends JFrame {

	private JPanel contentPane;
	private XYSeries dataZavislost1;
	private XYSeries dataZavislost2;
	private JPanel panelZavislostPracovnici1;
	private JPanel panelZavislostPracovnici2;
	private JButton btnStop;
	private JTextField textFieldPocetReplikacii;
	private JLabel lblPoetReplikciNa;
	private SwingWorker<Void, Double[]> worker;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PorovnavacieOkno frame = new PorovnavacieOkno();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PorovnavacieOkno() {
		setTitle("Porovn\u00E1vanie z\u00E1visloti");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1063, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnStart = new JButton("\u0160tart");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStop.setEnabled(true);
				btnStart.setEnabled(false);
				dataZavislost1.clear();
				dataZavislost2.clear();
				worker = new SwingWorker<Void, Double[]>() {
					int pocetReplikacii = Integer.parseInt(textFieldPocetReplikacii.getText());
					@Override
					protected Void doInBackground() throws Exception {
						AutoServisSim simulacia1;
						AutoServisSim simulacia2;
						for(int i = 0; i< 10 && !isCancelled(); i++) {
							simulacia1 = new AutoServisSim(0, true, i+1, 21);
							simulacia2 = new AutoServisSim(0, true, 5, i+16);
							simulacia1.vykonajPocetReplikacii(pocetReplikacii);
							simulacia2.vykonajPocetReplikacii(pocetReplikacii);
							publish(new Double[]{-1d,i+1d, simulacia1.getPriemernaDlzkaFrontuZakaznikov(true)});
							publish(new Double[]{1d,i+16d, simulacia2.getPriemDlzkaVServise(true)});
						}
						return null;
					}
					protected void done(){
						btnStop.setEnabled(false);
						btnStart.setEnabled(true);
					}
					protected void process(List<Double[]> vysledky) {
						for(Double[] vysledok : vysledky) {
							if(vysledok[0]<0) {
								dataZavislost1.add(vysledok[1], vysledok[2]);
							} else {
								dataZavislost2.add(vysledok[1], vysledok[2]);
							}
						}
					}
				};
				worker.execute();
			}
		});
		btnStart.setBounds(20, 11, 89, 23);
		contentPane.add(btnStart);
		
		panelZavislostPracovnici1 = new JPanel();
		panelZavislostPracovnici1.setBounds(20, 45, 500, 319);
		contentPane.add(panelZavislostPracovnici1);
		
		panelZavislostPracovnici2 = new JPanel();
		panelZavislostPracovnici2.setBounds(530, 45, 500, 319);
		contentPane.add(panelZavislostPracovnici2);
		
		btnStop = new JButton("Stop");
		btnStop.setEnabled(false);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStart.setEnabled(true);
				btnStop.setEnabled(false);
				worker.cancel(true);
			}
		});
		btnStop.setBounds(119, 11, 89, 23);
		contentPane.add(btnStop);
		
		textFieldPocetReplikacii = new JTextField();
		textFieldPocetReplikacii.setBounds(415, 12, 86, 20);
		contentPane.add(textFieldPocetReplikacii);
		textFieldPocetReplikacii.setColumns(10);
		
		lblPoetReplikciNa = new JLabel("Po\u010Det replik\u00E1ci\u00ED na test:");
		lblPoetReplikciNa.setBounds(252, 15, 153, 14);
		contentPane.add(lblPoetReplikciNa);
		nastavGrafy();
	}

	private void nastavGrafy() {
		dataZavislost1 = new XYSeries("Priemerný poèet èakajúcich v rade na poète pracovníkov 1");
		dataZavislost2 = new XYSeries("Priemer èasu v servise na poète pracovníkov 2");
		
		final JFreeChart grafZavislost1 = ChartFactory.createXYLineChart("Priemerný poèet èakajúcich v rade na poète pracovníkov 1",
				"Poèet pracovníkov 1", "Priemerný poèet zákazníkov v rade", new XYSeriesCollection(dataZavislost1), PlotOrientation.VERTICAL,
				false, true, false);
		grafZavislost1.setBorderPaint(Color.black);
		XYPlot plotZavislost1 = (XYPlot) grafZavislost1.getPlot();
		((NumberAxis) plotZavislost1.getDomainAxis()).setAutoTickUnitSelection(true);
		plotZavislost1.getRangeAxis().setAutoRange(true);
		((NumberAxis) plotZavislost1.getRangeAxis()).setAutoRangeIncludesZero(false);
		plotZavislost1.getRenderer().setSeriesPaint(0, Color.blue);
		panelZavislostPracovnici1.setLayout(new BorderLayout(0, 0));
		panelZavislostPracovnici1.add(new ChartPanel(grafZavislost1));
		
		final JFreeChart grafZavislost2 = ChartFactory.createXYLineChart("Priemer èasu v servise na poète pracovníkov 2",
				"Poèet pracovníkov 2", "Priemerný èas zákazníka v systéme", new XYSeriesCollection(dataZavislost2), PlotOrientation.VERTICAL,
				false, true, false);
		grafZavislost2.setBorderPaint(Color.black);
		XYPlot plotZavislost2 = (XYPlot) grafZavislost2.getPlot();
		((NumberAxis) plotZavislost2.getDomainAxis()).setAutoTickUnitSelection(true);
		plotZavislost2.getRangeAxis().setAutoRange(true);
		((NumberAxis) plotZavislost2.getRangeAxis()).setAutoRangeIncludesZero(false);
		plotZavislost2.getRenderer().setSeriesPaint(0, Color.blue);
		panelZavislostPracovnici2.setLayout(new BorderLayout(0, 0));
		panelZavislostPracovnici2.add(new ChartPanel(grafZavislost2));
		
	}
}
