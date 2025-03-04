import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// canvasID, size1, size2, size3, ..., sizeN

class Canvas implements Comparable<Canvas> {
    private final String id;
    private final List<Integer> size;

    public Canvas(String id, List<Integer> size) {
        this.id = id;
        this.size = new ArrayList<>();
    }

    public Canvas (String line) {
        this.size = new ArrayList<Integer>();
        String[] parts = line.split("\\s+");
        this.id = parts[0];


        for (int i = 1; i < parts.length; i++) {
            size.add(Integer.parseInt(parts[i]));
        }
    }

    public int getPerimeter() {
        int perimeter = 0;
        for (Integer s : size) {
            perimeter += 4 * s;
        }

        return perimeter;
    }

    @Override
    public String toString() {
        return String.format("%s %d %d", id, size.size(), getPerimeter());
    }

    public List<Integer> getSize() {
        return size;
    }

    @Override
    public int compareTo(Canvas o) {
        return this.getPerimeter() - o.getPerimeter();
    }
}

class ShapesApplication {
    private final List<Canvas> canvases;

    public ShapesApplication() {
        this.canvases = new ArrayList<>();
    }

    public int readCanvases(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);

        String str;
        int s = 0;

        while (scanner.hasNextLine()) {
            str = scanner.nextLine();
            s += str.split("\\s+").length - 1;
            canvases.add(new Canvas(str));
        }

        return s;
    }

    // Prints the window whose squares have the greatest perimeter
    public void printLargestCanvasTo (OutputStream outputStream) {
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(outputStream));
        pw.println( canvases.stream().max(Canvas::compareTo).get());
        pw.flush();
    }
}

public class ShapesApplicationTest {
    public static void main(String[] args) {
        ShapesApplication shapesApplication = new ShapesApplication();

        System.out.println("===READING SQUARES FROM INPUT STREAM===");
        System.out.println(shapesApplication.readCanvases(System.in));
        System.out.println("===PRINTING LARGEST CANVAS TO OUTPUT STREAM===");
        shapesApplication.printLargestCanvasTo(System.out);
    }
}
