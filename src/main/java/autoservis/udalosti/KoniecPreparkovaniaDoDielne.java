package autoservis.udalosti;

import autoservis.Oprava;
import core.SimulacneJadro;

public class KoniecPreparkovaniaDoDielne extends UdalostServis {

	public KoniecPreparkovaniaDoDielne(double casUdalosti, SimulacneJadro mojaSimulacia, Oprava oprava) {
		super(casUdalosti, mojaSimulacia, oprava);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		
		getMojaSimulaciaServisu().uvolniRobotnika1();
		// planovanie zacatia opravy ak je robotnik volny
		if(getMojaSimulaciaServisu().jeVolnyRobotnik2() && getMojaSimulaciaServisu().getPocetPokazenychAutVoFronte()==0) {
			getMojaSimulaciaServisu().naplanujUdalost(
					new ZaciatokOpravy(getCasUdalosti(), getMojaSimulaciaServisu(), getOprava()));
		} else {
			getMojaSimulaciaServisu().pridajAutoDoFrontuPokazenych(getOprava());
		}
		// ak je hotove auto zacneme ho odovzdavat
		if(getMojaSimulaciaServisu().getPocetOpravenychAutVoFronte()>0) {
			getMojaSimulaciaServisu().naplanujUdalost(
					new ZaciatokPreparkovaniaZDielne(getCasUdalosti(),
							getMojaSimulaciaServisu(), getMojaSimulaciaServisu().zoberOpraveneAutoZFrontu()));
			return;
		}
		// ak je neaky zakaznik v rade zacneme ho obsluhovat
		if(getMojaSimulaciaServisu().getPocetZakaznikovVoFronte()>0) {
			getMojaSimulaciaServisu().naplanujUdalost(
					new ZaciatokZadavaniaObjednavky(getCasUdalosti(), getMojaSimulaciaServisu(),
							getMojaSimulaciaServisu().zoberZakaznikaZfrontu()));
		}

	}

}
