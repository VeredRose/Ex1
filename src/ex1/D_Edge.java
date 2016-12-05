/*
 *this class represents an edge in a graph, its weight and which vertex it is attached to
 *the other side is represented by the index of the array of vertexes it is saved in 
 */
public class D_Edge implements Comparable <D_Edge>{
	private int vertex;//id of vertex
	private double weigth;//weight to connected vertex-depending on which array it appears in
	
	public D_Edge(int vertex, double weigth) {
		this.vertex = vertex;
		this.weigth = weigth;
	}

	public int getVertex() {
		return vertex;
	}

	public void setVertex(int vertex) {
		this.vertex = vertex;
	}

	public double getWeigth() {
		return weigth;
	}

	public void setWeigth(double weigth) {
		this.weigth = weigth;
	}
	@Override
	public int compareTo(D_Edge o) {
		return ((Double)this.weigth).compareTo(o.weigth);
	}
}
