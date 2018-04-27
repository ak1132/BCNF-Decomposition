package com.cs539;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RelList {

	private List<Relation> relList;

	private Iterator<Relation> it;

	public RelList() {
		relList = new ArrayList<>();
	}

	public void insert(Relation r) {
		relList.add(r);
	}

	public Relation getFirst() {
		if (!relList.isEmpty() || relList != null) {
			return relList.get(0);
		}
		return null;
	}

	public Relation getNext() {
		it = relList.iterator();
		if (it.hasNext()) {
			return it.next();
		}
		return null;
	}

	public void resetIterator() {
		it = relList.iterator();
	}

	public boolean hasNext() {
		return it.hasNext();
	}

	public Iterator<Relation> getIterator() {
		return relList.iterator();
	}

	public String toString() {
		StringBuilder output = new StringBuilder();
		for (Relation rel : relList) {
			output.append(rel.toString() + "\n");
		}
		return output.toString();
	}

	public void remove(Relation t) {
		relList.remove(t);
	}

	public List<Relation> getList() {
		return relList;
	}
}
