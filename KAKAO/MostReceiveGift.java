/*
 
    두 사람이 선물을 주고받은 기록이 있다면, 이번 달까지 두 사람 사이에 더 많은 선물을 준 사람이 다음 달에 선물을 하나 받습니다.
        예를 들어 A가 B에게 선물을 5번 줬고, B가 A에게 선물을 3번 줬다면 다음 달엔 A가 B에게 선물을 하나 받습니다.
    두 사람이 선물을 주고받은 기록이 하나도 없거나 주고받은 수가 같다면, 선물 지수가 더 큰 사람이 선물 지수가 더 작은 사람에게 선물을 하나 받습니다.
        선물 지수는 이번 달까지 자신이 친구들에게 준 선물의 수에서 받은 선물의 수를 뺀 값입니다.
            예를 들어 A가 친구들에게 준 선물이 3개고 받은 선물이 10개라면 A의 선물 지수는 -7입니다. B가 친구들에게 준 선물이 3개고 받은 선물이 2개라면 B의 선물 지수는 1입니다. 
            만약 A와 B가 선물을 주고받은 적이 없거나 정확히 같은 수로 선물을 주고받았다면, 다음 달엔 B가 A에게 선물을 하나 받습니다.
            만약 두 사람의 선물 지수도 같다면 다음 달에 선물을 주고받지 않습니다.

    위에서 설명한 규칙대로 다음 달에 선물을 주고받을 때, 당신은 선물을 가장 많이 받을 친구가 받을 선물의 수를 알고 싶습니다.
 */


package KAKAO;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MostReceiveGift {

    public int solution (String[] friends, String[] gifts) {
        int[][] giftGraph = new int[friends.length][friends.length];
        HashMap<String,Integer> giftIndex = new HashMap<>();
        HashMap<String, Integer> nextMonthGift = new HashMap<>();
        List friendList = Arrays.asList(friends);


        for(String gift : gifts) {
            String person[] = gift.split(" ");
            String from = person[0];
            String to = person[1];

            giftGraph[friendList.indexOf(from)][friendList.indexOf(to)] += 1;
            giftIndex.put(from, giftIndex.getOrDefault(from, 0)+1);
            giftIndex.put(to, giftIndex.getOrDefault(to, 0)-1);
        }


        System.out.println("선물 리스트");
        for( int i = 0; i < friends.length; i++ ){
            for(int j = 0; j < friends.length; j++){
                System.out.print(giftGraph[i][j] + " ");
            }
            System.out.println();
        }

        for( String friend : friends ){
            for(int receivedFriendIndex = 0; receivedFriendIndex<friendList.size(); receivedFriendIndex++){
                int friendIndex = friendList.indexOf(friend);

                //자신 제외
                if(friendIndex == receivedFriendIndex) continue;

                if(giftGraph[friendIndex][receivedFriendIndex] > giftGraph[receivedFriendIndex][friendIndex]) {
                    nextMonthGift.put(friend, nextMonthGift.getOrDefault(friend, 0)+1);
                } else if ( giftGraph[friendIndex][receivedFriendIndex] == giftGraph[receivedFriendIndex][friendIndex])
                if ((giftIndex.getOrDefault(friend,0)  > giftIndex.getOrDefault(friendList.get(receivedFriendIndex),0))) {
                    nextMonthGift.put(friend, nextMonthGift.getOrDefault(friend, 0)+1);
                }
            }
        }

        System.out.println(nextMonthGift.size());

        if(nextMonthGift.size() == 0)
        return 0;
        else 
        return Collections.max(nextMonthGift.values());
    }
    
    public static void main(String[] args) {
        String[] ang = {"a", "b", "c"};
        String[] kimozi = {"a b", "b a", "c a", "a c", "a c", "c a"};
        System.out.println(new MostReceiveGift().solution(ang, kimozi));
    }
}
