import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static String formatDate(LocalDate paramDate){
        DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern(Settings.getDateFormat());
        return paramDate.format(dateFormater);
    }

    public static String getFileName(String fileName){
        return fileName+".txt";
    }
}
