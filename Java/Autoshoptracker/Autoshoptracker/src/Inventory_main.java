import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.*;
import javax.imageio.ImageIO;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;


public class Inventory_main {
  
  //variable town
  
  static Object terry;//terry our lord and saviour
  static JTextField quickUpdateField;
  static int MAIN_PAGE = 0;
  public static String[] filterNames = {"All","Steel","Electronics","Auto Parts","Welding"};
  public static String[] itemLists = {"Steel","Electronics","Auto Parts","Welding"};
  
  
  static JTextField imagePath = new JTextField(" ");
  static JTextField itemAddCode = new JTextField("Item Code");
  static JTextField itemAddStock = new JTextField("Item Stock");
  static JTextField itemAddDesc = new JTextField("Item description");
  static JTextField itemAddName = new JTextField("Item name");
  static JButton addItemImage = new JButton("Import Image");
  static JFrame loadingFrame;
  static boolean addItemBoolean = false;
  static ImageIcon[][] images = new ImageIcon[4][6];
  static int row = 0;
  static Border etch = BorderFactory.createEtchedBorder();
  static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  static String userHomePath = System.getProperty("user.dir");
  static FIO itemNames = new FIO(new File(userHomePath+"\\itemNames.txt"));
  static FIO itemStock = new FIO(new File(userHomePath+"\\itemStock.txt"));
  static FIO itemCodes = new FIO(new File(userHomePath+"\\itemCodes.txt"));
  static FIO itemimages = new FIO(new File(userHomePath+"\\itemImages.txt"));
  static FIO itemDescription = new FIO(new File(userHomePath+"\\ItemDescriptions.txt"));
  
  public static JButton update = new JButton("Update");
  static int increments = 100;
  static JFrame uploadImageFrame = new JFrame("Upload an Image");
  static JFrame addFrame = new JFrame("Add an Item");
  static JFileChooser chooser = new JFileChooser();
  static FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
  static JFrame frame = new JFrame();
  static JComboBox<String> filterMenu = new JComboBox<String>(filterNames);
  static JBox scrollBox = JBox.vbox();
  static JBox mainbox;
  static JButton newItem = new JButton("Add Item");
  static JPanel scrollBoxPanel = new JPanel();
  static JScrollPane scrollPane;
  static JPanel boxes[] = {
    new JPanel(),new JPanel(),new JPanel(), new JPanel()};
  public static JLabel[] titles = {new JLabel("All"),new JLabel("Steel"),new JLabel("Electronics"),new JLabel("Auto Parts"),new JLabel("Welding")};
  static JLabel titleText = new JLabel("Mr Poortinga's Cool\n Inventory Managment™");
  static ArrayList<JComboBox<String>> categoryBoxes = new ArrayList<JComboBox<String>>();
  static ArrayList<JComboBox<String>> itemBoxes = new ArrayList<JComboBox<String>>();
  static ArrayList<JTextField> stockFields = new ArrayList<JTextField>();
  static int i = 0, o;
  static JFrame updateStock = new JFrame();
  static boolean setupDone = false;
  static String newImage = "";
  static JProgressBar loadingBar = new JProgressBar();
  static int loadingProgress = 0;
  static JButton removeButton;
  
  
  //please god help me from these array lists
  static ArrayList<String> steelItems = new ArrayList<String>();
  static ArrayList<String> electronicsItems =  new ArrayList<String>();
  static ArrayList<String> autoItems =  new ArrayList<String>();
  static ArrayList<String> weldingItems =  new ArrayList<String>();
  static ArrayList<String> miscItems =  new ArrayList<String>();
  static ArrayList<ArrayList<String>> categoryNameList =  new ArrayList<ArrayList<String>>();
  
  static ArrayList<String> steelImages = new ArrayList<String>();
  static ArrayList<String> electronicsImages =  new ArrayList<String>();
  static ArrayList<String> autoImages =  new ArrayList<String>();
  static ArrayList<String> weldingImages =  new ArrayList<String>();
  static ArrayList<String> miscImages =  new ArrayList<String>();
  static ArrayList<ArrayList<String>> categoryImageList =  new ArrayList<ArrayList<String>>();
  
  static ArrayList<String> steelCodes = new ArrayList<String>();
  static ArrayList<String> electronicsCodes =  new ArrayList<String>();
  static ArrayList<String> autoCodes =  new ArrayList<String>();
  static ArrayList<String> weldingCodes =  new ArrayList<String>();
  static ArrayList<String> miscCodes =  new ArrayList<String>();
  static ArrayList<ArrayList<String>> categoryCodeList =  new ArrayList<ArrayList<String>>();
  static JMenuBar menuBar;
  static ArrayList<JLabel> steelStock = new ArrayList<JLabel>();
  static ArrayList<JLabel> electronicsStock =  new ArrayList<JLabel>();
  static ArrayList<JLabel> autoStock =  new ArrayList<JLabel>();
  static ArrayList<JLabel> weldingStock =  new ArrayList<JLabel>();
  static ArrayList<JLabel> miscStock =  new ArrayList<JLabel>();
  static ArrayList<ArrayList<JLabel>> categoryStockList =  new ArrayList<ArrayList<JLabel>>();
  
