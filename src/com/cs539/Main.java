package com.cs539;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Main {

	public static Set<Relation> checkBcnf(FdList fdList, Relation relation, Set<Relation> output)
			throws CloneNotSupportedException {

		List<Fd> fds = fdList.getList();

		for (int i = 0; i < fds.size(); i++) {
			if (fds.get(i).BCNFviolation(relation)) {
				bcnfDecomposition(relation, fds.get(i), fdList, output);
				break;
			}
		}

		return output;

	}

	public static void bcnfDecomposition(Relation relation, Fd violation, FdList fds, Set<Relation> relList)
			throws CloneNotSupportedException {

		// System.out.println("Relation : " + relation.toString());
		// System.out.println("Violation : " + violation.toString());
		// System.out.println("All Fds for the current relation:\n" + fds.toString());

		Relation x = violation.getLHS();

		Relation xclosure = fds.closure(x);
		Relation y = xclosure.difference(x);

		Relation xy = x.union(y);
		FdList xyFds = project(fds, xy, relation.difference(xy));
		relList.add(xy);

		Relation xz = x.union(relation.difference(xclosure));
		FdList xzFds = project(fds, xz, relation.difference(xz));
		relList.add(xz);

		relList.remove(relation);

		// System.out.println("Decomposed to " + xy.toString() + " and " + xz.toString()
		// + "\n");
		checkBcnf(xyFds, xy, relList);
		checkBcnf(xzFds, xz, relList);

	}

	public static FdList project(FdList oldFds, Relation newRelation, Relation change)
			throws CloneNotSupportedException {
		FdList newFds = new FdList();
		List<Relation> powerSet = newRelation.getPowerSets();
		Iterator<Relation> it = powerSet.iterator();
		while (it.hasNext()) {
			Relation powerRel = it.next();
			Relation computedClosure = oldFds.closure(powerRel);
			Relation changed = computedClosure.difference(change);
			Relation diff = changed.difference(powerRel);
			if (diff != null) {
				newFds.insert(new Fd(powerRel, diff));
			}
		}
		return newFds;
	}

	public static void main(String[] args) throws CloneNotSupportedException {
		BufferedReader br = null;
		try {

			br = new BufferedReader(
					new FileReader(new File("C:\\Users\\Shinigami\\eclipse-workspace\\Db Proj 2\\src\\input2")));

			String strLine = br.readLine();

			RelList relList = new RelList();
			FdList fdList = new FdList();
			FdList newFds = new FdList();

			if (strLine != null) {
				Relation attrList = new Relation(strLine);
				relList.insert(attrList);
			}

			while ((strLine = br.readLine()) != null) {

				String[] cs = strLine.split("->");

				Relation lhs = new Relation(cs[0].trim());
				Relation rhs = new Relation(cs[1].trim());

				fdList.insert(new Fd(lhs, rhs));
			}

			System.out.println("Relation : " + relList);
			System.out.println("Functional dependencies : \n" + fdList);

			List<Relation> powerSet = relList.getFirst().getPowerSets();

			Iterator<Relation> it = powerSet.iterator();

			System.out.println("The list of non trivial dependencies : ");
			while (it.hasNext()) {
				Relation powerRel = it.next();
				Relation computedClosure = fdList.closure(powerRel);
				Relation diff = computedClosure.difference(powerRel);
				if (diff != null) {
					newFds.insert(new Fd(powerRel, diff));
				}
			}
			System.out.println(newFds);

			System.out.println("The list of bcnf violating functional dependencies : ");
			List<Fd> fds = newFds.getList();

			for (int i = 0; i < fds.size(); i++) {
				if (fds.get(i).BCNFviolation(relList.getFirst())) {
					System.out.println(fds.get(i));
				}
			}

			System.out.print("\nThe relations after BCNF Decomposition: ");

			Set<Relation> output = new HashSet<Relation>();
			checkBcnf(newFds, relList.getFirst(), output);
			StringBuilder sb = new StringBuilder();
			String prev = null;
			for (Relation out : output) {
				if (out.toString().equals(prev)) {
					continue;
				}
				sb.append(out.toString() + ",");
				prev = out.toString();
			}
			String outp = sb.length() > 0 ? sb.substring(0, sb.length() - 1) : "";
			System.out.println("{" + outp + "}");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
