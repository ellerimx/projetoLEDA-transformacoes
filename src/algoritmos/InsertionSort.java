package algoritmos;

import formatarTweet.Tweet;

public class InsertionSort {

    public static void sortByDate(Tweet[] array) {
        for (int i = 1; i < array.length; i++) {
            Tweet temp = array[i]; 
            int j = i - 1;
    
            while (j >= 0 && 
                   (temp.getYear() < array[j].getYear() || 
                   (temp.getYear() == array[j].getYear() && temp.getMonth() < array[j].getMonth()) ||
                   (temp.getYear() == array[j].getYear() && temp.getMonth() == array[j].getMonth() && temp.getDay() < array[j].getDay()))) {
                array[j + 1] = array[j];
                j--;
            }
            
            array[j + 1] = temp; 
        }
    }

    public static void sortByMentionedCount(Tweet[] array){
        for (int i = 1; i < array.length; i++){
            Tweet temp = array[i]; 
            int j = i - 1; 
            
            while (j >= 0 && array[j].getMentioned_person_count() < temp.getMentioned_person_count()){ 
                array[j+1] = array[j];
                j--; 
            }
            array[j+1] = temp;
        }
    }

    public static void sortByUser(Tweet[] array){
        for (int i = 1; i < array.length; i++){
            Tweet temp = array[i]; 
            int j = i - 1; 
            
            while (j >= 0 && (array[j].getUser().compareTo(temp.getUser())) > 0){ 
                array[j+1] = array[j];
                j--; 
            }
            array[j+1] = temp;
        }
    }

}

