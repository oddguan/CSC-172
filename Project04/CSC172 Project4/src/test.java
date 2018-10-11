import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.List;

public class test extends JComponent{
	Graph graph = new Graph();
	double minx=200;
    double maxy=0;
    double maxx=-200;
    double miny=100;
	public test(String file){
		try{
			BufferedReader br = new BufferedReader(new FileReader(new File(file)));
			String s;
			while((s=br.readLine())!=null){
				StringTokenizer st = new StringTokenizer(s);
				List<String> splited = new ArrayList<String>();
				while(st.hasMoreTokens()){
                    splited.add(st.nextToken());
                }
				if(splited.get(0).equals("i")){
					graph.addNode(splited.get(1), Double.parseDouble(splited.get(2)), Double.parseDouble(splited.get(3)));
					if(minx>Double.parseDouble(splited.get(3))){
	                	minx = Double.parseDouble(splited.get(3));
	                }
					if(maxx<Double.parseDouble(splited.get(3))){
						maxx = Double.parseDouble(splited.get(3));
					}
	                if(maxy<Double.parseDouble(splited.get(2))){
	                	maxy = Double.parseDouble(splited.get(2));
	                }
	                if(miny>Double.parseDouble(splited.get(2))){
	                	miny = Double.parseDouble(splited.get(2));
	                }
				}
				else if(splited.get(0).equals("r")){
					graph.addEdge(splited.get(1), splited.get(2), splited.get(3));
				}
				
			}
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	@Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        
        for(Edge e:graph.edges){
        	if((graph.Nodes.get(e.getFrom()).isHighlighted())&&(graph.Nodes.get(e.getTo()).isHighlighted())){
        		g2.setColor(Color.RED);
        		g2.setStroke(new BasicStroke(2.5f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f));
        	}
        	else{
        		g2.setColor(Color.BLACK);
        		g2.setStroke(new BasicStroke(1.2f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f));
        	}
        	double x1 = (720/(maxx-minx))*((graph.Nodemap.get(e.getFrom())[0])-minx)+100;
        	double x2 = (720/(maxx-minx))*((graph.Nodemap.get(e.getTo())[0])-minx)+100;
        	double y1 = (-720/(maxy-miny))*((graph.Nodemap.get(e.getFrom())[1])-maxy)+100;
        	double y2 = (-720/(maxy-miny))*((graph.Nodemap.get(e.getTo())[1])-maxy)+100;
        	Line2D line = new Line2D.Double(x1,y1,x2,y2);
        	g2.draw(line);
        	//System.out.println(x1);
        	//System.out.println(y1);
        }
        /*try{
            BufferedReader br = new BufferedReader(new FileReader(new File("ur.txt")));
            String s;
            while((s=br.readLine())!=null){
                List<String> splited = new ArrayList<String>();
                StringTokenizer st = new StringTokenizer(s);
                while(st.hasMoreTokens()){
                    splited.add(st.nextToken());
                }
                double minx=200;
                double maxy=0;
                if(minx>Double.parseDouble(splited.get(3))){
                	minx = Double.parseDouble(splited.get(3));
                }
                if(maxy>Double.parseDouble(splited.get(2))){
                	maxy = Double.parseDouble(splited.get(2));
                }
                double y1 = -100000*Double.parseDouble(splited.get(2))+4313200;
                double x1 = +100000*Double.parseDouble(splited.get(3))+7763300;
                System.out.println(x1);
                System.out.println(y1);
                Line2D line2 = new Line2D.Double(x1,y1,x1+10,y1+10);
                g2.draw(line2);
                double x = 480*(180-Double.parseDouble(splited.get(2)))/360;
                double y = 480*(90+Double.parseDouble(splited.get(3)))/180;

                Line2D line = new Line2D.Double(x,y,x+5,y+5);
                g2.draw(line);
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }*/
    }

    public Dimension getPreferredSize(){
        return new Dimension(1080,1080);
    }
    public static void main(String[] args) {
    	String fileName = args[0];
    	test test1 = new test(fileName);
    	test1.graph.modifyGraph();
        if(Arrays.asList(args).contains("show")){
            if(Arrays.asList(args).contains("directions")){
                String from = args[3];
                String to = args[4];
                List<String> temp = test1.graph.getShortestPath(from, to);
                if(temp.size()==0){
                	System.out.println("No path between "+from+" and "+to+ ".");
                }
                else{
                	System.out.println("Shortest path: "+temp);
                    System.out.println("Total distance: "+ test1.graph.Nodes.get(to).getDistance()+" miles.");
                }
            }
            JFrame frame = new JFrame("test");
            frame.add(test1);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
        else if(Arrays.asList(args).contains("directions")){
            String from = args[2];
            String to = args[3];
            List<String> temp = test1.graph.getShortestPath(from, to);
            if(temp.size()==0){
            	System.out.println("No path between "+from+" and "+to+ ".");
            }
            else{
            	System.out.println("Shortest path: "+temp);
                System.out.println("Total distance: "+ test1.graph.Nodes.get(to).getDistance()+" miles.");
            }
        }
    	/*test test1 = new test("ur.txt");
    	test1.graph.modifyGraph();
    	System.out.println(test1.graph.getShortestPath("CSB", "GOERGEN-ATHLETIC"));
    	System.out.printf("%.4f",test1.graph.Nodes.get("GOERGEN-ATHLETIC").getDistance());
        JFrame frame = new JFrame("test");
        frame.add(test1);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        */
    }

}
