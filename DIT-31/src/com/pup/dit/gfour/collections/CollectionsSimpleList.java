package com.pup.dit.gfour.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * 	Group 4
 *  10-10-26
 *	JAVA COLLECTION FRAMEWORKS 
 */
public class CollectionsSimpleList {

	public static void main(String[] args) {
		
		   //wrapper
		List<String> Duvan = new ArrayList<>();
		Duvan.add("String");
		Duvan.add("Integer");
		Duvan.add("Float");
		Duvan.add("Double");
		Duvan.add("Character");
		Duvan.add("Character");
		
		//JOptionPane.showMessageDialog(null, "List of Duvan: " + Duvan.get(1));
		
		LinkedList<String> JMPinkman = new LinkedList<>();
		
		/*
		JMPinkman.push(10);
		JMPinkman.push(20);
		JMPinkman.push(20);
		JMPinkman.push(30);
		JMPinkman.push(40);
		JMPinkman.push(50);
		JMPinkman.push(60);
		*/
		
		JMPinkman.offer("Pangalan 1");
		JMPinkman.offer("Pangalan 2");
		JMPinkman.offer("Pangalan 4");
		JMPinkman.offer("Pangalan 5");
		
		JMPinkman.add(2, "Pangalan 3");
		
		System.out.print(JMPinkman);
		
	}
}
