class Solution {
    
    class Node{
        int node;
        int val;
        Node(int node,int val){
            this.node=node;
            this.val=val;
        }
    }
    
    public int minCostConnectPoints(int[][] points) {
        //prims algorithm
        
        int V=points.length;
        
        int distance[]=new int[V];
        
        boolean vis[]=new boolean[V];
        
        Arrays.fill(vis,false);
        
        int ans=0;
        
       PriorityQueue<Node> pq=new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.val-o2.val;
            }
        });
        distance[0]=0;
        
        Arrays.fill(distance,Integer.MAX_VALUE);
        
        pq.add(new Node(0,0));
        
        while(!pq.isEmpty()){
            
            Node curr=pq.poll();
            int currNode=curr.node;//curr Node index or current node
            int currVal=curr.val;//currNode distance
            
            vis[curr.node]=true;
            
            if(distance[currNode]<=currVal){//if best distance node was already added, then no need -
                //-to check for this node..
                
                continue;
            }
            
            distance[currNode]=currVal;
            
            ans+=currVal;
            
             
            for(int i=0;i<V;i++){
                
                if(i!=currNode&&!vis[i]){
                    
                    int distX=Math.abs(points[currNode][0]-points[i][0]);
                    int distY=Math.abs(points[currNode][1]-points[i][1]);
                    
                    int dist=distX+distY;
                    
                    if(dist<distance[i]){
                        
                         pq.add(new Node(i,dist));
                         
                    }
                    
                    
                }
            }
        }
       
        return ans;
        
    }
}
