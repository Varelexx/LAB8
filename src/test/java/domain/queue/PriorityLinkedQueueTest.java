package domain.queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriorityLinkedQueueTest {

    @Test
    void test() {
        PriorityLinkedQueue priorityQueue = new PriorityLinkedQueue();

        try {
            priorityQueue.enQueue("Juan",1);
            priorityQueue.enQueue("Pedro",2);
            priorityQueue.enQueue("Maria",3);
            priorityQueue.enQueue("Julio",3);
            System.out.println(priorityQueue);

        }catch (QueueException e){
          throw new RuntimeException(e);
        }
    }
}