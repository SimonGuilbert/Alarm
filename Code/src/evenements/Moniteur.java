package evenements;

import java.util.ArrayList;

public class Moniteur implements IncendieListener, GazListener, RadiationListener{
	
	protected String nom; 
	public ArrayList<AnomalieEvent> evenements = new ArrayList<AnomalieEvent>();

	public Moniteur(String unNom) {
		this.nom = unNom;
	}

	public String getType() {
		return this.nom.substring(0, 1);
	}
	
	@Override
	public void declenchementAlarmeIncendie(IncendieEvent incendie) {
		this.evenements.add(incendie);
//		System.out.println("ALERTE INCENDIE");
//		System.out.println("Date : " + incendie.getDate());
//		System.out.println("Localisation : " + incendie.getLocalisation());
//	    System.out.println("Niveau d'importance : " + incendie.getNivImportance()+"\n");
	}
	
	@Override
	public void declenchementAlarmeGaz(GazEvent gaz) {
		this.evenements.add(gaz);
//		System.out.println("ALERTE GAZ");
//		System.out.println("Date : " + gaz.getDate());
//		System.out.println("Localisation : " + gaz.getLocalisation());
//	    System.out.println("Niveau d'importance : " + gaz.getNivImportance());
//	    System.out.println("Type : "+gaz.getTypeGaz()+"\n");
	}
	
	@Override
	public void declenchementAlarmeRadiation(RadiationEvent radiation) {
		this.evenements.add(radiation);
//		System.out.println("ALERTE RADIATION");
//		System.out.println("Date : " + radiation.getDate());
//		System.out.println("Localisation : " + radiation.getLocalisation());
//	    System.out.println("Niveau d'importance : " + radiation.getNivImportance());
//	    System.out.println("Niveau de radiation : " + radiation.getNiveau()+"\n");
	}

}
