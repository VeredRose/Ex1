package ex1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Test {
	private final int GRAPH_TEST=0;
	private long start_time;
	private long end_time;
	private long totalRunTime;
	private double graphRadius;
	private double graphDiameter;
	private String GraphFilePath;
	private String testFilePath;
	private String outPutFilePath;

	public Test(String GraphFilePath, String outputFilePath) {
		this.outPutFilePath = outputFilePath;
		this.GraphFilePath = GraphFilePath;
		runTest0();		
	}
	public Test(String GraphFilePath,String testFilePath, String outputFilePath){
		this.GraphFilePath = GraphFilePath;
		this.testFilePath=testFilePath;
		this.outPutFilePath = outputFilePath;
		runTest1();
	}
	private void runTest0() {
		try {
			File outputFile= new File(outPutFilePath);
			PrintWriter printToOutputFile= new PrintWriter(outputFile);
			start_time = System.currentTimeMillis();
			Graph g = new Graph(GraphFilePath);
			end_time = System.currentTimeMillis();
			totalRunTime = end_time-start_time;
			Graph_algo graphAlgo = new Graph_algo();
			printToOutputFile.print("Graph: |V|="+graphAlgo.numOfVertexes(g)+", |E|="+graphAlgo.numOfEdges(g)+","+graphAlgo.isTie(g)+", Radius:"+graphAlgo.radius(g)+",  Diameter:"+ graphAlgo.diameter(g)+", runtime:"+ totalRunTime+"");
			printToOutputFile.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	private void runTest1() {

		try {
			File outputFile= new File(outPutFilePath);
			PrintWriter printToOutputFile= new PrintWriter(outputFile);
			Graph g = new Graph(GraphFilePath);
			Graph_algo graphAlgo = new Graph_algo();
			File testFile = new File(testFilePath);
			Scanner s = new Scanner(testFile);
			int numberOfQueries =s.nextInt();
			int count=0;
			start_time = System.currentTimeMillis();
			while(++count<=numberOfQueries){
				int startIndex=s.nextInt();
				int endIndex=s.nextInt();
				int numOfBlackVertexes=s.nextInt();
				printToOutputFile.print(startIndex+" "+endIndex+" "+numOfBlackVertexes+" ");
				int[]blackVertexes=new int[numOfBlackVertexes];
				for (int i = 0; i < blackVertexes.length; i++) {
					blackVertexes[i]=s.nextInt();
					printToOutputFile.print(blackVertexes[i]+" ");
				}
				double distance=graphAlgo.blockedDistance(g, startIndex, endIndex, blackVertexes);
				printToOutputFile.print(distance);
				printToOutputFile.println("");
			}
			end_time=System.currentTimeMillis();
			totalRunTime = end_time-start_time;
			printToOutputFile.print("Graph: |V|="+graphAlgo.numOfVertexes(g)+", |E|="+graphAlgo.numOfEdges(g)+", !TIE, Radius:"+graphAlgo.radius(g)+",  Diameter:"+ graphAlgo.diameter(g)+", runtime:"+ totalRunTime+"");
			printToOutputFile.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public static void main(String[] args)  {
		Test t = new Test("C:/Users/malka_000/Desktop/Graph_1000_2594_Test_Radius_Diameter.txt","C:/Users/malka_000/Desktop/output.txt");
		
		/*Graph g=new Graph("C:/Users/malka_000/Desktop/Graph_1000_4487_1480077476577_no_triangle.txt");
		File f= new File("C:/Users/malka_000/Desktop/output0.txt");
		try {
			PrintWriter pw= new PrintWriter(f);
			Vertex [] v= g.getGraph();
			for (int i = 0; i < v.length; i++) {
				for (int j = 0; j < v[i].edges.size(); j++) {
					pw.println(v[i].id+" "+v[i].edges.get(j).getVertex()+" "+v[i].edges.get(j).getWeigth());

				}
			}
			pw.print("radius"+g.getRadius()+"diameter"+g.getDiameter());
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}
}
