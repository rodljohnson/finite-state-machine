package org.example.fsm;

import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class Transition<S, E> {
    private final Node<S, E> source;
    private final Node<S, E> target;
    private final Predicate<E> condition;
    private final BiConsumer<S, E> action;

    Transition(Node<S, E> source, Node<S, E> target, Predicate<E> condition, BiConsumer<S, E> action) {
        this.source = source;
        this.target = target;
        this.condition = condition;
        this.action = action;
    }

    boolean test(S state, E event) {
        return source.state.equals(state) && condition.test(event);
    }

    void accept(S state, E event) {
        if (action != null) {
            action.accept(state, event);
        }
    }

    Node<S, E> getTarget() {
        return target;
    }
}
