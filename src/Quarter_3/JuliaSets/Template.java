package Quarter_3.JuliaSets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class Template extends JPanel implements AdjustmentListener, ActionListener {

	JScrollBar[] scrollBars;
	JFrame frame;
	JCheckBox checkBox;
//	int red;
//	int[] colors;

	public Template() {
		frame = new JFrame();
		frame.setTitle("Julia Sets");
		frame.add(this); // add jFrame (this) to jPanel

//		red = 0;

		scrollBars = new JScrollBar[3];
//		colors = new int[3];

		JPanel scrollPanel = new JPanel();
		scrollPanel.setLayout(new GridLayout(3,1));

		for (int i = 0; i < scrollBars.length; i++) {
			JScrollBar scrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 255);
			scrollBar.addAdjustmentListener(this);

			scrollPanel.add(scrollBar);
			scrollBar.setUnitIncrement(1);

			scrollBars[i] = scrollBar;
		}

		JPanel otherPanel = new JPanel();
		otherPanel.setLayout(new BorderLayout());

		checkBox = new JCheckBox();
		checkBox.addActionListener(this);

		otherPanel.add(scrollPanel, BorderLayout.CENTER);
		otherPanel.add(checkBox, BorderLayout.EAST);

		frame.add(otherPanel, BorderLayout.SOUTH);

		frame.setSize(1200, 800);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // clean up operation
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(new Color(scrollBars[0].getValue(), scrollBars[1].getValue(), scrollBars[2].getValue()));
		g.fillRect( 0, 0, frame.getWidth(), frame.getHeight()); // Set background to purple
	}

	public static void main(String[] args) {
		new Template();
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
//		for (int i = 0; i < scrollBars.length; i++)
//			if (e.getSource() == scrollBars[i])
//				colors[i] = scrollBars[i].getValue();
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == checkBox)
			for (JScrollBar scrollBar : scrollBars)
				scrollBar.setValue(255 - scrollBar.getValue());
		repaint();
	}

}

