package domain.stack;
import domain.Person;
import domain.stack.ArrayStack;
import domain.stack.LinkedStack;
import domain.stack.Stack;
import org.junit.jupiter.api.Test;


class StackTest {
    private static ArrayStack arrayStack;
    private static LinkedStack linkedStack;

    @Test
    void test(){
        // Crear las pilas
        arrayStack = new ArrayStack(5);
        linkedStack = new LinkedStack();
        Person[] arrayPerson = {
                new Person(1, "Ana", 23),
                new Person(2, "Pablo", 24),
                new Person(3, "Ana", 25),
                new Person(4, "Pablo", 19),
                new Person(5, "Victoria", 18),
                new Person(6, "Nicole", 26),
                new Person(7, "Mateo", 18),
                new Person(8, "Nicole", 23),
                new Person(9, "Victoria", 20),
                new Person(10, "Ana", 28)
        };

        // Apilar objetos en las pilas
        try {
            for(int i = 4; i >= 0; i--){
                arrayStack.push(arrayPerson[i]);
            }
            for(int j = 9; j >= 5; j--){
                linkedStack.push(arrayPerson[j]);
            }
            // Mostrar contenido de las pilas
            System.out.println("Content of the stack 'ArrayStack':");
            System.out.println(arrayStack);

            System.out.println("\nContent of the stack 'LinkedStack':");
            System.out.println(linkedStack);

            // Desapilar y mostrar contenido según criterios
            System.out.println("\nContent unstacking according to criteria:");
            System.out.println("Case 1: age<=20");
            printFilteredContent(arrayStack, linkedStack, 1);

            System.out.println("\nCase 2: age<=23");
            printFilteredContent(arrayStack, linkedStack, 2);

            System.out.println("\nCase 3: name=Ana");
            printFilteredContent(arrayStack, linkedStack, 3);

        } catch (StackException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void printFilteredContent(ArrayStack arrayStack, LinkedStack linkedStack, int caseNumber) throws StackException {
        ArrayStack arrayStackAux = new ArrayStack(arrayStack.size());
        LinkedStack linkedStackAux = new LinkedStack();

        while (!arrayStack.isEmpty()) {
            Person person = (Person) arrayStack.pop();
            if (meetsCriteria(person, caseNumber)) {
                System.out.println(person);
            }
            arrayStackAux.push(person);
        }

        while (!linkedStack.isEmpty()) {
            Person person = (Person) linkedStack.pop();
            if (meetsCriteria(person, caseNumber)) {
                System.out.println(person);
            }
            linkedStackAux.push(person);
        }

        // Llenar las pilas originales con los elementos de las pilas auxiliares
        while (!arrayStackAux.isEmpty()) {
            arrayStack.push(arrayStackAux.pop());
        }

        while (!linkedStackAux.isEmpty()) {
            linkedStack.push(linkedStackAux.pop());
        }

    }


    private static boolean meetsCriteria(Person person, int caseNumber) {
        switch (caseNumber) {
            case 1:
                return person.getAge() <= 20;
            case 2:
                return person.getAge() <= 23;
            case 3:
                return person.getName().equals("Ana");
            default:
                return false;
        }
    }

    private static void printStackContent(Stack stack) throws StackException {
        Stack stackAux = new LinkedStack(); // Utilizamos una LinkedStack para la pila auxiliar

        // Guardar el contenido en la pila auxiliar
        while (!stack.isEmpty()) {
            stackAux.push(stack.pop());
        }

        // Mostrar el contenido de la pila original y restaurarla con los elementos de la pila auxiliar
        while (!stackAux.isEmpty()) {
            Person person = (Person) stackAux.pop();
            System.out.println(person);
            stack.push(person);
        }
    }


}