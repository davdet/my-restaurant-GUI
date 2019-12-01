package com.tnv.mypackage;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.tnv.mypackage.Alimento.Allergene;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;

public class InserimentoAlimento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nomeAlimento, prezzoAlimento;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InserimentoAlimento frame = new InserimentoAlimento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public InserimentoAlimento() {}
	
	public InserimentoAlimento(boolean cibiBevandeFlag) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNomeAlimento = new JLabel("Nome");
		lblNomeAlimento.setBounds(5, 5, 96, 16);
		contentPane.add(lblNomeAlimento);
		
		nomeAlimento = new JTextField();
		nomeAlimento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(!nomeAlimento.getText().isEmpty())
					nomeAlimento.setBackground(new Color(49, 173, 71));
				else
					nomeAlimento.setBackground(new Color(235, 108, 82));
			}
		});
		nomeAlimento.setToolTipText("Inserisci il nome dell'alimento");
		nomeAlimento.setBounds(5, 26, 573, 26);
		nomeAlimento.setColumns(35);
		contentPane.add(nomeAlimento);
		
		JLabel lblPrezzoAlimento = new JLabel("Prezzo");
		lblPrezzoAlimento.setBounds(5, 57, 96, 16);
		contentPane.add(lblPrezzoAlimento);
		
		prezzoAlimento = new JTextField();
		prezzoAlimento.setToolTipText("Inserisci il prezzo, usa il punto come separatore decimale.");
		prezzoAlimento.setBounds(5, 78, 573, 26);
		prezzoAlimento.setColumns(35);
		contentPane.add(prezzoAlimento);
		
		JCheckBox chckbxVegan = new JCheckBox("Vegan");
		chckbxVegan.setBounds(5, 116, 109, 23);
		contentPane.add(chckbxVegan);
		
		JCheckBox chckbxVegetariano = new JCheckBox("Vegetariano");
		chckbxVegetariano.setBounds(120, 116, 128, 23);
		contentPane.add(chckbxVegetariano);

		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for(Allergene item:Allergene.values())
			listModel.addElement(item.getNomeAllergene());

		JList<String> listaAllergeni = new JList<String>(listModel);
		listaAllergeni.setBounds(5, 210, 573, 100);
		listaAllergeni.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listaAllergeni.setLayoutOrientation(JList.VERTICAL_WRAP);
		listaAllergeni.setVisibleRowCount(-1);
		contentPane.add(listaAllergeni);
		
		/* Campi esclusivi dei cibi */
		DefaultComboBoxModel<Cibo.Portata> comboModelPortata = new DefaultComboBoxModel<Cibo.Portata>();
		for(Cibo.Portata item:Cibo.Portata.values())
			comboModelPortata.addElement(item);
		
		JComboBox comboPortata = new JComboBox(comboModelPortata);
		comboPortata.setBounds(300, 118, 87, 22);
		contentPane.add(comboPortata);
		
		DefaultComboBoxModel<Cibo.Cottura> comboModelCottura = new DefaultComboBoxModel<Cibo.Cottura>();
		for(Cibo.Cottura item:Cibo.Cottura.values())
			comboModelCottura.addElement(item);
		
		JComboBox comboCottura = new JComboBox(comboModelCottura);
		comboCottura.setBounds(300, 155, 87, 22);
		contentPane.add(comboCottura);
		
		/* Campi esclusivi delle bevande */
		JCheckBox chckbxAlcolico = new JCheckBox("Alcolico");
		chckbxAlcolico.setBounds(444, 116, 97, 23);
		contentPane.add(chckbxAlcolico);
		
		DefaultComboBoxModel<Bevanda.Tipo> comboModelTipoBevanda = new DefaultComboBoxModel<Bevanda.Tipo>();
		for(Bevanda.Tipo item: Bevanda.Tipo.values())
			comboModelTipoBevanda.addElement(item);
		
		JComboBox comboTipoBevanda = new JComboBox(comboModelTipoBevanda);
		comboTipoBevanda.setBounds(448, 155, 87, 22);
		contentPane.add(comboTipoBevanda);
		
		/* Disattivazione dei campi esclusivi */
		if (!cibiBevandeFlag) {
			comboPortata.setEnabled(false);
			comboCottura.setEnabled(false);
		} else {
			chckbxAlcolico.setEnabled(false);
			comboTipoBevanda.setEnabled(false);
		}
		
		JButton btnAggiungiAlimento = new JButton("Aggiungi");
		btnAggiungiAlimento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(cibiBevandeFlag) {
					Cibo cibo = new Cibo();
					cibo.setNome(nomeAlimento.getText());
					cibo.setPrezzo(Float.parseFloat(prezzoAlimento.getText()));
					cibo.setVegano(chckbxVegan.isSelected());	
					cibo.setVegetariano(chckbxVegetariano.isSelected());
					cibo.setElencoAllergeni(cibo.getAllergeniFromStrings(listaAllergeni.getSelectedValuesList()));
					cibo.setTipoPortata((Cibo.Portata) comboPortata.getSelectedItem());
					cibo.setTipoCottura((Cibo.Cottura) comboCottura.getSelectedItem());
					JDialog dialogConferma= new TestDialog("alimento", cibo);
					dialogConferma.setVisible(true);
				} else {
					Bevanda bevanda = new Bevanda();
					bevanda.setNome(nomeAlimento.getText());
					bevanda.setPrezzo(Float.parseFloat(prezzoAlimento.getText()));
					bevanda.setVegano(chckbxVegan.isSelected());	
					bevanda.setVegetariano(chckbxVegetariano.isSelected());
					bevanda.setElencoAllergeni(bevanda.getAllergeniFromStrings(listaAllergeni.getSelectedValuesList()));
					bevanda.setAlcolico(chckbxAlcolico.isSelected());
					bevanda.setTipo((Bevanda.Tipo) comboTipoBevanda.getSelectedItem());
					JDialog dialogConferma= new TestDialog("alimento", bevanda);
					dialogConferma.setVisible(true);
				}
			}
		});
		btnAggiungiAlimento.setBounds(196, 321, 383, 29);
		contentPane.add(btnAggiungiAlimento);
		
		JButton btnCancella = new JButton("Cancella");
		btnCancella.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nomeAlimento.setText(null);
				prezzoAlimento.setText(null);
				chckbxVegan.setSelected(false);
				chckbxVegetariano.setSelected(false);
				listaAllergeni.clearSelection();
			}
		});
		btnCancella.setBounds(5, 321, 181, 29);
		contentPane.add(btnCancella);
	}
}
