import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Graph {
	Map<String, double[]> Nodemap;
	Map<String, Node> Nodes;
	int nodesize;
	ArrayList<Edge> edges;
	int edgesize;
	public Graph(){
		Nodemap = new HashMap<String, double[]>();
		Nodes = new HashMap<String, Node>();
		edges = new ArrayList<Edge>();
		edgesize = 0;
	}
	public Graph(int N){
		edges = new ArrayList<Edge>();
		edgesize = 0;
	}
	public void addNode(String name, double Latitude, double Longitude){
		Nodemap.put(name, new double[]{Longitude,Latitude});
	}
	public double getEdgeLength(String node1, String node2){
		double result;
		double x1 = Nodemap.get(node1)[0];
		double x2 = Nodemap.get(node2)[0];
		double y1 = Nodemap.get(node1)[1];
		double y2 = Nodemap.get(node2)[1];
		double diffx = (x1-x2)*53.06;
		double diffy = (y1-y2)*68.99;
		result = Math.sqrt(Math.pow(diffx, 2)+Math.pow(diffy, 2));
		return result;
	}
	public void addEdge(String ID, String from, String to){
		edges.add(new Edge(ID,from,to,this.getEdgeLength(from, to)));
		edgesize++;
	}
	public void modifyGraph(){
		Edge[] edges = new Edge[this.edges.size()];
		for(int temp=0;temp<edges.length;temp++){
			edges[temp] = this.edges.get(temp);
		}
		
		for(String name:Nodemap.keySet()){
			Nodes.put(name, new Node());
		}
		this.edgesize = edges.length;
		for(int j=0;j<edgesize;j++){
			Nodes.get(edges[j].getFrom()).getEdges().add(edges[j]);
			Nodes.get(edges[j].getTo()).getEdges().add(edges[j]);
		}
	}
	//Shortest distance from a given node to all other nodes
	public void dijkstra(String start, String end){//Starting with any node
		String next = start;
		Nodes.get(next).setDistance(0);
		for(String name:Nodes.keySet()){//Iterate over all nodes
			List<Edge> temp = new ArrayList<Edge>();
			if(Nodes.get(end).isVisited()){
				break;
				//If we already visited end, no need to continue.
			}
			if(next.equals("")){
				break;
			}
			temp = Nodes.get(next).getEdges();
			for(int j=0;j<temp.size();j++){//Iterate over all edges of this node
				String neighbor = temp.get(j).getNeighbor(next);
				if(!Nodes.get(neighbor).isVisited()){//Not yet visited
					double dist = Nodes.get(next).getDistance()+temp.get(j).getLength();
					if(dist<Nodes.get(neighbor).getDistance()){
						Nodes.get(neighbor).setDistance(dist);
						Nodes.get(neighbor).setPrev(next);
					}
				}
			}
			Nodes.get(next).setVisited(true);
			String storage = this.getPath();
			next = storage;
		}
	}
	public String getPath(){
		String storage="";
		double storage2 = Integer.MAX_VALUE;
		for(String key:Nodes.keySet()){
			double temp = Nodes.get(key).getDistance();
			if(!(Nodes.get(key).isVisited())&&(temp < storage2)){
				storage2 = temp;
				storage = key;
			}
		}
		return storage;
	}
	public List<String> getShortestPath(String a, String b){
		List<String> path = new ArrayList<String>();
		this.dijkstra(a,b);
		if(a.equals(b)){
			path.add(a);
			return path;
		}
		else if(Nodes.get(b).getDistance()==Integer.MAX_VALUE){
			return path;
		}
		else{
			Stack<String> stack = new Stack<String>();
			String temp;
			temp = b;
			stack.push(b);
			Nodes.get(b).setHighlighted(true);
			while(!(Nodes.get(temp).getPrev().equals(a))){
				temp = Nodes.get(temp).getPrev();
				Nodes.get(temp).setHighlighted(true);
				stack.push(temp);
			}
			stack.push(a);
			Nodes.get(a).setHighlighted(true);
			while(!stack.isEmpty()){
				path.add(stack.pop());
			}
			return path;
		}
	}
}
