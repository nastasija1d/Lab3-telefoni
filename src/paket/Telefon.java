package paket;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

public class Telefon extends Panel {
		
	private Broj brojTelefona;
	private Imenik imenik;
	Button	dodaj;
	Button iskljuci;
	private Tastatura brojevna;
	private Tastatura slovna;
	private boolean ukljucen;
	private boolean numericka;
	
	public Telefon(Broj b, Color c) {
		brojTelefona = b;
		this.setBackground(c);
		this.setLayout(new BorderLayout());
		populate();
	}

	private void populate() {
//card koji ce da sadrzi tastature - NORTH
		CardLayout cardLayout = new CardLayout();
		Panel cardPanel = new Panel(cardLayout);
		Panel card1 = new Panel();
		Panel card2 = new Panel();
	
		brojevna = new Tastatura(true, this);
		slovna = new Tastatura(false, this);
		
		card1.setLayout(new BorderLayout());
		card2.setLayout(new BorderLayout());
		card1.add(brojevna, BorderLayout.CENTER);
		card2.add(slovna, BorderLayout.CENTER);
		
		cardPanel.add(card1);
		cardPanel.add(card2);
		
		numericka = true;
		add(cardPanel,BorderLayout.NORTH);
		
//imenik - CENTER
		imenik = new Imenik();
		add(imenik,BorderLayout.CENTER);
		
//dugmici - SOUTH
		dodaj = new Button("Dodaj kontakt");
		iskljuci = new Button("Iskljuci telefon");
		Panel south1 = new Panel();
		south1.add(dodaj);
		south1.add(iskljuci);
		
//DUGME ZA DODAVANJE KONTAKTA I PROMENU TASTATURE		
		dodaj.addActionListener((ae)->{
			cardLayout.next(cardPanel);
			String naslov = "";
			String tekst = "";
			if(!numericka) {
				tekst = brojevna.getBroj().getText();
				naslov = slovna.getIme().getText();
				brojevna.getBroj().setText("");
				brojevna.getIme().setText("");
				slovna.getBroj().setText("");
				slovna.getIme().setText("");
				if (tekst.length() < 7) {
					numericka = ! numericka;
					return;
				}
				
				Kontakt k = new Kontakt(naslov, new Broj(tekst).toString());
				imenik.dodaj(k);
				imenik.revalidate();
			}
			numericka = !numericka;
		});

//DUGME ZA ISKLJUCIVANJE TELEFONA
		ukljucen = true;
		iskljuci.addActionListener((a)->{
			if (ukljucen) {
				dodaj.setEnabled(false);
				brojevna.iskljuci();
				slovna.iskljuci();
				iskljuci.setBackground(Color.RED);
			}else {
				dodaj.setEnabled(true);
				brojevna.ukljuci();
				slovna.ukljuci();
				iskljuci.setBackground(dodaj.getBackground());				
			}
			ukljucen = !ukljucen;
		});
		
//DODAVANJE DUGMICA I BROJA TELEFONA		
		Label labela = new Label(brojTelefona.toString());
		labela.setFont(new Font("Arial", Font.BOLD, 14));
		Panel south2 = new Panel();
		Panel south3 = new Panel();
		south2.setLayout(new GridLayout(2, 1));
		south2.add(south1);
		south3.add(labela);
		south2.add(south3);
		add(south2,BorderLayout.SOUTH);
	}
	
	public void azuriraj(boolean b) {
		brojevna.setIme(slovna.getIme());
		slovna.setBroj(brojevna.getBroj());
		brojevna.getIme().revalidate();
		slovna.getBroj().revalidate();
	}
	
	public void stop() {
		slovna.stop();
		brojevna.stop();
	}
}




















