package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import autoservis.AutoServisSim;
import core.PozorovatelSimulacie;
import core.SimulacneJadro;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class SledovacieOkno extends JFrame implements PozorovatelSimulacie {

	private JPanel contentPane;
	private JTextField textFieldPocet1;
	private JTextField textFieldPocet2;
	private AutoServisSim simulacia;
	
	private JLabel lblCakanieNaOpravu;
	private JLabel lblCakanieVRade;
	private JLabel lblVolny1;
	private JLabel lblVolny2;
	private JLabel lblFrontZakaznikov;
	private JLabel lblFrontZakaznikovNaKonciDna;
	private JLabel lblFrontNeopravenych;
	private JLabel lblFrontNeopravenychNaKonciDna;
	private JLabel lblFrontOpravenych;
	private JLabel lblFrontOpravenychNaKonciDna;
	private JTextField textFieldPocetReplikacii;
	private SwingWorker<Void, Void> worker;
	private JButton btnPauza;
	private JButton btnUkoni;
	private JButton buttonPokracuj;
	private JLabel lblPomerRychlosti;
	private int dlzkaSpanku;
	private JLabel lblCas;
	private JLabel lblCasSimulacie;
	private JLabel lblPoetVolnchParcovnkov;
	private JLabel lblPoetVonchPracovnkov;
	private JLabel lblDkaFrontuZkaznkov;
	private JLabel lblDkaFrontuut;
	private JLabel lblDkaFrontuOpravench;
	private JLabel lblPocetPracovnikov1;
	private JLabel lblPocetPracovnikov2;
	private JLabel lblDlzkaFrontuZakaznikov;
	private JLabel lblDlzkaFrontuAutCakajucichNaOpravu;
	private JLabel lblDlzkaFrontuOpravenychAut;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SledovacieOkno frame = new SledovacieOkno();
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
	public SledovacieOkno() {
		setTitle("Sledovac\u00ED m\u00F3d");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPoetPracovnkovSkupiny = new JLabel("Po\u010Det pracovn\u00EDkov skupiny 1:");
		lblPoetPracovnkovSkupiny.setBounds(10, 11, 173, 14);
		contentPane.add(lblPoetPracovnkovSkupiny);
		
		textFieldPocet1 = new JTextField();
		textFieldPocet1.setBounds(193, 8, 86, 20);
		contentPane.add(textFieldPocet1);
		textFieldPocet1.setColumns(10);
		
		JLabel lblPoetPracovnkovSkupiny_1 = new JLabel("Po\u010Det pracovn\u00EDkov skupiny 2:");
		lblPoetPracovnkovSkupiny_1.setBounds(289, 11, 174, 14);
		contentPane.add(lblPoetPracovnkovSkupiny_1);
		
		textFieldPocet2 = new JTextField();
		textFieldPocet2.setBounds(474, 8, 86, 20);
		contentPane.add(textFieldPocet2);
		textFieldPocet2.setColumns(10);
		
		JButton btntart = new JButton("\u0160tart");
		btntart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btntart.setEnabled(false);
				simulacia = new AutoServisSim(0,false, Integer.parseInt(textFieldPocet1.getText()), Integer.parseInt(textFieldPocet2.getText()));
				simulacia.pridajPozorovatela(getOkno());
				simulacia.nastavRychlost(dlzkaSpanku);
				worker = new SwingWorker<Void, Void>() {

					@Override
					protected Void doInBackground() throws Exception {
						simulacia.vykonajPocetReplikacii(Integer.parseInt(textFieldPocetReplikacii.getText()));
						return null;
					}
					@Override
					protected void done() {
						btnPauza.setEnabled(false);
						btnUkoni.setEnabled(false);
						buttonPokracuj.setEnabled(false);
						btntart.setEnabled(true);
					}
				};
				worker.execute();
				btnPauza.setEnabled(true);
				btnUkoni.setEnabled(true);
				
			}
		});
		btntart.setBounds(10, 57, 89, 23);
		contentPane.add(btntart);
		
		JLabel lblPriemernDkaakania = new JLabel("Priemern\u00E1 d\u013A\u017Eka \u010Dakania na zadanie objedn\u00E1vky :");
		lblPriemernDkaakania.setBounds(10, 91, 376, 14);
		contentPane.add(lblPriemernDkaakania);
		
		JLabel lblPriemernDkaakania_1 = new JLabel("Priemern\u00E1 d\u013A\u017Eka \u010Dakania na opravu:");
		lblPriemernDkaakania_1.setBounds(10, 116, 376, 14);
		contentPane.add(lblPriemernDkaakania_1);
		
		JLabel lblPriemernPoetVonch = new JLabel("Priemern\u00FD po\u010Det vo\u013En\u00FDch pracovn\u00EDkov skupiny 1:");
		lblPriemernPoetVonch.setBounds(10, 141, 376, 14);
		contentPane.add(lblPriemernPoetVonch);
		
		JLabel lblPriemernPoetVonch_1 = new JLabel("Priemern\u00FD po\u010Det vo\u013En\u00FDch pracovn\u00EDkov skupiny 2:");
		lblPriemernPoetVonch_1.setBounds(10, 166, 376, 14);
		contentPane.add(lblPriemernPoetVonch_1);
		
		JLabel lblPriemernDlkaFrontu = new JLabel("Priemern\u00E1 dl\u017Eka frontu z\u00E1kazn\u00EDkov:");
		lblPriemernDlkaFrontu.setBounds(10, 191, 376, 14);
		contentPane.add(lblPriemernDlkaFrontu);
		
		JLabel lblPriemernDkaFrontu = new JLabel("Priemern\u00E1 d\u013A\u017Eka frontu z\u00E1kazn\u00EDkov na konci d\u0148a:");
		lblPriemernDkaFrontu.setBounds(10, 216, 376, 14);
		contentPane.add(lblPriemernDkaFrontu);
		
		JLabel lblPriemernDkaFrontu_1 = new JLabel("Priemern\u00E1 d\u013A\u017Eka frontu neopraven\u00FDch \u00E1ut:");
		lblPriemernDkaFrontu_1.setBounds(10, 241, 376, 14);
		contentPane.add(lblPriemernDkaFrontu_1);
		
		JLabel lblPriemernDkaFrontu_2 = new JLabel("Priemern\u00E1 d\u013A\u017Eka frontu neopraven\u00FDch \u00E1ut na konci d\u0148a:");
		lblPriemernDkaFrontu_2.setBounds(10, 266, 376, 14);
		contentPane.add(lblPriemernDkaFrontu_2);
		
		JLabel lblPriemernDkaFrontu_3 = new JLabel("Priemern\u00E1 d\u013A\u017Eka frontu opraven\u00FDch \u00E1ut:");
		lblPriemernDkaFrontu_3.setBounds(10, 287, 376, 14);
		contentPane.add(lblPriemernDkaFrontu_3);
		
		JLabel lblPriemernDkaFrontu_4 = new JLabel("Priemern\u00E1 d\u013A\u017Eka frontu opraven\u00FDch \u00E1ut na konci d\u0148a:");
		lblPriemernDkaFrontu_4.setBounds(10, 312, 376, 14);
		contentPane.add(lblPriemernDkaFrontu_4);
		
		btnPauza = new JButton("Pauza");
		btnPauza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				simulacia.pozastav();
				btnUkoni.setEnabled(false);
				btnPauza.setEnabled(false);
				buttonPokracuj.setEnabled(true);
			}
		});
		btnPauza.setBounds(109, 57, 89, 23);
		contentPane.add(btnPauza);
		btnPauza.setEnabled(false);
		
		lblCakanieVRade = new JLabel("New label");
		lblCakanieVRade.setBounds(396, 91, 268, 14);
		contentPane.add(lblCakanieVRade);
		
		lblCakanieNaOpravu = new JLabel("New label");
		lblCakanieNaOpravu.setBounds(396, 116, 268, 14);
		contentPane.add(lblCakanieNaOpravu);
		
		lblVolny1 = new JLabel("New label");
		lblVolny1.setBounds(396, 141, 268, 14);
		contentPane.add(lblVolny1);
		
		lblVolny2 = new JLabel("New label");
		lblVolny2.setBounds(396, 166, 268, 14);
		contentPane.add(lblVolny2);
		
		lblFrontZakaznikov = new JLabel("New label");
		lblFrontZakaznikov.setBounds(396, 191, 268, 14);
		contentPane.add(lblFrontZakaznikov);
		
		lblFrontZakaznikovNaKonciDna = new JLabel("New label");
		lblFrontZakaznikovNaKonciDna.setBounds(396, 216, 268, 14);
		contentPane.add(lblFrontZakaznikovNaKonciDna);
		
		lblFrontNeopravenych = new JLabel("New label");
		lblFrontNeopravenych.setBounds(396, 241, 268, 14);
		contentPane.add(lblFrontNeopravenych);
		
		lblFrontNeopravenychNaKonciDna = new JLabel("New label");
		lblFrontNeopravenychNaKonciDna.setBounds(396, 266, 268, 14);
		contentPane.add(lblFrontNeopravenychNaKonciDna);
		
		lblFrontOpravenych = new JLabel("New label");
		lblFrontOpravenych.setBounds(396, 287, 268, 14);
		contentPane.add(lblFrontOpravenych); 
		
		lblFrontOpravenychNaKonciDna = new JLabel("New label");
		lblFrontOpravenychNaKonciDna.setBounds(396, 312, 268, 14);
		contentPane.add(lblFrontOpravenychNaKonciDna);
		
		JLabel lblPoetReplikci = new JLabel("Po\u010Det replik\u00E1ci\u00ED:");
		lblPoetReplikci.setBounds(10, 32, 173, 14);
		contentPane.add(lblPoetReplikci);
		
		textFieldPocetReplikacii = new JTextField();
		textFieldPocetReplikacii.setBounds(193, 29, 86, 20);
		contentPane.add(textFieldPocetReplikacii);
		textFieldPocetReplikacii.setColumns(10);
		
		btnUkoni = new JButton("Ukon\u010Di\u0165");
		btnUkoni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				simulacia.zastav();
				btntart.setEnabled(true);
				btnPauza.setEnabled(false);
				btnUkoni.setEnabled(false);
			}
		});
		btnUkoni.setBounds(297, 57, 89, 23);
		contentPane.add(btnUkoni);
		btnUkoni.setEnabled(false);
		
		buttonPokracuj = new JButton("Pokra\u010Duj");
		buttonPokracuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				simulacia.pokracuj();
				buttonPokracuj.setEnabled(false);
				btnUkoni.setEnabled(true);
				btnPauza.setEnabled(true);
			}
		});
		buttonPokracuj.setBounds(203, 57, 89, 23);
		contentPane.add(buttonPokracuj);
		buttonPokracuj.setEnabled(false);
		
		JSlider sliderPomerCasu = new JSlider();
		sliderPomerCasu.setMaximum(5000);
		sliderPomerCasu.setValue(5000);
		sliderPomerCasu.setBounds(617, 11, 200, 23);
		contentPane.add(sliderPomerCasu);
		sliderPomerCasu.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				dlzkaSpanku = sliderPomerCasu.getValue();
				if(dlzkaSpanku==0) {
					lblPomerRychlosti.setText("maximum");
				} else {
					lblPomerRychlosti.setText(Double.toString(((double)sliderPomerCasu.getMaximum())/dlzkaSpanku));
				}
				if(simulacia!=null)
				simulacia.nastavRychlost(dlzkaSpanku);
			}
		});
		
		JLabel lblAktulnyPomerRchlosti = new JLabel("Aktu\u00E1lny pomer r\u00FDchlosti: ");
		lblAktulnyPomerRchlosti.setBounds(827, 11, 228, 14);
		contentPane.add(lblAktulnyPomerRchlosti);
		
		lblPomerRychlosti = new JLabel("New label");
		lblPomerRychlosti.setBounds(1065, 11, 109, 14);
		contentPane.add(lblPomerRychlosti);
		
		lblCas = new JLabel("Cas :");
		lblCas.setBounds(617, 45, 67, 14);
		contentPane.add(lblCas);
		
		lblCasSimulacie = new JLabel("New label");
		lblCasSimulacie.setBounds(693, 45, 481, 14);
		contentPane.add(lblCasSimulacie);
		
		lblPoetVolnchParcovnkov = new JLabel("Po\u010Det voln\u00FDch parcovn\u00EDkov 1:");
		lblPoetVolnchParcovnkov.setBounds(693, 91, 199, 14);
		contentPane.add(lblPoetVolnchParcovnkov);
		
		lblPoetVonchPracovnkov = new JLabel("Po\u010Det vo\u013En\u00FDch pracovn\u00EDkov 2:");
		lblPoetVonchPracovnkov.setBounds(693, 116, 199, 14);
		contentPane.add(lblPoetVonchPracovnkov);
		
		lblDkaFrontuZkaznkov = new JLabel("D\u013A\u017Eka frontu z\u00E1kazn\u00EDkov:");
		lblDkaFrontuZkaznkov.setBounds(693, 141, 199, 14);
		contentPane.add(lblDkaFrontuZkaznkov);
		
		lblDkaFrontuut = new JLabel("D\u013A\u017Eka frontu \u00E1ut \u010Dakaj\u00FAcich na opravu: ");
		lblDkaFrontuut.setBounds(693, 166, 240, 14);
		contentPane.add(lblDkaFrontuut);
		
		lblDkaFrontuOpravench = new JLabel("D\u013A\u017Eka frontu opraven\u00FDch \u00E1ut:");
		lblDkaFrontuOpravench.setBounds(693, 191, 240, 14);
		contentPane.add(lblDkaFrontuOpravench);
		
		lblPocetPracovnikov1 = new JLabel("New label");
		lblPocetPracovnikov1.setBounds(1009, 91, 46, 14);
		contentPane.add(lblPocetPracovnikov1);
		
		lblPocetPracovnikov2 = new JLabel("New label");
		lblPocetPracovnikov2.setBounds(1009, 116, 46, 14);
		contentPane.add(lblPocetPracovnikov2);
		
		lblDlzkaFrontuZakaznikov = new JLabel("New label");
		lblDlzkaFrontuZakaznikov.setBounds(1009, 141, 46, 14);
		contentPane.add(lblDlzkaFrontuZakaznikov);
		
		lblDlzkaFrontuAutCakajucichNaOpravu = new JLabel("New label");
		lblDlzkaFrontuAutCakajucichNaOpravu.setBounds(1009, 166, 46, 14);
		contentPane.add(lblDlzkaFrontuAutCakajucichNaOpravu);
		
		lblDlzkaFrontuOpravenychAut = new JLabel("New label");
		lblDlzkaFrontuOpravenychAut.setBounds(1009, 191, 46, 14);
		contentPane.add(lblDlzkaFrontuOpravenychAut);
		dlzkaSpanku = sliderPomerCasu.getValue();
	}

	@Override
	public void refresh(SimulacneJadro simJadro) {
		lblCakanieVRade.setText(Double.toString(((AutoServisSim)simJadro).getIsCakaniaVRade(false)[0])+" ; "+
				Double.toString(((AutoServisSim)simJadro).getIsCakaniaVRade(false)[1]));
		lblCakanieNaOpravu.setText(Double.toString(((AutoServisSim)simJadro).getPriemernaDobaCakaniaNaOpravu(false)));
		lblFrontNeopravenych.setText(Double.toString(((AutoServisSim)simJadro).getPriemernaDlzkaFrontuNeopravenychAut(false)));
		lblFrontNeopravenychNaKonciDna.setText(Double.toString(((AutoServisSim)simJadro).getPriemernaDlzkaFrontuNeopravenychAutNaKonciDna(false)));
		lblFrontOpravenych.setText(Double.toString(((AutoServisSim)simJadro).getPriemernaDlzkaFrontuOpravenychAut(false)));
		lblFrontOpravenychNaKonciDna.setText(Double.toString(((AutoServisSim)simJadro).getPriemernaDlzkaFrontuOpravenychAutNaKonciDna(false)));
		lblFrontZakaznikov.setText(Double.toString(((AutoServisSim)simJadro).getPriemernaDlzkaFrontuZakaznikov(false)));
		lblFrontZakaznikovNaKonciDna.setText(Double.toString(((AutoServisSim)simJadro).getPriemernaDlzkaFrontuZakaznikovNaKonciDna(false)));
		lblVolny1.setText(Double.toString(((AutoServisSim)simJadro).getPriemernyPocetVolnychPracovnikov1(false)));
		lblVolny2.setText(Double.toString(((AutoServisSim)simJadro).getPriemernyPocetVolnychPracovnikov2(false)));
		lblPocetPracovnikov1.setText(Integer.toString(((AutoServisSim)simJadro).getAktualnyPocetRobotnikov1()));
		lblPocetPracovnikov2.setText(Integer.toString(((AutoServisSim)simJadro).getAktualnyPocetRobotnikov2()));
		lblDlzkaFrontuAutCakajucichNaOpravu.setText(Integer.toString(((AutoServisSim)simJadro).getPocetPokazenychAutVoFronte()));
		lblDlzkaFrontuOpravenychAut.setText(Integer.toString(((AutoServisSim)simJadro).getPocetOpravenychAutVoFronte()));
		lblDlzkaFrontuZakaznikov.setText(Integer.toString(((AutoServisSim)simJadro).getPocetZakaznikovVoFronte()));
		lblCasSimulacie.setText(getCasVLudskejPodobe(simJadro.getSimulacnyCas()));
	}
	public String getCasVLudskejPodobe(double casSimulacie) {
		long cas = Math.round(casSimulacie);
		long replikacie = cas/(8*60*60*90);
		cas -= replikacie*(8*60*60*90);
		long dni = cas/(8*60*60);
		cas-=dni*(8*60*60);
		long hodiny = cas/(60*60);
		cas -= hodiny*(60*60);
		long minuty = cas/(60);
		cas -= minuty*60;
		long sekundy = cas;
		LocalTime lCas = LocalTime.of((int)hodiny+7, (int)minuty, (int)sekundy);
		return "Replikácia èíslo: "+(replikacie+1)+" deò: "+(dni+1)+" èas : "+lCas.toString();
	}
	public SledovacieOkno getOkno() {
		return this;
	}
}
