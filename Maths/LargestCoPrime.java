/*
* You are given two positive numbers A and B . You need to find the maximum valued integer X such that:
X divides A i.e. A % X = 0
X and B are co-prime i.e. gcd(X, B) = 1
* */
public class LargestCoPrime {
    public int cpFact(int A, int B) {
        int X = A;
        while(GCDAndLCM.gcd(X, B) != 1){
            X = X / GCDAndLCM.gcd(X, B);
        }
        return X;
    }
}
