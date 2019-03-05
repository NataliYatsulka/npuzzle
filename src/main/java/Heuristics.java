public class Heuristics {

    public static int manhattanDist(int[] list) {
        int dist = 0;
        int size = (int) Math.sqrt(list.length + 1);

        for (int i = 0; i < list.length; i++) {
            if (i == list.length - 1 && list[i] == 0) {
                continue;
            }
            if (list[i] != i + 1) {
                if (list[i] == 0) {
                    dist += Math.abs(i % size - size - 1) + Math.abs(((i + 1) / size) - size);
                } else {
                    dist += Math.abs(i % size - (list[i] - 1) % size) + Math.abs(((i + 1) / size) - (list[i] / size));
                }
            }
        }

        return dist;
    }

    public static int hammingDistance(int[] list) {
        int dist = 0;
        for (int i = 0; i < list.length; i++) {
            if (i == list.length - 1 && list[i] == 0) {
                continue;
            }
            if (list[i] != i + 1) {
                dist++;
            }
        }
        return dist;
    }

    public static int euclidianDistance(int[] list) {
        int dist = 0;
        int size = (int) Math.sqrt(list.length + 1);
        for (int i = 0; i < list.length; i++) {
            if (i == list.length - 1 && list[i] == 0) {
                continue;
            }
            if (list[i] != i + 1) {
                if (list[i] == 0) {
                    Math.sqrt(Math.pow(i % size - (list[i] - 1) % size, 2) + Math.pow((i + 1) / size - list[i] / size, 2));
                    dist += Math.sqrt(Math.pow(i % size - size - 1, 2) + Math.pow((i + 1) / size - size, 2));
                } else {
                    dist += Math.sqrt(i % size - (list[i] - 1) % size) + Math.abs((int) ((i + 1) / size) -
                            (int) (list[i] / size));
                }
            }
        }
        return dist;
    }

}
