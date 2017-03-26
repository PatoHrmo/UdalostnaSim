package testy;

import util.Statistika;

public class TestStatistiky {

	public static void main(String[] args) {
		Statistika stat = new Statistika();
		stat.pridaj(51.3);
		stat.pridaj(62.8);
		stat.pridaj(51.9);
		stat.pridaj(57);
		stat.pridaj(55.3);
		stat.pridaj(52.1);
		stat.pridaj(58.4);
		stat.pridaj(51.3);
		stat.pridaj(62.8);
		stat.pridaj(51.9);
		System.out.println("priemer je: "+stat.getPriemer());
		System.out.println("rozptyl:"+stat.getSmerodajnaOdchylka());
		System.out.println("IS: <"+stat.get90IntervalSpolahlivosti()[0]+"; "+stat.get90IntervalSpolahlivosti()[1]+">");
	}

}
