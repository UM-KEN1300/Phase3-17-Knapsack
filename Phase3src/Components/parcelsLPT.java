package Components;

import java.util.Arrays;

public class parcelsLPT {

    // representations of parcels
    static int[][][] arrL = { { { 1 }, { 1 }, { 1 }, { 1 } }, { { 1 }, { 0 }, { 0 }, { 0 } } };

    static int[][][] arrP = { { { 1, 1 }, { 1, 1 }, { 1, 0 } } };

    static int[][][] arrT = { { { 1, 1, 1 }, { 0, 1, 0 }, { 0, 1, 0 } } };

    /**
     * Method used to rotate a PentominoParcel around the X axis.
     */
    public static int[][][] rotateX(int[][][] parcelArray) {
        int[][][] rotateArray = new int[parcelArray.length][parcelArray[0][0].length][parcelArray[0].length];
        for (int i = 0; i < rotateArray.length; i++) {
            for (int j = 0; j < rotateArray[0].length; j++) {
                for (int k = 0; k < rotateArray[0][0].length; k++) {
                    rotateArray[i][j][k] = parcelArray[i][rotateArray[0][0].length - 1 - k][j];
                }
            }
        }
        return rotateArray;
    }

    /**
     * Method used to rotate a PentominoParcel around the Y axis.
     */
    public static int[][][] rotateY(int[][][] parcelArray) {
        int[][][] rotateArray = new int[parcelArray[0].length][parcelArray.length][parcelArray[0][0].length];
        for (int i = 0; i < rotateArray.length; i++) {
            for (int j = 0; j < rotateArray[0].length; j++) {
                for (int k = 0; k < rotateArray[0][0].length; k++) {
                    rotateArray[i][j][k] = parcelArray[j][rotateArray.length - 1 - i][k];
                }
            }
        }
        return rotateArray;
    }

    /**
     * Method used to rotate a PentominoParcel around the Z axis.
     */
    public static int[][][] rotateZ(int[][][] parcelArray) {
        int[][][] rotateArray = new int[parcelArray[0][0].length][parcelArray[0].length][parcelArray.length];
        for (int i = 0; i < rotateArray.length; i++) {
            for (int j = 0; j < rotateArray[0].length; j++) {
                for (int k = 0; k < rotateArray[0][0].length; k++) {
                    rotateArray[i][j][k] = parcelArray[k][j][rotateArray.length - 1 - i];
                }
            }
        }
        return rotateArray;
    }

    // all possible rotations parcel L
    public static int[][][][] parcelLRot() {
        int[][][][] allPossibilities = {
                arrL, rotateY(arrL),rotateX(arrL),
                rotateZ(arrL), rotateY(rotateX(arrL)),rotateX(rotateX(arrL)), rotateZ(rotateX(arrL)), rotateX(rotateY(arrL)),
                rotateY(rotateY(arrL)),
                rotateZ(rotateZ(arrL)), rotateX(rotateX(rotateX(arrL))), rotateY(rotateZ(arrL)),
                rotateY(rotateX(rotateX(arrL))),
                rotateZ(rotateX(rotateX(arrL))), rotateX(rotateY(rotateX(arrL))),
                rotateY(rotateY(rotateX(arrL))), rotateZ(rotateZ(rotateX(arrL))),
                rotateX(rotateX(rotateY(arrL))),
                rotateY(rotateY(rotateY(arrL))), rotateZ(rotateZ(rotateZ(arrL))),
                rotateY(rotateX(rotateX(rotateX(arrL)))),
                rotateX(rotateY(rotateX(rotateX(arrL)))),
                rotateX(rotateX(rotateY(rotateX(arrL)))),
                rotateY(rotateY(rotateY(rotateX(arrL))))
        };
     return allPossibilities;
    };

    // all possible rotations parcel P
    public static int[][][][] parcelPRot() {
        int[][][][] allPossibilities = { arrP, rotateX(arrP), rotateY(arrP),
                rotateZ(arrP), rotateX(rotateX(arrP)),
                rotateY(rotateX(arrP)), rotateZ(rotateX(arrP)), rotateX(rotateY(arrP)),
                rotateY(rotateY(arrP)),
                rotateY(rotateZ(arrP)), rotateZ(rotateZ(arrP)),
                rotateX(rotateX(rotateX(arrP))),
                rotateY(rotateX(rotateX(arrP))), rotateZ(rotateX(rotateX(arrP))),
                rotateX(rotateY(rotateX(arrP))),
                rotateY(rotateY(rotateX(arrP))), rotateZ(rotateZ(rotateX(arrP))),
                rotateX(rotateX(rotateY(arrP))),
                rotateY(rotateY(rotateY(arrP))), rotateZ(rotateZ(rotateZ(arrP))),
                rotateY(rotateX(rotateX(rotateX(arrP)))),
                rotateX(rotateY(rotateX(rotateX(arrP)))),
                rotateX(rotateX(rotateY(rotateX(arrP)))),
                rotateY(rotateY(rotateY(rotateX(arrP))))
        };

        return allPossibilities;
    };

    // all possible rotations parcel T
    public static int[][][][] parcelTRot() {
        int[][][][] allPossibilities = { arrT, rotateX(arrT), rotateY(arrT), rotateZ(arrT),
                rotateY(rotateX(arrT)), rotateZ(rotateX(arrT)), rotateY(rotateZ(arrT)), rotateX(rotateX(arrT)),
                rotateX(rotateX(rotateX(arrT))), rotateY(rotateX(rotateX(arrT))),
                rotateZ(rotateX(rotateX(arrT))), rotateY(rotateY(rotateY(rotateX(arrT))))
        };

        return allPossibilities;
    };

    // test
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(parcelTRot()));
        // checkDuplicate(parcelTRot());
    }

    public static boolean checkDuplicate(int[][][][] parcelList) {
        for (int i = 0; i < parcelList.length; i++) {
            for (int j = 0; j < parcelList.length; j++) {
                if (i != j) {
                    if (parcelList[i] == parcelList[j]) {
                        System.out.println(i);
                        System.out.println(j);
                        return true;
                    }
                }
            }
        }
        System.out.println("good");
        return false;
    }
}
