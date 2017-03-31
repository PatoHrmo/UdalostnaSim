package autoservis;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import autoservis.udalosti.PrichodZakaznika;
import core.SimulacneJadro;
import util.DobaOpravyGen;
import util.EmpGenerator;
import util.ExpGenerator;
import util.RovnomernyGenerator;
import util.TriangleGenerator;

public class AutoServisSim extends SimulacneJadro {
	private	Queue<Oprava> frontaZakaznikov;
	private Queue<Oprava> frontaNeopravenychAutVDielni;
	private Queue<Oprava> frontaOpravenychAutVDielni;
	private int pocetRobotnikov1;
	private int pocetRobotnikov2;
	private ExpGenerator genPrichoduVSekundach;
	private RovnomernyGenerator genCasuZadavaniaObjednavky;
	private RovnomernyGenerator genCasuPrevzatiaAuta;
	private TriangleGenerator genCasuPreparkovaniaDoDielne;
	private TriangleGenerator genCasuPreparkovaniaZDielne;
	private RovnomernyGenerator genCasuOdovzdaniaAuta;
	private DobaOpravyGen genDlzkaOpravy;
	private EmpGenerator genPocetOprav;
	public AutoServisSim(double simulacnyCas,int pocetRobotnikov1, int pocetRobotnikov2) {
		super(simulacnyCas);
		frontaZakaznikov = new LinkedList<>();
		frontaNeopravenychAutVDielni = new LinkedList<>();
		frontaOpravenychAutVDielni = new LinkedList<>();
		this.pocetRobotnikov1 = pocetRobotnikov1;
		this.pocetRobotnikov2 = pocetRobotnikov2;
		Random genNasad = new Random(0);
		genPrichoduVSekundach = new ExpGenerator(genNasad.nextLong(), 5*60);
		genCasuOdovzdaniaAuta = new RovnomernyGenerator(genNasad.nextLong(), 123, 257);
		genCasuPreparkovaniaDoDielne = new TriangleGenerator(genNasad.nextLong(), 120, 540,240);
		genCasuPreparkovaniaZDielne = new TriangleGenerator(genNasad.nextLong(), 120, 540, 240);
		genCasuPrevzatiaAuta = new  RovnomernyGenerator(genNasad.nextLong(), 80, 160);
		genCasuZadavaniaObjednavky = new RovnomernyGenerator(genNasad.nextLong(), 70, 310);
		genDlzkaOpravy = new DobaOpravyGen(genNasad.nextLong());
		LinkedList<double[]> poctyOprav = new LinkedList<>();
		poctyOprav.add(new double[]{1,0.4});
		poctyOprav.add(new double[]{2,0.15});
		poctyOprav.add(new double[]{3,0.14});
		poctyOprav.add(new double[]{4,0.12});
		poctyOprav.add(new double[]{5,0.1});
		poctyOprav.add(new double[]{6,0.09});
		genPocetOprav = new EmpGenerator(genNasad.nextLong(), poctyOprav);
	}
	@Override
	protected void nastartujSimulaciu() {
		naplanujUdalost(new PrichodZakaznika(0, this, null));
	}
	public void pridajZakaznikaDoFrontu(Oprava zakaznik) {
		frontaZakaznikov.add(zakaznik);
	}
	public Oprava zoberZakaznikaZfrontu(){
		return frontaZakaznikov.poll();
	}
	public int getPocetZakaznikovVoFronte() {
		return frontaZakaznikov.size();
	}
	public void pridajAutoDoFrontuPokazenych(Oprava pokazeneAuto) {
		frontaNeopravenychAutVDielni.add(pokazeneAuto);
	}
	public Oprava zoberPokazeneAutoZFrontu() {
		return frontaNeopravenychAutVDielni.poll();
	}
	public int getPocetPokazenychAutVoFronte() {
		return frontaNeopravenychAutVDielni.size();
	}
	public void pridajAutoDoFrontuOpravenych(Oprava opraveneAuto) {
		frontaOpravenychAutVDielni.add(opraveneAuto);
	}
	public Oprava zoberOpraveneAutoZFrontu() {
		return frontaOpravenychAutVDielni.poll();
	}
	public int getPocetOpravenychAutVoFronte() {
		return frontaOpravenychAutVDielni.size();
	}
	public boolean jeVolnyRobotnik1() {
		return pocetRobotnikov1==0 ? false : true;
	}
	public boolean jeVolnyRobotnik2() {
		return pocetRobotnikov2==0 ? false : true;
	}
	public void zamestnajRobotnika1(){
		if(!jeVolnyRobotnik1()){
			System.out.println("zamestnanie neexistujuceho pracovnika1");
			System.exit(1);
		}
		pocetRobotnikov1--;
	}
	public void zamestnajRobotnika2(){
		if(!jeVolnyRobotnik2()){
			System.out.println("zamestnanie neexistujuceho pracovnika2");
			System.exit(1);
		}
		pocetRobotnikov2--;
	}
	public void uvolniRobotnika1() {
		pocetRobotnikov1++;
	}
	public void uvolniRobotnika2() {
		pocetRobotnikov2++;
	}
	public double nextCasPrichodu() {
		return genPrichoduVSekundach.nextExp();
	}
	public double getCasZadavaniaObjednavky() {
		return genCasuZadavaniaObjednavky.next();
	}
	public double getCasPrevzatiaAutaOdZakaznika(){
		return genCasuPrevzatiaAuta.next();
	}
	public double getCasPreparkovaniaDoDielne() {
		return genCasuPreparkovaniaDoDielne.nextTria();
	}
	public double getCasPreparkovaniaZDielne() {
		return genCasuPreparkovaniaZDielne.nextTria();
	}
	public double getCasOpravy() {
		int pocetOprav = (int)Math.round(genPocetOprav.next());
		double casOpravy = 0;
		for(int i=0;i<pocetOprav;i++) {
			casOpravy+= genDlzkaOpravy.nextDlzkaOpravy();
		}
		return casOpravy*60;
	}
	public double getCasOdovzdavaniaHotovehoAutaZakaznikovy() {
		return genCasuOdovzdaniaAuta.next();
	}

}
