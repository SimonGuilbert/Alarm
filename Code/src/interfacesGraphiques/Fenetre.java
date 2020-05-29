package interfacesGraphiques;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import evenements.AnomalieEvent;
import evenements.Capteur;
import evenements.GazCapteur;
import evenements.IncendieCapteur;
import evenements.Moniteur;
import evenements.RadiationCapteur;
import exceptions.ListeDeroulanteVideException;
import exceptions.TypesDifferentsException;
import exceptions.ValeurIncorrecteException;

public class Fenetre extends JFrame {
	
	private FenetreGauche fenetreGauche;
	private FenetreDroite fenetreDroite;
	
	private int cleMax = 0; // Clé max du dicoCapteurs 
	private Moniteur moniteurA = new Moniteur("A : Caserne de pompiers");
	private Moniteur moniteurB = new Moniteur("B : Services environnementaux");
	private Moniteur moniteurActif = this.moniteurA; // Moniteur sélectionné par défaut
	private Hashtable<Integer,Capteur> dicoCapteurs = new Hashtable<Integer,Capteur>(); // Liste des capteurs créés
	private DefaultListModel<String> listeEvents = new DefaultListModel<String>();      // Liste des évènements simulés
	private JButton details;                    // Bouton Détails
	private JButton traitee;                    // Bouton Traitée
	private JButton statistiques;               // Bouton Statistiques
	private DetailsFenetre detailsfenetre;      // Fenetre des détails
	private JFrame statFenetre = new JFrame();  // Fenetre des statistiques
	
	// Constructeur
	public Fenetre() {
        
		fenetreGauche = new FenetreGauche(gauche()); // Fenetre de gauche de simulation d'alarmes
		fenetreGauche.setJMenuBar(creerMenuBar());   // Menu en haut de la fenetre
		fenetreDroite = new FenetreDroite(droite());
		fenetreDroite.setJMenuBar(creerMenuBar());
        
	}
	
	// ------------------------------------------------------------------------------------ //
	// -------------------------- CONTENU FENETRE DE GAUCHE ------------------------------- //
	// ------------------------------------------------------------------------------------ //
	
