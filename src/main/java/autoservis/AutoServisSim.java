package autoservis;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import autoservis.udalosti.NovaReplikacia;
import autoservis.udalosti.NovyDen;
import autoservis.udalosti.PrichodZakaznika;
import core.SimulacneJadro;
import util.DobaOpravyGen;
import util.EmpGenerator;
import util.ExpGenerator;
import util.RovnomernyGenerator;
import util.Statistika;
import util.TriangleGenerator;
import util.VazenyPriemer;

public class AutoServisSim extends SimulacneJadro {
	private Queue<Oprava> frontaZakaznikov;
	private Queue<Oprava> frontaNeopravenychAutVDielni;
	private Queue<Oprava> frontaOpravenychAutVDielni;
	private int aktualnyPocetRobotnikov1;
	private int aktualnyPocetRobotnikov2;
	private final int POCET_ROBOTNIKOV1;
	private final int POCET_ROBOTNIKOV2;
	private ExpGenerator genPrichoduVSekundach;
	private RovnomernyGenerator genCasuZadavaniaObjednavky;
	private RovnomernyGenerator genCasuPrevzatiaAuta;
	private TriangleGenerator genCasuPreparkovaniaDoDielne;
	private TriangleGenerator genCasuPreparkovaniaZDielne;
	private RovnomernyGenerator genCasuOdovzdaniaAuta;
	private DobaOpravyGen genDlzkaOpravy;
	private EmpGenerator genPocetOprav;
	private Statistika priemCakanieZakaznikovVoFronte;
	private Statistika priemCakanieZakaznikovNaOpravu;
	private Statistika priemDlzkaFrontuZakaznikovNaKonciDna;
	private Statistika priemDlzkaFrontuNeopravenychAutNaKonciDna;
	private Statistika priemDlzkaFrontuOpravenychAutNaKonciDna;
	private VazenyPriemer priemPocetVolnychPracovnikov1;
	private VazenyPriemer priemPocetVolnychPracovnikov2;
	private VazenyPriemer priemDlzkaFrontuZakaznikov;
	private VazenyPriemer priemDlzkaFrontuNeopravenychAut;
	private VazenyPriemer priemDlzkaFrontuOpravenychAut;
	// statistiky replikacii
	private Statistika replPriemCakanieZakaznikovVoFronte;
	private Statistika replPriemCakanieZakaznikovNaOpravu;
	private Statistika replPriemDlzkaFrontuZakaznikovNaKonciDna;
	private Statistika replPriemDlzkaFrontuNeopravenychAutNaKonciDna;
	private Statistika replPriemDlzkaFrontuOpravenychAutNaKonciDna;
	private Statistika replPriemPocetVolnychPracovnikov1;
	private Statistika replPriemPocetVolnychPracovnikov2;
	private Statistika replPriemDlzkaFrontuZakaznikov;
	private Statistika replPriemDlzkaFrontuNeopravenychAut;
	private Statistika replPriemDlzkaFrontuOpravenychAut;
	private int pocetVolnychZamestnancov1PredZmenou;
	private int pocetVolnychZamestnancov2PredZmenou;
	private int dlzkaFrontuZakaznikovPredZmenou;
	private int dlzkaFrontuNeopravenychAutPredZmenou;
	private int dlzkaFrontuOpravenychAutPredZmenou;
	private double casMinulehoZaznamuPoctuVolnychZamestnancov1;
	private double casMinulehoZaznamuPoctuVolnychZamestnancov2;
	private double casMinulehoZaznamuDlzkyFrontuZakaznikov;
	private double casMinulehoZaznamuDlzkyFrontuNeopravenychAut;
	private double casMinulehoZaznamuDlzkyFrontuOpravenychAut;

