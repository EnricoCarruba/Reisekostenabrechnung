import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MeinPanel extends JPanel implements ActionListener {
	
	
	/* Treiber für die SQL-Datenbank laden und Adresse der 
	Datenbank inkl. Benutzername und Passwort */ 
	
	private String treiber	= "com.mysql.cj.jdbc.Driver";
	private String url		= "jdbc:mysql://192.168.64.2/Kosten?user=woot";
	
	// Variablen in die die Datenbank-Lesezugriffe geschrieben werden
	double readTagegeld, readFahrtkosten, readUebernachtung, readSonstiges;
	
	// Konstruktor für die Fehlerüberprüfung aufrufen
	
	ReisekostenCheck datumCheck = new ReisekostenCheck();
	ReisekostenCheck nameCheck = new ReisekostenCheck();
	ReisekostenCheck vornameCheck = new ReisekostenCheck();
	ReisekostenCheck strasseCheck = new ReisekostenCheck();
	ReisekostenCheck plzCheck = new ReisekostenCheck();
	ReisekostenCheck ortCheck = new ReisekostenCheck();
	ReisekostenCheck anzTagegeldCheck = new ReisekostenCheck();
	ReisekostenCheck anzUebernachtungCheck = new ReisekostenCheck();
	ReisekostenCheck anzFahrtkostenCheck = new ReisekostenCheck();
	ReisekostenCheck anzSonstigesCheck = new ReisekostenCheck();
	ReisekostenCheck idCheck = new ReisekostenCheck();
	ReisekostenCheck abIdCheck = new ReisekostenCheck();
	
	ReisekostenCheck koIdTagegeldCheck = new ReisekostenCheck();
	ReisekostenCheck koIdFahrtkostenCheck = new ReisekostenCheck();
	ReisekostenCheck koIdUebernachtungCheck = new ReisekostenCheck();
	ReisekostenCheck koIdSonstigesCheck = new ReisekostenCheck();
	
	
	
	// Steuerelemente anlegen
	
	private JLabel		titel			= new JLabel("Reisekostenabrechnung");
	private JLabel		rgNr			= new JLabel("Rechnungsnummer:");
	private JLabel		datum			= new JLabel("Datum:");
	private JLabel		name			= new JLabel("Name	:");
	private JLabel		vorname			= new JLabel("Vorname:");
	private JLabel		strasse			= new JLabel("Straße:");
	private JLabel		plz				= new JLabel("PLZ	:");
	private JLabel		ort				= new JLabel("Ort:");
	private JLabel		kostenart		= new JLabel("KOSTENART");
	private JLabel		anzahl			= new JLabel("ANZAHL");
	private JTextField	anzTagegeld		= new JTextField(20);
	private JLabel		einzel			= new JLabel("EINZELVERGÜTUNG");
	private JLabel		gesamt			= new JLabel("GESAMTVERGÜTUNG");
	private JLabel		tagegeld		= new JLabel("");
	private JLabel		tagegeldGesamt	= new JLabel("0");
	private JLabel		uebernachtung	= new JLabel("Übernachtung");
	private JTextField	anzUebernachtung = new JTextField(20);
	private JLabel		uebernGesamt	= new JLabel("0");
	private JLabel		fahrtkosten		= new JLabel("Fahrkosten");
	private JTextField	anzFahrtkosten	= new JTextField(20);
	private JLabel		fahrtkGesamt	= new JLabel("0");
	private JLabel		sonstiges		= new JLabel("Sonstiges");
	private JTextField	anzSonstiges	= new JTextField(20);
	private JLabel 		sonstigesGesamt	= new JLabel("0");
	private JLabel		evgTagegeld		= new JLabel("");
	private JLabel		evgUebernachtung= new JLabel("");
	private JLabel		evgFahrtkosten	= new JLabel("0");
	private JLabel		evgSonstiges	= new JLabel("0");
	private JLabel		gvgTagegeld		= new JLabel("0");
	private JLabel		gvgUebernachtung= new JLabel("0");
	private JLabel		gvgFahrtkosten	= new JLabel("0");
	private JLabel		gvgSonstiges	= new JLabel("0");
	private JTextField	fieldDatum			= new JTextField(20);
	private JTextField	fieldName			= new JTextField(20);
	private JTextField	fieldVorname		= new JTextField(20);
	private JTextField	fieldStrasse		= new JTextField(20);
	private JTextField	fieldPlz			= new JTextField(20);
	private JTextField	fieldOrt			= new JTextField(20);
	private JTextField	fieldAnzahl			= new JTextField(20);
	private JLabel		rgNrNeu				= new JLabel("");
	private JButton		absenden			= new JButton("absenden");
	
	// Konstruktor
	
	public MeinPanel() {
	
	this.setLayout(null);
	
	// Listener aktivieren
	
	absenden.addActionListener(this);
	fieldDatum.addActionListener (this);
	fieldName.addActionListener (this);
	fieldVorname.addActionListener (this);
	fieldStrasse.addActionListener (this);
	fieldPlz.addActionListener (this);
	fieldOrt.addActionListener (this);
	fieldAnzahl.addActionListener (this);
	anzTagegeld.addActionListener(this);
	anzUebernachtung.addActionListener(this);
	anzFahrtkosten.addActionListener(this);
	anzSonstiges.addActionListener(this);
	
	// Platzierung der Steuerlemente
	
	// Überschrift
	titel.setBounds			(290,10,200,40); 
	
	// Rechnungsnummer und Datum
	rgNr.setBounds			(30,100,160,40);
	rgNrNeu.setBounds		(160,100,160,40); 
	datum.setBounds			(400, 100, 160, 40);
	fieldDatum.setBounds	(460, 100, 160, 40);  
	
	// Name, Vorname
	name.setBounds			(30, 200, 200, 40);  
	fieldName.setBounds		(160, 200, 160, 40);
	vorname.setBounds		(400, 200, 200, 40);
	fieldVorname.setBounds	(460, 200, 160, 40); 
	
	// Straße
	strasse.setBounds		(30, 240, 200, 40);
	fieldStrasse.setBounds	(160, 240, 160, 40);
	
	// PLZ und Ort
	plz.setBounds			(30, 280, 200, 40);
	fieldPlz.setBounds		(160, 280, 160, 40);
	ort.setBounds			(400,280,200,40);
	fieldOrt.setBounds		(460, 280, 160, 40);
	
	// Tabelle
	kostenart.setBounds(65, 340, 160, 40);
	anzahl.setBounds(225, 340, 160, 40);
	einzel.setBounds(335, 340, 160, 40);
	gesamt.setBounds(480, 340, 160, 40);
	tagegeld.setBounds(70, 380, 160, 40);
	uebernachtung.setBounds(60, 420, 160, 40);
	fahrtkosten.setBounds(65, 460, 160, 40);
	sonstiges.setBounds(70, 500, 160, 40);
	anzTagegeld.setBounds(235, 380, 40, 40);
	anzUebernachtung.setBounds(235, 420, 40, 40);
	anzFahrtkosten.setBounds(235, 460, 40, 40);
	anzSonstiges.setBounds(235, 500, 40, 40);
	evgTagegeld.setBounds(385, 380, 40, 40);
	evgUebernachtung.setBounds(377, 420, 40, 40);
	evgFahrtkosten.setBounds(392, 460, 40, 40);
	evgSonstiges.setBounds(385, 500, 40, 40);
	gvgTagegeld.setBounds(535, 380,40,40);
	gvgUebernachtung.setBounds(535,420,40,40);
	gvgFahrtkosten.setBounds(535, 460, 40, 40);
	gvgSonstiges.setBounds(535, 500, 40, 40);
	
	// Absenden-Button
	absenden.setBounds		(290, 550, 100, 30);
	
	// Hinzufügen der Steuerelemente
	this.add(titel);		
	this.add(rgNr);	
	this.add(datum);
	this.add(name);	
	this.add(vorname);	
	this.add(strasse);	
	this.add(plz);		
	this.add(ort);				
	this.add(kostenart);			
	this.add(anzahl);
	this.add(einzel);	
	this.add(gesamt);
	this.add(tagegeld);		
	this.add(tagegeldGesamt);		
	this.add(uebernachtung);	
	this.add(uebernGesamt);		
	this.add(fahrtkosten);		
	this.add(fahrtkGesamt);	
	this.add(sonstiges);	
	this.add(sonstigesGesamt);	
	this.add(fieldDatum);	
	this.add(fieldName);				
	this.add(fieldVorname);
	this.add(fieldStrasse);	
	this.add(fieldPlz);	
	this.add(fieldOrt);
	this.add(fieldAnzahl);
	this.add(rgNrNeu);	
	this.add(absenden);	
	this.add(anzFahrtkosten);
	this.add(anzSonstiges);
	this.add(anzTagegeld);
	this.add(anzUebernachtung);
	this.add(evgTagegeld);
	this.add(evgUebernachtung);
	this.add(evgFahrtkosten);
	this.add(evgSonstiges);
	this.add(gvgTagegeld);
	this.add(gvgUebernachtung);
	this.add(gvgFahrtkosten);
	this.add(gvgSonstiges);
		
	dbReadTagegeld();
	dbReadUebernachtung();
	dbReadFahrtkosten();
	dbReadSonstiges();
	
	
		
	} // Ende Konstruktor
	
	protected void paintComponent (Graphics g) {
		super.paintComponent(g);
		// Tabelle mit Rechtecken zeichnen	
		int x = 30, y = 340;
		for(int i = 0; i < 4; i++) {
					for(int z = 0; z < 5; z++) {
						g.drawRect(x, y, 147, 40);
						y += 40;
					}
					x += 147;
					y = 340;
				}
	}
	
	// Datenbank Treiber Laden, Beträge für Einzelvergütungen lesen und Datenbankverbindung trennen
	
	public void dbReadTagegeld() {
		
		try
		{
			Class.forName (treiber);
			
			// Verbindung aufnehmen zur im String "url" angegebenen Datenbank:
			
			Connection c = DriverManager.getConnection (url);
			
			// SQL Befehl
			
			String sql = "SELECT * FROM kostenart WHERE ko_id = 1";
			Statement s = c.createStatement ();  
			ResultSet rs = s.executeQuery (sql); 
			
			while (rs.next())
			{
				evgTagegeld.setText(rs.getString("euro"));
				tagegeld.setText(rs.getString("beschreibung"));
				koIdTagegeldCheck.setId(rs.getInt("ko_id"));
				
			}
			
			c.close ();
		}
		
		catch (ClassNotFoundException cnfe)
		{
			System.out.println (cnfe);
		}
		catch (SQLException sqle)
		{
			System.out.println (sqle);
		}
	}
	
	public void dbReadUebernachtung() {
		
		try
		{
			Class.forName (treiber);
			
			// Verbindung aufnehmen zur im String "url" angegebenen Datenbank:
			
			Connection c = DriverManager.getConnection (url);
			
			// SQL Befehl
			
			String sql = "SELECT * FROM kostenart WHERE ko_id = 2";
			Statement s = c.createStatement ();  
			ResultSet rs = s.executeQuery (sql); 
			
			while (rs.next())
			{
				evgUebernachtung.setText(rs.getString("euro"));
				uebernachtung.setText(rs.getString("beschreibung"));
				koIdUebernachtungCheck.setId(rs.getInt("ko_id"));
				
			}
			
			c.close ();
		}
		
		catch (ClassNotFoundException cnfe)
		{
			System.out.println (cnfe);
		}
		catch (SQLException sqle)
		{
			System.out.println (sqle);
		}
	}
	
	public void dbReadFahrtkosten() {
		
		try
		{
			Class.forName (treiber);
			
			// Verbindung aufnehmen zur im String "url" angegebenen Datenbank:
			
			Connection c = DriverManager.getConnection (url);
			
			// SQL Befehl
			
			String sql = "SELECT * FROM kostenart WHERE ko_id = 3";
			Statement s = c.createStatement ();  
			ResultSet rs = s.executeQuery (sql); 
			
			while (rs.next())
			{
				evgFahrtkosten.setText(rs.getString("euro"));
				fahrtkosten.setText(rs.getString("beschreibung"));
				koIdFahrtkostenCheck.setId(rs.getInt("ko_id"));
				
			}
			
			c.close ();
		}
		
		catch (ClassNotFoundException cnfe)
		{
			System.out.println (cnfe);
		}
		catch (SQLException sqle)
		{
			System.out.println (sqle);
		}
	}

	public void dbReadSonstiges() {
		
		try
		{
			Class.forName (treiber);
			
			// Verbindung aufnehmen zur im String "url" angegebenen Datenbank:
			
			Connection c = DriverManager.getConnection (url);
			
			// SQL Befehl
			
			String sql = "SELECT * FROM kostenart WHERE ko_id = 4";
			Statement s = c.createStatement ();  
			ResultSet rs = s.executeQuery (sql); 
			
			while (rs.next())
			{
				evgSonstiges.setText(rs.getString("euro"));
				sonstiges.setText(rs.getString("beschreibung"));
				koIdSonstigesCheck.setId(rs.getInt("ko_id"));
				
			}
			
			c.close ();
		}
		
		catch (ClassNotFoundException cnfe)
		{
			System.out.println (cnfe);
		}
		catch (SQLException sqle)
		{
			System.out.println (sqle);
		}
	}
	
	public void dbWriteAnwender() {		
		try 
		{
			Class.forName (treiber);
			
			// Verbindung aufnehmen zur im String "url" angegebenen Datenbank:
			
			Connection c = DriverManager.getConnection (url);
			
			// SQL Befehl
			
			String sql = String.format("INSERT INTO anwender ( vorname, name, straße, plz) VALUES "
									 + "('%s', '%s', '%s', '%s')",vornameCheck.getJText(), nameCheck.getJText(), strasseCheck.getJText(), plzCheck.getPlz()); 
			Statement s = c.createStatement ();  
			s.executeUpdate (sql); 
			
			c.close ();
		}
		
		catch (ClassNotFoundException cnfe)
		{
			System.out.println (cnfe);
		}
		catch (SQLException sqle)
		{
			System.out.println (sqle);
		}
	}
	
	public void dbWriteOrt() {		
		try 
		{
			Class.forName (treiber);
			
			// Verbindung aufnehmen zur im String "url" angegebenen Datenbank:
			
			Connection c = DriverManager.getConnection (url);
			
			// SQL Befehl
			
			String sql = String.format("INSERT INTO `ort` (`plz`, `ort`) VALUES "
									 + "('%s', '%s')", plzCheck.getPlz(), ortCheck.getJText());
			Statement s = c.createStatement ();  
			s.executeUpdate (sql); 
			
			c.close ();
		}
		
		catch (ClassNotFoundException cnfe)
		{
			System.out.println (cnfe);
		}
		catch (SQLException sqle)
		{
			System.out.println (sqle);
		}
	}
	
	public void dbWriteAbrechnung() {		
		try 
		{
			Class.forName (treiber);
			
			// Verbindung aufnehmen zur im String "url" angegebenen Datenbank:
			
			Connection c = DriverManager.getConnection (url);
			
			// SQL Befehl
						
			String sql = String.format("INSERT INTO `abrechnung` (`ab_id`, `an_id`, `datum`) VALUES " 
									 + "('%s', '%s', '%s')", abIdCheck.getId(), idCheck.getId(), datumCheck.getDatum());
			Statement s = c.createStatement ();  
			s.executeUpdate (sql); 
			
			c.close ();
		}
		
		catch (ClassNotFoundException cnfe)
		{
			System.out.println (cnfe);
		}
		catch (SQLException sqle)
		{
			System.out.println (sqle);
		}
	}
	
	public void dbWriteAbrechnungPositionenTagegeld() {		
		try 
		{
			Class.forName (treiber);
			
			// Verbindung aufnehmen zur im String "url" angegebenen Datenbank:
			
			Connection c = DriverManager.getConnection (url);
			
			// SQL Befehl
						
			String sql = String.format("INSERT INTO `abrechnungPositionen` (`ab_id`, `ko_id`, `anzahl`) VALUES " 
									 + "('%s', '%s', '%s')", abIdCheck.getId(), koIdTagegeldCheck.getId(), anzTagegeld.getText());
			Statement s = c.createStatement ();  
			s.executeUpdate (sql); 
			
			c.close ();
		}
		
		catch (ClassNotFoundException cnfe)
		{
			System.out.println (cnfe);
		}
		catch (SQLException sqle)
		{
			System.out.println (sqle);
		}
	}
	
	public void dbWriteAbrechnungPositionenUebernachtung() {		
		try 
		{
			Class.forName (treiber);
			
			// Verbindung aufnehmen zur im String "url" angegebenen Datenbank:
			
			Connection c = DriverManager.getConnection (url);
			
			// SQL Befehl
						
			String sql = String.format("INSERT INTO `abrechnungPositionen` (`ab_id`, `ko_id`, `anzahl`) VALUES " 
									 + "('%s', '%s', '%s')", abIdCheck.getId(), koIdUebernachtungCheck.getId(), anzUebernachtung.getText());
			Statement s = c.createStatement ();  
			s.executeUpdate (sql); 
			
			c.close ();
		}
		
		catch (ClassNotFoundException cnfe)
		{
			System.out.println (cnfe);
		}
		catch (SQLException sqle)
		{
			System.out.println (sqle);
		}
	}
	
	public void dbWriteAbrechnungPositionenFahrtkosten() {		
		try 
		{
			Class.forName (treiber);
			
			// Verbindung aufnehmen zur im String "url" angegebenen Datenbank:
			
			Connection c = DriverManager.getConnection (url);
			
			// SQL Befehl
						
			String sql = String.format("INSERT INTO `abrechnungPositionen` (`ab_id`, `ko_id`, `anzahl`) VALUES " 
									 + "('%s', '%s', '%s')", abIdCheck.getId(), koIdFahrtkostenCheck.getId(), anzFahrtkosten.getText());
			Statement s = c.createStatement ();  
			s.executeUpdate (sql); 
			
			c.close ();
		}
		
		catch (ClassNotFoundException cnfe)
		{
			System.out.println (cnfe);
		}
		catch (SQLException sqle)
		{
			System.out.println (sqle);
		}
	}
	
	public void dbWriteAbrechnungPositionenSonstiges() {		
		try 
		{
			Class.forName (treiber);
			
			// Verbindung aufnehmen zur im String "url" angegebenen Datenbank:
			
			Connection c = DriverManager.getConnection (url);
			
			// SQL Befehl
						
			String sql = String.format("INSERT INTO `abrechnungPositionen` (`ab_id`, `ko_id`, `anzahl`) VALUES " 
									 + "('%s', '%s', '%s')", abIdCheck.getId(), koIdSonstigesCheck.getId(), anzSonstiges.getText());
			Statement s = c.createStatement ();  
			s.executeUpdate (sql); 
			
			c.close ();
		}
		
		catch (ClassNotFoundException cnfe)
		{
			System.out.println (cnfe);
		}
		catch (SQLException sqle)
		{
			System.out.println (sqle);
		}
	}
	
	public void dbReadAnwenderId() {
		try
		{
			Class.forName (treiber);
			
			// Verbindung aufnehmen zur im String "url" angegebenen Datenbank:
			
			Connection c = DriverManager.getConnection (url);
			
			// SQL Befehl
			
			String sql = String.format("SELECT * FROM anwender WHERE vorname = '%s' AND name = '%s' AND straße = '%s' AND plz = '%s'", 
						vornameCheck.getJText(), nameCheck.getJText(), strasseCheck.getJText(), plzCheck.getPlz()); 
			Statement s = c.createStatement ();  
			ResultSet rs = s.executeQuery (sql); 
			
			while (rs.next())
			{
				idCheck.setId(rs.getInt("an_id"));
				
			}
			
			c.close ();
		}
		
		catch (ClassNotFoundException cnfe)
		{
			System.out.println (cnfe);
		}
		catch (SQLException sqle)
		{
			System.out.println (sqle);
		}
	}

	public void dbReadAbrechnungId() {
		try
		{
			Class.forName (treiber);
			
			// Verbindung aufnehmen zur im String "url" angegebenen Datenbank:
			
			Connection c = DriverManager.getConnection (url);
			
			// SQL Befehl
			
			String sql = String.format("SELECT * FROM abrechnung WHERE an_id = '%s'",idCheck.getId()); 
			Statement s = c.createStatement ();  
			ResultSet rs = s.executeQuery (sql); 
			
			while (rs.next())
			{
				abIdCheck.setId(rs.getInt("ab_id"));
				
			}
			
			c.close ();
		}
		
		catch (ClassNotFoundException cnfe)
		{
			System.out.println (cnfe);
		}
		catch (SQLException sqle)
		{
			System.out.println (sqle);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == absenden) {
			try {
			double ergebnis = Integer.parseInt(anzTagegeld.getText()) * Double.parseDouble(evgTagegeld.getText());
			anzTagegeldCheck.setAnzahlGes(ergebnis);
			gvgTagegeld.setText("" + anzTagegeldCheck.getAnzahlGes());
			
			}
			catch (NumberFormatException nfe) {
				anzTagegeld.setText("0");
				gvgTagegeld.setText("0");
			}
			
		}
		
		if (e.getSource() == absenden) {
			try {
				double ergebnis = Integer.parseInt(anzUebernachtung.getText()) * Double.parseDouble(evgUebernachtung.getText());
				anzUebernachtungCheck.setAnzahlGes(ergebnis);
				gvgUebernachtung.setText("" + anzUebernachtungCheck.getAnzahlGes());
			}
			catch (NumberFormatException nfe) {
				anzUebernachtung.setText("0");
				gvgUebernachtung.setText("0");
			}		
		}
		
		if (e.getSource() == absenden) {
			try {
				double ergebnis = Integer.parseInt(anzFahrtkosten.getText()) * Double.parseDouble(evgFahrtkosten.getText());
				anzFahrtkostenCheck.setAnzahlGes(ergebnis);
				gvgFahrtkosten.setText("" + anzFahrtkostenCheck.getAnzahlGes());
			}
			catch (NumberFormatException nfe) {
				anzFahrtkosten.setText("0");
				gvgFahrtkosten.setText("0");
			}		
		}
		
		if (e.getSource() == absenden) {
			try {
				double ergebnis = Integer.parseInt(anzSonstiges.getText()) * Double.parseDouble(evgSonstiges.getText());
				anzSonstigesCheck.setAnzahlGes(ergebnis);
				gvgSonstiges.setText("" + anzSonstigesCheck.getAnzahlGes());
			}
			catch (NumberFormatException nfe) {
				anzSonstiges.setText("0");
				gvgSonstiges.setText("0");
			}	
		}
		if(e.getSource() == absenden) {
			nameCheck.setJText(fieldName.getText());
			fieldName.setText(nameCheck.getJText());
		}	
		if(e.getSource() == absenden) {
			plzCheck.setPlz(fieldPlz.getText());
			fieldPlz.setText(plzCheck.getPlz());
		}
		if(e.getSource() == absenden) {
			strasseCheck.setJText(fieldStrasse.getText());
			fieldStrasse.setText(strasseCheck.getJText());			
		}
		if(e.getSource() == absenden) {
			vornameCheck.setJText(fieldVorname.getText());
			fieldVorname.setText(vornameCheck.getJText());
		}
		if(e.getSource() == absenden) {
			datumCheck.setDatum(fieldDatum.getText());
			fieldDatum.setText(datumCheck.getDatum());
		}
		if(e.getSource() == absenden) {
			ortCheck.setJText(fieldOrt.getText());
			fieldOrt.setText(ortCheck.getJText());
		}
		
		/*
		 * ABSENDEN! 
		 *  
		 */
		if (e.getSource() == absenden) {
			
			if(nameCheck.dbIstOk() 		== true &&
			   vornameCheck.dbIstOk() 	== true &&
			   strasseCheck.dbIstOk() 	== true &&
			   datumCheck.dbIstOk() 	== true &&
			   plzCheck.dbIstOk() 		== true &&
			   ortCheck.dbIstOk() 		== true) {
				
				
				/*
				 *  
				 * Hier Datenbank aufrufen
				 * 
				 */
				
				dbWriteAnwender();
				dbReadAnwenderId();
				dbWriteOrt();
				dbWriteAbrechnung();
				dbReadAbrechnungId();
				dbWriteAbrechnungPositionenTagegeld();
				dbWriteAbrechnungPositionenUebernachtung();
				dbWriteAbrechnungPositionenFahrtkosten();
				dbWriteAbrechnungPositionenSonstiges();
				
				
				rgNrNeu.setText("" + idCheck.getId());
				
				
			}
		}
	}
	
	
} // ENDE Klasse
