package inf5;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MapSet<T> implements Set<T>{
	private T[] data;
	
	public MapSet(T[] arr) {
		this.data = arr;
	}

	@Override
	public boolean add(Object el) {
		printInfo("This operation is not supported");
		return false;
	}

	@Override
	public boolean addAll(Collection coll) {
		printInfo("This operation is not supported");
		return false;
	}

	@Override
	public void clear() {
		this.data = (T[]) new Object[0];
		
	}

	@Override
	public boolean contains(Object el) {
		for (int i = 0; i < data.length; i++) {
			if (data[i].equals(el)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection coll) {
		int collSize = coll.size();
		for (int i = 0; i < collSize; i++) {
			Object el = coll.iterator().next();
			if (!contains(el)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		if (data.length == 0) {
			return true;
		}
		return false;
	}

	@Override
	public Iterator iterator() {
		return new ArrIterator();
	}

	@Override
	public boolean remove(Object el) {
		if (contains(el)) {
			T[] newData = (T[]) new Object[data.length - 1];
			int j = 0;
			for (int i = 0; i < data.length; i++) {
				if (!data[i].equals(el)) {
					newData[j] = data[i];
					j++;
				}
			}
			this.data = newData;
			return true;
		}
		return false;
	}

	@Override
	public boolean removeAll(Collection coll) {
		if (containsAll(coll)) {
			T[] newData = (T[]) new Object[data.length - coll.size()];
			int j = 0;
			Object el = coll.iterator().next();
			for (int i = 0; i < data.length; i++) {
				if (!data[i].equals(el)) {
					newData[j] = data[i];
					j++;
				} else {
					el = coll.iterator().next();
				}
			}
			this.data = newData;
			return true;
		}
		return false;
	}

	@Override
	public boolean retainAll(Collection coll) {
		if (containsAll(coll)) {
			T[] newData = (T[]) new Object[coll.size()];
			int j = 0;
			Object el = coll.iterator().next();
			for (int i = 0; i < data.length; i++) {
				if (data[i].equals(el)) {
					newData[j] = data[i];
					j++;
					el = coll.iterator().next();
				}
			}
			this.data = newData;
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		return data.length;
	}

	@Override
	public Object[] toArray() {
		return data;
	}

	@Override
	public Object[] toArray(Object[] someArr) {
		Object[] arr = (Object[]) data;
		return arr;
	}
	
	public T get(int index) {
		return data[index];
	}
	
	private class ArrIterator implements Iterator{
		private int cursor;
		private T[] arr;
		
		public ArrIterator() {
			this.cursor = 0;
			this.arr = data;
		}

		@Override
		public boolean hasNext() {
			for (int i = cursor; i < data.length; i++) {
				if (!data[i].equals(null)) {
					return true;
				}
			} return false;
		}

		@Override
		public Object next() {
			if (hasNext()) {
				T obj = (T) arr[cursor];
				cursor++;
				return obj;
			} return null;
		}
	}
	
	public void printInfo(String info) {
		System.out.println(info);
	}
}
