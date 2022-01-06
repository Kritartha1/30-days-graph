class Solution {
    public Node[] nodes;
    class Node{
        int parent;
        int rank;
        Node(int parent,int rank){
            this.parent=parent;
            this.rank=rank;
        }
        
        
    }
    public int makeConnected(int n, int[][] connections) {
        int m=connections.length;
        //to connect all the connected we need atleast (n-1) edges..
        //if we have more than (n-1) edges then we can disconnect some and connect to the disconnected ones to make the full graph connected..
        //if less than (n-1) nodes..then not possible to connect all the nodes..
        if(m<(n-1)){
            return -1;
        }
        
        int components=n;
        
        nodes=new Node[n];//it contains parent and rank(rank of the absolute parent element)
        for(int i=0;i<n;i++){
            nodes[i]=new Node(-1,0);
           
        }
        int ans=Components(connections,components,n);
        //eg: if 1->2    3-.4    ,then no of connected components=2
        //eg: 1->2->3->4  ,then connected components=1
        //so...2 connected components means need 1 edge to connect both the components.
        // ..for 2nd eg: 1 connected components means nedded 0 edge to connect both components..
        //so total edges needed=(connected-1);
        return (ans-1);
    }
    //
    public int Components(int[][] connections,int components,int n){
        //this method will return the number of connected components..
        //eg: if 1->2    3-.4    ,then no of connected components=2
        //eg: 1->2->3->4  ,then connected components=1
        for(int[] edge:connections){
            
            int from=find(edge[0]);
            int to=find(edge[1]);
            if(from!=to){//if parent not same then there is a missing edge between the nodes..
                union(from,to);//connecting the nodes//i.e with the absolute parent ones
                //after connecting the nodes..two connected components become a single component..
                //so components decrease by 1
                components--;
               
            }
        }
        return components;
    }
    public int find(int v){//compresses find method
        if(nodes[v].parent==-1){
            return v;
        }return nodes[v].parent=find(nodes[v].parent);
    }
    
    public void union(int from,int to){//connecting edges
        if(nodes[from].rank>nodes[to].rank){
            nodes[to].parent=from;
        }else if(nodes[from].rank<nodes[to].rank){
            nodes[from].parent=to;
        }else{
            nodes[from].parent=to;
            nodes[to].rank+=1;
        }
    }
}
