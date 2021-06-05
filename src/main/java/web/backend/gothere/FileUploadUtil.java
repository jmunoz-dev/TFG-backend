package web.backend.gothere;
 
import java.text.Normalizer;
import java.text.Normalizer.Form;
 
public class FileUploadUtil {
     
    public static String getFileName(String originalName, String newName){

        String[] parts = originalName.split("\\.");
        String extension = parts[parts.length-1];
        newName = Normalizer.normalize(newName, Form.NFD);

        newName = newName.replaceAll("\\p{M}", "");
        newName = newName.replaceAll("[^a-zA-Z0-9]+","-").toLowerCase();
        return newName + "." +extension;
    }
}