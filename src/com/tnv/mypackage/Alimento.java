package com.tnv.mypackage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class Alimento {

	protected enum Allergene{
		ARACHIDI_E_DERIVATI ("Arachidi e derivati"),
		CROSTACEI ("Crostacei"),
		FRUTTA_A_GUSCIO ("Frutta a guscio"),
		GLUTINE ("Glutine"),
		LATTE_E_DERIVATI ("Latte e derivati"),
		LUPINI ("Lupini"),
		MOLLUSCHI ("Molluschi"),
		SENAPE ("Senape"),
		PESCE ("Pesce"),
		SEDANO ("Sedano"),
		SESAMO ("Sesamo"),
		ANIDRIDE_SOLFOROSA_E_SOLFITI ("Anidride solforosa e solfiti"),
		SOIA ("Soia"),
		UOVA_E_DERIVATI ("Uova e derivati");
		
		private String nomeAllergene;
		private int peso;

		public static Allergene ottieniAllergene(int i) { 
			return Allergene.values()[i];
	    }
		
		private Allergene(String nomeAllergene) {
			this.setNomeAllergene(nomeAllergene);
		}

		public String getNomeAllergene() {
			return nomeAllergene;
		}

		public void setNomeAllergene(String nomeAllergene) {
			this.nomeAllergene = nomeAllergene;
		}
		
	}
	
	private Allergene allergene; //Valori dell'enumerazione
	private Float prezzo; //Prezzo del piatto
	protected String nome; //Nome del piatto
	private Boolean vegano; //True se il piatto � adatto ai vegani
	private Boolean vegetariano; //True se il piatto � adatto ai vegetariani
	private ArrayList<Allergene> elencoAllergeni = new ArrayList<Allergene>(); //Elenco degli allergeni dell'alimento
	
	public Alimento() {
		super();
	}

	public Alimento(Float prezzo, String nome, Boolean vegano, Boolean vegetariano,
			ArrayList<Allergene> elencoAllergeni) {
		super();
		this.prezzo = prezzo;
		this.nome = nome;
		this.vegano = vegano;
		this.vegetariano = vegetariano;
		this.elencoAllergeni = elencoAllergeni;
	}
	
	public Allergene getAllergene() {
		return allergene;
	}

	public void setAllergene(Allergene allergene) {
		this.allergene = allergene;
	}

	protected Float getPrezzo() {
		return prezzo;
	}

	protected String getNome() {
		return nome;
	}

	public Boolean getVegano() {
		return vegano;
	}

	public Boolean getVegetariano() {
		return vegetariano;
	}

	public ArrayList<Allergene> getElencoAllergeni() {
		return elencoAllergeni;
	}

	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setVegano(Boolean vegano) {
		this.vegano = vegano;
	}

	public void setVegetariano(Boolean vegetariano) {
		this.vegetariano = vegetariano;
	}

	void setElencoAllergeni(ArrayList<Allergene> elencoAllergeni) {
		this.elencoAllergeni = elencoAllergeni;
	}
	
	/**
	 * Questo metodo permette di creare la lista di allergeni presenti in quel determintato tipo di alimento
	 * @param allergenes � un varargs che ci permette di passare un numero variabile di allergeni
	 * @return un vettore di allergeni
	 */
	ArrayList<Allergene> assegnaAllergeni(Allergene...allergenes){
		
		ArrayList<Allergene> elencoAllergeni = new ArrayList<Alimento.Allergene>();
		
		for (Allergene allergene : allergenes)  {
		elencoAllergeni.add(allergene);}
		
		return elencoAllergeni;
	}
	
	public ArrayList<Allergene> getAllergeniFromStrings(List<String>list) {
		ArrayList<Allergene> elenco = new ArrayList<Allergene>();
		for(int i = 0; i < list.size(); i++)
			for(Allergene item: Allergene.values()){
				if(item.getNomeAllergene() == list.get(i))
					elenco.add(item);
			}
		
		return elenco;
	}
	
	public static int getAlimentoFromString(String nomeAlimento, boolean cibiBevandeFlag) {
		for(int i = 0; i < Menu.elenco.size(); i++)
			if(nomeAlimento.equals(Menu.elenco.get(i).getNome()))
				if((cibiBevandeFlag && Menu.elenco.get(i) instanceof Cibo) || (!cibiBevandeFlag && Menu.elenco.get(i) instanceof Bevanda))
					return i;
		
		return -1;
	}
	
	//stampa dati alimento a video
	 public void stampaInfo() {
		System.out.println("---------------------------------------------------------");
		System.out.println("           Nome alimento: " + getNome());
		System.out.println("                  Prezzo: " + getPrezzo()+"€");
		System.out.println("         Lista allergeni: " + getElencoAllergeni());
		System.out.println("     Alimento per vegani: " + (getVegano()? "Si":"No"));
		System.out.println("Alimento per vegetariani: " + (getVegetariano()? "Si":"No"));
	}
	
	// Stampa a video l'elenco degli alimenti contenuti nel vettore alimenti
	static void stampaElencoAlimenti(ArrayList<Alimento> lista) {
			for(Alimento item: lista) {
				item.stampaInfo();
			}
		}
		
	// Genera lista casuale di allergeni
	static ArrayList<Allergene> generaAllergeniCasuali(){
			
			ArrayList<Allergene> lista = new ArrayList<Alimento.Allergene>();
			Random random = new Random();
			int i = 0;
			int randNLista = random.nextInt(Allergene.values().length/4);
			
			if(randNLista > 0) 
				while(i<randNLista) {
					lista.add(Allergene.values()[(random.nextInt(Allergene.values().length))]);			
					++i;
				}
	
			return lista;
		}
		
	// Genera alimento casuale
//	static Alimento generaAlimentoCasuale() {
//			Alimento a = new Alimento();
//			Random random = new Random();
//			
//		    String[] antipasti = {"Salumi", "Formaggi", "Crostini", "Suppl�", "Olive ascolane"};
//		    Float[]  prezzi = {7.0f, 4.5f, 8.0f, 3.5f, 9.0f};
//
//		    a.nome = antipasti[random.nextInt(antipasti.length)];
//		    a.prezzo = prezzi[random.nextInt(prezzi.length)];
//		    a.vegano = random.nextBoolean();
//		    a.vegetariano = random.nextBoolean();
//		    a.elencoAllergeni = generaAllergeniCasuali();
//		    return a;
//		}
		
//	//implementare una funzione static che permetta la scrittura su file
//	static void salvaAlimentoSuFile(Alimento a){
////		if (a instanceof Cibo)
////			Cibo.salvaAlimentoSuFile(a);
////		else
////			Bevanda.salvaAlimentoSuFile(a);
//			String path = "listaAlimenti.txt";
//			try {
//				File file = new File(path);
//				FileWriter fw = new FileWriter(file, true);
//				fw.write("Nome:                     " + a.getNome() + "\n");
//				fw.write("Prezzo:                   " + a.getPrezzo() +"€" + "\n");
//				fw.write("Elenco allergeni:         " + a.getElencoAllergeni() + "\n");
//				fw.write("Alimento per vegani:      " + a.getVegano() + "\n");
//				fw.write("Alimento per vegetariani: " + a.getVegetariano() + "\n");
//				fw.flush();
//				fw.close();
//			} catch(IOException e) {
//				e.printStackTrace();
//			}
//	}
//	
	abstract void salvaAlimentoSuFile();
	
	static void salvaTuttoSuFile(ArrayList<Alimento> alimenti) {
		for(Alimento item: alimenti)
			item.salvaAlimentoSuFile();
	}

	/**
	 * Questo metodo genera casualmente un array di 20 alimenti (10 cibi e 10 bevende) 
	 * tutti nominalmente diversi tra loro e poi li mischia
	 * @return un arrayList non ordinata di alimenti unici 
	 */
	static ArrayList<Alimento> generaMenu(){
		//Creare un vettore di alimenti 
		ArrayList<Alimento> menu = new ArrayList<Alimento>();
		int i;
		
		//Aggiunta casuale di 10 cibi all'array di alimenti
		menu.add(Cibo.generaCiboCasuale());
		for(i=1; i<10; ++i) {
			boolean flagRipetizione;
			Cibo c;
			do{
				flagRipetizione = false;
				c = Cibo.generaCiboCasuale();
					
				for(int j=0;j<i && flagRipetizione == false; ++j) {
					if(menu.get(j).getNome().compareTo(c.getNome())==0)
						flagRipetizione = true;
				}	
			}while(flagRipetizione); 
					
			menu.add(c);
		}
				
		//Aggiunta casuale di 10 bevande all'array di alimenti
		menu.add(Bevanda.generaBevandaCasuale());
		for(i=11 ; i<20; ++i) {
			boolean flagRipetizione;
			Bevanda b;
			do{
				flagRipetizione = false;
				b = Bevanda.generaBevandaCasuale();
					
				for(int j=10;j<i && flagRipetizione == false; ++j) {
					if(menu.get(j).getNome().compareTo(b.getNome())==0)
						flagRipetizione = true;
				}	
			}while(flagRipetizione); 
					
			menu.add(b);
		}
				
		//Mischio tra loro i cibi e le bevande presenti del vettore alimenti
		Collections.shuffle(menu);


		return menu;
	}
}
		

