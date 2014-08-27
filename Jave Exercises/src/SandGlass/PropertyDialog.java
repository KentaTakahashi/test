package SandGlass;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class PropertyDialog extends JFrame
{

    private static final long serialVersionUID = 1L;

    private Container container ;
    private JSlider slider1 ;
    private JSlider slider2 ;
    private JLabel lbl1 ;
    private JLabel lbl2 ;
    private JPanel panel1 ;
    private JPanel panel2 ;
    private JTextField txt1 ;
    private JTextField txt2 ;

    public PropertyDialog() {
        super("Demo Slider Sample");
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(14555));
        setSize(new Dimension(400,400));
        setResizable(true);
        /****************************************************************/
        container = getContentPane();
        BorderLayout containerLayout = new BorderLayout();
        container.setLayout(containerLayout);

        /****************** Labels Properties ********************************/
        lbl1 = new JLabel("Slider 1");
        lbl2 = new JLabel("Slider 2");

        /****************** TextField Properties ********************************/
        txt1 = new JTextField(4);
        txt2 = new JTextField(4);

        /****************** Sliders Properties ***********************************/
        slider1 = new JSlider(JSlider.HORIZONTAL,0,1000,0);//direction , min , max , current
        slider1.setFont(new Font("Tahoma",Font.BOLD,12));
        slider1.setMajorTickSpacing(100);
        slider1.setMinorTickSpacing(25);
        slider1.setPaintLabels(true);
        slider1.setPaintTicks(true);
        slider1.setPaintTrack(true);
        slider1.setAutoscrolls(true);
        slider1.setPreferredSize(new Dimension(500,500));
        
        slider2 = new JSlider(JSlider.VERTICAL,0,1000,500);//direction , min , max , current
        slider2.setFont(new Font("Tahoma",Font.BOLD,12));
        slider2.setMajorTickSpacing(100);
        slider2.setMinorTickSpacing(25);
        slider2.setPaintLabels(true);
        slider2.setPaintTicks(true);
        slider2.setPaintTrack(true);
        slider2.setAutoscrolls(true);
        /*************************** Controls Events ************************************/
        //When Changing Slider 1 Cursor...do this
        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                txt1.setText(String.valueOf(slider1.getValue()));
            }
        });

        //When Changing Slider 2 Cursor...do this
        slider2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                txt2.setText(String.valueOf(slider2.getValue()));
            }
        });

        //When Press Enter After Change...do this
        txt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    slider1.setValue(Integer.parseInt(txt1.getText()));
                }
                catch(Exception ex)
                {
                    txt1.setText("ERROR");
                    txt1.setToolTipText("Set Value in Range between 0 - 1000 ") ;
                }
            }
        });

        txt2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    slider2.setValue(Integer.parseInt(txt2.getText()));
                }
                catch(Exception ex)
                {
                    txt2.setText("ERROR");
                    txt2.setToolTipText("Set Value in Range between 0 - 1000 ") ;
                }
            }
        });

        this.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e){
            }

            @Override
            public void focusGained(FocusEvent e) {
                txt1.setText(String.valueOf(slider1.getValue()));
                txt2.setText(String.valueOf(slider2.getValue()));
            }
        });
        /****************************************************************/

        panel1 = new JPanel();
        container.add(panel1, BorderLayout.WEST);
        panel1.add(lbl1);
        panel1.add(txt1);
        panel1.add(slider1);

        panel2 = new JPanel();
        container.add(panel2, BorderLayout.NORTH);
        panel2.add(lbl2);
        panel2.add(txt2);
        panel2.add(slider2);


        setVisible(true);
    }

    public static void main(String args[])
    {
        new PropertyDialog();
    }
}
