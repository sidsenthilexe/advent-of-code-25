import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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


        ArrayList<BigInteger> freshRangeStart = new ArrayList<>();
        ArrayList<BigInteger> freshRangeEnd = new ArrayList<>();

        for (String r : fresh) {
            String startS = r.substring(0, r.indexOf("-"));
            String endS = r.substring(r.indexOf("-") + 1);

            BigInteger start = new BigInteger(startS);
            BigInteger end = new BigInteger(endS);
            freshRangeStart.add(start);
            freshRangeEnd.add(end);





        }

        for (int i = 0; i < freshRangeStart.size(); i++) {
            for (int j = i+1; j < freshRangeStart.size(); j++) {
                if (freshRangeStart.get(j).compareTo(freshRangeStart.get(i)) < 0) {
                    BigInteger tempStart = freshRangeStart.get(i);
                    BigInteger tempEnd = freshRangeEnd.get(i);
                    freshRangeStart.set(i, freshRangeStart.get(j));
                    freshRangeEnd.set(i, freshRangeEnd.get(j));
                    freshRangeStart.set(j, tempStart);
                    freshRangeEnd.set(j, tempEnd);
                }
            }
        }

        ArrayList<BigInteger> mergedStart = new ArrayList<>();
        ArrayList<BigInteger> mergedEnd = new ArrayList<>();

        for (int i = 0; i < freshRangeStart.size(); i++) {
            BigInteger start = freshRangeStart.get(i);
            BigInteger end = freshRangeEnd.get(i);

            if (mergedStart.isEmpty()) {
                mergedStart.add(start);
                mergedEnd.add(end);
            } else {
                int lastId = mergedStart.size()-1;
                BigInteger lastEnd = mergedEnd.get(lastId);

                if (start.compareTo(lastEnd.add(BigInteger.ONE)) <= 0) {

                    if (end.compareTo(lastEnd) > 0) {
                        mergedEnd.set(lastId, end);
                    }

                } else {
                    mergedStart.add(start);
                    mergedEnd.add(end);
                }

            }
        }



        BigInteger totalFresh = BigInteger.ZERO;
        for (int i = 0; i < mergedStart.size(); i++) {
            totalFresh = totalFresh.add(mergedEnd.get(i).subtract(mergedStart.get(i)).add(BigInteger.ONE));
        }





        System.out.println(totalFresh);


    }
}



