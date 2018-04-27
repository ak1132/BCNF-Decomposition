package com.cs539;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FdList {

	private List<Fd> fdList;

	private Iterator<Fd> it;

	public FdList() {
		fdList = new ArrayList<>();
	}

	public void insert(Fd fd) {
		fdList.add(fd);
		it = fdList.iterator();
	}

	public Fd getFirst() {
		if (!fdList.isEmpty() || fdList != null) {
			return fdList.get(0);
		}
		return null;
	}

	public Fd getNext() {
		if (it.hasNext()) {
			return it.next();
		}
		return null;
	}

	public Relation closure(Relation r) throws CloneNotSupportedException {

		Relation closure = (Relation) r.clone();
		Relation prevClosure = null;
		while (true) {
			for (Fd fd : fdList) {
				if (closure.subset(fd.getLHS())) {
					closure = closure.union(fd.getRHS());
				}
			}
			if (closure.equals(prevClosure)) {
				return closure;
			}
			prevClosure = closure;
		}
	}

	public boolean hasNext() {
		return it.hasNext();
	}

	public Iterator<Fd> getIterator() {
		return fdList.iterator();
	}

	public List<Fd> getList() {
		return fdList;
	}

	public String toString() {
		StringBuilder output = new StringBuilder();
		for (Fd rel : fdList) {
			output.append(rel.toString() + "\n");
		}
		return output.toString();
	}

	public void resetIterator() {
		it = fdList.iterator();
	}
}
