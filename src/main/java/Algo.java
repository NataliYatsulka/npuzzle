import com.carrotsearch.hppc.ObjectObjectOpenHashMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Function;

public class Algo {

    public Function<int[], Integer> heuristicFunc;
    public int sizePuzzle;
    public int koef;
    public Node node;
    public PriorityQueue<Node> openNodes;
    public List<State> path;
    public ObjectObjectOpenHashMap<State, Node> closedNodes;

    public Algo(int[] mas, String heuristic, int koef) {

        switch (heuristic) {
            case "h":
                this.heuristicFunc = Heuristics::hammingDistance;
                break;
            case "m":
                this.heuristicFunc = Heuristics::manhattanDist;
                break;
            case "e":
                this.heuristicFunc = Heuristics::euclidianDistance;
                break;
            default:
                throw new RuntimeException("Something went wrong");
        }
        this.koef = koef;
        this.sizePuzzle = (int) Math.sqrt(mas.length);
//        System.out.println("this.sizePuzzle = " + this.sizePuzzle);
        this.node = new Node(null, new State(mas, null, 0, 0));
        this.openNodes = new PriorityQueue<>((Node n1, Node n2) -> {
            return (n1.state.bonus - n2.state.bonus);
        });
        this.path = new ArrayList<>();
        this.closedNodes = new ObjectObjectOpenHashMap<>();
    }

    public void startSearch() {
        int maxOpenNode = 0;
        openNodes.add(node);

        if (!State.checkState(node.state.list, sizePuzzle)) {
            System.out.println("There are no solution of this puzzle");
            return;
        }

        node.state.cost = heuristicFunc.apply(node.state.list);
        node.state.bonus = node.state.dist + node.state.cost * koef;
        printPuzzle(node.state.list);

        while (!openNodes.isEmpty() && path.isEmpty()) {
            Node n = openNodes.poll();
            if (closedNodes.containsKey(n.state)) {
                continue;
            }
            if (n.state.cost == 0) {
                for (Node p = n; p != null; p = p.node)
                    path.add(p.state);
                break;
            }
            closedNodes.put(n.state, n);
            for (int i = 0; i < Move.values().length; i++) {
                State state = n.state.makeAMove(Move.values()[i], sizePuzzle);
                if (state != null) {
                    Node node = new Node(n, state);
                    node.state.cost = heuristicFunc.apply(node.state.list);
                    node.state.bonus = node.state.dist + node.state.cost * koef;
                    openNodes.add(node);
                }
            }

            maxOpenNode = openNodes.size() > maxOpenNode ? openNodes.size() : maxOpenNode;
        }
        printPath();
        System.out.println("Initial state:");
        printPuzzle(node.state.list);

        System.out.println("Number of moves: " + (path.size() - 1));
        System.out.println("Maximum number of states (complexity in size): " + maxOpenNode);
        System.out.println("Complexity in time: " + closedNodes.size());


    }

    private void printPuzzle(int[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.print(String.format("%3d", list[i]));
            if ((i + 1) % sizePuzzle == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    private void printPath() {
        Collections.reverse(path);
        path.forEach(node -> {
            if (node.move != null) {
                System.out.println(node.move);
                System.out.println();
            }
            printPuzzle(node.list);
        });
    }
}
