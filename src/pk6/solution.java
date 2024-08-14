package pk6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class solution {

    public static void main(String[] args) {
        String[][] forms = new String[][]
                {{"jm@email.com", "제이엠"},
                {"jason@email.com", "제이슨"},
                {"woniee@email.com", "워니"},
                {"mj@email.com", "엠제이"},
                {"nowm@email.com", "이제엠"}};
        System.out.println(solution(forms));
    }

    public static ArrayList<String> solution(String[][] forms) {

        // 기능 구현
        // 1. 배열을 돌며 같은 글자가 연속적으로 있는지 탐색(두 글자 이상)
        // 2. 중복이 있는 지원자의 이메일을 리스트에 추가
        // 3. 리스트 정렬(오름차순, 중복 제거)

        // 중복 제거 위한 HashSet
        HashSet<String> hashSet = new HashSet<>();

        for (int i = 0; i < forms.length; i++) {
            String name = forms[i][1];
            for (int j = i+1; j < forms.length - i; j++) {
                for (int k = 0; k < forms[i].length-1; k++) { // 닉네임을 두 글자씩 끊어서 탐색
                      String target = name.substring(k, k + 2);
                    if(forms[j][1].contains(target)) { // 중복이 있다면
                        hashSet.add(forms[i][0]);
                        hashSet.add(forms[j][0]);
                    }
                }
            }
        }

        ArrayList<String> result = new ArrayList<>();
        for (String s : hashSet) {
            result.add(s);
        }

        // 오름차순 정렬
        Collections.sort(result);

        return result;
    }
}
