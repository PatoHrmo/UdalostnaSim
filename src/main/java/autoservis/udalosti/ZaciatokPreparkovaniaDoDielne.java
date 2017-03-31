package autoservis.udalosti;

import autoservis.Oprava;
import core.SimulacneJadro;

public class ZaciatokPreparkovaniaDoDielne extends UdalostServis {

	public ZaciatokPreparkovaniaDoDielne(double casUdalosti, SimulacneJadro mojaSimulacia, Oprava oprava) {
		super(casUdalosti, mojaSimulacia, oprava);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		
		getMojaSimulaciaServisu().naplanujUdalost(
				new KoniecPreparkovaniaDoDielne(getCasUdalosti()+getMojaSimulaciaServisu().getCasPreparkovaniaDoDielne(),
						getMojaSimulaciaServisu(), getOprava()));

	}

}
