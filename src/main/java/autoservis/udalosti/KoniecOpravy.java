package autoservis.udalosti;

import autoservis.Oprava;
import core.SimulacneJadro;

public class KoniecOpravy extends UdalostServis {

	public KoniecOpravy(double casUdalosti, SimulacneJadro mojaSimulacia, Oprava oprava) {
		super(casUdalosti, mojaSimulacia, oprava);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		getMojaSimulaciaServisu().uvolniRobotnika2();
		// naplanuj dalsiu opravu ak su pokazene auta vo fronte
		if(getMojaSimulaciaServisu().getPocetPokazenychAutVoFronte()>1) {
			getMojaSimulaciaServisu().naplanujUdalost(
					new ZaciatokOpravy(getCasUdalosti(), getMojaSimulaciaServisu(),
							getMojaSimulaciaServisu().zoberPokazeneAutoZFrontu()));
		}
		// odparkuj hotove auto ak sa da, ak nie tak ho daj do frontu opravenych
		if(getMojaSimulaciaServisu().jeVolnyRobotnik1()){
			getMojaSimulaciaServisu().naplanujUdalost(
					new ZaciatokPreparkovaniaZDielne(getCasUdalosti(), getMojaSimulaciaServisu(), getOprava()));
		} else {
			getMojaSimulaciaServisu().pridajAutoDoFrontuOpravenych(getOprava());
		}

	}

}
