package paket;

public class Kontakt extends Stavka {
	

	public Kontakt(String n, Broj t) {
		super(n, t.toString());
	}
	
	public Kontakt(String n, String t) {
		super(n, t);
	}
}
