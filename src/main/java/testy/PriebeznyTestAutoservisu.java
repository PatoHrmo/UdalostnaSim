package testy;

import autoservis.AutoServisSim;

public class PriebeznyTestAutoservisu {

	

	public static void main(String[] args) {
		AutoServisSim sim = new AutoServisSim(0,false, 20, 20);
		sim.vykonajPocetReplikacii(100);;

	}

}
