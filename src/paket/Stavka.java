package paket;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

public class Stavka extends Panel {
	private Label naslov;
	private Label text;
	
	public Stavka(String n, String t) {
		naslov = new Label(n);
		text = new Label(t);
		naslov.setFont(new Font("Arial", Font.BOLD, 12));
		text.setFont(new Font("Arial",Font.PLAIN ,10));
		this.setLayout(new GridLayout(2, 1));
		add(naslov);
		add(text);
	}

	public void setNaslov(Label naslov) {
		this.naslov.setText(naslov.getText());
		naslov.revalidate();
	}
	
	public void setNaslov(String n) {
		this.naslov.setText(n);
		naslov.revalidate();
	}

	public Label getNaslov() {
		return naslov;
	}

	public Label getText() {
		return text;
	}

}
