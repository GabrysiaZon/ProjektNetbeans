package com.aplikacja.kontroler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SasKontroler {
    
    
//Dostęp do folderu resources    
@Autowired
ResourceLoader resourceLoader;

//Wywołanie usługi zwracającej wykres
   @RequestMapping(value = "/wykres", method = RequestMethod.GET,produces = MediaType.IMAGE_PNG_VALUE)
    public void getImage(HttpServletResponse response) throws IOException, InterruptedException { 
        
     //Ustawienie ścieżek dostępwych:
    
      //Folder resources
      String lokalizacja_resources = System.getProperty("user.dir")+"\\src\\main\\resources\\";
      
      //Dostęp do sas.exe
      String lokalizacja_sas = "C:\\Program Files\\SASHome\\SASFoundation\\9.4\\sas.exe";
     
      //Kod wywołujący plik wykres.sas
      String kod_cmd = "\""+lokalizacja_sas+"\" "+"\""+lokalizacja_resources+"wykres.sas"+"\"";
    
      //W logu zobaczymy pełną ścieżkę wywolującą kod wykres.sas
      System.out.println(kod_cmd);
      
      //Wywołanie kodu w systemie Windows
      Runtime.getRuntime().exec(kod_cmd); 
      Thread.sleep(2000);
      
    //Wywołanie nowego pliku wykres.png z folderu oraz przekazanie go jako wynik działania usługi 
    final Resource resource = resourceLoader.getResource("classpath:wykres.png");
    response.setContentType(MediaType.IMAGE_PNG_VALUE);
    StreamUtils.copy(resource.getInputStream(), response.getOutputStream());
    }
    
//    
//    @RequestMapping(value = "/wykres2", method = RequestMethod.GET,produces = MediaType.IMAGE_PNG_VALUE)
////    public void getImage2(HttpServletResponse response,@RequestParam String tabela, @RequestParam String x, @RequestParam String y) throws IOException { 
//        
//    try {
//        //Ustawienie ścieżek dostępwych:
//        
//        //Folder resources
//        String lokalizacja_resources = System.getProperty("user.dir")+"\\src\\main\\resources\\";
//        
//        //Dostęp do sas.exe
//        String lokalizacja_sas = "C:\\Program Files\\SASHome\\SASFoundation\\9.4\\sas.exe" ;
//        
//        //Parametryzujemy kod wykresu
//        String kod_sas = "ods _all_ close;\n" +
//                "ods listing gpath = \".\\src\\main\\resources\";\n" +
//                "ods graphics on /reset imagefmt = png reset imagename = \"wykres2\";\n" +
//                " \n" +
//                "proc sgplot data =sashelp."+tabela+";\n" +
//                "scatter x="+x+" y="+y+";\n" +
//                "run;\n" +
//                " \n" +
//                "ods graphics off;\n" +
//                "ods listing ;";
//        
//        //Zapisujemy go do pliku
//        Files.write( Paths.get(lokalizacja_resources+"wykres2.sas"), kod_sas.getBytes());
//        
//        
//        //Kod wywołujący plik wykres.sas
//        String kod_cmd = "\""+lokalizacja_sas+"\" "+"\""+lokalizacja_resources+"wykres2.sas"+"\"";
//        
//        //W logu zobaczymy pełną ścieżkę wywolującą kod wykres.sas
//        System.out.println(kod_cmd);
//        
//        //Wywołanie kodu w systemie Windows
//        Runtime.getRuntime().exec(kod_cmd);
//        Thread.sleep(2000);
//        
//        //Wywołanie nowego pliku wykres.png z folderu oraz przekazanie go jako wynik działania usługi
//        final Resource resource = resourceLoader.getResource("classpath:wykres2.png");
//        response.setContentType(MediaType.IMAGE_PNG_VALUE);
//        StreamUtils.copy(resource.getInputStream(), response.getOutputStream());
//    } catch (InterruptedException ex) {
//        Logger.getLogger(SasKontroler.class.getName()).log(Level.SEVERE, null, ex);
//    }
//    }
    
    
}
