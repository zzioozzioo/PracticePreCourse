package pk1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class solution {

    public static void main(String[] args) {

        int[] pobi = {131, 132};
        int[] crong = {197, 212};

        System.out.println("result = " + solution(pobi, crong));

    }

    public static int solution(int[] pobi, int[] crong) {
        // 포비 승: 1, 크롱 승: 2, 무승부: 0, 예외사항: -1

        // 예외사항!!
        // 배열[0] = 왼쪽 페이지 번호, 배열[1] = 오른쪽 페이지 번호가 아닌 경우

        Integer x = findException(pobi);
        Integer y = findException(crong);
        if (x != null || y != null) return x;

        int pobiMax = calculateNum(pobi);
        int crongMax = calculateNum(crong);

        return getResult(pobiMax, crongMax);

    }

    private static Integer findException(int[] member) {
        if(member[0]+1 != member[1]) { // 예외사항인 경우
            return -1;
        }
        return null;
    }

    private static int calculateNum(int[] member) {
        ArrayList<Integer> maxList = new ArrayList<>(4);
        int maxNum = 0;

        for(int i=0; i<2; i++) {
            // 각 자릿수의 합, 곱 구하기
            int plusResult = 0;
            int mulResult = 1;
            while(true) {
                int n = member[i]%10;
                plusResult += n;
                mulResult *= n;

                if(member[i] < 10) break;

                member[i] /= 10;
            }
            maxList.add(plusResult);
            maxList.add(mulResult);
        }

        return getMaxNum(maxList);
    }

    private static int getMaxNum(ArrayList<Integer> maxList) {
        int maxNum;
        maxNum = maxList
                .stream()
                .max(Comparator.comparing(x -> x))
                .orElseThrow(NoSuchElementException::new);

        return maxNum;
    }

    private static int getResult(int pobiMax, int crongMax) {
        if(pobiMax > crongMax) { // 포비 승
            return 1;
        }
        if(pobiMax < crongMax) { // 크롱 승
            return 2;
        }
        if(pobiMax == crongMax) { // 무승부
            return 0;
        }
        return -1;
    }
}
