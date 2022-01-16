package Components;

/**
 *  This class has all of rotations of 3D ABC parcels and store them in a 4D array.    
 */
public class parcelsABC{
    // rotations for parcels A,B,C 

    // each line of each array is a different rotation

    /**
     * Method contains all 3d rotations of parcel A and store in a 4d array
     * @return the address of the 4D array
     */
    public static int[][][][] parcelARot(){
        // 3 rotations
        int[][][][] parcelA =
            {   {   // int[2][4][2]  
                    {{1,1},{1,1},{1,1},{1,1}},
                    {{1,1},{1,1},{1,1},{1,1}}  
                },
                {   // int[4][2][2]
                    {{1,1},{1,1}},
                    {{1,1},{1,1}},
                    {{1,1},{1,1}},
                    {{1,1},{1,1}}
                },
                {   // int[2][2][4]
                    {{1,1,1,1},{1,1,1,1}},
                    {{1,1,1,1},{1,1,1,1}}    
                }
                
            };

        return parcelA;
    }

    /**
     * Method contains all 3d rotations of parcel B and store in a 4d array
     * @return the address of the 4D array
     */
    public static int[][][][] parcelBRot(){
        // 6 rotations
        int[][][][] parcelB =
            {
                {   // int[2][3][4]
                    {{1,1,1,1},{1,1,1,1},{1,1,1,1}},
                    {{1,1,1,1},{1,1,1,1},{1,1,1,1}}
                },
                {   // int[2][4][3]
                    {{1,1,1},{1,1,1},{1,1,1},{1,1,1}},
                    {{1,1,1},{1,1,1},{1,1,1},{1,1,1}}
                },
                {   // int[4][3][2]
                    {{1,1},{1,1},{1,1}},
                    {{1,1},{1,1},{1,1}},
                    {{1,1},{1,1},{1,1}},
                    {{1,1},{1,1},{1,1}}
                },
                {   // int[3][2][4]
                    {{1,1,1,1},{1,1,1,1}},
                    {{1,1,1,1},{1,1,1,1}},
                    {{1,1,1,1},{1,1,1,1}}
                },
                {   // int[4][2][3]
                    {{1,1,1},{1,1,1}},
                    {{1,1,1},{1,1,1}},
                    {{1,1,1},{1,1,1}},
                    {{1,1,1},{1,1,1}}
                },
                {   // int[3][4][2]
                    {{1,1},{1,1},{1,1},{1,1}},
                    {{1,1},{1,1},{1,1},{1,1}},
                    {{1,1},{1,1},{1,1},{1,1}}
                }
            };

        return parcelB;
    }

    /**
     * Method contains all 3d rotations of parcel C and store in a 4d array
     * @return the address of the 4D array
     */
    public static int[][][][] parcelCRot(){
        // 1 rotation
        int[][][][] parcelC =
            {
                {   // int[3][3][3]
                    {{1,1,1},{1,1,1},{1,1,1}},
                    {{1,1,1},{1,1,1},{1,1,1}},
                    {{1,1,1},{1,1,1},{1,1,1}}
                }
            };

        return parcelC;
    }


}