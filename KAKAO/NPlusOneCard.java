/*

당신은 1~n 사이의 수가 적힌 카드가 하나씩 있는 카드 뭉치와 동전 coin개를 이용한 게임을 하려고 합니다. 카드 뭉치에서 카드를 뽑는 순서가 정해져 있으며, 게임은 다음과 같이 진행합니다.

    처음에 카드 뭉치에서 카드 n/3장을 뽑아 모두 가집니다. (n은 6의 배수입니다.) 당신은 카드와 교환 가능한 동전 coin개를 가지고 있습니다.
    게임은 1라운드부터 시작되며, 각 라운드가 시작할 때 카드를 두 장 뽑습니다. 카드 뭉치에 남은 카드가 없다면 게임을 종료합니다. 뽑은 카드는 카드 한 장당 동전 하나를 소모해 가지거나, 동전을 소모하지 않고 버릴 수 있습니다.
    카드에 적힌 수의 합이 n+1이 되도록 카드 두 장을 내고 다음 라운드로 진행할 수 있습니다. 만약 카드 두 장을 낼 수 없다면 게임을 종료합니다.

예를 들어 n = 12, coin = 4이고 [3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4] 순서대로 카드를 뽑도록 카드 뭉치가 섞여 있습니다.

처음에 3, 6, 7, 2 카드 4장(= n/3)과 동전 4개(= coin)를 가지고 시작합니다. 다음 라운드로 진행하기 위해 내야 할 카드 두 장에 적힌 수의 합은 13(= n+1)입니다. 다음과 같은 방법으로 최대 5라운드까지 도달할 수 있습니다.

    1라운드에서 뽑은 카드 1, 10을 동전 두 개를 소모해서 모두 가집니다. 카드 3, 10을 내고 다음 라운드로 진행합니다. 이때 손에 남은 카드는 1, 2, 6, 7이고 동전이 2개 남습니다.
    2라운드에서 뽑은 카드 5, 9를 동전을 소모하지 않고 모두 버립니다. 카드 6, 7을 내고 다음 라운드로 진행합니다. 이때 손에 남은 카드는 1, 2고 동전이 2개 남습니다.
    3라운드에서 뽑은 카드 8, 12 중 동전 한 개를 소모해서 카드 12를 가집니다. 카드 1, 12을 내고 다음 라운드로 진행합니다. 이때 손에 남은 카드는 2이고 동전이 1개 남습니다.
    4라운드에서 뽑은 카드 11, 4 중 동전 한 개를 소모해서 카드 11을 가집니다. 카드 2, 11을 내고 다음 라운드로 진행합니다. 이때 손에 남은 카드와 동전은 없습니다.
    5라운드에서 카드 뭉치에 남은 카드가 없으므로 게임을 종료합니다.

처음에 가진 동전수를 나타내는 정수 coin과 카드를 뽑는 순서대로 카드에 적힌 수를 담은 1차원 정수 배열 cards가 매개변수로 주어질 때, 게임에서 도달 가능한 최대 라운드의 수를 return 하도록 solution 함수를 완성해 주세요.
 
coin 	cards 	                                                            result
4 	    [3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4] 	                        5
3 	    [1, 2, 3, 4, 5, 8, 6, 7, 9, 10, 11, 12] 	                        2
2 	    [5, 8, 1, 2, 9, 4, 12, 11, 3, 10, 6, 7] 	                        4
10 	    [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18] 	1

*/

package KAKAO;

import java.util.HashSet;

class Solution {

    boolean canMatchCards(HashSet<Integer> cards1, HashSet<Integer> cards2, int target) {
        for (int card : cards1) {
            if (cards2.contains(target - card)) {
                cards1.remove(card);
                cards2.remove(target - card);
                return true;
            }
        }
        return false;
    }

    public int solution(int coin, int[] cards) {

        int answer = 1;
        int cardsCount = cards.length;
        int targetSum = cardsCount + 1;

        HashSet<Integer> handleCards = new HashSet<>();
        HashSet<Integer> pickedCards = new HashSet<>();

        for (int i = 0; i < cardsCount / 3; i++) {
            handleCards.add(cards[i]);
        }

        // 카드를 두장 뽑습니다.
        for (int i = cardsCount / 3; i < cardsCount; i += 2) {
            
            pickedCards.add(cards[i]);
            pickedCards.add(cards[i+1]);

            if (canMatchCards(handleCards, handleCards, targetSum)) {

            } else if (canMatchCards(handleCards, pickedCards, targetSum) && coin > 0) {
                coin--;
            } else if (canMatchCards(pickedCards, pickedCards, targetSum) && coin > 1) {
                coin-=2;
            } else 
                break;
            answer++;
        }

        return answer;

    }

    public static void main(String[] args) {
        int[] cardTest = { 3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4 };
        System.out.println(new Solution().solution(2, cardTest));
    }
}