package domain.queue;

import domain.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayQueueTest {

    @Test
    void test() {

        ArrayQueue arr= new ArrayQueue(20);
        try {

            for (int i = 0; i < 20; i++){
                arr.enQueue(new Person(util.Utility.getName(),util.Utility.getMood(),util.Utility.getAttentionTime()));
               }

            System.out.println(arr);
            System.out.println(arr.size());
            for (int i = 0; i < arr.size(); i++) {
               Person p = new Person(util.Utility.getName());
                if (arr.contains(p)){
                    System.out.println("Se encontro la persona "+p.getName()+" En la posicion:"+(arr.indexOf(p)+1));
                }

            }
            System.out.println("\nDequeue by Cheerful\n");
            dequeeByMood(arr, "Cheerful");
            System.out.println(arr);
        }catch (QueueException e){
            throw new RuntimeException(e);
        }

    }

    private void dequeeByMood(ArrayQueue arr, String mood) throws QueueException {
        ArrayQueue aux = new ArrayQueue(arr.size());
        while (!arr.isEmpty()) {
            Person p = (Person) arr.front();
            if (p.getMood().equals(mood)) {
                System.out.println("Dequeue: " + arr.deQueue());
            } else {
                aux.enQueue(arr.deQueue());
            }
        }
        while (!aux.isEmpty()) {
            arr.enQueue(aux.deQueue());
        }
    }
}