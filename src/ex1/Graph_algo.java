package ex1;

public class Graph_algo {

	/*
	 *returns the distance of shortest path between 2 vertexes in a graph 	
	 */
	public double distance(Graph g, int vertex1,int vertex2){
		return g.distance(vertex1,vertex2);
	}
	/*
	 *returns the distance of shortest path between 2 vertexes in a graph without passing in
	 *a list of vertexes 	
	 */
	public double blockedDistance(Graph g, int vertex1,int vertex2,int[]blockedVertxes){
		return g.distanceWithBlackVertexes(vertex1,vertex2,blockedVertxes);
	}
	public double radius(Graph g){
		return g.getRadius();
	}
	public double diameter(Graph g){
		return g.getDiameter();
	}
	public int numOfEdges(Graph g){
		return g.getNumofedges();
	}
	public int numOfVertexes(Graph g){
		return g.getNumOfVertexes();
	}
	public String isTie(Graph g) {
		return g.isTie();
	}
}
