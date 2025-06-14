package org.example.fsm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FiniteStateMachineTest {

    @Test
    void initial_state() {
        FiniteStateMachine<String, Integer> fsm = new FiniteStateMachine<>("a");
        assertEquals("a", fsm.currentState());
    }

    @Test
    void transition_from_a_to_b() {
        FiniteStateMachine<String, Integer> fsm = new FiniteStateMachine<>("a");
        fsm.add("a", "b", 1,
            e -> e.equals(1),
            (s, e) -> System.out.println("a to b"));
        fsm.send(1);
        assertEquals("b", fsm.currentState());
    }

    @Test
    void transition_from_a_to_b_no_action() {
        FiniteStateMachine<String, Integer> fsm = new FiniteStateMachine<>("a");
        fsm.add("a", "b", 1,
            e -> e.equals(1));
        fsm.send(1);
        assertEquals("b", fsm.currentState());
    }

    @Test
    void only_transition_from_a_to_b_should_be_called() {
        FiniteStateMachine<String, Integer> fsm = new FiniteStateMachine<>("a");
        fsm.add("a", "b", 1,
            e -> e.equals(1),
            (s, e) -> System.out.println("a to b"));
        fsm.add("c", "d", 1,
            e -> e.equals(1),
            (s, e) -> System.out.println("Should not be called"));
        fsm.send(1);
        assertEquals("b", fsm.currentState());
    }

    @Test
    void invalid_event() {
        FiniteStateMachine<String, Integer> fsm = new FiniteStateMachine<>("a");
        fsm.add("a", "b", 1,
            e -> e.equals(1),
            (s, e) -> System.out.println("Should not be called"));
        fsm.send(2);
        assertEquals("a", fsm.currentState());
    }

    @Test
    void no_matching_transition_should_not_change_state() {
        FiniteStateMachine<String, Integer> fsm = new FiniteStateMachine<>("a");
        fsm.send(1);
        assertEquals("a", fsm.currentState());
    }

    @Test
    void same_event_different_source_states() {
        FiniteStateMachine<String, Integer> fsm = new FiniteStateMachine<>("a");
        fsm.add("a", "b", 1,
            e -> e.equals(1),
            (s, e) -> System.out.println("a to b"));
        fsm.add("b", "c", 1,
            e -> e.equals(1),
            (s, e) -> System.out.println("Should not run from a"));
        fsm.send(1);
        assertEquals("b", fsm.currentState());

        fsm.send(1);
        assertEquals("c", fsm.currentState());
    }

    @Test
    void self_transition_should_work() {
        FiniteStateMachine<String, Integer> fsm = new FiniteStateMachine<>("a");
        fsm.add("a", "a", 1,
            e -> e.equals(1),
            (s, e) -> System.out.println("a to a"));
        fsm.send(1);
        assertEquals("a", fsm.currentState());
    }

}
