package small_yan_class18;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ZigzagIterator2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	Iterator<Iterator<Integer>> outer;
	Iterator<Integer> inner;
	
	List<Iterator<Integer>> list;
	boolean flag;
	
	public ZigzagIterator2(List<Iterator<Integer>> list) {
		this.list = list;
		inner = null;
		outer = list.iterator();
		flag = false;
	}
	
	public boolean hasNext() {
		if (!flag) {
			succeed();
			flag = true;
		}
		if (inner == null || !inner.hasNext()) {
			return false;
		}
		return true;
	}
	
	public Integer next() {
		if (!flag) {
			succeed();
		}
		if (inner == null || !inner.hasNext()) {
			flag = true;
			throw new NoSuchElementException("no such element");
		}
		flag = false;
		return inner.next();
	}
	
	
	
	private void succeed() {
		move();
		if (inner != null && inner.hasNext()) {
			return ;
		}
		outer = list.iterator();
		move();
	}
	
	private void move() {
		while(outer.hasNext()) {
			inner = outer.next();
			if (!inner.hasNext()) {
				outer.remove();
			} else {
				break;
			}
		}
	}
	
	
}
