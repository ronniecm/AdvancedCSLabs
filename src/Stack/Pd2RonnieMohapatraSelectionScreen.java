package Stack;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Pd2RonnieMohapatraSelectionScreen extends JPanel {
	private JButton m1 = new JButton("Maze1"), m2 = new JButton("Maze 2"), m3 = new JButton("Maze 3"), m4 = new JButton("Maze 4"), m5 = new JButton("Maze 5");
	private JPanel buttonPanel;
	private JLabel label;
	public Pd2RonnieMohapatraSelectionScreen()
	{
		setLayout(new BorderLayout());
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		add(buttonPanel, BorderLayout.SOUTH);
		m1.addActionListener(new MazeSelector(0));
		m2.addActionListener(new MazeSelector(1));
		m3.addActionListener(new MazeSelector(2));
		m4.addActionListener(new MazeSelector(3));
		m5.addActionListener(new MazeSelector(4));
		buttonPanel.add(m1);
		buttonPanel.add(m2);
		buttonPanel.add(m3);
		buttonPanel.add(m4);
		buttonPanel.add(m5);
		label = new JLabel("Press one of the buttons to view the maze.");
		add(label, BorderLayout.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	private class MazeSelector implements ActionListener
	{
		private int maze;
		public MazeSelector(int m)
		{
			maze = m;
		}
		
		public void actionPerformed(ActionEvent e)
		{
			if(true)
			{
				try {
					UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				} catch(Exception ex) {
					ex.getStackTrace();
				}
			}
			
			JFrame f = new JFrame("Maze");
			f.setLocation(0, 0);
			f.setSize(1280, 730);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setContentPane(new Pd2RonnieMohapatraMazeGUI(maze));
			f.setVisible(true);
		}
	}
}
