/* Fout or File Output class
 * Created by Tim Schatz-Ouellette
 * Generic Saving and Loading
 * 
 */ 




import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class FIO{
     
     public File file;
     public String path;
     public String name;
//     private String END_KEY = "�";
//     
//     public void setEndKey(String endKey){
//          if(endKey.equals("") || endKey.equals(" "))
//               throw new TschaFIOException(file, "End Key invalid");
//          else
//               END_KEY = endKey;
//     }
     
     public FIO(File f){
          file = f; 
          name = file.getName();
          path = file.getPath().substring(0, file.getPath().length()-name.length());
     }
     
     public FIO(){
          file = null; 
          name = null;
          path = null;
     }
     
     public FIO(FIO io){
          file = io.file;
          name = file.getName();
          path = file.getPath().substring(0, file.getPath().length()-name.length());
     }
     
     public FIO(String str){
          file = new File(str);
          name = file.getName();
          path = file.getPath().substring(0, file.getPath().length()-name.length());
     }
     
     public void delete() {//Deletes fileName
          if(file.exists())
               file.delete();
          else
               throw new TschaFIOException(file, name + " does not exist");
     } 
     
     public void setFile(String str){
          file = new File(str);
          name = file.getName();
          path = file.getPath().substring(0, file.getPath().length()-name.length());
     }
     
     public void setFile(File f){
          file = f; 
          name = file.getName();
          path = file.getPath().substring(0, file.getPath().length()-name.length());
     }
     
     public void changeFile(File f){
          setFile(f);
     }
     
     public void changeFile(String str){
          setFile(str);
     }
     
     public void create(){//Deletes fileName
          try {
               if(file.exists())
                    throw new TschaFIOException(file, name + " already exists");
               else{
                    if(path.contains("\\")){
                         String str = "";
                         for(int i=0;i<path.length();i++){
                              if(path.charAt(i) == '\\'){
                                   str += path.charAt(i);
                                   if(new File(str).exists() == false)
                                        new File(str).mkdirs();
                              } else{
                                   str += path.charAt(i);
                              }
                         }
                    } 
                    FileWriter fw = new FileWriter(file);
                    fw.write("");
                    fw.close();
               }
          } catch (IOException ioe) {
               throw new TschaFIOException(file, "IOException @ FIO.create()");
          }
     } 
     
     public void reset(){
          if(file.exists()){
               try{
                    FileWriter fw = new FileWriter(file);
                    fw.write("");
                    fw.close();
               } catch (IOException e) {}//false positive
          } else 
               throw new TschaFIOException(file, name + " does not exist");
     }
     
     public void add(Object...data){//Saves a string to a text file
          if (file.exists()){
               Object[] loadData = load();
               Object[] fullData = new Object[loadData.length + data.length];
               for(int i=0;i<fullData.length;i++){
                    if(i>loadData.length-1)
                         fullData[i] = data[i-loadData.length];
                    else
                         fullData[i] = loadData[i];
               }
               overwrite(fullData);
          } else if(file.length() == 0){
               overwrite(data);
          } else 
               throw new TschaFIOException(file, name + " does not exist");
     }
     
     public void overwrite(Object...data){//Saves a string to a text file
          try {
               if(file.exists()){
                    FileWriter fw = new FileWriter(file);
                    for(Object d:data)
                         fw.write(d + "\n");
                    fw.close();
               } else 
                    throw new TschaFIOException(file, name + " does not exist");
          } catch (IOException ioe) {
               throw new TschaFIOException(file, "IOException @ FIO.create()");
          }
     }
     
     public void save(Object...data){
          overwrite(data);
     }
     
     private int getLines() throws IOException {//Checks ammount of lines in a file
          BufferedReader reader = new BufferedReader(new FileReader(file));
          int lines = 0;
          try{
               for(;reader.readLine().equals(null) == false;lines++){}
          } catch (NullPointerException npe){}
          reader.close();
          return lines;
     }
     
     public String[] load(){//Loads data into a String array from the desired fileName
          try {
               if(file.exists()) {
                    BufferedReader saveFile = new BufferedReader(new FileReader(file));
                    String[] o = new String[getLines()];
                    for(int i=0;i<o.length;i++) 
                         o[i] = saveFile.readLine();
                    saveFile.close();
                    return o;
               }
               else
                    throw new TschaFIOException(file, name + " does not exist");
          } catch (IOException ioe) {
               throw new TschaFIOException(file, "IOException @ FIO.create()");
          }
     }
     
     public String load(int line){//Loads data into a String array from the desired fileName
          try {
               if(file.exists()) {
                    BufferedReader saveFile = new BufferedReader(new FileReader(file));
                    for(int i=0;i<(line-1);i++) 
                         saveFile.readLine();
                    String str = saveFile.readLine();
                    saveFile.close();
                    return str;
               }
               else
                    throw new TschaFIOException(file, name + " does not exist");
          } catch (IOException ioe) {
               throw new TschaFIOException(file, "IOException @ FIO.create()");
          }
     }
     
     public final static int RANGE = -7986, DIRECT = -7987;
     public String[] load(int...lineNumbers){
          try {
               BufferedReader saveFile = new BufferedReader(new FileReader(file));
               if(lineNumbers.length == 1){
                    if (file.exists()) {
                         for(int i=0;i<lineNumbers[0];i++)
                              saveFile.readLine();
                         String o = saveFile.readLine();
                         saveFile.close();
                         return new String[]{o};
                    } else
                         throw new TschaFIOException(file, name + " does not exist"); 
               }
               saveFile.mark(1);
               for(int i=0;i<lineNumbers.length;i++){
                    if (lineNumbers[i] < 0){
                         if(i>0)
                              throw new TschaFIOException(file, "Line number below zero");
                    } else if (getLines() < lineNumbers[i])
                         throw new TschaFIOException(file, "Max line number exceeded for " + name + " (" + getLines() + ")");
               }
               if (lineNumbers[0] == RANGE || lineNumbers.length == 2) {
                    if (lineNumbers.length==2){//If the input is only two values it will default to RANGE
                         String obj[] = new String[lineNumbers[1]-lineNumbers[0]];
                         if (file.exists()) {
                              for(int i=0;i<lineNumbers[1];i++){
                                   if(i<lineNumbers[0])
                                        saveFile.readLine();
                                   else
                                        obj[i-lineNumbers[0]] = saveFile.readLine();
                              }
                              saveFile.close();
                              return obj;
                         } else
                              throw new TschaFIOException(file, name + " does not exist");
                    } else if (lineNumbers.length==3){
                         String obj[] = new String[lineNumbers[2]-lineNumbers[1]];
                         if (file.exists()) {
                              for(int i=0;i<lineNumbers[2];i++){
                                   if(i<lineNumbers[1])
                                        saveFile.readLine();
                                   else
                                        obj[i-lineNumbers[1]] = saveFile.readLine();
                              }
                              saveFile.close();
                              return obj;
                         } else
                              throw new TschaFIOException(file, name + " does not exist");
                    } else
                         throw new TschaFIOException(file, "RANGE requires 3 integers in the method load, not " + (lineNumbers.length+1));
               } else if (lineNumbers[0] == DIRECT || lineNumbers.length > 2) {//3 7
                    String obj[];
                    if(lineNumbers[0]==DIRECT){
                         obj = new String[lineNumbers.length-1];
                         for(int i=1;i<lineNumbers.length;i++){
                              saveFile.reset();
                              for(int o=0;o<lineNumbers[i]+1;o++){
                                   if(o==lineNumbers[i])
                                        obj[i-1] = saveFile.readLine();
                                   else
                                        saveFile.readLine();
                              }
                         }
                    } else {
                         obj = new String[lineNumbers.length];
                         for(int i=0;i<lineNumbers.length;i++){
                              saveFile.reset();
                              for(int o=0;o<lineNumbers[i]+1;o++){
                                   if(o==lineNumbers[i])
                                        obj[i] = saveFile.readLine();
                                   else
                                        saveFile.readLine();
                              }
                         }
                    }
                    saveFile.close();
                    if (file.exists()) {
                         return obj;
                    } else
                         throw new TschaFIOException(file, name + " does not exist");
               } else
                    throw new TschaFIOException(file, "Invalid Parameters");
          } catch (IOException ioe) {
               throw new TschaFIOException(file, "IOException @ FIO.create()");
          }
     }
     
     public boolean exists() {//Checks if a file exits
          return file.exists();
     }
     
}