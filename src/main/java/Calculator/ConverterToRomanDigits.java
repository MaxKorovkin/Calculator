package Calculator;

public class ConverterToRomanDigits {
    int a;

    public ConverterToRomanDigits(int a) {
        this.a = a;
    }

    public static  String romanDigit (int n, String one, String five, String ten){

        if(n >= 1)
        {
            if(n == 1)
            {
                return one;
            }
            else if (n == 2)
            {
                return one + one;
            }
            else if (n == 3)
            {
                return one + one + one;
            }
            else if (n==4)
            {
                return one + five;
            }
            else if (n == 5)
            {
                return five;
            }
            else if (n == 6)
            {
                return five + one;
            }
            else if (n == 7)
            {
                return five + one + one;
            }
            else if (n == 8)
            {
                return five + one + one + one;
            }
            else if (n == 9)
            {
                return one + ten;
            }

        }
        return "";
    }

    public static String convert(int number) {

        if (number == 100) {
            return "C";
        }


        String romanOnes = romanDigit( number%10, "I", "V", "X");
        number /=10;
        String romanTens = romanDigit( number%10, "X", "L", "C");
        number /=10;

        String result = romanTens + romanOnes;
        return result;
    }
}

