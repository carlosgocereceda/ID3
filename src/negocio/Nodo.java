package negocio;

import java.util.ArrayList;

public class Nodo {

	private Nodo padre;
	private Tabla tabla;
	private String camino;
	private String atributo;
	private ArrayList<Nodo> hijos;
	
	public Nodo() {
		padre = null;
		tabla = null;
		camino = "";
		atributo = "";
		hijos = new ArrayList<>();
	}

	public Nodo getPadre() {
		return padre;
	}

	public void setPadre(Nodo padre) {
		this.padre = padre;
	}

	public Tabla getTabla() {
		return tabla;
	}

	public void setTabla(Tabla tabla) {
		this.tabla = tabla;
	}

	public String getCamino() {
		return camino;
	}

	public void setCamino(String camino) {
		this.camino = camino;
	}

	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	public ArrayList<Nodo> getHijos() {
		return hijos;
	}

	public void setHijos(ArrayList<Nodo> hijos) {
		this.hijos = hijos;
	}
	public void addHijo(Nodo hijo) {
		this.hijos.add(hijo);
	}
	
	
}
