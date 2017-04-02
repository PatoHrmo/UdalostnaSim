package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class HlavneOkno extends JFrame {

	private JPanel contentPane;
	private TurboOkno turboOkno;
	private SledovacieOkno sledovacieOkno;
	private PorovnavacieOkno porovnavacieOkno;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HlavneOkno frame = new HlavneOkno();
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
	public HlavneOkno() {
		turboOkno = new TurboOkno();
		turboOkno.setVisible(false);
		turboOkno.setDefaultCloseOperation(HIDE_ON_CLOSE);
		porovnavacieOkno = new PorovnavacieOkno();
		porovnavacieOkno.setVisible(false);
		porovnavacieOkno.setDefaultCloseOperation(HIDE_ON_CLOSE);
		sledovacieOkno = new SledovacieOkno();
		sledovacieOkno.setVisible(false);
		sledovacieOkno.setDefaultCloseOperation(HIDE_ON_CLOSE);
		setTitle("Simul\u00E1cia servisu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnTurboMod = new JButton("Turbo m\u00F3d");
		btnTurboMod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turboOkno.setVisible(true);
			}
		});
		btnTurboMod.setBounds(25, 17, 349, 58);
		contentPane.add(btnTurboMod);
		
		JButton btnSledovaciMod = new JButton("Sledovac\u00ED m\u00F3d");
		btnSledovaciMod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sledovacieOkno.setVisible(true);
			}
		});
		btnSledovaciMod.setBounds(25, 86, 349, 58);
		contentPane.add(btnSledovaciMod);
		
		JButton btnPorovnanie = new JButton("Porovn\u00E1vanie z\u00E1vislost\u00ED");
		btnPorovnanie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				porovnavacieOkno.setVisible(true);
			}
		});
		btnPorovnanie.setBounds(25, 155, 349, 58);
		contentPane.add(btnPorovnanie);
	}
}
