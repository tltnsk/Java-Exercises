package Lab05.complexnums;

import java.util.Comparator;

public class ComplexNumber<T extends Number, U extends Number> implements Comparable<ComplexNumber<?,?>>{
    private final T real;
    private final U imaginary;

    public ComplexNumber(T real, U imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public T getReal() {
        return real;
    }

    public U getImaginary() {
        return imaginary;
    }

    public double modul() {
        return Math.sqrt(Math.pow(real.doubleValue(), 2) + Math.pow(imaginary.doubleValue(), 2));
    }

    @Override
    public int compareTo(ComplexNumber<?, ?> o) {
        return Double.compare(this.modul(), o.modul());
    }

    @Override
    public String toString() {
        return String.format("%.2f+%.2fi", real.doubleValue(), imaginary.doubleValue());
    }
}
