package testy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import util.ExpGenerator;
import util.TriangleGenerator;

public class TestSpravnostiRozdeleni {

	public static void main(String[] args) {
		ExpGenerator gen = new ExpGenerator(0, 4);
		TriangleGenerator tria1 = new TriangleGenerator(0, 10, 30, 20);
		TriangleGenerator tria2 = new TriangleGenerator(0, 10, 30, 25);
		TriangleGenerator tria3 = new TriangleGenerator(0, 10, 30, 11);
		try {
			PrintWriter writerExp = new PrintWriter(new File("exp4.txt"));
			PrintWriter writerTria1 = new PrintWriter(new File("tria 10 20 30.txt"));
			PrintWriter writerTria2 = new PrintWriter(new File("tria 10 25 30.txt"));
			PrintWriter writerTria3 = new PrintWriter(new File("tria 10 11 30.txt"));
			for(int i = 0; i<1000000;i++) {
				writerExp.println(gen.nextExp());
				writerTria1.println(tria1.nextTria());
				writerTria2.println(tria2.nextTria());
				writerTria3.println(tria3.nextTria());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
