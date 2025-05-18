package algoritmos;

import formatarTweet.Tweet;

public class QuickSort {

    //date
    public static void sortByDateIterative(Tweet[] array) {
        if (array == null || array.length == 0) {
            return;
        }
    
        int[] stack = new int[array.length];
        int top = -1;
    
        stack[++top] = 0;
        stack[++top] = array.length - 1;
    
        while (top >= 0) {
            int high = stack[top--];
            int low = stack[top--];
    
            int pivotIndex = partitionByDate(array, low, high);
    
            if (pivotIndex - 1 > low) {
                stack[++top] = low;
                stack[++top] = pivotIndex - 1;
            }
    
            if (pivotIndex + 1 < high) {
                stack[++top] = pivotIndex + 1;
                stack[++top] = high;
            }
        }
    }

    private static int partitionByDate(Tweet array[], int low, int high) {
        Tweet pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compareDates(array[j], pivot)) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    public static void sortByAverageDateIterative(Tweet[] array) {
        if (array == null || array.length == 0) {
            return;
        }
    
        int[] stack = new int[array.length];
        int top = -1;
    
        stack[++top] = 0;
        stack[++top] = array.length - 1;
    
        while (top >= 0) {
            int high = stack[top--];
            int low = stack[top--];
    
            int pivotIndex = partitionByAverageDate(array, low, high);
    
            if (pivotIndex - 1 > low) {
                stack[++top] = low;
                stack[++top] = pivotIndex - 1;
            }
    
            if (pivotIndex + 1 < high) {
                stack[++top] = pivotIndex + 1;
                stack[++top] = high;
            }
        }
    }
    
    private static int partitionByAverageDate(Tweet[] array, int low, int high) {
        int mid = low + (high - low) / 2;
    
        if (compareDates(array[mid], array[low])) {
            swap(array, low, mid);
        }
        if (compareDates(array[high], array[low])) {
            swap(array, low, high);
        }
        if (compareDates(array[high], array[mid])) {
            swap(array, mid, high);
        }
    
        Tweet pivot = array[mid];
        swap(array, mid, high);  
    
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (compareDates(array[j], pivot)) {
                i++;
                swap(array, i, j);
            }
        }
    
