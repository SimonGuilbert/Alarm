package interfacesGraphiques;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FenetreGauche extends JFrame{

	public FenetreGauche(JPanel contenu) {
		super("Simulation alarmes"); // Titre de la fenêtre
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int largeur = 303;
		int hauteur = 465;
		
		this.setSize(largeur,hauteur);
		this.setResizable(false);
        this.setLocation((int) screenSize.getWidth()/5, (int) screenSize.getHeight()/2-hauteur/2);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
        
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setBorder(BorderFactory.createLineBorder(new Color( 18, 30, 49)));
      
        contentPane.add(contenu);
      
        this.setVisible(true);
	}


	

}
