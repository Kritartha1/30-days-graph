class Solution {
   
    //leetcode.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/
    public int find(int v,int[] parent){
        if(parent[v]==-1){
            return v;
        }
        return parent[v]=find(parent[v],parent);
    }
    
    public int components(int[] parent){
        int components=0;
         for(int i=1;i<parent.length;i++){
            
            if(parent[i]==-1){
                ++components;
            }
            
        }
        return components;
    }
    
    
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        
        int[] parent=new int[n+1];
        int[] parent_Alice=new int[n+1];
        int[] parent_Bob=new int[n+1];
   
        Arrays.fill(parent,-1);
        
        int counter=0;
        
        int parentOfX;
        int parentOfY;
        //////////////////////////////////////////////////////
        for(int[] edge:edges){
            
            if(edge[0]!=3){
                continue;
            }
            
            parentOfX=find(edge[1],parent);
            parentOfY=find(edge[2],parent);
            
           if(parentOfX==parentOfY){
               
                counter++;
                continue;
            }
            parent[parentOfX]=parentOfY;
        }
        
        
        
       
        
        //////////////////////////////////////////////////////
        //copying the value of parent(type 3 edges) to the 
        for(int i=1;i<=n;i++){
            parent_Alice[i]=parent[i];
            parent_Bob[i]=parent[i];
        }
        //////////////////////////////////////////////////////
        
        for(int[] edge:edges){
            if(edge[0]==3){
                continue;
            }
        
           
            parentOfX=(edge[0]==1)?find(edge[1],parent_Alice):find(edge[1],parent_Bob);
            parentOfY=(edge[0]==1)?find(edge[2],parent_Alice):find(edge[2],parent_Bob);
            
            
            
            if(parentOfX==parentOfY){
                
                //if the nodes already have an undirected or directed edge,then there is no point of connecting the edge..
                
                counter++;
                continue;
            }
            
            if(edge[0]==1){
                 parent_Alice[parentOfX]=parentOfY;
                
            }else{//edge[0]==2
                 parent_Bob[parentOfX]=parentOfY;
            }
        }
        
        //finding number of comopnents..
        int components_Alice=components(parent_Alice);
        int components_Bob=components(parent_Bob);

        
        
        //if any of the two graph has more than one components..then we can't navigate to all the nodes..
        return components_Alice>1||components_Bob>1?-1:counter;
        
        
    }
}
