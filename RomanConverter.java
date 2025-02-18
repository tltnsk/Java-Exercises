import java.util.ArrayList;
import java.util.HashMap;

public class RomanConverter {
    public static String toRoman(int a) {
        StringBuilder sb = new StringBuilder();
        HashMap<Integer, String> list = new HashMap<>();
        ArrayList<Integer> nums = new ArrayList<>();

        list.put(1000, "M");
        list.put(900, "CM");
        list.put(500, "D");
        list.put(400, "CD");
        list.put(100, "C");
        list.put(90, "XC");
        list.put(50, "L");
        list.put(40, "XL");
        list.put(10, "X");
        list.put(9, "IX");
        list.put(5, "V");
        list.put(4, "IV");
        list.put(1, "I");

        nums.add(1000);
        nums.add(900);
        nums.add(500);
        nums.add(400);
        nums.add(100);
        nums.add(90);
        nums.add(50);
        nums.add(40);
        nums.add(10);
        nums.add(9);
        nums.add(5);
        nums.add(4);
        nums.add(1);

        for (int i : nums) {
            while (a >= i) {
                sb.append(list.get(i));
                a -= i;
            }
        }
        return sb.toString();
    }
}
