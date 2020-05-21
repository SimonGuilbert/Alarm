package exceptions;

import javax.swing.JOptionPane;

public class ValeurIncorrecteException extends RuntimeException{

	public ValeurIncorrecteException() {
	}
	
	public void affErreur() {
		JOptionPane.showMessageDialog(null, "Le niveau de radiation doit être compris entre 1 et 100 !", 
				"Erreur", JOptionPane.ERROR_MESSAGE);
	}
	

}
