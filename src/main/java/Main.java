import org.apache.commons.cli.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String path = null;
        String size = null;
        Options options = Parsing.parsingInputArgs(args);
        String heuristic;
        boolean mapPath;
        boolean original;

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

//delete
        if (size != null) {
            for (int i = 0; i < Integer.parseInt(size) * Integer.parseInt(size); i++) {
                System.out.print(randomMap[i] + "   ");
                if (i % Integer.parseInt(size) == Integer.parseInt(size) - 1)
                    System.out.println();
            }
        }

        String input;
        List<Integer> list;
        try {
            if (path != null) {
                File f = new File(path);
                if(!f.exists() || f.isDirectory()) {
                    throw new FileNotFoundException("ERROR:   File not found");
                }
                input = Parsing.readTextFile(path);
                list = Parsing.checkParsingFile(Parsing.readTextFileByLines(path));
//                System.out.println(Parsing.puzzleSize);
            }
        } catch (IOException | ParseException ex) {
            System.out.println(ex.getMessage());
            Parsing.usage(formatter, options);
        }

        System.out.println("\nEnf Of File");
    }
}
