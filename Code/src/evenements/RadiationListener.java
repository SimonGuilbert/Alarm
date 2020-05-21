package evenements;

public interface RadiationListener extends AnomalieListener {

	public abstract void declenchementAlarmeRadiation(RadiationEvent radiation);

}
