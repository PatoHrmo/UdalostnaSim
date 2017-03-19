package stanokSim;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import core.SimulacneJadro;
import util.ExpGenerator;

public class SimulaciaStanku extends SimulacneJadro {
	private boolean predavackaJeVolna;
	private Queue<Zakaznik> frontZakaznikov;
	private ExpGenerator genPrichodu;
	private ExpGenerator genCasuObsluhy;
	private Random genSeedov;
	private double celkovaDobaCakaniaVSysteme;
	private int pocetZakaznikovVsysteme;
	private double celkovaVelkostFrontu;
	private double predoslyCasZmenyFronty;
	private int predoslaVelkostFrontu;
	public SimulaciaStanku(double simulacnyCas, long seed) {
		super(simulacnyCas);
		genSeedov = new Random(seed);
		genPrichodu = new ExpGenerator(genSeedov.nextLong(), 5);
		genCasuObsluhy = new ExpGenerator(genSeedov.nextLong(), 4);
		predavackaJeVolna = true;
		frontZakaznikov = new LinkedList<>();
		celkovaDobaCakaniaVSysteme = 0;
		pocetZakaznikovVsysteme = 0;
		celkovaVelkostFrontu = 0;
		predoslyCasZmenyFronty = simulacnyCas;
		predoslaVelkostFrontu = 0;
	}
	boolean jePredavackaVolna() {
		return predavackaJeVolna;
	}
	void zamestnajPredavacku() {
		predavackaJeVolna= false;
	}
	void uvolniPredavacku() {
		predavackaJeVolna = true;
	}
    void pridajZakaznikaDoFrontu(Zakaznik zakaznik) {
    	frontZakaznikov.add(zakaznik);
    }
    Zakaznik zoberZakaznikaZFrontu() {
    	return frontZakaznikov.remove();
    }
    double getCasZaKedyPrideDalsiZakaznik() {
    	return genPrichodu.nextExp();
    }
    void pridajCakaciuDobuZakaznika(Zakaznik zakaznik) {
    	pocetZakaznikovVsysteme++;
    	celkovaDobaCakaniaVSysteme+=zakaznik.getCasCakaniaVoFronte();
    }
    public double getPriemernyCasCakaniaVoFronte() {
    	return celkovaDobaCakaniaVSysteme/pocetZakaznikovVsysteme;
    }
    boolean jeFrontPrazdny() {
    	return frontZakaznikov.isEmpty();
    }
    double getCasKolkoBudeObsluhovat() {
    	return genCasuObsluhy.nextExp();
    }
    @Override
    protected void nastartujSimulaciu() {
    	UdalostPrichod prvaUdalost = new UdalostPrichod(getSimulacnyCas(), this);
    	naplanujUdalost(prvaUdalost);
    }
	public double getPriemernaDlzkaFrontu() {
		return celkovaVelkostFrontu/getTrvanieSimulacie();
	}
	void pridajDlzkuFrontu(double casSimulacie){
		celkovaVelkostFrontu+=(casSimulacie-predoslyCasZmenyFronty)*predoslaVelkostFrontu;
		predoslyCasZmenyFronty = casSimulacie;
		predoslaVelkostFrontu = frontZakaznikov.size();
	}
}
