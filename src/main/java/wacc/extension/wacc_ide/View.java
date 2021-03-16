package wacc.extension.wacc_ide;

import org.antlr.v4.runtime.misc.Pair;
import org.apache.commons.io.IOUtils;
import wacc.ErrorCode;
import wacc.WaccCompiler;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.*;

public class View extends JFrame implements ActionListener {

  private final List<JTextPane> tabs = new ArrayList<>();
  JFrame frame;
  private JTextPane currentPane;
  private final Model model;
  private static int returnValue = 0;
  private static final JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
  private JTabbedPane jtp;
  private int ntabs = 0;
  private final Map<JTextPane, Pair<Boolean, String>> paneState = new HashMap<>();
  private String currRelativePath;

  public View() {
    model = new Model(this);
    display();
  }

  private void display() {

     frame = new JFrame("WACC IDE");

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
    JMenuItem menuReset = new JMenuItem("Reset");
    JMenuItem menuSave = new JMenuItem("Save");
    JMenuItem menuCompile = new JMenuItem("Compile");

    // Add action listener for each menu options.
    menuOpen.addActionListener(this);
    menuReset.addActionListener(this);
    menuSave.addActionListener(this);
    menuCompile.addActionListener(this);

    mainMenu.add(menu);
    mainMenu.add(newTab);

    // Add actual menu items.
    menu.add(menuReset);
    menu.add(menuOpen);
    menu.add(menuSave);
    menu.add(menuCompile);

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

        while (tabs.size() != 0) {
          closeTab(tabs.get(0));
        }

      }
    });

    //creating a new tab and adding an action listener onto its pane
    newTab.addActionListener(e -> createTab());

    //changing currentPane to the pane in use when tabs are switched
    jtp.addChangeListener(e -> {
      try {
        int x = jtp.getSelectedIndex();
        currentPane = tabs.get(x);
        model.check();
        currRelativePath = paneState.get(currentPane).b;
      } catch (IndexOutOfBoundsException | IOException | BadLocationException a) {
        a.printStackTrace();
      }
    });

    // Finally, set frame as visible.
    frame.setVisible(true);


  }

  private void createTab() {



    JPanel newPage = new JPanel();
    JTextPane pane = new JTextPane(){

      @Override
      public String getToolTipText(MouseEvent event) {
        return model.getErrorMsg(event.getY());
      }
    };

    // Initially, pane is not modified.
    paneState.put(pane, new Pair<>(false, null));

    ToolTipManager.sharedInstance().registerComponent(pane);

    // Add Mouse Position Listener
    pane.addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {
      }

      @Override
      public void mousePressed(MouseEvent e) {

      }

      @Override
      public void mouseReleased(MouseEvent e) {
        int pos = currentPane.getCaretPosition();

      }

      @Override
      public void mouseEntered(MouseEvent e) {

      }

      @Override
      public void mouseExited(MouseEvent e) {

      }
    });

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
          model.check();
        } catch (IOException | BadLocationException ioException) {
          ioException.printStackTrace();
        }

        // Reset caret/cursor
        currentPane.setCaretPosition(offset);

        // Set current pane modified to true
        Pair<Boolean, String> p = paneState.get(currentPane);
        paneState.put(currentPane, new Pair<>(true, p.b));

        //keyboard shortcuts
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S){
            saveFile(jfc);
        }

        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Q){
              compile_code();
          }

        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_W){
          closeTab(currentPane);
        }
      }
    });

    tabs.add(pane);

    pane.setBounds(0,0,800,800);
    newPage.setLayout(null);
    newPage.setPreferredSize(new Dimension(800,800));

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
    btnClose.setMargin(new Insets(1, 1, 1,1));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 0;

    pnlTab.add(lblTitle, gbc);

    // Handle actual closing of tab
    btnClose.addActionListener(e -> {

      closeTab(pane);
    });

    gbc.gridx++;
    gbc.weightx = 0;
    pnlTab.add(btnClose, gbc);
    return pnlTab;
  }

  private void closeTab(JTextPane pane) {

    int index = tabs.indexOf(pane);

    jtp.setSelectedIndex(index);

    boolean save = saveFirst();

    // Prompt user to save.
    if (save) {
      saveFile(jfc);
    }

    if (index >= 0 && jtp.getSelectedIndex() >= 0) {
      jtp.removeTabAt(index);
      tabs.remove(pane);
      paneState.remove(pane);
      ntabs--;
      if (ntabs != 0) {
        currentPane = tabs.get(jtp.getSelectedIndex());
        currRelativePath = paneState.get(currentPane).b;
      }
    }
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
          try {
            File f = new File(jfc.getSelectedFile().getAbsolutePath());
            FileReader read = new FileReader(f);
            Scanner scan = new Scanner(read);
            while (scan.hasNextLine()) {
              String line = scan.nextLine() + "\n";
              ingest.append(line);
            }
            currentPane.setText(ingest.toString());

            // Update path of current pane.
            currRelativePath = f.getAbsolutePath();

            // Rename tab
            JPanel jPanel = getjPanel(f.getName() + "        ", currentPane);
            int index = jtp.getSelectedIndex();
            jtp.setTabComponentAt(index, jPanel);

            // Update pane entry
            Pair<Boolean, String> p = new Pair<>(false, currRelativePath);
            paneState.put(currentPane, p);
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

      // Reset
      case "Reset":

        // Determine if the user wants to save file.
        save = saveFirst();

        // Prompt user to save.
        if (save) {
          saveFile(jfc);
        }

        currentPane.setText("");

        break;

      //compile
      case "Compile":
        compile_code();


    }
  }

    private void compile_code() {
    WaccCompiler compiler = null;
    try {
      compiler = new WaccCompiler(currentPane.getText());
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
    assert compiler != null;
    ErrorCode errorCode = compiler.compile();

    if (errorCode != ErrorCode.SUCCESS) {
      JOptionPane.showMessageDialog(frame,"code contains errors, cant be compiled");
      return ;
    }

    String sourceCode = compiler.getSourceCode();

    File file = new File("temp.s");

    FileWriter writer = null;
    try {
      writer = new FileWriter(file);

      if(sourceCode != null){
        writer.write(sourceCode);
        writer.close();
      }

    } catch (IOException ioException) {
      ioException.printStackTrace();
    }


    try {
      Runtime runtime = Runtime.getRuntime();
      Process compileSourceProcess = runtime
              .exec("arm-linux-gnueabi-gcc -o EXEName -mcpu=arm1176jzf-s "
                      + "-mtune=arm1176jzf-s temp.s");

      compileSourceProcess.waitFor();

      Process execWacc = runtime
              .exec("qemu-arm -L /usr/arm-linux-gnueabi/ EXEName");
      InputStream inputStream = execWacc.getInputStream();

      execWacc.waitFor();

      File exec = new File("EXEName");
      exec.deleteOnExit();
      file.deleteOnExit();

      String text = IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
      JOptionPane.showMessageDialog(frame,"output: " + text);

    } catch (InterruptedException | IOException e2) {
      e2.printStackTrace();
    }
  }

  private boolean saveFirst() {

    if(!paneState.get(currentPane).a) {
      return false;
    }

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

      currRelativePath = f.getAbsolutePath();

      JPanel jPanel = getjPanel(f.getName() + "        ", currentPane);
      int index = jtp.getSelectedIndex();
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

    // Saved, so set modified field to be false again.
    Pair<Boolean, String> p = new Pair<>(false, currRelativePath);
    paneState.put(currentPane, p);
  }

  public JTextPane getPane() {
    return currentPane;
  }

  public String getCurrRelativePath() {
    return currRelativePath;
  }
}