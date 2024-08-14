package pk3;

public class solution {

    public static void main(String[] args) {
        int number = 103;
        System.out.println("result = " + solution(number));
    }

    public static int solution(int number) {

        // 기능 구현
        // 1. 1부터 number까지 탐색
        // 2. 3, 6, 9가 들어가는지 여부 체크
        // 2-1.
        // 3. 손뼉 치는 횟수 count

        int count = 0;
        for (int i=1; i<number+1; i++) {

            int n = i; // 원본 i 유지하기 위한 변수

            count = getCount(number, n, count, i);
        }
        return count;
    }

    private static int getCount(int number, int n, int count, int i) {
        while (true) {
            // 3, 6, 9 들어가는지 여부 체크
            if (n % 10 == 3 || n % 10 == 6 || n % 10 == 9) {
                count++;
            }
            if (n <= 10 || i == number + 1) {
                break;
            }
            // 10 이상일 때만 10으로 나누기
            n /= 10;
        }
        return count;
    }
}
