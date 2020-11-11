

import java.util.Scanner;

public class NumericAssignment {
	
	public static void main(String[] args) {
		int method;
		double pol1,pol2,pol3,interv1,interv2,tolval;
		System.out.println("Please enter the values.");
		Scanner input = new Scanner(System.in);
		System.out.println("Method index:");
		method = input.nextInt();
		System.out.println("Coefficient of the first term of polynomial:");
		pol1=input.nextDouble();
		System.out.println("Coefficient of the second term of polynomial:");
		pol2=input.nextDouble();
		System.out.println("Coefficient of the third term of polynomial:");
		pol3=input.nextDouble();
		System.out.println("First interval variable:");
		interv1=input.nextDouble();
		System.out.println("Second interval variable:");
		interv2=input.nextDouble();
		System.out.println("Tolerance value:");
		tolval=input.nextDouble();
		switch (method) {
		case 1:
			startnewton_Raphson(pol1, pol2, pol3, interv1, interv2, tolval);
			break;
		case 2:
			theVonMises(pol1, pol2, pol3, interv1, interv2, tolval);
			break;
		case 3:
			Bisection test = new Bisection((int)pol1, (int)pol2, (int)pol3, (int)interv1, (int)interv2, tolval);
			break;
		case 4:
			regulaFalsi((int)pol1, (int)pol2, (int)pol3, interv1, interv2, tolval);
			break;
		}
		//vonMises(2.0,-2.5,-5.0,2,2,0.01); 
		input.close();
	}
	/*--------------------------------------------------------------------------------------------------------------------------------*/
	/*--------------------------------------------------------------------------------------------------------------------------------*/
	/*--------------------------------------------------------------------------------------------------------------------------------*/
	/*														VON MISES													        	  */
	/*--------------------------------------------------------------------------------------------------------------------------------*/
	/*--------------------------------------------------------------------------------------------------------------------------------*/
	/*--------------------------------------------------------------------------------------------------------------------------------*/
	
	
	static void theVonMises(double f1,double f2,double f3,double i1,double i2,double tolval) {
		System.out.println("Von Mises calculations for first root;");
		vonMises(f1, f2, f3, i1, i2, tolval);
		if (f1!=0) {
			System.out.println("Von Mises calculations for second root;");
			vonMises(f1, f2, f3, -i1, i2, tolval);
		}
		
	
	}
	
	static void vonMises(double f1,double f2,double f3,double i1,double i2,double tolval) 
    { 
		
		final double EPSILON = tolval; int i=0;
        double derivx0 =derivFunc(f1,f2,f3,i1); 
        double h=func(f1,f2,f3,i1)/derivx0;
        while (Math.abs(h) >= EPSILON) 
        { 
            h = func(f1,f2,f3,i1) / derivx0; 
            System.out.print("Iteration:"+i+"\tRold:"+(double) (Math.floor(i1* 100)) / (100));

            i++;
            // x(i+1) = x(i) - f(x) / f'(0)  
            i1 = i1 - h;
            System.out.println("\tRnew:"+(double) (Math.floor(i1* 100)) / (100)+"\tErr:"+h);
            if(i>100) {
            	System.out.println("There is no convergence.");
            	break;
            }
        } 
        System.out.print("The value of the"
                + " root is : " 
                + Math.round(i1 * 100.0) / 100.0); 
    }
	static double func(double f1,double f2,double f3,double x) 
    { 
		
        return f1*Math.pow(x,2)+f2*Math.pow(x,1)+f3; 
    } 
	
