package hw21;

import java.util.Arrays;

public class Set<T> {
	private T[] data;
	
	public Set() {
		this.data = (T[]) new Object[0];
	}
	
	public Set(T[] arr) {
		this.data = arr;
	}

	public void add(T obj) {
		if (!has(obj)) {
			T[] newArr = (T[]) new Object[data.length + 1];
			for (int i = 0; i < data.length; i++) {
				newArr[i] = data[i];
			}
			newArr[data.length] = obj;
			data = newArr;
		}
	}
	
	public boolean has(T obj) {
		for (T element: data) {
			if (element.equals(obj)) {
				return true;
			}
		} return false;
	}
	
	public int size() {
		return data.length;
	}
	
	public void remove(T obj) {
		if (has(obj)) {
			T[] newArr = data;
			int j = 0;
			data = (T[]) new Object[newArr.length - 1];
			for (int i = 0; i < newArr.length; i++) {
				if (!newArr[i].equals(obj)) {
					data[j] = newArr[i];
					j++;
				}
			}
		}
	}
	
	public Set<T> merge(Set<T> setR) {
		T[] newArr = (T[]) new Object[data.length + setR.size()];
		for(int i = 0; i < data.length; i++) {
			newArr[i] = data[i];
		}
		
		int n = setR.size();
		T[] otherArr = (T[]) setR.getData();
		for (int i = 0; i < otherArr.length; i++) {
			newArr[n] = otherArr[i];
		}
		return new Set<T>(newArr);
	}
	
	public boolean contains(Set<T> osr) {
		T[] otherArr = (T[]) osr.getData();
		int n = otherArr.length;
		int m = n;
		for (int j = 0; j < otherArr.length; j++) {
			for (int i = 0; i < this.data.length; i++) {
				if (otherArr[j].equals(this.data[i])) {
					m--;
				}
			} if (m == n) {
				return false;
			} n--;
		}
		return true;
	}
	
	public T[] getData() {
		return data;
	}

	public void setData(T[] arr) {
		this.data = arr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(data);
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
		Set other = (Set) obj;
		if (!Arrays.deepEquals(data, other.data))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Set [data=" + Arrays.toString(data) + "]";
	}
	
	public static void main(String[] args) {
		Set<String> set = new Set<>(new String[] {"Java", ".NET"});
		Set<String >newSet = new Set<>(new String[] {"Java"});
		System.out.println(set.contains(newSet));
	}
}
