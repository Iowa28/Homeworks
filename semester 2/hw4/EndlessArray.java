package inf4;

import java.util.Arrays;

import java.util.Iterator;
import java.util.NoSuchElementException;
import inf4.IndexException;

/**
*
* Endless array. Has the ability to add elements to the array and delete them.
* @author Aminov Niaz yazzzik@list.ru
* 
* The code is written by a student studying at the Kazan Federal University, ITIS. First course. 11-903 group.
* 
*/

public class EndlessArray<T> {
	private int size;
	private int freeCapacity;
	private T[] arr;
	
	public EndlessArray(T[] arr) {
		this.arr = arr;
		this.size = arr.length;
		this.freeCapacity = 0;
	}
	
	public void add(T obj) {
		if (freeCapacity == 0) {
			expansion();
		}
		arr[getLenght()] = obj;
		freeCapacity--;
	}
	
	public void remove(int index) throws IndexException {
		if (index >= getLenght()) {
			throw new IndexException("The element with such index remains.");
		}
		T[] arrCopy = arr;
		size--;
		arr = (T[]) new Object[size];
		int j = 0;
		for (int i = 0; i < arr.length; i++) {
			if (j == index) {
				j++;
			}
			arr[i] = arrCopy[j];
			j++;
		}
	}
	
	private void expansion() {
		T[] arrCopy = arr;
		size++;
		freeCapacity++;
		arr = (T[]) new Object[size];
		for (int i = 0; i < arrCopy.length; i++) {
			arr[i] = arrCopy[i];
		}
		
	}
	
	public void idnexOf(T obj) {
		for (int i = 0; i < getLenght(); i++) {
			if (arr[i] == obj) {
				printN(i);
				System.exit(1);
			}
		}
		printInfo("There is no such number.");
	}
	
	public void indexOf(T obj, int fromIndex) throws IndexException {
		if (fromIndex >= getLenght()) {
			throw new IndexException("The element with such index remains.");
		}
		for (int i = fromIndex; i < getLenght(); i++) {
			if (arr[i] == obj) {
				printN(i);
				System.exit(1);
			}
		}
		printInfo("There is no such number.");
	}
	
	public T get(int index) {
			return arr[index];
	}
	
	public void sort() {
		System.out.println(arr.getClass());
		for (int j = 0; j < arr.length; j++) {
			for (int i = 0; i < arr.length - 1; i++) {
				
			}
		}
	}
	
	public int getLenght() {
		return size - freeCapacity;
	}
	
	public void printArr() {
		for (int i = 0; i < getLenght(); i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	public  void printInfo(String s) {
		System.err.println(s);
	}
	
	public void printN(int number) {
		System.out.println(number);
	}
	
	public void print(T obj) {
		System.out.println(obj);
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getFreeCapacity() {
		return freeCapacity;
	}

	public void setFreeCapacity(int freeCapacity) {
		this.freeCapacity = freeCapacity;
	}

	public T[] getArr() {
		return arr;
	}

	public void setArr(T[] arr) {
		this.arr = arr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(arr);
		result = prime * result + freeCapacity;
		result = prime * result + size;
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
		EndlessArray other = (EndlessArray) obj;
		if (!Arrays.deepEquals(arr, other.arr))
			return false;
		if (freeCapacity != other.freeCapacity)
			return false;
		if (size != other.size)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EndlessArray [size=" + size + ", freeCapacity=" + freeCapacity + ", arr=" + Arrays.toString(arr) + "]";
	}	
	
}
