package abgabeAufgabe2;

import de.medieninf.ads.ADSTool;

public class Aufgabe2 {

    private static char[] summand1;
    private static char[] summand2;

    public static void main(String[] args) {
        if(args.length == 0) addNumbersInFile(ADSTool.readStringArray("src/abgabeAufgabe2/langeZahlen.dat"));
        else addNumbersInFile(ADSTool.readStringArray("src/abgabeAufgabe2/" + args[0]));
    }

    static void swap(){
        char[] temp = summand1;
        summand1 = summand2;
        summand2 = temp;
    }

    public static void addNumbersInFile(String[] lines) {
        for (int i = 0; i < lines.length; i += 2) {
            summand1 = langeZahl(lines[i]);
            summand2 = langeZahl(lines[i + 1]);

            // swap numbers because number1 needs to be bigger than number2
            if(summand1.length < summand2.length) swap();

            // sum
            System.out.println(String.valueOf(add(summand1, summand2)));
        }
    }

    static char[] add(char[] number1, char[] number2) {

        String result = "";
        int digitDiff = number1.length - number2.length;
        int sum, carry = 0;

        for (int i = 0; i < number1.length; i++) {
            if(i < number1.length - digitDiff) sum = Integer.parseInt(String.valueOf(number1[i])) + Integer.parseInt(String.valueOf(number2[i])) + carry;
            else sum = Integer.parseInt(String.valueOf(number1[i])) + carry;
            result += String.valueOf(sum % 10);
            carry = sum / 10;
        }

        if(carry != 0) result += "1";

        return result.toCharArray();
    }

    // turns a String in a char Array with inverted order
    static char[] langeZahl(String number) {
        char[] chars = number.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            // swap the values at the left and right indices
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;

            // move the left and right index pointers in toward the center
            left++;
            right--;
        }
        return chars;
    }
}
