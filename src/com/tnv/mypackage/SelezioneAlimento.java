package com.tnv.mypackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.tnv.mypackage.Alimento.Allergene;

public class SelezioneAlimento extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelezioneAlimento frame = new SelezioneAlimento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SelezioneAlimento() {}
	
	/**
	 * Create the frame.
	 */
	public SelezioneAlimento(boolean cibiBevandeFlag, boolean modCancFlag) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Seleziona " + (cibiBevandeFlag ? "il cibo " : "la bevanda ") + "da modificare");
		title.setBounds(5, 5, 297, 16);
		contentPane.add(title);
		
		DefaultListModel<String> listModelAlimenti = new DefaultListModel<String>();
		for(Alimento item:Menu.elenco) {
			if (cibiBevandeFlag) {
				if (item instanceof Cibo)
					listModelAlimenti.addElement(item.getNome());
			} else {
				if (item instanceof Bevanda)
					listModelAlimenti.addElement(item.getNome());
			}
		}
		
		JList<String> listaAlimenti = new JList<String>(listModelAlimenti);
		listaAlimenti.setBounds(5, 32, 573, 250);
		listaAlimenti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaAlimenti.setLayoutOrientation(JList.VERTICAL_WRAP);
		listaAlimenti.setVisibleRowCount(-1);
		contentPane.add(listaAlimenti);
		
		JButton btnModificaAlimento = new JButton(modCancFlag ? "Elimina" : "Modifica");
		btnModificaAlimento.setBounds(5, 321, 573, 29);
		btnModificaAlimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!modCancFlag)
					myGUI.centraFinestra(new InserimentoAlimento(cibiBevandeFlag));
				else {
					Menu.eliminaAlimento(Alimento.getAlimentoFromString(listaAlimenti.getSelectedValue(), cibiBevandeFlag));
				}
			}
		});
		contentPane.add(btnModificaAlimento);
	}

}
