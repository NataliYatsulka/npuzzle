import org.apache.commons.cli.*;

public class Main {

    public static void main(String[] args) {
        String path;
        String size;
        String heuristic;
        boolean mapPath;
        boolean original;

        Options options = Parsing.parceInputArgs(args);
        HelpFormatter formatter = new HelpFormatter();

        try {
            CommandLine cmd = new BasicParser().parse(options, args);

            path = cmd.getOptionValue("path");
            size = cmd.hasOption("size") ? cmd.getOptionValue("size") : null;
            heuristic = cmd.getOptionValue("heuristic");
            mapPath = cmd.hasOption("mapPath");
            original = cmd.hasOption("original");
            Parsing.initArgs(path, size, heuristic);

            System.out.println("path = " + path + "\nsize = " + size + "\nheuristic = " + heuristic + "\nmapPath = "
                    + mapPath + "\norig = " + original);

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("npuzzle", options);
            System.exit(1);
        }

        System.out.println("\nEnf Of File");
    }
}
