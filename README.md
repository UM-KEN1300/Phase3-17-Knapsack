# Phase3-17-Knapsack
Group 17's ReadMe file for phase 3: - 

Yaonan Hu i6285725
Rafali Azzam Afran i6289152
Alex Balan i6276570 
Ruben de Lope Tudanca i6273604 
Selma Rimmelzwaan i6282600 
Anna Nowowiejska i6289598

-----------------------------------------------------------------------------
To succesfully run our code after downloading our repository, please proceed to the Main class and run it. This will take you into the 
MainMenu page in which you can select to either access our answers for the research questions posed to us in the Modules section which 
were: -

A- Given a list of cubed parcels (Type A, Type B, and Type C) with sizes 1.0x1.0x2.0, 1.0x1.5x2.0, and 1.5x1.5x1.5, is it possible to fit a container with dimensions 16.5x4.0x2.5 without filling any gaps?
B- What is the highest possible value that can be yielded in the container using only parcel Types A, B, and C if they have values 3, 4, and 5?
C- Given a list of Pentomino Parcels (Type L, Type P, and Type T) with each polycube having dimensions 0.5x0.5x0.5, is it possible to fit a container with dimensions 16.5x4.0x2.5 without filling any gaps? 
D- What is the highest possible value which can be yielded in the container using only parcel Types L, P, and T if they have values 3, 4, and 5?

We also added a General mode which will alow you to input however many parcels of each type they want alongside determining
their values and run our Greedy, Backtracking or Genetic Algorithms. As our DLX and Divide and Conquer algorithms works differently 
from these algorithms we have seperate modes for these algorithms.

The algorithmX mode is added which will allow you to run and fill the container using our DLX algorithm with some customable features: -

-Parcel Type: Select whether to fill container with ABC or LPT parcels 

-Find First Solution: If LPT is selected, select whether or not to stop and display the solution when the first solution is found or 
to find the best solution according to a predetermined time limit 

-Time Limit: If parcel type LPT is selected and we want to find the best solution or parcel type ABC is selected, select the time limit in 
miliseconds    

The divide and conquer mode will allow you to run the divide and conquer algorithm and will display the solution.
