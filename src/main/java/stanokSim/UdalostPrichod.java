package stanokSim;

import core.SimulacneJadro;

public class UdalostPrichod extends UdalostStanku {

	public UdalostPrichod(double casUdalosti, SimulacneJadro mojaSimulacia) {
		super(casUdalosti, mojaSimulacia);
		
	}

	@Override
	protected void execute() {
		naplanujDalsiPrichod();
		Zakaznik zakaznikKtoryPraveVosielDoSystemu = new Zakaznik(getCasUdalosti());
		if(getMojaSimulacia().jePredavackaVolna()) {
			UdalostZaciatokObsluhy zaciatok = new UdalostZaciatokObsluhy(getCasUdalosti(), getMojaSimulacia());
			zaciatok.setZakaznik(zakaznikKtoryPraveVosielDoSystemu);
			getMojaSimulacia().naplanujUdalost(zaciatok);
		} else {
			getMojaSimulacia().pridajZakaznikaDoFrontu(zakaznikKtoryPraveVosielDoSystemu);
			getMojaSimulacia().pridajDlzkuFrontu(getCasUdalosti());
		}
		

	}

	private void naplanujDalsiPrichod() {
		UdalostPrichod prichod = 
		 new UdalostPrichod(this.getCasUdalosti()+getMojaSimulacia().getCasZaKedyPrideDalsiZakaznik(), getMojaSimulacia());
		getMojaSimulacia().naplanujUdalost(prichod);
	}
	public String toString() {
		return super.toString()+" typ udalosti: prichod";
	}

}
