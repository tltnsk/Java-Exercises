import java.io.*;
import java.util.Comparator;
import java.util.stream.Stream;

class Person implements Comparable<Person> {
    String name;
    int age;

    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public Person(String line) {
        String [] parts = line.split("\\s+");
        this.name = parts[0];
        this.age = Integer.parseInt(parts[1]);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        return Integer.compare(this.age, o.age);
    }
}

public class OldestPersonTest {

    public static Person find (InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
       // br.lines().map(line -> new Person(line.split("\\s+")[0], Integer.parseInt(line.split("//s+")[1])));
        return br.lines()
                .map(Person::new)
                .max(Comparator.naturalOrder())
                .orElse(new Person("Stefan", 27));
    }

    public static void main(String[] args) {
        try {
            InputStream isFromFile = new FileInputStream(new File("C:\\LABVEZHBI\\Aud04\\src\\mk\\ukim\\finki\\oldestperson\\people.txt"));
            System.out.println(OldestPersonTest.find(isFromFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
