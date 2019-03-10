import org.apache.commons.cli.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main2 {

    public static void main(String[] args) {
        String path = null;
        String size = null;
        Options options = Parsing.parsingInputArgs(args);
        String heuristic = "m";
        boolean mapPath;
        boolean original;
        int puzzleSize = 0;

        HelpFormatter formatter = new HelpFormatter();

        try {
            CommandLine cmd = new BasicParser().parse(options, args);

            path = cmd.getOptionValue("path");
            size = cmd.hasOption("size") ? cmd.getOptionValue("size") : null;
            heuristic = cmd.getOptionValue("heuristic");
            mapPath = cmd.hasOption("mapPath");
            original = cmd.hasOption("original");
            Parsing.initArgs(path, size, heuristic, formatter, options);

            System.out.println("path = " + path + "\nsize = " + size + "\nheuristic = " + heuristic + "\nmapPath = "
                    + mapPath + "\norig = " + original);

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            Parsing.usage(formatter, options);
        }
        Integer[] randomMap = (size != null) ? Parsing.generatePuzzle(Integer.parseInt(size)) : Parsing.readFromFile(path);
        List<Integer> listInt = new ArrayList<>();
        for (int i = 0; i < randomMap.length; i++) {
            listInt.add(randomMap[i]);
        }
//        System.out.println("lisssst = " + listInt);
//        System.out.println("Integer.parseInt(size) = " + Integer.parseInt(size));
        List<Integer> listCopy = new ArrayList<>();
        listCopy.addAll(listInt);
        try {
            if (size != null) {
                Parsing.checkMassIntegers(listCopy, Integer.parseInt(size));
                puzzleSize = Integer.parseInt(size);
            }
        } catch (ParseException ex) {
            Parsing.usage(formatter, options);
        }

//delete
        if (size != null) {
            for (int i = 0; i < Integer.parseInt(size) * Integer.parseInt(size); i++) {
                System.out.print(randomMap[i] + "  ");
                if (i % Integer.parseInt(size) == Integer.parseInt(size) - 1)
                    System.out.println();
            }
        }


        String input;
//        List<Integer> list;
        try {
            if (path != null) {
                File f = new File(path);
                if (!f.exists() || f.isDirectory() || !f.canRead()) {
                    throw new FileNotFoundException("ERROR:   File not found");
                }
                input = Parsing.readTextFile(path);
                listInt = Parsing.checkParsingFile(Parsing.readTextFileByLines(path));
//                System.out.println(Parsing.puzzleSize);
                System.out.println("list = " + listInt);
                puzzleSize = Parsing.puzzleSize;
            }
        } catch (IOException | ParseException ex) {
            System.out.println(ex.getMessage());
            Parsing.usage(formatter, options);
        }
        int[] mas = listInt.stream()
                .mapToInt(Integer::intValue)
                .toArray();
        System.out.println(Arrays.toString(mas));
        System.out.println("PPUUUUUUUZZZLLELELLE = " + puzzleSize);
        if (!State.checkState(mas, puzzleSize))
            System.out.println("There are no solution of this puzzle");
        else {
            new Algo(mas, heuristic).startSearch();
        }
        Heuristics.puzzleGoal(puzzleSize);

        System.out.println("\nEnf Of File");
    }
}
