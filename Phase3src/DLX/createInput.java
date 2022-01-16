package DLX;

import java.util.ArrayList;

import Components.Container;
import Components.parcelsABC;
import Components.parcelsLPT;

//Class for creating 2D Matrix for Dancing Links 

public class createInput {
    //Static variables created from the components in the components package  
    public static int[][][][] parcelA = parcelsABC.parcelARot();
    public static int[][][][] parcelB = parcelsABC.parcelBRot();
    public static int[][][][] parcelC = parcelsABC.parcelCRot();
    public static int[][][][] parcelL = parcelsLPT.parcelLRot();
    public static int[][][][] parcelP = parcelsLPT.parcelPRot();
    public static int[][][][] parcelT = parcelsLPT.parcelTRot();

    //sampleArray is a 3D Array with the same size of the Container (33x8x5) and is used later on to create the 2D matrix 
    public static int[][][] sampleArray = createSampleArray();

    /**
     * Method which simulates and saves all the possible placements for parcelTypes ABC inside the container
     * @return an ArrayList containing all the possible placements 
     */
    public static ArrayList<int[][][]> createPlacementsforABC() {
        ArrayList<int[][][]> placements = new ArrayList<>();
        Container testDummy = new Container();
        for (int i = 0; i < parcelC.length; i++) {
            for (int j = 0; j < testDummy.getLength(); j++) {
                for (int k = 0; k < testDummy.getHeight(); k++) {
                    for (int l = 0; l < testDummy.getWidth(); l++) {
                        if (testDummy.checkAvailable(parcelC[i], j, k, l)) {
                            testDummy.add(parcelC[i], j, k, l, 2);
                            int[][][] tempo = copy3DArray(testDummy.getShape());
                            placements.add(tempo);
                            testDummy.remove(parcelC[i], j, k, l);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < parcelB.length; i++) {
            for (int j = 0; j < testDummy.getLength(); j++) {
                for (int k = 0; k < testDummy.getHeight(); k++) {
                    for (int l = 0; l < testDummy.getWidth(); l++) {
                        if (testDummy.checkAvailable(parcelB[i], j, k, l)) {
                            testDummy.add(parcelB[i], j, k, l, 1);
                            int[][][] tempo = copy3DArray(testDummy.getShape());
                            placements.add(tempo);
                            testDummy.remove(parcelB[i], j, k, l);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < parcelA.length; i++) {
            for (int j = 0; j < testDummy.getLength(); j++) {
                for (int k = 0; k < testDummy.getHeight(); k++) {
                    for (int l = 0; l < testDummy.getWidth(); l++) {
                        if (testDummy.checkAvailable(parcelA[i], j, k, l)) {
                            testDummy.add(parcelA[i], j, k, l, 0);
                            int[][][] tempo = copy3DArray(testDummy.getShape());
                            placements.add(tempo);
                            testDummy.remove(parcelA[i], j, k, l);
                        }
                    }
                }
            }
        }
        return placements;
    }
     /**
     * Method which simulates and saves all the possible placements for parcelTypes LPT inside the container
     * (Prunes out the L Pieces to get higher value, exact cover can be made using only P and T Pieces)
     * @return an ArrayList containing all the possible placements 
     */
    public static ArrayList<int[][][]> createPlacementsforLPT() {
        ArrayList<int[][][]> placements = new ArrayList<>();
        Container testDummy = new Container();
        for (int j = 0; j < testDummy.getShape().length; j++) {
            for (int k = 0; k < testDummy.getShape()[0].length; k++) {
                for (int l = 0; l < testDummy.getShape()[0][0].length; l++) {
                    for (int i = 0; i < parcelT.length; i++) {
                        int[][][] pent = parcelT[i];
                        if (testDummy.checkAvailable(pent, j, k, l)) {
                            testDummy.add(pent, j, k, l, 5);
                            int[][][] tempo = copy3DArray(testDummy.getShape());
                            placements.add(tempo);
                            testDummy.setDefault();
                        }
                    }
                }
            }
        }
        for (int j = 0; j < testDummy.getShape().length; j++) {
            for (int k = 0; k < testDummy.getShape()[0].length; k++) {
                for (int l = 0; l < testDummy.getShape()[0][0].length; l++) {
                    for (int i = 0; i < parcelP.length; i++) {
                        int[][][] pent = parcelP[i];
                        if (testDummy.checkAvailable(pent, j, k, l)) {
                            testDummy.add(pent, j, k, l, 4);
                            int[][][] tempo = copy3DArray(testDummy.getShape());
                            placements.add(tempo);
                            testDummy.setDefault();
                        }
                    }
                }
            }
        }
        // for (int j = 0; j < testDummy.getShape().length; j++) {
        //     for (int k = 0; k < testDummy.getShape()[0].length; k++) {
        //         for (int l = 0; l < testDummy.getShape()[0][0].length; l++) {
        //             for (int i = 0; i < parcelL.length; i++) {
        //                 int[][][] pent = parcelL[i];
        //                 if (testDummy.checkAvailable(pent, j, k, l)) {
        //                     testDummy.add(pent, j, k, l, 3);
        //                     int[][][] tempo = copy3DArray(testDummy.getShape());
        //                     placements.add(tempo);
        //                     testDummy.setDefault();
        //                 }
        //             }
        //         }
        //     }
        // }
        return placements;
    }
    /**
     * Create an exact copy of a 3D array
     * @param temp the 3D Array to be copied 
     * @return a copy of the parameter 
     */
    private static int[][][] copy3DArray(int[][][] temp) {
        int[][][] toReturn = new int[temp.length][temp[0].length][temp[0][0].length];
        for (int i = 0; i < toReturn.length; i++) {
            for (int j = 0; j < toReturn[0].length; j++) {
                for (int k = 0; k < toReturn[0][0].length; k++) {
                    toReturn[i][j][k] = temp[i][j][k];
                }
            }
        }
        return toReturn;
    }
    /**
     * Creates the input Matrix that is used in dancing links  
     * @param parcelOrPent determines whether to create the input matrix for box parcels or pentomino parcels 
     * @return the 2D Input matrix to be used in DLX
     */
    public static int[][] createInputMatrix(boolean parcelOrPent) {
        ArrayList<int[][][]> placementList = new ArrayList<>();
        if (parcelOrPent) {
            placementList = createPlacementsforABC();
        } else {
            placementList = createPlacementsforLPT();
        }
        int[][] twoDMatrix = new int[placementList.size()][33 * 8 * 5];
        for (int i = 0; i < twoDMatrix.length; i++) {
            twoDMatrix[i] = create1DMatrix(placementList.get(i));
        }
        return twoDMatrix;
    }
    /**
     * Creates a one dimensional matrix from a 3 dimensional matrix by "squishing it", turns it into a row in the 2D input matrix for DLX
     * @param placement the 3D matrix to be squished into a one dimensional matrix
     * @return the squished 1D Matrix
     */
    public static int[] create1DMatrix(int[][][] placement) {
        int[] oneDMatrix = new int[33 * 8 * 5];
        for (int i = 0; i < placement.length; i++) {
            for (int j = 0; j < placement[0].length; j++) {
                for (int k = 0; k < placement[0][0].length; k++) {
                    oneDMatrix[sampleArray[i][j][k]] = placement[i][j][k];
                }
            }
        }
        // System.out.println(Arrays.toString(oneDMatrix));
        return oneDMatrix;
    }
    /**
     * A method to create 3d array of the same size which has unique integers as their ID
     * This 3D array is very helpful in building the 2D input matrix for DLX as well as remaking the 3D container after DLX
     * @return the 3D array of the same 
     */
    public static int[][][] createSampleArray() {
        int[][][] sampleContainer = new int[33][8][5];
        int counter = 0;
        for (int i = 0; i < sampleContainer.length; i++) {
            for (int j = 0; j < sampleContainer[0].length; j++) {
                for (int k = 0; k < sampleContainer[0][0].length; k++) {
                    sampleContainer[i][j][k] = counter;
                    counter++;
                }
            }
        }
        return sampleContainer;
    }

}
