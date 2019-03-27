package negocio;

import java.util.ArrayList;

public class Tabla {
	private ArrayList<Atributo> tabla;
	
	public Tabla() {
		tabla = new ArrayList<Atributo>();
	}

	public ArrayList<Atributo> getTabla(){
		return tabla;
	}
	public void setTabla(ArrayList<Atributo> tabla) {
		for(int i = 0; i < tabla.size(); i++) {
			this.tabla.add(new Atributo(tabla.get(i).getTitulo()));
			this.tabla.get(i).setValores(tabla.get(i).getValores());
		}
	}
	public void addAtributo(Atributo atributo) {
		this.tabla.add(atributo);
	}
	public void removeFila(int pos) {
		for(int i = 0; i < tabla.size(); i++) {
			tabla.get(i).getValores().remove(pos);
		}
	}
}
