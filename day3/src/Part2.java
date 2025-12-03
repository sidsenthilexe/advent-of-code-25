import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Part2 {
    public static void main(String[] args) throws Exception {
        String path = "input.txt";

        File file = new File(path);

        if (!file.exists()) {
            System.err.println("Checked path: " + file.getAbsolutePath() + " and failed");
        }
        try (Scanner scan = new Scanner(file)) {
            parseScan(scan);
        } catch (FileNotFoundException exception) {
            System.err.println("Unable to open " + exception.getMessage());
        }

    }

    public static void parseScan(Scanner scan) {
        long totalJoltage = 0;

        //parse each bank
        while (scan.hasNextLine()) {
            String bank = scan.nextLine();
            String[] bankElementsArr = bank.split("");
            ArrayList<String> bankElements = new ArrayList<>(Arrays.asList(bankElementsArr));

            String allDigits = "";

            int prevDigitIndex = -1;

            for (int k = 0; k < 12; k++) {
                allValsLoop:
                for (int j = 9; j >= 0; j--) {
                    String checkVal = String.valueOf(j);

                    for (int i = prevDigitIndex + 1; i <= bankElements.size() - (12-k); i++) {
                        String currentVal = bankElements.get(i);

                        if (currentVal.equals(checkVal)) {
                            prevDigitIndex = i;
                            allDigits += currentVal;
                            break allValsLoop;
                        }
                    }

                }
            }

            totalJoltage += Long.parseLong(allDigits);




        }

        System.out.println(totalJoltage);






    }
}



