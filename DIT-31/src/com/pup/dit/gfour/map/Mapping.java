package com.pup.dit.gfour.map;

import java.util.HashMap;
import java.util.Map;

public class Mapping {

	public static void main(String[] args) {
		
		//uses for key-value pair
		Map<String, String> toyBox = new HashMap<>();
		
			toyBox.put("actionFigure", "barbie");
			toyBox.put("Car", "Missyoubishi");
			toyBox.put("Lego", "city");
			
			String myToy = toyBox.get("Car");
			
			System.out.print(myToy);
	}
}
