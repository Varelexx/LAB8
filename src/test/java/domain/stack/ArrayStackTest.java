package domain.stack;

import domain.Person;
import org.junit.jupiter.api.Test;

import java.lang.invoke.SwitchPoint;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStackTest {

   /* @Test
    void test() {
        ArrayStack ar = new ArrayStack(11);
       // Person: {1,Alana, 18}, {2, Pablo, 20}, {3, Ana, 21},
        // {4, María, 20}, {5, Victoria, 18}, {6, Nicole, 19},
        // {7, Mateo, 18},{8, Nicole, 23}, {9, Alana, 22},
        // {10, Pablo, 19}, {11, Ana, 18}


        try {
            ar.push(new  Person(1,"Alana",18));
            ar.push(new  Person(2, "Pablo", 20));
            ar.push(new  Person(3, "Ana", 21));
            ar.push(new  Person(4, "María", 20));
            ar.push(new  Person(5, "Victoria", 18));
            ar.push(new  Person(6, "Nicole", 19));
            ar.push(new  Person(7, "Mateo", 18));
            ar.push(new  Person(8, "Nicole", 23));
            ar.push(new  Person(9, "Alana", 22));
            ar.push(new  Person(10, "Pablo", 19));
            ar.push(new  Person(11, "Ana", 18));
            System.out.println("Case 1\n");
            System.out.println(getCase1(ar));
            System.out.println("\nCase 2\n");
            System.out.println(getCase2(ar));
            System.out.println("\nCase 3\n");
            System.out.println(getCase3(ar));
            }catch (StackException e) {

                System.out.println(e);
            }
        }



        private ArrayStack getCase1(ArrayStack ar){
        ArrayStack result = new ArrayStack(ar.size());
        ArrayStack aux = new ArrayStack(ar.size());
        try {
            while (!ar.isEmpty()) {
                Person p= (Person) ar.top();
               if(p.getAge()==18&&p.getName().charAt(0)=='A'){ result.push(ar.top());}
               aux.push(ar.pop());
            }

            while (!aux.isEmpty()){
                ar.push(aux.pop());
            }
        }catch (StackException e){
            System.out.println(e);
        }


        return result;
        }

    private ArrayStack getCase2(ArrayStack ar){
        ArrayStack result = new ArrayStack(ar.size());
        ArrayStack aux = new ArrayStack(ar.size());
        try {
            while (!ar.isEmpty()) {
                Person p= (Person) ar.peek();
                if(p.getAge()==19&&p.getName().equals("Nicole")) result.push(ar.peek());
                aux.push(ar.pop());
            }

            while (!aux.isEmpty()){
                ar.push(aux.pop());
            }
        }catch (StackException e){
            System.out.println(e);
        }


        return result;
    }

    private ArrayStack getCase3(ArrayStack ar){
        ArrayStack result = new ArrayStack(ar.size());
        ArrayStack aux = new ArrayStack(ar.size());
        try {
            while (!ar.isEmpty()) {
                Person p= (Person) ar.peek();
                if(p.getAge()<=23&&p.getAge()>=20) result.push(ar.peek());
                aux.push(ar.pop());
            }

            while (!aux.isEmpty()){
                ar.push(aux.pop());
            }
        }catch (StackException e){
            System.out.println(e);
        }


        return result;
    }



    */}