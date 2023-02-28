package paket;

import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;

public class ListaStavki extends Panel {

	ArrayList<Stavka> lista;
	
	public ListaStavki() {
		this.setLayout(new GridLayout(7, 1));
		lista = new ArrayList<>();
	}
	
	public void dodaj(Stavka s) {
		lista.add(s);
		add(s);
	}
}
