import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.plaf.FileChooserUI;

public class MyEditer  implements ActionListener
{
		
	JFrame jf, fontFrame;
	JMenuBar menuBar;
	JMenu menu1,menu2,menu3;
	JMenuItem neww,open,save,saveAs,exit,cut ,copy,paste, selectAll, delete , font, color,background;
	JTextArea textArea=new JTextArea();
	JComboBox size, style, fonts;
	JButton okButton;
	
	
	
	MyEditer(){
		try {
		     UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//This line gives Windows Theme
		 
		    } 
		catch (Exception e) 
		{
		      e.printStackTrace();
		    }

		
		 jf = new JFrame("/* MY Notepad */");
		 jf.setBounds(200, 20, 500, 500);
		 jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 jf.setLocationRelativeTo(null);
		// jf.setLayout(null);
		 
		 //creating menuBar
		 JMenuBar menuBar =new JMenuBar();
		 
		 //creating menu
		  menu1 = new JMenu("FILE");
		  menu2= new JMenu("EDIT");
		  menu3=new JMenu("FORMAT");
		  
		  
		  // creating menu items
		  neww=new JMenuItem("New");
		  neww.addActionListener(this);
		  open=new JMenuItem("Open");
		  open.addActionListener(this);
		  save =new JMenuItem("Save file");
		  save.addActionListener(this);
		  saveAs=new JMenuItem("Save as");
		  saveAs.addActionListener(this);
		  exit=new JMenuItem("Exit");
		  exit.addActionListener(this);
		  
		  cut=new JMenuItem("Cut");
		  cut.addActionListener(this);
		  copy=new JMenuItem("Copy");
		  copy.addActionListener(this);
		  paste=new JMenuItem("Paste");
		  paste.addActionListener(this);
		  selectAll=new JMenuItem("Select All");
		  selectAll.addActionListener(this);
		  delete=new JMenuItem("Delete");
		  delete.addActionListener(this);
		  
		  font=new JMenuItem("Font...");
		  font.addActionListener(this);
		  color=new JMenuItem("Color");
		  color.addActionListener(this);
		  background=new JMenuItem("BackgroundColor");
		  background.addActionListener(this);
		  
		  
		  
		 //adding menuItems
		 menu1.add(neww);
		 menu1.add(open);
		 menu1.add(save);
	     menu1.add(saveAs);
		 menu1.add(exit);
		 
		 menu2.add(cut);
		 menu2.add(copy);
		 menu2.add(paste);
		 menu2.add(selectAll);
		 menu2.add(delete);
		 
		 menu3.add(font);
		 menu3.add(color);
		 menu3.add(background);
		 
		 
		 // adding menu in menuBar
		 menuBar.add(menu1);
		 menuBar.add(menu2);
		 menuBar.add(menu3);
		 
		 // set textArea
		 textArea=new JTextArea();
		 
		 //scrollBar in textArea
		 JScrollPane scroll=new JScrollPane(textArea);
		 scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		 scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 
		 //
		
		
		 //JScrollBar scroll =new JScrollBar();
		 
		 
		  
		 //adding Elements in JFrame
		 jf.setJMenuBar(menuBar);
		 jf.add(scroll);
	  
		 
		jf.setVisible(true);
	}


	public static void main(String[] args) {
		
		new MyEditer();		

	}
	JFileChooser fileChooser =new JFileChooser();
	
