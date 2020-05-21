package evenements;

import java.util.GregorianCalendar;
import java.util.Hashtable;

import exceptions.ValeurIncorrecteException;

public class RadiationEvent extends AnomalieEvent{
	
	private int niveau; // Entre 1 et 100

	public RadiationEvent(GregorianCalendar date, String localisation, int nivImportance, int niveau, Capteur source) {
		super(date, localisation, nivImportance, source);
			setNiveau(niveau);
	}

	public String getNiveau() {
		return Integer.toString(niveau);
	}
	
	public void setNiveau(int valeur) {
		if (valeur < 1 || valeur > 100) {
			throw new ValeurIncorrecteException();
		} else {
			this.niveau = valeur;
		}
	}

	@Override
	public Hashtable<String, String> stockInfos() {
		Hashtable<String, String> res = new Hashtable<String, String>();
		res.put("Date : ", this.getDate());
		res.put("Localisation : ",this.getLocalisation());
		res.put("Niveau d'importance : ", this.getNivImportance());
		res.put("Niveau de radiation : ", this.getNiveau());
		return res;
	}

	@Override
	public String getType() {
		return "Accident nucléaire";
	}
	
}
