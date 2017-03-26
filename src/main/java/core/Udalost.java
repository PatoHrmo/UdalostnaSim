package core;

public abstract class Udalost implements Comparable<Udalost> {
	private final double casUdalosti;
	private final SimulacneJadro mojaSimulacia;
	
	
	public Udalost(double casUdalosti, SimulacneJadro mojaSimulacia) {
		this.casUdalosti = casUdalosti;
		this.mojaSimulacia = mojaSimulacia;
	}
	protected abstract void execute();
	public int compareTo(Udalost udalost) {
		//FIXME ozaj?
		
		if(casUdalosti<udalost.casUdalosti) {
			return -1;
		} else if (casUdalosti>udalost.casUdalosti) {
			return 1;
		}
		return 0;
	}
	public double getCasUdalosti() {
		return casUdalosti;
	}
	public String toString() {
		return "Cas Udalosti: "+casUdalosti;
	}
	protected SimulacneJadro getMojaSimulacia() {
		return mojaSimulacia;
	}
}
