package presentacion;

import java.io.IOException;
import java.util.ArrayList;

import ficheros.Leer;
import negocio.ID3;
import negocio.Nodo;

public class Main {

	public static void main(String[] args) {
		Leer l = new Leer();
		try {
			l.leerAtributos("AtributosJuego.txt");
			l.leerValores("Juego.txt");
			ID3 id3 = new ID3();
			id3.algoritmo(l.getTabla(), null);
			ArrayList<Nodo> arbol = id3.getArbol();
			for(int i = 0; i < arbol.size(); i++) {
				String padre = "";
				if(arbol.get(i).getPadre() != null)
					padre = arbol.get(i).getPadre().getAtributo() + "->" + arbol.get(i).getPadre().getCamino();
				System.out.println(arbol.get(i).getCamino() + " " + arbol.get(i).getAtributo() + " padre: " + padre);
			}
			//Vista vista = new Vista(arbol);
			System.out.println("PAUSA");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
