package com.tnv.mypackage;

import java.util.ArrayList;

public class MenuFisso {
	
	String nomeMenu;
	float scontoMenu;
	ArrayList<Alimento> elencoPiattiMenu;

	public MenuFisso(String nomeMenu, float scontoMenu, ArrayList<Alimento> elencoPiattiMenu) {
		super();
		this.nomeMenu = nomeMenu;
		this.scontoMenu = scontoMenu;
		this.elencoPiattiMenu = elencoPiattiMenu;
	}

	public String getNomeMenu() {
		return nomeMenu;
	}

	public void setNomeMenu(String nomeMenu) {
		this.nomeMenu = nomeMenu;
	}

	public float getScontoMenu() {
		return scontoMenu;
	}

	public void setScontoMenu(float scontoMenu) {
		this.scontoMenu = scontoMenu;
	}

	public ArrayList<Alimento> getElencoPiattiMenu() {
		return elencoPiattiMenu;
	}

	public void setElencoPiattiMenu(ArrayList<Alimento> elencoPiattiMenu) {
		this.elencoPiattiMenu = elencoPiattiMenu;
	}
	
	/**
	 * Questo metodo calcola il prezzo del menu fisso data la lista di alimenti in esso presente
	 * @param menu passiamo il menù fisso con i rispettivi prezzi dei singoli cibi e bevande
	 * @return la somma dei prezzi dei singoli alimenti
	 */
	float prezzoTotaleMenu(MenuFisso menu) {
		
		float prezzoTotale = 0;
		
		for(Alimento item : elencoPiattiMenu) {
			prezzoTotale += item.getPrezzo();
		}
		return prezzoTotale;
	}
	
	/**
	 * Questo metodo calcola il prezzo del menu fisso dopo l'applicazione dello sconto
	 * @param menu menu fisso
	 * @return prezzo del menù fisso scontato
	 */
	float prezzoScontatoMenu(MenuFisso menu) {
		
		float prezzoTotale = prezzoTotaleMenu(menu);
	
		return prezzoTotale * (1 - scontoMenu);
	}

	// Metodo che stampa il menù
	static void stampaMenuFisso(MenuFisso menu) {

		System.out.println("Nome menù: " + menu.nomeMenu);
		System.out.println("Lista piatti e bevande: " );
		stampaNomidelMenu(menu);
		System.out.printf("Prezzo del menù: %.2f€\n", menu.prezzoTotaleMenu(menu));
		System.out.printf("Prezzo scontato: %.2f€\n", menu.prezzoScontatoMenu(menu));
		System.out.printf("Sconto del %.2f%%", menu.scontoMenu*100);
	}
	
	private static void stampaNomidelMenu(MenuFisso menu) {
		for(Alimento item: menu.elencoPiattiMenu) {
			System.out.println("- " + item.getNome());
		}
	}

}