	 public void actionPerformed(ActionEvent event) {
		 
		 
		 //String cmnd = event.getActionCommand();
		 
		 //openFile action setting
		 if(event.getSource()==open) {
			 textArea.setText(null);
			fileChooser.showOpenDialog(jf);
			File file=fileChooser.getSelectedFile();
			
			
	         // setting selected file name in titlebar
	        String name = file.getName();
	        jf.setTitle("SELECTED File: "+name);
			 
			 try (FileInputStream fis=new FileInputStream(file))
			    {    
				    int i;
				   
				    while(( i= fis.read())!= -1) {
				    	
				    	textArea.append((String.valueOf((char)i)));
				  }
				  }
				  catch(Exception e) {
					  e.printStackTrace();
				  }
			 
		       }

		  if (event.getSource()==neww) { //action on new button
			 textArea.setText(null);
			 jf.setTitle("/* Untitled notepad */");
		 }
		 
		 if(event.getSource()== exit) { // actions on exit button	
			  
			 System.exit(0);
			
		 }
		 
		 
		 if(event.getSource()==save) { // Actions on save button
			if(jf.getTitle().equals("/* MY Notepad */")) {
			   saveAsText();
			   
			  
			}
			else
				saveText();
			
			
		 }
		 
		 if(event.getSource()==saveAs) { // Actions on saveAs button
				
				saveAsText();
			 }
		 
		 
		 
		//actions on menu2 Items 
	     if(event.getSource()==cut)
	    	 textArea.cut();
	     if(event.getSource()==copy)
	    	 textArea.copy();
	     if(event.getSource()==paste)
	    	 textArea.paste();
	     if(event.getSource()==selectAll)
	    	 textArea.selectAll();
	     if(event.getSource()==delete)
	    	 textArea.setText(null);
	     
	     
	     //actions on menu3
	     if(event.getSource()==font) {
	    	 setFont();
	     }
	
	     if(event.getSource()==okButton) {
	    	 okFun();
	    	 fontFrame.setVisible(false);
	     }
	     
	     if (event.getSource()==color) {
	    	 
	    	Color c=JColorChooser.showDialog(jf, "Choose Font Color", Color.black);
	    	 textArea.setForeground(c);
	     }
	     
	     if (event.getSource()==background) {
	    	 Color c=JColorChooser.showDialog(jf, "Choose Font Color", Color.white);
	    	 textArea.setBackground(c);  
	     }
	     
		 
   }
	 
	 
	//save as function
	 public  void saveAsText()
	    {
		 fileChooser.showSaveDialog(jf);
		 String msg= textArea.getText();
		 byte[] buffer = msg.getBytes();
		 
		 File file=fileChooser.getSelectedFile();
		 
		 try {
			 FileOutputStream fos= new FileOutputStream(file);
			 fos.write(buffer);
			 fos.close();
			 String name = file.getName();
		     jf.setTitle("SELECTED File: "+name);
			 JOptionPane.showMessageDialog(jf, "Your file is successflly saved.");
			 
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
	    }
	 
    //save function 
   public  void saveText()
    {
	 
	 String msg= textArea.getText();
	 byte[] buffer = msg.getBytes();
	 
	 File file=fileChooser.getSelectedFile();
	 
	 try {
		 FileOutputStream fos= new FileOutputStream(file);
		 fos.write(buffer);
		 fos.close();
		 String name = file.getName();
	     jf.setTitle("SELECTED File: "+name);
		
		 
	 }
	 catch(Exception e) {
		 e.printStackTrace();
	 }
    }
   
   
   // set Fonts function
    public void setFont() {
    	 fontFrame= new JFrame("/* FONTCHOOSER */");
		 fontFrame.setSize(400, 400);
		 fontFrame.setLocationRelativeTo(jf);
		 fontFrame.setLayout(null);
		 
		 
		 GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		 Font[] fontFamilies= e.getAllFonts();
		 
		 
		 String [] arr= {"12", "14","16","18","22","26","30","35","40","50"};
		 String[] fontStyle= {"Regular","Italic","Bold","Bold Italic"};
		 
		 String[] fontName =new String[fontFamilies.length];
		 
		
		 int i=0;
		 for(i=0;i<fontFamilies.length;i++) {
		
		 fontName[i]=fontFamilies[i].getFontName();
		   }
		 
		 
		 size= new JComboBox(arr);
		size.setBounds(30,50,50, 30);
		 fontFrame.add(size);
		
		style=new JComboBox(fontStyle);
		style.setBounds(120,50,80, 30);
		 fontFrame.add(style);
		
	     
	     fonts=new JComboBox(fontName);
		 fonts.setBounds(220,50,100, 30);
	     fontFrame.add(fonts);
	     
	     okButton=new JButton("OK");
	     okButton.addActionListener(this);
	     okButton.setBounds(170, 270, 60, 50);
	     fontFrame.add(okButton);
	     

		 fontFrame.setVisible(true);
		 
		
    	
    }
    
    public void okFun() {
    	
    	String setSize=(String)size.getSelectedItem();
    	String setStyle=(String)style.getSelectedItem();
    	String setFontFamily=(String)fonts.getSelectedItem();
    	
    	int style;
    	if(setStyle=="Regular")
    		style=0;
    	else if(setStyle=="Italic")
    		style=1;
    	else if(setStyle=="Bold")
    		style=2;
    	else if (setStyle=="Bold Italic")
    		style=3;
    	else
    		style=4;
    	
    	
    	Font font=new Font(setFontFamily, style , Integer.parseInt(setSize));
    	
    	textArea.setFont(font);
    			
    	
    }
   
   

}
