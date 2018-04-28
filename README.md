# Fifteen Puzzle Solver (2017)
Java application solving fifteen puzzle.

-------------------

### Instruction

##### CLI arguments

You need to provide 5 arguments.

CLI arguments pattern:
[strategy] [parameter] [input_file] [result_output_file] [stats_output_file]

You can choose one of following strategies:
- astr - A* strategy
- bfs - BFS strategy
- dfs - DFS strategy

For A* strategy you can provide one of following parameters (distance algorithm):
- hamm - Hamming distance
- manh - Manhattan distance

For BFS and DFS strategies you can provide one of following parameters (search order):
- DRLU
- DRUL
- LUDR
- LURD
- RDLU
- RDUL
- ULDR
- ULRD

where D = down, U = UP, R = RIGHT, L = LEFT.

##### Input files
Input file is a text file and should look as follows:

```
[puzzle_height] [puzzle width]
x x x x
x x x x
x x x x
x x x x
```
e.g.
```
4 4
1 2 3 4
5 6 7 8
10 13 11 12
9 14 0 15
```

##### Output files

As a result, you get two output files:
- result file:
  + final path's length
  + final path (path to solve)
- stats file:
  + final path's length
  + number of nodes visited
  + number of nodes fully explored
  + maximum recursion depth
  + execution time
