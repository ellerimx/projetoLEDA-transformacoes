package algoritmos;

import formatarTweet.Tweet;

public class CountingSort {

    public static void sortByDate(Tweet[] array) {
        int n = array.length;
        if (n == 0) return; 

        int[] dateValues = new int[n];
        for (int i = 0; i < n; i++) {
            int dateToNumber = array[i].getYear() * 10000 + array[i].getMonth() * 100 + array[i].getDay();
            dateValues[i] = dateToNumber;
        }

        int max = dateValues[0];
        for (int i = 1; i < n; i++) {
            if (dateValues[i] > max) {
                max = dateValues[i];
            }
        }

        int[] count = new int[max + 1];

        for (int i = 0; i < n; i++) {
            count[dateValues[i]]++;
        }

        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        Tweet[] sortedArray = new Tweet[n];

        for (int i = n - 1; i >= 0; i--) {
            int value = dateValues[i];
            sortedArray[count[value] - 1] = array[i];
            count[value]--;
        }
        for (int i = 0; i < n; i++) {
            array[i] = sortedArray[i];
        }
    }

    public static void sortByMentionedCount(Tweet[] array) {
        int n = array.length;
        if (n == 0) return;
    
        int max = array[0].getMentioned_person_count();
        for (int i = 1; i < n; i++) {
            if (array[i].getMentioned_person_count() > max) {
                max = array[i].getMentioned_person_count();
            }
        }
    
        int[] count = new int[max + 1];
        for (int i = 0; i < n; i++) {
            count[array[i].getMentioned_person_count()]++;
        }
    
        for (int i = max - 1; i >= 0; i--) {
            count[i] += count[i + 1];
        }

        Tweet[] sortedArray = new Tweet[n];
    
        for (int i = n - 1; i >= 0; i--) {
            int value = array[i].getMentioned_person_count();
            sortedArray[count[value] - 1] = array[i];
            count[value]--;
        }
    
        for (int i = 0; i < n; i++) {
            array[i] = sortedArray[i];
        }
    }

}