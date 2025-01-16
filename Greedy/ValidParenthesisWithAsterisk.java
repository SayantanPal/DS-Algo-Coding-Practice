public class ValidParenthesisWithAsterisk {

    public static boolean recursion(int i, String s, int counter){

        if(counter < 0) return false; // if in between scanning, any moment counter becomes -ve, then it can never be balanced even if counter becomes 0

        if(i == s.length()){
            if(counter == 0) return true; // if any one pattern matches the valid balance, then true
            else if(counter > 0) return false; // string ends but still the counter is not balanced
        }

        if(s.charAt(i) == '(')
            return recursion(i + 1, s, counter + 1);
        else if(s.charAt(i) == ')')
            return recursion(i + 1, s, counter - 1);
        else
            return recursion(i + 1, s, counter + 1) || recursion(i + 1, s, counter - 1) || recursion(i + 1, s, counter);
    }

    public static boolean checkValidString(String s) {
        return recursion(0, s, 0);
    }

    public static void main(String[] args){
        System.out.println(checkValidString("("));
    }
}
