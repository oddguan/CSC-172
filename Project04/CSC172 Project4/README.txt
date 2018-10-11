Name: Chenxiao Guan
NetID: cguan3
Project partner: Haokun Liu
Assignment: Project 4

In this project, Haokun and I implemented Dijkstra's algorithm to find the shortest path from a given place to another. 

Edge.java class represents edges, we add the edges by reading the roads from the txt files given and create new edges and add the the graph instance.

Node.java class reprensents nodes. Similarly, we add nodes to the graph instance by reading the intersections from the txt files.

Graph.java is the most important class. All information readed from the txt files are transferred into proper instances of edges and nodes and then added to the graph instance. We used several lists and hashmaps to store the instances of edges and nodes, for accessing later. In this class we also wrote several method for convenience.

getEdgeLength method will calculate the distance (in miles) between two intersections, given the intersections' names. For convenience, we assumed that the earth is flat. Then we transferred the difference between latitude and longitude into miles, and used pythagoras theorem to calculate the distance between the two intersections. 

modifyGraph method will modify the graph instances we created, based on the information of the edges and intersection. This will help our implementation of Dijkstra's algorithm to be easier.

dijkstra method is where we calculate the shortest path between two intersections. 

getShortestPath method is just returning a list of the intersections visited in our shortest path. 

The way to run this program is:
1. Compile the class test.java. Run the class in terminal by giving the arguments:
	1. The text file directory
	2. Show
	3. Directions a b

Some example command lines would be:
java ur.txt show directions CSB HOEING (take the ur.txt and print it as a graph, and print out the shortest path between CSB and HOEING in the terminal).
Java nys.txt show (only prints out the map that given by the nys.txt)
Java Monroe.txt directions i1 i5(prints out the shortest path between i1 and i5 in the terminal, but not showing the graph).

If the user asked to show the graph and the directions between two points at the same time, the path will be highlighted as red in the printed-out graph. 

If the user input some nodes that are not in the graph (text file), it will cause the NULLPOINTEREXCEPTION. 

Runtime analysis:
In this project, we tested several cases using all three txt files (ur.txt, monroe.txt, nys.txt). We found that our runtime for ur.txt is really small. Running monroe.txt is also pretty fast, considering its 5M size. However, nys.txt will produce a very long runtime, considering its 34M size. The best case should  be O(1), since we did some optimazations inside our algorithm. We start from visiting the starting node the user gives us, and loop over all nodes. After visiting the destination that the user required, the algorithm will iterating and give the result. The worst case in our algorithm should have O(nlogn), for n intersections. 
