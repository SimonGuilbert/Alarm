package interfacesGraphiques;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FenetreDroite extends JFrame{

	public FenetreDroite(JPanel contenu) {
		super("Gestion alarmes"); // Titre de la fenêtre
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int largeur = 375;
		int hauteur = 450;
		
		this.setSize(largeur,hauteur);
		this.setResizable(false);
		this.setLocation((int) (screenSize.getWidth()/5)*4-largeur, (int) screenSize.getHeight()/2-hauteur/2);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
      JPanel contentPane = (JPanel) getContentPane();
      contentPane.setBorder(BorderFactory.createLineBorder(new Color( 18, 30, 49)));
      
      contentPane.add(contenu);
      
      this.setVisible(true);
	}
	

}
