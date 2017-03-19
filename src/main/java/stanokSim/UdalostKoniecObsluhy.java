package stanokSim;

import core.SimulacneJadro;

public class UdalostKoniecObsluhy extends UdalostStanku {

	

	public UdalostKoniecObsluhy(double casUdalosti, SimulacneJadro mojaSimulacia) {
		super(casUdalosti, mojaSimulacia);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void execute() {
		getMojaSimulacia().uvolniPredavacku();
		if(!getMojaSimulacia().jeFrontPrazdny()) {
			
			UdalostZaciatokObsluhy zaciatokObsluhy = new UdalostZaciatokObsluhy(getCasUdalosti(), getMojaSimulacia());
			zaciatokObsluhy.setZakaznik(getMojaSimulacia().zoberZakaznikaZFrontu());
			getMojaSimulacia().pridajDlzkuFrontu(getCasUdalosti());
			getMojaSimulacia().naplanujUdalost(zaciatokObsluhy);
		}
		
	}
	public String toString() {
		return super.toString()+" typ udalosti: koniec obsluhy";
	}

}
