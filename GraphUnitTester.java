import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GraphUnitTester {

	@Test (timeout = 1000)
	public void nearbyOntario_1_15pts() {
		Graph g = new Graph("Edges.csv");
		String origin = "Oshawa";
		int distance = 2;
		Set <String> modelSet = Graphs.nearby(g, origin, distance);
		Set <String> actualSet = Graphs.nearby(g, origin, distance);
		removeQuotes(modelSet);
		removeQuotes(actualSet);
		System.out.println(modelSet);
		System.out.println(actualSet);
		assertTrue("Incorrect entry format or sets of cities are different", modelSet.equals(actualSet));
	}

	@Test (timeout = 1000)
	public void nearbyOntario_2_15pts() {
		Graph g = new Graph("Edges.csv");
		String origin = "Grimsby";
		int distance = 3;
		Set <String> modelSet = Graphs.nearby(g, origin, distance);
		Set <String> actualSet = Graphs.nearby(g, origin, distance);
		removePunctuation(modelSet);
		removePunctuation(actualSet);
		System.out.println(modelSet);
		System.out.println(actualSet);
		modelSet.retainAll(actualSet);
		assertTrue("Some cities are missing and some are extraneous", !modelSet.isEmpty());
	}

	@Test (timeout = 1000)
	public void nearbyOntario_3_20pts() {
		Graph g = new Graph("Edges.csv");
		String origin = "Grimsby";
		int distance = 3;
		Set <String> modelSet = Graphs.nearby(g, origin, distance);
		Set <String> actualSet = Graphs.nearby(g, origin, distance);
		removePunctuation(modelSet);
		removePunctuation(actualSet);
		System.out.println(modelSet);
		System.out.println(actualSet);
		actualSet.addAll(modelSet);
		assertTrue("Some cities are extraneous", modelSet.equals(actualSet));
	}

	@Test (timeout = 1000)
	public void nearbyOntario_4_20pts() {
		Graph g = new Graph("Edges.csv");
		String origin = "Grimsby";
		int distance = 3;
		Set <String> modelSet = Graphs.nearby(g, origin, distance);
		Set <String> actualSet = Graphs.nearby(g, origin, distance);
		removePunctuation(modelSet);
		removePunctuation(actualSet);
		System.out.println(modelSet);
		System.out.println(actualSet);
		modelSet.addAll(actualSet);
		assertTrue("Some cities are missing", modelSet.equals(actualSet));
	}

	@Test (timeout = 1000)
	public void nearbyEdges1_1_15pts() {
		Graph g = new Graph("Edges1.csv");
		String origin = "0";
		int distance = 1;
		Set <String> modelSet = Graphs.nearby(g, origin, distance);
		Set <String> actualSet = Graphs.nearby(g, origin, distance);
		removePunctuation(modelSet);
		removePunctuation(actualSet);
		System.out.println(modelSet);
		System.out.println(actualSet);
		assertTrue("Sets of vertices are different", modelSet.equals(actualSet));
	}

	@Test (timeout = 1000)
	public void nearbyEdges1_2_15pts() {
		Graph g = new Graph("Edges1.csv");
		String origin = "6";
		int distance = 2;
		Set <String> modelSet = Graphs.nearby(g, origin, distance);
		Set <String> actualSet = Graphs.nearby(g, origin, distance);
		removePunctuation(modelSet);
		removePunctuation(actualSet);
		System.out.println(modelSet);
		System.out.println(actualSet);
		assertTrue("Sets of vertices are different", modelSet.equals(actualSet));
	}
	
	private void removeQuotes(Set <String> set) {
		List <String> list = new ArrayList<>(set);
		set.clear();
		for (String s : list) {
			s = s.replace("\"", "");
			set.add(s);
		}
	}

	private void removePunctuation(Set <String> set) {
		List <String> list = new ArrayList<>(set);
		set.clear();
		for (String s : list) {
			s = s.replace("\"", "");
			s = s.replace(",", "");
			set.add(s);
		}
	}

}
