package com.tnv.mypackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SceltaMenuFisso extends JFrame {

	private JPanel contentPane;
	private JTextField fieldPrezzo;
	private JTextField fieldSconto;
	private JTextField fieldPrScontato;
	ArrayList<Alimento> alimenti;
	private JTextField fieldNomeMenu;

	public SceltaMenuFisso() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Seleziona gli alimenti da includere nel menu fisso");
		title.setBounds(5, 5, 297, 16);
		contentPane.add(title);
		
		DefaultListModel<String> listModelAlimenti = new DefaultListModel<String>();
		for(Alimento item:Menu.elenco) {
			listModelAlimenti.addElement(item.getNome());
		}
		
		JList<String> listaAlimenti = new JList<String>(listModelAlimenti);
		listaAlimenti.setBounds(5, 32, 573, 163);
		listaAlimenti.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listaAlimenti.setLayoutOrientation(JList.VERTICAL_WRAP);
		listaAlimenti.setVisibleRowCount(-1);
		contentPane.add(listaAlimenti);	
		
		JLabel lblPrezzo = new JLabel("Prezzo");
		lblPrezzo.setBounds(5, 260, 170, 14);
		contentPane.add(lblPrezzo);
		
		fieldPrezzo = new JTextField();
		fieldPrezzo.setBounds(5, 276, 127, 20);
		contentPane.add(fieldPrezzo);
		fieldPrezzo.setColumns(10);
		fieldPrezzo.setEnabled(false);
		
		JLabel lblSconto = new JLabel("Sconto");
		lblSconto.setBounds(185, 260, 170, 14);
		contentPane.add(lblSconto);
		
		fieldSconto = new JTextField();
		fieldSconto.setBounds(185, 276, 127, 20);
		contentPane.add(fieldSconto);
		fieldSconto.setColumns(10);
		
		JLabel lblPrezzoScontato = new JLabel("Prezzo scontato");
		lblPrezzoScontato.setBounds(365, 260, 170, 14);
		contentPane.add(lblPrezzoScontato);
		
		fieldPrScontato = new JTextField("0");
		fieldPrScontato.setBounds(365, 276, 127, 20);
		contentPane.add(fieldPrScontato);
		fieldPrScontato.setColumns(10);
		fieldPrScontato.setEnabled(false);
		
		JButton btnCreaMenu = new JButton("Crea menu fisso");
		btnCreaMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				alimenti = Alimento.getAlimentiFromStrings(listaAlimenti.getSelectedValuesList());
				MenuFisso.elencoMenuFissi.add(new MenuFisso(fieldNomeMenu.getText(), Float.parseFloat(fieldSconto.getText()), alimenti));
			}
		});
		btnCreaMenu.setBounds(196, 321, 383, 29);
		contentPane.add(btnCreaMenu);
		
		JButton btnCalcola = new JButton("Calcola prezzo");
		btnCalcola.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					alimenti = Alimento.getAlimentiFromStrings(listaAlimenti.getSelectedValuesList());
					Float prezzo = Alimento.getPrezzoAlimentiSelezionati(alimenti);
					Float prezzoScontato = Alimento.getPrezzoScontatoAlimentiSelezionati(prezzo, Float.parseFloat(fieldSconto.getText()));
					fieldPrezzo.setText(prezzo.toString() + "€");
					fieldPrScontato.setText(prezzoScontato.toString() + "€");
				} catch(Exception exc) {
					JDialog dialogError = new ErrorDialog("Errore: imposta la percentuale di sconto.");
					dialogError.setVisible(true);
				}
			}
		});
		btnCalcola.setBounds(5, 321, 181, 29);
		contentPane.add(btnCalcola);
		
		JLabel lblNomeMenu = new JLabel("Nome menu");
		lblNomeMenu.setBounds(5, 210, 154, 14);
		contentPane.add(lblNomeMenu);
		
		fieldNomeMenu = new JTextField();
		fieldNomeMenu.setBounds(5, 228, 307, 20);
		contentPane.add(fieldNomeMenu);
		fieldNomeMenu.setColumns(10);
	}
}
