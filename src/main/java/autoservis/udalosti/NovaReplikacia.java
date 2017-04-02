package autoservis.udalosti;

import autoservis.Oprava;
import core.SimulacneJadro;

public class NovaReplikacia extends UdalostServis {
	private static int pocet = 0;
	public NovaReplikacia(double casUdalosti, SimulacneJadro mojaSimulacia, Oprava oprava) {
		super(casUdalosti, mojaSimulacia, oprava);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		getMojaSimulaciaServisu().vyhodnotStatistikyReplikacie();
		getMojaSimulaciaServisu().resetujStatistiky();
		getMojaSimulaciaServisu().naplanujUdalost(new NovaReplikacia(getCasUdalosti()+8*60*60*90, getMojaSimulaciaServisu(), null));
		pocet++;
		if(pocet%100==0)
		getMojaSimulaciaServisu().refreshGUI();
	}

}
