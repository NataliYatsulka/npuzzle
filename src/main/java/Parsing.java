import java.text.ParseException;

public class Parsing {

    public static void initArgs(String path, String size, String heuristic) {
        int min = 3;
        int max = 15;

        if (path != null && size != null) {
            System.out.println("ERROR:  You can use only one of path/size");
            System.exit(1);
        }
        if (path != null &&
                (Integer.parseInt(size) < min || Integer.parseInt(size) > max)) {
            System.out.println("ERROR:   size of map cannot be < " + min + " or > " + max);
            System.exit(1);
        }
        if (heuristic != null && !heuristic.equals("m") && !heuristic.equals("h") && !heuristic.equals("e")) {
            System.out.println("ERROR:  Heuristic can be only m/h/e, not " + heuristic);
            System.exit(1);
        }
    }
}
