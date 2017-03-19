package testy;

import stanokSim.SimulaciaStanku;

public class Test {

	public static void main(String[] args) {
		SimulaciaStanku sim = new SimulaciaStanku(0, 0);
		sim.vykonajReplikaciu(888888888);
		System.out.println(sim.getPriemernyCasCakaniaVoFronte());
		System.out.println(sim.getPriemernaDlzkaFrontu());
	}

}