  static ArrayList<String> steelDesc = new ArrayList<String>();
  static ArrayList<String> electronicsDesc =  new ArrayList<String>();
  static ArrayList<String> autoDesc =  new ArrayList<String>();
  static ArrayList<String> weldingDesc =  new ArrayList<String>();
  static ArrayList<String> miscDesc =  new ArrayList<String>();
  static ArrayList<ArrayList<String>> categoryDescList =  new ArrayList<ArrayList<String>>();
  
  static ArrayList<JButton> steelButtons = new ArrayList<JButton>();
  static ArrayList<JButton> electronicButtons = new ArrayList<JButton>();
  static ArrayList<JButton> autoButtons = new ArrayList<JButton>();
  static ArrayList<JButton> weldingButtons = new ArrayList<JButton>();
  static ArrayList<ArrayList<JButton>> categoryButtonsList = new ArrayList<ArrayList<JButton>>();
  
  
  static boolean init = false;
  static String user = "autoshopstocker.noreply@gmail.com";
  static String pass = "8437851303";
  static Properties props = new Properties();
  static Session session;
  static String username, password;
  
  public static void send(String sentTo, String messages){
      send(sentTo, "No Subject", messages);
   }
   public static void send(String sentTo, String subject, String messages){
      session = Session.getInstance(props,
                                    new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
         }
      });
      try {
         
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress(username));
         message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(sentTo));
         message.setSubject(subject);
         
         message.setText(messages);
         
         Transport.send(message);
         
         
         
      } catch (MessagingException e) {
         e.printStackTrace(); 
         
      }
   }
  
  public static void setup(int filterMenu)throws java.io.IOException{
    try{
    if(init == false){
      loadingBar.setString("Loading graphics");
    }
    if(filterMenu == 0){
      for(int i = 0;i<categoryCodeList.size();i++){
        boxes[i].removeAll();
        boxes[i] = new JPanel();
        JBox bigBox = JBox.hbox(JBox.vbox(
                                          
                                          JBox.vglue()
                                         ));
        
        for(int j = 0;j<categoryCodeList.get(i).size();j++){
         String[] arr = categoryStockList.get(i).get(j).getText().split(" ");
         
          Item item = new Item(categoryCodeList.get(i).get(j).toString());
          
          if(Integer.parseInt(arr[2])<=2) {
           categoryStockList.get(i).get(j).setForeground(Color.red);
           
          }
          else {
           categoryStockList.get(i).get(j).setForeground(new Color(187, 190, 196));
              
          }
          
          item.getStockText().setText("Stock : ");
          JBox item_box = JBox.vbox(
                                    JBox.vbox( 
                                              JBox.hbox(new JLabel("     "),categoryButtonsList.get(i).get(j)),
                                              JBox.vbox(
                                                        
                                                        JBox.hbox(item.getRemoveButton(),new JLabel(" "),categoryStockList.get(i).get(j),new JLabel("   "),item.getDescButton())
                                                          
                                                       )
                                             ));
          item_box.setMaximumSize(new Dimension(180,185));
          bigBox.setMaximumSize(new Dimension(8000,185));
          
          bigBox.setBorder(javax.swing.BorderFactory.
                             createTitledBorder(etch, filterNames[i+1], javax.swing.border.
                                                  TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.
                                                  TitledBorder.DEFAULT_POSITION, null, new Color(187, 190, 196)));
          
          ((TitledBorder)bigBox.getBorder()).setTitleFont(new Font("Segoe UI Symbol", Font.BOLD, 25));
          bigBox.add( JBox.hbox(
                                
                                item_box,
                                JBox.hbox(new JLabel("   "))));
          
          item_box.setBorder(javax.swing.BorderFactory.
                               createTitledBorder(null, item.getNameS(),+ javax.swing.border.
                                                    TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.
                                                    TitledBorder.DEFAULT_POSITION, new Font("Fixedsys", Font.PLAIN, 12), new Color(187, 190, 196)));
          
        }
        
        scrollBox.setMaximumSize(new Dimension(8000,8000));
        scrollBox.add(bigBox);
        scrollBox.setBackground(new Color(54, 57, 62));
        scrollBox.setBorder(javax.swing.BorderFactory.
                              createTitledBorder(null, titleText.getText(),+ javax.swing.border.
                                                   TitledBorder.CENTER, javax.swing.border.
                                                   TitledBorder.CENTER, new Font("Franklin Gothic Medium", Font.BOLD, 40), new Color(187, 190, 196)));
      }
      
    }
    else{
      JBox bigBox = JBox.hbox(JBox.vbox(
                                        JBox.vglue()
                                       ));
      
      for(int j = 0;j<categoryNameList.get(filterMenu-1).size();j++){
        
        Item item = new Item(categoryCodeList.get(filterMenu-1).get(j).toString());
        String[] arr = categoryStockList.get(filterMenu-1).get(j).getText().split(" ");
        Func.print(arr);
        if(Integer.parseInt(arr[2])<=2) {
         categoryStockList.get(filterMenu-1).get(j).setForeground(Color.red);
         
        }
        else {
         categoryStockList.get(filterMenu-1).get(j).setForeground(new Color(187, 190, 196));
            
        }
        JBox item_box = JBox.vbox(
                                  JBox.vbox( 
                                            JBox.hbox(item.getImage()),
                                            JBox.vbox(
                                                      
                                                      JBox.hbox(item.getRemoveButton(),new JLabel(" "),categoryStockList.get(filterMenu-1).get(j),new JLabel("   "),item.getDescButton())
                                                        
                                                     )
                                           ));
        item_box.setMaximumSize(new Dimension(180,185));
        bigBox.setMaximumSize(new Dimension(8000,150));
        bigBox.add( JBox.hbox(
                              
                              item_box,
                              JBox.hbox(new JLabel("   "))));
        
        item_box.setBorder(javax.swing.BorderFactory.
                             createTitledBorder(null, item.getNameS(), javax.swing.border.
                                                  TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.
                                                  TitledBorder.DEFAULT_POSITION, new Font("Fixedsys", Font.PLAIN, 12), new Color(187, 190, 196)));
        
      }
      
      scrollBox.setMaximumSize(new Dimension(8000,8000));
      scrollBox.add(bigBox);
      scrollBox.setBackground(new Color(54, 57, 62));
    }
  }catch(Exception g){g.printStackTrace();}
  }
  
  static JProgressBar savingBar = new JProgressBar();
  static JFrame savingFrame = new JFrame();
  public static JMenuBar createMenuBar() throws java.io.IOException{
    try{
    
    JMenu fileMenu, propMenu;
    
    
    menuBar = new JMenuBar();
    menuBar.setBackground(new Color(54, 57, 62));
    menuBar.setForeground(new Color(217, 217, 217));
    fileMenu = new JMenu("File");
    fileMenu.setForeground(new Color(217, 217, 217));
    fileMenu.setMnemonic(KeyEvent.VK_F);
    menuBar.add(fileMenu);
    JMenuItem newProjectItem = new JMenuItem("New Project", KeyEvent.VK_N);
    newProjectItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
    fileMenu.add(newProjectItem);
    JMenuItem updateStock = new JMenuItem("Update Stock", KeyEvent.VK_U);
    updateStock.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.ALT_MASK));
    fileMenu.add(updateStock);
    JMenuItem addItem = new JMenuItem("Add Item", KeyEvent.VK_A);
    addItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
    fileMenu.add(addItem);
    JButton close = new JButton("Close");
    
    
    close.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        try{
          username = user;
          password = pass;
          if(user.contains("gmail")||user.contains("@ocdsb"))
             props.put("mail.smtp.host", "smtp.gmail.com");
          else if(user.contains("@hotmail")||user.contains("@outlook")||user.contains("@live"))
             props.put("mail.smtp.host", "smtp-mail.outlook.com");
          props.put("mail.smtp.auth", "true");
          props.put("mail.smtp.starttls.enable", "true");
          props.put("mail.smtp.port", "587");
          
          
          
          
          try{
            for(int k = 0; k<categoryStockList.size();k++){
              for(int h = 0;h<categoryStockList.get(k).size();h++){
                Item item = new Item(categoryCodeList.get(k).get(h).toString());
                if(item.getStockInt() <2){
                  send("jwell5@ocdsb.ca",(item.getName().getText().toUpperCase()+" NEEDS RESTOCKING"), "The stock of the "+item.getName().getText()+" is at below recommended levels. \n\nLocate your nearest Home Depot™ to purchase replacements/resupply");
                }
              }
            }
          }catch(Exception g){}
          
          itemNames.overwrite();
          itemStock.overwrite();
          itemCodes.overwrite();
          itemimages.overwrite();
          itemDescription.overwrite();
          for(int i = 0;i<categoryNameList.size();i++){ //saves item names to text file
            for(int j=0;j<categoryNameList.get(i).size();j++){
              
              Item item = new Item(categoryCodeList.get(i).get(j).toString());
              itemNames.add(item.getNameS());
            }
            itemNames.add("-");
          }
          for(int i = 0;i<categoryCodeList.size();i++){
            for(int j=0;j<categoryCodeList.get(i).size();j++){
              Item item = new Item(categoryCodeList.get(i).get(j).toString());
              itemCodes.add(item.getCode());
            }
            itemCodes.add("-");
          }
          for(int i = 0;i<categoryImageList.size();i++){
            for(int j=0;j<categoryImageList.get(i).size();j++){
              itemimages.add(categoryImageList.get(i).get(j).toString());
            }
            itemimages.add("-");
          }
          for(int i = 0;i<categoryStockList.size();i++){
            for(int j=0;j<categoryStockList.get(i).size();j++){
              Item item = new Item(categoryCodeList.get(i).get(j).toString());
              itemStock.add("Stock : "+item.getStockInt());
            }
            itemStock.add("-");
          }
      for(int i = 0;i<categoryDescList.size();i++){ //saves item Descriptions to text file
            for(int j=0;j<categoryDescList.get(i).size();j++){
              
              Item item = new Item(categoryCodeList.get(i).get(j).toString());
              itemDescription.add(item.getDesc());
            }
            itemDescription.add("-");
          }
      frame.setVisible(false); 
        }catch(java.io.IOException f){}
        System.exit(0);
      }});
    addItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
       if(addItemBoolean==false) {
        addFrame.setSize(500,500);
        addFrame.setLocation(frame.getWidth()/4,frame.getHeight()/4);
        final JComboBox<String> addItemCategory = new JComboBox<String>(itemLists);
        addItemCategory.setSelectedIndex(0);
        addItemCategory.setBounds(20,0,150,30);
        addFrame.add(addItemCategory);
        addFrame.setBackground(new Color(54, 57, 62));
        
        itemAddName.setText("Item name");
        itemAddName.setBounds(20,30,200,30);
        addFrame.add(itemAddName);
        
        itemAddDesc.setText("Item description");
        itemAddDesc.setBounds(20,150,200,30);
        addFrame.add(itemAddDesc);
        itemAddName.addFocusListener(new FocusListener(){
          @Override
          public void focusGained(FocusEvent e){
            if(itemAddName.getText().equals("Item name")||itemAddName.getText().equals("Invalid name")){
              itemAddName.setText("");
              itemAddName.setForeground(Color.black);
            }
            else{
              Func.print("ya don goofed"); 
            }
          }
          public void focusLost(FocusEvent e){
            if(itemAddName.getText().equals(""))
              itemAddName.setText("Item name");
            else
              Func.print("ya don goofed");
          }
        });
        itemAddDesc.addFocusListener(new FocusListener(){
          @Override
          public void focusGained(FocusEvent e){
            if(itemAddDesc.getText().equals("Item description")){
              itemAddDesc.setText("");
            }
            else{
            }
          }
          public void focusLost(FocusEvent e){
            if(itemAddDesc.getText().equals(""))
              itemAddDesc.setText("Item description");
          }
        });
        
        
        itemAddStock.setText("Item Stock");
        itemAddStock.setBounds(20,60,200,30);
        addFrame.add(itemAddStock);
        
        itemAddStock.addFocusListener(new FocusListener(){
          @Override
          public void focusGained(FocusEvent e){
            if(itemAddStock.getText().equals("Item Stock")||itemAddStock.getText().equals("Number Required")||itemAddStock.getText().equals("Negative inventory is not allowed")){
              itemAddStock.setText("");
              itemAddStock.setForeground(Color.black);
            }
            else{
              Func.print("ya don goofed"); 
            }
          }
          public void focusLost(FocusEvent e){
            if(itemAddStock.getText().equals("")){
              itemAddStock.setText("Item Stock");
            }
            else
              Func.print("ya don goofed");
          }
        });
        
        itemAddCode.setText("Item Code");
        itemAddCode.setBounds(20,90,200,30);
        addFrame.add(itemAddCode);
        itemAddCode.addFocusListener(new FocusListener(){
          @Override
          public void focusGained(FocusEvent e){
            if(itemAddCode.getText().equals("Item Code")||itemAddCode.getText().equals("3 Characters max")){
              itemAddCode.setText("");
              itemAddCode.setForeground(Color.black);
            }
            else{
              Func.print("ya don goofed"); 
            }
          }
          public void focusLost(FocusEvent e){
            if(itemAddCode.getText().equals("")){
              itemAddCode.setText("Item Code");
            }
            else{
              itemAddCode.setText(itemAddCode.getText().toUpperCase());
            }
          }
        });
        itemAddCode.addKeyListener(new KeyAdapter() {
          public void keyTyped(KeyEvent e) {
            int f = itemAddCode.getText().length();
            if(f >=3){
              
            }
            else{
              
            }
          }
        });
        
        
        imagePath.setText(" ");
        imagePath.setBounds(155,120,125,30);
        addFrame.add(imagePath);
        addItemImage.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent event) {
            chooser.setFileFilter(filter);
            chooser.setBounds(0,0,600,400);
            uploadImageFrame.add(chooser);
            uploadImageFrame.setLayout(null);
            uploadImageFrame.setSize(700,500);
            uploadImageFrame.setVisible(true);
            chooser.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent event) {
                try{
                  uploadImageFrame.setVisible(false);
                  newImage = chooser.getSelectedFile().getAbsolutePath();
                  imagePath.setText(newImage);
                }catch(NullPointerException e){}
              }});
          }});
        addItemImage.setBounds(20,120,125,30);
        addFrame.add(addItemImage);
        JButton submit = new JButton("Add Item");
        submit.setBounds(20,180,100,30);
        addFrame.add(submit);
        submit.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent event) {
            int validItemInput = 0;
            if(!itemAddName.getText().equals("Item name")){
              validItemInput++;
            }
            else{
              itemAddName.setText("Invalid name");
              itemAddName.setForeground(Color.red);
            }
            try{
              Integer.parseInt(itemAddStock.getText());
              if(Integer.parseInt(itemAddStock.getText())>=0) {
              validItemInput++;
              }
              else {
               itemAddStock.setText("Negative inventory is not allowed");
               itemAddStock.setForeground(Color.red);
              }
            }catch(NumberFormatException e){
              itemAddStock.setText("Number Required");
              itemAddStock.setForeground(Color.red);
            }
            
            
            if(itemAddCode.getText().length()<=3){
              
              validItemInput++;
            }
            else{
              itemAddCode.setText("3 Characters max");
              itemAddCode.setForeground(Color.red);
            }
            if(!imagePath.getText().equals("")){
              
              validItemInput++;
            }
            else{
              JOptionPane.showMessageDialog(frame, "Please Select An Image.");
            }
            if(validItemInput == 4){
              try{
              categoryDescList.get(addItemCategory.getSelectedIndex()).add(itemAddDesc.getText());
              categoryImageList.get(addItemCategory.getSelectedIndex()).add(imagePath.getText());
              categoryCodeList.get(addItemCategory.getSelectedIndex()).add(itemAddCode.getText());
              categoryNameList.get(addItemCategory.getSelectedIndex()).add(itemAddName.getText());
              categoryStockList.get(addItemCategory.getSelectedIndex()).add(new JLabel("Stock : "+itemAddStock.getText()));
              Item item = new Item(categoryCodeList.get(addItemCategory.getSelectedIndex()).get(categoryCodeList.get(addItemCategory.getSelectedIndex()).size()-1));
              categoryButtonsList.get(addItemCategory.getSelectedIndex()).add(item.getImage());
              Func.print(categoryNameList.get(addItemCategory.getSelectedIndex()).get(categoryNameList.get(addItemCategory.getSelectedIndex()).size()-1));
              Func.print(categoryStockList.get(addItemCategory.getSelectedIndex()).get(categoryStockList.get(addItemCategory.getSelectedIndex()).size()-1));
              
              addFrame.setVisible(false);
              }catch(IOException e){}
              try{
                scrollBox.removeAll();
                filterMenu.setPreferredSize(new Dimension(30,30));
                filterMenu.setOpaque(false);
                i = filterMenu.getSelectedIndex();
                Func.print(i);
                mainbox.remove(scrollPane);
                frame.remove(mainbox);
                scrollBoxPanel.remove(scrollBox);
                setup(MAIN_PAGE);
                scrollBox.setMaximumSize(new Dimension(900,100));
                mainbox.add(JBox.vbox(scrollPane));
                scrollBoxPanel.add(scrollBox, BorderLayout.CENTER);
                scrollBoxPanel.setMaximumSize(new Dimension(8000,100));
                scrollPane = new JScrollPane(scrollBoxPanel);
                scrollPane.setMaximumSize(new Dimension(8000,8000));
                scrollPane.getVerticalScrollBar().setUnitIncrement(16);
                scrollPane.setBackground(new Color(54, 57, 62));
                mainbox = JBox.vbox(
                                    JBox.hbox(
                                              JBox.vbox(
                )
                                             ),
                                    JBox.hbox(
                                              
                                              scrollPane 
                                             )
                                   );
                mainbox.setBackground(new Color(54, 57, 62));
                frame.add(mainbox);
                frame.repaint();
                frame.setVisible(true);
              }catch(java.io.IOException e){
                e.printStackTrace();
              }
            }
          }});
        addFrame.setBackground(Color.DARK_GRAY);
        addFrame.setLayout(null);
        addFrame.setVisible(true);
      }
       else {
        imagePath.setText(" ");
        itemAddCode.setText("Item Code");
        itemAddName.setText("Item name");
        itemAddDesc.setText("Item description");
       }
        
      }});
    updateStock.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        try{
          update();
        }catch(Exception e){
        e.printStackTrace();
        }
      }});
    menuBar.add(fileMenu);
    propMenu = new JMenu("Settings");
    propMenu.setForeground(new Color(217, 217, 217));
    propMenu.setMnemonic(KeyEvent.VK_F);
    menuBar.add(propMenu);
    JMenuItem propertiesItem = new JMenuItem("Properties  ",KeyEvent.VK_P);
    propertiesItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
    propMenu.add(propertiesItem);
    propertiesItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        JFrame propertiesFrame = new JFrame();
        JPanel propertiesPanel = new JPanel();
        propertiesPanel.setLayout(null);
        propertiesFrame.add(propertiesPanel);
        propertiesFrame.setSize(500,500);
        propertiesFrame.setLocation(frame.getSize().width/4, frame.getSize().height/4);
        propertiesFrame.setVisible(true);
      }});
    menuBar.add(new JLabel("   "));
    
    filterMenu.setMaximumSize(new Dimension(100,30));
    filterMenu.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        try{
          scrollBox.removeAll();
          filterMenu.setPreferredSize(new Dimension(30,30));
          i = filterMenu.getSelectedIndex();
          Func.print(i);
          mainbox.remove(scrollPane);
          frame.remove(mainbox);
          scrollBoxPanel.remove(scrollBox);
          setup(i);
          scrollBox.setMaximumSize(new Dimension(900,100));
          mainbox.add(JBox.vbox(scrollPane));
          scrollBoxPanel.add(scrollBox, BorderLayout.CENTER);
          scrollBoxPanel.setMaximumSize(new Dimension(8000,100));
          scrollPane = new JScrollPane(scrollBoxPanel);
          scrollPane.setMaximumSize(new Dimension(8000,8000));
          scrollPane.getVerticalScrollBar().setUnitIncrement(16);
          scrollPane.setBackground(Color.GRAY);
          mainbox = JBox.vbox(
                              JBox.hbox(
                                        JBox.vbox(
          )
                                       ),
                              JBox.hbox(
                                        
                                        scrollPane 
                                       )
                             );
          mainbox.setBackground(Color.BLACK);
          frame.add(mainbox);
          frame.repaint();
          frame.setVisible(true);
          
        }catch(java.io.IOException e){}
      }
    });
    menuBar.add(filterMenu);
