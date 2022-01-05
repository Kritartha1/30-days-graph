/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    //we are doing simple dfs
    
    public Node[] vis=new Node[101];
    public Node cloneGraph(Node node) {
        if(node==null){
            return null;
        }
       
        Arrays.fill(vis,null);
        Node newNode=new Node(node.val,new ArrayList<Node>());
        dfs(newNode,node);
        return newNode;
        
        
    }
    public void dfs(Node newNode,Node node){
        vis[newNode.val]=newNode;
        for(Node neighbour:node.neighbors){
            if(vis[neighbour.val]==null){//means the node is not visited//
              
                Node neighbourNode=new Node(neighbour.val,new ArrayList<Node>());
              //first we add it as a neighbour to the current node
                newNode.neighbors.add(neighbourNode);
              //then doing dfs for this node....
                dfs(neighbourNode,neighbour);
            }else{
              //if neighbour node is already visited, then no need to do dfs for this node..
              //bcoz it's dfs is done..that's why it vis[neighbour node]!=null
              //so we are just adding the node as the newNode's member. 
                newNode.neighbors.add(vis[neighbour.val]);
            }
        }
    }
    
}
