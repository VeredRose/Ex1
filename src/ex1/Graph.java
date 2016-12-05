import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
public class Graph {

	private Vertex [] graph;
	private double[]exentrics;//the longest path that connects to i starts at exentrics[i]
	private double diameter;
	private double radius;
	private int numOfEdges;
	private int numOfVertexes;
	private boolean isTie;

	//constructor which reads from file
	public Graph(String filename) {
		readFromFile(filename);
		//printToConsole();
		calculateDiameterAndRadius();
	}
	//return shortest distance between two vertexes
	public double distance(int vertex1, int vertex2) {
		calculateDijkstra(vertex1);
		double distance= graph[vertex2].distance;
		clearDijkstra();
		return distance;
	}
	//return shortest distance between two vertexes without passing in list of blocked vertexes
	public double distanceWithBlackVertexes(int vertex1, int vertex2,int[]blockedVertxes) {
		for (int i = 0; i < blockedVertxes.length; i++) {
			graph[blockedVertxes[i]].isVisited=true;
		}
		calculateDijkstra(vertex1);
		double distance= graph[vertex2].distance;
		clearDijkstra();
		return distance;
	}
	public double getDiameter() {
		return diameter;
	}

	public double getRadius() {
		return radius;
	}

	public Vertex[] getGraph() {
		return graph;
	}

	public int getNumofedges() {
		return numOfEdges;
	}
	public int getNumOfVertexes() {
		return numOfVertexes;
	}
	public String isTie() {


		if(isTie){
			return "TIE";
		}
		return "!TIE";
	}
	public void printToConsole(){
		for (int i = 0; i < graph.length; i++) {
			System.out.print("vertex "+graph[i].id+" : ");
			for (int j = 0; j < graph[i].edges.size(); j++) {
				System.out.print(graph[i].edges.get(j).getVertex()+" ");
			}
			System.out.println("");
		}
	}
	//calculate all distances between start vertex and all others
	private void calculateDijkstra(int start){
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		graph[start].distance = 0;
		queue.add(graph[start]);
		while(!queue.isEmpty()) {
			int v = queue.poll().id;
			graph[v].isVisited = true;
			for(D_Edge edge : graph[v].edges) {
				int u = edge.getVertex();
				if(!graph[u].isVisited) {
					if(graph[u].distance > graph[v].distance + edge.getWeigth()) {
						queue.remove(graph[u]);
						graph[u].parent = v;
						graph[u].distance = graph[v].distance + edge.getWeigth();
						queue.add(graph[u]);

					}

				}
			}
		}
	}
	private void clearDijkstra() {
		for (int i = 0; i < graph.length; i++) {
			graph[i].distance=Double.POSITIVE_INFINITY;
			graph[i].isVisited=false;
			graph[i].parent = -1;
		}

	}

	private void calculateDiameterAndRadius() {
		isTie = true;
		exentrics = new double[graph.length];
		/*
		 * for every vertex in graph we calculate paths to all others
		 *  and choose the maximal path-called exentric[i]
		 */
		for (int i = 0; i < exentrics.length; i++) {
			calculateDijkstra(i);
			double max=0;
			for (int j = 0; j < graph.length; j++) {
				if(max<graph[j].distance){
					max=graph[j].distance;
				}
				exentrics[i]=max;	
			}
			for (int j = 0; j < graph[i].edges.size(); j++) {
				int vertex = graph[i].edges.get(j).getVertex(); // take vertex neighbor
				int shortestPath = getShorestPath(i,vertex); //  find shortest path between vertex and his neighbor
				if (shortestPath > 1) { // if path longer then 1 Edge => TE = false
					isTie = false;
				}
				clearDijkstra();
			}
		}
		/*
		 * by definition:
		 * radius = minimal exentric
		 * diameter = maximal exentric
		 */
		radius=Double.MAX_VALUE;
		diameter=0;
		for (int i = 0; i < exentrics.length; i++) {
			if(radius>exentrics[i]){
				radius=exentrics[i];
			}
			if(diameter<exentrics[i]){
				diameter=exentrics[i];
			}
		}
	}
	private int getShorestPath(int i,int v) {
		int t = v;
		int ans = 0;
		while (t != i && t != -1) {
			t = graph[t].parent;
			ans += 1;
		}
		return ans;
	}
	private void readFromFile(String path){
		try{
			File file = new File(path);
			Scanner s= new Scanner(file);
			numOfVertexes=s.nextInt();
			numOfEdges=s.nextInt();
			this.graph=new Vertex[numOfVertexes];
			for (int i = 0; i < graph.length; i++) {
				graph[i]=new Vertex(i, new ArrayList<D_Edge>());
			}
			while(s.hasNext()){
				int vertexIndex=s.nextInt();
				int connectedVertexIndex=s.nextInt();
				double weight=s.nextDouble();
				graph[vertexIndex].edges.add(new D_Edge(connectedVertexIndex,weight));
				graph[connectedVertexIndex].edges.add(new D_Edge(vertexIndex,weight));
			}
			s.close();
		}
		catch(Exception e){}
	}

}
