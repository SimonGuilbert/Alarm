package exceptions;

import javax.swing.JOptionPane;

public class TypesDifferentsException extends Exception{
	
	private String lieu;
	private String type;

	public TypesDifferentsException(String lieu,String type) {
		this.lieu = lieu;
		this.type = type;
	}
	
	public String getLieu() {
		return lieu;
	}

	public String getType() {
		if (this.type.equals("Incendie"))
			return "un incendie";
		else if (this.type.equals("Gaz"))
			return "une fuite de gaz";
		else
			return "un accident nucléaire";
	}

	public void info() {
		JOptionPane.showMessageDialog(null, "L'anomalie a été simulée avec succès mais elle n'aura aucun effet puisqu'il "
				+ "n'existe aucun\ncapteur capable de détecter "+getType()+" dans le lieu : "+getLieu()+".",
				"Aucun effet",JOptionPane.INFORMATION_MESSAGE);
	}

	

}
