package evenements;

public interface GazListener extends AnomalieListener {

	public abstract void declenchementAlarmeGaz(GazEvent gaz);

}