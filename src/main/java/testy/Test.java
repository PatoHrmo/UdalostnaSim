package testy;

import java.util.LinkedList;
import java.util.List;

import util.EmpGenerator;

public class Test {

	public static void main(String[] args) {
		List<double[]> p = new LinkedList<>();
		p.add(new double[]{10,0.5});
		p.add(new double[]{11,15,0.5});
		EmpGenerator gen  = new EmpGenerator(101,p);
		int[] pocetnosti = new int[6];
		int celkovyPocetCisel = 0;
		for(int i = 0; i< 100000000;i++) {
			pocetnosti[((int)gen.next())-10]++;
			celkovyPocetCisel++;
		}
		for(int i = 0;i<pocetnosti.length;i++) {
			System.out.println("pocet "+(i+10)+" bolo: "+pocetnosti[i]+" - to je "+((double)pocetnosti[i])*100/celkovyPocetCisel+"%");
		}
	}

}
