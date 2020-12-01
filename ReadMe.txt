Yixuan Liu a1733854
Junwei Li a1710640
Xuejiao Mu a1685169
Fang Xu a1713591
a1733854@student.adelaide,edu.au

For this part in our assignment, we have implemented two algorithms besides the randomly search, which are the climb mountain and the genetic algorithm. We have packed these two algorithms in the same project upon Mahoud¡¯s project, therefore it becomes easy to run our code. You only need set the parameter than you can run different algorithms. For both two algorithms, we can set the experiment times, the corresponded parameter is int times = 1000; // experiment times in class Guioptimiser, before run the application, you need select which algorithm you would like to run, the corresponded parameter is int algorithm = 3; // 1 means Random Search, 2 means Genetic Algorithm, 3 means Climb Mountain which is also in class Guioptimiser. The energy consumption is calculated upon the energy consumption we calculated  from exercise 2, and the energy consumption results in my code is the parameter fitness, therefore, the better fitness is always the smaller one, the current best fitness( energy consumption) would be printed in this type current best fitness: 54.09287 , the unit is mA. For judging the Euclidean Distance, I define the judgeEuclideanDistance method in the class GA, the GA class also includes the main logic code of Genetic Algorithm and the Climb Mountain Algorithm. The best solution would be stored in the file bestSolution.csv

For the climb mountain algorithm, we can set the change interval with the parameter int stepLength = 8; // for climb mountain in class Guioptimiser. 

For the Genetic Algorithm, we did not use the Crossover, the method we used is choosing  the best solution as the parent from the last generation, and mutate the parent to generate the offsprings. We can set how many kids in each generation with the parameter int kidsNum = 5; // number of kids( for Genetic Algorithm). 

Please notice that before run the code, set the route: newsaveToCSV("C://Users//84464//Desktop//sbse//sbse-as02-cust//bestSolution.csv",
And File image1 = new File("C://Users//84464//Desktop//sbse//sbse-as02-cust//" + pathTemp); first.