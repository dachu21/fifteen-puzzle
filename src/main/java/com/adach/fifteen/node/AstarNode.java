package com.adach.fifteen.node;

public class AstarNode extends Node {

  private int score = Integer.MAX_VALUE;

  public AstarNode() {
    super();
  }

  public AstarNode(Node node) {
    setState(node.getState());
    setParent(node.getParent());
    setOperator(node.getOperator());
    setCost(node.getCost());
    setZeroX(node.getZeroX());
    setZeroY(node.getZeroY());
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }
}