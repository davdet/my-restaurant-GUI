package com.tnv.mypackage;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.SystemColor;

public class myGUI {

	private JFrame frame;
	private Color myMainColor = SystemColor.window;
	private static JTextField textFieldCibi;
	private static JTextField textFieldBevande;
	private static JTextField textFieldAlimenti;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myGUI window = new myGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public myGUI() {
		initialize();
	}

	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(myMainColor);
		frame.setBounds(100, 100, 600, 400);
		centraFinestra(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPiattiInArchivio = new JLabel("Piatti in archivio");
		
		lblPiattiInArchivio.setBounds(21, 25, 155, 16);
		frame.getContentPane().add(lblPiattiInArchivio);
		textFieldCibi = new JTextField();
		textFieldCibi.setEditable(false);
		textFieldCibi.setBounds(181, 20, 130, 26);
		frame.getContentPane().add(textFieldCibi);
		textFieldCibi.setColumns(10);
		
		JLabel lblBevandeInArchivio = new JLabel("Bevande in archivio");
		lblBevandeInArchivio.setBounds(21, 53, 155, 16);
		frame.getContentPane().add(lblBevandeInArchivio);
		textFieldBevande = new JTextField();
		textFieldBevande.setEditable(false);
		textFieldBevande.setBounds(181, 48, 130, 26);
		frame.getContentPane().add(textFieldBevande);
		textFieldBevande.setColumns(10);

		JLabel lblAlimentiInArchivio = new JLabel("Alimenti in archivio");
		lblAlimentiInArchivio.setBounds(21, 81, 155, 16);
		frame.getContentPane().add(lblAlimentiInArchivio);
		textFieldAlimenti = new JTextField();
		textFieldAlimenti.setEditable(false);
		textFieldAlimenti.setBounds(181, 76, 130, 26);
		frame.getContentPane().add(textFieldAlimenti);
		textFieldAlimenti.setColumns(10);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);
		
		JMenu menuAggiungi = new JMenu("Aggiungi");
		menu.add(menuAggiungi);
		
		JMenuItem aggiungiCibo = new JMenuItem("Cibo");
		aggiungiCibo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centraFinestra(new InserimentoAlimento(true));
			}
		});
		menuAggiungi.add(aggiungiCibo);
	
		JMenuItem aggiungiBevanda = new JMenuItem("Bevanda");
		aggiungiBevanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centraFinestra(new InserimentoAlimento(false));
			}
		});
		menuAggiungi.add(aggiungiBevanda);
		
		JMenu mnModifica = new JMenu("Modifica");
		menu.add(mnModifica);
		
		JMenuItem mntmCibo = new JMenuItem("Cibo");
		mntmCibo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centraFinestra(new SelezioneAlimento(true, false));
			}
		});
		mnModifica.add(mntmCibo);
		
		JMenuItem mntmBevanda = new JMenuItem("Bevanda");
		mntmBevanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centraFinestra(new SelezioneAlimento(false, false));
			}
		});
		mnModifica.add(mntmBevanda);
		
		JMenu mnCancella = new JMenu("Cancella");
		menu.add(mnCancella);
		
		JMenuItem mntmCibo_1 = new JMenuItem("Cibo");
		mntmCibo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centraFinestra(new SelezioneAlimento(true, true));
			}
		});
		mnCancella.add(mntmCibo_1);
		
		JMenuItem mntmBevanda_1 = new JMenuItem("Bevanda");
		mntmBevanda_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centraFinestra(new SelezioneAlimento(false, true));
			}
		});
		mnCancella.add(mntmBevanda_1);
		
		JMenu mnFile = new JMenu("File");
		menu.add(mnFile);
		
		JMenuItem mntnFileSalva = new JMenuItem("Salva");
		mntnFileSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Alimento.salvaTuttoSuFile(Menu.elenco);
			}
		});
		mnFile.add(mntnFileSalva);
		
		JMenuItem mntnFileCarica = new JMenuItem("Apri");
		mntnFileCarica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		mnFile.add(mntnFileCarica);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Esci");
		menu.add(mntmNewMenuItem_1);
	}
	
	public static void centraFinestra(JFrame ordineFrame) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double xCenter, yCenter;
		double screenWidth = screenSize.getWidth();
		double screenHeight = screenSize.getHeight();
		xCenter = screenWidth/2;
		yCenter = screenHeight/2;
		int screenWidthOrdineFrame = 600;
		int screenHeightOrdineFrame = 400;
		int xOrdineFrame = (int)(xCenter - screenWidthOrdineFrame/2);
		int yOrdineFrame = (int)(yCenter - screenHeightOrdineFrame/2);
		ordineFrame.setBounds(xOrdineFrame, yOrdineFrame, screenWidthOrdineFrame, screenHeightOrdineFrame);
		ordineFrame.setVisible(true);
	}
	
	static void update(){
		textFieldCibi.setText(String.valueOf(Menu.getNumeroCibi()));
		textFieldBevande.setText(String.valueOf(Menu.getNumeroBevande()));
		textFieldAlimenti.setText(String.valueOf(Menu.getNumeroAlimenti()));
	}
}
