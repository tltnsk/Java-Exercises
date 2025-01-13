import java.util.Arrays;
import java.util.stream.IntStream;

public class IntegerArray extends ResizableArray<Integer>{
    public IntegerArray() {
        super();
    }

    private IntStream getStream() {
        return Arrays.stream(toArray()).limit(count()).mapToInt(Integer.class::cast);
    }

    public double sum() {
        return getStream().sum();
    }

    public double mean(){
        return sum() / count();
    }

    public int countNonZero() {
        return (int) getStream().filter(i -> i != 0).count();
    }

    public IntegerArray distinct() {
        IntegerArray ia = new IntegerArray();

        getStream().distinct().forEach(ia::addElement);

        return ia;
    }

    public IntegerArray increment(int offset) {
        IntegerArray ia = new IntegerArray();

        getStream().map(i -> i + offset).forEach(ia::addElement);

        return ia;
    }
}
