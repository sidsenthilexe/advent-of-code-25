import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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

        long count = 0;
        String input = scan.nextLine();
        System.out.println(input);
        String[] arr = input.split(",");

        for (String in : arr) {

            String[] split = in.split("-");

            String num1 = split[0];
            long num1l = Long.parseLong(num1);
            String num2 = split[1];
            long num2l = Long.parseLong(num2);

            for (long currNum = num1l; currNum <= num2l; currNum ++) {
                String currNumS = String.valueOf(currNum);
                long maxAdd = currNumS.length()/2;
                if (maxAdd == 0) maxAdd = 1;

                for (long i = 1; i <= maxAdd; i++) {
                    if (currNumS.length() % i != 0) continue;
                    if (currNumS.length()/i < 2) continue;
                    boolean isAllEqual = true;
                    for(long j = i; j+i <= currNumS.length(); j+= i) {
                        if(!currNumS.substring((int)j, (int)(j+i))
                                .equals(currNumS.substring(0, (int)i))) {
                            isAllEqual = false;
                            break;
                        }
                    }

                    if (isAllEqual) {
                        count += currNum;
                        break;
                    }

                }

            }


        }
        System.out.println(count);





    }
}



