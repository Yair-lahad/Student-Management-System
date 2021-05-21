import java.util.Comparator;

public class StringComparator implements Comparator<String>{
    @Override

    public int compare(String s1, String s2) {
    	if (s1==null | s2==null)
     		throw new IllegalArgumentException();
        return s1.compareTo(s2);
    }
}