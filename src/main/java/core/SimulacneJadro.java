package core;


import java.util.PriorityQueue;

public abstract class SimulacneJadro {
	private int pocetIteracii;
	private double simulacnyCas;
	private boolean prebieha;
	private double trvanieSimulacie;
	private PriorityQueue<Udalost> kalendarUdalosti;
	public SimulacneJadro(double simulacnyCas) {
		pocetIteracii=0;
		this.simulacnyCas = simulacnyCas;
		prebieha = true;
		kalendarUdalosti = new PriorityQueue<Udalost>();
		trvanieSimulacie=0;
	}
	/**
	 * vykoná pokus danného monte carla NEIKREMENTOVAT pocet iteracii
	 */
    void vykonajUdalostnuSimulaciu(double doKedy){
    	nastartujSimulaciu();
    	Udalost pomUdalost;
    	long pocetUdalosti = 0;
    	while(!kalendarUdalosti.isEmpty() && simulacnyCas<doKedy && prebieha) {
    		pocetUdalosti++;
    		if(pocetUdalosti%100000000==0) {
    			System.out.println(pocetUdalosti);
    		}
    		pomUdalost = kalendarUdalosti.poll();
    		trvanieSimulacie+=pomUdalost.getCasUdalosti()-simulacnyCas;
    		simulacnyCas = pomUdalost.getCasUdalosti();
    		//vypisStav();
    		//System.out.println("momentalne sa spracuvava udalost "+pomUdalost.toString());
    		
    		pomUdalost.execute();
    		//System.out.println("po vykonani tejto udalosti vyzera kalendar takto");
    		//System.out.println();
    		//vypisStav();
    		//System.out.println("-------------------------------");
    	}
    }
    protected abstract void nastartujSimulaciu();
	public void naplanujUdalost(Udalost udalost) {
    	if(udalost.getCasUdalosti()<simulacnyCas) {
    		System.out.println("udalost sa mala vyskytnut v minulosti program konci");
    		System.exit(1);
    	}
    	kalendarUdalosti.add(udalost);
    }
    public void vykonajReplikaciu(double dokedy){
    	vykonajUdalostnuSimulaciu(dokedy);
    	pocetIteracii++;
    }
	public int getPocetIteracii() {
		return pocetIteracii;
	}
	public double getSimulacnyCas() {
		return simulacnyCas;
	}
	public void vypisStav() {
		System.out.println("kalendar udalosti v case "+getSimulacnyCas()+" vyzera takto:");
		for(Udalost udalost : kalendarUdalosti) {
			System.out.println(udalost.toString());
		}
		System.out.println();
	}
	public double getTrvanieSimulacie() {
		return trvanieSimulacie;
	}
}
