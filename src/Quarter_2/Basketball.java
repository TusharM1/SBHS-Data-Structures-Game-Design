package Quarter_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Basketball {

    public static void main(String[] args) {

        HashMap<String, HashSet<BasketballPlayer>> hashMap = new HashMap<>();
        TreeMap<String, TreeSet<BasketballPlayer>> treeMap = new TreeMap<>();
        File file = new File("src/Quarter_2/BasketballPlayerList.txt");
        try {
            BufferedReader input = new BufferedReader(new FileReader(file));
            String text;
            input.readLine();
            while ((text = input.readLine()) != null) {
                int salary, weight, age, uniformNumber;
                String name, height, position, team, university;

                uniformNumber = Integer.parseInt(text.substring(0, text.indexOf("\t")));
                text = text.substring(text.indexOf("\t") + 1);

                name = text.substring(0, text.indexOf("\t"));
                text = text.substring(text.indexOf("\t") + 1);

                position = text.substring(0, text.indexOf("\t"));
                text = text.substring(text.indexOf("\t") + 1);

                age = Integer.parseInt(text.substring(0, text.indexOf("\t")));
                text = text.substring(text.indexOf("\t") + 1);

                height = text.substring(0, text.indexOf("\t"));
                text = text.substring(text.indexOf("\t") + 1);

                weight = Integer.parseInt(text.substring(0, text.indexOf("\t")));
                text = text.substring(text.indexOf("\t") + 1);

                university = text.substring(0, text.indexOf("\t"));
                text = text.substring(text.indexOf("\t") + 1);

                salary = Integer.parseInt(text.substring(0, text.indexOf("\t")).replaceAll("[$, ]", ""));
                text = text.substring(text.indexOf("\t") + 1);

                team = text;

                BasketballPlayer player = new BasketballPlayer(uniformNumber, name, position, age, height, weight, university, salary, team);

                if (!hashMap.containsKey(team))
                    hashMap.put(team, new HashSet<>());
                hashMap.get(team).add(player);

                if (!treeMap.containsKey(team))
                    treeMap.put(team, new TreeSet<>());
                treeMap.get(team).add(player);
            }

            System.out.println("HashMap");
            for (String key: hashMap.keySet()) {
                System.out.println(key);
                // Would use for-each loop, but will use iterator instead
                Iterator iterator = hashMap.get(key).iterator();
                while (iterator.hasNext())
                    System.out.println("\t" + iterator.next());
            }
            System.out.println();

            System.out.println("TreeMap");
            for (String key: treeMap.keySet()) {
                System.out.println(key);
                // Would use for-each loop, but will use iterator instead
                Iterator iterator = treeMap.get(key).iterator();
                while (iterator.hasNext())
                    System.out.println("\t" + iterator.next());
            }

        }
        catch (IOException io) {
            System.err.println("File does not exist");
        }
    }

}

class BasketballPlayer implements Comparable<BasketballPlayer> {

    private int salary, weight, age, uniformNumber;
    private String name, height, position, team, university;

    public BasketballPlayer(int uniformNumber, String name, String position, int age, String height, int weight, String university, int salary, String team) {
        this.uniformNumber = uniformNumber;
        this.name = name;
        this.position = position;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.university = university;
        this.salary = salary;
        this.team = team;
    }

    @Override
    public int compareTo(BasketballPlayer o) {
        int result = this.position.compareTo(o.getPosition());
        if (result != 0)
            return -result;
        result = this.height.compareTo(o.getHeight());
        if (result != 0)
            return -result;
        result = Integer.compare(this.weight, o.getWeight());
        if (result != 0)
            return -result;
        result = Integer.compare(this.uniformNumber, o.getUniformNumber());
        if (result != 0)
            return -result;
        result = Integer.compare(this.salary, o.getSalary());
        if (result != 0)
            return -result;
        result = this.university.compareTo(o.getUniversity());
        if (result != 0)
            return -result;
        result = Integer.compare(this.age, o.getAge());
        if (result != 0)
            return -result;
        return 0;
    }

    @Override
    public String toString() {
        return uniformNumber + " " + name + " " + position + " " + age + " " + height + " " + weight + " " + university + " " + salary + " " + team;
    }

    public int getSalary() {
        return salary;
    }
    public int getWeight() {
        return weight;
    }
    public int getAge() {
        return age;
    }
    public int getUniformNumber() {
        return uniformNumber;
    }
    public String getName() {
        return name;
    }
    public String getHeight() {
        return height;
    }
    public String getPosition() {
        return position;
    }
    public String getTeam() {
        return team;
    }
    public String getUniversity() {
        return university;
    }

}
