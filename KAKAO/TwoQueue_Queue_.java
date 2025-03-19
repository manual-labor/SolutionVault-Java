// https://school.programmers.co.kr/learn/courses/30/lessons/118667

package KAKAO;
public class TwoQueue_Queue_ {

    static int getValueByIndex(int index, int[] queue1, int[] queue2) {
        int length = queue1.length;
        if (index > queue1.length * 2 - 1) {
            return queue1[index % length];
        } else if (index > queue1.length - 1) {
            return queue2[index % length];
        }

        return queue1[index];
    }

    public static int solution(int[] queue1, int[] queue2) {

        int count = 0;

        long queue1Sum = 0, queue2Sum = 0;
        int length = queue1.length;
        for (int i = 0; i < length; i++) {
            queue1Sum += queue1[i];
            queue2Sum += queue2[i];
        }

        int queue1Front = 0,queue2Front = length;
        while (queue1Sum >= 0 && queue2Sum >= 0 && count <= length * 4) {
            if (queue1Sum > queue2Sum) {
                int dequeue = getValueByIndex(queue1Front, queue1, queue2);
                queue1Sum -= dequeue;
                queue2Sum += dequeue;
                queue1Front++;
            } else if (queue2Sum > queue1Sum) {
                int dequeue = getValueByIndex(queue2Front, queue1, queue2);
                queue2Sum -= dequeue;
                queue1Sum += dequeue;
                queue2Front++;
            } else if (queue1Sum == queue2Sum) {
                return count;
            }
            count++;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] queue1 = { 1, 2,3,4,5,6,7 };
        int[] queue2 = { 1, 2,3,4,5,6,8 };
        System.out.println(solution(queue1, queue2));
    }
}
