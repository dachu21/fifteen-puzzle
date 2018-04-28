package com.adach.fifteen.distance;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;

public abstract class DistanceCalculator {

  protected Map<Byte, SimpleEntry<Byte, Byte>> elementsPositions = new HashMap<Byte, SimpleEntry<Byte, Byte>>();
  protected byte[][] targetPuzzle;

  public DistanceCalculator(byte[][] targetPuzzle) {
    this.targetPuzzle = targetPuzzle;
    for (Byte i = 0; i < targetPuzzle.length; i++) {
      for (Byte j = 0; j < targetPuzzle[i].length; j++) {
        elementsPositions.put(targetPuzzle[i][j], new SimpleEntry<Byte, Byte>(j, i));
      }
    }
  }

  public abstract int calcGlobalDistance(byte[][] state);
}