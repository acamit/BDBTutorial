package DB;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

/**
 * @author Amit Chawla
 **/
public class DataStructures {
    public static void Main() {
        List<String> list = new ArrayList<>();


        Set<String> set = new HashSet<>();
        set.add("A");
        set.remove("A");

        Map<String , Integer> map = new HashMap<>();
        map.put("A", 1);

        Stack<String> stack = new Stack<>();
        stack.push("A");
        stack.pop();

        Deque<String> deque = new ArrayDeque<>();
        deque.add("A");
        deque.remove("A");

        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.reverseOrder());

    }
}
