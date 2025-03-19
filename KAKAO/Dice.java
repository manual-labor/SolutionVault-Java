/*
 https://school.programmers.co.kr/learn/courses/30/lessons/258709

A와 B가 n개의 주사위를 가지고 승부를 합니다. 주사위의 6개 면에 각각 하나의 수가 쓰여 있으며, 주사위를 던졌을 때 각 면이 나올 확률은 동일합니다. 각 주사위는 1 ~ n의 번호를 가지고 있으며, 주사위에 쓰인 수의 구성은 모두 다릅니다.

A가 먼저 n / 2개의 주사위를 가져가면 B가 남은 n / 2개의 주사위를 가져갑니다. 각각 가져간 주사위를 모두 굴린 뒤, 나온 수들을 모두 합해 점수를 계산합니다. 점수가 더 큰 쪽이 승리하며, 점수가 같다면 무승부입니다.

A는 자신이 승리할 확률이 가장 높아지도록 주사위를 가져가려 합니다.

다음은 n = 4인 예시입니다.
주사위 	구성
#1 	[1, 2, 3, 4, 5, 6]
#2 	[3, 3, 3, 3, 4, 4]
#3 	[1, 3, 3, 4, 4, 4]
#4 	[1, 1, 4, 4, 5, 5]

    예를 들어 A가 주사위 #1, #2를 가져간 뒤 6, 3을 굴리고, B가 주사위 #3, #4를 가져간 뒤 4, 1을 굴린다면 A의 승리입니다. (6 + 3 > 4 + 1)

A가 가져가는 주사위 조합에 따라, 주사위를 굴린 1296가지 경우의 승패 결과를 세어보면 아래 표와 같습니다.
A의 주사위 	승 	     무 	 패
#1, #2 	    596 	196 	504
#1, #3 	    560 	176 	560
#1, #4 	    616 	184 	496
#2, #3 	    496 	184 	616
#2, #4 	    560 	176 	560
#3, #4 	    504 	196 	596

A가 승리할 확률이 가장 높아지기 위해선 주사위 #1, #4를 가져가야 합니다.

주사위에 쓰인 수의 구성을 담은 2차원 정수 배열 dice가 매개변수로 주어집니다. 이때, 자신이 승리할 확률이 가장 높아지기 위해 A가 골라야 하는 주사위 번호를 오름차순으로 1차원 정수 배열에 담아 return 하도록 solution 함수를 완성해 주세요. 승리할 확률이 가장 높은 주사위 조합이 유일한 경우만 주어집니다. 

 */




package KAKAO;

public class Dice {
    static int[] combination;
    static int[][] dice;
    static int N;
    
    
    private static void generateCombinations(int startIndex, int currentDepth, int combinationLength) {
        // 기저 조건: 현재 깊이가 목표 깊이에 도달한 경우
        if (currentDepth == combinationLength) {
            // 현재 조합을 저장
           
            return;
        }
    
        // 가능한 값들을 순차적으로 탐색
        for (int i = startIndex; i < dice.length; i++) {
            combination[currentDepth] = i; // 조합에 현재 값을 추가
            generateCombinations(i + 1, currentDepth + 1, combinationLength); // 다음 깊이로 재귀 호출
        }
    }
    
    public int[] solution(int[][] dice) {
        int[] answer = {};
        return answer;
    }


    public static void main(String[] args) {
        int[][] dice = {{1, 2, 3, 4, 5, 6}, {3, 3, 3, 3, 4, 4}, {1, 3, 3, 4, 4, 4}, {1, 1, 4, 4, 5, 5}};
        N = dice.length;



        generateCombinations(0, 0, N/2);
    }
}
