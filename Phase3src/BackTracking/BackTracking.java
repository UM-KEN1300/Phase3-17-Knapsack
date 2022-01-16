package BackTracking;


import java.util.ArrayList;

import Components.*;

/**
 * The backtracking algorithm 
 */
public class BackTracking {
    public Container truck = new Container();
    public int[][][] field = truck.getShape();
    private double[] values;
    private final int PARCEL1VOL = 16;
    private final int PARCEL2VOL = 24;
    private final int PARCEL3VOL = 27;
    private char[] parcelTypes = {'A','B','C','L','P','T'};

    public BackTracking(int[] parcelAmounts, double[] values) {
        this.values = values;
        parcelTypes = createParcelABCArray(parcelAmounts[0], parcelAmounts[1], parcelAmounts[2], parcelAmounts[3], parcelAmounts[4], parcelAmounts[5]);
    }

    /**
     * The method shall be used after the algorithm !!! 
     * The method is used to calculate the total value of parcels in a container based on the numberID of the parcels.
     * The method uses six counter to count the frequency of numberID and calculate the value based on each parcels volume.
     * @return the value of all the parcels in the container
     */
    private double totalValue() {
        int countA = 0, countB = 0, countC = 0, countL = 0, countP = 0, countT = 0;
        
        for (int i = 0; i < truck.getShape().length; i++) {
            for (int j = 0; j < truck.getShape()[0].length; j++) {
                for (int k = 0; k < truck.getShape()[0][0].length; k++) {
                    if (truck.getShape()[i][j][k] == 0) {
                        countA++;
                    } else if (truck.getShape()[i][j][k] == 1) {
                        countB++;
                    } else if (truck.getShape()[i][j][k] == 2) {
                        countC++;
                    } else if(truck.getShape()[i][j][k] == 3){ 
                        countL++;
                    } else if(truck.getShape()[i][j][k] == 4) {
                        countP++;
                    } else if(truck.getShape()[i][j][k] == 5) {
                        countT++;
                    }
                }
            }
        }   

        return countA*values[0]/PARCEL1VOL + countB*values[1]/PARCEL2VOL + countC*values[2]/PARCEL3VOL + countL*values[3]/5 + countP*values[4]/5 + countT*values[5]/5;
    }

    /**
     * This method creates a char array based on number of parcels.
     * for example 3 Lparcels, 4 Pparcels, 5 Tparcels will result a array {'L','L','L','P','P','P','P','T','T','T','T','T'}
     * @param Ammount amount of target parcel
     * @return  a char array for bracktracking
     */
    private char[] createParcelABCArray(int aAmmount, int bAmmount, int cAmmount, int lAmmount, int pAmmount, int tAmmount) {
        ArrayList<Character> temp = new ArrayList<>();
        for (int i = 0; i < cAmmount; i++) {
            temp.add('C');
        }
        for (int i = 0; i < aAmmount; i++) {
            temp.add('A');
        }
        for (int i = 0; i < bAmmount; i++) {
            temp.add('B');
        }
        for (int i = 0; i < tAmmount; i++) {
            temp.add('T');
        }
        for (int i = 0; i < lAmmount; i++) {
            temp.add('L');
        }
        for (int i = 0; i < pAmmount; i++) {
            temp.add('P');
        }
        char[] toReturn = new char[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            toReturn[i] = temp.get(i);
        }
        return toReturn;
    }


    /**
     * A method recursively call itself and go though all the possiblities to find a ideal solution.
     * Apply the knowledge in phase 1. Add the parcels based on the order of the char array
     * @param parcelIndex
     * @return true if there is a solution
     */

    public boolean locateSearch(int parcelIndex) {
        if (truck.checkFull(field)) {
            return true;
        }
        if (parcelIndex == parcelTypes.length - 1) {
            return true;
        }
        int colorID = parcelColorID(parcelTypes[parcelIndex]);
        int[][][][] targetData = getParcelData(parcelTypes[parcelIndex]);
        for (int i = 0; i < targetData.length; i++) {
            int[][][] targetParcel = targetData[i];
            for (int j = 0; j < field.length; j++) {
                for (int l = 0; l < field[0].length; l++) {
                    for (int k = 0; k < field[0][0].length; k++) {
                        if (truck.checkAvailable(targetParcel, j, l, k)) {
                            truck.add(targetParcel, j, l, k, colorID);
                            if (locateSearch(parcelIndex + 1)) {
                                return true;
                            }
                            truck.remove(targetParcel, j, l, k);
                        }
                    }
                }
            }
        }
        return locateSearch(parcelIndex+1);
    }

    

    /**
     * The method is used to set up colorID
     * @param parcelType
     * @return  numberID/colorID based on the char input
     */
    public int parcelColorID(char parcelType) {
        int colorID = -1;
        switch (parcelType) {
            case 'A':
                colorID = 0;
                break;
            case 'B':
                colorID = 1;
                break;
            case 'C':
                colorID = 2;
                break;
            case 'L':
                colorID = 3;
                break;
            case 'P':
                colorID = 4;
                break;
            case 'T':
                colorID = 5;
                break;
        }
        return colorID;
    }

    /**
     * This method is used to get the data from Component package based on the char type
     * @param parcelType
     * @return  A 4D array with has all the permunitations of a specific type of parcel
     */
    public int[][][][] getParcelData(char parcelType) {
        int data[][][][] = null;
        switch (parcelType) {
            case 'A':
                data = parcelsABC.parcelARot();
                break;
            case 'B':
                data = parcelsABC.parcelBRot();
                break;
            case 'C':
                data = parcelsABC.parcelCRot();
                break;
            case 'L':
                data = parcelsLPT.parcelLRot();
                break;
            case 'P':
                data = parcelsLPT.parcelPRot();;
                break;
            case 'T':
                data = parcelsLPT.parcelTRot();
                break;
        }
        return data;
    }

    
}
