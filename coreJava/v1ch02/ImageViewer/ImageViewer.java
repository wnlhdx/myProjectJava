import java.awt.*;
import java.io.*;
import javax.swing.*;

/**
 * @author : root
 * @created : 2022-01-28
**/
public class ImageViewer{
  public static void main (String[] args){
    EventQueue.invokeLater(()->{
      JFrame jframe = new ImageViewerFrame();
      jframe.setTitle("ImageViewer");
      jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      jframe.setVisible(true);
    });
  }
}

class ImageViewerFrame extends JFrame{
  private JLabel label;
  private JFileChooser chooser;
  private static final int DEFAULT_WIDTH=300;
  private static final int DEFAULT_HEIGHT=400;

  public ImageViewerFrame(){
    setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    label = new JLabel();
    add(label);
    chooser=new JFileChooser();
    chooser.setCurrentDirectory(new File(".JMe"));
    JMenuBar menuBar= new JMenuBar();
    setJMenuBar(menuBar);
    JMenu menu = new JMenu("File");
    menuBar.add(menu);
    JMenuItem openItem = new JMenuItem("Open");
    menu.add(openItem);
    openItem.addActionListener(event->{
      int result = chooser.showOpenDialog(null);
      if(result==JFileChooser.APPROVE_OPTION){
        String name=chooser.getSelectedFile().getPath();
        label.setIcon(new ImageIcon(name));
      }
    });
    JMenuItem exitItem=new JMenuItem("Exit");
    menu.add(exitItem);
    exitItem.addActionListener(event->System.exit(0));

  }
}


