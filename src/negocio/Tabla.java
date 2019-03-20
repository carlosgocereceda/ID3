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
		this.tabla = tabla;
	}
	public void addAtributo(Atributo atributo) {
		this.tabla.add(atributo);
	}
}