	public AutoServisSim(double simulacnyCas, boolean turboMod, int pocetRobotnikov1, int pocetRobotnikov2) {
		super(simulacnyCas, turboMod);
		frontaZakaznikov = new LinkedList<>();
		frontaNeopravenychAutVDielni = new LinkedList<>();
		frontaOpravenychAutVDielni = new LinkedList<>();
		this.aktualnyPocetRobotnikov1 = pocetRobotnikov1;
		this.aktualnyPocetRobotnikov2 = pocetRobotnikov2;
		POCET_ROBOTNIKOV1 = pocetRobotnikov1;
		POCET_ROBOTNIKOV2 = pocetRobotnikov2;
		Random genNasad = new Random(0);
		genPrichoduVSekundach = new ExpGenerator(genNasad.nextLong(), 5 * 60);
		genCasuOdovzdaniaAuta = new RovnomernyGenerator(genNasad.nextLong(), 123, 257);
		genCasuPreparkovaniaDoDielne = new TriangleGenerator(genNasad.nextLong(), 120, 540, 240);
		genCasuPreparkovaniaZDielne = new TriangleGenerator(genNasad.nextLong(), 120, 540, 240);
		genCasuPrevzatiaAuta = new RovnomernyGenerator(genNasad.nextLong(), 80, 160);
		genCasuZadavaniaObjednavky = new RovnomernyGenerator(genNasad.nextLong(), 70, 310);
		genDlzkaOpravy = new DobaOpravyGen(genNasad.nextLong());
		LinkedList<double[]> poctyOprav = new LinkedList<>();
		poctyOprav.add(new double[] { 1, 0.4 });
		poctyOprav.add(new double[] { 2, 0.15 });
		poctyOprav.add(new double[] { 3, 0.14 });
		poctyOprav.add(new double[] { 4, 0.12 });
		poctyOprav.add(new double[] { 5, 0.1 });
		poctyOprav.add(new double[] { 6, 0.09 });
		genPocetOprav = new EmpGenerator(genNasad.nextLong(), poctyOprav);
		// inicialozovanie atributov potrebnych na statistiku
		priemCakanieZakaznikovVoFronte = new Statistika();
		priemCakanieZakaznikovNaOpravu = new Statistika();
		priemPocetVolnychPracovnikov1 = new VazenyPriemer();
		priemPocetVolnychPracovnikov2 = new VazenyPriemer();
		priemDlzkaFrontuOpravenychAut = new VazenyPriemer();
		priemDlzkaFrontuNeopravenychAut = new VazenyPriemer();
		priemDlzkaFrontuZakaznikov = new VazenyPriemer();
		priemDlzkaFrontuZakaznikovNaKonciDna = new Statistika();
		priemDlzkaFrontuOpravenychAutNaKonciDna = new Statistika();
		priemDlzkaFrontuNeopravenychAutNaKonciDna = new Statistika();
		replPriemCakanieZakaznikovVoFronte = new Statistika();
		replPriemCakanieZakaznikovNaOpravu = new Statistika();
		replPriemDlzkaFrontuZakaznikovNaKonciDna = new Statistika();
		replPriemDlzkaFrontuNeopravenychAutNaKonciDna = new Statistika();
		replPriemDlzkaFrontuOpravenychAutNaKonciDna = new Statistika();
		replPriemPocetVolnychPracovnikov1 = new Statistika();
		replPriemPocetVolnychPracovnikov2 = new Statistika();
		replPriemDlzkaFrontuZakaznikov = new Statistika();
		replPriemDlzkaFrontuNeopravenychAut = new Statistika();
		replPriemDlzkaFrontuOpravenychAut = new Statistika();
		casMinulehoZaznamuPoctuVolnychZamestnancov1 = simulacnyCas;
		casMinulehoZaznamuPoctuVolnychZamestnancov2 = simulacnyCas;
		pocetVolnychZamestnancov1PredZmenou = pocetRobotnikov1;
		pocetVolnychZamestnancov2PredZmenou = pocetRobotnikov2;
		dlzkaFrontuZakaznikovPredZmenou = 0;
		dlzkaFrontuNeopravenychAutPredZmenou = 0;
		dlzkaFrontuOpravenychAutPredZmenou = 0;
		casMinulehoZaznamuDlzkyFrontuZakaznikov = simulacnyCas;
		casMinulehoZaznamuDlzkyFrontuNeopravenychAut = simulacnyCas;
		casMinulehoZaznamuDlzkyFrontuOpravenychAut = simulacnyCas;
	}

	@Override
	protected void nastartujSimulaciu() {
		double dlzkaDnaVSekundach = 8*60*60;
		naplanujUdalost(new PrichodZakaznika(0, this, null));
		naplanujUdalost(new NovyDen(dlzkaDnaVSekundach, this, null));
		// FIXME ozaj az po dni?
		naplanujUdalost(new NovaReplikacia(dlzkaDnaVSekundach*90, this, null));
	}
	public void vykonajPocetReplikacii(int pocetReplikacii) {
		super.vykonajUdalostnuSimulaciu(pocetReplikacii*8*60*60*90);
	}

