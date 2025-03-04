import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// Driver_name lap1 lap2 lap3
class Lap implements Comparable<Lap>{
    private final int minutes;
    private final int seconds;
    private final int milliseconds;

    public Lap(String line) {
        String[] parts = line.split(":");
        this.minutes = Integer.parseInt(parts[0]);
        this.seconds = Integer.parseInt(parts[1]);
        this.milliseconds = Integer.parseInt(parts[2]);
    }

    @Override
    public int compareTo(Lap o) {
        if (minutes != o.minutes) {
            return minutes - o.minutes;
        }
        if (seconds != o.seconds) {
            return seconds - o.seconds;
        }
        if (milliseconds != o.milliseconds) {
            return this.milliseconds - o.milliseconds;
        }
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%01d:02%d:%03d", minutes, seconds, milliseconds);
    }
}

class Driver implements Comparable<Driver>{
    private final String name;
    private final List<Lap> laps;

    public Driver(String line) {
        laps = new ArrayList<>();
        String [] parts = line.split("\\s+");
        this.name = parts[0];

        for (int i = 1; i < 4; i++) {
            laps.add(new Lap(parts[i]));
        }
    }

    public Lap bestLap() {
        return Collections.min(laps);
    }

    @Override
    public int compareTo(Driver o) {
        return this.bestLap().compareTo(o.bestLap());
    }

    @Override
    public String toString() {
        return String.format("%-10s%10s", name, bestLap().toString());
    }

}
class F1Race {
    private final List<Driver> drivers;

    F1Race() {
        this.drivers = new ArrayList<>();
    }

    public void readResults(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNextLine()) {
            Driver d = new Driver(scanner.nextLine());
            drivers.add(d);
        }
    }

    public void printSorted (PrintStream out) {
        Collections.sort(drivers);

        for (int i = 0; i < drivers.size(); i++) {
            out.printf("%d. %s%n", i + 1, drivers.get(i).toString());
        }
    }
}

public class F1Test {
    public static void main(String[] args) {
        F1Race f1Race = new F1Race();
        f1Race.readResults(System.in);
        f1Race.printSorted(System.out);
    }
}
