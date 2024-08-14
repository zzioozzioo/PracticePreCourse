package pk2;

public class solution {

    public static void main(String[] args) {

        String cryptogram = "browoanoommnaon";
        System.out.println("result = " + solution(cryptogram));
    }

    public static String solution(String cryptogram) {
        // 연속하는 중복 문자 삭제 규칙: 앞에서부터, 중복이 여러 개면 모두 제거

        // 기능 구현
        // 1. 문자열 처음부터 끝까지 탐색
        // 2. 중복 문자 체크
        // 3. 중복 문자 삭제

        while(true) {
            String newCryptogram = searchCryptogram(cryptogram);
            if (newCryptogram.equals(cryptogram)) break; // 탐색 끝
            cryptogram = newCryptogram;
        }

        return cryptogram;
    }

    private static String searchCryptogram(String cryptogram) {
        StringBuilder newCryptogram = new StringBuilder();
        int i = 0; // length()-1번 반복하기 위한 변수
        while (i < cryptogram.length()) {
            i = checkDupl(cryptogram, i, newCryptogram);
        }

        return newCryptogram.toString();
    }

    private static int checkDupl(String cryptogram, int i, StringBuilder newCryptogram) {
        if(i < cryptogram.length()-1 && cryptogram.charAt(i)== cryptogram.charAt(i +1)) {
            // 중복 발견한 경우
            i = deleteDupl(cryptogram, i);
        } else {
            newCryptogram.append(cryptogram.charAt(i));
            i++;
        }
        return i;
    }

    private static int deleteDupl(String cryptogram, int i) {
        while (i < cryptogram.length() - 1 && cryptogram.charAt(i) == cryptogram.charAt(i + 1)) {
            i++;
        }
        i++;
        return i;
    }
}