	public void pridajZakaznikaDoFrontu(Oprava zakaznik) {
		frontaZakaznikov.add(zakaznik);
		pridajDlzkuFrontuZakaznikov();
	}

	public Oprava zoberZakaznikaZfrontu() {
		Oprava oprava = frontaZakaznikov.poll();
		pridajDlzkuFrontuZakaznikov();
		return oprava;
	}

	public int getPocetZakaznikovVoFronte() {
		return frontaZakaznikov.size();
	}

	public void pridajAutoDoFrontuPokazenych(Oprava pokazeneAuto) {
		frontaNeopravenychAutVDielni.add(pokazeneAuto);
		pridajDlzkuFrontuNeopravenychAut();
	}

	public Oprava zoberPokazeneAutoZFrontu() {
		Oprava o = frontaNeopravenychAutVDielni.poll();
		pridajDlzkuFrontuNeopravenychAut();
		return o;
	}

	public int getPocetPokazenychAutVoFronte() {
		return frontaNeopravenychAutVDielni.size();
	}

	public void pridajAutoDoFrontuOpravenych(Oprava opraveneAuto) {
		frontaOpravenychAutVDielni.add(opraveneAuto);
		pridajDlzkuFrontuOpravenychAut();
	}

	public Oprava zoberOpraveneAutoZFrontu() {
		Oprava o = frontaOpravenychAutVDielni.poll();
		pridajDlzkuFrontuOpravenychAut();
		return o;
	}

	public int getPocetOpravenychAutVoFronte() {
		return frontaOpravenychAutVDielni.size();
	}

	public boolean jeVolnyRobotnik1() {
		return aktualnyPocetRobotnikov1 == 0 ? false : true;
	}

	public boolean jeVolnyRobotnik2() {
		return aktualnyPocetRobotnikov2 == 0 ? false : true;
	}

	public int getAktualnyPocetRobotnikov1() {
		return aktualnyPocetRobotnikov1;
	}

	public int getAktualnyPocetRobotnikov2() {
		return aktualnyPocetRobotnikov2;
	}

	public void zamestnajRobotnika1() {
		if (!jeVolnyRobotnik1()) {
			System.out.println("zamestnanie neexistujuceho pracovnika1");
			System.exit(1);
		}
		aktualnyPocetRobotnikov1--;
		pridajPocetVolnychZamestnancov1();
	}

	public void zamestnajRobotnika2() {
		if (!jeVolnyRobotnik2()) {
			System.out.println("zamestnanie neexistujuceho pracovnika2");
			System.exit(1);
		}
		aktualnyPocetRobotnikov2--;
		pridajPocetVolnychZamestnancov2();
	}

	public void uvolniRobotnika1() {
		aktualnyPocetRobotnikov1++;
		pridajPocetVolnychZamestnancov1();
	}

	public void uvolniRobotnika2() {
		aktualnyPocetRobotnikov2++;
		pridajPocetVolnychZamestnancov2();
	}

	public double nextCasPrichodu() {
		return genPrichoduVSekundach.nextExp();
	}

	public double getCasZadavaniaObjednavky() {
		return genCasuZadavaniaObjednavky.next();
	}

	public double getCasPrevzatiaAutaOdZakaznika() {
		return genCasuPrevzatiaAuta.next();
	}

	public double getCasPreparkovaniaDoDielne() {
		return genCasuPreparkovaniaDoDielne.nextTria();
	}

	public double getCasPreparkovaniaZDielne() {
		return genCasuPreparkovaniaZDielne.nextTria();
	}

	public double getCasOpravy() {
		int pocetOprav = (int) Math.round(genPocetOprav.next());
		double casOpravy = 0;
		for (int i = 0; i < pocetOprav; i++) {
			casOpravy += genDlzkaOpravy.nextDlzkaOpravy();
		}
		return casOpravy * 60;
	}

	public double getCasOdovzdavaniaHotovehoAutaZakaznikovy() {
		return genCasuOdovzdaniaAuta.next();
	}

	public void pridajCasCakaniaZakaznikaVoFronte(double dlzkaCakaniaVoFronteZakaznikov) {
		priemCakanieZakaznikovVoFronte.pridaj(dlzkaCakaniaVoFronteZakaznikov);
	}

	public void pridajCasCakaniaNaPrevzatieOpravenehoAuta(double dlzkaCakaniaNaOpravuAuta) {
		priemCakanieZakaznikovNaOpravu.pridaj(dlzkaCakaniaNaOpravuAuta);

	}

