package com.adach.fifteen.engine.impl;

import com.adach.fifteen.node.AstarNode;
import com.adach.fifteen.node.AstarNodeComparator;
import com.adach.fifteen.distance.DistanceCalculator;
import com.adach.fifteen.distance.impl.HammingCalculator;
import com.adach.fifteen.distance.impl.ManhattanCalculator;
import com.adach.fifteen.engine.PuzzleEngine;
import com.adach.fifteen.node.Node;
import java.util.PriorityQueue;

public class AstarEngine extends PuzzleEngine {

  private final String DIRECTIONS = "RLUD";

  private DistanceCalculator calculator = null;
  private AstarNodeComparator nodeComparator = new AstarNodeComparator();

  private AstarNode currentNode = null;
  private boolean targetFound = false;

  private PriorityQueue<AstarNode> frontier = new PriorityQueue<>(nodeComparator);
  private PriorityQueue<AstarNode> explored = new PriorityQueue<>(nodeComparator);

  @Override
  public Node solvePuzzle(Node rootNode) {

    initializeDistanceCalculator();

    if (checkIfTargetFound(rootNode)) {
      targetFound = true;
      return rootNode;
    } else {
      frontier.add(new AstarNode(rootNode));
    }

    while (!frontier.isEmpty()) {
      currentNode = frontier.poll();
      for (int i = 0; i < DIRECTIONS.length(); i++) {
        processNewNode(super.performOperation(currentNode, DIRECTIONS.charAt(i)));
        if (targetFound) {
          if (i == DIRECTIONS.length() - 1) {
            exploredCount = explored.size() + 1;
          } else {
            exploredCount = explored.size();
          }
          recursionDepth = currentNode.getCost();
          return currentNode;
        }
      }
      explored.add(currentNode);
    }

    exploredCount = explored.size();
    recursionDepth = currentNode.getCost();
    return null;
  }

  @Override
  protected void processNewNode(Node newNode) {

    if (newNode != null) {
      AstarNode newAstarNode = new AstarNode(newNode);
      if (!explored.contains(newAstarNode) && !frontier.contains(newAstarNode)) {
        if (checkIfTargetFound(newAstarNode)) {
          currentNode = newAstarNode;
          targetFound = true;
        }
        int distCost = calculator.calcGlobalDistance(newAstarNode.getState());
        newAstarNode.setScore(distCost + newAstarNode.getCost());
        frontier.add(newAstarNode);
        visitedCount++;
      }
    }
  }

  private void initializeDistanceCalculator() {
    switch (engineParameter) {
      case "manh":
        calculator = new ManhattanCalculator(targetPuzzle);
        break;
      case "hamm":
        calculator = new HammingCalculator(targetPuzzle);
        break;
    }
  }
}