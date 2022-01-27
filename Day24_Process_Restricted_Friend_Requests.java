class Solution {
  //leetcode.com/problems/process-restricted-friend-requests/submissions/
    //Union find
     class Node{
        int parent;
        int rank;
         
        Node(int parent,int rank){
            this.parent=parent;
            this.rank=rank;
        }
     } 
    
    Node[] parents;
    
    public int find(int V){
        if(parents[V].parent==-1){
            return V;
        }
        return parents[V].parent=find(parents[V].parent);
        
        
    }
    
    public void union(int from,int to){
        if(parents[from].rank>parents[to].rank){
            parents[to].parent=from;
        }else if(parents[from].rank<parents[to].rank){
            parents[from].parent=to;
        }else{
            parents[from].parent=to;
            parents[to].rank+=1;
        }
    }
    
    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        
        parents=new Node[n];
        
        boolean ans[]=new boolean[requests.length];
        
        int i=0;
        
        for(;i<n;i++){
            parents[i]=new Node(-1,0);
        }
        
        i=0;
        
        for(int[] request:requests){
            
            int person1=find(request[0]);
            int person2=find(request[1]);
            
            if(person1==person2){//if they are already indirect or direct friends,then then request is succesful bcoz they are already friends
                ans[i++]=true;
                continue;
            }
            
            boolean dushman=false;
            
            for(int[] rstrcn:restrictions){
                
                int x=find(rstrcn[0]);
                int y=find(rstrcn[1]);
                
                //if they have restrictions directly or indirectly,then they can't be friends...
                if((x==person1&&y==person2)||(x==person2&&y==person1)){
                    dushman=true;
                    break;
                }
                
            }
            if(dushman){
                ++i;
                continue;
            }
            
            //if no restrictions and a fresh connection, do a union operation between them and return a true as they are now friends.....
            ans[i++]=true;//! dushman means they are friends..LOL
            union(person1,person2);
            
        }
        return ans;
        
    }
}
