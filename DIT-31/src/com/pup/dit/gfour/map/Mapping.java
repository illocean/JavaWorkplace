package com.pup.dit.gfour.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Mapping {

	public static void main(String[] args) {
		
		//uses for key-value pair
		Map<String, String> toyBox = new HashMap<>();
		
			toyBox.put("actionFigure", "barbie");
			toyBox.put("Car", "Missyoubishi");
			toyBox.put("Lego", "city");
			toyBox.put("Lego1", "city");
			
			String myToy = toyBox.get("Lego1");
			//System.out.println(myToy);
			
			//
			HashSet<Object> candyBag = new HashSet<>();
			candyBag.add("redCandy");
			candyBag.add("blueCandy");
			candyBag.add("redCandy");
			
			candyBag.add("redCandy");
			candyBag.add(1);
			//System.out.println(candyBag);
			
			
			
		List<String> students = new ArrayList<>();
		students.add("student 1");
		students.add("student 2");
		students.add("student 3");
		students.add("student 4");
		students.add("student 5");
		
		//safest way to loop, just use foreach loop if model (best option)
		Iterator<String> loop = students.iterator();
		
		while (loop.hasNext()) {
			System.out.println(loop.next());
			
		}
	}
}