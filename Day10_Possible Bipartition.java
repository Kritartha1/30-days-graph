class Solution {
    ArrayList<ArrayList<Integer>> graph;
    
    public void BuildGraph(int[][] edge,int n){
        
        graph=new ArrayList<>();
        
        for(int i=0;i<=n;i++){
            
            graph.add(new ArrayList<>());
            
        }
        
        for(int i=0;i<edge.length;i++){
            
            graph.get(edge[i][0]).add(edge[i][1]);
            graph.get(edge[i][1]).add(edge[i][0]);
            
        }
        
    }
    public boolean possibleBipartition(int n, int[][] dislikes) {
        
        BuildGraph(dislikes,n);
        
        int[] color=new int[n+1];
        
        Arrays.fill(color,-1);
        
        for(int i=1;i<=n;i++){
            
            if(color[i]==-1){
                
                if(!isBipartite(i,color)){
                    
                    return false;
                    
                }
            }
        }
        return true;
    }
    
    
    //BFS
    public boolean isBipartite(int i,int[] color){
        
        color[i]=1;
        
        Queue<Integer> q=new LinkedList<>();
        
        q.add(i);
        
        while(!q.isEmpty()){
            
            int curr=q.poll();
           
            for(int neighbours:graph.get(curr)){
                
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
