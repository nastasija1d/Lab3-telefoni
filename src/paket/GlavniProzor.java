package paket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GlavniProzor extends Frame {

	Telefon telefon;
	
	public GlavniProzor() {
		setBounds(700, 200, 300, 600);
		telefon = new Telefon(new Broj("+381605556979"), Color.LIGHT_GRAY);
		add(telefon,BorderLayout.CENTER);
		
		setVisible(true);
		setTitle("Telefoni");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				telefon.stop();
			}
	
		});
	}
	
	
	public static void main(String arg[]) {
		new GlavniProzor();
	}
}
