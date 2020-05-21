package evenements;

import java.util.GregorianCalendar;

public class RadiationCapteur extends Capteur {
		
	public RadiationCapteur(String localisation) {
		super(localisation);
	}
	
	public void simulerAccidentNucleaire(int nivImportance, int nivRadiation) {
		RadiationEvent rad = new RadiationEvent(new GregorianCalendar(),this.localisation,nivImportance,nivRadiation,this);
		for (RadiationListener rl : this.listeners.getListeners(RadiationListener.class)) {
			rl.declenchementAlarmeRadiation(rad);
		}			
	}
	
	public void addListener(RadiationListener listener) {
        this.listeners.add(RadiationListener.class, listener);
    }
	
	@Override
	public String getTypeCapteur() {
		return "Radiation";
	}


}
