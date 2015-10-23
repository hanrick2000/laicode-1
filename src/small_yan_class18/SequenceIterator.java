package small_yan_class18;

import java.util.Iterator;



/*
 * {{1, 2, 3}, {}, {4}, {}, {5, 6}}
 *  1, 2, 3, 4, 5, 6.
 */

public class SequenceIterator implements Iterator<Integer>{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] array = {
				{},
				{1,2,3},
				{},
				{},
				{4},
				{5,6}
		};
		SequenceIterator seq = new SequenceIterator(array);
		while (seq.hasNext()) {
			System.out.println(seq.next());
		}
	}
	
	public int outIndex;
	public int inIndex;
	int[][] array;
	
	public SequenceIterator(int[][] array) {
		// TODO Auto-generated constructor stub
		this.array = array;
		outIndex = 0;
		inIndex = 0;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		while(outIndex < array.length && inIndex == array[outIndex].length) {
			outIndex ++;
			inIndex = 0;
		}
		if (outIndex < array.length && (inIndex < array[outIndex].length)) {
			return true;
		}
		return false;
	}

	@Override
	public Integer next() {
		// TODO Auto-generated method stub
		if (hasNext()) {
			int result = array[outIndex][inIndex];
			inIndex ++;
			// here, we need to use while, not if, 
			// since there might many {}, {}, {}, etc
			while(outIndex < array.length && inIndex == array[outIndex].length) {
				outIndex ++;
				inIndex = 0;
			}
			return result;
		} 
		return null;
		
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub	
	}
}
