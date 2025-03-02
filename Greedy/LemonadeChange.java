
// https://leetcode.com/problems/lemonade-change/
public class LemonadeChange {

    public boolean lemonadeChange(int[] bills) {
        int five = 0;
        int ten = 0;

        for(int i = 0; i < bills.length; i++){
            if(bills[i] == 5)
                five++;
            else if(bills[i] == 10){
                if(five >= 1){ // cust pays 10 - lemonade cost 5 = change Rs. 5
                    five--;
                    ten++;
                } else{
                    return false;
                }
            }
            else if(bills[i] == 20){
                // greedily try to pay with 10 denomination combination first
                // because saving 5 denominations will be more helpful
                // multiple 5 can form 10 but 10 needs 5 change to repay back
                // and each lemonade costs 5 and not 10
                if(five >= 1 && ten >= 1){ // cust pays 20 - lemonade cost 5 = change Rs. 15 = 1 x Rs. 5 change + 1 x Rs. 10 change
                    five--;
                    ten--;
                } else if(five >= 3){ // cust pays 20 - lemonade cost 5 = change Rs. 15 = 3 x Rs. 5 change
                    five -= 3;
                } else{
                    return false;
                }
            }
        }
        return true;
    }
}
