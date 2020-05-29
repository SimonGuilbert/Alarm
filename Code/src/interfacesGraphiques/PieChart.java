package interfacesGraphiques;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;

import evenements.AnomalieEvent;
import evenements.Moniteur;

public class PieChart extends JComponent {
	
	private Moniteur moniteur;
	private Section[] sections;

	public PieChart(Moniteur moniteur) {
		this.moniteur = moniteur;
		this.sections = new Section[] { new Section(recherche("1"),Color.GREEN),
				   new Section(recherche("2"),Color.ORANGE),
				   new Section(recherche("3"),Color.RED) };
		
	}
	
	public void paint(Graphics g) {
	    drawPie((Graphics2D) g, getBounds(), sections);
	}
	
	void drawPie(Graphics2D g, Rectangle area, Section[] sect) {
		double total = 0.0D;
	    for (int i = 0; i < sect.length; i++) {
	      total += sect[i].valeur;
	    }

	    double curValue = 0.0D;
	    int startAngle = 0;
	    for (int i = 0; i < sect.length; i++) {
	      startAngle = (int) (curValue * 360 / total);
	      int arcAngle = (int) (sect[i].valeur * 360 / total);

	      g.setColor(sect[i].couleur);
	      g.fillArc(area.x, area.y, area.width, area.height, startAngle, arcAngle);
	      curValue += sect[i].valeur;
	    }
  }
	
	private int recherche(String n) {
		int res = 0;
		for (AnomalieEvent ae : this.moniteur.evenements) {
			if (ae.getNivImportance().equals(n))
				res += 1;
		}
		return res;
	}

	
	
}
