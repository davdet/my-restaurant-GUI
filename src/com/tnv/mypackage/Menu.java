package com.tnv.mypackage;

import java.util.ArrayList;

public class Menu {

	public static ArrayList<Alimento> elenco = new ArrayList<Alimento>();
	
	public static void aggiungiAlimento(Alimento a) {
		elenco.add(a);
		myGUI.update();
	}
	
	public static void modificaAlimento(Alimento a, int index) {
		elenco.set(index, a);
		myGUI.update();
	}
	
	public static void eliminaAlimento(int index) {
		elenco.remove(index);
		myGUI.update();
	}
	
	public static void salvaSuFile(ArrayList<Alimento> alimenti) {
		String path = "ordine.txt";
		
		for(Alimento item: alimenti) {
			
		}
	}
	
	public static int getNumeroBevande() {
		int counter=0;
		for(Alimento item:elenco) {
			if(item instanceof Bevanda)
				counter++;
		}
		return counter;
	}
	
	public static int getNumeroCibi() {
		int counter=0;
		for(Alimento item:elenco) {
			if(item instanceof Cibo)
				counter++;
		}
		return counter;
	}
	
	public static int getNumeroAlimenti() {
		return elenco.size();
	}

}
