import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        Option mapPath = new Option("m", "path", false, "view path solving npuzzle");
        path.setRequired(false);
        options.addOption(mapPath);

        Option original = new Option("o", "original", false, "initial npuzzle");
        path.setRequired(false);
        options.addOption(original);

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

        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).isEmpty() && isNumeric(list.get(i))) {
                puzzleSize = Integer.parseInt(list.get(i));
                break;
            }
        }
        System.out.println("puzzleSize = " + puzzleSize);
        if (puzzleSize < 3)
            throw new ParseException("ERROR:  Size of map cannot be < 3");

        int j = 0;
        int k = 0;
        String t = Integer.toString(puzzleSize);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("") || list.get(i).equals(t)) {
                k++;
                System.out.println("k = " + k);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals("") && !list.get(i).equals(t) && j < (list.size() - k)) {
                list1.set(j, list.get(i));
                j++;
            }
        }
        while (j < list1.size())
            list1.remove(j);

        System.out.println("qqq" + list1);

        if (list1.size() != puzzleSize)
            throw new ParseException("ERROR: Puzzle doesn't exist with this size");

//        k = 0;
//        for (int i = 0; i < list1.size(); i++) {
//            while (k++ < puzzleSize) {
//                for (int z = 0; z < puzzleSize; z++)
//                    list1.get(i).matches("(//s*//d+)");
//                System.out.println("true");
//            }
//        }


        String[] temp;
//        for (int i = 0; i < list1.size(); i++) {
//            String arr = list1.get(i);
//            for (j = 0; j < puzzleSize; j++) {
//                temp = arr.split("//s*");
//                System.out.println("temp = " + temp.length);
//                System.out.println(temp[0]);
//                if (!temp[0].isEmpty() && isNumeric(temp[0])){
//                    listInt.add(Integer.valueOf(temp[0]));
//                }
//                Pattern.compile("//s*//d+").matcher(arr);
//                while (Pattern.compile("//s*//d+").matcher(arr).find()) {
//                    arr =
//                }
        k = 0;
        for (int i = 0; i < list1.size(); i++) {
            temp = list1.get(i).split("\\s+");
            System.out.println(list1.get(i).charAt(5));
            k = k + temp.length;
            System.out.println("k = " + k);
//            System.out.println("temp = " + temp.length);
//            System.out.println(temp[0]);
        }
        if (!(k == puzzleSize * puzzleSize)) {
            throw new ParseException("ERROR:  Puzzle is not valid");
        }
        for (int i = 0; i < list1.size(); i++) {
            temp = list1.get(i).split("\\s+");
            for (j = 0; j < puzzleSize; j++) {
                if (temp.length != puzzleSize)
                    throw new ParseException("ERROR:  Bad row length");
                if (!isNumeric(temp[j]))
                    throw new ParseException("ERROR:  Puzzle should be only numeric");
                if (isNumeric(temp[j]))
                    listInt.add(Integer.valueOf(temp[j]));
            }
        }


//        System.out.println(list);
        System.out.println(listInt);
        return listInt;
    }
}
