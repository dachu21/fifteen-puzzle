package com.adach.fifteen.utils;

public final class MatrixUtils {

  private MatrixUtils() {
  }

  public static byte[][] copyMatrix(byte[][] input) {
    byte[][] output = new byte[input.length][];
    for (int i = 0; i < input.length; i++) {
      output[i] = input[i].clone();
    }
    return output;
  }

  public static void swapElements(byte matrix[][], byte aX, byte aY, byte bX, byte bY) {
    byte tmp = matrix[aY][aX];
    matrix[aY][aX] = matrix[bY][bX];
    matrix[bY][bX] = tmp;
  }
}