import java.util.ArrayList;
import java.util.List;

// Link: https://neetcode.io/problems/string-encode-and-decode/question
public class Day25EncoderDecoderString {

    public String encode_v2(List<String> strs) {
        StringBuilder encodedStr = new StringBuilder();
        for(String str: strs){
            encodedStr.append(str.length());
            encodedStr.append("#");
            encodedStr.append(str);
        }
        return encodedStr.toString();
    }

    public List<String> decode_v2(String str) {
        List<String> result = new ArrayList<>();
        int i = 0;
        while (i < str.length()) {
            int hashIdx = str.indexOf('#', i);
            int len = Integer.parseInt(str.substring(i, hashIdx));
            result.add(str.substring(hashIdx + 1, hashIdx + 1 + len));
            i = hashIdx + 1 + len;
        }
        return result;
    }





    public static final String EMPTY_STRING = "EMPTY_STRING";
    public static final String DELIMITER = "DELIMITER";
    public static final String EMPTY_LIST = "EMPTY_LIST";

    public String encode_v1(List<String> strs) {
        StringBuilder encodedStr = new StringBuilder();
        if(strs.isEmpty()) return EMPTY_LIST;
        for(String str: strs){
            if(str == ""){
                encodedStr.append(EMPTY_STRING);
            }else{
                encodedStr.append(str);
            }
            encodedStr.append(DELIMITER);
        }
        return encodedStr.toString();
    }

    public List<String> decode_v1(String str) {
        if(str.equals(EMPTY_LIST)) return new ArrayList<>();
        String[] decodedStrs = str.split(DELIMITER);
        List<String> decodedStrsList = new ArrayList<>();
        for(String decodedStr: decodedStrs){
            if(decodedStr.equals(EMPTY_STRING)){
                decodedStrsList.add("");
            }else{
                decodedStrsList.add(decodedStr);
            }
        }
        return decodedStrsList;
    }
}
