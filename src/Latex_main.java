import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.util.Scanner;

import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;
public class Latex_main extends JFrame{
	
	static derivate d = new derivate();
	static expressions e = new expressions();
	
	public Latex_main() throws HeadlessException {
		super();
		setTitle("Derivative Demo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 530);
		
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Latex_main app = new Latex_main();
		
		String newLine = System.getProperty("line.separator");
		System.out.println("Type function..." + newLine);
		
		Scanner myObj = new Scanner(System.in);
		String math = myObj.nextLine();
		
		
		math = math.replaceAll("\\s+", "");
		math = d.derivate(math);
		math = e.finalStringFormat(math);
		
		// --------------- DE LUCRAT LA POWER FORMAT ---------------------
		
		System.out.println(math);
		//return;
		
		
		TeXFormula formula = new TeXFormula(math);
		TeXIcon ti = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 40);
		BufferedImage b = new BufferedImage(ti.getIconWidth(), ti.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		
		ti.paintIcon(new JLabel(), b.getGraphics(), 0, 0);
		
		JPanel mainPanel = new JPanel();
		JLabel fLabel = new JLabel();
		fLabel.setIcon(ti);
		///*
		mainPanel.add(fLabel);
		app.add(mainPanel);
		app.setVisible(true);
		app.pack();
		//*/
		
	}
}