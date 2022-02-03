class Solution {
    //leetcode.com/problems/find-eventual-safe-states/submissions/
    //dfs
    //O(N+E): time.  O(N) space
    //graph coloring :
    //0: unvisited node.
    //1: processed node.
    //2: processing node.
    
    public List<Integer> eventualSafeNodes(int[][] graph) {
        
        int n=graph.length;
        
        int[] color=new int[n];
        List<Integer> ans=new ArrayList<>();
        for(int i=0;i<n;i++){
            if(dfs(graph,color,i)){
                ans.add(i);
            }
        }
        return ans;
       
    }
    
    public boolean dfs(int[][] graph,int[] color,int from){
        if(color[from]>0){
            return color[from]==1;
        }
        color[from]=2;
        
        for(int to:graph[from]){
            if(!dfs(graph,color,to)){
                return false;
            }
            //if dfs to all the neghbouring nodes is not succesful..
            //means we have a neighbouring node(direct or indirect) whuch forms a cycle..
            //so if a cycle is found..then it can't be a safe node ..since the cyclic ode won't be a terminal.
        }
        
        //if dfs to all the neighbours are successfull..
        //means no cycle node found on any neoghbour..
        //means we can always get to a terminal node from the current node..
        //do its a safe node
        
        color[from]=1;
        return true;
        
        
        
    }
}
