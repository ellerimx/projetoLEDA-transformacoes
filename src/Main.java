import algoritmos.*;
import formatarTweet.Tweet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    
    // mudar o caminho do diretorio do database
    public static final String DIR_TWEETS_DATABASE = "C:\\Users\\Mirelle\\Documents\\projetoLEDA-transformacoes-main\\src\\dataBaseTweets";
    public static final String DIR_PROJECT_DATABASE = System.getProperty("user.dir") + File.separator + "src" + File.separator + "dataBaseTweets";

    public static final int DATABASE_LENGTH = 1048575; // tamanho do total de tweets no csv
    
    public static void main(String[] args) throws Exception {
        Tweet[] dataBaseTweets;

        dataBaseTweets = extract_tweets_database(DIR_TWEETS_DATABASE);
        System.out.println("Escrevendo os arquivos com datas transformadas...");
        write_date_file("tweets_formated_data", dataBaseTweets);
        write_mentioned_persons_file("tweets_mentioned_persons", dataBaseTweets);
        dataBaseTweets = null;

        // ## counting sort      
        //campo count; medio, melhor e pior cenarios
        System.out.println("# Counting Sort | campo Count = gerando arquivos");
        dataBaseTweets = extract_database("tweets_mentioned_persons");
        CountingSort.sortByMentionedCount(dataBaseTweets); //medio caso
        write_ordened_file("tweets_mentioned_persons_count_countingSort_medioCaso", dataBaseTweets);
        CountingSort.sortByMentionedCount(dataBaseTweets); //melhor caso
        write_ordened_file("tweets_mentioned_persons_count_countingSort_melhorCaso", dataBaseTweets);
       // gerando pior caso:
        MergeSort.mergeByReverseDate(dataBaseTweets);
        CountingSort.sortByMentionedCount(dataBaseTweets); //pior caso
        write_ordened_file("tweets_mentioned_persons_count_countingSort_piorCaso", dataBaseTweets);
        dataBaseTweets = null;

        //# heap sort
        // campo date; medio, melhor e pior cenarios
        System.out.println("# Heap Sort | campo Date = gerando arquivos");
        dataBaseTweets = extract_database("tweets_mentioned_persons");
        HeapSort.sortByDate(dataBaseTweets); //medio caso
        write_ordened_file("tweets_mentioned_persons_date_heapSort_medioCaso", dataBaseTweets);
        HeapSort.sortByDate(dataBaseTweets); //melhor caso
        write_ordened_file("tweets_mentioned_persons_date_heapSort_melhorCaso", dataBaseTweets);
        MergeSort.mergeByReverseDate(dataBaseTweets); //gerando pior caso
        HeapSort.sortByDate(dataBaseTweets); //pior caso
        write_ordened_file("tweets_mentioned_persons_date_heapSort_piorCaso", dataBaseTweets);
        dataBaseTweets = null;

        //campo count; medio, melhor e pior cenarios
        System.out.println("# Heap Sort | campo count = gerando arquivos");
        dataBaseTweets = extract_database("tweets_mentioned_persons");
        HeapSort.sortByMentionedCount(dataBaseTweets); //medio caso
        write_ordened_file("tweets_mentioned_persons_count_heapSort_medioCaso", dataBaseTweets);
        HeapSort.sortByMentionedCount(dataBaseTweets); //melhor caso
        write_ordened_file("tweets_mentioned_persons_count_heapSort_melhorCaso", dataBaseTweets);
        MergeSort.mergeByReverseMentionedCount(dataBaseTweets); //gerando pior caso
        HeapSort.sortByMentionedCount(dataBaseTweets); //pior caso
        write_ordened_file("tweets_mentioned_persons_count_heapSort_piorCaso", dataBaseTweets);
        dataBaseTweets = null;

        //campo user; medio, melhor e pior cenarios
        System.out.println("# Heap Sort | campo user = gerando arquivos");
        dataBaseTweets = extract_database("tweets_mentioned_persons");
        HeapSort.sortByUser(dataBaseTweets); //medio caso
        write_ordened_file("tweets_mentioned_persons_user_heapSort_medioCaso", dataBaseTweets);
        HeapSort.sortByUser(dataBaseTweets); //melhor caso
        write_ordened_file("tweets_mentioned_persons_user_heapSort_melhorCaso", dataBaseTweets);
        MergeSort.mergeByReverseUser(dataBaseTweets); //gerando pior caso
        HeapSort.sortByUser(dataBaseTweets); //pior caso
        write_ordened_file("tweets_mentioned_persons_user_heapSort_piorCaso", dataBaseTweets);
        dataBaseTweets = null;

        // # insertion sort
        // campo date; medio, melhor e pior cenarios
        System.out.println("# Insertion Sort | campo Date = gerando arquivos");
        dataBaseTweets = extract_database("tweets_mentioned_persons");
        InsertionSort.sortByDate(dataBaseTweets); //medio caso
        write_ordened_file("tweets_mentioned_persons_date_insertionSort_medioCaso", dataBaseTweets);
        InsertionSort.sortByDate(dataBaseTweets); //melhor caso
        write_ordened_file("tweets_mentioned_persons_date_insertionSort_melhorCaso", dataBaseTweets);
        MergeSort.mergeByReverseDate(dataBaseTweets); //gerando pior caso
        InsertionSort.sortByDate(dataBaseTweets); //pior caso
        write_ordened_file("tweets_mentioned_persons_date_insertionSort_piorCaso", dataBaseTweets);
        dataBaseTweets = null;

        //campo count; medio, melhor e pior cenarios
        System.out.println("# Insertion Sort | campo count = gerando arquivos");
        dataBaseTweets = extract_database("tweets_mentioned_persons");
        InsertionSort.sortByMentionedCount(dataBaseTweets); //medio caso
        write_ordened_file("tweets_mentioned_persons_count_insertionSort_medioCaso", dataBaseTweets);
        InsertionSort.sortByMentionedCount(dataBaseTweets); //melhor caso
        write_ordened_file("tweets_mentioned_persons_count_insertionSort_melhorCaso", dataBaseTweets);
        MergeSort.mergeByReverseMentionedCount(dataBaseTweets); //gerando pior caso
        InsertionSort.sortByMentionedCount(dataBaseTweets); //pior caso
        write_ordened_file("tweets_mentioned_persons_count_insertionSort_piorCaso", dataBaseTweets);
        dataBaseTweets = null;
        
        // campo user; medio, melhor e pior cenarios
        System.out.println("# Insertion Sort | campo user = gerando arquivos");
        dataBaseTweets = extract_database("tweets_mentioned_persons");
        InsertionSort.sortByUser(dataBaseTweets); //medio caso
        write_ordened_file("tweets_mentioned_persons_user_insertionSort_medioCaso", dataBaseTweets);
        InsertionSort.sortByUser(dataBaseTweets); //melhor caso]
        write_ordened_file("tweets_mentioned_persons_user_insertionSort_melhorCaso", dataBaseTweets);
        MergeSort.mergeByReverseUser(dataBaseTweets); //gerando pior caso
        InsertionSort.sortByUser(dataBaseTweets); //pior caso
        write_ordened_file("tweets_mentioned_persons_user_insertionSort_piorCaso", dataBaseTweets);
        dataBaseTweets = null;

        // # merge sort
        // campo date; medio, melhor e pior cenarios
        System.out.println("# Merge Sort | campo date = gerando arquivos");
        dataBaseTweets = extract_database("tweets_mentioned_persons");
        MergeSort.mergeByDate(dataBaseTweets); //medio caso
        write_ordened_file("tweets_mentioned_persons_date_mergeSort_medioCaso", dataBaseTweets);
        MergeSort.mergeByDate(dataBaseTweets); //melhor caso
        write_ordened_file("tweets_mentioned_persons_date_mergeSort_melhorCaso", dataBaseTweets);
        MergeSort.mergeByReverseDate(dataBaseTweets); //gerando pior caso
        MergeSort.mergeByDate(dataBaseTweets); //pior caso
        write_ordened_file("tweets_mentioned_persons_date_mergeSort_piorCaso", dataBaseTweets);
        dataBaseTweets = null;
        
        // campo count; medio, melhor e pior cenarios
        System.out.println("# Merge Sort | campo count = gerando arquivos");
        dataBaseTweets = extract_database("tweets_mentioned_persons");
        MergeSort.mergeByMentionedCount(dataBaseTweets); //medio caso
        write_ordened_file("tweets_mentioned_persons_count_mergeSort_medioCaso", dataBaseTweets);
        MergeSort.mergeByMentionedCount(dataBaseTweets); //melhor caso
        write_ordened_file("tweets_mentioned_persons_count_mergeSort_melhorCaso", dataBaseTweets);
        MergeSort.mergeByReverseMentionedCount(dataBaseTweets); //gerando pior caso
        MergeSort.mergeByMentionedCount(dataBaseTweets); //pior caso
        write_ordened_file("tweets_mentioned_persons_count_mergeSort_piorCaso", dataBaseTweets);
        dataBaseTweets = null;
        
        // campo user; medio, melhor e pior cenarios
        System.out.println("# Merge Sort | campo user = gerando arquivos");
        dataBaseTweets = extract_database("tweets_mentioned_persons");
        MergeSort.mergeByUser(dataBaseTweets); //medio caso
        write_ordened_file("tweets_mentioned_persons_user_mergeSort_medioCaso", dataBaseTweets);
        MergeSort.mergeByUser(dataBaseTweets); //melhor caso
        write_ordened_file("tweets_mentioned_persons_user_mergeSort_melhorCaso", dataBaseTweets);
        MergeSort.mergeByReverseUser(dataBaseTweets); //gerando pior caso
        MergeSort.mergeByUser(dataBaseTweets); //pior caso
        write_ordened_file("tweets_mentioned_persons_user_mergeSort_piorCaso", dataBaseTweets);
        dataBaseTweets = null;

        // # quick sort com mediana de 3        
        //campo date; medio, melhor e pior cenarios
        System.out.println("# Quick Sort Mediana| campo date = gerando arquivos");
        dataBaseTweets = extract_database("tweets_mentioned_persons");
        QuickSort.sortByAverageDateIterative(dataBaseTweets); //medio caso
        write_ordened_file("tweets_mentioned_persons_date_quickSort(media)_medioCaso", dataBaseTweets);
        QuickSort.sortByAverageDateIterative(dataBaseTweets); //melhor caso
        write_ordened_file("tweets_mentioned_persons_date_quickSort(media)_melhorCaso", dataBaseTweets);
        MergeSort.mergeByReverseDate(dataBaseTweets); //gerando pior caso
        QuickSort.sortByAverageDateIterative(dataBaseTweets); //pior caso
        write_ordened_file("tweets_mentioned_persons_date_quickSort(media)_piorCaso", dataBaseTweets);
        dataBaseTweets = null;

        //campo count; medio, melhor e pior cenarios
        System.out.println("# Quick Sort Mediana| campo count = gerando arquivos");
        dataBaseTweets = extract_database("tweets_mentioned_persons");
        QuickSort.sortByAverageMentionedCountIterative(dataBaseTweets); //medio caso
        write_ordened_file("tweets_mentioned_persons_count_quickSort(media)_medioCaso", dataBaseTweets);
        QuickSort.sortByAverageMentionedCountIterative(dataBaseTweets); //melhor caso
        write_ordened_file("tweets_mentioned_persons_count_quickSort(media)_melhorCaso", dataBaseTweets);
        MergeSort.mergeByReverseDate(dataBaseTweets); //gerando pior caso
        QuickSort.sortByAverageMentionedCountIterative(dataBaseTweets); //pior caso
        write_ordened_file("tweets_mentioned_persons_count_quickSort(media)_piorCaso", dataBaseTweets);
        dataBaseTweets = null;
        
        // quick sort 
        // campo date; medio, melhor e pior cenarios
        System.out.println("# Quick Sort | campo date = gerando arquivos");
        dataBaseTweets = extract_database("tweets_mentioned_persons");
        QuickSort.sortByDateIterative(dataBaseTweets); //medio caso
        write_ordened_file("tweets_mentioned_persons_date_quickSort_medioCaso", dataBaseTweets);
        QuickSort.sortByDateIterative(dataBaseTweets); //melhor caso
        write_ordened_file("tweets_mentioned_persons_date_quickSort_melhorCaso", dataBaseTweets);
        MergeSort.mergeByReverseDate(dataBaseTweets); //gerando pior caso
        QuickSort.sortByDateIterative(dataBaseTweets); //pior caso
        write_ordened_file("tweets_mentioned_persons_date_quickSort_piorCaso", dataBaseTweets);
        dataBaseTweets = null;

        //campo count; medio, melhor e pior cenarios
        System.out.println("# Quick Sort | campo count = gerando arquivos");
        dataBaseTweets = extract_database("tweets_mentioned_persons");
        QuickSort.sortByMentionedCountIterative(dataBaseTweets); //medio caso
        write_ordened_file("tweets_mentioned_persons_count_quickSort_medioCaso", dataBaseTweets);
        QuickSort.sortByMentionedCountIterative(dataBaseTweets); //melhor caso
        write_ordened_file("tweets_mentioned_persons_count_quickSort_melhorCaso", dataBaseTweets);
        MergeSort.mergeByReverseDate(dataBaseTweets); //gerando pior caso
        QuickSort.sortByMentionedCountIterative(dataBaseTweets); //pior caso
        write_ordened_file("tweets_mentioned_persons_count_quickSort_piorCaso", dataBaseTweets);
        dataBaseTweets = null;

        //campo user; medio, melhor e pior cenarios
        System.out.println("# Quick Sort | campo User = gerando arquivos");
        dataBaseTweets = extract_database("tweets_mentioned_persons");
        QuickSort.sortByUserIterative(dataBaseTweets); //medio caso
        write_ordened_file("tweets_mentioned_persons_user_quickSort(iterativo)_medioCaso", dataBaseTweets);
        QuickSort.sortByUserIterative(dataBaseTweets); //melhor caso
        write_ordened_file("tweets_mentioned_persons_user_quickSort(iterativo)_melhorCaso", dataBaseTweets);
        MergeSort.mergeByReverseDate(dataBaseTweets); //gerando pior caso
        QuickSort.sortByUserIterative(dataBaseTweets); //pior caso
        write_ordened_file("tweets_mentioned_persons_user_quickSort(iterativo)_piorCaso", dataBaseTweets);
        dataBaseTweets = null;
        
        // # selection sort
        //campo date; medio, melhor e pior cenarios
        System.out.println("# Selection Sort | campo date = gerando arquivos");
        dataBaseTweets = extract_database("tweets_mentioned_persons");
        SelectionSort.sortByDate(dataBaseTweets); //medio caso
        write_ordened_file("tweets_mentioned_persons_date_selectionSort_medioCaso", dataBaseTweets);
        SelectionSort.sortByDate(dataBaseTweets); //melhor caso
        write_ordened_file("tweets_mentioned_persons_date_selectionSort_melhorCaso", dataBaseTweets);
        MergeSort.mergeByReverseDate(dataBaseTweets); //gerando pior caso
        SelectionSort.sortByDate(dataBaseTweets); //pior caso
        write_ordened_file("tweets_mentioned_persons_date_selectionSort_piorCaso", dataBaseTweets);
        dataBaseTweets = null;

        //campo count; medio, melhor e pior cenarios
        System.out.println("# Selection Sort | campo count = gerando arquivos");
        dataBaseTweets = extract_database("tweets_mentioned_persons");
        SelectionSort.sortByMentionedCount(dataBaseTweets); //medio caso
        write_ordened_file("tweets_mentioned_persons_count_selectionSort_medioCaso", dataBaseTweets);
        SelectionSort.sortByMentionedCount(dataBaseTweets); //melhor caso
        write_ordened_file("tweets_mentioned_persons_count_selectionSort_melhorCaso", dataBaseTweets);
        MergeSort.mergeByReverseMentionedCount(dataBaseTweets); //gerando pior caso
        SelectionSort.sortByMentionedCount(dataBaseTweets); //pior caso
        write_ordened_file("tweets_mentioned_persons_count_selectionSort_piorCaso", dataBaseTweets);
        dataBaseTweets = null;

        //campo user; medio, melhor e pior cenarios
        System.out.println("# Selection Sort | campo user = gerando arquivos");
        dataBaseTweets = extract_database("tweets_mentioned_persons");
        SelectionSort.sortByUser(dataBaseTweets); //medio caso
        write_ordened_file("tweets_mentioned_persons_user_selectionSort_medioCaso", dataBaseTweets);
        SelectionSort.sortByUser(dataBaseTweets); //melhor caso
        write_ordened_file("tweets_mentioned_persons_user_selectionSort_melhorCaso", dataBaseTweets);
        MergeSort.mergeByReverseUser(dataBaseTweets); //gerando pior caso
        SelectionSort.sortByUser(dataBaseTweets); //pior caso
        write_ordened_file("tweets_mentioned_persons_user_selectionSort_piorCaso", dataBaseTweets);
        dataBaseTweets = null;

    }

    public static Tweet[] extract_tweets_database(String dir){
        String path = dir + File.separator + "tweets.csv";
        int lines = DATABASE_LENGTH;
        Tweet[] database = new Tweet[lines];
        
        try (BufferedReader file = new BufferedReader(new FileReader(path))){
            System.out.println("Extraindo database...");
            String line;
            int i = 0;
            while((line = file.readLine()) != null){
                if(i > 0){ //pulando a linha incial de cabeçalho
                    String[] field =  line.split(","); //separando a linha em campos
                    Tweet tweet = new Tweet(field[0], field[1], field[2], field[3], field[4], field[5].trim());
                    database[i-1] = tweet;
                }
                i++;
            }
            System.out.println("Data base completa.");
        } catch (IOException e) {
            System.out.println("Erro extraindo os dados");
            e.printStackTrace();
        }
        return database;
    }

    public static Tweet[] extract_database(String name){
        String path = DIR_PROJECT_DATABASE + File.separator + name + ".csv";
        int lines = DATABASE_LENGTH;
        Tweet[] database = new Tweet[lines]; 

        try (BufferedReader file = new BufferedReader(new FileReader(path))) {
            String line;
            int i = 0;
            
            while ((line = file.readLine()) != null) {
                if (i > 0) { 
                    String[] field = line.split(","); // separar a linha em campos
                    
                    int count_mentioned_person = 0;
                    try {
                        count_mentioned_person = Integer.parseInt(field[7]);
                    } catch (NumberFormatException e) {
                        System.out.println("Erro ao converter numeros");
                        e.printStackTrace();
                    }
                    
                    Tweet tweet = new Tweet(field[0], field[1], field[2], field[3], field[4], field[5], field[6], count_mentioned_person);
                    database[i - 1] = tweet;
                }
                i++;
            }
        } catch (IOException e) {
            System.out.println("Erro ao extrair dados.");
            e.printStackTrace();
        }

        return database;
    }

    public static void write_date_file(String file_name, Tweet[] data){
        String path = DIR_PROJECT_DATABASE + File.separator + file_name + ".csv";

        try (BufferedWriter file = new BufferedWriter(new FileWriter(path))){
            System.out.println("SAlvando data formatada...");
			file.write("Target,ID,Date,flag,User,Text");
            file.newLine();

            for(int i = 0; i < data.length; i++) {
                String line = String.format("%s,%s,%s,%s,%s,%s", 
                    data[i].getTarget(), 
                    data[i].getId(), 
                    data[i].getFormatted_date(), 
                    data[i].getFlag(), 
                    data[i].getUser(), 
                    data[i].getText()
                );
                file.write(line);
                file.newLine();
            }
            System.out.println("Formatacao da data salva.");
        } catch (IOException e){
			System.out.println("Erro ao escrever arquivo");
			e.printStackTrace();
        }
    }

    public static void write_mentioned_persons_file(String file_name, Tweet[] data){
        String path_formatted_date = DIR_PROJECT_DATABASE + File.separator + "tweets_formated_data.csv";
        String path_mentioned_persons = DIR_PROJECT_DATABASE + File.separator + file_name + ".csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(path_formatted_date));
             BufferedWriter writer = new BufferedWriter(new FileWriter(path_mentioned_persons))) {

            System.out.println("Salvando arquivo das datas com mencoes...");
			

            int i = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                if(i == 0){
                    writer.write(line + ",mentioned_person,mentioned_person_count");
                    writer.newLine();
                } else{
                    if (i - 1 < data.length && data[i - 1] != null) {
                        writer.write(line + "," +
                                     data[i - 1].getMentioned_person() + "," +
                                     data[i - 1].getMentioned_person_count());
                        writer.newLine();
                    }
                }
                i++;
            }
            System.out.println("Informacao salva.");
        } catch (IOException e){
			System.out.println("Erro ao processar arquivo.");
			e.printStackTrace();
        }
    }

    public static void write_ordened_file(String name, Tweet[] data){
        String path = DIR_PROJECT_DATABASE + File.separator + "dataBase_ordenada" + File.separator + name + ".csv";

        try (BufferedWriter file = new BufferedWriter(new FileWriter(path))){
			file.write("Target,ID,Date,flag,User,Text,mentioned_person,mentioned_person_count");
            file.newLine();

            int i = 0;
            while (i < data.length && data[i] != null) {
                String line = String.format("%s,%s,%s,%s,%s,%s,%s,%d", 
                    data[i].getTarget(), 
                    data[i].getId(), 
                    data[i].getFormatted_date(), 
                    data[i].getFlag(), 
                    data[i].getUser(), 
                    data[i].getText(),
                    data[i].getMentioned_person(),
                    data[i].getMentioned_person_count()
                );
                file.write(line);
                file.newLine();
                i++;
            }
        } catch (IOException e){
			System.out.println("Erro ao escrever arquivo");
			e.printStackTrace();
        }
    }
    
}
