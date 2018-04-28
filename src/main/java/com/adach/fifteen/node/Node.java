package com.adach.fifteen.node;

import com.adach.fifteen.utils.MatrixUtils;
import java.util.Arrays;

public class Node {

  //region PRIVATE
  private byte[][] state;
  private Node parent = null;
  private char operator;
  private int cost = 0;

  private byte zeroX = 0;
  private byte zeroY = 0;
  //endregion

  //region GETTERS AND SETTERS
  public byte[][] getState() {
    return state;
  }

  public void setState(byte[][] state) {
    this.state = state;
  }

  public Node getParent() {
    return parent;
  }

  public void setParent(Node parent) {
    this.parent = parent;
  }

  public char getOperator() {
    return operator;
  }

  public void setOperator(char operator) {
    this.operator = operator;
  }

  public int getCost() {
    return cost;
  }

  public void setCost(int cost) {
    this.cost = cost;
  }

  public byte getZeroX() {
    return zeroX;
  }

  public void setZeroX(byte zeroX) {
    this.zeroX = zeroX;
  }

  public byte getZeroY() {
    return zeroY;
  }

  public void setZeroY(byte zeroY) {
    this.zeroY = zeroY;
  }
  //endregion

  //region PUBLIC METHODS
  public boolean canLeft() {
    return zeroX > 0;
  }

  public boolean canRight() {
    return zeroX < state[0].length - 1;
  }

  public boolean canUp() {
    return zeroY > 0;
  }

  public boolean canDown() {
    return zeroY < state.length - 1;
  }

  // -------------------------------------------

  public Node moveLeft() {
    Node node = createNextNode('L');
    MatrixUtils.swapElements(node.state, zeroX, zeroY, (byte) (zeroX - 1), zeroY);
    node.setZeroX((byte) (zeroX - 1));
    return node;
  }

  public Node moveRight() {
    Node node = createNextNode('R');
    MatrixUtils.swapElements(node.state, zeroX, zeroY, (byte) (zeroX + 1), zeroY);
    node.setZeroX((byte) (zeroX + 1));
    return node;
  }

  public Node moveUp() {
    Node node = createNextNode('U');
    MatrixUtils.swapElements(node.state, zeroX, zeroY, zeroX, (byte) (zeroY - 1));
    node.setZeroY((byte) (zeroY - 1));
    return node;
  }

  public Node moveDown() {
    Node node = createNextNode('D');
    MatrixUtils.swapElements(node.state, zeroX, zeroY, zeroX, (byte) (zeroY + 1));
    node.setZeroY((byte) (zeroY + 1));
    return node;
  }

  // -------------------------------------------

  public String getPathFromRootNode() {
    StringBuilder sb = new StringBuilder();
    Node node = this;
    while (node.getParent() != null) {
      sb.append(node.getOperator());
      node = node.getParent();
    }
    return sb.reverse().toString();
  }

  // -------------------------------------------

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Node node = (Node) o;

    return Arrays.deepEquals(state, node.state);
  }

  @Override
  public int hashCode() {
    return Arrays.deepHashCode(state);
  }
  //endregion

  //region PRIVATE METHODS
  private Node createNextNode(char operator) {
    Node node = new Node();
    node.setParent(this);
    node.setState(MatrixUtils.copyMatrix(this.state));
    node.setCost(this.cost + 1);
    node.setZeroX(this.zeroX);
    node.setZeroY(this.zeroY);
    node.setOperator(operator);
    return node;
  }
  //endregion
}