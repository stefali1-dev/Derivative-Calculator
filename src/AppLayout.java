import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Scanner;

import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

public class AppLayout extends JFrame implements ActionListener{
	
	static JFrame frame;
	static JPanel pane;
	static JLabel Formula;
	static JTextField Input;
	static AppLayout app;
	static String math;
	
	public AppLayout() throws HeadlessException {
		super();
		setTitle("Derivative Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 530);
		
	}

    public static void main(String[] args) {
    	app = new AppLayout();
    	frame = new JFrame("AppLayout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        pane = (JPanel) frame.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        //
        math = "\\cdot";
		TeXFormula formula = new TeXFormula(math);
		TeXIcon ti = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 40);
		BufferedImage b = new BufferedImage(ti.getIconWidth(), ti.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		ti.paintIcon(new JLabel(), b.getGraphics(), 0, 0);
		//
		Formula = new JLabel();
		Formula.setIcon(ti);
		Formula.setAlignmentX(Component.CENTER_ALIGNMENT);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		expressions E = new expressions();
		math = Input.getText();
		
		math = math.replaceAll("\\s+", "");
		math = E.derivate(math);
		math = E.finalStringFormat(math);
		
		System.out.println(math);
		
		TeXFormula formula = new TeXFormula(math);
		TeXIcon ti = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 40);
		BufferedImage b = new BufferedImage(ti.getIconWidth(), ti.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		ti.paintIcon(new JLabel(), b.getGraphics(), 0, 0);
		Formula.setIcon(ti);
		app.pack();
		
	}
}