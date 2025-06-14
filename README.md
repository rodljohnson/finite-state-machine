### Finite State Machine (FSM) Framework in Java

### Prerequisites
- Java 17
- Maven

```
$>git clone https://github.com/rodljohnson/finite-state-machine.git
$>cd finite-state-machine
$>mvn install
```

### Maven Repository
Add to your pom.xml file:
```
<dependency>
  <groupId>org.example</groupId>
  <artifactId>finite-state-machine</artifactId>
  <version>1.0</version>
</dependency>
```

### Example Usage
```
FiniteStateMachine<String, Integer> fsm = new FiniteStateMachine<>("a");

// Add a transition from "a" to "b" on event 1
fsm.add("a", "b", 1,
    e -> e.equals(1),
    (s, e) -> System.out.println("Transition: a → b"));

fsm.send(1); // Triggers the transition
System.out.println(fsm.currentState()); // Output: "b"
```
