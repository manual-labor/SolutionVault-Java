// https://school.programmers.co.kr/learn/courses/30/lessons/150367

/*
 당신은 이진트리를 수로 표현하는 것을 좋아합니다.

이진트리를 수로 표현하는 방법은 다음과 같습니다.

    이진수를 저장할 빈 문자열을 생성합니다.
    주어진 이진트리에 더미 노드를 추가하여 포화 이진트리로 만듭니다. 루트 노드는 그대로 유지합니다.
    만들어진 포화 이진트리의 노드들을 가장 왼쪽 노드부터 가장 오른쪽 노드까지, 왼쪽에 있는 순서대로 살펴봅니다. 노드의 높이는 살펴보는 순서에 영향을 끼치지 않습니다.
    살펴본 노드가 더미 노드라면, 문자열 뒤에 0을 추가합니다. 살펴본 노드가 더미 노드가 아니라면, 문자열 뒤에 1을 추가합니다.
    문자열에 저장된 이진수를 십진수로 변환합니다.

이진트리에서 리프 노드가 아닌 노드는 자신의 왼쪽 자식이 루트인 서브트리의 노드들보다 오른쪽에 있으며, 자신의 오른쪽 자식이 루트인 서브트리의 노드들보다 왼쪽에 있다고 가정합니다.


노드들을 왼쪽에 있는 순서대로 살펴보며 0과 1을 생성한 문자열에 추가하면 "0111010"이 됩니다. 이 이진수를 십진수로 변환하면 58입니다.

당신은 수가 주어졌을때, 하나의 이진트리로 해당 수를 표현할 수 있는지 알고 싶습니다.

이진트리로 만들고 싶은 수를 담은 1차원 정수 배열 numbers가 주어집니다. numbers에 주어진 순서대로 하나의 이진트리로 해당 수를 표현할 수 있다면 1을, 표현할 수 없다면 0을 1차원 정수 배열에 담아 return 하도록 solution 함수를 완성해주세요.
제한사항

    1 ≤ numbers의 길이 ≤ 10,000
        1 ≤ numbers의 원소 ≤ 1015

입출력 예
numbers 	result
[7, 42, 5] 	[1, 1, 0]
[63, 111, 95] 	[1, 1, 0]
 */

package KAKAO;


class Solution {


    static int getVoidedLength (int number) {
         int result = 1;

        while(result < number) {
            result = (result * 2) + 1;
        }

        return result;
    }

    static boolean isTree (String binary, int left, int right, boolean parentValue) {
       int mid = (left+right)/2;

        boolean isMidHasValue = binary.charAt(mid) == '1' ? true : false;

        if(parentValue == false && isMidHasValue == true) {
            return false;
        }

        if(left != right)
            return isTree(binary, left, mid-1, isMidHasValue) && isTree(binary, mid+1, right, isMidHasValue);

        return true;

    }
    public int[] solution(long[] numbers) {
        int[] result = new int[numbers.length];

        for(int i=0; i<numbers.length; i++) {
          String binary = Long.toBinaryString(numbers[i]);
            int needToVoid = getVoidedLength(binary.length());
            StringBuilder sb = new StringBuilder();


            for(int j=binary.length(); j<needToVoid; j++ ) {
                sb.append("0");
            }

            sb.append(binary);
            binary = sb.toString();

            if(binary.charAt(binary.length()/2) == '0') {
                result[i] = 0;
                continue;
            }

            result[i] = isTree(binary, 0, binary.length()-1, true) ? 1 : 0 ;
        }
        return result;
    }
}

