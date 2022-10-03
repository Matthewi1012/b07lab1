import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Polynomial {

	double [] coefficients;
	int [] exponents;
	
    public Polynomial(){
        coefficients = new double[]{0};
        exponents = new int[]{0};
    }
    
    public Polynomial(double[] arg)
    {
        int length = 0;
        int index = 0;
        for(int i = 0; i < arg.length; i++)
        {
        	if(arg[i] != 0)
        		length++;
        }
        coefficients = new double[length];
        exponents = new int[length];
        for(int i = 0; i < arg.length; i++)
        {
        	if(arg[i] != 0) {
        		coefficients[index] = arg[i];
                exponents[index] = i;
                index++;
        	}
        }
    }
    
    public Polynomial(File file) throws FileNotFoundException  {
    	Scanner input = new Scanner(file);
    	String poly = input.next();
    	int length = 0, p = 0, x = 0, start;
    	if(poly.length() != 1) {
    		for(int i = 1; i < poly.length(); i++)
        	{
        		if(poly.charAt(i) == '-' || poly.charAt(i) == '+')
        		{
        			length++;
        		}
        	}
    	}
    	length++;
    	coefficients = new double[length];
    	exponents = new int[length];
    	poly = poly.replace("-", "+-");
    	String[] terms = poly.split("\\+");
    	if(terms[0] == "") {
    		start = 1;
    	}
    	else {
    		start = 0;
    	}
    	for(int i = start; i < terms.length; i++) {
    		x = terms[i].indexOf("x");
    		if(x != -1) {
    			coefficients[p] = Double.parseDouble(terms[i].substring(0,x));
    			exponents[p] = Integer.parseInt(terms[i].substring(x+1));
    			p++;
    		}
    		else {
    			coefficients[p] = Double.parseDouble(terms[i]);
    			exponents[p] = 0;
    			p++;
    		}
    	}
    	input.close();
    }

    public Polynomial add(Polynomial poly){
    	int length = highestExpAdd(poly, exponents) + 1;
    	double sum = 0;
    	double[] newPolyCo = new double[length];
    	for(int i = 0; i < newPolyCo.length; i++) {
    		sum = sumAdd(poly, i);
    		newPolyCo[i] = sum;
    	}
    	Polynomial newPoly = new Polynomial(newPolyCo);
    	return newPoly;
    }
    
    public int highestExpAdd(Polynomial poly, int[] exp) {
		int max = 0;
		for(int i = 0; i < poly.exponents.length; i++) {
			if(poly.exponents[i] >= max) {
				max = poly.exponents[i];
			}
		}
		for(int i = 0; i < exp.length; i++) {
			if(exp[i] >= max) {
				max =exp[i];
			}
		}
		return max;
	}
    
    public double sumAdd(Polynomial poly, int exp) {
    	int sum = 0;
    	for(int i = 0; i < exponents.length; i++) {
    		if(exponents[i] == exp) {
    			sum += coefficients[i];
    		}
    	}
    	for(int i = 0; i < poly.exponents.length; i++) {
    		if(poly.exponents[i] == exp) {
    			sum += poly.coefficients[i];
    		}
    	}
    	return sum;
    }
    
    public double evaluate(double num){
        double eval = 0;
        for(int i = 0; i < coefficients.length; i++)
        {
            eval += coefficients[i]*Math.pow(num, exponents[i]);
        }
        return eval;
    }

    public boolean hasRoot(double num){
        double eval = evaluate(num);
        return eval == 0;
    }
    
    public Polynomial multiply(Polynomial poly) {
    	int length = highestExpMul(poly, exponents) + 1;
    	double sum = 0;
    	double[] newPolyCo = new double[length];
    	for(int i = 0; i < newPolyCo.length; i++) {
    		sum = sumMult(poly, i);
    		newPolyCo[i] = sum;
    	}
    	Polynomial newPoly = new Polynomial(newPolyCo);
    	return newPoly;
    }
    
    public int highestExpMul(Polynomial poly, int[] exp) {
    	int max1 = 0, max2 = 0;
    	for(int i = 0; i < poly.exponents.length; i++) {
    		if(poly.exponents[i] >= max1) {
    			max1 = poly.exponents[i];
    		}
    	}
    	for(int i = 0; i < exponents.length; i++) {
    		if(exponents[i] >= max2) {
    			max2 = exponents[i];
    		}
    	}
    	return max1+max2;
    }
    
    public double sumMult(Polynomial poly, int exp) {
    	double sum = 0;
    	for(int i = 0; i < poly.exponents.length; i++) {
    		for(int j = 0; j < exponents.length; j++) {
    			if(poly.exponents[i] + exponents[j] == exp) {
    				sum += poly.coefficients[i] * coefficients[j];
    			}
    		}
    	}
    	return sum;
    }
    
    public void saveToFile(String file) throws FileNotFoundException {
    	PrintStream ps = new PrintStream(file);
    	for(int i = 0; i < coefficients.length; i++) {
    		if(coefficients[i] > 0 && i != 0)
    			ps.print("+");
    		ps.print(coefficients[i]);
    		if(exponents[i] != 0) {
    			ps.print("x");
    			ps.print(exponents[i]);
    		}
    	}
    }