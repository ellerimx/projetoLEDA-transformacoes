import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.io.File;
import java.util.TimeZone;

public class ProcessCSV{
    public static void main(String[] args) throws IOException{
        //leitura do arquivo do csv
        String inputFile = "dataBaseTweets"+File.separator+"tweets.csv"; 
        //criacao do csv com datas formatadas
        String formatedData = "tweets_formated_data.csv"; 

        //criar csv com as datas transformadas
        try(BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(formatedData))){

            //leitura o cabeçalho e criando novas colunas na ordem correta
            String header = reader.readLine();
            writer.write(header);
            writer.newLine();

            String line;
            while((line = reader.readLine()) != null){
                String[] columns = line.split(",");

                //garantindo que temos pelo menos 6 colunas (para evitar erro de índice)
                if(columns.length < 6) continue;

                //converter data (coluna Date - posição 2)
                //posicao da data pra fazer a transformação
                columns[2] = format_date(columns[2]); 

                //escreve a formatação da data
                writer.write(String.join(",", columns));
                writer.newLine();
            }
        }

        System.out.println("Arquivo com a data formatada com sucesso! Arquivo criado como: " + formatedData);

        //cria novo csv baseado no da data formatada
        //novo csv contendo 2 colunas
        String mentionedPersonCSV = "tweets_mentioned_persons.csv"; // com mencoes e contador, na versao final
        createCSVWithMentionedPersons(formatedData,mentionedPersonCSV);
    }

    //metodos 
    
    //para formatar a data
    public static String format_date(String dateString){

        String[] parts = dateString.split(" ");
        if(parts.length >= 6){
            dateString = String.format("%s %s %s %s %s", parts[0], parts[1], parts[2], parts[3], parts[5]);
        }

        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy", Locale.ENGLISH);
        inputFormat.setLenient(false);

        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

        try{
            return outputFormat.format(inputFormat.parse(dateString));
        } 
        catch(ParseException e){
            //retorna original se não conseguir converter
            return dateString; 
        }
    }

    //para criar o csv de "tweets_mentioned_persons"
    public static void createCSVWithMentionedPersons(String formatedData, String mentionedPersonCSV) throws  IOException{
        try(BufferedReader reader = new BufferedReader(new FileReader(formatedData));
        BufferedWriter writer = new BufferedWriter(new FileWriter(mentionedPersonCSV))){

            String line;
            String header = reader.readLine();
            //writer.write("Target,ID,Date,flag,User,Text,mentioned_person,count_mentioned_person");
            writer.write(header + ",mentioned_person,count_mentioned_person");
            writer.newLine();

            while((line = reader.readLine()) != null){
                String[] columns = line.split(",");
                //procura a pessoa mencionada na coluna de Text
                String mentionedPersons = search_mentioned_person(columns[5]); 
                
                //contador
                int mentionedCount = count_mentioned_person(mentionedPersons);

                //adiciona duas colunas
                String[] newRow = new String[columns.length + 2]; 
                System.arraycopy(columns, 0, newRow, 0, columns.length);
                //com o user de quem mencionou
                newRow[columns.length] = (mentionedPersons == null) ? "null" : mentionedPersons; 
                newRow[columns.length + 1] = String.valueOf(mentionedCount);

                //escrever linha no csv
                writer.write(String.join(",", newRow));
                writer.newLine();
            }
        }

        System.out.println("Arquivo criado com sucesso! Arquivo criado como: " + mentionedPersonCSV);
    }

    //pra procurar menções
    public static String search_mentioned_person(String text){
        if(text == null || text.isEmpty()) return "null";

        String[] words = text.split(" ");
        String mentioned = "";

        for(String word : words){
            if((word.startsWith("@") || word.startsWith("\"")) && word.length() > 1){
                String mention = word.replaceAll("[^@\\w]", "");
                if(mention.startsWith("@")){
                    //removendo o primeiro caractera, que é o @, do csv na parte de menção
                    mention = mention.substring(1); 

                    if(!mentioned.isEmpty()){
                        mentioned += "/";
                    }
                    mentioned += mention;
                }
            }
        }
        return mentioned.isEmpty() ? "null" : mentioned;
    }

    //metodo de contador
    public static int count_mentioned_person(String mentioned){
        //se nao tiver menção, retorna 0
        if(mentioned == null || mentioned.isEmpty() || mentioned.equals("null")){
            return 0;
        }
    
        //cria array separado por "/"
        String[] mentionsArray = mentioned.split("/");
    
        //o tamanho do arry é o numero de menções
        return mentionsArray.length;
    }
}
