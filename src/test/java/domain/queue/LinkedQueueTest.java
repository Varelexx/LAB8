package domain.queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedQueueTest {

    @Test
    void test1(){
        String[] array = {"(())()", "(()", "())(", "", "((()))", "(()(()))"};
        for (String i : array) {
            boolean result = isBalanced(i);
            System.out.println("Expression: " + i + " → " + (result ? "is balanced" : "is not balanced"));
        }
    }

    @Test
    void test3(){
        LinkedQueue linkedQueue = new LinkedQueue();
       try{
        for (int i = 0; i < 20; i++)
            linkedQueue.enQueue(util.Utility.getRandom(20));
        System.out.println("___With duplicates"+linkedQueue);
        removeDuplicates(linkedQueue);
        System.out.println("___Without duplicates"+linkedQueue);
    }catch (QueueException e){
           throw new RuntimeException(e);
       }}

    private void removeDuplicates(LinkedQueue lin) {
        LinkedQueue aux = new LinkedQueue();

            try {

                while(!lin.isEmpty()){
                if(aux.isEmpty()||!aux.contains(lin.front())){
                    aux.enQueue(lin.front());
                }
                lin.deQueue();
                }
                while (!aux.isEmpty()){
                    lin.enQueue(aux.deQueue());
                }
            } catch (QueueException e) {
                throw new RuntimeException(e);
            }
    }

    private boolean isBalanced(String expression) {
        LinkedQueue li = new LinkedQueue();
       try{
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if(c=='('){
                li.enQueue(c);
            } else if (c==')') {
                if(li.isEmpty()){
                    return false;
                }

            }

        }}catch(QueueException e){
            throw new RuntimeException(e);
           }
       return li.isEmpty();
    }


    void test2() {

        LinkedQueue li = new LinkedQueue();
        try {
            for (int i = 0; i < 15; i++)
                li.enQueue(util.Utility.getRandom(30));
            System.out.println(li);

        }catch (QueueException e){
            throw new RuntimeException(e);
        }
    }
}