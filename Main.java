package bullscows;
import java.util.Random;
import java.util.Scanner;
public class Main {
    static Scanner sc = new Scanner(System.in);
        public static void main(String[] args) {
            System.out.println("Please, enter the secret code's length:");
            int length = 0;
            try {
                String lengt = sc.nextLine();
                length = Integer.parseInt(lengt);
            } catch (NumberFormatException e) {
                System.out.println("Error: \"abc 0 -7\" isn't a valid number.");
            }
            if (length <= 0) {
                System.out.println("Error");
            }



            if (length > 0) {
                String random = getRandomNumber(length);
                if (!random.contains("Error")) {
                    System.out.println("Okay, let's start a game!");

                    compare(random);
                }
            }

        }



        public static String getRandomNumber(int length) {

            if (length > 36) {
                String output = String.format("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                System.out.println(output);
                return output;
            }
            String candidateChars = "0123456789abcdefghijklmnopqrstuvwxyz";

            System.out.println(";Input the number of possible symbols in the code:");
            int len = sc.nextInt();

            if (len < length) {
                String out = String.format("Error: it's not possible to generate a code with a length of %d with %d unique symbols.",length,len);
                System.out.println(out);
                return out;
            } else if (len > 36) {
                String output = String.format("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                System.out.println(output);
                return output;
            }

            Random random = new Random();

            int[] digits = new int[len];
            StringBuilder sb = new StringBuilder();
            while (sb.length() < length) {

                char ch = candidateChars.charAt(random.nextInt(len));

                if (!sb.toString().contains(String.valueOf(ch))) {
                    sb.append(ch);
                }
            }
            System.out.print("The secret is prepared: ");
            for (int i = 0; i < length; i++) {
                System.out.print("*");
            }
            if (len <= 10) {
                System.out.println("(0-9, a-z)");
            } else {
                System.out.printf("(0-9, a-%c)",candidateChars.charAt(len - 1));
            }
            return sb.toString();


        }

        public static void compare(String n) {
            int count = 0;
            while (true) {
                count++;
                System.out.printf("Turn %d:\n", count);
                String number = sc.nextLine();
                String[] arrNum = number.split("");
                String[] arrn = n.split("");
                int countBull = 0;
                int countCow = 0;
                for (int i = 0; i < arrNum.length; i++) {
                    if (arrNum[i].equals(arrn[i])) {
                        countBull++;
                    }
                    for (int j = 0; j < arrNum.length; j++) {
                        if (i != j && arrNum[i].equals(arrn[j])) {
                            countCow++;
                        }
                    }
                }

                if (countBull == 0 && countCow == 0) {
                    System.out.printf("Grade: None\n");
                } else if (countBull > 0 && countCow == 0) {
                    if (countBull == arrn.length) {
                        System.out.printf("Grade: %d bulls\n", countBull);
                        System.out.println("Congratulations! You guessed the secret code.");
                        break;
                    } else {
                        System.out.printf("Grade: %d bull(s)\n", countBull);
                    }
                } else if (countCow > 0 && countBull == 0) {
                    System.out.printf("Grade: %d cow(s)\n", countCow);
                } else if (countBull < arrn.length && countCow > 0) {
                    System.out.printf("Grade: %d bull(s) and %d cow(s)\n", countBull, countCow);
                }
            }

        }

}
