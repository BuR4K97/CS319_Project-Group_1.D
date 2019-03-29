package ModelClasses;
import java.util.ArrayList;

public class TerritoryGraph {
	
	private static class GraphNode {
		private Territory territory;
		private ArrayList<GraphNode> connectedTerritories;
		
		private boolean connectTerritory(GraphNode connect) {
			if(this == connect) return false;
			for(GraphNode currNode : connectedTerritories)
				if(currNode == connect) return false;
			connectedTerritories.add(connect);
			return true;
		}
		
		private boolean disconnectTerritory(GraphNode connect) {
			for(int i = 0; i < connectedTerritories.size(); i++)
				if(connectedTerritories.get(i) == connect) {
					connectedTerritories.remove(i);
					return true;
				}
			return false;
		}
		
		private boolean checkConnect(Territory check) {
			for(GraphNode currNode : connectedTerritories)
				if(currNode.territory == check) return true;
			return false;
		}
		
	}//endInnerClass

	private ArrayList<GraphNode> territories = new ArrayList<GraphNode>();

	public boolean addTerritory(Territory territory) {
		GraphNode graphNode = findGraphNode(territory);
		if(graphNode == null) {
			graphNode = new GraphNode();
			graphNode.territory = territory;
			territory.connectToGraph(this);
			territories.add(graphNode);
			return true;
		}
		return false;
	}
	
	public boolean removeTerritory(Territory territory) {
		GraphNode graphNode = findGraphNode(territory);
		if(graphNode != null) {
			for(GraphNode currNode : graphNode.connectedTerritories)
				currNode.disconnectTerritory(graphNode);
			territories.remove(graphNode);
			return true;
		}
		return false;
	}
	
	public boolean connectTerritory(Territory sourceTerritory, Territory targetTerritory) {
		GraphNode sourceGraphNode = findGraphNode(sourceTerritory), targetGraphNode;
		if(sourceGraphNode != null) {
			targetGraphNode = findGraphNode(sourceTerritory);
			if(targetGraphNode != null) {
				sourceGraphNode.connectTerritory(targetGraphNode);
				return targetGraphNode.connectTerritory(sourceGraphNode);
			}
		}
		return false;
	}
	
	public boolean checkConnect(Territory sourceTerritory, Territory targetTerritory) {
		GraphNode sourceGraphNode = findGraphNode(sourceTerritory);
		if(sourceGraphNode != null)
			return sourceGraphNode.checkConnect(targetTerritory); 
		return false;
	}
	
	public ArrayList<Territory> getTerritories() {
		ArrayList<Territory> result = new ArrayList<Territory>();
		for(GraphNode currNode : territories) result.add(currNode.territory);
		return result;
	}
	
	private GraphNode findGraphNode(Territory territory) {
		for(GraphNode currNode : territories)
			if(currNode.territory == territory) return currNode;
		return null;
	}
	
}//endClass
