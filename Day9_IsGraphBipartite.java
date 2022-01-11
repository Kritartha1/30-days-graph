class Solution {
    public boolean isBipartite(int[][] graph) {
        
        //Used concept of bipartition and graoh coloring..
        //if a graph is not bipartite , the there must be a odd length cycle ..
        //when graph coloring is done..
        //i.e if two colors are used 0 and 1..
        //if root element color is 1..
        //neighbour elements are coloured as 0's
        //so when graph is fully coloured..if we find any two adjacent nodes of same color, then the graph  must have odd cycle length ..means not bipartite
        int n=graph.length;
        int[] color=new int[n];
        Arrays.fill(color,-1);
        for(int i=0;i<n;i++){
            if(color[i]==-1){
                if(!isBipartite(graph,color,i)){
                    return false;
                }
            }
        }
        return true;
    }
    
    //Simple bfs done
    public boolean isBipartite(int[][] graph,int[] color,int i){
        Queue<Integer> q =new LinkedList<>();
        q.add(i);
        color[i]=1;
        while(!q.isEmpty()){
            int curr=q.poll();
            for(int neighbours:graph[curr]){
                if(color[neighbours]==color[curr]){
                    return false;
                }else if(color[neighbours]==-1){
                    color[neighbours]=1-color[curr];
                    q.add(neighbours);
                }
            }
        }
        return true;
    }
}
