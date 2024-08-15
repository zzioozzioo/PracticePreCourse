package pk7;

import java.util.*;

public class solution {

    public static void main(String[] args) {
        String user = "mrko";
        String[][] friends = {{"donut", "andole"}, {"donut", "jun"},
                {"donut", "mrko"}, {"shakevan", "andole"},
                {"shakevan", "jun"}, {"shakevan", "mrko"}};
        String[] visitors = {"bedi", "bedi", "donut", "bedi", "shakevan"};

        System.out.println(solution(user, friends, visitors));

    }

    public static List<String> solution(String user, String[][] friends, String[] visitors) {

        // 기능 구현
        // 1. 함께 아는 친구의 수 계산
        // 1-1. user이 포함된 배열을 찾기
        // 1-2. 해당 배열에서 user와 친구인 member가 포함된 다른 배열 탐색
        // 1-3. 해당 배열에서 member이 아닌 friends에게 +10점
        // 2. 타임 라인에 방문한 횟수 계산
        // 2-1. visitors 배열에 있는 member들에게 +1
        // 3. 점수 높은 순(내림차순) -> 이름순(오름차순) 정렬

        // 해시맵 선언 및 초기화
        HashMap<String, Integer> hashMap = makePeopleMap(friends, visitors);

        // user와 친구인 member 탐색
        searchUserFriendMember(user, friends, hashMap);


        // 타임 라인에 방문한 횟수 당 +1점
        scoreToVisitTimeline(visitors, hashMap);

        // 추천 점수 == 0인 경우는 제외
        removeZeroScore(hashMap);

        // value값 기준으로 내림차순 정렬
        List<Map.Entry<String, Integer>> entryList = sortByScore(hashMap);

        // value값을 리스트 형식으로 리턴
        List<String> resultList = convertMapToList(entryList);

        return resultList;
    }


    private static HashMap<String, Integer> makePeopleMap(String[][] friends, String[] visitors) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String[] friend : friends) {
            hashMap.put(friend[0], 0);
            hashMap.put(friend[1], 0);
        }
        for (String visitor : visitors) {
            hashMap.put(visitor, 0);
        }
        return hashMap;
    }

    private static void searchUserFriendMember(String user, String[][] friends, HashMap<String, Integer> hashMap) {
        String targetMember = "";
        for (String[] friend : friends) {
            if (friend[0].equals(user)) {
                targetMember = friend[1];
            } else if(friend[1].equals(user)) {
                targetMember = friend[0];
            }
            // member와 친구인 people 탐색
            searchMemberFriendPeople(user, friends, targetMember, hashMap);
        }
    }

    private static void searchMemberFriendPeople(String user, String[][] friends, String targetMember, HashMap<String, Integer> hashMap) {
        String targetPerson = "";
        for (String[] s : friends) {
            if (s[0].equals(targetMember)) {
                targetPerson = s[1];
            } else if (s[1].equals(targetMember)) {
                targetPerson = s[0];
            }
            scoreToUserFriend(user, hashMap, targetPerson);
        }
    }

    private static void scoreToUserFriend(String user, HashMap<String, Integer> hashMap, String targetPerson) {
        int plusScore = 10;

        if (!targetPerson.equals(user) && targetPerson != "") { // 사용자 본인인 경우는 제외
            // 함께 아는 친구의 수 1명 당 +10점
            int oldScore = hashMap.get(targetPerson);
            int newScore = oldScore + plusScore;
            hashMap.replace(targetPerson, newScore);
        }
    }

    private static void scoreToVisitTimeline(String[] visitors, HashMap<String, Integer> hashMap) {
        int plusScore = 1;

        for (String visitor : visitors) {
            int oldScore = hashMap.get(visitor);
            int newScore = oldScore + plusScore;
            hashMap.replace(visitor, newScore);
        }
    }

    private static void removeZeroScore(HashMap<String, Integer> hashMap) {
        Iterator<Map.Entry<String, Integer>> entries = hashMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Integer> entry = entries.next();
            if (entry.getValue() == 0) {
                hashMap.remove(entry.getKey());
            }
        }
    }

    private static List<Map.Entry<String, Integer>> sortByScore(HashMap<String, Integer> hashMap) {
        List<Map.Entry<String, Integer>> entryList = new LinkedList<>(hashMap.entrySet());
        entryList.sort(((o1, o2) -> hashMap.get(o2.getKey()) - hashMap.get(o1.getKey())));
        return entryList;
    }

    private static List<String> convertMapToList(List<Map.Entry<String, Integer>> entryList) {
        List<String> resultList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : entryList) {
            resultList.add(entry.getKey());
        }
        return resultList;
    }
}
