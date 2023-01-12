
//class for common element in the frames 

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.event.ActionEvent;

public class MainClass extends JFrame {
	
  static Font titlefont=new Font("Algerian", 1, 20);
  JLabel title; 
  JPanel title_pn;
  JMenuBar m = new JMenuBar(); 
  JMenu file = new JMenu("File");
  JMenu Edit = new JMenu("Edit");
  JMenu View = new JMenu("View"); 
  JMenu help = new JMenu("Help"); 

  JMenuItem save = new JMenuItem("Save");

  JMenuItem close = new JMenuItem("Quit"); 
  JMenuItem copy;
  JMenuItem cut; 
  JMenuItem paste; 
  JMenuItem about = new JMenuItem("About us");
  JMenuItem Option = new JMenuItem("change theme");

  public MainClass(String Title, int L, int H, Color color) {

    title = new JLabel(Title);
    title.setFont(titlefont); 
    title.setForeground(Color.DARK_GRAY);
 
    title_pn = new JPanel();
    title_pn.add(title);
    getContentPane().add(title_pn, BorderLayout.NORTH);

    setTitle(Title); 
    setSize(L, H); 
   
    setLocation(245, 180);

    //initialisation of copy paste and cut actions
    
    Action alt = new DefaultEditorKit.CopyAction();
    Action actionCopy = new DefaultEditorKit.CopyAction();
    Action actionPast = new DefaultEditorKit.PasteAction();
    Action actionCut = new DefaultEditorKit.CutAction();
    KeyStroke k = KeyStroke.getKeyStroke(KeyEvent.CTRL_MASK, KeyEvent.VK_C);
    KeyStroke k1 = KeyStroke.getKeyStroke(KeyEvent.CTRL_MASK, KeyEvent.VK_V);
    KeyStroke k2 = KeyStroke.getKeyStroke(KeyEvent.CTRL_MASK, KeyEvent.VK_X);
    KeyStroke k3 = KeyStroke.getKeyStroke(KeyEvent.ALT_MASK, KeyEvent.VK_F);
    JTextPane textPane = new JTextPane();

    copy = new JMenuItem(actionCopy);
    copy.setText("copy");
    cut = new JMenuItem(actionCut);
    cut.setText("cut");
    paste = new JMenuItem(actionPast);
    paste.setText("past");

    save.setEnabled(false);
    close.setEnabled(false);
    Option.setEnabled(false);
    about.setEnabled(false);


    file.add(save);

    file.addSeparator();
    file.add(close);

    //adding element of edit menu
    Edit.add(copy);
    Edit.add(cut);
    Edit.add(paste);

    //adding element of view menu
    View.add(Option);

    //adding elements of help menu
    help.add(about);

    //adding menu to the bar
    m.add(file);
    m.add(Edit);
    m.add(View);
    m.add(help);
    
    //adding the bar menu
    setJMenuBar(m);

    //closing the main frame
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent evt) {
;
        MainMenu.F.enable();
     
      }
    });

    setResizable(false);
    show();
  }

}