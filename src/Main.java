import java.awt.Color;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame obj = new JFrame();
		Gameplay gameplay= new Gameplay();
		
		
		obj.setBounds(10, 10, 905, 700);
		obj.setBackground(Color.gray);
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	//add gameplay on obj 
		obj.add(gameplay);
		
	}

}
