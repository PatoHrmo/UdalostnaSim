package autoservis.udalosti;

import autoservis.Oprava;
import core.SimulacneJadro;

public class KoniecPreparkovaniaZDielne extends UdalostServis {

	public KoniecPreparkovaniaZDielne(double casUdalosti, SimulacneJadro mojaSimulacia, Oprava oprava) {
		super(casUdalosti, mojaSimulacia, oprava);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		getMojaSimulaciaServisu().naplanujUdalost(
				new ZaciatokOdovzdaniaOpravenehoAuta(getCasUdalosti(), getMojaSimulacia(), getOprava()));

	}

}
