package com.tnv.mypackage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TestDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	public TestDialog(String s, Alimento a) {
		JPanel buttonPane = init();
		
		JLabel lblSeiSicuroDi = new JLabel("Sei sicuro di voler aggiungere " + a.getNome() + " al menu?");
		contentPanel.add(lblSeiSicuroDi);
		
		JButton okButton = new JButton("OK");
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Menu.aggiungiAlimento(a);
				Menu.elenco.get(Menu.elenco.size()-1).stampaInfo();
				setVisible(false);
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	}
	
	public TestDialog(String s, Alimento a, int index) {
		JPanel buttonPane = init();
		
		JLabel lblSeiSicuroDi = new JLabel("Sei sicuro di voler modificare " + a.getNome() + "?");
		contentPanel.add(lblSeiSicuroDi);
		
		JButton okButton = new JButton("OK");
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Menu.modificaAlimento(a, index);
				Menu.elenco.get(Menu.elenco.size()-1).stampaInfo();
				setVisible(false);
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	}
	
	private JPanel init() {
		setBounds(100, 100, 400, 100);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
		return buttonPane;
	}
}
