package basics;

public class BasicOperations {
    public boolean isUpperCaseAlpha(char c){
        return (c >= 'A' && c <= 'Z');
    }
    public boolean isLowerCaseAlpha(char c){
        return (c >= 'a' && c <= 'z');
    }

    public char toUpperCaseAlpha(char c){
        if(isLowerCaseAlpha(c)) return (char)(c - 'a' + 'A');
        else return c;
    }

    public char toLowerCaseAlpha(char c){
        if(isUpperCaseAlpha(c)) return (char)(c - 'A' + 'a');
        else return c;
    }

    public boolean isTogglingCaseEquals(char c1, char c2){
        return
                (isUpperCaseAlpha(c1) && isLowerCaseAlpha(c2) && (toLowerCaseAlpha(c1) == c2))
             || (isLowerCaseAlpha(c1) && isUpperCaseAlpha(c2) && (toUpperCaseAlpha(c1) == c2));
    }
}
