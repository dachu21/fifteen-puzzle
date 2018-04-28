package com.adach.fifteen.engine.impl;

import com.adach.fifteen.engine.PuzzleEngine;
import com.adach.fifteen.node.Node;
import java.util.LinkedHashSet;

public class BfsEngine extends PuzzleEngine {

  private Node currentNode = null;
  private boolean targetFound = false;

  private LinkedHashSet<Node> frontier = new LinkedHashSet<>();
  private LinkedHashSet<Node> explored = new LinkedHashSet<>();

  @Override
  public Node solvePuzzle(Node rootNode) {

    if (checkIfTargetFound(rootNode)) {
      targetFound = true;
      return rootNode;
    } else {
      frontier.add(rootNode);
    }

    while (!frontier.isEmpty()) {
      currentNode = frontier.iterator().next();
      for (int i = 0; i < engineParameter.length(); i++) {
        processNewNode(super.performOperation(currentNode, engineParameter.charAt(i)));
        if (targetFound) {
          if (i == engineParameter.length() - 1) {
            exploredCount = explored.size() + 1;
          } else {
            exploredCount = explored.size();
          }
          recursionDepth = currentNode.getCost();
          return currentNode;
        }
      }
      frontier.remove(currentNode);
      explored.add(currentNode);
    }

    exploredCount = explored.size();
    recursionDepth = currentNode.getCost();
    return null;
  }

  @Override
  protected void processNewNode(Node newNode) {
    if (newNode != null && !explored.contains(newNode) && !frontier.contains(newNode)) {
      if (checkIfTargetFound(newNode)) {
        targetFound = true;
        currentNode = newNode;
      }
      frontier.add(newNode);
      visitedCount++;
    }
  }
}