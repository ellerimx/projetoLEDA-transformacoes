package algoritmos;

import formatarTweet.Tweet;

public class HeapSort {
    
    public static void sortByDate(Tweet[] array) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapByDate(array, n, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            Tweet temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            heapByDate(array, i, 0);
        }
    }

    private static void heapByDate(Tweet[] array, int n, int i) {
        int largest = i; 
        int left = 2 * i + 1; 
        int right = 2 * i + 2; 
    
        if (left < n) {
            if (array[left].getYear() > array[largest].getYear() || 
                (array[left].getYear() == array[largest].getYear() && array[left].getMonth() > array[largest].getMonth()) ||
                (array[left].getYear() == array[largest].getYear() && array[left].getMonth() == array[largest].getMonth() && array[left].getDay() > array[largest].getDay())) {
                    largest = left;
            }
        }
    
        if (right < n) {
            if (array[right].getYear() > array[largest].getYear() || 
                (array[right].getYear() == array[largest].getYear() && array[right].getMonth() > array[largest].getMonth()) ||
                (array[right].getYear() == array[largest].getYear() && array[right].getMonth() == array[largest].getMonth() && array[right].getDay() > array[largest].getDay())) {
                    largest = right;
            }
        }
    
        if (largest != i) {
            Tweet swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;
    
            heapByDate(array, n, largest);
        }
    }

    public static void sortByMentionedCount(Tweet[] array) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapByMentionedCount(array, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            Tweet temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapByMentionedCount(array, i, 0);
        }
    }

    private static void heapByMentionedCount(Tweet[] array, int n, int i) {
        int largest = i;  
        int left = 2 * i + 1;  
        int right = 2 * i + 2;  

        if (left < n && array[left].getMentioned_person_count() < array[largest].getMentioned_person_count()) {
            largest = left;
        }

        if (right < n && array[right].getMentioned_person_count() < array[largest].getMentioned_person_count()) {
            largest = right;
        }

        if (largest != i) {
            Tweet swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            heapByMentionedCount(array, n, largest);
        }
    }

    public static void sortByUser(Tweet[] array) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapByUser(array, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            Tweet temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapByUser(array, i, 0);
        }
    }

    private static void heapByUser(Tweet[] array, int n, int i) {
        int largest = i; 
        int left = 2 * i + 1; 
        int right = 2 * i + 2; 

        if (left < n && array[left].getUser().compareTo(array[largest].getUser()) > 0) {
            largest = left;
        }

        if (right < n && array[right].getUser().compareTo(array[largest].getUser()) > 0) {
            largest = right;
        }

        if (largest != i) {
            Tweet swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;
            heapByUser(array, n, largest);
        }
    }

}
