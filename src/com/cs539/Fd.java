package com.cs539;

public class Fd {

	private Relation lhs, rhs;

	public Fd(Relation in_lhs, Relation in_rhs) {
		super();
		this.lhs = in_lhs;
		this.rhs = in_rhs;
	}

	public Relation getLHS() {
		return lhs;
	}

	public Relation getRHS() {
		return rhs;
	}

	public String toString() {
		return lhs.toString() + " -> " + rhs.toString();
	}

	public boolean BCNFviolation(Relation s) {
		Relation u = lhs.union(rhs);

		if (s.subset(lhs) && s.intersect(rhs) != null && !u.subset(s)) {
			return true;
		}
		return false;
	}

}
