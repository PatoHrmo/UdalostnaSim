package autoservis.udalosti;

import autoservis.Oprava;
import core.SimulacneJadro;

public class KoniecZadavaniaObjednavky extends UdalostServis {

	public KoniecZadavaniaObjednavky(double casUdalosti, SimulacneJadro mojaSimulacia, Oprava oprava) {
		super(casUdalosti, mojaSimulacia, oprava);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		getMojaSimulaciaServisu().setPocetPracovnikovZadavajucichObjednavku(getMojaSimulaciaServisu().getPocetPracovnikovZadavajucichObjednavku()-1);
		getOprava().setDlzkaOpravy(getMojaSimulaciaServisu().getCasOpravy());
		getMojaSimulaciaServisu().naplanujUdalost(
				new ZaciatokPrevzatiaAuta(getCasUdalosti(), getMojaSimulaciaServisu(), getOprava()));
		
	}

}