	private void pridajPocetVolnychZamestnancov1() {
		double cas = getSimulacnyCas() - casMinulehoZaznamuPoctuVolnychZamestnancov1;
		double pocet = pocetVolnychZamestnancov1PredZmenou;
		priemPocetVolnychPracovnikov1.pridaj(pocet, cas);
		casMinulehoZaznamuPoctuVolnychZamestnancov1 = getSimulacnyCas();
		pocetVolnychZamestnancov1PredZmenou = aktualnyPocetRobotnikov1;
	}

	private void pridajPocetVolnychZamestnancov2() {
		double cas = getSimulacnyCas() - casMinulehoZaznamuPoctuVolnychZamestnancov2;
		double pocet = pocetVolnychZamestnancov2PredZmenou;
		priemPocetVolnychPracovnikov2.pridaj(pocet, cas);
		casMinulehoZaznamuPoctuVolnychZamestnancov2 = getSimulacnyCas();
		pocetVolnychZamestnancov2PredZmenou = aktualnyPocetRobotnikov2;
	}

	private void pridajDlzkuFrontuZakaznikov() {
		double cas = getSimulacnyCas() - casMinulehoZaznamuDlzkyFrontuZakaznikov;
		double pocet = dlzkaFrontuZakaznikovPredZmenou;
		priemDlzkaFrontuZakaznikov.pridaj(pocet, cas);
		casMinulehoZaznamuDlzkyFrontuZakaznikov = getSimulacnyCas();
		dlzkaFrontuZakaznikovPredZmenou = frontaZakaznikov.size();
	}

	private void pridajDlzkuFrontuNeopravenychAut() {
		double cas = getSimulacnyCas() - casMinulehoZaznamuDlzkyFrontuNeopravenychAut;
		double pocet = dlzkaFrontuNeopravenychAutPredZmenou;
		priemDlzkaFrontuNeopravenychAut.pridaj(pocet, cas);
		casMinulehoZaznamuDlzkyFrontuNeopravenychAut = getSimulacnyCas();
		dlzkaFrontuNeopravenychAutPredZmenou = frontaNeopravenychAutVDielni.size();
	}

	private void pridajDlzkuFrontuOpravenychAut() {
		double cas = getSimulacnyCas() - casMinulehoZaznamuDlzkyFrontuOpravenychAut;
		double pocet = dlzkaFrontuOpravenychAutPredZmenou;
		priemDlzkaFrontuOpravenychAut.pridaj(pocet, cas);
		casMinulehoZaznamuDlzkyFrontuOpravenychAut = getSimulacnyCas();
		dlzkaFrontuOpravenychAutPredZmenou = frontaOpravenychAutVDielni.size();
	}

	public void urobStatistikuNaKonciDna() {
		priemDlzkaFrontuNeopravenychAutNaKonciDna.pridaj(frontaNeopravenychAutVDielni.size());
		priemDlzkaFrontuOpravenychAutNaKonciDna.pridaj(frontaOpravenychAutVDielni.size());
		priemDlzkaFrontuZakaznikovNaKonciDna.pridaj(frontaZakaznikov.size());

	}

	public void posliDomovZakaznikovVoFronte() {
		frontaZakaznikov.clear();
		pridajDlzkuFrontuZakaznikov();
	}

	public void vyhodnotStatistikyReplikacie() {
		pridajDlzkuFrontuNeopravenychAut();
		pridajDlzkuFrontuOpravenychAut();
		pridajDlzkuFrontuZakaznikov();
		pridajPocetVolnychZamestnancov1();
		pridajPocetVolnychZamestnancov2();
		replPriemCakanieZakaznikovNaOpravu.pridaj(priemCakanieZakaznikovNaOpravu.getPriemer());
		replPriemCakanieZakaznikovVoFronte.pridaj(priemCakanieZakaznikovVoFronte.getPriemer());
		replPriemDlzkaFrontuNeopravenychAut.pridaj(priemDlzkaFrontuNeopravenychAut.getPriemer());
		replPriemDlzkaFrontuNeopravenychAutNaKonciDna.pridaj(priemDlzkaFrontuNeopravenychAutNaKonciDna.getPriemer());
		replPriemDlzkaFrontuOpravenychAut.pridaj(priemDlzkaFrontuOpravenychAut.getPriemer());
		replPriemDlzkaFrontuOpravenychAutNaKonciDna.pridaj(priemDlzkaFrontuOpravenychAutNaKonciDna.getPriemer());
		replPriemDlzkaFrontuZakaznikov.pridaj(priemDlzkaFrontuZakaznikov.getPriemer());
		replPriemDlzkaFrontuZakaznikovNaKonciDna.pridaj(priemDlzkaFrontuZakaznikovNaKonciDna.getPriemer());
		replPriemPocetVolnychPracovnikov1.pridaj(priemPocetVolnychPracovnikov1.getPriemer());
		replPriemPocetVolnychPracovnikov2.pridaj(priemPocetVolnychPracovnikov2.getPriemer());
	}

