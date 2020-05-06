package evenements;

import java.util.GregorianCalendar;
import java.util.Hashtable;

public class IncendieEvent extends AnomalieEvent{

	public IncendieEvent(GregorianCalendar date, String localisation, int nivImportance) {
		super(date, localisation, nivImportance);
	}

	@Override
	public Hashtable<String, String> stockInfos() {
		Hashtable<String, String> res = new Hashtable<String, String>();
		res.put("Date : ", this.getDate());
		res.put("Localisation : ",this.getLocalisation());
		res.put("Niveau d'importance : ", this.getNivImportance());
		return res;
	}
	
}
