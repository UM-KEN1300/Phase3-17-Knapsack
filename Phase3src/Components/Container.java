package Components;

/**
 * The Container class has several method for modifying the 3D interger array inside the class
 */
public class Container {
    private final int LENGTH = 33;
    private final int HEIGHT = 8;
    private final int WIDTH = 5;
    private int[][][] shape;

    // The following is to create only one object

    public Container() {
        shape = new int[LENGTH][HEIGHT][WIDTH];
        setDefault();
    }

    /**
     * Set everything in array as -1
     * 
     * @param shape
     */
    public void setDefault() {
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                for (int w = 0; w < shape[0][0].length; w++) {
                    shape[i][j][w] = -1;
                }
            }
        }
    }
    /**
     * Useful setters and getters for the container class
     */
    public int getHeight() {
        return HEIGHT;
    }
    public int getLength() {
        return LENGTH;
    }
    public int[][][] getShape() {
        return shape;
    }
    public int getWidth() {
        return WIDTH;
    }
    /**
     * Method which adds a parcel to the container 
     * @param Array3d the array representation of the parcel to be added 
     * @param x starting x coordinate to be added from
     * @param y starting y coordinate to be added from
     * @param z starting z coordinate to be added from
     * @param intID the integer to set the element in the 3d array as
     */    
    public void add(int[][][] Array3d, int x, int y, int z, int intID) {
        for (int i = 0; i < Array3d.length; i++) {
            for (int j = 0; j < Array3d[0].length; j++) {
                for (int k = 0; k < Array3d[0][0].length; k++) {
                    if (Array3d[i][j][k] == 1) {
                        shape[x + i][y + j][k + z] = intID;
                    }
                }
            }
        }
    }
    /**
     * Method which checks whether a parcel will collide with another parcel if it is added in those 
     * coordinates 
     * @param Array3d the array representation of the parcel to be added 
     * @param x starting x coordinate to check collision from
     * @param y starting y coordinate to check collision from
     * @param z starting z coordinate to check collision from
     */  
    public boolean checkAvailable(int[][][] Array3d, int x, int y, int z) {
        if(x+Array3d.length>getLength() || y+Array3d[0].length>getHeight() || z+Array3d[0][0].length>getWidth()) {
            return false;
        }
        for (int i = 0; i < Array3d.length; i++) { //Length
            for (int j = 0; j < Array3d[0].length; j++) { //Height
                for (int k = 0; k < Array3d[0][0].length; k++) { //Width
                    if (Array3d[i][j][k] == 1) {
                        if (shape[x + i][y + j][z + k] != -1) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * check each element in 3d array is "occupied" or not.
     * The default number is -1; if there is an element is -1,
     * the method will return false. It will only return true if all
     * element is not -1.
     * 
     * @param field
     * @return true for full case, else return false
     */
    public boolean checkFull(int[][][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                for (int j2 = 0; j2 < field[0][0].length; j2++) {
                    if (field[i][j][j2] == -1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * This method is used to remove a certain parcel in a specific loaction by
     * setting the target place to -1.
     * @param Array3d
     * @param x
     * @param y
     * @param z
     */
    public void remove(int[][][] Array3d, int x, int y, int z) {
        for (int i = 0; i < Array3d.length; i++) {
            for (int j = 0; j < Array3d[0].length; j++) {
                for (int k = 0; k < Array3d[0][0].length; k++) {
                    if (Array3d[i][j][k] == 1) {
                        shape[x + i][y + j][k + z] = -1;
                    }
                }
            }
        }
    }

    /**
     * Copy the 3D array in the field 
     * @return the address of new 3D array
     */
    public int [][][] copyOf (){
        int [][][] temp = new int [LENGTH][HEIGHT][WIDTH];
        for(int i=0; i<getLength(); i++){
            for(int j=0; j<getHeight(); j++){
                for(int k=0; k<getWidth(); k++){
                    temp[i][j][k]=shape[i][j][k];
                }
            }
        }
        return temp;
    }

    /**
     * Method which is used to see how many pieces is used based on the diffferent 
     * numberID in current object's 3D array.
     */
    public void checkUsedPieces(){
        int countA = 0, countB = 0, countC = 0, countL = 0, countP = 0, countT = 0;
        for(int i=0; i<getLength(); i++){
            for(int j=0; j<getHeight(); j++){
                for(int k=0; k<getWidth(); k++){
                    if(shape[i][j][k] == 0) countA++;
                    else if(shape[i][j][k] == 1) countB++;
                    else if(shape[i][j][k] == 2) countC++;
                    else if(shape[i][j][k] == 3) countL++;
                    else if(shape[i][j][k] == 4) countP++;
                    else if(shape[i][j][k] == 5) countT++;
                }
            }
        }
        System.out.println("The amout of A used is " + countA/16);
        System.out.println("The amout of B used is " + countB/24);
        System.out.println("The amout of C used is " + countC/27);
        System.out.println("The amout of L used is " + countL/5);
        System.out.println("The amout of P used is " + countP/5);
        System.out.println("The amout of T used is " + countT/5);
    }

    /**
     * Method which is used to see how many pieces is used based on the diffferent 
     * numberID in current object's 3D array.
     * 
     * @return String of the result
     */
    public String giveUsedPieces(){
        int countA = 0, countB = 0, countC = 0, countL = 0, countP = 0, countT = 0;
        for(int i=0; i<getLength(); i++){
            for(int j=0; j<getHeight(); j++){
                for(int k=0; k<getWidth(); k++){
                    if(shape[i][j][k] == 0) countA++;
                    else if(shape[i][j][k] == 1) countB++;
                    else if(shape[i][j][k] == 2) countC++;
                    else if(shape[i][j][k] == 3) countL++;
                    else if(shape[i][j][k] == 4) countP++;
                    else if(shape[i][j][k] == 5) countT++;
                }
            }
        }
        int sumValue = countA/16*3 + countB/24*4 + countC/27*5 + countL/5*3 + countP/5*4 + countT/5*5;
        String result = "<html>The amout of A used is " + countA/16 + "<br/> \nThe amout of B used is " + countB/24 + "<br/> The amout of C used is " + countC/27 +
        "<br/> The amout of L used is " + countL/5 + "<br/> The amout of P used is " + countP/5 + "<br/> The amout of T used is " + countT/5 + "<br/> The total default value is "
        + sumValue +"<html>";
        return result;
    }
}
