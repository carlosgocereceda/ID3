package negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ID3 {
	
	private ArrayList<Nodo> arbol = new ArrayList<Nodo>();
	
	public void algoritmo(Tabla tabla, Nodo padre) {
		//Le paso el atributo que quiero calcular y la ultima columna donde dice si o no
		double meritoMenor = Double.POSITIVE_INFINITY;
		String meritoMenorS = "";
		for(int i = 0; i < tabla.getTabla().size()-1; i++) {
			double aux = merito(tabla.getTabla().get(i), tabla.getTabla().get(tabla.getTabla().size()-1));
			System.out.println("--- " + tabla.getTabla().get(i).getTitulo() + " " + aux);
			if(aux < meritoMenor) {
				meritoMenor = aux;
				meritoMenorS = tabla.getTabla().get(i).getTitulo();
				System.out.println("He elegido " + tabla.getTabla().get(i).getTitulo() + " " + aux);
			}
		}
		//ArrayList<Tabla> tablas = construyeTablas(tabla, meritoMenorS);
		ArrayList<Nodo> nodos = construyeTablas(tabla, meritoMenorS);
		for(int i = 0; i < nodos.size(); i++) {
			nodos.get(i).setPadre(padre);
			arbol.add(nodos.get(i));
		}
		for(int i = 0; i < nodos.size(); i++) {
			if(nodos.get(i).getAtributo().equalsIgnoreCase("Viento")) {
				System.out.println("HUMEDAD");
			}
			if(!decision(nodos.get(i).getTabla()))
				algoritmo(nodos.get(i).getTabla(), nodos.get(i));
		}
		
	}
	
	private double merito(Atributo atributo, Atributo res) {
		HashMap<String, Integer> valoresDistintos = new HashMap<>();
		//Cuento el número de apariciones
		for(int i = 0; i < atributo.getValores().size(); i++) {
			if(!valoresDistintos.containsKey(atributo.getValores().get(i))) {
				valoresDistintos.put(atributo.getValores().get(i), 1);
			}
			else{
				valoresDistintos.put(atributo.getValores().get(i), valoresDistintos.get(atributo.getValores().get(i)) + 1);
			}
		}
		double solMerito = 0.0;
		for (Map.Entry<String, Integer> entry : valoresDistintos.entrySet()) {
		    InformacionMerito informacion = new InformacionMerito(entry.getKey(), entry.getValue(), 
		    		atributo, res);
		    informacion.calcula();
		    solMerito += informacion.getR() * infor(informacion.getP(), informacion.getN());
		}
		return solMerito;
	}

	private double infor(double p, double n) {
		double pValue = 0.0, nValue = 0.0;
		if(p != 0)
			pValue = -p * (Math.log(p)/Math.log(2));
		if(n != 0)
			nValue = -n * (Math.log(n)/Math.log(2));
		return pValue + nValue;
	}
	
	public ArrayList<Nodo> construyeTablas(Tabla tabla, String atributo) {
		ArrayList<Tabla> tablas = new ArrayList<Tabla>();
		ArrayList<Nodo> nodos = new ArrayList<>();
		Atributo atributoBorrar = null;
		ArrayList<String> valores = new ArrayList<>();
		for(int i = 0; i < tabla.getTabla().size(); i++) {
			if(tabla.getTabla().get(i).getTitulo().equalsIgnoreCase(atributo)) {
				atributoBorrar = tabla.getTabla().get(i);
			}	
		}
		//Aquí miro cuantos valores distintos hay Ej: C1, C2, C3
		for(int i = 0; i < atributoBorrar.getValores().size();i++ ) {
			if(!valores.contains(atributoBorrar.getValores().get(i))) {
				valores.add(atributoBorrar.getValores().get(i));
			}
		}
		//Ahora construyo una tabla para cada valor de C1, C2, C3
		for(int i = 0; i < valores.size(); i++) {
			ArrayList<Integer> posBorrar= new ArrayList<>();
			Tabla tablaNueva = new Tabla();
			tablaNueva.setTabla(new ArrayList<Atributo>(tabla.getTabla()));
			for(int j = 0; j < atributoBorrar.getValores().size(); j++) {
				//A la tabla nueva le voy a borrar todos los valores que no esten en la misma fila
				if(!atributoBorrar.getValores().get(j).equalsIgnoreCase(valores.get(i))) {
					//Aquí guardo las posiciones que cumplen que son C1...
					posBorrar.add(j);
				}
			}
			//Los borro de atras a delante para no moverlos
			for(int j = posBorrar.size()-1; j >= 0; j--) {
				tablaNueva.removeFila(posBorrar.get(j));
			}
			for(int j = 0; j < tablaNueva.getTabla().size(); j++) {
				if(tablaNueva.getTabla().get(j).getTitulo().equalsIgnoreCase(atributo)) {
					tablaNueva.getTabla().remove(j);
				}
			}
			tablas.add(tablaNueva);
			Nodo nodo = new Nodo();
			nodo.setTabla(tablaNueva);
			nodo.setCamino(valores.get(i));
			nodo.setAtributo(atributo);
			nodos.add(nodo);
		}
		return nodos;
	}
	private boolean decision(Tabla tabla) {
		//Aqui miro si todos son sies o noes
		int res = -1; //0 -> No 1 -> Si
		for(int i = 0; i < tabla.getTabla().get(tabla.getTabla().size()-1).getValores().size(); i++) {
			if(tabla.getTabla().get(tabla.getTabla().size()-1).getValores().get(i).equalsIgnoreCase("si")) {
				if(res == -1) res = 1;
				else if(res == 0) return false;
			}
			else {
				if(res == -1) res = 0;
				else if(res == 1) return false;
			}
		}
		return true;
	}
	public ArrayList<Nodo> getArbol(){
		return this.arbol;
	}
}
