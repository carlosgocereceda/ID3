package negocio;

import java.util.HashMap;
import java.util.Map;

public class ID3 {
	
	public ID3(Tabla tabla) {
		//Le paso el atributo que quiero calcular y la ultima columna donde dice si o no
		double meritoMenor = 0.0;
		String meritoMenorS = "";
		for(int i = 0; i < tabla.getTabla().size()-1; i++) {
			double aux = merito(tabla.getTabla().get(i), tabla.getTabla().get(tabla.getTabla().size()-1));
			System.out.println("--- " + tabla.getTabla().get(i).getTitulo() + " " + aux);
			if(aux < meritoMenor) {
				meritoMenor = aux;
				meritoMenorS = tabla.getTabla().get(i).getTitulo();
				
			}
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
		    System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue());
		    InformacionMerito informacion = new InformacionMerito(entry.getKey(), entry.getValue(), 
		    		atributo, res);
		    informacion.calcula();
		    solMerito += informacion.getR() * infor(informacion.getP(), informacion.getN());
		}
		 System.out.println("Merito " + solMerito);
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
}
