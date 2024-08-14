package pk4;

public class solution {

    public static void main(String[] args) {

        String word = "I love you";
        System.out.println("result = " + solution(word));
    }

    public static String solution(String word) {

        // 기능 구현
        // 1. 반대로 변환된 배열 생성
        // 2. 문자열 word 탐색
        // 2-1. 대소문자 판별
        // 2-2. 공백인지 문자인지 판별
        // 3. word의 각 문자 원본 사전 - 거꾸로 사전 매치
        // 4. 새로운 문자열에 추가

        // 새로운 문자열 StringBuilder 생성
        StringBuilder newString = new StringBuilder(word.length());

        // 문자열 word 탐색
        for(int i=0; i<word.length(); i++) {

            char targetChar = word.charAt(i);

            // 대소문자 판별
            boolean upper = isUpperOrLower(targetChar, i);

            // i번째 인덱스 문자 -> 문자열 변환
            String targetString = String.valueOf(targetChar);

            // 공백 or 문자 판별
            if (isCharOrBlank(targetString, newString)) continue;

            // 원본 사전 -> 거꾸로 사전 변환
            change(upper, targetString, newString);
        }

        return newString.toString();
    }

    private static boolean isUpperOrLower(char targetChar, int i) {
        boolean upper = Character.isUpperCase(targetChar);
        return upper;
    }

    private static boolean isCharOrBlank(String targetString, StringBuilder newString) {
        if (targetString.equals(" ")) {
            newString.append(" ");
            return true;
        }
        return false;
    }

    private static void change(boolean upper, String targetString, StringBuilder newString) {
        // 반대로 변환된 배열 생성
        String originalUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String reverseUpper = "ZYXWVUTSRQPONMLKJIHGFEDCBA";

        String originalLower = "abcdefghijklmnopqrstuvwxyz";
        String reverseLower = "zyxwvutsrqponmlkjihgfedcba";

        if (upper) { // 대문자인 경우
            changeOriginalToReverse(targetString, newString, originalUpper, reverseUpper);
        } else { // 소문자인 경우
            changeOriginalToReverse(targetString, newString, originalLower, reverseLower);
        }
    }

    private static void changeOriginalToReverse(String targetString, StringBuilder newString, String originalUpper, String reverseUpper) {
        // 오리지널 문자열에서 몇번째 인덱스인지 추출
        int index = originalUpper.indexOf(targetString);
        // 거꾸로 문자열에서 해당되는 문자 새로운 문자열에 추가
        newString.append(reverseUpper.charAt(index));
    }
}