        swap(array, i + 1, high);
        return i + 1;
    }
    
    private static boolean compareDates(Tweet a, Tweet b) {
        return a.getYear() < b.getYear() || 
               (a.getYear() == b.getYear() && a.getMonth() < b.getMonth()) || 
               (a.getYear() == b.getYear() && a.getMonth() == b.getMonth() && a.getDay() <= b.getDay());
    }
    
    //count
    public static void sortByAverageMentionedCountIterative(Tweet[] array) {
        if (array == null || array.length == 0) {
            return;
        }
    
        int[] stack = new int[array.length];
        int top = -1;
    
        stack[++top] = 0; 
        stack[++top] = array.length - 1; 
    
        while (top >= 0) {
            int high = stack[top--]; 
            int low = stack[top--]; 
    
            int pivotIndex = partitionByAverageMentionedCount(array, low, high); 
    
            if (pivotIndex - 1 > low) {
                stack[++top] = low;
                stack[++top] = pivotIndex - 1;
            }
    
            if (pivotIndex + 1 < high) {
                stack[++top] = pivotIndex + 1;
                stack[++top] = high;
            }
        }
    }
    
    private static int partitionByAverageMentionedCount(Tweet[] array, int low, int high) {
        int totalMentions = 0;
        for (int i = low; i <= high; i++) {
            totalMentions += array[i].getMentioned_person_count();
        }
        int averageMentionCount = totalMentions / (high - low + 1);
    
        // Escolher o pivô, aqui o pivô continua sendo o elemento na posição 'low'
        Tweet pivot = array[low];
        int pivotIndex = low;
    
        // Encontrar o elemento mais próximo da média
        for (int i = low; i <= high; i++) {
            if (Math.abs(array[i].getMentioned_person_count() - averageMentionCount) < 
                Math.abs(pivot.getMentioned_person_count() - averageMentionCount)) {
                pivot = array[i]; 
                pivotIndex = i; // Atualizar o índice do pivô
            }
        }
    
        // Trocar o pivô com o último elemento da partição
        swap(array, pivotIndex, high);
        pivotIndex = high; // Atualizar o pivô para o novo índice
    
        int i = low - 1;
        // Agora ordena do mais mencionado para o menos mencionado
        for (int j = low; j < pivotIndex; j++) {
            if (array[j].getMentioned_person_count() > pivot.getMentioned_person_count()) { // Alterar a comparação para '>'
                i++;
                swap(array, i, j);
            }
        }
    
        // Colocar o pivô na posição correta
        swap(array, i + 1, pivotIndex); 
        return i + 1; 
    }

    public static void sortByMentionedCountIterative(Tweet[] array) {
        // Verifica se o array é nulo ou vazio
        if (array == null || array.length == 0) {
            return;
        }
    
        // Um vetor para armazenar os limites das subpartições
        int[] limits = new int[array.length * 2];
        int limitIndex = 0;
    
        // Adiciona os limites iniciais
        limits[limitIndex++] = 0; // low
        limits[limitIndex++] = array.length - 1; // high
    
        // Enquanto houver limites a serem processados
        while (limitIndex > 0) {
            // Remove os limites do vetor
            int high = limits[--limitIndex];
            int low = limits[--limitIndex];
    
            // Particiona o array e obtém o índice do pivô
            int pivotIndex = partitionByMentionedCount(array, low, high);
    
            // Adiciona os limites das subpartições à lista
            if (pivotIndex - 1 > low) {
                limits[limitIndex++] = low;
                limits[limitIndex++] = pivotIndex - 1; // Parte esquerda
            }
    
            if (pivotIndex + 1 < high) {
                limits[limitIndex++] = pivotIndex + 1;
                limits[limitIndex++] = high; // Parte direita
            }
        }
    }
    
    private static int partitionByMentionedCount(Tweet[] array, int low, int high) {
        // Escolhe o último elemento como pivô
        Tweet pivot = array[high];
        int i = (low - 1); // Índice do menor elemento
    
        for (int j = low; j < high; j++) {
            // Compara o número de menções para ordenar em ordem decrescente
            if (array[j].getMentioned_person_count() > pivot.getMentioned_person_count()) {
                i++;
                swap(array, i, j); // Troca
            }
        }
    
        swap(array, i + 1, high); // Troca o pivô para a posição correta
        return i + 1; // Retorna o índice do pivô
    }

    //user
    public static void sortByUserIterative(Tweet[] array) {
        // Verifica se o array é nulo ou vazio
        if (array == null || array.length == 0) {
            return;
        }
    
        // Um vetor para armazenar os limites das subpartições
        int[] limits = new int[array.length * 2];
        int limitIndex = 0;
    
        // Adiciona os limites iniciais
        limits[limitIndex++] = 0; // low
        limits[limitIndex++] = array.length - 1; // high
    
        // Enquanto houver limites a serem processados
        while (limitIndex > 0) {
            // Remove os limites do vetor
            int high = limits[--limitIndex];
            int low = limits[--limitIndex];
    
            // Particiona o array e obtém o índice do pivô
            int pivotIndex = partitionByUser(array, low, high);
    
            // Adiciona os limites das subpartições à lista
            if (pivotIndex - 1 > low) {
                limits[limitIndex++] = low;
                limits[limitIndex++] = pivotIndex - 1; // Parte esquerda
            }
    
            if (pivotIndex + 1 < high) {
                limits[limitIndex++] = pivotIndex + 1;
                limits[limitIndex++] = high; // Parte direita
            }
        }
    }

    private static int partitionByUser(Tweet[] array, int low, int high) {
        String pivot = array[high].getUser(); // Escolhe o último elemento como pivô
        int i = (low - 1); // Índice do menor elemento
    
        for (int j = low; j < high; j++) {
            // Compara os nomes dos usuários para ordenar em ordem alfabética
            if (array[j].getUser().compareTo(pivot) < 0) {
                i++;
                swap(array, i, j); // Troca
            }
        }
    
        swap(array, i + 1, high); // Troca o pivô para a posição correta
        return i + 1; // Retorna o índice do pivô
    }

    //geral
    private static void swap(Tweet[] array, int i, int j) {
        Tweet temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    
}

