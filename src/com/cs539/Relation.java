package com.cs539;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Relation implements Cloneable {

	private int[] a = new int[26];

	public Relation() {
	}

	public Relation(String in_r) {
		String[] s = in_r.split(" ");
		for (String ch : s) {
			a[ch.toUpperCase().charAt(0) - 65] = 1;
		}
	}

	public boolean contains(char c) {
		return a[c - 65] == 1;
	}

	public boolean subset(Relation r2) {
		if (getCharSet(this.a).containsAll(getCharSet(r2.a))) {
			return true;
		}
		return false;
	}

	public Relation union(Relation r2) {
		if (r2 == null) {
			return this;
		}
		Set<Character> aSet = getCharSet(a);
		Set<Character> bSet = getCharSet(r2.a);
		aSet.addAll(bSet);
		Relation newRel = new Relation();
		List<Character> aList = new ArrayList<>(aSet);
		if (!aList.isEmpty()) {
			for (int i = 0; i < aList.size(); i++) {
				newRel.a[aList.get(i) - 65] = 1;
			}
			return newRel;
		}
		return null;
	}

	public Relation intersect(Relation r2) {

		if (r2 == null) {
			return null;
		}

		Set<Character> aSet = getCharSet(a);
		Set<Character> bSet = getCharSet(r2.a);
		aSet.retainAll(bSet);
		Relation newRel = new Relation();
		List<Character> aList = new ArrayList<>(aSet);
		if (!aList.isEmpty()) {
			for (int i = 0; i < aList.size(); i++) {
				newRel.a[aList.get(i) - 65] = 1;
			}
			return newRel;
		}
		return null;
	}

	public Relation difference(Relation r2) {

		if (r2 == null) {
			return this;
		}

		Set<Character> aSet = getCharSet(this.a);
		Set<Character> bSet = getCharSet(r2.a);
		aSet.removeAll(bSet);
		Relation newRel = new Relation();
		List<Character> aList = new ArrayList<>(aSet);
		if (!aList.isEmpty()) {
			for (int i = 0; i < aList.size(); i++) {
				newRel.a[aList.get(i) - 65] = 1;
			}
			return newRel;
		}
		return null;
	}

	public Set<Relation> getPowerSets() {
		List<Character> charSet = new ArrayList<Character>(getCharSet(this.a));
		Set<Relation> powerSet = new HashSet<>();
		int n = charSet.size();

		for (int i = 0; i < (1 << n); i++) {
			List<Character> sets = new ArrayList<>();

			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) > 0) {
					sets.add(charSet.get(j));
				}
			}
			String str = getStringRepresentation(sets);
			if (str != null) {
				powerSet.add(new Relation(str));
			}
		}
		return powerSet;
	}

	private String getStringRepresentation(List<Character> sets) {

		if (sets.isEmpty()) {
			return null;
		}
		String builder = new String();
		for (Character ch : sets) {
			builder += (ch + " ");
		}
		return builder.toString().trim();
	}

	private Set<Character> getCharSet(int[] arr) {
		if (arr == null) {
			return null;
		}
		Set<Character> charset = new HashSet<>();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 1)
				charset.add((char) (i + 65));
		}
		return charset;
	}

	public Set<Character> getCharacterRepSet() {
		Set<Character> charset = new HashSet<>();
		for (int i = 0; i < a.length; i++) {
			if (a[i] == 1)
				charset.add((char) (i + 65));
		}
		return charset;
	}

	public String toString() {
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < a.length; i++) {
			if (a[i] == 1) {
				output.append((char) (i + 65));
			}
		}
		return output.toString();
	}

	protected Relation clone() throws CloneNotSupportedException {
		return (Relation) super.clone();
	}

	@Override
	public boolean equals(Object o) {

		if (o == null) {
			return false;
		}

		if (o == this) {
			return true;
		}

		if (!(o instanceof Relation)) {
			return false;
		}

		Relation r = (Relation) o;

		for (int i = 0; i < a.length; i++) {
			if (a[i] != r.a[i])
				return false;
		}
		return true;
	}

}
