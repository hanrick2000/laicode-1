package small_yan_class18;

import java.util.Iterator;
import java.util.List;


/*
 * more investigation
 */
public class SequenceIterator2 implements Iterator<Integer>{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	Iterator<Integer> inner;
	Iterator<Iterator<Integer>> outer;
	public SequenceIterator2(List<Iterator<Integer>> list) {
		inner = null;
		outer = list.iterator();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		move();
		if (inner == null || ! inner.hasNext()) {
			return false;
		}
		return true;
	}

	@Override
	public Integer next() {
		// TODO Auto-generated method stub
		if (hasNext()) {
			return inner.next();
		}
		return null;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
	private void move() {
		while((inner == null || !inner.hasNext()) && outer.hasNext()) {
			inner = outer.next();
		}
	}

}
