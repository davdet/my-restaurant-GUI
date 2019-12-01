package com.tnv.mypackage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Cibo extends Alimento{
	
	public enum Portata{
		ANTIPASTO("Antipasto"),
		PRIMO("Primo"),
		SECONDO("Secondo"),
		CONTORNO("Contorno"),
		DESSERT("Dessert");
		
		private String nomePortata;
		
		private Portata(String nomePortata) {
			this.nomePortata = nomePortata;
		}
		
		public String getNomePortata() {
			return nomePortata;
		}

		public void setNomePortata(String nomePortata) {
			this.nomePortata = nomePortata;
		}
	}
	
	public enum Cottura{
		BASSA("Bassa"),
		MEDIA("Media"),
		ALTA("Alta");
		
		private String nomeCottura;
		
		private Cottura(String nomeCottura) {
			this.nomeCottura = nomeCottura;
		}

		public String getNomeCottura() {
			return nomeCottura;
		}

		public void setNomeCottura(String nomeCottura) {
			this.nomeCottura = nomeCottura;
		}
	}
	
	Portata tipoPortata; 
	Cottura tipoCottura;
	
	public Cibo() {
		
	}
	
	public Cibo(Float prezzo, String nome, Boolean vegano, Boolean vegetariano,
			ArrayList<Allergene> elencoAllergeni, Portata p) {
		super(prezzo, nome, vegano, vegetariano, elencoAllergeni);
		this.tipoPortata = p;
		this.tipoCottura = Cottura.MEDIA;
	}
	
	public Cibo(Float prezzo, String nome, Boolean vegano, Boolean vegetariano,
			ArrayList<Allergene> elencoAllergeni, Portata p, Cottura c) {
		super(prezzo, nome, vegano, vegetariano, elencoAllergeni);
		this.tipoPortata = p;
		this.tipoCottura = c;
	}

	public Portata getTipoPortata() {
		return tipoPortata;
	}

	public Cottura getTipoCottura() {
		return tipoCottura;
	}

	public void setTipoPortata(Portata tipoPortata) {
		this.tipoPortata = tipoPortata;
	}

	public void setTipoCottura(Cottura tipoCottura) {
		this.tipoCottura = tipoCottura;
	}

	//Stampa a video di un cibo
	public void stampaInfo() {
		super.stampaInfo();
		System.out.println("                 Portata: " + getTipoPortata());
		System.out.println("         Tipo di cottura: " + getTipoCottura());
	}
	
	// Stampa a video l'elenco del cibo 
	static void stampaElencoBevande(ArrayList<Cibo> lista) {
		for(Cibo item: lista) {
			item.stampaInfo();
		}
	}
	
	//predisporre una funzione static in cibo che restituisce un oggetto cibo generato casualmente
	static Cibo generaCiboCasuale() {
		Cibo c = new Cibo();
		Random random = new Random();
		
	    String[] cibi = {"Tagliere di salumi e formaggi", "Bruschette primavera", "Bistecca di cavallo", "Tagliata di manzo", "Carbonara", "Tonnarelli Cacio e Pepe", "Tartare di Tonno", "Orata al cartoccio", "Cheesecake", "Tiramisù"};
	    Float[]  prezzi = {10.0f, 16.0f, 15.0f, 5.0f, 4.5f, 6.5f, 4.0f, 7.0f, 9.5f};

	    c.setNome(cibi[random.nextInt(cibi.length)]);
	    c.setPrezzo(prezzi[random.nextInt(prezzi.length)]);
	    c.setTipoPortata(Portata.values()[(random.nextInt(Portata.values().length))]);
	    c.setTipoCottura(Cottura.values()[(random.nextInt(Cottura.values().length))]);
	    c.setVegano(random.nextBoolean());
	    c.setVegetariano(random.nextBoolean());
	    c.setElencoAllergeni(generaAllergeniCasuali());
	    
	    return c;
	}
	
	//implementare una funzione static che permetta la scrittura su file
	static void salvaCiboSuFile(Cibo c){
		
		String path = "listaCibi.txt";
		try {
			File file = new File(path);
			FileWriter fw = new FileWriter(file, true);
			fw.write("Nome:                    " + c.getNome() + "\n");
			fw.write("Prezzo:                  " + c.getPrezzo() +"€" + "\n");
			fw.write("Tipo di portata:         " + c.getTipoPortata() + "\n");
			fw.write("Tipo di cottura:         " + c.getTipoCottura() + "\n");
			fw.write("Elenco allergeni:        " + c.getElencoAllergeni() + "\n");
			fw.write("Bevanda per vegani:      " + c.getVegano() + "\n");
			fw.write("Bevanda per vegetariani: " + c.getVegetariano() + "\n");
			fw.flush();
			fw.close();
			}
			catch(IOException e) {
				e.printStackTrace();
				}
	}
	
}

