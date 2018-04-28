package com.adach.fifteen.distance.impl;

import static java.lang.Math.abs;

import com.adach.fifteen.distance.DistanceCalculator;

public class ManhattanCalculator extends DistanceCalculator {

  public ManhattanCalculator(byte[][] targetPuzzle) {
    super(targetPuzzle);
  }

  @Override
  public int calcGlobalDistance(byte[][] state) {
    int distance = 0;
    for (int i = 0; i < state.length; i++) {
      for (int j = 0; j < state[i].length; j++) {
        if (state[i][j] != targetPuzzle[i][j]) {
          distance += calcManhDist(j, i, elementsPositions.get(state[i][j]).getKey(),
              elementsPositions.get(state[i][j]).getValue());
        }
      }
    }
    return distance;
  }

  private int calcManhDist(int currentX, int currentY, int targetX, int targetY) {
    return (abs(currentX - targetX) + abs(currentY - targetY));
  }
}