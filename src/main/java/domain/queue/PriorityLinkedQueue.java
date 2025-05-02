/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.queue;
/**
 *
 * @author Profesor Lic. Gilberth Chaves A.
 * Cola enlazada
 */
public class PriorityLinkedQueue implements Queue {
    private Node front; //anterior
    private Node rear; //posterior
    private int count; //control de elementos encolados

    //Constructor
    public PriorityLinkedQueue(){
        front=rear=null;
        count=0;
    }
    
    public Node getFront(){
        return this.front;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void clear() {
        front=rear=null;
        count=0;
    }

    @Override
    public boolean isEmpty() {
        return front==null;
    }

    @Override
    public int indexOf(Object element) throws QueueException {
        if (isEmpty()) throw new QueueException("Priority Linked Queue is Empty");
        int position = 1;
        int foundIndex = -1;
        Node current = front;
        while (current != null) {
            if (util.Utility.compare(current.data, element) == 0) {
                foundIndex = position;
                break;
            }
            current = current.next;
            position++;
        }
        return foundIndex;
    }

    @Override
    public void enQueue(Object element) throws QueueException {
        Node newNode = new Node(element);
        if(isEmpty()){ //la cola no existe
            rear = newNode;
            //garantizo q anterior quede apuntando al primer nodo
            front=rear; //anterior=posterior
        }else{ //significa q al menos hay un elemento en la cola
            rear.next = newNode; //posterior.sgte = nuevoNodo
            rear = newNode; //posterior = nuevoNodo
        }
        //al final actualizo el contador
        count++;
    }
    @Override
    public void enQueue(Object element, Integer priority) throws QueueException {
        Node newNode = new Node(element, priority);
        if(isEmpty()){ //la cola no existe
            rear = newNode;
            //garantizo que anterior quede apuntando al primer nodo
            front = rear;
        }else{ //que pasa si ya hay elementos encolados
            Node aux = front;
            Node prev = front;
            while(aux!=null&&aux.priority>=priority){
                prev = aux; //dejo un rastro
                aux = aux.next;
            }
            //se sale cuando alcanza nulo o la prioridad del nuevo elemento es mayor
            if(aux==front){//preguntar si el nuevo elemento tiene una prioridad mas alta al elemento del frente
                newNode.next = front;
                front = newNode;
            }else
            if(aux==null){ //encola de forma normal
                prev.next = newNode;
                rear = newNode;
            }else{ //el nuevo elemento queda en medio de 2 nodos
                prev.next = newNode;
                newNode.next = aux;
            }
        }
        count++;
    }


    @Override
    public Object deQueue() throws QueueException {
        if(isEmpty())
            throw new QueueException("Priority Linked Queue is Empty");
        Object element = front.data;
        //caso 1. cuando solo hay un elemento
        //cuando estan apuntando al mismo nodo
        if(front==rear){
            clear(); //elimino la cola
        }else{ //caso 2. caso contrario
            front = front.next; //anterior=anterior.sgte
        }
        //actualizo el contador de elementos encolados
        count--;
        return element;
    }

    @Override
    public boolean contains(Object element) throws QueueException {
        if (isEmpty()) throw new QueueException("Priority Linked Queue is Empty");
        Node current = front;
        while (current != null) {
            if (util.Utility.compare(current.data, element) == 0) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public Object peek() throws QueueException {
        if(isEmpty())
            throw new QueueException("Priority Linked Queue is Empty");
        return front.data;
    }

    @Override
    public Object front() throws QueueException {
        if(isEmpty())
            throw new QueueException("Priority Linked Queue is Empty");
        return front.data;
    }
    
    public Integer frontPriority() throws QueueException {
        if(isEmpty())
            throw new QueueException("Priority Linked Queue is Empty");
        return front.priority;
    }
    
    @Override
    public String toString(){
        if (isEmpty()) return "Priority Linked Queue is Empty";
        StringBuilder result = new StringBuilder("\nPriority Linked Queue Content:\n");
        Node current = front;
        while (current != null) {
            result.append(current.data).append(" (Priority: ").append(current.priority).append(")\n");
            current = current.next;
        }
        return result.toString();
    }

}
