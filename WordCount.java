import java.io.*;
import java.util.function.Consumer;

class LineConsumer implements Consumer<String> {
    int l = 0;
    int w = 0;
    int c = 0;

    @Override
    public void accept(String s) {
        l++;
        w += s.split("\\s+").length;
        c += s.length();
    }

    @Override
    public String toString() {
        return String.format("Lines: %d, Words: %d, Chars: %d", l, w, c);
    }
}

class WordCounter {
    public static void count(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        LineConsumer lc = new LineConsumer();
        br.lines().forEach(lc);
        System.out.println(lc);
    }
}

public class WordCount {
    public static void main(String[] args) {
        try {
            InputStream isFromFile = new FileInputStream(new File("words.txt"));
            WordCounter.count(isFromFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
