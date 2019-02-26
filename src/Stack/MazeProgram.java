package Stack;

import javax.swing.*;

public class MazeProgram {
	public static void main(String[] args)
	{
		if(true)
		{
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch(Exception e) {
				e.getStackTrace();
			}
		}
		JFrame selectionFrame = new JFrame("Selection");
		selectionFrame.setLocation(455, 281);
		selectionFrame.setSize(500, 200);
		selectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		selectionFrame.setContentPane(new Pd2RonnieMohapatraSelectionScreen());
		selectionFrame.setVisible(true);
	}
}
