package stanokSim;

import core.SimulacneJadro;
import core.Udalost;

public abstract class UdalostStanku extends Udalost {
	private Zakaznik zakaznik;
	private SimulaciaStanku mojeJadro;
	public UdalostStanku(double casUdalosti, SimulacneJadro mojaSimulacia) {
		super(casUdalosti, mojaSimulacia);
		mojeJadro = (SimulaciaStanku) mojaSimulacia;
	}
	void setZakaznik(Zakaznik zakaznik) {
		this.zakaznik = zakaznik;
	}
	Zakaznik getZakaznik() {
		return this.zakaznik;
	}
	SimulaciaStanku getMojaSimulacia() {
		return mojeJadro;
	}
	protected abstract void execute();
	
	public String toString() {
		return super.toString();
	}
}