	private JPanel gauche() {
		JPanel gauche = new JPanel(); 
		gauche.setLayout(new BorderLayout());  // Layout points cardinaux (NORTH, SOUTH, ...)
		
		// PARTIE SUD : SIMULATION D'ANOMALIES
		JPanel sud = new JPanel(); // Container
		sud.add(getTitre("Simuler une Anomalie"));   // Mise en forme du titre avec la méthode getTitre()
		sud.add(new JLabel("Renseignez les caractéristiques de l'anomalie"));
		sud.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color( 18, 30, 49))); // Barre de séparation bleue
		
		// Localisation
		JPanel localisation = new JPanel();
		localisation.add(new JLabel("Localisation : "));
		JComboBox<String> ldLocal = new JComboBox<String>(); // Liste déroulante vide
		localisation.add(ldLocal);
		
		// Boutons radio
	    JPanel boutonsRadio = new JPanel();
	    boutonsRadio.add(new JLabel("Niveau d'importance : "));
	    JRadioButton niv1 = new JRadioButton("1");
	    niv1.setSelected(true); // Le premier bouton radio est automatiquement sélectionné
	    JRadioButton niv2 = new JRadioButton("2");
	    JRadioButton niv3 = new JRadioButton("3");
	    ButtonGroup bg = new ButtonGroup(); // ButtonGroup pour empêcher la sélection de plusieurs boutons radio en meme temps
	    bg.add(niv1); bg.add(niv2); bg.add(niv3);
	    JPanel panRadio = new JPanel();
	    panRadio.add(niv1); panRadio.add(niv2); panRadio.add(niv3);
	    boutonsRadio.add(panRadio);
		
		// Texte additionnel
		JPanel texteGazRadiation = new JPanel();
		
		// Type de l'anomalie (incendie, gaz ou radiation)
		JPanel typeAnomalie = new JPanel();
		typeAnomalie.add(new JLabel("Type d'anomalie : "));
		JComboBox<String> listeTypeAlarme = new JComboBox<String>(getChoix()); // Liste déroulante contenant Incendie, Gaz, Radiation
		listeTypeAlarme.addItemListener(new ItemListener() { // Ajout du listener
			// Classe anonyme
			@Override
			public void itemStateChanged(ItemEvent e) { // Dès que l'utilisateur sélectionne un autre item
				fenetreGauche.setVisible(false);
				texteGazRadiation.removeAll(); // On supprime la zone de texte
				if (listeTypeAlarme.getSelectedItem().equals("Gaz")) { // Si l'item choisi est "Gaz"
					texteGazRadiation.add(getZoneTexte("Type de gaz (hélium, ...) : ")); // On crée une zone de texte adaptée
				} else if (listeTypeAlarme.getSelectedItem().equals("Radiation")){
					texteGazRadiation.add(getZoneTexte("Niveau de radiation (1 à 100) : "));
				}
				fenetreGauche.setVisible(true);
			}
		});
		typeAnomalie.add(listeTypeAlarme); // Ajout de la liste au container
		
		//Bouton simuler
		JButton simuler = new JButton("Simuler");
		simuler.setEnabled(false);                                                  // Le bouton est désactivé au début
		simuler.setToolTipText("Créez un capteur pour pouvoir utiliser ce bouton"); // Info-bulle
		simuler.addActionListener(new ActionListener() {                            // Ajout du listener
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Récupération de la zone de texte optionnelle
				String txt = null;
				if (texteGazRadiation.getComponents().length!=0) { 
					JTextField jtextfield = (JTextField) ((Container) texteGazRadiation.getComponents()[0]).getComponents()[1];
					txt = jtextfield.getText();
				}
				
				// Récupération valeur bouton radio sélectionné
				int nivImportance = 1;
				if (niv2.isSelected()) {
					nivImportance = 2;
				} else if (niv3.isSelected()){
					nivImportance = 3;
				}
				
				// Récupération du type de l'anomalie
				String type = listeTypeAlarme.getSelectedItem().toString();
				
				// Actions lors de l'appuie sur le bouton Simuler
				try {
					boutonSimuler(ldLocal,nivImportance,txt,type);
				} catch (TypesDifferentsException e1) {      // Si le capteur créé n'est pas compatible avec l'anomalie déclenchée
					e1.info();
				} catch (ListeDeroulanteVideException e2) {  // Si la liste déroulante est vide
					e2.warning();
				} catch (ValeurIncorrecteException e3) { // Si le niveau de radiation n'est pas compris entre 1 et 100
					e3.affErreur();
				}
			}
		});
		
		// Ajout des éléments au container
		sud.add(localisation);
		sud.add(typeAnomalie);
		sud.add(boutonsRadio);
		sud.add(texteGazRadiation);
		sud.add(simuler);
		
		
		// PARTIE NORD : AJOUT D'UN CAPTEUR
		JPanel nord = new JPanel();
		nord.setPreferredSize(new Dimension(-1,130));   // On ne contrôle pas la largeur --> -1
		nord.add(getTitre("Créer un nouveau Capteur")); // Mise en forme du titre avec la méthode getTitre()
		nord.add(new JLabel("Indiquez le nom de la zone couverte par le capteur"));
		
		// Localisation
		JTextField newCapteurNom = new JTextField(13); // Zone de texte
		
		// Type de capteur
		JComboBox<String> newCapteurType = new JComboBox<String>(getChoix()); // Liste déroulante
		
		//Bouton créer
		JButton creer = new JButton("Créer");
		creer.addActionListener(new ActionListener() { // Ajout du listener
			// Classe anonyme
			@Override
			public void actionPerformed(ActionEvent e) {
				simuler.setEnabled(true); // Désactivation 
				simuler.setToolTipText("Simuler une anomalie");
				if (newCapteurType.getSelectedItem().equals("Incendie")){
					Fenetre.this.addCapteur(new IncendieCapteur(newCapteurNom.getText()));
				} else if (newCapteurType.getSelectedItem().equals("Gaz")) {
					Fenetre.this.addCapteur(new GazCapteur(newCapteurNom.getText()));
				} else {
					Fenetre.this.addCapteur(new RadiationCapteur(newCapteurNom.getText()));
				}	
				setPossibilites(ldLocal);
				System.out.println("Le capteur "+newCapteurType.getSelectedItem()+" "+newCapteurNom.getText()+
						" a bien été créé");
				newCapteurNom.setText("");
			}
		});
		
		nord.add(newCapteurNom);
		nord.add(newCapteurType);
		nord.add(creer);
		
		gauche.add(nord,BorderLayout.NORTH);
		gauche.add(sud,BorderLayout.CENTER);
		//gauche.add(barre(),BorderLayout.SOUTH);
		return gauche;
	}
	
	
	// ------------------------------------------------------------------------------------ //
	// -------------------------- CONTENU FENETRE DE DROITE ------------------------------- //
	// ------------------------------------------------------------------------------------ //
	
	private JPanel droite() {
		JPanel droite = new JPanel();
		droite.setLayout(new BorderLayout());
		
		// PARTIE NORD : CHOIX DU MONITEUR
		JPanel nord = new JPanel();
		nord.setPreferredSize(new Dimension(-1,90));
		nord.add(getTitre("Choix du Moniteur"));
		
		// Boutons radio moniteurs
	    JPanel boutonsRadioMoniteurs = new JPanel();
	    boutonsRadioMoniteurs.add(new JLabel("Moniteur actif : "));
	    JRadioButton moniteur1 = new JRadioButton("A : Caserne de pompiers");
	    moniteur1.setSelected(true); // Le premier bouton radio est automatiquement sélectionné
	    moniteur1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setMoniteurActif(Fenetre.this.moniteurA);
				setEvents();
			}
		});
	    JRadioButton moniteur2 = new JRadioButton("B : Services environnementaux");
	    moniteur2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setMoniteurActif(Fenetre.this.moniteurB);
				setEvents();
			}
		});
	    ButtonGroup bg = new ButtonGroup(); // ButtonGroup pour empêcher la sélection de plusieurs boutons radio en meme temps
	    bg.add(moniteur1); bg.add(moniteur2); 
	    JPanel groupe = new JPanel();
	    groupe.setLayout(new BoxLayout(groupe, BoxLayout.Y_AXIS));
	    groupe.add(moniteur1); groupe.add(moniteur2); 
	    boutonsRadioMoniteurs.add(groupe);
	    
	    nord.add(boutonsRadioMoniteurs);
	    
	    // PARTIE SUD : Gestion des évènements
	    JPanel sud = new JPanel();
	    sud.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color( 18, 30, 49)));
	    sud.add(getTitre("Gestion des évènements"));
	    sud.add(new JLabel("Sélectionnez un évènement puis cliquez sur le bouton"));
	    
	    // Liste des évènements
	    JList<String> liste = new JList<String>(this.listeEvents);
	    liste.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	    		if (!getMoniteurActif().evenements.isEmpty()) {
	    			details.setEnabled(true);
	    			traitee.setEnabled(false);
	    		}	
	    	}
	    });
	    
	    JScrollPane scListe = new JScrollPane(liste);
	    scListe.setPreferredSize(new Dimension(300,180));
	    
	    // Bouton Détails
	    details = new JButton("Détails");
	    details.setEnabled(false);
	    details.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!getMoniteurActif().evenements.isEmpty()) {
					traitee.setEnabled(true);
					detailsfenetre = new DetailsFenetre(getMoniteurActif().evenements.get(liste.getSelectedIndex()));
				} else 
					JOptionPane.showMessageDialog(null, "Veuillez d'abord choisir un élément de la liste avant "
							+ "d'afficher les détails.","Attention",JOptionPane.WARNING_MESSAGE);
			}
		});
	    
	    // Bouton Traitée
	    traitee = new JButton("Traitée");
	    traitee.setEnabled(false);
	    traitee.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				detailsfenetre.dispose();
				getMoniteurActif().evenements.remove(liste.getSelectedIndex());
				setEvents();
				
			}
		});
	    
	    // Bouton Statistiques
	    statistiques = new JButton("Statistiques");
	    statistiques.setEnabled(false);
	    statistiques.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				statFenetre.setTitle("Moniteur "+getMoniteurActif().getType());
				statFenetre.getContentPane().add(new PieChart(getMoniteurActif()));
				statFenetre.setSize(300, 300);
				statFenetre.setLocationRelativeTo(null);
				statFenetre.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				statFenetre.setVisible(true);
			    JOptionPane.showMessageDialog(fenetreGauche, legende(), "Légende", JOptionPane.PLAIN_MESSAGE);
			}
		});
	    
	  
	    sud.add(scListe);
	    sud.add(details);
	    sud.add(traitee);
	    sud.add(statistiques);
	    

	    droite.add(nord,BorderLayout.NORTH);
	    droite.add(sud,BorderLayout.CENTER);
	    //droite.add(barre(),BorderLayout.SOUTH);
	    return droite;
	}
	
	private JPanel barre() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Polytech Annecy-Chambéry | IDU3 | Année 2019-2020");

		panel.add(label);
		panel.setBorder(BorderFactory.createLineBorder(new Color( 18, 30, 49)));
		return panel;
	}

	public void addCapteur(Capteur capteur){
		setListeners(capteur);
		this.dicoCapteurs.put(this.cleMax+1,capteur);
		this.cleMax += 1;
	}
	
	private void setListeners(Capteur capteur) {
		if (capteur instanceof GazCapteur) {
			((GazCapteur) capteur).addListener(this.moniteurA);
			((GazCapteur) capteur).addListener(this.moniteurB);
		} else if (capteur instanceof IncendieCapteur){
			((IncendieCapteur) capteur).addListener(this.moniteurA);
		} else if (capteur instanceof RadiationCapteur){ 
			((RadiationCapteur) capteur).addListener(this.moniteurB);
		}
	}
	
	private JMenuBar creerMenuBar() {
		JMenuBar menu = new JMenuBar();
		JMenu premier = new JMenu("Fermer");
		JMenuItem fermer = new JMenuItem("Tout fermer");
		fermer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				statFenetre.dispose();
				fenetreDroite.dispose();
				fenetreGauche.dispose();	
			}
		});
		premier.add(fermer);
		menu.add(premier);
		return menu;
	}

	public Moniteur getMoniteurActif() {
		return moniteurActif;
	}

	private void setMoniteurActif(Moniteur m) {
		this.moniteurActif = m;
	}

	private JLabel getTitre(String titre) {
		JLabel titreModif = new JLabel(titre);
		titreModif.setFont(new Font("Tahoma",Font.PLAIN,18));
		return titreModif;
	    
	}
	
	private void setPossibilites(JComboBox<String> listeDeroulante) {
		listeDeroulante.removeAllItems();
		for (int cle : this.dicoCapteurs.keySet()) {
			listeDeroulante.addItem(this.dicoCapteurs.get(cle).getLocalisation());
		}
	}
	
	private void boutonSimuler(JComboBox<String> listeDeroulante, int nivImportance, String texte, String type) 
			throws TypesDifferentsException,ListeDeroulanteVideException,ValeurIncorrecteException{
		int indiceInverse = this.dicoCapteurs.size()-listeDeroulante.getSelectedIndex();
		Capteur capteurDeclencheur = this.dicoCapteurs.get(indiceInverse);
		if (listeDeroulante.getSelectedItem()==null) {
			throw new ListeDeroulanteVideException();
		} else if (!type.equals(capteurDeclencheur.getTypeCapteur())) {
			throw new TypesDifferentsException(listeDeroulante.getSelectedItem().toString(),type);
		} else {
			if (capteurDeclencheur instanceof IncendieCapteur) {
				((IncendieCapteur) this.dicoCapteurs.get(indiceInverse)).simulerIncendie(nivImportance);
				JOptionPane.showMessageDialog(null, "Alarme incendie déclenchée !", 
						"Alarme incendie déclenchée", JOptionPane.PLAIN_MESSAGE,new ImageIcon("data/incendie.png"));
			} else if (capteurDeclencheur instanceof GazCapteur) {
				((GazCapteur) this.dicoCapteurs.get(indiceInverse)).simulerFuiteGaz(nivImportance, texte);
				JOptionPane.showMessageDialog(null, "Alarme fuite de gaz déclenchée !", 
						"Alarme gaz déclenchée", JOptionPane.PLAIN_MESSAGE,new ImageIcon("data/gaz.png"));
			} else {
				((RadiationCapteur) this.dicoCapteurs.get(indiceInverse)).simulerAccidentNucleaire(nivImportance, Integer.parseInt(texte));;
				JOptionPane.showMessageDialog(null, "Alarme radiation déclenchée !", 
						"Alarme radiation déclenchée", JOptionPane.PLAIN_MESSAGE,new ImageIcon("data/radiation.png"));
			}
		}
		setEvents();
	}
	
	private String legende() {
		String res = ("LEGENDE\n");
		res += "Vert : Niveau d'importance 1\n";
		res += "Orange : Niveau d'importance 2\n";
		res += "Rouge : Niveau d'importance 3\n";
		return res;
	}
	
	private JPanel getZoneTexte(String intitule) {
		JPanel res = new JPanel();
		JLabel label = new JLabel(intitule);
		JTextField zone = new JTextField(8);
		res.add(label);
		res.add(zone);
		return res;
	}
	
	private String[] getChoix() {
		return new String[]{"Incendie", "Gaz", "Radiation"};
	}
	
	private void setEvents() {
		this.listeEvents.removeAllElements();
		this.details.setEnabled(false);
		this.traitee.setEnabled(false);
		if (getMoniteurActif().evenements.isEmpty())
			statistiques.setEnabled(false);
		else
			statistiques.setEnabled(true);
		for (AnomalieEvent ae : getMoniteurActif().evenements) {
			this.listeEvents.addElement(ae.getType()+" - "+ae.getLocalisation()+" - "+ae.getDate());
		}
	}

}
