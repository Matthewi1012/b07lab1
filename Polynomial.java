public class Polynomial{
    double [] coefficients;
    public Polynomial(){
        coefficients = new double[]{0};
    }
    public Polynomial(double[] arg)
    {
        coefficients = new double[arg.length];
        for(int i = 0; i < arg.length; i++)
        {
            coefficients[i] = arg[i];
        }
    }

    public Polynomial add(Polynomial poly){
        int length = 0;
        Polynomial newPoly;
        if(poly.coefficients.length >= coefficients.length)
        {
            length = coefficients.length;
            newPoly = new Polynomial(poly.coefficients);
        }
        else
        {
            length = poly.coefficients.length;
            newPoly = new Polynomial(coefficients);
        }

        for(int i = 0; i < length; i++)
        {
            newPoly.coefficients[i] = poly.coefficients[i] + coefficients[i];
        }

        return newPoly;
    }

    public double evaluate(double num){
        double eval = 0;
        for(int i = 0; i < coefficients.length; i++)
        {
            eval += coefficients[i]*Math.pow(num, i);
        }
        return eval;
    }

    public boolean hasRoot(double num){
        double eval = evaluate(num);
        return eval == 0;
    }
}