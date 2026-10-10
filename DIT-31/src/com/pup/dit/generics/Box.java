package com.pup.dit.generics;

/**
 * 09 - 26 - 2026
 * 
 * Notes.java
 * Generics + Methods
 * 
 * @param <T> = type parameter (dynamic data type of item)
 */
public class Box <T>{
	//string without type parameter
	/*T item;
	
	public void setItem(T item) {
		this.item = item;
	}
	
	public T getItem() {
		return this.item;
	}*/
	
	
//	public static int  method(int number) - by dafault
	public static<T> T method(T item) {
		
		return item;
	}
	
	
}
