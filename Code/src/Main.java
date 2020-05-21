import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import interfacesGraphiques.Fenetre;

public class Main {
	
	public static void main(String[] args) throws Exception{
		
		darkMode();
		
		new Fenetre();
	}
	
	public static void darkMode() throws Exception{
		
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		
		UIManager.put( "nimbusBase", new Color( 0, 0, 0) ); // Fond boutons
		UIManager.put( "nimbusFocus", new Color(115,164,209) ); // Contour zone de texte - liste
		//UIManager.put( "nimbusInfoBlue", new Color( 66, 139, 221) );
		UIManager.put( "nimbusLightBackground", new Color( 18, 30, 49) ); // Remplissage zone de texte - liste
		UIManager.put( "nimbusSelectedText", new Color( 255, 255, 255) ); // Texte sélectionné
		//UIManager.put( "nimbusSelectionBackground", new Color( 104, 93, 156) );
	    UIManager.put( "text", new Color( 230, 230, 230) ); // Couleur du texte
	    UIManager.put( "control", new Color( 65, 65, 65) ); // Fond JPanel
	    UIManager.put( "info", new Color(65,65,65) ); // Tooltip boutons Simuler
	    UIManager.put( "nimbusDisabledText", new Color( 121, 128, 129) ); // Texte boutons désactivés
	}
	
}
