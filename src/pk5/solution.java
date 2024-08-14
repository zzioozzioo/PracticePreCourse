package pk5;

import java.util.ArrayList;

public class solution {

    public static int change = 0;

    public static void main(String[] args) {
        int money = 50237;
        System.out.println(solution(money));
    }

    public static ArrayList<Integer> solution(int money) {

        // 기능 구현
        // 1. 각 돈의 단위당 변환되는 갯수 구하기
        // 2. 리스트에 추가

        ArrayList<Integer> result = new ArrayList<>(9);

        change = money;
        result.add(calculate(50000));
        result.add(calculate(10000));
        result.add(calculate(5000));
        result.add(calculate(1000));
        result.add(calculate(500));
        result.add(calculate(100));
        result.add(calculate(50));
        result.add(calculate(10));
        result.add(calculate(1));

        return result;
    }

    private static int calculate(int amount) {
        int moneyType = 0;

        moneyType = change / amount;
        change -= moneyType * amount;

        return moneyType;
    }
}
