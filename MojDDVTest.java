import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


class AmountNotAllowedException extends Exception {
    public AmountNotAllowedException(int amount) {
        super(String.format("Receipt with amount %d is not allowed to be scanned", amount));
    }
}

class Article {
    private final int price;
    private final String type;

    public Article(int price, String type) {
        this.price = price;
        this.type = type;
    }

    public double calculateTax() {
        if (type.equals("A")) return  0.18 * 0.15 * price;
        else if (type.equals("B")) return 0.05 * 0.15 * price;
        else return 0;
    }

    public int getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }
}

class Receipt {
    private final String id;
    private final List<Article> articles;

    public Receipt(String id, List<Article> articles) {
        this.id = id;
        this.articles = articles;
    }

    public static Receipt create(String line) {
        String[] parts = line.split("\\s+");
        String id = parts[0];
        List<Article> articles = new ArrayList<>();

        for (int i = 1; i < parts.length; i+= 2) {
            articles.add(new Article(Integer.parseInt(parts[i]), parts[i + 1]));
        }

        Receipt r = new Receipt(id, articles);

        try {
            if (r.totalAmount() > 30000) {
                throw new AmountNotAllowedException(r.totalAmount());
            } else {
                return r;
            }
        } catch (AmountNotAllowedException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public int totalAmount() {
        return articles.stream().mapToInt(Article::getPrice).sum();
    }

    public double totalTaxReturn() {
        return articles.stream().mapToDouble(Article::calculateTax).sum();
    }

    @Override
    public String toString() {
        return String.format("%s %d %.2f", id, totalAmount(), totalTaxReturn());
    }
}

class MojDDV {
    List<Receipt> receipts;

    public MojDDV() {
        this.receipts = new ArrayList<>();
    }

    public void readRecords (InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        receipts = br.lines().map(Receipt::create).collect(Collectors.toList());
    }

    public void printTaxReturns (OutputStream outputStream) {
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(outputStream));
        for (Receipt r : receipts) {
            if (r != null) {
                pw.println(r.toString());
            }
        }

        pw.flush();
    }
}

public class MojDDVTest {
    public static void main(String[] args) {
        MojDDV mojDDV = new MojDDV();

        System.out.println("===READING RECORDS FROM INPUT STREAM===");
        mojDDV.readRecords(System.in);

        System.out.println("===PRINTING TAX RETURNS RECORDS TO OUTPUT STREAM ===");
        mojDDV.printTaxReturns(System.out);
    }
}
