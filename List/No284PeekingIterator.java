package List;

import java.util.Iterator;

public class No284PeekingIterator implements Iterator<Integer>{

	public No284PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    top = null;
        this.iterator = iterator;
	}

    Iterator<Integer> iterator;
    Integer top;
    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        if(top == null) top = iterator.next();
        return top;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
        if(top == null) return iterator.next();
        int tmp = top;
        top = null;
        return tmp;
	}

	@Override
	public boolean hasNext() {
        if(top == null) return iterator.hasNext();
        return true; // top must has a int
	}
}
