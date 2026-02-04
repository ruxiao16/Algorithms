package graphs

import "testing"

func numIslands(grid [][]byte) int {
	numOfRows := len(grid)
	numOfCols := len(grid[0])
	res := 0

	// this only allocate outer slice ("the rows), unlike in java we do boolean[][] visited = new boolean[numOfRows][numOfCols]
	visited := make([][]bool, numOfRows)

	// loop thru and allocate each inner slice (the columns)
	for i := 0; i < numOfRows; i++ {
		visited[i] = make([]bool, numOfCols)
	}

	for r, rowSlice := range grid {
		for c, val := range rowSlice {
			if !visited[r][c] && val == '1' {
				bfs(visited, r, c, grid)
				res++
			}
		}
	}
	return res
}

func bfs(visited [][]bool, row int, col int, grid [][]byte) {
	dr := []int{-1, 1, 0, 0}
	dc := []int{0, 0, 1, -1}
	numOfRows := len(grid)
	numOfCols := len(grid[0])

	var q [][2]int // or q := [][]int{}
	visited[row][col] = true
	q = append(q, [2]int{row, col})

	for len(q) > 0 {
		// get the first element
		curr := q[0]
		// slice off the first element
		q = q[1:]

		r, c := curr[0], curr[1]

		for i := 0; i < 4; i++ {
			newRow := r + dr[i]
			newCol := c + dc[i]
			if newRow < 0 || newCol < 0 || newRow >= numOfRows || newCol >= numOfCols {
				continue
			}
			if grid[newRow][newCol] == '0' {
				continue
			}
			if visited[newRow][newCol] {
				continue
			}
			q = append(q, [2]int{newRow, newCol})
			visited[newRow][newCol] = true
		}
	}
}

func TestNumOfIslands(t *testing.T) {
	grid := [][]byte{
		{'1', '1', '1', '1', '0'},
		{'1', '1', '0', '1', '0'},
		{'1', '1', '0', '0', '1'},
		{'0', '0', '1', '0', '1'},
	}
	res := numIslands(grid)
	if res != 3 {
		t.Errorf("expect 3 getting %d", res)
	}

	grid2 := [][]byte{
		{'0', '1', '1', '1', '0'},
		{'0', '1', '0', '1', '0'},
		{'1', '1', '0', '0', '0'},
		{'0', '0', '0', '0', '0'},
	}
	res1 := numIslands(grid2)
	if res1 != 1 {
		t.Errorf("expect 1 getting %d", res)
	}
}
