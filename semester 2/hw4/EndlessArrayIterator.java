package inf4;

import java.util.Iterator;
import java.util.NoSuchElementException;

class EndlessArrayIterator<T> implements Iterator<T>{
	private EndlessArray<T> arr;
	private int cursor;
	
	public EndlessArrayIterator(EndlessArray<T> arr) {
		this.arr = arr;
		this.cursor = 0;
	}

	@Override
	public boolean hasNext() {
		for (int i = cursor; i < arr.getLenght(); cursor += 2) {
				if (arr.get(i) != null) {
					return true;
				}
		}
		return false;
	}

	@Override
	public T next() {
		try {
			for (int i = cursor; i < arr.getLenght(); i += 2) {
				cursor += 2;
				if (arr.get(i) != null) {
					T element = (T) arr.get(i);
					
					return element;
				}
			} return null;
			
		} 
		catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchElementException("That's not OK.");
		}
	}

	public EndlessArray<T> getArr() {
		return arr;
	}

	public void setArr(EndlessArray<T> arr) {
		this.arr = arr;
	}

	public int getCursor() {
		return cursor;
	}

	public void setCursor(int cursor) {
		this.cursor = cursor;
	}
}
