import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;

import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.LineBorder;


public class GUI extends JFrame implements WindowListener {
    JPanel panelMain;
    JButton buttonNext, buttonRun;
    String res= read("src\\Default.txt");

    public static final LineBorder line = new LineBorder(Color.BLACK, 3);
    public static final Color dark = new Color(212,53,132);
    public static final Font BTN_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 35);
    public static final Color background = new Color(126, 209, 212);

    public GUI() {



        //setPreferredSize(new Dimension( 600, 630));
        panelMain = new JPanel();
        panelMain.setLayout(new GridLayout(1,2,15,15));
        JPanel panel1 =new JPanel(new GridBagLayout());
        JPanel panel2 =new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JTextArea area = new JTextArea(res);
        area.setFont(new Font("Sans_Serif",Font.BOLD,20));
        area.setEditable(false);
        buttonNext = new JButton("Next");
        buttonRun = new JButton("Run");

        panel2.setBackground(background);
        area.setBackground(background);
        panelMain.setBackground(background);
        panel1.setBackground(background);
        c.anchor = GridBagConstraints.CENTER;
        panel1.add(buttonNext,c);
        panel2.add(area,c);
        panelMain.add(panel1);
        panelMain.add(panel2);

        // panelMain.add(buttonRun);

        buttonNext.setBorder(line);
        buttonNext.setFont(BTN_FONT);
        buttonNext.setForeground(Color.WHITE);
        buttonNext.setBackground(dark);


        buttonNext.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Game.runGame();
                String res= read("Result.txt");
                area.setText(res);
            }

        });



        buttonRun.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Game.setRunning();
                Game.runGame();
            }

        });
        //

        addWindowListener(this);
        setContentPane(panelMain);
        setVisible(true);
        setBackground(Color.PINK);
        pack();
        setSize(450, 375);
        setTitle("Game of Life");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowClosed(WindowEvent e) {

        System.out.println("ddd");

    }

    @Override
    public void windowClosing(WindowEvent e) {

        Game.checkLoops();

    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowIconified(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    public String read(String fileName){
        File file = new File(fileName);
        String st;
        String result="";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            while ((st = br.readLine()) != null) {
                st=st.replace("-","_") ;
                st =st.replace("O","0") ;

                st+="\n";
                result+=st;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }
}


