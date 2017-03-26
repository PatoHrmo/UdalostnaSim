package autoservis.udalosti;

import autoservis.Oprava;
import core.SimulacneJadro;

public class ZaciatokPreparkovaniaZDielne extends UdalostServis {

	public ZaciatokPreparkovaniaZDielne(double casUdalosti, SimulacneJadro mojaSimulacia, Oprava oprava) {
		super(casUdalosti, mojaSimulacia, oprava);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		getMojaSimulaciaServisu().zamestnajRobotnika1();
		getMojaSimulaciaServisu().naplanujUdalost(
				new KoniecPreparkovaniaZDielne(getCasUdalosti()+getMojaSimulaciaServisu().getCasPreparkovaniaZDielne(),
						getMojaSimulaciaServisu(), getOprava()));

	}

}
