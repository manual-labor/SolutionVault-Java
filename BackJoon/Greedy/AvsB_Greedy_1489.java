//https://www.acmicpc.net/problem/1489

/*

팀 A와 B가 대결을 하려고 한다. 각 팀에 속한 사람은 다른 팀에 속한 사람과 대결을 해야 한다. 두 팀에 속한 각 사람은 대결을 한 번씩 해야 한다. 대결의 승자는 2점을 획득하고, 무승부인 경우에는 1점을 획득한다.

팀 A에 속한 사람의 능력치는 A1, A2, ..., AN이고, 팀 B에 속한 사람의 능력치는 B1, B2, ..., BN이다. 대결은 능력치가 높은 사람이 이기며, 능력치가 같은 경우 비긴다.

두 팀의 능력치가 주어졌을 때, 팀 A가 얻을 수 있는 점수의 최댓값을 구해보자.
입력

첫째 줄에 팀에 속한 사람의 수 N이 주어진다. 둘째 줄에는 A1, A2, ..., AN이 주어지고, 셋째 줄에는 B1, B2, ..., BN이 주어진다.
출력

첫째 줄에 팀 A가 얻을 수 있는 점수의 최댓값을 출력한다.
제한

    1 ≤ N ≤ 50
    1 ≤ Ai, Bi ≤ 1,000

예제 입력 1

2
5 8
7 3

예제 출력 1

4

예제 입력 2

2
7 3
5 8

예제 출력 2

2
 */

package BackJoon.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class AvsB_Greedy_1489 {

    static int length = 0;
    static int maximumPoint;

    static ArrayList<Integer> A = new ArrayList<>();
    static ArrayList<Integer> B = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        length = Integer.parseInt(reader.readLine());

        A = new ArrayList<>();
        B = new ArrayList<>();

        String[] inputA = reader.readLine().split(" ");
        for (int i = 0; i < length; i++) {
            A.add(Integer.parseInt(inputA[i]));
        }

        String[] inputB = reader.readLine().split(" ");
        for (int i = 0; i < length; i++) {
            B.add(Integer.parseInt(inputB[i]));
        }

        Collections.sort(A);
        Collections.sort(B, (a, b) -> b - a);


        int point = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int playerA = A.get(i);
                int playerB = B.get(j);
                    if (playerB == 0)
                        continue;
                    if (playerA > playerB) {
                        point += 2;
                        A.set(i, 0);
                        B.set(j, 0);
                        break;
                    }
            }
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int playerA = A.get(i);
                if (playerA == 0)
                    continue;
                int playerB = B.get(j);
                if (playerB == 0)
                    continue;

                if (playerA == playerB) {
                    A.set(i, 0);
                    B.set(j, 0);
                    point++;
                    break;
                }
            }
        }

        System.out.println(point);
    }
}
