import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
import java.awt.image.BufferedImage;

public class Tycoon extends JFrame{
  static Random rnd = new Random();
  static String ore = "0", miner = "0";
  static double click = 1,cash, price = rnd.nextDouble(),total=0.0,ops = 0.0,drillPrice = 2500.0,pricePast1 = 0,pricePast2 = 0,pricePast3 = 0,pricePast4 = 0;
  static int  miners = 0,minerPrice = 10, i= 0, excavatorPrice = 150,excavators = 0,  upPrice = 50, k, drills, clickTime = 15, o = 0, hours, time;
  static DecimalFormat df = new DecimalFormat("#.##");
  static Scanner read;
  //[ore 0] - [miners 1] - [OPS 2] - [cash 3] - [ore price 4] -[past prices 5 6 7 8] - [miner count 9] - [excavator count 10] - [drills count 11]
  static JFrame frame = new JFrame();
  static boolean loop0 = false,loop1,loop2,loop3;
  static File file = new File("Tycoon.txt");
  static JButton[] buttons = {new JButton("   Mine   ("+df.format(click)+" Ore/Click)"),           new JButton("Miner($"+minerPrice+") +1 OPS",  new ImageIcon("Pictures//Pickaxe.JPG"))       ,new JButton("Excavator($"+excavatorPrice+") +2 OPS", new ImageIcon("Pictures//Exc.JPG")), new JButton("Sell", new ImageIcon("Pictures//1.JPG")),         new JButton("New"), new JButton("Load"), new JButton("+0.1 "+upPrice), new JButton("Drill ($"+drillPrice+") + 5 OPS",new ImageIcon("Pictures//drill.JPG"))};
  //[click to mine 0] - [buy nimer 1] - [buy excavator 2] - [sell 3] - [new 4] - [load 5] - [clicker upgrade 6] - [Electric drill 7]
  static JBox box1 = JBox.hbox(
                               JBox.hbox(buttons[4],buttons[5])
                                 
                              );
  
  static JProgressBar bar = new JProgressBar();
  static JLabel[] labels = {new JLabel("Ore : "+df.format(total)),new JLabel("Miners : "+miners),new JLabel("OPS : "+(ops)), new JLabel("$"+cash), new JLabel("Ore Price"), new JLabel("1 day(s) ago : "),new JLabel("2 day(s) ago : "),new JLabel("3 day(s) ago : "),new JLabel("4 day(s) ago : "), new JLabel("x"+miners), new JLabel("x"+excavators), new JLabel("x"+drills)};
  static JRootPane panel;
  static JLabel background = new JLabel(new ImageIcon("Pictures//TycoonBackground.JPG"));
  static JPanel backGround = new JPanel();
  
  
  
  public static void wait (int ms)
  {
    try{
      Thread.sleep(ms);
    }catch(Exception f){ }
  }
  
