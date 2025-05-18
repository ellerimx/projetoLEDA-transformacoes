package algoritmos;

import formatarTweet.Tweet;

public class SelectionSort {

    public static void sortByDate(Tweet[] array) {
        for (int i = 0; i < array.length; i++) {
            int menor = i;
            
            for (int j = i + 1; j < array.length; j++) {
                if (array[j].getYear() < array[menor].getYear() || 
                    (array[j].getYear() == array[menor].getYear() && array[j].getMonth() < array[menor].getMonth()) ||
                    (array[j].getYear() == array[menor].getYear() && array[j].getMonth() == array[menor].getMonth() && array[j].getDay() < array[menor].getDay())) {
                        menor = j;
                }
            }
            Tweet temp = array[menor];
            array[menor] = array[i];
            array[i] = temp;
        }
    }
    
    
    public static void sortByMentionedCount(Tweet[] array){
        for (int i = 0; i < array.length; i++){
            int menor = i;
            for (int j = i + 1; j < array.length; j++){
                if (array[j].getMentioned_person_count() > array[menor].getMentioned_person_count()){
                    menor = j;
                }
            }
            Tweet temp = array[menor];
            array[menor] = array[i];
            array[i] = temp;
        }
    }

    public static void sortByUser(Tweet[] array){
        for (int i = 0; i < array.length; i++){
            int menor = i;
            for (int j = i + 1; j < array.length; j++){
                if ((array[j].getUser().compareTo(array[menor].getUser())) < 0){
                    menor = j;
                }
            }
            Tweet temp = array[menor];
            array[menor] = array[i];
            array[i] = temp;
        }
    }

}
