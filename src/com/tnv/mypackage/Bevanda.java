package com.tnv.mypackage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Bevanda extends Alimento{

	public enum Tipo{
		VINO("Vino"),
		BIRRA("Birra"),
		BIBITA("Bibita"),
		AMARO("Amaro"),
		CAFFETTERIA("Caffetteria");
		
		private String nomeTipoBevanda;
		
		private Tipo(String nomeTipoBevanda) {
			this.nomeTipoBevanda = nomeTipoBevanda;
		}

		public String getNomeTipoBevanda() {
			return nomeTipoBevanda;
		}

		public void setNomeTipoBevanda(String nomeTipoBevanda) {
			this.nomeTipoBevanda = nomeTipoBevanda;
		}
	}
	
	Boolean alcolico; //Determina se una bibita Ã¨ alcolica
	Tipo tipo; //Rappresenta la bevanda scelta
	
	public Bevanda() {
		
	}
	
	public Bevanda(Float prezzo, String nome, Boolean vegano, Boolean vegetariano,
			ArrayList<Allergene> elencoAllergeni, Boolean alcolico, Tipo tipo) {
		super(prezzo, nome, vegano, vegetariano, elencoAllergeni);
		this.alcolico = alcolico;
		this.tipo = tipo;
	}

	public Boolean getAlcolico() {
		return alcolico;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setAlcolico(Boolean alcolico) {
		this.alcolico = alcolico;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	
	//stampa a video una bevanda

	public void stampaInfo() {
		super.stampaInfo();
		System.out.println("         Tipo di bevanda: " + getTipo());
		if(getAlcolico()==true) {
		System.out.println("                Alcolica: si");}
		else {
		System.out.println("                Alcolica: no");}
		
	}
	
	// Stampa a video l'elenco delle bevande 
	static void stampaElencoBevande(ArrayList<Bevanda> lista) {
		for(Bevanda item: lista) {
			item.stampaInfo();
		}
	}
	
	// Genera bevanda casuale
	static Bevanda generaBevandaCasuale() {
		Bevanda b = new Bevanda();
		Random random = new Random();
		
	    String[] bevande = {"Coca Cola Zero", "Fanta", "Birra [0.20l]", "Calice di Vino Rosso", "Birra [0.40l]", "Acqua", "Calice di Vino Bianco", "Mirto", "Grappa", "Caffè espresso" };
	    Float[]  prezzi = {4.0f, 4.5f, 3.0f, 3.5f, 6.0f, 1.0f, 2.5f, 5.0f, 5.5f};

	    b.setNome(bevande[random.nextInt(bevande.length)]);
	    b.setPrezzo(prezzi[random.nextInt(prezzi.length)]);
	    b.setAlcolico(random.nextBoolean());
	    b.setTipo(Tipo.values()[(random.nextInt(Tipo.values().length))]);
	    b.setVegano(random.nextBoolean());
	    b.setVegetariano(random.nextBoolean());
	    b.setElencoAllergeni(generaAllergeniCasuali());
	    
	    return b;
	}
	
	public void salvaAlimentoSuFile(){
		
		String path = "listaAlimenti.txt";
		try {
			File file = new File(path);
			FileWriter fw = new FileWriter(file, true);
			fw.write("[BEV]");
			fw.write("[NO]" + getNome() + "[/NO]");
			fw.write("[PR]" + getPrezzo() + "[/PR]");
			fw.write("[TI]" + getTipo() + "[/TI]");
			fw.write("[AK]" + getAlcolico() + "[/AK]");
			fw.write("[AL]" + getElencoAllergeni() + "[/AL]");
			fw.write("[VG]" + getVegano() + "[/VG]");
			fw.write("[VT]" + getVegetariano() + "[/VT]");
			fw.write("[/BEV]\n");
			fw.flush();
			fw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
