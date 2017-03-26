package autoservis.udalosti;

import autoservis.Oprava;
import core.SimulacneJadro;

public class PrichodZakaznika extends UdalostServis {

	

	public PrichodZakaznika(double casUdalosti, SimulacneJadro mojaSimulacia, Oprava oprava) {
		super(casUdalosti, mojaSimulacia, oprava);
		
	}

	

	@Override
	public void execute() {
		naplanujPrichodZakaznika();
		
		this.setOprava(new Oprava());
		this.getOprava().setZaciatokCakaniaVoFronteZakaznikov(getCasUdalosti());
		if(getMojaSimulaciaServisu().jeVolnyRobotnik1() && getMojaSimulaciaServisu().getPocetZakaznikovVoFronte()==0
				&& getMojaSimulaciaServisu().getPocetOpravenychAutVoFronte()==0) {
			getMojaSimulaciaServisu().naplanujUdalost(
					new ZaciatokZadavaniaObjednavky(getCasUdalosti(), getMojaSimulaciaServisu(), getOprava()));
		} else {
			getMojaSimulaciaServisu().pridajZakaznikaDoFrontu(getOprava());
		}
	}
	private void naplanujPrichodZakaznika() {
		this.getMojaSimulaciaServisu().naplanujUdalost(
				new PrichodZakaznika(getCasUdalosti()+getMojaSimulaciaServisu().nextCasPrichodu(),
						getMojaSimulaciaServisu(), null));
	}

}
