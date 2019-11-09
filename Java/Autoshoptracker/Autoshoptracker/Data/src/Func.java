
import java.util.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Calendar;


public class Func {
     
     private static int i, p, temp, number;
     private static boolean f = true;
     
     //bubble sort
     //////////////////////////////////////////////////////////////////////////////
     public static void bubsort(int arr[]){
          try{//bubble sort
               int p = arr.length;
               i = 0;
               while(f){
                    if(i==p){
                         f = false;
                        Func.print("p");
                    }
                    else if(arr[i]>arr[i+1]){
                         int temp = arr[i];
                         arr[i] = arr[i+1];
                         arr[i+1]= temp;
                         i = 0;
                    }
                    else{
                         i++;
                    }
               }
          }catch(Exception e){
               print("Sorted !");
          }
     }
     ///////////////////////////////////////////////////////////////////////////////
     
     
     
     //getting different parts of time for my own use
     /////////////////////////////////////////////////////////////////////////////// 
     public static int YEAR, MONTH, DAY, HOUR, MINUTE, SECOND, AMPM; 
     
     public static int getYear(){
       YEAR = Calendar.getInstance().get(Calendar.YEAR);
       return YEAR;
     }
     public static int getMonth(){
       MONTH = Calendar.getInstance().get(Calendar.MONTH);
       return MONTH+1;
     }
     public static int getDay(){
       DAY = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
       return DAY;
     }
     public static int getHour(){
       HOUR = Calendar.getInstance().get(Calendar.HOUR);
       return HOUR;
     }
     public static int getMinute(){
       MINUTE = Calendar.getInstance().get(Calendar.MINUTE);
       return MINUTE;
     }
     public static int getSecond(){
       SECOND = Calendar.getInstance().get(Calendar.SECOND);
       return SECOND;
     }
     public static String getAMPM(){
       AMPM = Calendar.getInstance().get(Calendar.AM_PM);
       switch(AMPM){
         case 0:
           return "am";
           
           
         case 1:
           return "pm";
           
       }
       return "";
     }
     /////////////////////////////////////////////////////////////////////////////// 
     
     //making playing sounds quick and easy for later use 
     /////////////////////////////////////////////////////////////////////////////// 
     
     public static Clip clip;
     
     public static void playSound(String str){
          try{
               clip = AudioSystem.getClip();
               clip.open(AudioSystem.getAudioInputStream(new File(str)));
               clip.start();
          }catch(Exception e){
               print("WRONG");
          }
     }
     
     public static void stopSound(){
          try{
               clip.stop();
          }catch(Exception e){
               print("WRONG");
          }
     }
     public static void enter(){
          In.getString("<<press enter>>");
     }
     public static void checkArr(){
     }
     // printing 
     ///////////////////////////////////////////////////////////////////////////////  
     public static void print(Object...s){
       for(int i = 0; i<s.length;i++){
         if(s[i] instanceof int[])
           for(int o = 0;  o<((int[])(s[i])).length;o++){
             System.out.print(((int[])(s[i]))[o]+" ");
           }
         
         else if(s[i] instanceof char[])
           for(int o = 0;  o<((char[])(s[i])).length;o++){
             System.out.print(((char[])s[i])[o]+" ");
           }
           
         
         else if(s[i] instanceof long[]){
           for(int o = 0;  o<((long[])(s[i])).length;o++){
             System.out.print(((long[])s[i])[o]+" ");
           }
         }
         else if(s[i] instanceof double[]){
           for(int o = 0;  o<((double[])(s[i])).length;o++){
             System.out.print(((double[])s[i])[o]+" ");
           }
         }
         else if(s[i] instanceof boolean[]){
           for(int o = 0;  o<((boolean[])(s[i])).length;o++){
             System.out.print(((boolean[])s[i])[o]+" ");
           }
         }
         else if(s[i] instanceof float[]){
           for(int o = 0;  o<((float[])(s[i])).length;o++){
             System.out.print(((float[])s[i])[o]+" ");
           }
         }
         else if(s[i] instanceof String[]){
           for(int o = 0;  o<((String[])(s[i])).length;o++){
             System.out.print(((String[])s[i])[o]+" ");
           }
         }
         else
              System.out.print(s[i]);
         
       }
     }
     
    
     
     
     public static void println(Object...s){
       
       for(int i = 0; i<s.length;i++){
         if(s[i] instanceof int[])
           for(int o = 0;  o<((int[])(s[i])).length;o++){
             System.out.print(((int[])(s[i]))[o]+" ");
             print("\n");
           }
         
         else if(s[i] instanceof char[])
           for(int o = 0;  o<((char[])(s[i])).length;o++){
             System.out.print(((char[])s[i])[o]+" ");
             print("\n");
           }
           
         
         else if(s[i] instanceof long[]){
           for(int o = 0;  o<((long[])(s[i])).length;o++){
             System.out.print(((long[])s[i])[o]+" ");
             print("\n");
           }
         }
         else if(s[i] instanceof double[]){
           for(int o = 0;  o<((double[])(s[i])).length;o++){
             System.out.print(((double[])s[i])[o]+" ");
             print("\n");
           }
         }
         else if(s[i] instanceof boolean[]){
           for(int o = 0;  o<((boolean[])(s[i])).length;o++){
             System.out.print(((boolean[])s[i])[o]+" ");
             print("\n");
           }
         }
         else if(s[i] instanceof float[]){
           for(int o = 0;  o<((float[])(s[i])).length;o++){
             System.out.print(((float[])s[i])[o]+" ");
           }
         }
         else if(s[i] instanceof String[]){
           for(int o = 0;  o<((String[])(s[i])).length;o++){
             System.out.print(((String[])s[i])[o]+" ");
             print("\n");
           }
         }
         else
              System.out.print(s[i]);
         print("\n");
         
       }
     }
     
     
     
    
     /////////////////////////////////////////////////////////////////////////////// 
     
     
     
     
     public static String setString(Object s){
       return s.toString();
       
     }
  
     
     
     
     
     // Printing an error message
     ///////////////////////////////////////////////////////////////////////////////  
     public static void err(String s){
          System.err.println(s);
          
          
     }
     
     public static void quit(){
       System.exit(0);
     }
     
     //printing out the string one character at a time with delay between them
     /////////////////////////////////////////////////////////////////////////////// 
     
     public static void type(String s, int speed){
          String[] r = s.split("");
          
          for(int i = 0; i<r.length;i++){
               try{
                    Thread.sleep(speed);
                    System.out.print(r[i]);
               }catch(Exception e){
               }
          }
          
     }
     public static void type(String s ){
          String[] r = s.split("");
          int speed = 20;
          for(int i = 0; i<r.length;i++){
               try{
                    Thread.sleep(speed);
                    System.out.print(r[i]);
               }catch(Exception e){
               }
          }
          
     }
     /////////////////////////////////////////////////////////////////////////////// 
}

