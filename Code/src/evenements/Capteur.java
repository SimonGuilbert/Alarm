package evenements;

import java.util.ArrayList;

public abstract class Capteur {
	
	ArrayList<IncendieListener> incendieListeners = new ArrayList<IncendieListener>();
	
	protected String localisation;

	public Capteur(String localisation) {
		this.localisation = localisation;
	}

	public String getLocalisation() {
		return localisation;
	}

}
