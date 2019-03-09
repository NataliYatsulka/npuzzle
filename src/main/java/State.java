import java.util.Arrays;

public class State {

    public int[] list;
    public Move move;
    public int pos;
    public int dist;
    public int cost;

    public State(int[] list, Move move, int cost, int dist) {
        this.list = list;
        this.move = move;
        this.pos = cost;
        this.dist = dist;
        for (int i = 0; i < list.length; i++) {
            if (list[i] == 0) {
                this.pos = i;
            }
        }
    }

    public State makeAMove(Move step, int size) {
        State clone;
        try {
            clone = this.copy();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        clone.dist++;
        clone.move = step;
        int x = clone.pos % size;
        int y = (clone.pos + 1) / size;


        switch (step) {
            case UP:
                if (clone.pos - size < 0 || clone.pos - size > clone.list.length - 1) {
                    return null;
                }
                clone.list[clone.pos] = clone.list[clone.pos - size];
                clone.pos = clone.pos - size;
                clone.list[clone.pos] = 0;
                return clone;
            case DOWN:
                if (clone.pos + size < 0 || clone.pos + size > clone.list.length - 1) {
                    return null;
                }
                clone.list[clone.pos] = clone.list[clone.pos + size];
                clone.pos = clone.pos + size;
                clone.list[clone.pos] = 0;
                return clone;
            case LEFT:
                if (x == size - 1 || clone.pos + 1 < 0 || clone.pos + 1 > clone.list.length - 1) {
                    return null;
                }
                clone.list[clone.pos] = clone.list[clone.pos + 1];
                clone.pos = clone.pos + 1;
                clone.list[clone.pos] = 0;
                return clone;
            case RIGHT:
                if (x == 0 || clone.pos - 1 < 0 || clone.pos - 1 > clone.list.length - 1) {
                    return null;
                }
                clone.list[clone.pos] = clone.list[clone.pos - 1];
                clone.pos = clone.pos - 1;
                clone.list[clone.pos] = 0;
                return clone;
            default:
                throw new RuntimeException("WTF");
        }
    }

    public static boolean checkState(int[] list, int puzzleSize) {
        int n = 0;
        int e = 0;
        for (int i = 0; i < list.length; i++) {
            /* Определяется номер ряда пустой клетки (считая с 1). */
            if (list[i] == 0) {
                System.out.println("pzlsz=" + puzzleSize);
                e = i / puzzleSize + 1;
            }
            if (i == 0)
                continue;
            /* Производится подсчет количества клеток меньших текущей */
            for (int j = i + 1; j < list.length; j++) {
                if (list[j] < list[i]) {
                    n++;
                }
            }
        }
        n = n + e;
        /* Если N является нечётной, то решения головоломки не существует. */
        return (n & 1) == 0; // Первый бит четного числа равен 0
    }

    private State copy() throws CloneNotSupportedException {
        return new State(list.clone(), move, cost, dist);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        return Arrays.equals(list, state.list);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(list);
    }
}
