package labs05;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntegerList {
    private List<Integer> list;

    public IntegerList() {
        this.list = new ArrayList<>();
    }

    public IntegerList(Integer... numbers) {
        this.list = new ArrayList<>();
        IntStream.range(0, numbers.length).forEach(i -> list.add(numbers[i]));
    }

    public void add(int e1, int idx) {
        if (idx > list.size()) {
            IntStream.range(list.size(), idx).forEach(i -> list.add(0));
        }
        list.add(idx, e1);
    }

    public int remove(int idx) {
        if (idx < 0 || idx >= list.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return list.remove(idx);
    }

    public void set(int e1, int idx) {
        if (idx < 0 || idx >= list.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        list.set(idx, e1);
    }

    public int get(int idx) {
        if (idx < 0 || idx >= list.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return list.get(idx);
    }

    public int size() {
        return list.size();
    }

    public int count(int e1) {
        return (int) IntStream.range(0, list.size())
                .filter(i -> list.get(i) == e1).count();
    }

    public void removeDuplicates() {
        Collections.reverse(list);
        list = list.stream().distinct().collect(Collectors.toList());
        Collections.reverse(list);
    }

    public int sumFirst(int k) {
        if (k < 0 || k > list.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return list.stream()
                .limit(k)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int sumLast(int k) {
        if (k < 0 || k > list.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return list.stream()
                .skip(list.size() - k)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public void shiftRight(int idx, int k) {
        if (idx < 0 || idx > list.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int newIdx = (idx + k) % list.size();

        int value = list.get(newIdx);

        list.remove(idx);

        list.add(newIdx, value);

    }

    public void shiftLeft(int idx, int k) {
        if (idx < 0 || idx > list.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int newIdx = (idx - k + list.size()) % list.size();
        int value = list.get(newIdx);
        list.remove(idx);
        list.add(newIdx, value);
    }

    public IntegerList addValue(int value) {
        IntegerList newList = new IntegerList();

        newList.list = list.stream()
                .map(i -> i + value)
                .collect(Collectors.toList());

        return newList;
    }
}
