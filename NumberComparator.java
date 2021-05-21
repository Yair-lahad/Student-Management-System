import java.util.Comparator;

public class NumberComparator implements Comparator<Integer>{

	@Override

	public int compare(Integer i1, Integer i2) {
		if (i1==null | i2==null)
			throw new IllegalArgumentException();
		return i1.compareTo(i2);
	}
}