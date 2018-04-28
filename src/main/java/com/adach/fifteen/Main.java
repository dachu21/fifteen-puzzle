package com.adach.fifteen;

import com.adach.fifteen.engine.PuzzleEngine;
import com.adach.fifteen.engine.impl.AstarEngine;
import com.adach.fifteen.engine.impl.BfsEngine;
import com.adach.fifteen.engine.impl.DfsEngine;

public class Main {

  //region PRIVATE
  private String strategy;
  private String parameter;
  private String inputFile;
  private String outputFile;
  private String statsFile;

  private PuzzleEngine engine;
  //endregion

  public static void main(String args[]) {
    Main main = new Main();
    main.run(args);
  }

  //region PRIVATE METHODS
  private void run(String args[]) {
    parseArgs(args);
    createEngine();
    engine.setEngineParameter(parameter);
    engine.createFiles(inputFile, outputFile, statsFile);
    engine.run();
  }

  private void parseArgs(String args[]) {
    strategy = args[0];
    parameter = args[1];
    inputFile = args[2];
    outputFile = args[3];
    statsFile = args[4];
  }

  private void createEngine() {
    switch (strategy) {
      case "bfs":
        engine = new BfsEngine();
        break;
      case "dfs":
        engine = new DfsEngine();
        break;
      case "astr":
        engine = new AstarEngine();
        break;
    }
  }
  //endregion
}