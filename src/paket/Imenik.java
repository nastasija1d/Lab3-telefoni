package paket;

public class Imenik extends ListaStavki {

	public Imenik() {
		super();
	}
	
	public String ime(Broj broj) throws GNepostoji {
		for (Stavka s : this.lista) {
			if(s.getText().getText().equals(broj.toString())) return s.getNaslov().getText();
		}
		if (true) throw new GNepostoji();
		return null;
	}
	
	public Broj broj(String ime) throws GNepostoji {
		for (Stavka s : this.lista) {
			if(s.getNaslov().getText().equals(ime)) {
				String str = s.getText().getText();
				return new Broj(str.substring(1,4),str.substring(5,7),str.substring(8));
			}
		}
		if(true) throw new GNepostoji();
		return null;
	}

	
	@Override 
	public void dodaj(Stavka s) {
		if (s instanceof Kontakt) super.dodaj(s);
	}
	
/*	public static void main(String[]arg) {
		Imenik imenik = new Imenik();
		Kontakt k1 = new Kontakt("Nastasija", new Broj("+381605556979"));
		Kontakt k2 = new Kontakt("Lilica", new Broj("+381654174060"));
		Kontakt k3 = new Kontakt("Bojan", new Broj("+381642601665"));
		
		Broj b1 = new Broj("+381605556979");
		
		imenik.dodaj(k1);
		imenik.dodaj(k2);
		imenik.dodaj(k3);
		
		try {
			System.out.println(imenik.ime(b1));
			System.out.println(imenik.broj("Lilica"));
			System.out.println(imenik.ime(new Broj(381,64,2601665)));
		} catch (GNepostoji e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
*/
}
