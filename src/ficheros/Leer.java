package ficheros;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import negocio.Atributo;
import negocio.Tabla;

public class Leer {

	private Tabla tabla;
	
	public Leer() {
		this.tabla = new Tabla();
	}
	
	public void leerAtributos(String fichero) throws IOException {
		String cadena;
		FileReader f = new FileReader(fichero);
		BufferedReader b = new BufferedReader(f);
		while ((cadena = b.readLine()) != null) {
			String[] atributos = cadena.split(",");
			for(int i = 0; i < atributos.length; i++) {
				Atributo atributo = new Atributo(atributos[i]);
				tabla.addAtributo(atributo);
			}
			System.out.println(cadena);
		}
		b.close();
	}
	public void leerValores(String fichero) throws IOException {
		String cadena;
		FileReader f = new FileReader(fichero);
		BufferedReader b = new BufferedReader(f);
		while ((cadena = b.readLine()) != null) {
			String[] valores = cadena.split(",");
			for(int i = 0; i < valores.length; i++) {
				if(!valores[i].equalsIgnoreCase("")) {
					Atributo atributo = tabla.getTabla().get(i);
					atributo.addValor(valores[i]);
				}		
			}
			System.out.println(cadena);
		}
		b.close();
	}
	public Tabla getTabla() {
		return this.tabla;
	}

}
