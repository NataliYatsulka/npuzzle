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

        int[] solved = Heuristics.puzzleGoal(puzzleSize);
        int inver = 0;
        int[] copy = new int[puzzleSize * puzzleSize];

        for (int t = 0; t < puzzleSize*puzzleSize; t++){
            copy[t] = list[t];
        }

        int i = 0;

        for (int l = 0; l < puzzleSize * puzzleSize; l++) {
            if (copy[l] == 0)
                i = l;
        }
//        while (list[i] != 0) {
//            i += 1;
//        }
        copy[i] = puzzleSize * puzzleSize;
        if (puzzleSize % 2 == 0) {
            inver += i / puzzleSize;
        }
        i = 0;
        while (solved[i] != 0){
        i += 1;}
        solved[i] = puzzleSize * puzzleSize;
        if (puzzleSize % 2 == 0) {
            inver += i / puzzleSize;
        }
        i = 0;
        int sol = 0;
        while (i < puzzleSize * puzzleSize) {
            int j = i;
            j += 1;
            while (j< puzzleSize * puzzleSize){
            if (copy[j] < copy[i] && copy[ i] !=(puzzleSize * puzzleSize)) {
                inver += 1;
            }
            if (solved[j] < solved[i] && solved[i] !=(puzzleSize * puzzleSize)){
            sol += 1;
            inver += 1;
            }
            j += 1;
            }
            i += 1;
        }
        if (inver % 2 == 0)
            return true;
    else
        return false;
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
