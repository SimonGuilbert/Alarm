package evenements;

public class Moniteur implements IncendieListener{
	
	protected String type; // A ou B

	public Moniteur(String unType) {
		this.type = unType;
	}

	public String getType() {
		return this.type;
	}
	
	@Override
	public void alarmeIncendieDeclanchee(IncendieEvent incendie) {
		System.out.println("ALERTE INCENDIE");
		System.out.println("Date : " + incendie.getDate());
		System.out.println("Localisation : " + incendie.getLocalisation());
	    System.out.println("Niveau d'importance : " + incendie.getNivImportance());
	        
	}

}
