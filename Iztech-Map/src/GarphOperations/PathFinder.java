package GarphOperations;

public class PathFinder { 
	
	double[][] graph;
	
	public PathFinder(double[][] graph) {
		this.graph = graph;
	}
	
    // A utility function to find the vertex with minimum distance value, 
    // from the set of vertices not yet included in shortest path tree 
    double minDistance(double dist[], Boolean sptSet[]) 
    { 
        // Initialize min value 
        double min = Integer.MAX_VALUE, min_index=-1; 
  
        for (int v = 0; v < graph.length; v++) 
            if (sptSet[v] == false && dist[v] <= min) 
            { 
                min = dist[v]; 
                min_index = v; 
            } 
  
        return min_index; 
    } 
  
    // A utility function to print the constructed distance array 
    void printSolution(double dist[]) 
    { 
        System.out.println("Vertex   Distance from Source"); 
        for (int i = 0; i < graph.length; i++) 
            System.out.println(i+" tt "+dist[i]); 
    } 
  
    // Funtion that implements Dijkstra's single source shortest path 
    // algorithm for a graph represented using adjacency matrix 
    // representation 
    public double[] shortestPathsFromAGivenNode(int src) 
    { 
    	double dist[] = new double[graph.length]; // The output array. dist[i] will hold 
                                 // the shortest distance from src to i 
  
        // sptSet[i] will true if vertex i is included in shortest 
        // path tree or shortest distance from src to i is finalized 
        Boolean sptSet[] = new Boolean[graph.length]; 
  
        // Initialize all distances as INFINITE and stpSet[] as false 
        for (int i = 0; i < graph.length; i++) 
        { 
            dist[i] = Integer.MAX_VALUE; 
            sptSet[i] = false; 
        } 
  
        // Distance of source vertex from itself is always 0 
        dist[src] = 0; 
  
        // Find shortest path for all vertices 
        for (int count = 0; count < graph.length-1; count++) 
        { 
            // Pick the minimum distance vertex from the set of vertices 
            // not yet processed. u is always equal to src in first 
            // iteration. 
        	double u = minDistance(dist, sptSet); 
  
            // Mark the picked vertex as processed 
            sptSet[(int) u] = true; 
  
            // Update dist value of the adjacent vertices of the 
            // picked vertex. 
            for (int v = 0; v < graph.length; v++) 
  
                // Update dist[v] only if is not in sptSet, there is an 
                // edge from u to v, and total weight of path from src to 
                // v through u is smaller than current value of dist[v] 
                if (!sptSet[v] && graph[(int) u][v]!=0 && 
                        dist[(int) u] != Integer.MAX_VALUE && 
                        dist[(int) u]+graph[(int) u][v] < dist[v]) 
                    dist[v] = dist[(int) u] + graph[(int) u][v]; 
        } 
        return dist;
    }
    
    public double shortestDistBetweenTwoSources(int source1,int source2) {
    	double shortestDist = 0.0;
    	double[] shortestPathsFromsource1 = shortestPathsFromAGivenNode(source1);
    	shortestDist = shortestPathsFromsource1[source2];
    	return shortestDist;
    }
    
    
    
   
}
