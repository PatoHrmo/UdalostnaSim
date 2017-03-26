package autoservis.udalosti;

import autoservis.AutoServisSim;
import autoservis.Oprava;
import core.SimulacneJadro;
import core.Udalost;

public abstract class UdalostServis extends Udalost {
	private Oprava oprava;
	
	public UdalostServis(double casUdalosti, SimulacneJadro mojaSimulacia, Oprava oprava) {
		super(casUdalosti, mojaSimulacia);
		this.oprava = oprava;
	}
	protected AutoServisSim getMojaSimulaciaServisu(){
		return (AutoServisSim)this.getMojaSimulacia();
	}
	
	public abstract void execute();
	
	Oprava getOprava() {
		return oprava;
	}
	void setOprava(Oprava oprava) {
		this.oprava = oprava;
	}
}
