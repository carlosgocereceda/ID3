package presentacion;

import java.io.IOException;

import ficheros.Leer;

public class Main {

	public static void main(String[] args) {
		Leer l = new Leer();
		try {
			l.leerAtributos("AtributosJuego.txt");
			l.leerValores("Juego.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
