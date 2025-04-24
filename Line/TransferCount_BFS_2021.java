package Line;

import java.util.*;

import java.io.*;

/*
 전략

 이 문제는 "최소 환승 비용"만 생각하면 됨

 그러므로
 준비물 : 1) 정거장이 어떤 노선을 지나는지 저장하는 리스트 2) 노선에 어떤 정거장이 있는지 리스트 3) 큐

 알고리즘
 1. 출발지를 포함하는 노선들을 가져오고 큐에 집어넣는다.
 2. 도착지를 포함하는 노선들을 가져오고 Set에 넣는다.
 3. 큐를 순환하며 도착지에 포함하는 노선이면 출력한다.
 */

public class TransferCount_BFS_2021 {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");

        int stationCount = Integer.parseInt(input[0]);
        int linesCount = Integer.parseInt(input[1]);

        // 정류장(key)를 지나가는 다른 정류장(value)
        HashMap<String, ArrayList<Integer>> stationToLines = new HashMap<String, ArrayList<Integer>>();
        ArrayList<List<String>> lines = new ArrayList<List<String>>();

        for (int i = 0; i < linesCount; i++) {
            String[] nodeInput = reader.readLine().split(" ");
            List<String> stations = new ArrayList<>();
            for (int j = 0; j < nodeInput.length - 1; j++) {
                stationToLines.computeIfAbsent(nodeInput[j], k -> new ArrayList<>()).add(i);
                stations.add(nodeInput[j]);
            }
            lines.add(stations);
        }

        String[] startAndEnd = reader.readLine().split(" ");

        String startStation = startAndEnd[0];
        String endStation = startAndEnd[1];

        if (!stationToLines.containsKey(startStation) || !stationToLines.containsKey(endStation)) {
            System.out.println("-1");
            return;
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] isVisited = new boolean[linesCount];
        HashSet<String> isVisitedStaions = new HashSet<>();

        for (int line : stationToLines.get(startStation)) {
            queue.offer(line);
            isVisited[line] = true;
        }

        List<Integer> endLines = stationToLines.get(endStation);

        HashSet<Integer> targetStation = new HashSet<>(endLines);

        int transferCount = 0;
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                int currentLine = queue.poll();
                if (targetStation.contains(currentLine)) {
                    System.out.println(transferCount);
                    return;
                }

                for (String station : lines.get(currentLine)) {
                    if (isVisitedStaions.add(station)) {
                        for (int line : stationToLines.get(station)) {
                            if (isVisited[line] == true)
                                continue;
                            queue.offer(line);
                            isVisited[line] = true;
                        }
                    }
                }
            }
            transferCount++;
        }

        System.out.println("-1");

    }
}