//    String space = " ";
    menuBar.add(Box.createHorizontalGlue());
    close.setBackground(Color.red);
    close.setForeground(Color.white);
    close.setSize(menuBar.getHeight(),100);
    menuBar.add(close);
    return menuBar;
  }catch(Exception g){g.printStackTrace();return menuBar;}
  
  }
  
  
  public static void update(){
    try{
    if(setupDone == false){
      setupDone = true;
      Item item = new Item();
      JBox newBoxes = JBox.vbox(JBox.hglue(),new JLabel("To add stock use positive numbers i.e 5.  To remove stock use negative numbers i.e -5."),JBox.hglue());
      JPanel updt = new JPanel();
      updt.setLayout(null);
      updateStock.setUndecorated(true);
      
      for(i = 0; i<10;i++){
        categoryBoxes.add((JComboBox<String>)new JComboBox(item.getCategories().toArray()));
        categoryBoxes.get(i).setMaximumSize(new Dimension(50,50));
        categoryBoxes.get(i).setSelectedIndex(0);
        categoryBoxes.get(i).setPrototypeDisplayValue("XXXXXXXXXXXXXXX");
        itemBoxes.add((JComboBox<String>)new JComboBox(categoryNameList.get(categoryBoxes.get(i).getSelectedIndex()).toArray()));
        itemBoxes.get(i).setMaximumSize(new Dimension(50,50));
        stockFields.add(new JTextField("Insert stock change"));
        newBoxes.add(
                     JBox.hbox(new JLabel((i+1)+". "),
                               categoryBoxes.get(i),
                               itemBoxes.get(i),
                               JBox.vbox(
                                         new JLabel("      "),
                                         
                                         new JLabel("      "),
                                         stockFields.get(i)
                                           
                                        )
                              ));
      }
      newBoxes.setForeground(new Color(217, 217, 217));
      for(int o = 0; o<stockFields.size();o++){
        stockFields.get(o).addFocusListener(new FocusListener(){
          @Override
          public void focusGained(FocusEvent e){
            if(((JTextField)(e.getSource())).getText().equals("Insert stock change")||((JTextField)(e.getSource())).getText().equals("Number required")){
              ((JTextField)(e.getSource())).setText("");
            }
            else{
              
            }
          }
          public void focusLost(FocusEvent e){
            if(((JTextField)(e.getSource())).getText().equals(""))
              ((JTextField)(e.getSource())).setText("Insert stock change");
            
            
          }
        });
      }
      for(o = 0; o<itemBoxes.size()-1;o++){
        categoryBoxes.get(o).addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent event) {
            
            itemBoxes.get(categoryBoxes.indexOf((JComboBox<String>)event.getSource())).setModel(new DefaultComboBoxModel(categoryNameList.get(((JComboBox<String>)(event.getSource())).getSelectedIndex()).toArray()));
            
          }});
      }
      JButton submit = new JButton("Submit");
      submit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent event) {
          for(int i = 0;i<stockFields.size();i++){
            try{
             String[] arrs = categoryStockList.get(categoryBoxes.get(i).getSelectedIndex()).get(itemBoxes.get(i).getSelectedIndex()).getText().split(" ");
              int temp = Integer.parseInt(arrs[2]);
              temp += Integer.parseInt(stockFields.get(i).getText());
              categoryStockList.get(categoryBoxes.get(i).getSelectedIndex()).get(itemBoxes.get(i).getSelectedIndex()).setText("Stock : "+String.valueOf(temp));
              arrs = categoryStockList.get(categoryBoxes.get(i).getSelectedIndex()).get(itemBoxes.get(i).getSelectedIndex()).getText().split(" ");
              temp = Integer.parseInt(arrs[2]);
              if(Integer.parseInt(arrs[2])<=2) {
               categoryStockList.get(i).get(itemBoxes.get(i).getSelectedIndex()).setForeground(Color.red);
               
              }
              else {
               categoryStockList.get(i).get(itemBoxes.get(i).getSelectedIndex()).setForeground(new Color(187, 190, 196));
                  
              }
            }catch(Exception e){
              
              updateStock.setVisible(false);
              
            }
          }
          
        }});
      
      submit.setMinimumSize(new Dimension(100,100));
      newBoxes.setSize(75*categoryBoxes.size(),500);
      newBoxes.setBorder(javax.swing.BorderFactory.
                           createTitledBorder(null, "Stock Change Sheet",+ javax.swing.border.
                                                TitledBorder.CENTER, javax.swing.border.
                                                TitledBorder.CENTER, new Font("Franklin Gothic Medium", Font.BOLD, 40), new Color(187, 190, 196)));
      newBoxes.setBackground(new Color(54, 57, 62));
      updateStock.setBackground(new Color(54, 57, 62));
      JBox mainUpdateBox = JBox.vbox(newBoxes,JBox.hbox(JBox.hglue(),submit,JBox.hglue()));
      
      mainUpdateBox.setBackground(new Color(54, 57, 62));
      updateStock.add(mainUpdateBox);
      updateStock.setSize(frame.getWidth(),frame.getHeight());
      
      updateStock.addWindowListener(new WindowAdapter(){
        public void windowClosing(WindowEvent e){
          try{
            setup(0);
          }catch(java.io.IOException epn){epn.printStackTrace();}
        }});
      updateStock.setVisible(true);
      updateStock.repaint();
    }  
    else{
      updateStock.setVisible(true);
      for(i = 0; i<stockFields.size();i++){
        stockFields.get(i).setText("Insert stock change");
        categoryBoxes.get(i).setSelectedIndex(0);
        
      }
    }
  }catch(Exception g){g.printStackTrace();}
  }
  
  
  static int count;
  public static void main(String[] args) throws java.io.IOException{
    try{
    Func.print(userHomePath);
    if(init == false){
      loadingFrame = new JFrame();
      loadingFrame.setUndecorated(true);
      loadingFrame.setLocationRelativeTo(null);
      loadingFrame.setSize(250,30);
      loadingFrame.setLayout(null);
      loadingBar.setBounds(0,0,250,30);
      loadingFrame.add(loadingBar);
      loadingFrame.setVisible(true);
      loadingBar.setStringPainted(true);
      loadingBar.setMaximum(100);
      loadingBar.setString("Loading Graphics");
      loadingBar.setIndeterminate(true);
      init = true;
    }
    titleText.setFont(new Font("Courier New", Font.BOLD, 40));
    ///////////////////////////////
    //Setting the settings/properties for the main frame
    frame.setResizable(true);
    frame.setMinimumSize(new Dimension(300,240));
    count = 1;
    //oh god it continues
    //Names added to R.A.M
    categoryNameList.add(steelItems);
    categoryNameList.add(electronicsItems);
    categoryNameList.add(autoItems);
    categoryNameList.add(weldingItems);
    //Images added to R.A.M
    categoryImageList.add(steelImages);
    categoryImageList.add(electronicsImages);
    categoryImageList.add(autoImages);
    categoryImageList.add(weldingImages);
    //Codes added to R.A.M
    categoryCodeList.add(steelCodes);
    categoryCodeList.add(electronicsCodes);
    categoryCodeList.add(autoCodes);
    categoryCodeList.add(weldingCodes);
    //Stocks added to R.A.M
    categoryStockList.add(steelStock);
    categoryStockList.add(electronicsStock);
    categoryStockList.add(autoStock);
    categoryStockList.add(weldingStock);
    //Descriptions added to R.A.M
    categoryDescList.add(steelDesc);
    categoryDescList.add(electronicsDesc);
    categoryDescList.add(autoDesc);
    categoryDescList.add(weldingDesc);
    //Clickable images added to R.A.M
    categoryButtonsList.add(steelButtons);
    categoryButtonsList.add(electronicButtons);
    categoryButtonsList.add(autoButtons);
    categoryButtonsList.add(weldingButtons);
    
    
    try{
    count = 1;
    for(int i =0; i<categoryNameList.size();i++){ //gets names
      
      for(;;){
        if(itemNames.load(count).equals("-")){
          count++;
          break;
        }
        else{
          categoryNameList.get(i).add(itemNames.load(count));
          count++;
        }
      }
    }
    /////////////////////////////////////////////////////////
    count = 1;
    for(int i =0; i<categoryImageList.size();i++){ //gets Images
      
      for(;;){
        if(itemimages.load(count).equals("-")){
          count++;
          break;
        }
        else{
          categoryImageList.get(i).add(itemimages.load(count));
          count++;
        }
      }
    }
    /////////////////////////////////////////////////////////
    count = 1;
    for(int i =0; i<categoryCodeList.size();i++){ //gets codes
      
      for(;;){
        if(itemCodes.load(count).equals("-")){
          count++;
          break;
        }
        else{
          categoryCodeList.get(i).add(itemCodes.load(count));
          count++;
        }
      }
    }
    /////////////////////////////////////////////////////////
    count = 1;
    for(int i =0; i<categoryStockList.size();i++){ //gets Stock
      
      for(;;){
        if(itemStock.load(count).equals("-")){
          count++;
          break;
        }
        else{
          categoryStockList.get(i).add(new JLabel(itemStock.load(count).toString()));
          count++;
        }
      }
    }
    //////////////////////////////////////////////////////////
    count = 1;
    for(int i =0; i<categoryStockList.size();i++){ //gets Descriptions
      
      for(;;){
        if(itemDescription.load(count).equals("-")){
          count++;
          break;
        }
        else{
          categoryDescList.get(i).add(itemDescription.load(count).toString());
          count++;
        }
      }
    }
    for(int g = 0; g<categoryCodeList.size();g++){
      for(int f = 0; f<categoryCodeList.get(g).size();f++){
        Item item = new Item(categoryCodeList.get(g).get(f));
        categoryButtonsList.get(g).add(item.getImage());
      }
    }
    }catch(Exception f){loadingBar.setString(f.getStackTrace().toString());}
    frame.setJMenuBar(createMenuBar());
    frame.setBackground(new Color(54, 57, 62));
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    try {
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
    } catch(Exception e){}
    setup(i);
    scrollBoxPanel.setLayout(new BorderLayout());
    scrollBoxPanel.add(scrollBox, BorderLayout.CENTER);
    scrollPane = new JScrollPane(scrollBoxPanel);
    scrollPane.getVerticalScrollBar().setUnitIncrement(16);
    scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
    update.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        try{
          setup(5);
        }catch(java.io.IOException e){}
      }
    });
    frame.setSize((int)screenSize.getWidth(),(int)screenSize.getHeight());
    mainbox = JBox.vbox(
                        JBox.hbox(
                                  JBox.vbox(
    )
                                 ),
                        JBox.hbox(
                                  JBox.vbox(
                                            scrollPane 
                                           )
                                 )
                       );
    frame.add(mainbox);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    frame.setUndecorated(true);
    
    
    frame.addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent e){
        try{
          
          
          
          
          
          
          username = user;
          password = pass;
          if(user.contains("gmail")||user.contains("@ocdsb"))
             props.put("mail.smtp.host", "smtp.gmail.com");
          else if(user.contains("@hotmail")||user.contains("@outlook")||user.contains("@live"))
             props.put("mail.smtp.host", "smtp-mail.outlook.com");
          
          props.put("mail.smtp.auth", "true");
          props.put("mail.smtp.starttls.enable", "true");
          props.put("mail.smtp.port", "587");
          
          
          
          
          try{
            for(int k = 0; k<categoryStockList.size();k++){
              for(int h = 0;h<categoryStockList.get(k).size();h++){
                Item item = new Item(categoryCodeList.get(k).get(h).toString());
                if(item.getStockInt() <2){
                  send("jwell5@ocdsb.ca",(item.getName().getText().toUpperCase()+" NEEDS RESTOCKING"), "The stock of the "+item.getName().getText()+" is at below recommended levels. \n\nLocate your nearest Home Depot™ to purchase replacements/resupply");
                }
              }
            }
          }catch(Exception g){}
          
          frame.setVisible(false);
          itemNames.overwrite();
          itemStock.overwrite();
          itemCodes.overwrite();
          itemimages.overwrite();
          itemDescription.overwrite();
          for(int i = 0;i<categoryNameList.size();i++){ //saves item names to text file
            for(int j=0;j<categoryNameList.get(i).size();j++){
              
              Item item = new Item(categoryCodeList.get(i).get(j).toString());
              itemNames.add(item.getNameS());
            }
            itemNames.add("-");
          }
          for(int i = 0;i<categoryCodeList.size();i++){
            for(int j=0;j<categoryCodeList.get(i).size();j++){
              Item item = new Item(categoryCodeList.get(i).get(j).toString());
              itemCodes.add(item.getCode());
            }
            itemCodes.add("-");
          }
          for(int i = 0;i<categoryImageList.size();i++){
            for(int j=0;j<categoryImageList.get(i).size();j++){
              itemimages.add(categoryImageList.get(i).get(j).toString());
            }
            itemimages.add("-");
          }
          for(int i = 0;i<categoryStockList.size();i++){
            for(int j=0;j<categoryStockList.get(i).size();j++){
              Item item = new Item(categoryCodeList.get(i).get(j).toString());
              itemStock.add(item.getStockInt());
            }
            itemStock.add("-");
          }
      for(int i = 0;i<categoryDescList.size();i++){ //saves item Descriptions to text file
            for(int j=0;j<categoryDescList.get(i).size();j++){
              
              Item item = new Item(categoryCodeList.get(i).get(j).toString());
              itemDescription.add(item.getDesc());
            }
            itemDescription.add("-");
          }
      
    
        }catch(java.io.IOException f){}
      }
    });
    
    
    loadingFrame.setVisible(false);
    frame.setVisible(true);
  }catch(Exception g){g.printStackTrace();}
  }
  
}