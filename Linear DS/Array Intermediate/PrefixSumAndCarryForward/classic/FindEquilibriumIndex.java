package classic;

/*
* You are given an array A of integers of size N.

Your task is to find the equilibrium index of the given array: The equilibrium index of an array is an index such that the sum of elements at lower indexes is equal to the sum of elements at higher indexes (without considering the equilibrium index)

* 1. If there are no elements that are at lower indexes or at higher indexes, then the corresponding sum of elements is considered as 0.
* 2. Array indexing starts from 0.
* 3. If there is no equilibrium index then return -1.
* 4. If there are more than one equilibrium indexes then return the minimum index.
*
* Input 1: A = [-7, 1, 5, 2, -4, 3, 0]
* Output 1: 3
*
* Input 2: A = [1, 2, 3]
* Output 2: -1
*
* */
public class FindEquilibriumIndex {
    public static int findEquilibriumIndex(int[] A) {
        int n = A.length;

        int[] prefixFromLeftToRight = new int[n];
        int[] prefixFromRightToLeft = new int[n];


        // System.out.println("prefixFromLeftToRight: ");
        prefixFromLeftToRight[0] = A[0];
        for(int i = 1; i < n; i++){
            prefixFromLeftToRight[i] = prefixFromLeftToRight[i - 1] + A[i];
            // System.out.print(prefixFromLeftToRight[i] + " ");
        }

        // System.out.println("prefixFromRightToLeft: ");
        prefixFromRightToLeft[n - 1] = A[n - 1];
        for(int i = n - 2; i >= 0 ; i--){
            prefixFromRightToLeft[i] = prefixFromRightToLeft[i + 1] + A[i];
            // System.out.print(prefixFromRightToLeft[i] + " ");
        }

        for(int i = 0; i < n; i++){
            if(i == 0 && prefixFromRightToLeft[1] == 0){ // If there are no elements that are at lower indexes or at higher indexes, then the corresponding sum of elements is considered as 0.
                return i;
            }else if(i == n - 1 && prefixFromLeftToRight[n - 2] == 0){ // If there are no elements that are at lower indexes or at higher indexes, then the corresponding sum of elements is considered as 0.
                return i;
            }else if((i > 0 && i < n - 1) && (prefixFromLeftToRight[i - 1] == prefixFromRightToLeft[i + 1])){ // The equilibrium index of an array is an index such that the sum of elements at lower indexes is equal to the sum of elements at higher indexes (without considering the equilibrium index)
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {9444,-2556,7937,8560,-8622,1440,-1076,5987,1419,996,-6530,4583,8890,-9127,8801,9034,-1579,-4395,1050,6527,5959,-1281,-8832,1363,-9296,-3791,-1316,4780,5836,-706,4650,-3278,8168,1329,-3756,7923,7291,-6320,-5414,-136,-9627,4609,-4626,-4655,-7457,-6424,-3573,-970,-5699,6139,-6334,2140,-3101,8478,1816,-4737,-8118,9277,8167,-4375,9711,-7224,-7777,2887,-9884,1275,-1116,-7240,5451,-3481,9449,5439,-9104,3421,6344,6995,7847,5166,2801,2217,1640,-8209,-8756,1586,121,-908,276,-8674,6499,818,6184,-5044,1257,-5923,7377,9104,1040,2587,-2676,-1172,-4221,-78,3611,4554,-4625,7293,-8077,1468,7559,9935,-7793,-1770,-9166,3555,3671,-7560,9206,-4264,-3848,2776,3998,-5961,-8423,-1288,-527,9968,1847,-6388,5042,8210,-4318,9107,525,-278,-7763,681,-9369,7563,5352,7773,-8169,3940,7088,-1872,-5870,7555,-3032,-2497,4134,-2995,3942,9092,-7739,6576,3955,-5175,-9007,5402,-6827,-3777,6057,-150,-4567,-94,5921,-9395,-6154,6385,-8445,8889,921,-7543,-4650,6461,-7468,-8693,328,1855,7683,495,-195,2554,7950,-1983,-5252,-5584,-9061,-5734,5425,-7041,9495,4647,1810,-5899,-1038,-5730,-6674,3818,-2146,-7694,-9968,-5853,9552,-164,7672,9711,-1281,1151,-1314,-9926,2948,-1280,686,1711,-624,-5462,-8864,2365,7361,-4694,2877,-339,-1560,-2100,-2342};

        System.out.println("\n\nEquilibrium Index: "+ findEquilibriumIndex(arr));
    }
}
