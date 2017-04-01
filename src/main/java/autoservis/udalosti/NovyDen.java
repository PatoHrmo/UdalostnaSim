package autoservis.udalosti;

import autoservis.Oprava;
import core.SimulacneJadro;

public class NovyDen extends UdalostServis {

	public NovyDen(double casUdalosti, SimulacneJadro mojaSimulacia, Oprava oprava) {
		super(casUdalosti, mojaSimulacia, oprava);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		getMojaSimulaciaServisu().urobStatistikuNaKonciDna();
		getMojaSimulaciaServisu().posliDomovZakaznikovVoFronte();
		getMojaSimulaciaServisu().naplanujUdalost(new NovyDen(getCasUdalosti()+8*60*60, getMojaSimulaciaServisu(), null));
	}

}
