package DivideAndConq;

import java.util.Arrays;
import Components.*;

/**
 * The code in this class is super similar to the code in phase 1, use recursive method
 * and floodfill to find the optimal solution for a  5x8 array which is exact a layer of 
 * the 3d array in the container
 */
public class Search2D {
    private int horizontalGridSize = 8;
    private int verticalGridSize = 5;
    private int[][] firstLayer = new int[8][5];
    public Container finalTruck = new Container();
    private int[][][] truckShape = finalTruck.getShape();
    private char[] input = "PPPPTTPPLLLL".toCharArray();


    /**
     * Start up the method and call everything
     * @return the total value for 3D array in Container
     */
    public int doAlgorithm(){
        for(int i=0; i<firstLayer.length; i++){
            for(int j=0; j<firstLayer[0].length; j++){
                firstLayer[i][j]=-1;
            }
        }
        int counter = 0;
        if(DFSsearch2D(firstLayer, 0)){
            for (int i = 0; i < firstLayer.length; i++) {
                for (int j = 0; j < firstLayer[0].length; j++) {
                    counter += firstLayer[i][j];
                }
            }
            for (int i = 0; i < truckShape.length; i++) {
                truckShape[i] = firstLayer;
            }
        }
        return counter/5 * 33;
        
    }

    /**
     * recursively call itself to go though every possibilties and to see if it can a solution
     * @param field the 2D layer 
     * @param i the index of char array input
     * @return  true if it finds a solution
     */
    public boolean DFSsearch2D (int [][] field, int i){
        // System.out.println(Arrays.deepToString(field));
		if (checkFull(field)) {
			return true;
		}
		int pentID = characterToID(input[i]);
		for (int j = 0; j <rotCounter(input[i]) ; j++) {
			int [][] pent = new int [0][0];
            switch (input[i]) {
                case 'L':
                pent = parcelLPT2D.parcelL2D()[j];
                break;
                case 'P':
                pent = parcelLPT2D.parcelP2D()[j];
                break;
                case 'T':
                pent = parcelLPT2D.parcelT2D()[j];
                break;
            }
			for (int k = 0; k < horizontalGridSize - pent.length + 1; k++) {
				for (int l = 0; l < verticalGridSize - pent[0].length + 1; l++) {
					if (checkCollision(field, pent, k, l) && checkAdded(field, pent, k, l)) {
						addPiece(field, pent, pentID, k, l);
						if (DFSsearch2D(field, i + 1)) {
							return true;
						};
						field = removePiece(field, pent, k, l);
					}
				}
			}
		}
		return false;
	}


    /**
     * set the ID based on the name
     * @param c current char
     * @return the number of ID
     */
    public int characterToID(char c) {
        switch (c) {
            case 'L':
                return 3;
            case 'P':
                return 4;
            case 'T':
                return 5;
        }
        return 0;
    }

    /**
     * get the number of rotations for a type of parcel based on its name
     * @param c the name of parcels in the input char array
     * @return  number of rotations of specific array
     */
    public int rotCounter(char c) {
        switch (c) {
            case 'L':
                return 8;
            case 'P':
                return 8;
            case 'T':
                return 4;
        }
        return 0;
    }

    /**
     * check if it is good to place that piece
     * @param field the 2d field 
     * @param pent  current pentomino
     * @param x
     * @param y
     * @return  true if is able to place into 2D array
     */
    public boolean checkAdded(int[][] field, int[][] pent, int x, int y) {
        int[][] copy = fieldCopy(field);
        addPiece(copy, pent, 1, x, y);
        return floodfill(copy);
    }

    /**
     * Copy the target field
     * @param field
     * @return  the address of copy field
     */
    public int[][] fieldCopy(int[][] field) {
        int[][] copyField = new int[field.length][field[0].length];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                copyField[i][j] = field[i][j];
            }
        }
        return copyField;
    }

    /**
     * purning method
     * @param field
     * @return false if the current branch is not good
     */
    public boolean floodfill(int[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j] == -1) {
                    if (floodfill2(field, i, j) % 5 != 0) {
                        return false;
                    }
                }
            }
        }
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field[0].length; y++) {
                if (field[x][y] == -5) {
                    field[x][y] = -1;
                }
            }
        }
        return true;
    }

    /**
     * Helper method which goes through every connected pieces
     * @param matrix current 2d layer
     * @param x x position of the pentomino
     * @param y x position of the pentomino
     * @return the number of element in that spot
     */
    public int floodfill2(int[][] matrix, int x, int y) {
        matrix[x][y] = -5;
        int counter = 1;
        if (x - 1 >= 0 && matrix[x - 1][y] == -1) {
            counter += floodfill2(matrix, x - 1, y);
        }
        if (x + 1 < matrix.length && matrix[x + 1][y] == -1) {
            counter += floodfill2(matrix, x + 1, y);
        }
        if (y - 1 >= 0 && matrix[x][y - 1] == -1) {
            counter += floodfill2(matrix, x, y - 1);
        }
        if (y + 1 < matrix[0].length && matrix[x][y + 1] == -1) {
            counter += floodfill2(matrix, x, y + 1);
        }
        return counter;
    }

    /**
     * Check if all elements in the target field is -1.
     * @param field a matrix representing the board to be fulfilled with
     *                pentominoes
     * @return false if there is a -1 in the field
     */
    public boolean checkFull(int[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j] == -1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Check collision
     * @param field a matrix representing the board to be fulfilled with
     *                pentominoes
     * @param pent  current pentominto
     * @param x x position of the pentomino
     * @param y y position of the pentomino
     * @return true if there is a collision
     */
    public boolean checkCollision(int[][] field, int[][] pent, int x, int y) {
        for (int i = 0; i < pent.length; i++) {
            for (int j = 0; j < pent[0].length; j++) {
                if (pent[i][j] == 1) {
                    if (field[i + x][j + y] != -1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Adds a pentomino to the position on the field (overriding current board at
     * that position)
     * 
     * @param field   a matrix representing the board to be fulfilled with
     *                pentominoes
     * @param piece   a matrix representing the pentomino to be placed in the board
     * @param pieceID ID of the relevant pentomino
     * @param x       x position of the pentomino
     * @param y       y position of the pentomino
     */
    public static void addPiece(int[][] field, int[][] piece, int pieceID, int x, int y) {
        for (int i = 0; i < piece.length; i++) // loop over x position of pentomino
        {
            for (int j = 0; j < piece[i].length; j++) // loop over y position of pentomino
            {
                if (piece[i][j] == 1) {
                    field[x + i][y + j] = pieceID;
                }
            }
        }
    }

    /**
     * removes a pentomino to the position on the field (overriding current board at
     * that position)
     * 
     * @param field   a matrix representing the board to be fulfilled with
     *                pentominoes
     * @param piece   a matrix representing the pentomino to be placed in the board
     * @param x       x position of the pentomino
     * @param y       y position of the pentomino
     */
    public static int[][] removePiece(int[][] field, int[][] piece, int x, int y) {
        int[][] matrix = field;
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[0].length; j++) {
                if (piece[i][j] == 1) {
                    matrix[x + i][y + j] = -1;
                }
            }
        }
        return matrix;
    }
}
