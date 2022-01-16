package Components;


/**
 *  This class has all of rotations of 2D LPT parcels and store them in a 3D array.    
 *  int[][][] a : a.length represents the length of all muntaions of the target parcel.
 *  parcelT2D[1] represents the first muntaiton of T parcel in a form of 2D array.
 */
public class parcelLPT2D {

    /**
     * all muntations of parcelT in 2D
     * @return the address of 3d array which has all muntations of parcelT in 2D
     */
    public static int[][][] parcelT2D(){
        int[][][] parcelT2D = {
            
            {
                {1, 1, 1}, 
                {0, 1, 0}, 
                {0, 1, 0}
            },
            {
                {1, 0, 0}, 
                {1, 1, 1}, 
                {1, 0, 0}
            },
            {
                {0, 1, 0}, 
                {0, 1, 0}, 
                {1, 1, 1}
            },	
            {
                {0, 0, 1}, 
                {1, 1, 1}, 
                {0, 0, 1}
            }

        };
        return parcelT2D;
    }

     /**
     * all muntations of parcelL in 2D
     * @return the address of 3d array which has all muntations of parcelL in 2D
     */
    public static int[][][] parcelL2D(){
        int[][][] parcelL2D = {
            {
                {1, 0},
                {1, 0}, 
                {1, 0}, 
                {1, 1}
            },
            {
                {0, 0, 0, 1}, 
                {1, 1, 1, 1}
            },
            {
                {1, 1}, 
                {0, 1}, 
                {0, 1}, 
                {0, 1}
            },
            {
                {1, 1, 1, 1}, 
                {1, 0, 0, 0}
            },
            {
                {0, 1}, 
                {0, 1}, 
                {0, 1}, 
                {1, 1}
            },
            {
                {1, 1, 1, 1}, 
                {0, 0, 0, 1}
            },
            {
                {1, 1}, 
                {1, 0}, 
                {1, 0}, 
                {1, 0}
            },
            {
                {1, 0, 0, 0}, 
                {1, 1, 1, 1}
            }

        };
        return parcelL2D;
    }

    /**
     * all muntations of parcelP in 2D
     * @return the address of 3d array which has all muntations of parcelP in 2D
     */
    public static int[][][] parcelP2D(){
        int[][][] parcelP2D = {
            {
                {1, 1}, 
                {1, 1}, 
                {1, 0}
            },
            {
                {1, 1, 0}, 
                {1, 1, 1}
            },
            {
                {0, 1}, 
                {1, 1}, 
                {1, 1}
            },
            {
                {1, 1, 1}, 
                {0, 1, 1}
            },
            {
                {1, 1}, 
                {1, 1}, 
                {0, 1}
            },
            {
                {1, 1, 1}, 
                {1, 1, 0}
            },
            {
                {1, 0}, 
                {1, 1}, 
                {1, 1}
            },
            {
                {0, 1, 1}, 
                {1, 1, 1}
            }


        };
        return parcelP2D;
    }




    
}