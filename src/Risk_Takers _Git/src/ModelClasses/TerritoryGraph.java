package ModelClasses;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

import Controller.GameController;

public class TerritoryGraph implements Serializable {

	private static class GraphNode implements Serializable {
		private Territory territory;
		private ArrayList<GraphNode> connectedTerritories = new ArrayList<GraphNode>();

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
				if(currNode.territory.equals(check)) return true;
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
			targetGraphNode = findGraphNode(targetTerritory);
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

	public boolean checkOverConnect(Territory sourceTerritory, Territory targetTerritory) {
		ArrayList<GraphNode> extendedList = new ArrayList<GraphNode>();
		GraphNode sourceGraphNode = findGraphNode(sourceTerritory);
		if(sourceGraphNode == null) return false;
		GraphNode targetGraphNode = findGraphNode(targetTerritory);
		if(targetGraphNode == null) return false;

		Player focusPlayer = sourceTerritory.getPlayer();
		if(focusPlayer != targetTerritory.getPlayer()) return false;

		ArrayList<GraphNode> searchList = new ArrayList<GraphNode>(); searchList.add(sourceGraphNode); 
		GraphNode currNode;
		while(!searchList.isEmpty()) {
			currNode = searchList.remove(0);
			extendedList.add(currNode);
			for(GraphNode check : currNode.connectedTerritories) {
				if(check.territory.playerCaptured != focusPlayer) continue;
				if(extendedList.contains(check)) continue;
				if(check == targetGraphNode) return true;
				searchList.add(check);
			}
		}
		return false;
	}

	public double extractDistributionalScore(ArrayList<Territory> list) {
		double distributionalScore = 0; 
		GraphNode execute; ArrayList<GraphNode> executeList;
		ArrayList<ArrayList<GraphNode>> searchList = new ArrayList<ArrayList<GraphNode>>();
		ArrayList<GraphNode> extendedList = new ArrayList<GraphNode>(); ArrayList<GraphNode> currList;
		int searchNumber;
		for(Territory terr : list) {
			searchNumber = 0;
			execute = findGraphNode(terr);
			executeList = new ArrayList<GraphNode>(); executeList.add(execute);
			searchList.add(executeList);
			search:while(!searchList.isEmpty()) {
				currList = searchList.remove(0);
				extendedList.add(currList.get(currList.size() - 1));
				for(GraphNode connect : currList.get(currList.size() - 1).connectedTerritories) {
					if(extendedList.contains(connect)) continue;
					ArrayList<GraphNode> insert = new ArrayList<GraphNode>(currList);
					insert.add(connect); boolean ins = true;
					for(ArrayList<GraphNode> check : searchList) {
						if(check.get(check.size() - 1) == connect) {
							int checkScore = 0, insertScore = 0;
							for(GraphNode path : check) if(path.territory.getPlayer() != terr.getPlayer()) checkScore += 1;
							for(GraphNode path : insert) if(path.territory.getPlayer() != terr.getPlayer()) insertScore += 1;
							if(!(insertScore < checkScore))	ins = false;
							else searchList.remove(check);
							break;
						}
					}
					if(ins) searchList.add(insert);
					if(list.contains((insert.get(insert.size() - 1).territory))) {
						int insertScore = 0;
						for(GraphNode path : insert) if(path.territory.getPlayer() != terr.getPlayer()) insertScore += 1;
						distributionalScore += terr.getUnitNumber() * Math.pow(insertScore, 2); 
						searchNumber++;
						if(searchNumber == list.size() - 1) break search;
					}
				}
			}
			extendedList.clear();
			searchList.clear();
		}
		return Math.log1p(distributionalScore);

		/**
		ArrayList<Territory> executes = new ArrayList<Territory>();
		executes.addAll(list);

		double distributionalScore = 0;
		GraphNode execute; ArrayList<GraphNode> currList; 
		ArrayList<ArrayList<GraphNode>> searchList = new ArrayList<ArrayList<GraphNode>>();
		ArrayList<GraphNode> extendedList = new ArrayList<GraphNode>();
		execute = findGraphNode(executes.get(0));
		while(executes.size() > 0) {
			ArrayList<GraphNode> executeList = new ArrayList<GraphNode>(); executeList.add(execute);
			searchList.add(executeList);
			extendedList.add(executeList.get(executeList.size() - 1));
			search:while(!searchList.isEmpty()) {
				currList = searchList.remove(0);
				for(GraphNode connect : currList.get(currList.size() - 1).connectedTerritories) {
					if(extendedList.contains(connect)) continue;
					extendedList.add(connect);
					ArrayList<GraphNode> insert = new ArrayList<GraphNode>(currList);
					insert.add(connect);
					searchList.add(insert);
					if(executes.contains((insert.get(insert.size() - 1).territory))) break search;
				}
			}
			executeList = searchList.get(searchList.size() - 1);
			distributionalScore += Math.pow(executeList.size() - 1, 2);
			execute = executeList.get(executeList.size() - 1);
			executes.remove(execute.territory);

			searchList.clear();
			extendedList.clear();
		}
		 **/

	}

