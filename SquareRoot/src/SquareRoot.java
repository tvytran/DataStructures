import java.text.NumberFormat;
import static java.lang.Double.isNaN;

/**
 * Thuy Vy Tran
 * tt2964
 * SquareRoot
 */
public class SquareRoot
{
    public static final double EPSILON = 1e-7;

    /**
     * finds the square root of a given number
     * @param num
     * @param epsilon
     * @return the square root
     */
    public static double sqrt(double num, double epsilon) {

        //making sure the number is a valid positive double
        if(num < 0 || Double.isNaN(num)) {
            return Double.NaN;
        }

        //if num is 0 or infinity, then return the number
        if(num == 0 ) {
            return num;
        }
        else if(num == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        }

        double previousGuess = 1;
        double currentGuess = num;

        //sqrt babylonian method
        do{
            previousGuess = currentGuess;
            currentGuess = 0.5*(previousGuess + (num/previousGuess));
        } while(Math.abs(currentGuess - previousGuess) > epsilon);

        return currentGuess;
    }
    public static void main(String[] args)
    {
            //if length is 0 or greater than 2 then program can't run
            if(args.length > 2 || args.length == 0) {
                System.err.println("Usage: java SquareRoot <value> [epsilon]");
                System.exit(1);
            }

            //checking if number is a double
            try {
                double number = Double.parseDouble(args[0]);
            }
            catch(NumberFormatException nfe) {
                System.err.println("Error: Value argument must be a double.");
                System.exit(1);
            }

            //checking if epsilon is a double
            if(args.length == 2) {
                try {
                    double number = Double.parseDouble(args[1]);
                } catch (NumberFormatException nfe) {
                    System.err.println("Error: Epsilon argument must be a positive double.");
                    System.exit(1);
                }
            }

            //checking if epsilon is negative
            if(args.length == 2 && Double.parseDouble(args[1]) <= 0) {
                System.err.println("Error: Epsilon argument must be a positive double.");
                System.exit(1);
            }

            //checking if there are one or two arguments to use the correct epsilon
            if(args.length == 1) {
                double value = sqrt(Double.parseDouble(args[0]),EPSILON);
                System.out.printf( "%.8f\n",value);
                System.exit(0);
            }
            else {
                double value = sqrt(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
                System.out.printf("%.8f\n", value);
                System.exit(0);
            }
    }
}
