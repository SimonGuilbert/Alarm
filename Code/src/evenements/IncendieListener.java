package evenements;

public interface IncendieListener extends AnomalieListener {

	public abstract void declenchementAlarmeIncendie(IncendieEvent incendie);

}
