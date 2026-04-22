package basics;

// Link: https://www.geeksforgeeks.org/problems/implement-atoi/1
public class AsciiToInteger {
    public int myAtoi(String s) {
        // code here
        char[] sCharArr = s.toCharArray();
        int i = 0;

        // 1. Ignore/Skip all leading whitespaces
        while(sCharArr[i] == ' '){
            i++;
        }

        // 2. Check Sign either '+' or '-' or implicitly '+'
        boolean isNeg = false;
        if(sCharArr[i] == '-'){
            isNeg = true;
            i++;
        }else if(sCharArr[i] == '+'){
            i++;
        }

        // Parse digit by digit while checking int overflow beforehand
        int result = 0;
        int digit;
        while(i < sCharArr.length && Character.isDigit(sCharArr[i])){
            digit = sCharArr[i] - '0';
            // NEVER DO CHECK INT OVERFLOW BY: if(result*10 + digit >= Integer.MAX_VALUE)
            // since result*10 + digit already results in overflow resulting garbage val
            // in order to avoid this, alternative is if((long)result*10 + digit >= Integer.MAX_VALUE)
            // but this typecasting won't work if the number is expected to be long - in that case it will be long overflow
            if(result >= (Integer.MAX_VALUE - digit)/10){
                if(isNeg)
                    return Integer.MIN_VALUE;
                else
                    return Integer.MAX_VALUE;
            }
            result = result*10 + digit;
            i++;
        }
        return isNeg ? -result : result;
    }
}
