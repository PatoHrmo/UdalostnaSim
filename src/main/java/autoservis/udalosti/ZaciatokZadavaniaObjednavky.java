package autoservis.udalosti;

import autoservis.Oprava;
import core.SimulacneJadro;

public class ZaciatokZadavaniaObjednavky extends UdalostServis {

	public ZaciatokZadavaniaObjednavky(double casUdalosti, SimulacneJadro mojaSimulacia, Oprava oprava) {
		super(casUdalosti, mojaSimulacia, oprava);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		getMojaSimulaciaServisu().zamestnajRobotnika1();
		getOprava().setKoniecCakaniaVoFronteZakaznikov(getCasUdalosti());
		getMojaSimulaciaServisu().pridajCasCakaniaZakaznikaVoFronte(getOprava().getDlzkaCakaniaVoFronteZakaznikov());
		
		getMojaSimulaciaServisu().naplanujUdalost(
				new KoniecZadavaniaObjednavky(getCasUdalosti()+getMojaSimulaciaServisu().getCasZadavaniaObjednavky(), getMojaSimulaciaServisu(), getOprava()));

	}

}
