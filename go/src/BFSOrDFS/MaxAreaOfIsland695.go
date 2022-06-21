package main

var n, m int

func maxAreaOfIsland(grid [][]int) int {
	n, m = len(grid), len(grid[0])
	visited := make([][]bool, n)
	for i := 0; i < n; i++ {
		visited[i] = make([]bool, m)
	}
	max := 0
	for i := 0; i < n; i++ {
		for j := 0; j < m; j++ {
			res := dfs(i, j, grid, visited)
			if res > max {
				max = res
			}
		}
	}
	return max
}

func dfs(x, y int, grid [][]int, visited [][]bool) int {
	if x < 0 || x >= n || y < 0 || y >= m || grid[x][y] == 0 || visited[x][y] {
		return 0
	}
	visited[x][y] = true
	return dfs(x+1, y, grid, visited) + dfs(x, y+1, grid, visited) + dfs(x-1, y, grid, visited) + dfs(x, y-1, grid, visited) + 1
}

func maxAreaOfIsland2(grid [][]int) int {
	var dir = [][]int{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}
	//dir := [][]int{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}
	n, m := len(grid), len(grid[0])
	visited := make([][]bool, n)
	for i := 0; i < n; i++ {
		visited[i] = make([]bool, m)
	}
	queue := make([][2]int, 0)
	max := 0
	for i := 0; i < n; i++ {
		for j := 0; j < m; j++ {
			if grid[i][j] == 1 {
				visited[i][j] = true
				res := 1
				queue = append(queue, [2]int{i, j})
				for len(queue) > 0 {
					p := queue[0]
					queue = queue[1:]
					for k := 0; k < 4; k++ {
						x, y := p[0]+dir[k][0], p[1]+dir[k][1]
						if x < 0 || x >= n || y < 0 || y >= m || grid[x][y] == 0 || visited[x][y] {
							continue
						}
						visited[x][y] = true
						queue = append(queue, [2]int{x, y})
						res++
					}
				}
				if res > max {
					max = res
				}
			}
		}
	}
	return max
}
