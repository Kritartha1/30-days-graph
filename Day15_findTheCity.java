class Solution {
  //Find the City With the Smallest Number of Neighbors at a Threshold Distance
  //https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/
  //https://www.youtube.com/watch?v=nV_wOZnhbog
    int[][] graph;
    int INT_MAX;
    //FloydWarshall
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        INT_MAX=distanceThreshold+1;
        graph=new int[n][n];
        buildGraph(n,edges);
        floydWarshall(n);
        int ans=(int)1e7;
        int city=0;
        for(int i=0;i<n;i++){
            int temp=0;
            for(int j=0;j<n;j++){
                
                if(i!=j&&graph[i][j]!=INT_MAX){
                    temp++;
                }
                
            }
            if(temp<ans){
                ans=temp;
                city=i;
            }
            else if(temp==ans){
                city=Math.max(i,city);
            }
            
        }
        return city;
        
    }
    
    public void buildGraph(int V,int[][] edges){
        for(int i=0;i<V;i++){
            for(int j=i+1;j<V;j++){
                graph[i][j]=INT_MAX;
                graph[j][i]=INT_MAX;
                
            }
        }
        for(int[] edge:edges){
            int from=edge[0];
            int to=edge[1];
            int wt=edge[2];
            graph[from][to]=wt;
            graph[to][from]=wt;
            
            
        }
        
    }
    public void floydWarshall(int V){
        for(int k=0;k<V;k++){
            for(int i=0;i<V;i++){
                for(int j=0;j<V;j++){
                    //Math.min(graph[i][k]+graph[k][j],INT_MAX)--> means if any edge weight is greater than treshold , then we are saying it as infinity..means unreachable...
                    //so in the end when we traverses for each nodes.if a edge wt is infinity , then we can say that it's weight> treshold..so don't count that edge
                    graph[i][j]=Math.min(graph[i][j],Math.min(graph[i][k]+graph[k][j],INT_MAX));
                }
            }
        }
    }
}
