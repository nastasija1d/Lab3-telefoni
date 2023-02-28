package paket;

import java.awt.Button;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tastatura extends Panel implements Runnable {
	
	private Label broj;
	private Label ime;
	private Button[][] dugmici;
	boolean numericka;
	private Telefon telefon;
	private Thread nit = new Thread(this);
	private Button prethodno;
	private String string;
	private int count;
	
	
	public Tastatura(boolean b,Telefon t) {
		numericka = b;
		telefon = t;
		dugmici = new Button[4][3];
		broj = new Label();
		ime = new Label();
		populate();		
	}

	private void populate() {
		this.setLayout(new GridLayout(2, 1));
		Panel labelePanel = new Panel(new GridLayout(2, 1));
		broj = new Label();
		ime = new Label();
		broj.setFont(new Font("Arial", Font.BOLD, 16));
		labelePanel.add(broj);
		ime.setFont(new Font("Arial", Font.BOLD, 14));
		labelePanel.add(ime);
		this.add(labelePanel);
		
		
		Panel dugmiciPanel = new Panel(new GridLayout(4,3));
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				Button dugme = new Button();
				dugmiciPanel.add(dugme);
				dugmici[i][j] = dugme;
//KAD SE KLIKNE DUGME
				dugme.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (numericka) {
							String str = broj.getText();
							broj.setText(str + dugme.getLabel());
							broj.revalidate();
							telefon.azuriraj(numericka);
						
						}else {										//ako je slovna tastatura
							//System.out.println("\nSlovna tastatura");	
							if(nit.isAlive()) {
								//System.out.println("Vec smo kliknuli dugme");
									if (dugme.equals(prethodno)) {	//ako smo dva puta kliknuli isto dugme
										//System.out.println("Dva puta je kliknuto isto dugme");
										nit.interrupt();
										count++;
										nit = new Thread(Tastatura.this);
										nit.start();
										
									}else {							//ako smo kliknuli jedno pa drugo dugme
										//System.out.println("Kliknuto je jedno pa drugo dugme");
										nit.interrupt();
										int x = string.length();
										if(x > 1) ime.setText(ime.getText() + string.charAt(count % x));
										if(x == 1) ime.setText(ime.getText() + "_");
										ime.revalidate();
										telefon.azuriraj(!numericka);
										//System.out.println("Ime: " + ime.getText());
										//System.out.println("---------------------");
										nit = new Thread(Tastatura.this);
										nit.start();
										prethodno = dugme;
										string = dugme.getLabel();
										count = 0;
									}
								
							}else {									//ako nit nije vec aktivna - prvi put klikcemo 
								//System.out.println("Nismo prethodno kliknuli dugme");
								nit = new Thread(Tastatura.this);
								nit.start();
								prethodno = dugme;
								string = dugme.getLabel();
								count = 0;
							}
						}
					}//zatvara funkciju
				});//zatvara action listener
			}//zatvara j petlju
		}//zatvara i petlju
		this.add(dugmiciPanel);
		if(numericka) {
			dugmici[0][0].setLabel("1");
			dugmici[0][1].setLabel("2");
			dugmici[0][2].setLabel("3");
			dugmici[1][0].setLabel("4");
			dugmici[1][1].setLabel("5");
			dugmici[1][2].setLabel("6");
			dugmici[2][0].setLabel("7");
			dugmici[2][1].setLabel("8");
			dugmici[2][2].setLabel("9");
			dugmici[3][0].setLabel("*");
			dugmici[3][1].setLabel("0");
			dugmici[3][2].setLabel("+");
		}else {
			dugmici[0][0].setLabel("");
			dugmici[0][1].setLabel("ABC");
			dugmici[0][2].setLabel("DEF");
			dugmici[1][0].setLabel("GHI");
			dugmici[1][1].setLabel("JKL");
			dugmici[1][2].setLabel("MNO");
			dugmici[2][0].setLabel("PQRS");
			dugmici[2][1].setLabel("TUV");
			dugmici[2][2].setLabel("WXYZ");
			dugmici[3][0].setLabel("");
			dugmici[3][1].setLabel("_");
			dugmici[3][2].setLabel("");
		}
		
	}

	public void iskljuci() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				dugmici[i][j].setEnabled(false);
			}
		}
	}
	
	public void ukljuci() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				dugmici[i][j].setEnabled(true);
			}
		}
	}
	
	public void stop() {
		if (nit.isAlive()) nit.interrupt();
	}

	public Label getBroj() {
		return broj;
	}

	public void setBroj(Label broj) {
		this.broj.setText(broj.getText());
	}

	public Label getIme() {
		return ime;
	}

	public void setIme(Label ime) {
		this.ime.setText(ime.getText());
	}

	@Override
	public void run() {
		try {
			//System.out.println("Nit ce sad da spava");
			//telefon.dodaj.setEnabled(false);
			Thread.sleep(1000);
			//System.out.println("Nit se probudila");
		} catch (Exception e) {
			//System.out.println("Nit je prekinuta");
			return;}
		int x = string.length();
		if(x > 1) ime.setText(ime.getText() + string.charAt(count % x));
		if(x == 1) ime.setText(ime.getText() + "_");
		//System.out.println("Ime: " + ime.getText());
		//System.out.println("---------------------");
		ime.revalidate();
		telefon.azuriraj(!numericka);
		//telefon.dodaj.setEnabled(true);
	}
	

}
