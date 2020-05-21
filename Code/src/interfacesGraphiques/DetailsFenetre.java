package interfacesGraphiques;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import evenements.AnomalieEvent;

public class DetailsFenetre extends JFrame implements ActionListener {
	
	public DetailsFenetre(AnomalieEvent ae) {
		this.setTitle("Détails ");
	    this.setSize(241, 210);
	    this.setResizable(false);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    
	    // TITRE
	    JPanel panTitre = new JPanel();
	    JLabel titre = new JLabel("Détails sur l'anomalie");
	    titre.setFont(new Font("Tahoma",Font.BOLD,18));
	    panTitre.add(titre);
	    
	    // INFOS
	    JPanel panInfos = new JPanel();
	    panInfos.add(new JLabel("Type d'anomalie : "+ae.getType()));
	    panInfos.setLayout(new BoxLayout(panInfos, BoxLayout.Y_AXIS));
	    for (String cle : ae.stockInfos().keySet()) {
	    	panInfos.add(new JLabel(cle+" "+ae.stockInfos().get(cle)));
	    }
	    
	    // BOUTON FERMER
	    JPanel panBouton = new JPanel();
	    JButton fermer = new JButton("Fermer");
	    fermer.addActionListener(this);
	    panBouton.add(fermer);
	    
	    // CONTENU
	    JPanel contenu = new JPanel();
	    //contenu.setLayout(new BoxLayout(contenu, BoxLayout.Y_AXIS));
	    contenu.add(panTitre);
	    contenu.add(panInfos);
	    contenu.add(panBouton);
	    
	    this.getContentPane().add(contenu);
	    this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
		
	}

}
