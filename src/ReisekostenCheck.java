
public class ReisekostenCheck {
	
	private String text,
				   plz,
				   datum;
	
	private double anzahl, anzahlGes;
	boolean istOk = false;
	private int id;
	
	
	public ReisekostenCheck() {
		
	}
	
	public void setJText(String text) {
		
		if(text != null && !text.isEmpty() && text != " " && text.length()<255) {
			text = text.substring(0,1).toUpperCase() + text.substring(1);
			this.text = text;
			istOk = true;
		}
		else {
			this.text = "Fehler in der Eingabe!";
			istOk = false;
		}
		
	}
	public String getJText() {
		return text;
	}
	
	public void setAnzahlGes(double anzahlGes) {
		this.anzahlGes = anzahlGes;
	}
	public double getAnzahlGes() {
		return anzahlGes;
	}
	public void setAnzahl(double anzahl) {
		this.anzahl = anzahl;
	}
	public double getAnzahl() {
		return anzahl;
	}
	
	public boolean dbIstOk() {
		return istOk;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setPlz(String plz) {
		int plzNummern;
		
		try {
			plzNummern = Integer.parseInt(plz);
			this.plz = "" + plzNummern;
			istOk = true;
		}
		catch(NumberFormatException nfe) {
			this.plz = "Nur Zahlen";
			istOk = false;
		}
	}
	public String getPlz() {
		return plz;
	}
	public void setDatum(String datum) {
		this.datum = datum;
		istOk = true;
	}
	public String getDatum() {
		return datum;
	}
	
}
