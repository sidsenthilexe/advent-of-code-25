import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Part1 {
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

          int digit1Index = 0;
          String digit1 = "";
          String digit2 = "";

          val1Loop:
          for (int j = 9; j >= 0; j--) {

              String checkVal = String.valueOf(j);

              for (int i = 0; i+1 < bankElements.size(); i++) {
                  String currentVal = bankElements.get(i);

                  if (currentVal.equals(checkVal)) {
                      digit1Index = i;
                      digit1 = currentVal;
                      break val1Loop;
                  }
              }

          }

          val2Loop:
          for (int j = 9; j >= 0; j--) {
              String checkVal = String.valueOf(j);

              for (int i = digit1Index+1; i < bankElements.size(); i++) {
                  String currentVal = bankElements.get(i);

                  if (currentVal.equals(checkVal)) {
                      digit2 = currentVal;
                      break val2Loop;
                  }
              }
          }

          String finalValS = digit1+digit2;
          totalJoltage += Long.parseLong(finalValS);




        }

        System.out.println(totalJoltage);






    }
}



