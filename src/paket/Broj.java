package paket;

public class Broj {

	private String drzava;
	private String operater;
	private String pretplatnik;
	
	public Broj(String d,String o,String p) {
		drzava = d;
		operater = o;
		pretplatnik = p;
	}
	
	public Broj(int d,int o,int p) {
		drzava = "" + d;
		operater = "" + o;
		pretplatnik = "" + p;
	}
	
	public Broj(String s) {//+381605556979
		drzava = s.substring(1, 4);
		operater = s.substring(4, 6);
		pretplatnik = s.substring(6);
	}
	
	public boolean istaDrzava(Broj b) {
		return this.drzava.equals(b.drzava);
	}
	
	public boolean istiOperater(Broj b) {
		return (istaDrzava(b) && this.operater.equals(b.operater));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Broj))
			return false;
		Broj other = (Broj) obj;
		return(istiOperater(other) && this.pretplatnik.equals(other.pretplatnik));
	}

	@Override
	public String toString() {
		return "+" + drzava + " " + operater + " " + pretplatnik;
	}

	public static void main(String[] arg) {
		Broj b = new Broj("+381605556979");
		Broj b2 = new Broj("+381604174060");
		System.out.println(b);
		System.out.println(b2);
		System.out.println(b.istaDrzava(b2));
		System.out.println(b.istiOperater(b2));
		System.out.println(b.equals(b2));
		System.out.println("------------");
		String str = "+381 60 5556979";
		System.out.println(str.substring(1,4)+str.substring(5,7)+str.substring(8));
	}

}
