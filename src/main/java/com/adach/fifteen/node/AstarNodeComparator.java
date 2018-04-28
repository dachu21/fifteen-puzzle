package com.adach.fifteen.node;

import java.util.Comparator;

public class AstarNodeComparator implements Comparator<AstarNode> {

  @Override
  public int compare(AstarNode n1, AstarNode n2) {
    return Integer.compare(n1.getScore(), n2.getScore());
  }
}
