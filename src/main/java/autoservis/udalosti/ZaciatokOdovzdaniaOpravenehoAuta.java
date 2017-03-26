package autoservis.udalosti;

import autoservis.Oprava;
import core.SimulacneJadro;

public class ZaciatokOdovzdaniaOpravenehoAuta extends UdalostServis {

	public ZaciatokOdovzdaniaOpravenehoAuta(double casUdalosti, SimulacneJadro mojaSimulacia, Oprava oprava) {
		super(casUdalosti, mojaSimulacia, oprava);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		getMojaSimulaciaServisu().naplanujUdalost(
				new KoniecOdovzdaniaOpravenehoAuta(
						getCasUdalosti()+getMojaSimulaciaServisu().getCasOdovzdavaniaHotovehoAutaZakaznikovy(),
						getMojaSimulaciaServisu(), getOprava()));

	}

}
