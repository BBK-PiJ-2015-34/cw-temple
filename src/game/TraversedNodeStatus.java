package game;

/**
 * Created by davidwright on 27/03/2016.
 */
public class TraversedNodeStatus {

    private Boolean visited;
    private NodeStatus node;

    public TraversedNodeStatus(NodeStatus node, Boolean visited){
        this.node = node;
        this.visited = visited;
    }

    public Boolean getVisited(){
        return visited;
    }

    public NodeStatus getNode() {
        return node;
    }

    public void setVisited(){
        visited = true;
    }
}
