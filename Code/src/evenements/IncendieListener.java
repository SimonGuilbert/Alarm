package evenements;

import java.util.EventListener;

public interface IncendieListener extends EventListener {

	public abstract void alarmeIncendieDeclanchee(IncendieEvent incendie);

}
