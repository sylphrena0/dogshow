package demo_database;

import java.io.Serializable;
import java.util.Objects;

public class Dog implements Serializable{

    private static final long serialVersionUID = 4672127077172239059L;

    private String name;
    private int age;

    public Dog(){
        name = "None";
        age = 0;
    }

    public Dog (String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "demo.Dog [name=" + name + ", age=" + age + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Dog other = (Dog) obj;
        return age == other.age && Objects.equals(name, other.name);
    }

    public static void main(String[] args) {

    }

}
