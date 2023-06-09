import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main (String[]args){
        System.out.println("Введите арифмитеческое выражение в одну строку, через пробел");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        System.out.println(calc(input));

    }
    static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    static boolean isNumericRoman(String str) {
        RomanNumbers[] mass = RomanNumbers.values();
            for (RomanNumbers s : mass) {
                if (s.name().equals(str)) {
                    return true;
                }

            }

        return false;
    }

    static int RomanToArabicNumbers(String a) {
        RomanNumbers ar = RomanNumbers.valueOf(a);
        return ar.ordinal() + 1;
    }

    static String ArabicToRomanNumbers(int a) {
        RomanNumbers[] mass = RomanNumbers.values();
        if (a < 11) {
            RomanNumbers s = (mass[a - 1]);
            return s.name();
        }
        else {
            RomanNumbers s = mass[a / 10 + 8];
            if (a % 10 == 0) return s.name();
            else {
                RomanNumbers s1 = mass[a % 10 - 1];
                return s.name() + s1.name();
            }
        }
    }

    static String Calculate(int a, String b, int c) {

        int result;
        switch (b) {
            case "+" -> {
                result = (a + c);
                return Integer.toString(result);
            }
            case "-" -> {
                result = (a - c);
                return Integer.toString(result);
            }
            case "*" -> {
                result = (a * c);
                return Integer.toString(result);
            }
            case "/" -> {
                result = (a / c);
                return Integer.toString(result);
            }
            default -> {
                System.out.println("Введен некорректный знак вычисления");
                throw new RuntimeException("Строка не соответствует требованиям");
            }
        }
    }

    static String calc(String input) throws ArrayIndexOutOfBoundsException{
        try {
            String[] words = input.split(" ");
            if (words.length > 3) {
                System.out.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                throw new RuntimeException("Строка не соответствует условиям");
            }
            String a = words[0];
            String b = words[1];
            String c = words[2];




            if (isNumeric(a) & (isNumeric(c))) {
                int a1 = Integer.parseInt(a);
                int c1 = Integer.parseInt(c);
                if (a1 >= 1 & a1 <= 10 & c1 >= 1 & c1 <= 10) {
                    return Calculate(a1, b, c1);
                } else {
                    System.out.println("Введенные числа должны быть от 1 до 10 включительно");
                    throw new RuntimeException("Строка не соответствует условиям");
                }
            } else if (isNumeric(a) & isNumericRoman(c)) {
                System.out.println("Умеем работать только с арабскими или римскими цифрами одновременно");
                throw new RuntimeException("Строка не соответствует условиям");
            } else if (isNumericRoman(a) & isNumeric(c)) {
                System.out.println("Умеем работать только с арабскими или римскими цифрами одновременно");
                throw new RuntimeException("Строка не соответствует условиям");
            } else if (isNumericRoman(a) & isNumericRoman(c)) {
                int a1 = RomanToArabicNumbers(a);
                int c1 = RomanToArabicNumbers(c);
                if (a1 >= 1 & a1 <= 10 & c1 >= 1 & c1 <= 10) {
                    String s = Calculate(a1, b, c1);

                    if (Integer.parseInt(s) > 0) {

                        return ArabicToRomanNumbers(Integer.parseInt(s));

                    } else {
                        System.out.println("В римской системе нет отрицательных чисел и нуля");
                        throw new RuntimeException("Строка не соответствует условиям");
                    }
                } else {
                    System.out.println("Введенные числа должны быть от 1 до 10 включительно");
                    throw new RuntimeException("Строка не соответствует условиям");
                }
            } else {
                System.out.println("Введите строку соответствующую условиям");
                throw new RuntimeException("Строка не соответствует условиям");
            }
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Строка не является математической операцией");
            throw new RuntimeException("Введите строку соответствующую условиям");
           }
            }


    }


