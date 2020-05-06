package evenements;

import java.util.GregorianCalendar;

public class IncendieCapteur extends Capteur {

	public IncendieCapteur(String localisation) {
		super(localisation);
	}
	
	public void addIncendieListener(IncendieListener incListener) {
		this.incendieListeners.add(incListener);
	}
	
	public void simulerIncendie(int nivImportance) {
		IncendieEvent incendie = new IncendieEvent(new GregorianCalendar(),this.localisation,nivImportance);
		for (IncendieListener il : this.incendieListeners) {
			il.alarmeIncendieDeclanchee(incendie);
		}			
	}

}
