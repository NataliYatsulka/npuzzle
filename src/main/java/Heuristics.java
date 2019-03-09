import java.util.ArrayList;
import java.util.List;

public class Heuristics {
//    public int[] goal = puzzleGoal(size);


    public static int[] puzzleGoal(int puzzleSize){
        int numb = puzzleSize * puzzleSize;
//        System.out.println("numb = " + numb);
        int[] mas = new int[numb];
        int curr = 1;
        int x = 0;
        int y = 0;
        int xi = 1;
        int yi = 0;
        for (int i = 1; i < numb; i++){
            mas[i] = -1;
        }

        while (true){
            mas[x + y * puzzleSize] = curr;
            if (curr == 0)
                break;
            curr = curr + 1;
            if ((x + xi == puzzleSize) || (x + xi < 0) || (xi != 0 && mas[x + xi + y * puzzleSize] != -1)){
                yi = xi;
                xi = 0;
            }
            else
            if ((y + yi == puzzleSize) || (y + yi < 0) || (yi != 0 && mas[x + (y + yi) * puzzleSize] != -1)){
                xi = -yi;
                yi = 0;
            }
            x += xi;
            y += yi;
            if (curr == numb)
                curr = 0;
        }

//        for (int j = 0; j < mas.length; j++) {
//            System.out.print(String.format("%3d", mas[j]));
//           if ((j + 1) % puzzleSize == 0)
//               System.out.println();
//        }
        return mas;
    }

    public static int manhattanDist(int[] list) {
        int pos = -1;
        int dist = 0;
        int size = (int) Math.sqrt(list.length + 1);
        int[] mas = puzzleGoal(size);
        for (int i = 0; i < list.length; i++) {
            if (i == list.length - 1 && list[i] == 0) {
                continue;
            }
            if (list[i] != mas[i]) {
                for (int k = 0; k < mas.length; k++) {
                    if (mas[k] == list[i])
                        pos = k;
                }
                dist += Math.abs(i / size - pos / size) + Math.abs(i % size - pos % size);
            }

//                if (list[i] == 0) {
//                    dist += Math.abs(i % size - size - 1) + Math.abs(((i + 1) / size) - size);
//                } else {
//                    dist += Math.abs(i % size - (list[i] - 1) % size) + Math.abs(((i + 1) / size) - (list[i] / size));
//                }
        }

        return dist;
    }

    public static int hammingDistance(int[] list) {
        int dist = 0;
        int size = (int) Math.sqrt(list.length + 1);
        int[] mas = puzzleGoal(size);
        for (int i = 0; i < list.length; i++) {
            if (i == list.length - 1 && list[i] == 0) {
                continue;
            }
            if (list[i] != mas[i]) {
                dist++;
            }
        }
        return dist;
    }

    public static int euclidianDistance(int[] list) {
        int dist = 0;
        int pos = -1;
        int size = (int) Math.sqrt(list.length + 1);
        int[] mas = puzzleGoal(size);
        for (int i = 0; i < list.length; i++) {
//            if (i == list.length - 1 && list[i] == 0) {
//                continue;
//            }

            if (list[i] != mas[i]) {
                for (int k = 0; k < mas.length; k++) {
                    if (mas[k] == list[i])
                        pos = k;
                }
                dist += Math.sqrt(Math.pow(i / size - pos / size, 2) + Math.pow(i % size - pos % size, 2));
            }

//            if (list[i] != i + 1) {
//                if (list[i] == 0) {
//                    Math.sqrt(Math.pow(i % size - (list[i] - 1) % size, 2) + Math.pow(((i + 1) / size) - (list[i] / size), 2));
//                    dist += Math.sqrt(Math.pow(i % size - size - 1, 2) + Math.pow(((i + 1) / size) - size, 2));
//                } else {
//                    dist += Math.sqrt(i % size - (list[i] - 1) % size) + Math.abs((int) ((i + 1) / size) -
//                            (int) (list[i] / size));
//                }
//            }
        }
        return dist;
    }

}