  public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException, NumberFormatException{ 
    for(;;){
      try{
        File pic = new File("Pictures//TycoonBackground.JPG");
        
        read = new Scanner(file);
        panel = new JRootPane();
        panel.setLayout(null);
        
        
        background.setBounds(0,0,800,800);
        panel.setContentPane(background);
        
        
        
        buttons[0].setPreferredSize(new Dimension(50,50));
        frame.setSize(800,800);
        frame.setTitle("Ore Clicker");
        labels[4].setText("Ore Price : $"+df.format(price));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        labels[0].setFont(new Font("Windings",Font.BOLD, 50));
        labels[1].setFont(new Font("Comic Sans",Font.BOLD, 50));
        labels[3].setFont(new Font("Comic Sans",Font.BOLD, 50));
        labels[4].setFont(new Font("Comic Sans",Font.BOLD, 15));
        labels[2].setFont(new Font("Comic Sans",Font.BOLD, 25));
        labels[9].setFont(new Font("Comic Sans",Font.BOLD, 35));
        labels[10].setFont(new Font("Comic Sans",Font.BOLD, 35));
        labels[11].setFont(new Font("Comic Sans",Font.BOLD, 35));
        
        //x,y,width,height
        
        
        
        
        buttons[4].addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
            try{
              new File("Tycoon.txt").createNewFile();
              frame.remove(box1);
              labels[0].setBounds(275,0,500,50);
              panel.getContentPane().add(labels[0]); //ore amount
              labels[3].setBounds(325,50,500,50);
              panel.getContentPane().add(labels[3]); //cash amount
              labels[2].setBounds(325,100,300,50);
              panel.getContentPane().add(labels[2]); //OPS
              buttons[0].setBounds(250,325,200,50);
              panel.getContentPane().add(buttons[0]); //click to mine
              buttons[6].setBounds(450,325,75,50);
              panel.getContentPane().add(buttons[6]); //upgrade clicker
              buttons[1].setBounds(0,500,250,50);
              panel.getContentPane().add(buttons[1]); //buy miner
              buttons[2].setBounds(0,550,250,50);
              panel.getContentPane().add(buttons[2]); //buy excavator
              buttons[3].setBounds(325,715,150,50);
              panel.getContentPane().add(buttons[3]); //sell ore
              labels[4].setBounds(500,575,150,50);
              panel.getContentPane().add(labels[4]); //ore price
              bar.setBounds(490,525,200,50);
              labels[5].setBounds(500,625,150,50);
              panel.getContentPane().add(labels[5]);
              labels[6].setBounds(500,675,150,50);
              panel.getContentPane().add(labels[6]);
              labels[7].setBounds(500,725,150,50);
              panel.getContentPane().add(labels[7]);
              labels[8].setBounds(500,775,150,50);
              panel.getContentPane().add(labels[8]);
              buttons[7].setBounds(0,600,250,50);
              panel.getContentPane().add(buttons[7]); //drill buttons
              labels[9].setBounds(250,500,150,50);
              panel.getContentPane().add(labels[9]);
              labels[10].setBounds(250,550,150,50);
              panel.getContentPane().add(labels[10]);
              labels[11].setBounds(250,600,150,50);
              panel.getContentPane().add(labels[11]);
              frame.add(bar);
              
              frame.add(panel.getContentPane());
              
              
            }catch(Exception f){
            }
          }
        });
        buttons[6].addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
            if(cash>=upPrice){
              
              cash-=upPrice;
              click+=1;
              upPrice = 50+(15*k);
              buttons[6].setText("+ $"+upPrice);
              labels[3].setText("$"+df.format(cash));
              buttons[0].setText("   Mine   ("+df.format(click)+" Ore/Click)");
              k++;
            }
          }
        });
        buttons[7].addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
            if(cash>=drillPrice){
              
              cash-=drillPrice;
              drills++;
              drillPrice = 2500+(1.13*drills);
              ops += 5;
              buttons[7].setText("Drill ($"+drillPrice+") + 3 OPS");
              labels[3].setText("$"+df.format(cash));
              labels[2].setText("OPS : ");
              labels[11].setText("x"+drills);
            }
          }
        });
        buttons[5].addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
            if(file.exists()){
              while(read.hasNextLine()){
                total = Double.valueOf(read.nextDouble());
                labels[0].setText("Ore : "+total);
                ops = Double.valueOf(read.nextDouble());
                miner = Double.toString(ops);
                labels[2].setText("MPS : "+(ops));
                cash = Double.valueOf(read.nextDouble());
                click = Double.valueOf(read.nextDouble());
                miners = Integer.valueOf(read.nextInt());
                labels[9].setText("x"+miners);
                excavators = Integer.valueOf(read.nextInt());
                labels[10].setText("x"+excavators);
                labels[3].setText("$"+df.format(cash));
                labels[2].setText("OPS : "+df.format(ops));
                minerPrice = (10+(2*miners));
                buttons[1].setText("Miner($"+minerPrice+") +1 OPS");
                excavatorPrice = (150+(10*excavators));
                buttons[2].setText("Excavator($"+excavatorPrice+") +2 OPS");
                k = Integer.valueOf(read.nextInt());
                upPrice = (50+(15*k));
                buttons[0].setText("   Mine   ("+df.format(click)+" Ore/Click)");
                buttons[6].setText("+1 "+upPrice);
                drills = Integer.valueOf(read.nextInt());
                labels[11].setText("x"+drills);
                
                
                
                labels[0].setBounds(275,0,500,50);
                panel.getContentPane().add(labels[0]); //ore amount
                labels[3].setBounds(325,50,500,50);
                panel.getContentPane().add(labels[3]); //cash amount
                labels[2].setBounds(325,100,300,50);
                panel.getContentPane().add(labels[2]); //OPS
                buttons[0].setBounds(250,325,200,50);
                panel.getContentPane().add(buttons[0]); //click to mine
                buttons[6].setBounds(450,325,75,50);
                panel.getContentPane().add(buttons[6]); //upgrade clicker
                buttons[1].setBounds(0,500,250,50);
                panel.getContentPane().add(buttons[1]); //buy miner
                buttons[2].setBounds(0,550,250,50);
                panel.getContentPane().add(buttons[2]); //buy excavator
                buttons[3].setBounds(325,715,150,50);
                panel.getContentPane().add(buttons[3]); //sell ore
                labels[4].setBounds(500,575,150,50);
                panel.getContentPane().add(labels[4]); //ore price
                bar.setBounds(490,525,200,50);
                labels[5].setBounds(500,625,150,50);
                panel.getContentPane().add(labels[5]);
                labels[6].setBounds(500,675,150,50);
                panel.getContentPane().add(labels[6]);
                labels[7].setBounds(500,725,150,50);
                panel.getContentPane().add(labels[7]);
                labels[8].setBounds(500,775,150,50);
                panel.getContentPane().add(labels[8]);
                buttons[7].setBounds(0,600,250,50);
                panel.getContentPane().add(buttons[7]); //drill buttons
                labels[9].setBounds(250,500,150,50);
                panel.getContentPane().add(labels[9]);
                labels[10].setBounds(250,550,150,50);
                panel.getContentPane().add(labels[10]);
                labels[11].setBounds(250,600,150,50);
                panel.getContentPane().add(labels[11]);
                frame.add(bar);
                
                frame.add(panel.getContentPane());
                
                
                
              }
              frame.remove(box1);
              
              
              
              read.close();
            }
            else{
              Func.pr("WRONG");
            }
          }
          
        });
        backGround.setSize(700,700);
        backGround.setSize(700,700);
        frame.add(box1);
        frame.setVisible(true);
        bar.setStringPainted(true);
        bar.setBorderPainted(true);
        buttons[0].addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
            total += click;
            labels[0].setText("Ore : "+df.format(total));
            
            
          }
        });
        buttons[1].addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
            
            if(cash>=minerPrice){
              ops +=1;
              miners+=1;
              cash-=minerPrice;
              labels[3].setText("$"+df.format(cash));
              labels[0].setText("Ore : "+df.format(total));
              labels[2].setText("OPS : "+(df.format(ops)));
              minerPrice=(10+(2*((miners))));
              buttons[1].setText("Miner($"+minerPrice+") +1 OPS");
              labels[9].setText("x"+miners);
              
              
            }
            
          }
        });
        frame.addWindowListener(new WindowAdapter()
                                  {
          
          public void windowClosing(WindowEvent e)
          {
            try{
              FileWriter writer = new FileWriter(file);
              writer.write(Double.toString(total));
              writer.write("\n"+ops);
              writer.write("\n"+cash);
              writer.write("\n"+click);
              writer.write("\n"+miners);
              writer.write("\n"+excavators);
              writer.write("\n"+k);
              writer.write("\n"+drills);
              System.out.println("Saved!");
              writer.close();
              e.getWindow().dispose();
            }catch(Exception f){
              Func.pr("yeet");
            }
          }
        });
        
        buttons[2].addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
            
            if(cash>=excavatorPrice){
              excavators++;  
              excavatorPrice = 150+(5*excavators);
              cash -= excavatorPrice;
              labels[3].setText("$"+df.format(cash));
              ops+=2;
              labels[2].setText("OPS : "+ops);
              buttons[2].setText("Excavator($"+excavatorPrice+") +2 OPS");
              labels[10].setText("x"+excavators);
              
            }
          }
        });
        buttons[3].addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
            cash += total*price;
            total = 0;
            labels[0].setText("Ore : "+total);
            labels[3].setText("$"+df.format(cash));
          }
        });
        
        for(;;){
          wait(10);
          i++;
          
          ore = Double.toString(total);
          if(i%100==0){
            total+= ops;
            labels[0].setText("Ore : "+df.format(total));
          }
          if(i%17==0){
            hours+=4;
            bar.setValue(hours);
            time+=1;
            bar.setString(time+":00");
            if(hours>100||time>24){
              hours = 0;
              time = 0;
              bar.setValue(hours);
              bar.setString(time+":00");
              pricePast3 = pricePast2;
              labels[7].setText("3 Day(s) ago : $"+df.format(pricePast3));
              pricePast2 = pricePast1;
              labels[6].setText("2 Day(s) ago : $"+df.format(pricePast2));
              pricePast1 = price;
              labels[5].setText("1 Day(s) ago : $"+df.format(pricePast1));
              
              price = rnd.nextDouble();
              labels[4].setText("Ore Price : $"+df.format(price));
            }
          }
          
          
        }
        
      }catch(FileNotFoundException e){
        new FileWriter(file).write("");
        
      }
    }
  }
  
}
