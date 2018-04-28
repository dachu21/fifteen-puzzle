package com.adach.fifteen.engine;

import com.adach.fifteen.node.Node;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public abstract class PuzzleEngine {

  //region PROTECTED
  protected String engineParameter = null;
  protected byte[][] targetPuzzle = null;

  protected int visitedCount = 1;
  protected int exploredCount = 0;
  protected int recursionDepth = 0;
  //endregion

  //region PRIVATE
  private File inputFile;
  private File outputFile;
  private File statsFile;
  //endregion

  //region GETTERS AND SETTERS
  public void setEngineParameter(String engineParameter) {
    this.engineParameter = engineParameter;
  }
  //endregion

  //region PUBLIC METHODS
  public void createFiles(String inputFile, String outputFile, String statsFile) {
    this.inputFile = new File(inputFile);
    this.outputFile = new File(outputFile);
    this.statsFile = new File(statsFile);
  }

  public void run() {
    Node rootNode = this.initialize();

    long timeStart = System.nanoTime();
    Node finalNode = this.solvePuzzle(rootNode);
    long timeStop = System.nanoTime();

    this.finalize(finalNode, (double) (timeStop - timeStart) / 1000000);
  }
  //endregion

  //region PROTECTED METHODS
  protected abstract Node solvePuzzle(Node rootNode);

  protected abstract void processNewNode(Node newNode);

  protected Node performOperation(Node inputNode, char operator) {
    Node newNode = null;
    switch (operator) {
      case 'L':
        if (inputNode.canLeft()) {
          newNode = inputNode.moveLeft();
        }
        break;
      case 'R':
        if (inputNode.canRight()) {
          newNode = inputNode.moveRight();
        }
        break;
      case 'U':
        if (inputNode.canUp()) {
          newNode = inputNode.moveUp();
        }
        break;
      case 'D':
        if (inputNode.canDown()) {
          newNode = inputNode.moveDown();
        }
        break;
      default:
        throw new IllegalArgumentException("INVALID OPERATOR");
    }
    return newNode;
  }

  protected boolean checkIfTargetFound(Node node) {
    return Arrays.deepEquals(node.getState(), targetPuzzle);
  }
  //endregion

  //region PRIVATE METHODS
  private Node initialize() {
    Node rootNode = new Node();

    try (Scanner scanner = new Scanner(inputFile)) {
      int puzzleHeight = scanner.nextInt();
      int puzzleWidth = scanner.nextInt();

      byte[][] startPuzzle = new byte[puzzleHeight][puzzleWidth];
      targetPuzzle = new byte[puzzleHeight][puzzleWidth];

      int k = 1;
      for (int i = 0; i < puzzleHeight; i++) {
        for (int j = 0; j < puzzleWidth; j++) {
          startPuzzle[i][j] = (byte) scanner.nextInt();
          if (startPuzzle[i][j] == 0) {
            rootNode.setZeroX((byte) j);
            rootNode.setZeroY((byte) i);
          }
          targetPuzzle[i][j] = (byte) k++;
        }
      }
      targetPuzzle[puzzleHeight - 1][puzzleWidth - 1] = 0;

      rootNode.setState(startPuzzle);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return rootNode;
  }

  private void finalize(Node finalNode, double totalTime) {
    try (PrintWriter outputWriter = new PrintWriter(
        outputFile); PrintWriter statsWriter = new PrintWriter(statsFile)) {
      if (finalNode != null) {
        outputWriter.println(finalNode.getCost());
        outputWriter.print(finalNode.getPathFromRootNode());

        statsWriter.println(finalNode.getCost());
        statsWriter.println(visitedCount);
        statsWriter.println(exploredCount);
        statsWriter.println(recursionDepth);
        statsWriter.printf(Locale.US, "%.3f", totalTime);
      } else {
        outputWriter.print(-1);

        statsWriter.println(-1);
        statsWriter.println(visitedCount);
        statsWriter.println(exploredCount);
        statsWriter.println(recursionDepth);
        statsWriter.printf(Locale.US, "%.3f", totalTime);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
  //endregion
}