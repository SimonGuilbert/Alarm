package interfacesGraphiques;

import java.awt.Color;

// ------------------------------------------------------------------------------------ //
// Cette classe permet de gérer les sections dans le graphique circulaire (Pie Chart)   //
// ------------------------------------------------------------------------------------ //

class Section {
	  double valeur;
	  Color couleur;

	  public Section(double valeur, Color couleur) {
	    this.valeur = valeur;
	    this.couleur = couleur;
	  }
	}
