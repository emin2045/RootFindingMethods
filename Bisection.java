
public class Bisection {

    private float EPSILON = (float) 0.01;
    private int coefficient_one, coefficient_two, coefficient_three;

    public Bisection(int coefficient_one, int coefficient_two, int coefficient_three, int a, int b) {
        this.coefficient_one = coefficient_one;
        this.coefficient_two = coefficient_two;
        this.coefficient_three = coefficient_three;
        bisection(a, b);
    }

    public Bisection(int coefficient_one, int coefficient_two, int coefficient_three, int a, int b, double tolerance) {
        this.coefficient_one = coefficient_one;
        this.coefficient_two = coefficient_two;
        this.coefficient_three = coefficient_three;
        EPSILON = (float) tolerance;
        bisection(a, b);
    }

    double func(double x) {
        return coefficient_one * x * x + coefficient_two * x + coefficient_three;
    }

    void bisection(double a, double b) {
        if (func(a) * func(b) > 0) {
            System.out.println("You have not assumed right a and b");
            return;
        }

        double c = a;
        while ((b - a) >= EPSILON) {
            System.out.println("b - a = " + (b - a) + ">= " + EPSILON);

            c = (a + b) / 2;
            System.out.println("Middle point = " + c);

            if (func(c) == 0.0) {
                break;
            } else if (func(c) * func(a) < 0) {
                b = c;
                System.out.println("Decide the side new b is c");
            } else {
                a = c;
                System.out.println("Decide the side new a is c");
            }

        }

        System.out.printf("The value of root is : %.4f", c);
    }

}