	public void resetujStatistiky() {
		priemCakanieZakaznikovNaOpravu.reset();
		priemCakanieZakaznikovVoFronte.reset();
		priemDlzkaFrontuNeopravenychAutNaKonciDna.reset();
		priemDlzkaFrontuOpravenychAutNaKonciDna.reset();
		priemDlzkaFrontuZakaznikovNaKonciDna.reset();
		priemDlzkaFrontuNeopravenychAut.reset();
		priemDlzkaFrontuOpravenychAut.reset();
		priemDlzkaFrontuZakaznikov.reset();
		priemPocetVolnychPracovnikov1.reset();
		priemPocetVolnychPracovnikov2.reset();
	}
	public double getPriemernaDobaCakaniaVRade(boolean celkovo) {
		if(celkovo)
		return replPriemCakanieZakaznikovVoFronte.getPriemer();
		return priemCakanieZakaznikovVoFronte.getPriemer();
	}
	public double[] getIsCakaniaVRade(boolean celkovo) {
		if(celkovo)
		return replPriemCakanieZakaznikovVoFronte.get90IntervalSpolahlivosti();
		return priemCakanieZakaznikovVoFronte.get90IntervalSpolahlivosti();
	}
	public double getPriemernaDobaCakaniaNaOpravu(boolean celkovo) {
		if(celkovo)
		return replPriemCakanieZakaznikovNaOpravu.getPriemer();
		return priemCakanieZakaznikovNaOpravu.getPriemer();
	}
	public double getPriemernyPocetVolnychPracovnikov1(boolean celkovo) {
		if(celkovo)
		return replPriemPocetVolnychPracovnikov1.getPriemer();
		return priemPocetVolnychPracovnikov1.getPriemer();
	}
	public double getPriemernyPocetVolnychPracovnikov2(boolean celkovo) {
		if(celkovo)
		return replPriemPocetVolnychPracovnikov2.getPriemer();
		return priemPocetVolnychPracovnikov2.getPriemer();
	}
	public double getPriemernaDlzkaFrontuZakaznikov(boolean celkovo) {
		if(celkovo)
		return replPriemDlzkaFrontuZakaznikov.getPriemer();
		return priemDlzkaFrontuZakaznikov.getPriemer();
	}
	public double getPriemernaDlzkaFrontuZakaznikovNaKonciDna(boolean celkovo) {
		if(celkovo)
		return replPriemDlzkaFrontuZakaznikovNaKonciDna.getPriemer();
		return priemDlzkaFrontuZakaznikovNaKonciDna.getPriemer();
	}
	public double getPriemernaDlzkaFrontuOpravenychAut(boolean celkovo) {
		if(celkovo)
		return replPriemDlzkaFrontuOpravenychAut.getPriemer();
		return priemDlzkaFrontuOpravenychAut.getPriemer();
	}
	public double getPriemernaDlzkaFrontuNeopravenychAut(boolean celkovo) {
		if(celkovo)
		return replPriemDlzkaFrontuNeopravenychAut.getPriemer();
		return priemDlzkaFrontuNeopravenychAut.getPriemer();
	}
	public double getPriemernaDlzkaFrontuOpravenychAutNaKonciDna(boolean celkovo) {
		if(celkovo)
		return replPriemDlzkaFrontuOpravenychAutNaKonciDna.getPriemer();
		return priemDlzkaFrontuOpravenychAutNaKonciDna.getPriemer();
	}
	public double getPriemernaDlzkaFrontuNeopravenychAutNaKonciDna(boolean celkovo) {
		if(celkovo)
		return replPriemDlzkaFrontuNeopravenychAutNaKonciDna.getPriemer();
		return priemDlzkaFrontuNeopravenychAutNaKonciDna.getPriemer();
	}

}
