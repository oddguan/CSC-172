
public class Edge {
	private String ID;
	private String from;
	private String to;
	private double length;
	public Edge(String ID, String from, String to, double length){
		this.ID = ID;
		this.from = from;
		this.to = to;
		this.length = length;
	}
	public String getNeighbor(String name){
		if(this.from.equals(name)){
			return this.to;
		}
		else{
			return this.from;
		}
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	
}
