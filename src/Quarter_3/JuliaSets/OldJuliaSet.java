package Quarter_3.JuliaSets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.image.BufferedImage;

public class OldJuliaSet extends JPanel implements AdjustmentListener, ActionListener {

	JScrollBar[] scrollBars;
	JFrame frame;
	JCheckBox checkBox;

	public OldJuliaSet() {
		frame = new JFrame();
		frame.setTitle("Julia Sets");
		frame.add(this);

		scrollBars = new JScrollBar[5];

		JPanel scrollPanel = new JPanel();
		scrollPanel.setLayout(new GridLayout(5,1));

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
		g.fillRect( 0, 0, frame.getWidth(), frame.getHeight());
	}

	public static void main(String[] args) {
		new OldJuliaSet();
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		repaint();
		drawSet();
	}

	private void drawSet() {
		BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		int zoom = 1;
		for (int i = 0; i < getHeight(); i++) {
			for (int j = 0; j < getWidth(); j++) {
				int count = 0;
				double zx = 1.5 * (j - getWidth() / 2.0) / (.5 * zoom * getWidth());
				double zy = (i - getHeight() / 2.0) / (.5 * zoom * getHeight());
				boolean a = Math.pow(zx, 2) + Math.pow(zy, 2) < 6;
				while (count < 300) {
					double sum = Math.pow(zx, 2) - Math.pow(zy, 2);
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == checkBox)
			for (JScrollBar scrollBar : scrollBars)
				scrollBar.setValue(255 - scrollBar.getValue());
		repaint();
	}

}

