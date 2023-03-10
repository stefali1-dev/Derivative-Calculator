import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class AppLayout extends JFrame implements ActionListener {

    static JFrame frame;
    static JPanel pane;
    static JLabel Formula;
    static JTextField Input;
    static AppLayout app;
    static String math = "";

    public AppLayout() throws HeadlessException {
        super();
        setTitle("Derivative Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 580, 530);

    }

    public void initialise() {
        app = new AppLayout();
        frame = new JFrame("AppLayout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        pane = (JPanel) frame.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));


        Formula = new JLabel();
        Formula.setAlignmentX(Component.CENTER_ALIGNMENT);
        paintIcon();

        pane.add(Formula);

        pane.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton button = new JButton("Calculate");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(app);
        pane.add(button);

        pane.add(Box.createRigidArea(new Dimension(0, 5)));

        Input = new JTextField(40);
        Input.setAlignmentX(Component.CENTER_ALIGNMENT);
        Input.setHorizontalAlignment(JTextField.CENTER);

        pane.add(Input);

        pane.add(Box.createRigidArea(new Dimension(0, 10)));

        //Display the window.
        app.add(pane);
        app.setVisible(true);
        app.pack();
    }

    public static void paintIcon() {
        TeXFormula formula = new TeXFormula(math);
        TeXIcon ti = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 40);
        BufferedImage b = new BufferedImage(ti.getIconWidth(), ti.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        ti.paintIcon(new JLabel(), b.getGraphics(), 0, 0);
        Formula.setIcon(ti);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Calculator E = new Calculator();
        math = Input.getText();

        math = math.replaceAll("\\s+", "");
        math = E.derivative(math);
        math = E.finalStringFormat(math);

        System.out.println(math);

        paintIcon();

        app.pack();

    }
}