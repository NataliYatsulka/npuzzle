import org.apache.commons.cli.*;

public class Main {

    public static void main(String[] args) {
        String path;
        String size;
        String heuristic;
        boolean mapPath;
        Options options = new Options();
        HelpFormatter formatter = new HelpFormatter();

        try {
            CommandLine cmd = new BasicParser().parse(options, args);

            path = cmd.getOptionValue("path");
            size = cmd.hasOption("size") ? cmd.getOptionValue("size") : null;
            heuristic = cmd.getOptionValue("heuristic");
            mapPath = cmd.hasOption("mapPath");
            Parsing.initArgs(path, size, heuristic);

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("npuzzle", options);
            System.exit(1);
        }

        System.out.println("Enf Of File");
    }
}
