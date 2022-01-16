package Greedy;


import java.util.Arrays;
import Components.*;
/**
 * This class uses greedy method to solove the problem
 */
public class Greedy { // {A,B,C}
    private int[] parcelAmounts; // {2,3,5}
    private double[] values; // {3,5,5}
    private char[] sorted = { 'A', 'B', 'C', 'L', 'P', 'T'};
    public Container c = new Container();
    public double totalValue = 0.0;


    public Greedy(int[] parcelAmounts, double[] values) {
        this.parcelAmounts = parcelAmounts;
        this.values = values;
        
    }
    /**
     * The core method in the class, go though every point in the container to see if it is possible to place the most valuable parcel,
     * if it is not, the move on the second most valuable parcel.
     */
    public void algorithm() {
        char[] sorted = createSortedArray();
        for (int i = 0; i < c.getShape().length; i++) {
            for (int j = 0; j < c.getShape()[0].length; j++) {
                for (int k = 0; k < c.getShape()[0][0].length; k++) {
                    if (c.getShape()[i][j][k] == -1) {
                        for (int l = 0; l < sorted.length; l++) {
                            if (parcelAmounts[l] == 0) {
                                continue;
                            }
                            if (trytoPlace(sorted[l], i, j, k)) {
                                parcelAmounts[l] -= 1;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * The method has two parts, one is find the current target and get its data based on its parcel type, and check if it 
     * is possible to place that parcel.
     * @param ParcelType
     * @param x
     * @param y
     * @param z
     * @return true if it can be put in to container without any boundary and collision problem
     */
    private boolean trytoPlace(char ParcelType, int x, int y, int z) {
        int[][][][] iterator = new int[0][0][0][0];
        int color = 0;
        double temp = 0;
        switch (ParcelType) {
            case 'A':
                iterator = parcelsABC.parcelARot();
                color = 0;
                temp = values[0];
                break;
            case 'B':
                iterator = parcelsABC.parcelBRot();
                color = 1;
                temp = values[1];
                break;
            case 'C':
                iterator = parcelsABC.parcelCRot();
                color = 2;
                temp = values[2];
                break;
            case 'L':
                iterator = parcelsLPT.parcelLRot();
                color = 3;
                temp = values[0];
                break;
            case 'P':
                iterator = parcelsLPT.parcelPRot();
                color = 4;
                temp = values[1];
                break;
            case 'T':
                iterator = parcelsLPT.parcelTRot();
                color = 5;
                temp = values[2];
                break;
        }
        for (int i = 0; i < iterator.length; i++) {
            if (c.checkAvailable(iterator[i], x, y, z)) {
                c.add(iterator[i], x, y, z, color);
                totalValue += temp;
                temp = 0;
                return true;
            }
        }
        return false;
    }

    /**
     * The method is used to determine which parcel is most valuable and creates a sorted array based on that;
     * meanwhile, the value and amount order is also chaged as same as sorted array.
     * Bubble sort is used in this method since there is only a few of samples
     * @return the accending char array based on its values
     */
    private char[] createSortedArray() {
        double[] valPerVol = values.clone();
        char tempo;
        double temp;
        int temp2;
        for (int i = valPerVol.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (valPerVol[j] < valPerVol[j + 1]) {
                    temp = valPerVol[j];
                    tempo = sorted[j];
                    temp2 = parcelAmounts[j];
                    valPerVol[j] = valPerVol[j + 1];
                    sorted[j] = sorted[j + 1];
                    parcelAmounts[j] = parcelAmounts[j + 1];
                    valPerVol[j + 1] = temp;
                    sorted[j + 1] = tempo;
                    parcelAmounts[j + 1] = temp2;
                }
            }
        }

        return sorted;
    }
}