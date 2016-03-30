package student;

import game.EscapeState;
import game.ExplorationState;
import game.NodeStatus;

import java.util.*;

public class Explorer {

    /**
     * Explore the cavern, trying to find the orb in as few steps as possible.
     * Once you find the orb, you must return from the function in order to pick
     * it up. If you continue to move after finding the orb rather
     * than returning, it will not count.
     * If you return from this function while not standing on top of the orb,
     * it will count as a failure.
     * <p>
     * There is no limit to how many steps you can take, but you will receive
     * a score bonus multiplier for finding the orb in fewer steps.
     * <p>
     * At every step, you only know your current tile's ID and the ID of all
     * open neighbor tiles, as well as the distance to the orb at each of these tiles
     * (ignoring walls and obstacles).
     * <p>
     * To get information about the current state, use functions
     * getCurrentLocation(),
     * getNeighbours(), and
     * getDistanceToTarget()
     * in ExplorationState.
     * You know you are standing on the orb when getDistanceToTarget() is 0.
     * <p>
     * Use function moveTo(long id) in ExplorationState to move to a neighboring
     * tile by its ID. Doing this will change state to reflect your new position.
     * <p>
     * A suggested first implementation that will always find the orb, but likely won't
     * receive a large bonus multiplier, is a depth-first search.
     *
     * @param state the information available at the current state
     */

    Stack<Long> breadcrumbs;
    Set<Long> visitedIds;
    Boolean foundOrb = false;

    public void explore(ExplorationState state) {
        //TODO:
        visitedIds = new HashSet<>();
        do {
            traverseNodes(state.getNeighbours(), state.getCurrentLocation(), state);


        } while (state.getDistanceToTarget() !=0);

        System.out.println(state.getCurrentLocation());



        System.out.println(state.getCurrentLocation());
        //ns.forEach(ne -> System.out.println(ne.getId()));

    }

    private void traverseNodes(Collection<NodeStatus> nodes, long locationId, ExplorationState state){

        long location = state.getCurrentLocation();

        if(nodes.size() == 1){

            for(NodeStatus ne : nodes){
                long neighbour = ne.getId();
                if(visitedIds.contains(neighbour)){
                    visitedIds.add(neighbour);
                    return;
                }
            }

        }
        Boolean visitedAll = true;
        for(NodeStatus ne : nodes){
            if(visitedIds.contains(ne.getId()) == false){
                visitedAll = false;
            }
        }
        if (visitedAll == true){
            return;
        }

        List<NodeStatus> ns = new ArrayList<NodeStatus>(nodes);
        Collections.sort(ns, new NeighbourSort());

        for(NodeStatus ne : ns){
            long id = ne.getId();
            if(visitedIds.contains(id) == false) {
                if (!foundOrb){
                    state.moveTo(id);
                }

                if(state.getDistanceToTarget() == 0){
                    foundOrb = true;
                }

                visitedIds.add(id);

                Collection<NodeStatus> nn = state.getNeighbours();

                traverseNodes(nn, state.getCurrentLocation(), state);
                if (!foundOrb) {
                    state.moveTo(locationId);
                }
            }
        }

    }

    /**
     * Escape from the cavern before the ceiling collapses, trying to collect as much
     * gold as possible along the way. Your solution must ALWAYS escape before time runs
     * out, and this should be prioritized above collecting gold.
     * <p>
     * You now have access to the entire underlying graph, which can be accessed through EscapeState.
     * getCurrentNode() and getExit() will return you Node objects of interest, and getVertices()
     * will return a collection of all nodes on the graph.
     * <p>
     * Note that time is measured entirely in the number of steps taken, and for each step
     * the time remaining is decremented by the weight of the edge taken. You can use
     * getTimeRemaining() to get the time still remaining, pickUpGold() to pick up any gold
     * on your current tile (this will fail if no such gold exists), and moveTo() to move
     * to a destination node adjacent to your current node.
     * <p>
     * You must return from this function while standing at the exit. Failing to do so before time
     * runs out or returning from the wrong location will be considered a failed run.
     * <p>
     * You will always have enough time to escape using the shortest path from the starting
     * position to the exit, although this will not collect much gold.
     *
     * @param state the information available at the current state
     */
    public void escape(EscapeState state) {
        //TODO: Escape from the cavern before time runs out
    }

    static class NeighbourSort implements Comparator<NodeStatus>{
        public int compare(NodeStatus o1, NodeStatus o2) {
                return o1.compareTo(o2);
        }
    }
}