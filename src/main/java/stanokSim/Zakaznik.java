package stanokSim;

public class Zakaznik {
	private double casPrichoduDoFronty;
	private double casZacatiaObsluhy;
	
	public Zakaznik(double casPrichoduDoFronty) {
		this.casPrichoduDoFronty = casPrichoduDoFronty;
	}
	
	public void setCasZacatiaObsluhy(double casZacatiaObsluhy) {
		this.casZacatiaObsluhy = casZacatiaObsluhy;
	}
	
	public double getCasCakaniaVoFronte() {
		double casCakaniaVoFronte = casZacatiaObsluhy-casPrichoduDoFronty;
		return casCakaniaVoFronte<0 ? -1 : casCakaniaVoFronte;
	}
	
}
