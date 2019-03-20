package negocio;

import java.util.ArrayList;

public class Atributo {
	private String titulo;
	private ArrayList<String> valores;
	
	public Atributo(String titulo) {
		this.titulo = titulo;
		valores = new ArrayList<String>();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public ArrayList<String> getValores() {
		return valores;
	}

	public void setValores(ArrayList<String> valores) {
		this.valores = valores;
	}
	public void addValor(String valor) {
		valores.add(valor);
	}

}
