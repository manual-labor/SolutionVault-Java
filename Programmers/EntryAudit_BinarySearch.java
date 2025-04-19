/*
https://school.programmers.co.kr/learn/courses/30/lessons/43238?language=java


 n명이 입국심사를 위해 줄을 서서 기다리고 있습니다. 각 입국심사대에 있는 심사관마다 심사하는데 걸리는 시간은 다릅니다.

처음에 모든 심사대는 비어있습니다. 한 심사대에서는 동시에 한 명만 심사를 할 수 있습니다. 가장 앞에 서 있는 사람은 비어 있는 심사대로 가서 심사를 받을 수 있습니다. 하지만 더 빨리 끝나는 심사대가 있으면 기다렸다가 그곳으로 가서 심사를 받을 수도 있습니다.

모든 사람이 심사를 받는데 걸리는 시간을 최소로 하고 싶습니다.

입국심사를 기다리는 사람 수 n, 각 심사관이 한 명을 심사하는데 걸리는 시간이 담긴 배열 times가 매개변수로 주어질 때, 모든 사람이 심사를 받는데 걸리는 시간의 최솟값을 return 하도록 solution 함수를 작성해주세요.
제한사항

    입국심사를 기다리는 사람은 1명 이상 1,000,000,000명 이하입니다.
    각 심사관이 한 명을 심사하는데 걸리는 시간은 1분 이상 1,000,000,000분 이하입니다.
    심사관은 1명 이상 100,000명 이하입니다.

입출력 예
n 	times 	return
6 	[7, 10] 	28

https://born2bedeveloper.tistory.com/40
 */
package Programmers;

import java.util.*;

public class EntryAudit_BinarySearch {

    public static long solution(int n, int[] times) {
        long answer = 0;

        Arrays.sort(times);

        long low = 0, high = times[times.length - 1] * (long) n; // 0~최댓값

        while (low <= high) {
            long mid = (low + high) / 2;

            long canProcess = 0;
            for (int i = 0; i < times.length; i++) {
                /*
                 * 각 심사대 별로 주어진 시간 mid동안 최대 몇명의 사람을 심사할 수 있는지 합산한다.
                 * (ex. mid = 10, times = {2, 3, 4}인 경우, 10 / 2 + 10 / 3 + 10 / 4로 총 5+3+2= 10명 가능
                 */
                canProcess += mid / times[i];
            }

            if (canProcess < n) {
                low = mid + 1;
            } else {
                high = mid - 1;
                answer = mid;
            }
        }

        return answer;
    }

    public static void main(String[] args) {

        int n = 6;
        int times[] = { 7, 10 };

        Arrays.sort(times);

        solution(n, times);
    }
}
