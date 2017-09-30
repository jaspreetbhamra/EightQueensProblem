package eightQueensUsingBacktracking;

import java.util.Scanner;

public class UsingBacktrackingApproach {

	public static int n = 8;
	public static boolean chessBoard[][];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter the size of the chess board");
//		n = sc.nextInt();
		System.out.println("Enter the initial locations of each of the eight queens in terms of their x and y coordinates (0-indexed)");
		for(int i = 0; i < n; ++i) {
			sc.nextInt();
			sc.nextInt();
		}
		chessBoard = new boolean[n][n];
		solveProblem();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(chessBoard[i][j])
					System.out.print("Q\t");
				else
					System.out.print("0\t");
			}
			System.out.println();
		}
	}

	public static void solveProblem() {
		int currentRow = 0;
		int numberOfQueensPlaced = 0;
		while(numberOfQueensPlaced < n) {
			if(currentRow >= n) {
				numberOfQueensPlaced--;
//				System.out.println(numberOfQueensPlaced);
				for(int j = 0; j < n; j++) {
					if(chessBoard[j][numberOfQueensPlaced]) {
						currentRow = j+1;
						chessBoard[j][numberOfQueensPlaced] = false;
						break;
					}
				}
			} else if(checkIfAnyAttackingQueens(currentRow, numberOfQueensPlaced)) {
				currentRow++;
			} else {
				chessBoard[currentRow][numberOfQueensPlaced] = true;
				numberOfQueensPlaced++;
				currentRow = 0;
			}
		}
	}

	public static boolean checkIfAnyAttackingQueens(int x, int y) {
		if(checkRow(x, y))
			return true;
		if(checkColumn(x, y))
			return true;
		if(checkForwardSlashDiagonal(x, y))
			return true;
		if(checkBackwardSlashDiagonal(x, y))
			return true;
		return false;
	}

	public static boolean checkRow(int x, int y) {
		for (int i = 0; i < n && y != i; i++)
			if(chessBoard[x][i])
				return true;                            //There is atleast one queen that can attack the current queen
		return false;
	}

	public static boolean checkColumn(int x, int y) {
		for (int i = 0; i < n && i != x; i++)
			if(chessBoard[i][y])
				return true;
		return false;
	}

	public static boolean checkForwardSlashDiagonal(int x, int y) {
		int tempX = x, tempY = y;
		while(tempY >= 0 && tempX <= n-1) {
			if(chessBoard[tempX][tempY])
				return true;
			tempX++;
			tempY--;
		}
		tempX = x; tempY = y;
		while(tempX >= 0 && tempY <= n-1) {
			if(chessBoard[tempX][tempY])
				return true;
			tempX--;
			tempY++;
		}
		return false;
	}

	public static boolean checkBackwardSlashDiagonal(int x, int y) {
		int tempX = x, tempY = y;
		while(tempX <= n-1 && tempY <= n-1) {
			if(chessBoard[tempX][tempY])
				return true;
			tempX++;
			tempY++;
		}
		tempX = x; tempY = y;
		while(tempX >= 0 && tempY >= 0) {
			if(chessBoard[tempX][tempY])
				return true;
			tempX--;
			tempY--;
		}
		return false;
	}

}
