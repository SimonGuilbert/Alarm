package evenements;

import java.util.GregorianCalendar;

public class IncendieCapteur extends Capteur {
	
	public IncendieCapteur(String localisation) {
		super(localisation);
	}
	
	public void simulerIncendie(int nivImportance) {
		IncendieEvent incendie = new IncendieEvent(new GregorianCalendar(),this.localisation,nivImportance,this);
		for (IncendieListener il : this.listeners.getListeners(IncendieListener.class)) {
			il.declenchementAlarmeIncendie(incendie);
		}			
	}

	public void addListener(IncendieListener listener) {
        this.listeners.add(IncendieListener.class, listener);
    }

	@Override
	public String getTypeCapteur() {
		return "Incendie";
	}




}
