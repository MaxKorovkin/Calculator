package Calculator;

import java.io.IOException;
import java.util.Scanner;

/**
 * The type Calculator.
 *  т/з
 *  Консольный калькулятор производит простые  вычисления с числами в диапазоне от 1-10 включительно
 *  с римскими и арабскими цифрами
 */
public class Calculator {
    String[] romanDigits =  {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};



    /**
     * метод Calculate.
     * Калькулятор - производит непосредственно вычисления
     *
     * @param a the  первое число
     * @param b the  второе число
     * @param o the  операция
     */
    public static void calculate (int a, int b, String o) {
        switch (o) {
            case "+" -> System.out.println("Результат: " + (a + b));
            case "-" -> System.out.println("Результат: " + (a - b));
            case "*" -> System.out.println("Результат: " + (a * b));
            case "/" -> System.out.println("Результат: " + (a / b));
        }
    }

    /**
     * Calculate roman digits int.
     * Специальный калькулятор, чтобы вычислять отрицательное значение
     *
     * @param a the первый операнд
     * @param b the второй операнд
     * @param o the операция
     * @return the int возвращает результат операции
     */
    public int calculateRomanDigits (int a, int b, String o) {
        int romanDigit = 0;
        switch (o) {
            case "+" -> romanDigit = a + b;
            case "-" -> romanDigit = a - b;
            case "*" -> romanDigit = a * b;
            case "/" -> romanDigit = a / b;
        }
        return romanDigit;
    }

    /**
     * Is roman string.
     * проверяет, является ли сторока римской цифрой от 1 до 10
     *
     * @param str строка, которую необходимо проверить
     * @return String возвращает римскую цифру
     */
    public String isRoman (String str) {
        String string = null;
        for (String romanDigit : romanDigits) {
            if (str.equals(romanDigit)) {
                string = romanDigit;
                break;
            }
        }
        return string;
    }

    /**
     * Converter int.
     * конвертирует римские цифры  в арабские
     *
     * @param str the str
     * @return the int
     */
    public int converter1 (String str) {
        int a = 0;
        switch (str) {
            case "I"  -> a = 1;
            case "II" -> a = 2;
            case "III" -> a = 3;
            case "IV" -> a = 4;
            case "V" -> a = 5;
            case "VI" -> a = 6;
            case "VII" -> a = 7;
            case "VIII" -> a = 8;
            case "IX" -> a = 9;
            case "X" -> a = 10;
        }

        return a;
    }

    /**
     * Converter 2 string.
     * конвертирует арабские цифры в римские,
     * использует специально написанный для этого метод конвертации
     * @param a the a
     * @return the string - результат метода ConverterToRomanDigits.convert();
     */
    public String converter2(int a) {
        return ConverterToRomanDigits.convert(a);
    }

    /**
     * Check the digits boolean.
     * проверяет цифры в заданном диапазоне 1 - 10
     *
     * @param a the a
     * @param b the b
     * @return the boolean
     */
    public boolean checkTheDigits (int a, int b) {
        return (a >= 1 && a <= 10) && (b >= 1 && b <= 10);
    }


    /**
     * Check operation boolean.
     * проверяет валидность арифметической операции
     * @param operations      the operations
     * @param operationString the operation string
     * @return the boolean
     */
    public boolean checkOperation(String[] operations, String[] operationString) {
        boolean isMathOperation = false;
        for (String s : operations) {
            if (s.equals(operationString[1])) {
                isMathOperation = true;
                break;
            }
        }
        return isMathOperation;
    }



    public static void main(String[] args) throws IOException {
        int a;
        int b;
        String operation;
        String[] operations = {"+", "-", "*", "/"};
        Calculator calculator = new Calculator();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println();
            System.out.println("введите операцию:");
            String[] operationString = scanner.nextLine().split(" ");

            if(!calculator.checkOperation(operations, operationString)) {
                throw new IOException("Ошибка! Калькулятор выполняет ТОЛЬКО операции " +
                        "сложения, вычитания, умножения и деления");
            }

            if (operationString.length > 3) {
                throw new IOException("формат математической операции не удовлетворяет заданию " +
                        "- два операнда и один оператор (+, -,) ");

            } else if (operationString.length < 3) {
                throw new IOException("строка не является математической операцией");
            }

            if (operationString[0].matches("\\d+") && operationString[2].matches("\\d+")) {
                a = Integer.parseInt(operationString[0]);
                b = Integer.parseInt(operationString[2]);
                if (!calculator.checkTheDigits(a, b)) {
                    throw new IOException("Ошибка! Числа должны быть от 1 до 10 включительно.");
                }
                operation = operationString[1];
                calculate(a, b, operation);
            } else if (operationString[0].matches("\\d+(\\.\\d+)")
                    || operationString[2].matches("\\d+(\\.\\d+)")) {
                throw new IOException("Это дробные числа, так нельзя!!!");
            } else if (!operationString[0].matches("\\d+") || !operationString[2].matches("\\d+")) {
                if (operationString[0].equals(calculator.isRoman(operationString[0]))
                        && operationString[2].equals(calculator.isRoman(operationString[2]))) {
                    int result;
                    a = calculator.converter1(operationString[0]);
                    b = calculator.converter1(operationString[2]);
                    operation = operationString[1];
                    result = calculator.calculateRomanDigits(a, b, operation);
                    if (result >= 1) {
                        String romanString = calculator.converter2(result);
                        System.out.println("Результат: " + romanString);
                    } else {
                        throw new IOException("в римской системе нет отрицательных чисел");
                    }
                } else if (operationString[0].equals(calculator.isRoman(operationString[0]))
                        && operationString[2].matches("\\d+")
                        || (operationString[0].matches("\\d+")
                        && operationString[2].equals(calculator.isRoman(operationString[2])))) {
                    throw new IOException("используются одновременно разные системы счисления");
                } else {
                    throw new IOException("Строка не является числом или математической операцией");
                }
            }
        }
    }
}
