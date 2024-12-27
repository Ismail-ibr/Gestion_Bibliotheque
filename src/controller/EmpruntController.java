package controller;

import module.Emprunt;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EmpruntController  {
    public static List<Emprunt> empruntList=new ArrayList<>();
    public static int EMPRUNT_ID_CPT=0;
    public static void readEmpruntFile() {
        try {
            BufferedReader ois = new BufferedReader(new FileReader("C:\\Users\\ibrah\\OneDrive\\Bureau\\ProjetJava\\src\\Emprunts.csv"));
            Emprunt e=new Emprunt();
            String line;
            SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
            while((line=ois.readLine()) !=null){
                String[] empruntFields=line.split(",");
                if(EMPRUNT_ID_CPT<Integer.parseInt(empruntFields[0])){EMPRUNT_ID_CPT=Integer.parseInt(empruntFields[0]);}
                e.setIdE(EMPRUNT_ID_CPT);
                e.setLivreEmprunte(LivreController.findBook(Integer.parseInt(empruntFields[1])));
                e.setEmprunteur(MembreController.findMember(Integer.parseInt(empruntFields[2])));
                e.setDateEmprunt(dateFormat.parse(empruntFields[3]));
                e.setDateRetourTheo(dateFormat.parse(empruntFields[4]));
                e.setDateRetourReel(dateFormat.parse(empruntFields[5]));
                empruntList.add(e);
            }
            ois.close();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }


    public static void writeEmpruntFile() {
        try{
            BufferedWriter oos=new BufferedWriter(new FileWriter("C:\\Users\\ibrah\\OneDrive\\Bureau\\ProjetJava\\src\\Emprunts.csv"));
            for(Emprunt e:empruntList){
                oos.write(e.toString());
                oos.newLine();
            }
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
