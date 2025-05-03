package util;

import domain.Person;
import domain.stack.LinkedStack;
import domain.stack.Stack;
import domain.stack.StackException;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.SortedMap;

public class Utility {

    //static init
    static {
    }

    public static String format(double value){
        return new DecimalFormat("###,###,###.##").format(value);
    }
    public static String $format(double value){
        return new DecimalFormat("$###,###,###.##").format(value);
    }
    public static String show(int[] a, int size) {
        String result="";
        for (int i = 0; i < size; i++) {
            result+= "a[i] ";
        }
        return result;
    }

    public static void fill(int[] a, int bound) {
        for (int i = 0; i < a.length; i++) {
            a[i] = new Random().nextInt(bound);
        }
    }

    public static int getRandom(int bound) {
        return new Random().nextInt(bound);
    }

    public static int compare(Object a, Object b) {
        switch (instanceOf(a, b)){
            case "Integer":
                Integer int1 = (Integer)a; Integer int2 = (Integer)b;
                return int1 < int2 ? -1 : int1 > int2 ? 1 : 0; //0 == equal
            case "String":
                String st1 = (String)a; String st2 = (String)b;
                return st1.compareTo(st2)<0 ? -1 : st1.compareTo(st2) > 0 ? 1 : 0;
            case "Character":
                Character c1 = (Character)a; Character c2 = (Character)b;
                return c1.compareTo(c2)<0 ? -1 : c1.compareTo(c2)>0 ? 1 : 0;
            case "Person":
                Person p1 = (Person)a; Person p2 = (Person)b;
                return p1.getName().compareTo(p2.getName())<0 ? -1 : p1.getName().compareTo(p2.getName())>0 ? 1 : 0;

        }
        return 2; //Unknown
    }

    private static String instanceOf(Object a, Object b) {
        if(a instanceof Integer && b instanceof Integer) return "Integer";
        if(a instanceof String && b instanceof String) return "String";
        if(a instanceof Character && b instanceof Character) return "Character";
        if(a instanceof Person && b instanceof Person) return "Person";
        return "Unknown";
    }
    public static Boolean isBalanced(Stack stack, String expression) throws StackException {

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if(c=='('||c=='{'||c=='['){
                stack.push(c);
            } else if (c==')'||c=='}'||c==']') {
                if(stack.isEmpty()){
                    return false;
                }
                char c1 = (char) stack.pop();

                if(c=='(' && c1!=')'||c=='{' && c1!='}'||c=='[' && c1!=']'){
                    System.out.println("Entre");
                    return false;
                }
            }

        }

        return stack.isEmpty();
    }

    public static String infixToPostfixConverter(String exp) throws StackException {
        LinkedStack stack = new LinkedStack();
        String expPostFix = "";
        for (char c : exp.toCharArray()) {
            if (Character.isLetterOrDigit(c))
                expPostFix += c;
            else if (c == '(')
                stack.push(c);
            else if (c == ')')
                while (!stack.isEmpty() && util.Utility.compare(stack.peek(), '(') != 0) {
                    expPostFix += stack.pop();
                    if (!stack.isEmpty() && util.Utility.compare(stack.top(), '(') != 0)
                        return "Invalid expression";
                    else {
                        if (!stack.isEmpty())
                            stack.pop();
                        else {
                            while (!stack.isEmpty() && util.Utility.getPriority(c) <=
                                    util.Utility.getPriority((char) stack.peek()))
                                expPostFix += stack.pop();
                            stack.push(c);
                        }
                    }
                }
                while (!stack.isEmpty())
                    expPostFix+=stack.pop();

        }
            return expPostFix;
        }

    private static int getPriority(char operator) {
        switch (operator){
            case '+': case'-': return 1;//return prioridad mas baja
            case '*': case '/': return 2;
            case '^': return 3;
        }
        return -1;
    }

    public static String getPlace() {
        String places[] = {"San José", "Ciudad Quesada", "Paraíso",
                "Turrialba", "Limón", "Liberia", "Puntarenas", "San Ramón", "Puerto Viejo", "Volcán Irazú", "Pérez Zeledón",
                "Palmares", "Orotina", "El coco", "Ciudad Neilly", "Sixaola", "Guápiles","Siquirres"
                , "El Guarco", "Cartago", "Santa Bárbara", "Jacó", "Manuel Antonio", "Quepos", "Santa Cruz",
                "Nicoya"};

        return places[getRandom(places.length-1)];
    }

    public static String getWeather(){
        String weather[] = {"rainy", "thunderstorm", "sunny", "cloudy", "foggy"};
        return weather[getRandom(weather.length-1)];
    }

    public static String getMood(){
        //Happiness, Sadness, Anger, Sickness, Cheerful,
        //Reflective, Gloomy, Romantic, Calm, Hopeful, Fearful, Tense, Lonely
        String mood[] = {"Happiness", "Sadness", "Anger", "Sickness", "Cheerful",
                "Reflective", "Gloomy", "Romantic", "Calm", "Hopeful", "Fearful", "Tense", "Lonely"};
        return mood[getRandom(mood.length-1)];
    }

    public static int getAttentionTime(){
       return getRandom(99)+1;
    }

    public static String getName() {
        String names[] = {"Juan", "Pedro", "María", "José", "Ana", "Luis", "Laura", "Carlos", "Marta", "Jorge",
                "Sofía", "Andrés", "Isabel", "Diego", "Carmen", "Fernando", "Patricia", "David",
                "Cristina", "Alejandro", "Verónica", "Ricardo", "Elena", "Javier", "Raquel"};
        return names[getRandom(names.length-1)];
    }
}

