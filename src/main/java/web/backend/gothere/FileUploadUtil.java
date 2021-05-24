package web.backend.gothere;
 
import java.io.*;
import java.nio.file.*;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import org.springframework.web.multipart.MultipartFile;
 
public class FileUploadUtil {
     
    public static void saveFile(String uploadDir, String fileName,
            MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
         
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {        
            throw new IOException("Could not save image file: " + fileName, ioe);
        }      
    }
    public static void deleteFile(String directory) throws IOException {
        Path path = Paths.get(directory);
         
        try{
            Files.delete(path);
        }catch(IOException ioe){
            throw new IOException("Could not delete image file: " + directory, ioe);
        }  
    }
    public static String getFileName(String originalName, String newName){

        String[] parts = originalName.split("\\.");
        String extension = parts[parts.length-1];
        newName = Normalizer.normalize(newName, Form.NFD);

        newName = newName.replaceAll("\\p{M}", "");
        newName = newName.replaceAll("[^a-zA-Z0-9]+","-").toLowerCase();
        return newName + "." +extension;
    }
}