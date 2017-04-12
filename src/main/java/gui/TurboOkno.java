package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class TurboOkno extends JFrame implements PozorovatelSimulacie {

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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TurboOkno frame = new TurboOkno();
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
	public TurboOkno() {
		setTitle("Turbo m\u00F3d");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 393);
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
				simulacia = new AutoServisSim(0,true, Integer.parseInt(textFieldPocet1.getText()), Integer.parseInt(textFieldPocet2.getText()));
				simulacia.pridajPozorovatela(getOkno());
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
		lblPriemernDkaFrontu_3.setBounds(10, 291, 376, 14);
		contentPane.add(lblPriemernDkaFrontu_3);
		
		JLabel lblPriemernDkaFrontu_4 = new JLabel("Priemern\u00E1 d\u013A\u017Eka frontu opraven\u00FDch \u00E1ut na konci d\u0148a:");
		lblPriemernDkaFrontu_4.setBounds(10, 316, 376, 14);
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
		
		lblCakanieVRade = new JLabel("");
		lblCakanieVRade.setBounds(396, 91, 268, 14);
		contentPane.add(lblCakanieVRade);
		
		lblCakanieNaOpravu = new JLabel("");
		lblCakanieNaOpravu.setBounds(396, 116, 268, 14);
		contentPane.add(lblCakanieNaOpravu);
		
		lblVolny1 = new JLabel("");
		lblVolny1.setBounds(396, 141, 268, 14);
		contentPane.add(lblVolny1);
		
		lblVolny2 = new JLabel("");
		lblVolny2.setBounds(396, 166, 268, 14);
		contentPane.add(lblVolny2);
		
		lblFrontZakaznikov = new JLabel("");
		lblFrontZakaznikov.setBounds(396, 191, 268, 14);
		contentPane.add(lblFrontZakaznikov);
		
		lblFrontZakaznikovNaKonciDna = new JLabel("");
		lblFrontZakaznikovNaKonciDna.setBounds(396, 216, 268, 14);
		contentPane.add(lblFrontZakaznikovNaKonciDna);
		
		lblFrontNeopravenych = new JLabel("");
		lblFrontNeopravenych.setBounds(396, 241, 268, 14);
		contentPane.add(lblFrontNeopravenych);
		
		lblFrontNeopravenychNaKonciDna = new JLabel("");
		lblFrontNeopravenychNaKonciDna.setBounds(396, 266, 268, 14);
		contentPane.add(lblFrontNeopravenychNaKonciDna);
		
		lblFrontOpravenych = new JLabel("");
		lblFrontOpravenych.setBounds(396, 291, 268, 14);
		contentPane.add(lblFrontOpravenych); 
		
		lblFrontOpravenychNaKonciDna = new JLabel("");
		lblFrontOpravenychNaKonciDna.setBounds(396, 316, 268, 14);
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
		
	}

	@Override
	public void refresh(SimulacneJadro simJadro) {
		lblCakanieVRade.setText(String.format("%.2f",(((AutoServisSim)simJadro).getIsCakaniaVRade(true)[0]))+" ; "+
				String.format("%.2f", (((AutoServisSim)simJadro).getIsCakaniaVRade(true)[1]))+" sekúnd");
		lblCakanieNaOpravu.setText(String.format("%.2f", (((AutoServisSim)simJadro).getIsCakaniaNaOpravu(true)[0]))+" ; "+
				String.format("%.2f", (((AutoServisSim)simJadro).getIsCakaniaNaOpravu(true)[1]))+" sekúnd");
		lblFrontNeopravenych.setText(String.format("%.2f", (((AutoServisSim)simJadro).getPriemernaDlzkaFrontuNeopravenychAut(true))));
		lblFrontNeopravenychNaKonciDna.setText(String.format("%.2f", (((AutoServisSim)simJadro).getPriemernaDlzkaFrontuNeopravenychAutNaKonciDna(true))));
		lblFrontOpravenych.setText(String.format("%.2f", (((AutoServisSim)simJadro).getPriemernaDlzkaFrontuOpravenychAut(true))));
		lblFrontOpravenychNaKonciDna.setText(String.format("%.2f", (((AutoServisSim)simJadro).getPriemernaDlzkaFrontuOpravenychAutNaKonciDna(true))));
		lblFrontZakaznikov.setText(String.format("%.2f",(((AutoServisSim)simJadro).getPriemernaDlzkaFrontuZakaznikov(true))));
		lblFrontZakaznikovNaKonciDna.setText(String.format("%.2f", (((AutoServisSim)simJadro).getPriemernaDlzkaFrontuZakaznikovNaKonciDna(true))));
		lblVolny1.setText(String.format("%.2f", (((AutoServisSim)simJadro).getPriemernyPocetVolnychPracovnikov1(true))));
		lblVolny2.setText(String.format("%.2f", (((AutoServisSim)simJadro).getPriemernyPocetVolnychPracovnikov2(true))));
	}
	public TurboOkno getOkno() {
		return this;
	}
}
