package presentacion;

import java.io.IOException;

import ficheros.Leer;
import negocio.ID3;

public class Main {

	public static void main(String[] args) {
		Leer l = new Leer();
		try {
			l.leerAtributos("AtributosJuego.txt");
			l.leerValores("Juego.txt");
			ID3 id3 = new ID3(l.getTabla());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
