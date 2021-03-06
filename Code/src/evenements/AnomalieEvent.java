package evenements;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.EventObject;
import java.util.GregorianCalendar;
import java.util.Hashtable;

public abstract class AnomalieEvent extends EventObject{
	
	private GregorianCalendar date;
	private String localisation;
	private int nivImportance; // 1, 2 ou 3

	public AnomalieEvent(GregorianCalendar date, String localisation, int nivImportance, Capteur source) {
		super(source);
		this.date = date;
		this.localisation = localisation;
		this.nivImportance = nivImportance;
	}
	
	// METHODES ABSTRAITES

	public abstract Hashtable<String,String> stockInfos();
	
	public abstract String getType();
	
	// GETTERS ET SETTERS
	
	public String getDate() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return df.format(date.getTime());
	}

	public void setDate(GregorianCalendar date) {
		this.date = date;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public String getNivImportance() {
		return Integer.toString(nivImportance);
	}

	public void setNivImportance(int nivImportance) {
		this.nivImportance = nivImportance;
	}

}
