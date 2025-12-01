import java.io.File;
import java.io.FileNotFoundException;
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
        int dialPos = 50;
        int dialMin = 0;
        int dialMax = 99;
        int countZeros = 0;

        while (scan.hasNextLine()) {
            String ln = scan.nextLine();
            String direction = ln.substring(0,1);
            String distanceStr = ln.substring(1);
            int distance = Integer.parseInt(distanceStr);

            for (int i = 0; i < distance; i++) {
                if (direction.equals("R")) {
                    if(dialPos == dialMax) dialPos = 0;
                    else dialPos ++;
                }

                if (direction.equals("L")) {
                    if(dialPos == dialMin) dialPos = 99;
                    else dialPos --;
                }

                if(dialPos == 0) countZeros ++;

            }
            System.out.println(dialPos);


        }

        System.out.println(countZeros);
    }
}
