package stanokSim;

import core.SimulacneJadro;

public class UdalostZaciatokObsluhy extends UdalostStanku {

	public UdalostZaciatokObsluhy(double casUdalosti, SimulacneJadro mojaSimulacia) {
		super(casUdalosti, mojaSimulacia);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void execute() {
		getMojaSimulacia().zamestnajPredavacku();
		UdalostKoniecObsluhy koniecObsluhy = 
				new UdalostKoniecObsluhy(getCasUdalosti()+getMojaSimulacia().getCasKolkoBudeObsluhovat(), getMojaSimulacia());
		koniecObsluhy.setZakaznik(this.getZakaznik());
		getMojaSimulacia().naplanujUdalost(koniecObsluhy);
		this.getZakaznik().setCasZacatiaObsluhy(getCasUdalosti());
		getMojaSimulacia().pridajCakaciuDobuZakaznika(this.getZakaznik());
	}
	public String toString() {
		return super.toString()+" typ udalosti: zaciatokObsluhy";
	}

}
