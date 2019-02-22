import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.util.*;

public class Parsing {

    public static void usage(HelpFormatter formatter, Options options) {
        formatter.printHelp("npuzzle", options);
        System.exit(1);
    }

    public static Integer[] removeTheElement(Integer[] arr, int index) {

        // If the array is empty
        // or the index is not in array range
        // return the original array
        if (arr == null
                || index < 0
                || index >= arr.length) {

            return arr;
        }

        // Create another array of size one less
        Integer[] anotherArray = new Integer[arr.length - 1];

        // Copy the elements except the index
        // from original array to the other array
        for (int i = 0, k = 0; i < arr.length; i++) {

            // if the index is
            // the removal element index
            if (i == index) {
                continue;
            }

            // if the index is not
            // the removal element index
            anotherArray[k++] = arr[i];
        }

        // return the resultant array
        return anotherArray;
    }

    public static Integer[] generatePuzzle(int count) {
        int size = count * count;

        Integer[] arr = new Integer[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        int[] mas = new int[size];
        Random r = new Random();
        int arrLen = size;
        int k = 0;

        for (int i = 0; i < mas.length; i++) {
            k = r.nextInt(arrLen);
            mas[i] = arr[k];
            removeTheElement(arr, k);
            arrLen--;
        }
        Collections.shuffle(Arrays.asList(arr));
        System.out.println(Arrays.toString(arr));

        return arr;


    }

    public static Integer[] readFromFile(String path) {
        int size = 0;
        Integer[] map = new Integer[size];
        return map;
    }

    public static Options parsingInputArgs(String[] args) {
        Options options = new Options();

        Option path = new Option("p", "path", true, "path to your file with npuzzle");
        path.setRequired(false);
        options.addOption(path);

        Option size = new Option("s", "size", true, "size of random npuzzle");
        path.setRequired(false);
        options.addOption(size);

        Option heuristic = new Option("h", "heuristic", true, "one of heuristic: MANHATTAN, HAMMING or EUCLIDIAN");
        path.setRequired(true);
        options.addOption(heuristic);

        Option mapPath = new Option("m", "path", false, "view path solving npuzzle");
        path.setRequired(false);
        options.addOption(mapPath);

        Option original = new Option("o", "original", false, "initial npuzzle");
        path.setRequired(false);
        options.addOption(original);

        return options;
    }

    public static void initArgs(String path, String size, String heuristic, HelpFormatter formatter, Options options) {
        int min = 3;

        if (path != null && size != null) {
            System.out.println("ERROR:  You can use only one of path/size");
            usage(formatter, options);
        }
        if (path == null && size == null) {
            System.out.println("ERROR:  Both path & size cannot be null");
            usage(formatter, options);
        }
        if (size != null && Integer.parseInt(size) < min) {
            System.out.println("ERROR:  Size of map cannot be < " + min);
            usage(formatter, options);
        }
        if (heuristic != null && !heuristic.equals("m") && !heuristic.equals("h") && !heuristic.equals("e")) {
            System.out.println("ERROR:  Heuristic can be only m/h/e, not " + heuristic);
            usage(formatter, options);
        }
    }
}
