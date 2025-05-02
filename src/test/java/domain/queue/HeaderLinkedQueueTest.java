package domain.queue;

import domain.Climate;
import domain.Place;
import domain.Weather;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeaderLinkedQueueTest {

    @Test
   void test(){
        HeaderLinkedQueue q1= new HeaderLinkedQueue();
        HeaderLinkedQueue q2= new HeaderLinkedQueue();
        HeaderLinkedQueue q3= new HeaderLinkedQueue();
        try {
            for (int i = 0; i < 20; i++) {
                q1.enQueue( new Climate(new Place(util.Utility.getPlace()),new Weather(util.Utility.getWeather())));

            }
            System.out.println(q1);
            System.out.println("\n Desencolar Sunny y Foggy");
            deQueeByTime(q1,q2,"sunny");
            deQueeByTime(q1,q2,"foggy");
            System.out.println("Q2 con Sunny y Fuggy"+q2);
            System.out.println("\n---------------------\n");
            System.out.println("Q1 Sin Sunny y Fuggy"+q1);
            System.out.println("\n Desencolar Lugares Paraíso y Liberia");
            deQueeByPlace(q1,q3,"Liberia");
            deQueeByPlace(q1,q3,"Paraíso");
            System.out.println("Q1 Sin Paraiso y Liberia"+q1);
            System.out.println("\n---------------------\n");
            System.out.println("Q3 agregando Paraiso y Liberia"+q3);

            System.out.println("\n Desencolar clima Thunderstom\n");

            deQueeByTime2(q1,q2,q3,"thunderstorm");
            System.out.println("Q1 Despues de desencolar Thunderstom\n"+q1);
            System.out.println("\n---------------------\n");
            System.out.println("Q2 Agregando Thunderstorm\n"+q2);
            System.out.println("\n---------------------\n");
            System.out.println("Q3 Agregando Thunderstorm\n"+q3);




        }catch (QueueException e){
            throw new RuntimeException(e);
        }
    }

    private void deQueeByTime(HeaderLinkedQueue li,HeaderLinkedQueue res,String m) throws QueueException {

        HeaderLinkedQueue aux = new HeaderLinkedQueue();
        while (!li.isEmpty()){
                Climate c = (Climate) li.front();
                Weather w = c.getWeather();
                if(w.getName().equals(m)){
                    res.enQueue(li.front());
                    li.deQueue();
                }else {
                    aux.enQueue(li.deQueue());
                }

            }
        while (!aux.isEmpty()){
            li.enQueue(aux.deQueue());
        }


    }

    private void deQueeByTime2(HeaderLinkedQueue li,HeaderLinkedQueue res,HeaderLinkedQueue res2,String m) throws QueueException {

        HeaderLinkedQueue aux = new HeaderLinkedQueue();

            while (!li.isEmpty()){
                Climate c = (Climate) li.front();
                Weather w = c.getWeather();
                if(w.getName().equals(m)){
                    res.enQueue(li.front());
                    res2.enQueue(li.front());
                    li.deQueue();
                }else {
                    aux.enQueue(li.deQueue());
                }

            }
            while (!aux.isEmpty()){
                li.enQueue(aux.deQueue());
            }


    }



    private void deQueeByPlace(HeaderLinkedQueue li,HeaderLinkedQueue res,String m) throws QueueException {

        HeaderLinkedQueue aux = new HeaderLinkedQueue();

            while (!li.isEmpty()){
                Climate c = (Climate) li.front();
                Place p = c.getPlace();
                if(p.getName().equals(m)){
                    res.enQueue(li.front());
                    li.deQueue();
                }else {
                    aux.enQueue(li.deQueue());
                }

            }
            while (!aux.isEmpty()){
                li.enQueue(aux.deQueue());
            }



    }








    void test1() {

        HeaderLinkedQueue he = new HeaderLinkedQueue();
        try {
            for (int i = 0; i < 15; i++)
                he.enQueue(util.Utility.getRandom(30));
            System.out.println(he);

        }catch (QueueException e){
            throw new RuntimeException(e);
        }
    }
}