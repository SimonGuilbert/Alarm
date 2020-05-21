package evenements;

import java.util.GregorianCalendar;
import java.util.Hashtable;

public class GazEvent extends AnomalieEvent{
	
	private String typeGaz; // Hydrogène, Hélium, CO2, ...

	public GazEvent(GregorianCalendar date, String localisation, int nivImportance, String typeGaz, Capteur source) {
		super(date, localisation, nivImportance, source);
		this.typeGaz = typeGaz;
	}

	public String getTypeGaz() {
		return typeGaz;
	}
	
	@Override
	public String getType() {
		return "Fuite de gaz";
	}

	@Override
	public Hashtable<String, String> stockInfos() {
		Hashtable<String, String> res = new Hashtable<String, String>();
		res.put("Date : ", this.getDate());
		res.put("Localisation : ",this.getLocalisation());
		res.put("Niveau d'importance : ", this.getNivImportance());
		res.put("Type : ", this.getTypeGaz());
		return res;
	}
	
}