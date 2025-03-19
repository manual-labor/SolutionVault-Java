/*
 카카오톡에서는 이모티콘을 무제한으로 사용할 수 있는 이모티콘 플러스 서비스 가입자 수를 늘리려고 합니다.
이를 위해 카카오톡에서는 이모티콘 할인 행사를 하는데, 목표는 다음과 같습니다.

    이모티콘 플러스 서비스 가입자를 최대한 늘리는 것.
    이모티콘 판매액을 최대한 늘리는 것.

1번 목표가 우선이며, 2번 목표가 그 다음입니다.

이모티콘 할인 행사는 다음과 같은 방식으로 진행됩니다.

    n명의 카카오톡 사용자들에게 이모티콘 m개를 할인하여 판매합니다.
    이모티콘마다 할인율은 다를 수 있으며, 할인율은 10%, 20%, 30%, 40% 중 하나로 설정됩니다.

카카오톡 사용자들은 다음과 같은 기준을 따라 이모티콘을 사거나, 이모티콘 플러스 서비스에 가입합니다.

    각 사용자들은 자신의 기준에 따라 일정 비율 이상 할인하는 이모티콘을 모두 구매합니다.
    각 사용자들은 자신의 기준에 따라 이모티콘 구매 비용의 합이 일정 가격 이상이 된다면, 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입합니다.

다음은 2명의 카카오톡 사용자와 2개의 이모티콘이 있을때의 예시입니다.
사용자 	비율 	가격
1 	    40 	    10,000
2 	    25 	    10,000
이모티콘 	가격
1 	        7,000
2 	        9,000

1번 사용자는 40%이상 할인하는 이모티콘을 모두 구매하고, 이모티콘 구매 비용이 10,000원 이상이 되면 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입합니다.
2번 사용자는 25%이상 할인하는 이모티콘을 모두 구매하고, 이모티콘 구매 비용이 10,000원 이상이 되면 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입합니다.

1번 이모티콘의 가격은 7,000원, 2번 이모티콘의 가격은 9,000원입니다.

만약, 2개의 이모티콘을 모두 40%씩 할인한다면, 1번 사용자와 2번 사용자 모두 1,2번 이모티콘을 구매하게 되고, 결과는 다음과 같습니다.
사용자 	구매한 이모티콘 	이모티콘 구매 비용 	    이모티콘 플러스 서비스 가입 여부
1 	    1, 2 	           9,600 	             X
2 	    1, 2 	           9,600 	             X

이모티콘 플러스 서비스 가입자는 0명이 늘어나고 이모티콘 판매액은 19,200원이 늘어납니다.

하지만, 1번 이모티콘을 30% 할인하고 2번 이모티콘을 40% 할인한다면 결과는 다음과 같습니다.
사용자 	구매한 이모티콘 	이모티콘 구매 비용 	이모티콘 플러스 서비스 가입 여부
1 	    2 	               5,400 	         X
2 	    1, 2 	           10,300 	         O

2번 사용자는 이모티콘 구매 비용을 10,000원 이상 사용하여 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입하게 됩니다.
따라서, 이모티콘 플러스 서비스 가입자는 1명이 늘어나고 이모티콘 판매액은 5,400원이 늘어나게 됩니다.

카카오톡 사용자 n명의 구매 기준을 담은 2차원 정수 배열 users, 이모티콘 m개의 정가를 담은 1차원 정수 배열 emoticons가 주어집니다. 이때, 행사 목적을 최대한으로 달성했을 때의 이모티콘 플러스 서비스 가입 수와 이모티콘 매출액을 1차원 정수 배열에 담아 return 하도록 solution 함수를 완성해주세요.
 */




package KAKAO;

import java.util.Arrays;

public class Emoticon {
    

    
        static int[] discountRate = {40, 30, 20, 10};
        static int[] combination;
        static int maxSubscribe = 0;
        static int maxSales = 0;
        
        void dfs(int index, int[][] users, int emoticons[]) {
            if(index == emoticons.length) {
                int totalPerchase = 0;
                int totalSubscribe = 0;
                
                for(int[] user : users) {
                    int preferDiscount = user[0];
                    int amountPromotion = user[1];
                    
                    int perchase=0;
                    
                    for(int emoticonIndex=0; emoticonIndex < emoticons.length; emoticonIndex++) {
                        if(combination[emoticonIndex] >= preferDiscount) {
                            perchase+= emoticons[emoticonIndex] / 100 * (100 - combination[emoticonIndex]);
                        }
                    }
                    
                    if(perchase >= amountPromotion) {
                        totalSubscribe++;
                    } else {
                        totalPerchase += perchase;
                    }
                }
                
                if(totalSubscribe > maxSubscribe) {
                    maxSubscribe = totalSubscribe;
                    maxSales = totalPerchase;
                } else if (totalSubscribe == maxSubscribe) {
                    if (totalPerchase > maxSales) {
                        maxSales = totalPerchase;
                    }
                }
            } else {
                for(int i=0; i<discountRate.length; i++) {
                    combination[index] = discountRate[i];
                    dfs(index+1, users, emoticons);
                }
            }
            
        }
    
        public int[] solution(int[][] users, int[] emoticons) {
            int[] answer = new int[2];
            combination = new int[emoticons.length];
            
            dfs(0,  users, emoticons);
            
            answer[0] = maxSubscribe;
            answer[1] = maxSales;
            return answer;
        }
    
        public static void main(String[] args) {
            int ang[][] = {{40,10000},{25,10000}};
            int e[] = {7000,9000};

            System.out.println(new Emoticon().solution(ang, e)[1]);
        }
    
}
