class Solution {
    //leetcode.com/problems/find-eventual-safe-states/submissions/
    //Little modification of Kahn's
    public List<Integer> eventualSafeNodes(int[][] graph) {
        
        int n=graph.length;
        
        int[] outdeg=new int[n];
        
        Queue<Integer> q=new LinkedList<>();
        
        HashMap<Integer,ArrayList<Integer>> mp=new HashMap<>();
        //mp will contain node ,list pair..
        //for evry node it will contain the incoming nodes to it...
        
        for(int i=0;i<n;i++){
            
            
            
            for(int node:graph[i]){
                
                ArrayList<Integer> temp=mp.getOrDefault(node,new ArrayList<Integer>());
                temp.add(i);
                mp.put(node,temp);
                ++outdeg[i];
            }
            
            if(outdeg[i]==0){
                q.add(i);
            }
            
        }
        
        
        List<Integer> ans=new ArrayList<>();
       
        
        while(!q.isEmpty()){
            
            int safe=q.poll();
            ans.add(safe);
            
            for(int to:mp.getOrDefault(safe,new ArrayList<>())){
                --outdeg[to];
                if(outdeg[to]==0){
                    q.add(to);
                }
            }
        }
        
        
        Collections.sort(ans);
        
        return ans;
        
        
        
        
        
        
    }
}
