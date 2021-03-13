package wacc.extension.wacc_ide;

import wacc.extension.wacc_ide.WaccIDE.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.*;

public class View extends JFrame implements ActionListener {

  private final ArrayList<JTextPane> tabs = new ArrayList<>();
  private JTextPane currentPane;
  private final Model model;
  private static int returnValue = 0;
  private static JFileChooser jfc
      = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
  private JTabbedPane jtp;
  private int ntabs = 0;

  public View(Controller controller) {
    model = new Model(this);
    display();
  }

  private void display() {

    JFrame frame = new JFrame("IDE");

    //creating tabbedPanes for coding areas.
    jtp = new JTabbedPane();
    jtp.setSize(jtp.getPreferredSize());
    createTab();
    currentPane = tabs.get(0);

    // Frame settings.
    frame.add(jtp);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 800);

    // Build the menu
    JMenuBar mainMenu = new JMenuBar();

    JMenu menu = new JMenu("File");

    JButton newTab = new JButton("new tab");

    // Add menu items.
    JMenuItem menuOpen = new JMenuItem("Open");
    JMenuItem menuNew = new JMenuItem("New");
    JMenuItem menuSave = new JMenuItem("Save");

    // Add action listener for each menu options.
    menuOpen.addActionListener(this);
    menuNew.addActionListener(this);
    menuSave.addActionListener(this);

    mainMenu.add(menu);
    mainMenu.add(newTab);

    // Add actual menu items.
    menu.add(menuNew);
    menu.add(menuOpen);
    menu.add(menuSave);

    // Add menu bar to frame.
    frame.setJMenuBar(mainMenu);

    // Add scroll bars and add to frame.
    JScrollPane inputScroll =
        new JScrollPane(
            jtp,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    frame.add(inputScroll);

    frame.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent windowEvent) {

        // Determine if the user wants to save file.
        boolean save = saveFirst();

        // Prompt user to save.
        if (save) {
          saveFile(jfc);
        }
      }
    });

    //creating a new tab and adding an action listener onto its pane
    newTab.addActionListener(e -> createTab());

    newTab.setToolTipText("create a new tab");

    //changing currentPane to the pane in use when tabs are switched
    jtp.addChangeListener(e -> {
      try {
        currentPane = tabs.get(jtp.getSelectedIndex());
      } catch (IndexOutOfBoundsException a) {
        System.exit(0);
      }
    });

    // Finally, set frame as visible.
    frame.setVisible(true);


  }

  private void createTab() {

    JPanel newPage = new JPanel();
    JTextPane pane = new JTextPane();

    pane.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {

      }

      @Override
      public void keyPressed(KeyEvent e) {

      }

      // Check when any key is released
      @Override
      public void keyReleased(KeyEvent e) {
        int offset = currentPane.getCaretPosition();

        try {
          model.check(e);
        } catch (IOException | BadLocationException ioException) {
          ioException.printStackTrace();
        }

        // Reset caret/cursor
        currentPane.setCaretPosition(offset);
      }
    });

    tabs.add(pane);

    pane.setBounds(0, 0, 800, 800);
    newPage.setLayout(null);
    newPage.setPreferredSize(new Dimension(800, 800));

    newPage.add(pane);

    ntabs++;

    String name = "New Tab         ";

    jtp.addTab(name, newPage);

    JPanel pnlTab = getjPanel(name, pane);

    jtp.setTabComponentAt(ntabs - 1, pnlTab);
  }

  private JPanel getjPanel(String name, JTextPane pane) {
    JPanel pnlTab = new JPanel(new GridBagLayout());
    pnlTab.setOpaque(false);
    JLabel lblTitle = new JLabel(name);
    JButton btnClose = new JButton("x");
    btnClose.setMargin(new Insets(1, 1, 1, 1));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 0;

    pnlTab.add(lblTitle, gbc);

    // Handle actual closing of tab
    btnClose.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int index = tabs.indexOf(pane);
        if (index >= 0) {
          jtp.removeTabAt(index);
          tabs.remove(pane);
          ntabs--;
        }
          if (ntabs == 0) {
              System.exit(0);
          }
      }
    });

    gbc.gridx++;
    gbc.weightx = 0;
    pnlTab.add(btnClose, gbc);
    return pnlTab;
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    StringBuilder ingest = new StringBuilder();

    jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

    boolean save;

    String ae = e.getActionCommand();
    switch (ae) {
      // Open
      case "Open":
        // Determine if the user wants to save file.
        save = saveFirst();

        // Prompt user to save.
        if (save) {
          saveFile(jfc);
        }

        jfc.setDialogTitle("Choose Source File.");

        returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File f = new File(jfc.getSelectedFile().getAbsolutePath());
          try {
            FileReader read = new FileReader(f);
            Scanner scan = new Scanner(read);
            while (scan.hasNextLine()) {
              String line = scan.nextLine() + "\n";
              ingest.append(line);
            }
            currentPane.setText(ingest.toString());
          } catch (FileNotFoundException ex) {
            ex.printStackTrace();
          }
        }
        break;

      // Save
      case "Save":
        jfc.setDialogTitle("Choose destination.");
        saveFile(jfc);
        break;

      // New
      case "New":

        // Determine if the user wants to save file.
        save = saveFirst();

        // Prompt user to save.
        if (save) {
          saveFile(jfc);
        }

        break;
    }
  }

  private boolean saveFirst() {

    JFrame parent = new JFrame();

    int n = JOptionPane.showConfirmDialog(
        parent,
        "Do you want to save the current file?",
        "",
        JOptionPane.YES_NO_OPTION);

    return n == 0;

  }

  private void saveFile(JFileChooser jfc) {
    returnValue = jfc.showSaveDialog(null);
    try {
      File f = new File(jfc.getSelectedFile().getAbsolutePath());

      JPanel jPanel = getjPanel(f.getName() + "        ", currentPane);
      int index = tabs.indexOf(currentPane);
      jtp.setTabComponentAt(index, jPanel);

      FileWriter out = new FileWriter(f);
      out.write(currentPane.getText());
      out.close();
    } catch (FileNotFoundException ex) {
      Component f = null;
      JOptionPane.showMessageDialog(f, "File not found.");
    } catch (IOException ex) {
      Component f = null;
      JOptionPane.showMessageDialog(f, "Error.");
    }
  }

  public JTextPane getPane() {
    return currentPane;
  }
}



