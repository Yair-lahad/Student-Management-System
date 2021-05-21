public class SimpleIntPair {
    private int first;
    private int second;

    public SimpleIntPair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public SimpleIntPair(SimpleIntPair other) {
        this(other.getFirst(), other.getSecond());
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public void setFirst(int value) {
        first = value;
    }

    public void setSecond(int value) {
        second = value;
    }

    public boolean equals(Object other) {
        return other instanceof SimpleIntPair &&
                (first == ((SimpleIntPair) other).getFirst() &
                        second == ((SimpleIntPair) other).getSecond());
    }

    public String toString() {
        return "(" + getFirst() + ", " + getSecond() + ")";
    }
}
