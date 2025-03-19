package KAKAO;

import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class PersonalInfomation {
    public int[] solution(String today, String[] terms, String[] privacies) {
        HashMap<String, Integer> termsMap = new HashMap<>();
       
           
       String[] stringToday = today.split("\\.");
       LocalDate todayTime = LocalDate.of(Integer.parseInt(stringToday[0]) , Integer.parseInt(stringToday[1]), Integer.parseInt(stringToday[2]));
       
       for(int i=0; i<terms.length; i++) {
          String[] term = terms[i].split(" ");
           termsMap.put(term[0], Integer.parseInt(term[1]));
       }
       
       int count = 1;
       ArrayList<Integer> list = new ArrayList<>();
       for(String privacy : privacies) {
          String[] privacyString = privacy.split(" ");
          String[] stringPrivacyday = privacyString[0].split("\\.");
          LocalDate privacyTime = LocalDate.of(Integer.parseInt(stringPrivacyday[0]), Integer.parseInt(stringPrivacyday[1]), Integer.parseInt(stringPrivacyday[2]));
          

          long period =  ChronoUnit.MONTHS.between(privacyTime, todayTime);

          if(period >= termsMap.get(privacyString[1])) {
              list.add(count);
          }
            count++;
       }

       int[] answer = new int[list.size()];
       
       for(int i=0; i<list.size(); i++) {
           answer[i] = list.get(i);
       }

       return answer;
   }

    public static void main(String[] args) {
        String[] terms = {"A 6", "B 12", "C 3"};
        String[] privacies = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};
        new PersonalInfomation().solution("2022.05.19", terms, privacies);
    }
}