package inf5;

import java.util.*;

public class MapR<E, T> extends AbstractMap<E, T> implements Map<E, T>{
	private E[] kArr;
	private T[] vArr;
	private int size;
	
	public MapR() {
		this.kArr = (E[]) new Object[0];
		this.vArr = (T[]) new Object[0];
	}
	
	public MapR(MapR<? extends E, ? extends T> mapR) {
		this.kArr = (E[]) mapR.kArr;
		this.vArr = (T[]) mapR.vArr;
	}
	
	public boolean containsKey(Object key) {
		for (int i = 0; i < kArr.length; i++) {
			if (kArr[i].equals(key)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean containsValue(Object value) {
		for (int i = 0; i < vArr.length; i++) {
			if (vArr[i].equals(value)) {
				return true;
			}
		}
		return false;
	}
	
	public T put(E key, T value) {
		if (!containsKey(key)) {
			E[] newKArr = (E[]) new Object[size + 1];
			T[] newVArr = (T[]) new Object[size + 1];
			for (int i = 0; i < size; i++) {
				newKArr[i] = kArr[i];
				newVArr[i] = vArr[i];
			}
			newKArr[size] = key;
			newVArr[size] = value;
			kArr = newKArr;
			vArr = newVArr;
			size++;
			return value;
		} 
		printInfo("You already have such key.");
		return null;
	}
	
	public T getV(Object key) { // return value
		for (int i = 0; i < size; i++) {
			if (kArr[i].equals(key)) {
				return vArr[i];
			}
		}
		return null;
	}
	
	public E getK(Object value) { //return key
		for (int i = 0; i < size; i++) {
			if (vArr[i].equals(value)) {
				return kArr[i];
			}
		}
		return null;
	}

	@Override
	public Set<Map.Entry<E, T>> entrySet() {
		return null;
	}
	
	public Set keySet() {
		return new MapSet(kArr);
	}
	
	public Collection values() {
		return new MapSet(vArr);
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}
	
	public void printInfo(String info) {
		System.out.println(info);
	}
	
}
