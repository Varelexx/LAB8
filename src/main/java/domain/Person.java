package domain;

public class Person {
    public String name;
    public String mood; //alegre,triste,enojao
    public  String attentionTime;//expresado en milisegundos
    private int priority;

    public Person(String name, String mood, String attentionTime) {
        this.name = name;
        this.mood = mood;
        this.attentionTime = attentionTime;
    }
    public Person(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getAttentionTime() {
        return attentionTime;
    }

    public void setAttentionTime(String attentionTime) {
        this.attentionTime = attentionTime;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", mood='" + mood + '\'' +
                ", attentionTime=" + attentionTime +
                '}';
    }
}
