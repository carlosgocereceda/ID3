package negocio;

public class InformacionMerito {
	
	private double total; // a
	private double p;
	private double n;
	private int N_;
	private double r;
	private String merito;
	//Atributos
	Atributo atributo; //El atributo actual
	Atributo res; //El atributo donde está si o no
	
	public InformacionMerito(String merito, double apariciones, Atributo atributo, Atributo res) {
		this.merito = merito;
		this.total = apariciones;
		this.atributo = atributo;
		this.res = res;
	}
	public void calcula() {
		double positivos = 0;
		double negativos = 0;
		for(int i = 0; i < atributo.getValores().size(); i++) {
			this.N_ += 1;
			if(atributo.getValores().get(i).equalsIgnoreCase(this.merito)) {
				if(res.getValores().get(i).equalsIgnoreCase("si"))
					positivos += 1;
				else
					negativos += 1;
			}
		}
		this.p = positivos / this.total;
		this.n = negativos / this.total;
		this.r = this.total / this.N_;
	}
	
	
	public double getP() {
		return p;
	}
	public void setP(double p) {
		this.p = p;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getN() {
		return n;
	}
	public void setN(double n) {
		this.n = n;
	}
	public int getN_() {
		return N_;
	}
	public void setN_(int n_) {
		N_ = n_;
	}
	public double getR() {
		return r;
	}
	public void setR(double r) {
		this.r = r;
	}


	public String getMerito() {
		return merito;
	}


	public void setMerito(String merito) {
		this.merito = merito;
	}
	
	public String toString() {
		return ("nombre " + merito + " total " + total + " p " + p + " n " + n + " N " + N_ + " r " + r);
	}

}
