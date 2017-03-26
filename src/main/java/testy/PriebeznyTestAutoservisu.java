package testy;

import autoservis.AutoServisSim;

public class PriebeznyTestAutoservisu {

	

	public static void main(String[] args) {
		AutoServisSim sim = new AutoServisSim(0, 1, 1);
		sim.vykonajReplikaciu(10000000);

	}

}
