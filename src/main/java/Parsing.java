import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Parsing {
    public static int puzzleSize;

    public static void usage(HelpFormatter formatter, Options options) {
        formatter.printHelp("npuzzle", options);
        System.exit(1);
    }

    public static Integer[] generatePuzzle(int count) {
        int size = count * count;

        Integer[] arr = new Integer[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
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

//        Option mapPath = new Option("m", "path", false, "view path solving npuzzle");
//        path.setRequired(false);
//        options.addOption(mapPath);

//        Option original = new Option("o", "original", false, "initial npuzzle");
//        path.setRequired(false);
//        options.addOption(original);

        Option koeficient = new Option("k", "koeficient", true, "koeficient");
        path.setRequired(false);
        options.addOption(koeficient);

        return options;
    }

    public static void initArgs(String path, String size, String heuristic, HelpFormatter formatter, Options options) throws ParseException {
        int min = 3;

        if (path != null && size != null) {
            throw new ParseException("ERROR:  You can use only one of path/size");
        }
        if (path == null && size == null) {
            throw new ParseException("ERROR:  Both path & size cannot be null");
        }
        if (size != null && Integer.parseInt(size) < min) {
            throw new ParseException("ERROR:  Both path & size cannot be null");
        }
        if (heuristic != null && !heuristic.equals("m") && !heuristic.equals("h") && !heuristic.equals("e")) {
            throw new ParseException("ERROR:  Heuristic can be only m/h/e, not " + heuristic);
        }
    }

    public static boolean checkMassIntegers(List<Integer> list, int size) throws ParseException {
        Collections.sort(list);
        System.out.println("listSort = " + list);
        List<Integer> range = IntStream.rangeClosed(0, size * size - 1)
                .boxed().collect(Collectors.toList());

        System.out.println("range = " + range);
        if (!list.equals(range))
            throw new ParseException("ERROR:  Bad number in the puzzle");
        return true;
    }

    public static String readTextFile(String fileName) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(fileName)));
        return content;
    }

    public static List<String> readTextFileByLines(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        return lines;
    }

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    public static List<Integer> checkParsingFile(List<String> list) throws ParseException {
        List<Integer> listInt = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String[] res = list.get(i).split("#");
            list.set(i, res[0]);
        }
        List<String> list1 = list;

        try {
            for (int i = 0; i < list.size(); i++) {
                list.set(i, list.get(i).trim());
                if (!list.get(i).isEmpty() && isNumeric(list.get(i))) {
//                    if (Integer.parseInt(list.get(i))
                    puzzleSize = Integer.parseInt(list.get(i));
                    break;
                }

            }
        } catch (NumberFormatException ex) {
            System.out.println("ERROR:  Too big number!");
            System.exit(1);
        }

//        System.out.println("puzzleSize = " + puzzleSize);
        if (puzzleSize < 3)
            throw new ParseException("ERROR:  Size of map cannot be < 3");

        int j = 0;
        int k = 0;
        String t = Integer.toString(puzzleSize);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("") || list.get(i).equals(t)) {
                k++;
//                System.out.println("11k = " + k);
            }
        }
//        System.out.println("list.size = " + list.size());
//        System.out.println("list.sizze - k = " + (list.size() - k));
        if (list.size() - k != puzzleSize)
            throw new ParseException("ERROR:  Not the same amount of row as puzzzleSize");

        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals("") && !list.get(i).equals(t) && j < (list.size() - k)) {
                list1.set(j, list.get(i));
                j++;
            }
        }

        while (j < list1.size()) {
            list1.remove(j);
        }

//        System.out.println("qqq" + list1);

        if (list1.size() != puzzleSize)
            throw new ParseException("ERROR: Puzzle doesn't exist with this size");

        String[] temp;
        k = 0;

        for (int i = 0; i < list1.size(); i++) {
            temp = list1.get(i).trim().split("\\s+");
//            System.out.println(list1.get(i).charAt(5));
            k = k + temp.length;
            System.out.println("k = " + k);
            if (k % puzzleSize != 0)
                throw new ParseException("ERROR:  Bad amount of element in the row");
        }
        if (!(k == puzzleSize * puzzleSize)) {
            throw new ParseException("ERROR:  Puzzle is not valid");
        }
        for (int i = 0; i < list1.size(); i++) {
            temp = list1.get(i).trim().split("\\s+");
            for (j = 0; j < puzzleSize; j++) {
//                System.out.println("temp.lenght = " + temp.length);
                if (temp.length != puzzleSize)
                    throw new ParseException("ERROR:  Bad row length");
                if (!isNumeric(temp[j]))
                    throw new ParseException("ERROR:  Puzzle should be only numeric");
                if (isNumeric(temp[j])) {
                    try {
                        listInt.add(Integer.valueOf(temp[j]));
                    } catch (NumberFormatException ex) {
                        System.out.println("ERROR:  Too big number!");
                        System.exit(1);
                    }
                }
            }
        }

        List<Integer> listCopy = new ArrayList<>();
        listCopy.addAll(listInt);
        checkMassIntegers(listCopy, puzzleSize);

        return listInt;
    }
}
