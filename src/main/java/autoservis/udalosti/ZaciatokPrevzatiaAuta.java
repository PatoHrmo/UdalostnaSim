package autoservis.udalosti;

import autoservis.Oprava;
import core.SimulacneJadro;

public class ZaciatokPrevzatiaAuta extends UdalostServis {

	public ZaciatokPrevzatiaAuta(double casUdalosti, SimulacneJadro mojaSimulacia, Oprava oprava) {
		super(casUdalosti, mojaSimulacia, oprava);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		getMojaSimulaciaServisu().setPocetPracovnikovPrevazajucichAutaOdZakaznikov(getMojaSimulaciaServisu().getPocetPracovnikovPrevazajucichAutaOdZakaznikov()+1);
		getMojaSimulaciaServisu().naplanujUdalost(
				new KoniecPrevzatiaAuta(getCasUdalosti()+getMojaSimulaciaServisu().getCasPrevzatiaAutaOdZakaznika(),
						getMojaSimulaciaServisu(), getOprava()));

	}

}
