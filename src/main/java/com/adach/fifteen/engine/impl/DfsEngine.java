package com.adach.fifteen.engine.impl;

import com.adach.fifteen.engine.PuzzleEngine;
import com.adach.fifteen.node.Node;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class DfsEngine extends PuzzleEngine {

  private final int RECURSION_DEPTH = 20;

  private Node currentNode = null;
  private boolean targetFound = false;

  private LinkedList<Node> frontier = new LinkedList<>();
  private HashMap<Node, Integer> explored = new HashMap<>();
  private HashSet<Node> rejected = new HashSet<>();

  @Override
  public Node solvePuzzle(Node rootNode) {

    if (checkIfTargetFound(rootNode)) {
      targetFound = true;
      return rootNode;
    } else {
      rejected.add(rootNode);
      frontier.push(rootNode);
    }

    while (!frontier.isEmpty()) {
      currentNode = frontier.pop();
      for (int i = engineParameter.length() - 1; i >= 0; i--) {
        processNewNode(super.performOperation(currentNode, engineParameter.charAt(i)));
        if (targetFound) {
          if (i == 0) { // Check if it's the last operation of currently explored Node.
            exploredCount = explored.size() + 1;
          } else {
            exploredCount = explored.size();
          }
          return currentNode;
        }
      }
      explored
          .put(currentNode, currentNode.getCost()); // Won't  add again if already explored before.
    }

    // Solution not found
    exploredCount = explored.size();
    return null;
  }

  @Override
  protected void processNewNode(Node newNode) {

    Integer oldCost;
    Integer nodeAlreadyInFrontierIndex;

    if (newNode != null) {

      recursionDepth = Math.max(newNode.getCost(), recursionDepth);

      // 1. Node already explored
      oldCost = explored.get(newNode);
      if (oldCost != null) {
        if (oldCost > newNode.getCost()) {
          nodeAlreadyInFrontierIndex = frontier.indexOf(newNode);
          if (nodeAlreadyInFrontierIndex != -1) {
            frontier.set(nodeAlreadyInFrontierIndex, newNode);
          } else {
            frontier.push(newNode);
          }
        }
        return;
      }

      // 2. Node already in frontier, but not explored yet
      nodeAlreadyInFrontierIndex = frontier.indexOf(newNode);
      if (nodeAlreadyInFrontierIndex != -1) {
        if (frontier.get(nodeAlreadyInFrontierIndex).getCost() > newNode.getCost()) {
          frontier.set(nodeAlreadyInFrontierIndex, newNode);
        }
        return;
      }

      // 3. Node rejected before with cost equal to RECURSION_DEPTH
      if (rejected.contains(newNode)) {
        if (newNode.getCost() < RECURSION_DEPTH) {
          frontier.push(newNode);
        }
        return;
      }

      // 4. Node not visited yet
      visitedCount++;
      if (checkIfTargetFound(newNode)) {
        targetFound = true;
        currentNode = newNode;
      }
      if (newNode.getCost() < RECURSION_DEPTH) {
        frontier.push(newNode);
      } else {
        rejected.add(newNode);
      }
    }
  }
}