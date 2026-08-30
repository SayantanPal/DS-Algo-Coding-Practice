public class NoOfSetBits {

    public int numSetBits(int A) {
        int countOfSetBits = 0;
        for(int i = 0; i < 32; i++){
            if( (A & (1 << i)) != 0){
                countOfSetBits++;
            }
        }
        return countOfSetBits;
    }
}
