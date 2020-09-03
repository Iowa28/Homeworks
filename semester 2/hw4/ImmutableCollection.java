package inf4;

import java.util.AbstractCollection;
import java.util.Iterator;

public class ImmutableCollection<T> extends AbstractCollection<T>{
	private T[] data;
	
	public ImmutableCollection() {
		this.data = (T[]) new Object[0];
	}
	
	public ImmutableCollection(ImmutableCollection<? extends T> collection) {
		this.data = (T[]) new Object[collection.data.length];
		int i = 0;
		for (T element: collection.data) {
			data[i] = element;
			i++;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrIterator();
	}
	
	@Override
	public int size() {
		return data.length;
	}
	
	private class ArrIterator implements Iterator{
		private int cursor;
		
		public ArrIterator() {
			this.cursor = 0;
		}

		@Override
		public boolean hasNext() {
			for (T el: data) {
				if (!el.equals(null)) {
					return true;
				}
			} return false;
		}

		@Override
		public Object next() {
			T obj = data[cursor];
			cursor++;
			return obj;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + cursor;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ArrIterator other = (ArrIterator) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (cursor != other.cursor)
				return false;
			return true;
		}

		private ImmutableCollection getEnclosingInstance() {
			return ImmutableCollection.this;
		}
		
		
	}
	
	public static void main(String[] args) {
		ImmutableCollection<String> ic = new ImmutableCollection<>();
		
	}
}
