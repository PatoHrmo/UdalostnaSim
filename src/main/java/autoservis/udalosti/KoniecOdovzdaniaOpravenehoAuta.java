package autoservis.udalosti;

import autoservis.Oprava;
import core.SimulacneJadro;

public class KoniecOdovzdaniaOpravenehoAuta extends UdalostServis {

	public KoniecOdovzdaniaOpravenehoAuta(double casUdalosti, SimulacneJadro mojaSimulacia, Oprava oprava) {
		super(casUdalosti, mojaSimulacia, oprava);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		
		getMojaSimulaciaServisu().uvolniRobotnika1();
		getOprava().setKoniecCakaniaNaOpravuOdovzdanehoAuta(getCasUdalosti());
		//getOprava().vypis();
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
