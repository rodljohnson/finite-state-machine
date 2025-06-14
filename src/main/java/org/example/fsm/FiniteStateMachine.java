package org.example.fsm;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class FiniteStateMachine<S, E> {
    private Node<S, E> currentNode;
    private final Map<S, Node<S, E>> nodes = new HashMap<>();

    public FiniteStateMachine(S initialState) {
        currentNode = new Node<>(initialState);
        nodes.put(initialState, currentNode);
    }

    public void addState(S state) {
        nodes.putIfAbsent(state, new Node<>(state));
    }

    public void add(S source, S target, E event, Predicate<E> condition, BiConsumer<S, E> action) {
        addState(source);
        addState(target);
        Node<S, E> fromNode = nodes.get(source);
        Node<S, E> toNode = nodes.get(target);
        fromNode.transitions.put(event, new Transition<>(fromNode, toNode, condition, action));
    }

    // overloaded add method to support optional actions
    public void add(S source, S target, E event, Predicate<E> condition) {
        add(source, target, event, condition, null);
    }

    public void send(E event) {
        Transition<S, E> transition = currentNode.transitions.get(event);
        if (transition != null && transition.test(currentNode.state, event)) {
            transition.accept(currentNode.state, event);
            currentNode = transition.getTarget();
        } else {
            System.out.printf("No transition for event '%s' in state '%s'%n", event, currentNode.state);
        }
    }

    public S currentState() {
        return currentNode.state;
    }

}
