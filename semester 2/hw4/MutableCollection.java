package inf4;

import java.util.*;

public class MutableCollection<T> extends AbstractCollection<T>{
	private T[] data;
	private int number;
	
	public MutableCollection() {
		this.data = (T[]) new Object[2];
		this.number = 0;
	}
	
	public MutableCollection(MutableCollection<? extends T> collection) {
		this.data = (T[]) new Object[collection.data.length];
		int i = 0;
		for (T element: collection.data) {
			data[i] = element;
			i++;
		}
		this.number = collection.data.length;
	}
	
	public boolean add(T obj) {
		if (number == data.length) {
			expansion();
		}
		if (!((ArrIterator) iterator()).has(obj)) {
			data[number] = obj;
			number++;
			return true;
		} else {
			return false;
		}
	}
	
	public void expansion() {
		T[] newArr = (T[]) new Object[data.length*2];
		for (int i = 0; i < data.length; i++) {
			newArr[i] = data[i];
		}
		data = newArr;
	}
	
	@Override
	public Iterator iterator() {
		return new ArrIterator();
	}

	@Override
	public int size() {
		return data.length;
	}
	
	private class ArrIterator implements Iterator{
		private int cursor = 0;

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
			T obj = (T) data[cursor];
			cursor++;
			return obj;
		}
		
		public boolean has(T obj) {
			for (T el: data) {
				if (el == obj) {
					return true;
				}
			}
			return false;
		}
		
		public void remove() {
			T[] newArr = (T[]) new Object[number - 1];
			for (int i = 0;i < newArr.length; i++) {
				newArr[i] = data[i];
			}
			data = newArr;
			number--;
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

		private MutableCollection getEnclosingInstance() {
			return MutableCollection.this;
		}
	}
	
	public static void main(String[] args) {
		MutableCollection<String> mc = new MutableCollection<>();
		mc.add("heey");
		System.out.println(mc.iterator().next());
		System.out.println(mc.iterator().next());
		mc.iterator().remove();
		System.out.println(mc.iterator().hasNext());
	}
}
