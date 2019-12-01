package com.tnv.mypackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.tnv.mypackage.Alimento.Allergene;

public class ModificaAlimento extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificaAlimento frame = new ModificaAlimento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ModificaAlimento() {}
	
	/**
	 * Create the frame.
	 */
	public ModificaAlimento(boolean ciboBevandaFlag) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ciboBevandaFlag = true;
		
		JLabel title = new JLabel("Seleziona " + (ciboBevandaFlag ? "il cibo " : "la bevanda ") + "da modificare");
		title.setBounds(5, 5, 297, 16);
		contentPane.add(title);
		
		DefaultListModel<Alimento> listModelAlimenti = new DefaultListModel<Alimento>();
		for(Alimento item:Menu.elenco) {
			if (ciboBevandaFlag) {
				if (item instanceof Cibo)
					listModelAlimenti.addElement(item);
			} else {
				if (item instanceof Bevanda)
					listModelAlimenti.addElement(item);
			}
		}
		
		JList<Alimento> listaAlimenti = new JList<Alimento>(listModelAlimenti);
		listaAlimenti.setBounds(5, 32, 573, 250);
		listaAlimenti.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listaAlimenti.setLayoutOrientation(JList.VERTICAL_WRAP);
		listaAlimenti.setVisibleRowCount(-1);
		contentPane.add(listaAlimenti);
		
		JButton btnAggiungiAlimento = new JButton("Modifica");
		btnAggiungiAlimento.setBounds(5, 321, 573, 29);
		contentPane.add(btnAggiungiAlimento);
	}

}
