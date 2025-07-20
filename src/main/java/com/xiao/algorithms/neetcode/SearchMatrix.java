package com.xiao.algorithms.neetcode;

// binary search on matrix
public class SearchMatrix {
	public boolean searchMatrix(int[][] matrix, int target) {
		int numsOfCols = matrix[0].length-1;
		int lowerBound = 0;
		int upperBound = matrix.length-1;

		int rowToSearch = -1;

		// identify the row to search on first, this will take log(m)
		while (lowerBound <= upperBound) {
			int mid = (upperBound + lowerBound)/2;
			// we found the row
			System.out.println("mid is "+ mid);
			if (target >= matrix[mid][0] && target <= matrix[mid][numsOfCols]) {
				System.out.println("row found at row "+mid);
				rowToSearch = mid;
				break;
			}
			else if (target > matrix[mid][numsOfCols]) {
				lowerBound = mid + 1;
			}
			else {
				//target smaller than the smallest element in this row
				upperBound = mid - 1;
			}
		}

		if (rowToSearch == -1) {
			return false;
		}

		int l = 0;
		int r = numsOfCols;

//		this takes log(n), then in total it taks log(m) + log(n) which is log(m*n)
		while (l <= r) {
			int mid  = l + ((r-l)/2);
			if (matrix[rowToSearch][mid] > target) {
				r = mid - 1;
			} else if (matrix[rowToSearch][mid] < target) {
				l = mid + 1;
			} else {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		SearchMatrix driver = new SearchMatrix();

		int testArr[][]={{1,2,4,8},{10,11,12,13},{14,20,30,40}};
		System.out.println("num of rows is " + testArr.length + " number of columns is "+ testArr[0].length);
		System.out.println(driver.searchMatrix(testArr, 10));

		System.out.println(driver.searchMatrix(testArr, 66));

		System.out.println(driver.searchMatrix(testArr, 1));

		int testArr1[][] = {{1}};
		System.out.println(driver.searchMatrix(testArr1, 1));
	}
}
