package negocio;

import java.util.HashMap;
import java.util.Map;

public class ID3 {
	
	public ID3(Tabla tabla) {
		//Le paso el merito que quiero calcular y la ultima columna donde dice si o no
		merito(tabla.getTabla().get(0), tabla.getTabla().get(tabla.getTabla().size()-1));
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
		for (Map.Entry<String, Integer> entry : valoresDistintos.entrySet()) {
		    System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue());
		    InformacionMerito informacion = new InformacionMerito(entry.getKey(), entry.getValue(), atributo, res);
		    informacion.calcula();
		    System.out.println(informacion.toString());
		}
		System.out.println("PAUSA");
		return 0;
	}

}
