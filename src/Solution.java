import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the hackerlandRadioTransmitters function below.
    private static int hackerlandRadioTransmitters(int[] dists, int k) {
        //Sort the array of house coordinates
        Arrays.sort(dists);
        //Position the antennae until the last house is covered
        //index of the first uncovered house, if no such houses - ind = -1;
        int currInd = 0;
        int antCount = 0;
        while (currInd >= 0) {

            currInd = positionAntenna(dists, currInd, k);
            antCount++;
        }
        return antCount;
    }

    private static int positionAntenna(int[] dists, int currInd, int k) {
        int iA = currInd;
        while (iA < dists.length && dists[iA] - k <= dists[currInd]) {
            iA++;
        }
        int optimalAntenna = iA - 1;
        int lastCovered = optimalAntenna;
        while (lastCovered < dists.length && dists[lastCovered] - k <= dists[optimalAntenna]) {
            lastCovered++;
        }
        if (lastCovered == dists.length) {
            return -1;
        }
        return lastCovered;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] x = new int[n];

        String[] xItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int xItem = Integer.parseInt(xItems[i]);
            x[i] = xItem;
        }

        int result = hackerlandRadioTransmitters(x, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