	static double derivFunc(double f1,double f2,double f3,double x) 
    { 
        return f1*2*Math.pow(x,1)+f2; 
    }
	/*--------------------------------------------------------------------------------------------------------------------------------*/
	/*--------------------------------------------------------------------------------------------------------------------------------*/
	/*--------------------------------------------------------------------------------------------------------------------------------*/
	/*													REGULA FALSI																  */
	/*--------------------------------------------------------------------------------------------------------------------------------*/
	/*--------------------------------------------------------------------------------------------------------------------------------*/
	/*--------------------------------------------------------------------------------------------------------------------------------*/
	static void regulaFalsi(int A,int B,int C,double a,double b, double tol) {
		if (functionX(a, A, B, C) * functionX(b, A, B, C) >= 0) {
			System.out.println("Please enter the bounds correctly");
		}
				
		double c = a;
		
		int k = 0;
		
		while (true) {
			k++;
			if (k ==100) {
				System.out.println("It is diverging");
				break;
			}
			
			c = (a * functionX(b, A, B, C) - b * functionX(a, A, B, C)) /
					 (functionX(b, A, B, C) - functionX(a, A, B, C));
			//c = b - (functionX(b,A,B,C) * ((a-b)/(functionX(a,A,B,C)-functionX(b,A,B,C))));
			
			if (functionX(c, A, B, C) == 0) {
				break;
			}

			else if (functionX(c, A, B, C) * functionX(a, A, B, C) < 0) {
				b = c;
			}

			else {
				a = c;
			}
			if(Math.abs(functionX(c,A,B,C))< tol) {
				break;
			}
			
			System.out.println("Iteration: "+k+" \ta: "+a+"\tb: "+ b+"\tc: "+(double) c+"\terror: "+Math.abs(functionX(c,A,B,C)));
			
		}
		System.out.println("\nThe value of root is : " + (double)c);   
        
        
         
    } 
	static double functionX(double x,int A,int B,int C) 
    { 
        return (A*x*x+B*x+C); 
    } 
	/*--------------------------------------------------------------------------------------------------------------------------------*/
	/*--------------------------------------------------------------------------------------------------------------------------------*/
	/*--------------------------------------------------------------------------------------------------------------------------------*/
	/*--------------------------------------------------------------------------------------------------------------------------------*/
	/*--------------------------------------------------------------------------------------------------------------------------------*/
	/*--------------------------------------------------------------------------------------------------------------------------------*/
	
	/*---------------------------Newton-Raphson Methods--------------------------------------------------------*/
	static void startnewton_Raphson(double firstCoefficient, double secondCoefficent, double constant,
			double firstInterval, double secondInterval, double epsilon) {
		if (firstCoefficient != 0) {
			System.out.println("--------------First Root---------------------");
			newton_Raphson(firstInterval, epsilon, firstCoefficient, secondCoefficent, constant);
			System.out.println("--------------Second Root---------------------");
			firstInterval = -firstInterval;
			newton_Raphson(firstInterval, epsilon, firstCoefficient, secondCoefficent, constant);
		} else {
			System.out.println("-------------- Root---------------------");
			newton_Raphson(firstInterval, epsilon, firstCoefficient, secondCoefficent, constant);
		}

	}

	static void newton_Raphson(double x, double epsilon, double firstCoefficient, double secondCoefficent,
			double constant) {
		int i = 0;// step counter
		double oldRoot = 0;
		double h = function(firstCoefficient, secondCoefficent, constant, x)
				/ derivFunction(firstCoefficient, secondCoefficent, constant, x);
		while (Math.abs(h) >= epsilon) {
			h = function(firstCoefficient, secondCoefficent, constant, x)
					/ derivFunction(firstCoefficient, secondCoefficent, constant, x);

			// x(i+1) = x(i) - f(x) / f'(x)
			oldRoot = x;
			x = x - h;
			System.out.println(i + ".step	" + "OldRoot:" + Math.round(oldRoot * 10000.0) / 10000.0 + "	NewRood:"
					+ Math.round(x * 10000.0) / 10000.0 + "	Error:" + Math.round(h * 10000.0) / 10000.0);
			i++;
		}

		System.out.println("The value of the" + " root is : " + Math.round(x * 100.0) / 100.0);
	}

	static double function(double firstCoefficient, double secondCoefficient, double constant, double number) {
		double sum = 0;
		sum = sum + firstCoefficient * number * number;
		sum = sum + secondCoefficient * number;
		sum = sum + constant;
		return sum;
	}

	static double derivFunction(double firstCoefficient, double secondCoefficient, double constant, double number) {
		double sum = 0;
		if (firstCoefficient != 0) {
			sum = sum + firstCoefficient * 2 * number;
		}
		if (secondCoefficient != 0) {
			sum = sum + secondCoefficient;
		}
		return sum;
	}
	/*-------------------------------------------------------------------------------------------------------------------*/
}
