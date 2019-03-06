public class State {

    public static boolean checkState(int[] list){
        int N = 0;
        int e = 0;
        int sideSize = 4;
        for (int i = 0; i < list.length; i++) {
            /* Определяется номер ряда пустой клетки (считая с 1). */
            if (list[i] == 0) {
                e = i / sideSize + 1;
            }
            if (i == 0)
                continue;
            /* Производится подсчет количества клеток меньших текущей */
            for (int j = i + 1; j < list.length; j++) {
                if (list[j] < list[i]) {
                    N++;
                }
            }
        }
        N = N + e;
        /* Если N является нечётной, то решения головоломки не существует. */
        return (N & 1) == 0; // Первый бит четного числа равен 0
    }
}
