package exceptions;

import javax.swing.JOptionPane;

public class ListeDeroulanteVideException extends NullPointerException{

	public ListeDeroulanteVideException() {
	}
	
	public void warning() {
		JOptionPane.showMessageDialog(null, "Vous devez créer au moins un capteur avant de simuler une anomalie.",
				"Capteur manquant",JOptionPane.WARNING_MESSAGE);
	}
	

}
