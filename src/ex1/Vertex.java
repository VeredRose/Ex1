package ex1;

import java.util.ArrayList;

public class Vertex implements Comparable<Vertex>{
	public int id, parent;//parent = the previous vertex from best path from start point 
	public boolean isVisited;// was visited when going over all graph
	public double distance;//distance from start point
	public ArrayList<D_Edge> edges;//all vertexes connected to this vertex

	public Vertex(int id,ArrayList<D_Edge> edges) {
		this.id = id;
		this.edges = edges;
		isVisited = false;
		distance = Double.POSITIVE_INFINITY;
		parent = -1;
	}
//copy constructor
	public Vertex(Vertex vertex) {
		id = vertex.id;
		edges = new ArrayList<D_Edge>();
		for (int i = 0; i < edges.size(); i++) {
			edges.add(i, new D_Edge(vertex.edges.get(i).getVertex(),vertex.edges.get(i).getWeigth()));
		}
		isVisited = vertex.isVisited;
		distance = vertex.distance;
		parent = vertex.parent;
	}

	@Override
	public int compareTo(Vertex v) {
		return ((Double)this.distance).compareTo(v.distance);
	}
	}
