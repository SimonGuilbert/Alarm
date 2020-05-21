package evenements;

import javax.swing.event.EventListenerList;

public abstract class Capteur {
	
	public EventListenerList listeners = new EventListenerList();
	
	protected String localisation;

	public Capteur(String localisation) {
		this.localisation = localisation;
	}
	
	public String getLocalisation() {
		return localisation;
	}
	
	public void viderListeners() {
		this.listeners = new EventListenerList();
	}
	
	public abstract String getTypeCapteur();
	
	
}
