

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) throws FileNotFoundException {
		Polynomial p = new Polynomial(); 
		System.out.println(p.evaluate(3)); 
	    double [] c1 = {6,5}; 
	    int[] c2 = {0,3};
		Polynomial p1 = new Polynomial(c1, c2); 
		double [] c3 = {-2,-9}; 
		int[] c4 = {1,4};
		Polynomial p2 = new Polynomial(c3,c4); 
		Polynomial s = p2.add(p1);
		System.out.println("s = 6+5x3 + -2x1-9x4");
		System.out.println("s(1) = " + s.evaluate(1));
		System.out.println("p2: -2x1-9x4"); 
		if(p2.hasRoot(1)) 
		  System.out.println("1 is a root of p2"); 
		else 
		  System.out.println("1 is not a root of p2");
		File file = new File("/Users/matthewiannantuono/eclipse-workspace/B07/Labs/week2/construct.txt");
		Polynomial poly = new Polynomial(file);
		System.out.println("poly from file = -4x1-3x3+5+7x2");
		System.out.println("poly(1) from file = 5: " + (poly.evaluate(1) == 5.0));
		Polynomial m = p1.multiply(p2);
		System.out.println("m = 6+5x3 * -2-9x4");
		System.out.println("m(1) from p1*p2 = -121: " + (m.evaluate(1) == -121.0));
		File file2 = new File("/Users/matthewiannantuono/eclipse-workspace/B07/Labs/week2/writeTo.txt");
		System.out.println("Saving poly : -4x1-3x3+5+7x2 to file");
		poly.saveToFile("/Users/matthewiannantuono/eclipse-workspace/B07/Labs/week2/writeTo.txt");
		Scanner input = new Scanner(file2);
		String written = input.nextLine();
		System.out.println("Written text: " + written);
		input.close();
	}
}
