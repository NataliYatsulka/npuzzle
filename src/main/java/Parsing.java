import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class Parsing {

    public static Options parceInputArgs(String[] args){
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

    public static void initArgs(String path, String size, String heuristic) {
        int min = 3;

        if (path != null && size != null) {
            System.out.println("ERROR:  You can use only one of path/size");
            System.exit(1);
        }
        if (size != null && Integer.parseInt(size) < min) {
            System.out.println("ERROR:  size of map cannot be < " + min);
            System.exit(1);
        }
        if (heuristic != null && !heuristic.equals("m") && !heuristic.equals("h") && !heuristic.equals("e")) {
            System.out.println("ERROR:  Heuristic can be only m/h/e, not " + heuristic);
            System.exit(1);
        }
    }
}
