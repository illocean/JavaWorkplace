package com.pup.dit.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import com.pup.dit.model.Equipment;

public class EquipmentRegistry<T extends Equipment> {

	private final List<T> equipment = new ArrayList<>();

	public void add(T item) {
		equipment.add(item);
	}

	public List<T> all() {
		return equipment;
	}

	public boolean isEmpty() {
		return equipment.isEmpty();
	}

	public T findById(int id) {
		for (T item : equipment) {
			if (item.getId() == id) {
				return item;
			}
		}
		return null;
	}

	public static <E> String toText(List<E> list) {
		StringJoiner text = new StringJoiner("\n\n");
		for (E item : list) {
			text.add(String.valueOf(item));
		}
		return text.toString();
	}

}
