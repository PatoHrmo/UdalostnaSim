package core;


import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public abstract class SimulacneJadro {
	private double simulacnyCas;
	private boolean prebieha;
	private double trvanieSimulacie;
	private boolean turboMod;
	private PriorityQueue<Udalost> kalendarUdalosti;
	private boolean pauza;
	private List<PozorovatelSimulacie> pozorovatelia;
	private int dlzkaSpanku;
	private int pocetSekundSimulacnehoCasuMedziAnimaciamy;
	public SimulacneJadro(double simulacnyCas, boolean turboMod) {
		this.simulacnyCas = simulacnyCas;
		prebieha = true;
		kalendarUdalosti = new PriorityQueue<Udalost>();
		trvanieSimulacie=0;
		pauza = false;
		pozorovatelia = new LinkedList<>();
		this.turboMod = turboMod;
		dlzkaSpanku = 1000;
		pocetSekundSimulacnehoCasuMedziAnimaciamy=5;
	}
	/**
	 * vykoná pokus danného monte carla NEIKREMENTOVAT pocet iteracii
	 */
    protected void vykonajUdalostnuSimulaciu(double doKedy){
    	nastartujSimulaciu();
    	if(!turboMod) {
    		naplanujSystemovuUdalostAnimacie();
    	}
    	Udalost pomUdalost;
    	while(!kalendarUdalosti.isEmpty() && simulacnyCas<=doKedy && prebieha) {
    		while(pauza) {
    			try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					System.out.println("chyba v pauze v sim jadre");
					e.printStackTrace();
				}
    		}
    		pomUdalost = kalendarUdalosti.poll();
    		trvanieSimulacie+=pomUdalost.getCasUdalosti()-simulacnyCas;
    		simulacnyCas = pomUdalost.getCasUdalosti();
    		pomUdalost.execute();
    		if(!turboMod)refreshGUI();
    	}
    }
    private void naplanujSystemovuUdalostAnimacie() {
    	naplanujUdalost(new AnimacnaUdalost(pocetSekundSimulacnehoCasuMedziAnimaciamy, this, dlzkaSpanku));
	}
	protected abstract void nastartujSimulaciu();
	public void naplanujUdalost(Udalost udalost) {
    	if(udalost.getCasUdalosti()<simulacnyCas) {
    		System.out.println("udalost sa mala vyskytnut v minulosti program konci");
    		System.exit(1);
    	}
    	kalendarUdalosti.add(udalost);
    }
    
	public double getSimulacnyCas() {
		return simulacnyCas;
	}
	public double getTrvanieSimulacie() {
		return trvanieSimulacie;
	}
	public void pozastav() {
		pauza= true;
	} 
	public void pokracuj() {
		pauza = false;
	}
	public void zastav() {
		prebieha = false;
	}
	public void pridajPozorovatela(PozorovatelSimulacie pozorovatel) {
		pozorovatelia.add(pozorovatel);
	}
	public void refreshGUI() {
		for(PozorovatelSimulacie pozorovatel : pozorovatelia) {
			pozorovatel.refresh(this);
		}
	}
	public boolean isTurboMode() {
		return turboMod;
	}
	public void nastavRychlost(int cas) {
		this.dlzkaSpanku = cas;
	}
	class AnimacnaUdalost extends Udalost {
		private int dobaSpanku;
		public AnimacnaUdalost(double casUdalosti, SimulacneJadro mojaSimulacia, int dobaSpankuVMilisekundach) {
			super(casUdalosti, mojaSimulacia);
			dobaSpanku = dobaSpankuVMilisekundach;
		}

		@Override
		protected void execute() {
			naplanujUdalost(new AnimacnaUdalost(pocetSekundSimulacnehoCasuMedziAnimaciamy+getSimulacnyCas(), getMojaSimulacia(), dlzkaSpanku));
			try {
				refreshGUI();
				Thread.sleep(dobaSpanku);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
