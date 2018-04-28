package com.adach.fifteen.distance.impl;

import com.adach.fifteen.distance.DistanceCalculator;

public class HammingCalculator extends DistanceCalculator {

  public HammingCalculator(byte[][] targetPuzzle) {
    super(targetPuzzle);
  }

  @Override
  public int calcGlobalDistance(byte[][] state) {
    int distance = 0;
    for (int i = 0; i < state.length; i++) {
      for (int j = 0; j < state[i].length; j++) {
        if (state[i][j] != targetPuzzle[i][j]) {
          distance++;
        }
      }
    }
    return distance;
  }
}