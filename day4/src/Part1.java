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

        int accessible = 0;

        ArrayList<String> full = new ArrayList<>();
        while (scan.hasNextLine()) {
            full.add(scan.nextLine());
        }

        for (int i = 0; i < full.size(); i++) {
            String line = full.get(i);

            for (int j = 0; j < line.length(); j++) {

                int sidesCovered = 0;

                String c = line.substring(j, j+1);

                if (c.equals("@")) {

                boolean tl = false, t = false, tr = false, l = false, r = false, bl = false, b = false, br = false;

                if(i > 0) {
                    if (j > 0) tl = full.get(i - 1).charAt(j - 1) == '@';
                    t = full.get(i-1).charAt(j) == '@';
                    if (j+1 < line.length()) tr = full.get(i-1).charAt(j+1) == '@';
                }

                if (i + 1 < full.size()) {
                    if (j > 0) bl = full.get(i + 1).charAt(j - 1) == '@';
                    b = full.get(i + 1).charAt(j) == '@';
                    if (j + 1 < line.length()) br = full.get(i + 1).charAt(j + 1) == '@';
                }

                if (j > 0) l = line.charAt(j-1) == '@';
                if (j+1 < line.length()) r = line.charAt(j+1) == '@';

                if (tl) sidesCovered++;
                if (t) sidesCovered++;
                if (tr) sidesCovered++;
                if (l) sidesCovered++;
                if (r) sidesCovered++;
                if (bl) sidesCovered++;
                if (b) sidesCovered++;
                if (br) sidesCovered++;

                if (sidesCovered < 4) accessible++;
                }


            }

        }
        System.out.println(accessible);


    }
}



