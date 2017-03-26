package autoservis.udalosti;

import autoservis.Oprava;
import core.SimulacneJadro;

public class KoniecPrevzatiaAuta extends UdalostServis {

	public KoniecPrevzatiaAuta(double casUdalosti, SimulacneJadro mojaSimulacia, Oprava oprava) {
		super(casUdalosti, mojaSimulacia, oprava);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		getOprava().setZaciatokCakaniaNaOpravuOdovzdanehoAuta(getCasUdalosti());
		getMojaSimulaciaServisu().naplanujUdalost(
				new ZaciatokPreparkovaniaDoDielne(getCasUdalosti(), getMojaSimulaciaServisu(), getOprava()));

	}

}
