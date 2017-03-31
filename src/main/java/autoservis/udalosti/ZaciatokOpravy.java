package autoservis.udalosti;

import autoservis.Oprava;
import core.SimulacneJadro;

public class ZaciatokOpravy extends UdalostServis {

	public ZaciatokOpravy(double casUdalosti, SimulacneJadro mojaSimulacia, Oprava oprava) {
		super(casUdalosti, mojaSimulacia, oprava);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		
		getMojaSimulaciaServisu().zamestnajRobotnika2();
		getMojaSimulaciaServisu().naplanujUdalost(
				new KoniecOpravy(getCasUdalosti()+getOprava().getDlzkaOpravy(), getMojaSimulaciaServisu(), getOprava()));

	}

}
