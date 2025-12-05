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

        ArrayList<String> fullList = new ArrayList<>();

        while (scan.hasNextLine()) {
            fullList.add(scan.nextLine());
        }

        int blankLineIndex = 0;
        for (int i = 0; i < fullList.size(); i++) {
            if (fullList.get(i).isEmpty()) {
                blankLineIndex = i;
                break;
            }
        }

        System.out.println(blankLineIndex);

        ArrayList<String> fresh = new ArrayList<>();


        ArrayList<String> items = new ArrayList<>();

        for (int i = 0; i < blankLineIndex; i++) {
            fresh.add(fullList.get(i));
        }

        for (int i = blankLineIndex + 1; i < fullList.size(); i++) {
            items.add(fullList.get(i));
        }


        ArrayList<Long> freshRangeStart = new ArrayList<>();
        ArrayList<Long> freshRangeEnd = new ArrayList<>();

        for (String r : fresh) {
            String startS = r.substring(0, r.indexOf("-"));
            String endS = r.substring(r.indexOf("-")+1);

            long start = Long.parseLong(startS);
            long end = Long.parseLong(endS);
            freshRangeStart.add(start);
            freshRangeEnd.add(end);


        }


        int amtFreshItems = 0;

        for (String i : items) {
            long itemLong = Long.parseLong(i);

            for (int j = 0; j < freshRangeStart.size(); j++) {
                if (freshRangeStart.get(j)<=itemLong && itemLong<=freshRangeEnd.get(j)) {
                    amtFreshItems++;
                    break;
                }
            }

        }

        System.out.println(amtFreshItems);


    }
}



