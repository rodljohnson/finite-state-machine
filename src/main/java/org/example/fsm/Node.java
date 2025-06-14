package org.example.fsm;

import java.util.HashMap;
import java.util.Map;

public class Node<S, E> {
    final S state;
    final Map<E, Transition<S, E>> transitions = new HashMap<>();

    public Node(S state) {
        this.state = state;
    }
}
