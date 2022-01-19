class Solution {
    
    //Prims algorithm is used
    //www.youtube.com/watch?v=xthRL0lcx2w
    int[][] graph;
    int V;
    public int minCostConnectPoints(int[][] points) {
        V=points.length;
        graph=new int[V][V];
        buildGraph(points);
        int[] val=new int[V];
        boolean[] setMST=new boolean[V];
        Arrays.fill(val,Integer.MAX_VALUE);
        Arrays.fill(setMST,false);
        
        val[0]=0;
        int ans=0;
        //since mst will have V-1 edges
        for(int i=0;i<V-1;i++){
            //greedy approach
            int v=findMinVertex(val,setMST); //it finds the min val node which is not in the minimum spanning tree set
           
            setMST[v]=true;
            
            
            for(int j=0;j<V;j++){//iterating through the neighbouring nodes
                
                //if the node is not in minimum spnning set and if it's distance from an adjacent node is greater than the dist between v and j then we can update it's value
                if(!setMST[j]&&graph[v][j]<val[j]){
                    val[j]=graph[v][j];
                    
                    
                }
            }
        }
        
        
        for(int value:val){
            ans+=value;
        }
        
        return ans;
        
    }
    public void buildGraph(int[][] points){
        for(int i=0;i<points.length;i++){
            for(int j=i+1;j<points.length;j++){
                int x=Math.abs(points[i][0]-points[j][0]);
                int y=Math.abs(points[i][1]-points[j][1]);
                int dist=x+y;
                graph[i][j]=dist;
                graph[j][i]=dist;
                
            }
        }
    }
    public int findMinVertex(int[] val,boolean[] setMST){
        int min=Integer.MAX_VALUE;
        int v=0;
        for(int i=0;i<V;i++){
            if(!setMST[i]&&val[i]<min){
                v=i;
                min=val[i];
            }
        }
        return v;
    }
}
