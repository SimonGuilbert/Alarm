import evenements.IncendieCapteur;
import evenements.Moniteur;

public class Main {

	public static void main(String[] args) {
		
		Moniteur caserne = new Moniteur("A");
		
		IncendieCapteur ic = new IncendieCapteur("Tour Eiffel 1er �tage");
		
		ic.addIncendieListener(caserne);
		ic.simulerIncendie(2);
		

	}

}
