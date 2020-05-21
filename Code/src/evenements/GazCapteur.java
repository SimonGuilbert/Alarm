package evenements;

import java.util.GregorianCalendar;

public class GazCapteur extends Capteur {
		
	public GazCapteur(String localisation) {
		super(localisation);
	}
	
	public void simulerFuiteGaz(int nivImportance, String typeGaz) {
		GazEvent gaz = new GazEvent(new GregorianCalendar(),this.localisation,nivImportance,typeGaz,this);
		for (GazListener gl : this.listeners.getListeners(GazListener.class)) {
			gl.declenchementAlarmeGaz(gaz);
		}			
	}
	
	public void addListener(GazListener listener) {
        this.listeners.add(GazListener.class, listener);
    }
	
	@Override
	public String getTypeCapteur() {
		return "Gaz";
	}


}