	public Territory extractFlushTerritory(Territory flush) {
		ArrayList<GraphNode> flushes = new ArrayList<GraphNode>(); flushes.add(findGraphNode(flush));
		GraphNode execute;
		for(int i = 0; i < flushes.size(); i++) {
			execute = flushes.get(i); if(execute == null) continue;
			for(GraphNode insert : execute.connectedTerritories) {
				if(insert.territory.getPlayer() == flush.getPlayer()) {
					if(!flushes.contains(insert)) flushes.add(insert);
				}
			}
		}
		
		check:for(int i = 0; i < flushes.size(); i++) {
			execute = flushes.get(i);
			for(GraphNode connect : execute.connectedTerritories)
				if(connect.territory.getPlayer() != execute.territory.getPlayer()) continue check;
			flushes.remove(execute);
			i--;
		}
		
		SearchNode longestPath = null; Territory result = null;
		ArrayList<SearchNode> searchList = new ArrayList<SearchNode>(); SearchNode search;
		ArrayList<GraphNode> extendedList = new ArrayList<GraphNode>();
		for(GraphNode executeFlush : flushes) {
			searchList.add(new SearchNode(executeFlush));
			while(!searchList.isEmpty()) {
				if(searchList.get(0).checkExecuted()) break;
				search = searchList.get(0);
				extendedList.add(search.path.get(search.path.size() - 1));
				ArrayList<SearchNode> branches = search.execute();
				for(int i = 0; i < branches.size(); i++) {
					SearchNode branch = branches.get(i);
					if(branch.path.get(branch.path.size() - 1).territory.getPlayer() == flush.getPlayer()) {
						branches.remove(branch); i--; continue;
					}
					if(extendedList.contains(branch.path.get(branch.path.size() - 1))) {
						branches.remove(branch); i--;
					}
				}
				searchList.addAll(branches);
				searchList.sort(null);
			}
			extendedList.clear();
			if(longestPath == null) {
				longestPath = searchList.get(0);
				result = executeFlush.territory;
			}
			else if(searchList.get(0).path.size() > longestPath.path.size()) {
				longestPath = searchList.get(0);
				result = executeFlush.territory;
			}
			searchList.clear();
		}
		return result;
	}

	public ArrayList<Territory> getTerritories() {
		ArrayList<Territory> result = new ArrayList<Territory>();
		for(GraphNode currNode : territories) result.add(currNode.territory);
		return result;
	}

	private GraphNode findGraphNode(Territory territory) {
		for(GraphNode currNode : territories)
			if(currNode.territory.equals(territory)) return currNode;
		return null;
	}
	
	/**
	 * Specialized SearchNode to find the longest path possible.
	 * You can specialize by defining new heuristic function mode.
	 * Executes returns the branches but mot eliminates the loops since inefficiency.
	 * Try to eliminate with A*' extended list reasoning.
	 */
	private static class SearchNode implements Comparable<SearchNode> {
		
		private ArrayList<GraphNode> path = new ArrayList<>();
		private boolean executed = false;
		private double heuristicValue;
		
		private SearchNode() {}
		private SearchNode(GraphNode initialNode) {
			this.path.add(initialNode);
			this.extractHeuristicValue();
		}
		
		private boolean checkExecuted() {
			return executed;
		}
		
		@Override
		public int compareTo(SearchNode check) {
			if(heuristicValue < check.heuristicValue) return -1;
			if(heuristicValue > check.heuristicValue) return 1;
			return 0;
		}
		
		private void extractHeuristicValue() {
			if(executed) heuristicValue = GameController.activeMode.territoryGraph.territories.size() / (double)path.size();
			else heuristicValue = 1 / (double) path.size();
		}
		
		private SearchNode copy() {
			SearchNode copy = new SearchNode();
			for(GraphNode pathNode : path)
				copy.path.add(pathNode);
			return copy;
		}
		
		private ArrayList<SearchNode> execute() {
			ArrayList<SearchNode> branhces = new ArrayList<SearchNode>();
			SearchNode insert;
			for(GraphNode branch : path.get(path.size() - 1).connectedTerritories) {
				insert = this.copy();
				insert.path.add(branch);
				insert.extractHeuristicValue();
				branhces.add(insert);
			}
			executed = true;
			this.extractHeuristicValue();
			return branhces;
		}

	}

}//endClass
