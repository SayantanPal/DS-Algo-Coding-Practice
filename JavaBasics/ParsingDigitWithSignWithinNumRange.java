
public class ParsingDigitWithSignWithinNumRange {

    public int myAtoi(String s) {
        int i = 0;

        // Skip whitespace
        while (i < s.length() && s.charAt(i) == ' ') // s = s.trim(); // allocates a new String - slow
            i++;

        // Check for empty str after whitespace
        if(i == s.length()) return 0; //if(s.isEmpty()) return 0;


        // Check sign
        boolean isNeg = false;
        if(s.charAt(i) == '-'){
            isNeg = true;
            i++;
        }else if(s.charAt(i) == '+'){
            i++;
        }

        /*
        StringBuilder sb = new StringBuilder();
        while(i < s.length()){
            if(!Character.isDigit(s.charAt(i))){
                break;
            }
            sb.append(s.charAt(i));
            i++;
        }
        String actualNo = sb.toString();
        if(actualNo.isEmpty()) return 0;
        */

        // Check digits
        /*
        int j = 0, result = 0;
        while(j < actualNo.length()){
            int digit =  actualNo.charAt(j) - '0'; // Integer.parseInt(String.valueOf(actualNo.charAt(j)));
            if(result > (Integer.MAX_VALUE - digit) / 10){ // check before overflowing
                return isNeg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            // rebuild no digit by digit within integer range
            result = result*10 + digit;
            j++;
        }
    */
        int result = 0;
        while(i < s.length() && Character.isDigit(s.charAt(i))){
            int digit =  s.charAt(i) - '0'; // Integer.parseInt(String.valueOf(actualNo.charAt(j)));
            if(result > (Integer.MAX_VALUE - digit) / 10){ // check before overflowing
                return isNeg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            result = result*10 + digit;
            i++;
        }
        return isNeg ? -result : result;
    }
}
