import org.apache.commons.cli.*;

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
                if (i % Integer.parseInt(size) == Integer.parseInt(size) -1 )
                    System.out.println();
            }
        }

        System.out.println("\nEnf Of File");
    }
}
