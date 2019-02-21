package Quarter_3.JuliaSets;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JuliaSet extends JPanel {

	JScrollBar[] scrollBars;
	JFrame frame;

	private float shiftX = -0.7f;
	private float shiftY = 0.27015f;
	private float zoom = 1;
	private float hue = 0.5f;
	private float saturation = 1.0f;
	private float change = 2;

	public JuliaSet() {
		frame = new JFrame();
		frame.setTitle("Julia Sets");
		frame.setSize(1200, 800);

		JPanel scrollPanel = new JPanel();
		scrollPanel.setLayout(new GridLayout(6,1));

		scrollBars = new JScrollBar[6];

		scrollBars[0] = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, -200, 200);
		scrollBars[0].addAdjustmentListener(e -> {
			shiftX = e.getValue() / 100f;
			repaint(0, 0, getWidth(), getHeight());
		});
		scrollPanel.add(scrollBars[0]);

		scrollBars[1] = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, -65, 65);
		scrollBars[1].addAdjustmentListener(e -> {
			shiftY = e.getValue() / 100f;
			repaint(0, 0, getWidth(), getHeight());
		});
		scrollPanel.add(scrollBars[1]);

		scrollBars[2] = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, -100, 100);
		scrollBars[2].addAdjustmentListener(e -> {
			hue = e.getValue() / 100f;
			repaint(0, 0, getWidth(), getHeight());
		});
		scrollPanel.add(scrollBars[2]);

		scrollBars[3] = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, -100, 100);
		scrollBars[3].addAdjustmentListener(e -> {
			saturation = e.getValue() / 100f;
			repaint(0, 0, getWidth(), getHeight());
		});
		scrollPanel.add(scrollBars[3]);

		scrollBars[4] = new JScrollBar(JScrollBar.HORIZONTAL, 10, 0, 0, 1000);
		scrollBars[4].addAdjustmentListener(e -> {
			zoom = e.getValue() / 10f;
			repaint(0, 0, getWidth(), getHeight());
		});
		scrollPanel.add(scrollBars[4]);

		scrollBars[5] = new JScrollBar(JScrollBar.HORIZONTAL, 2, 0, -8, 10);
		scrollBars[5].addAdjustmentListener(e -> {
			change = e.getValue();
			repaint(0, 0, getWidth(), getHeight());
		});
		scrollPanel.add(scrollBars[5]);

		JPanel labels = new JPanel();
		labels.setLayout(new GridLayout(6,1));

		labels.add(new JLabel("ShiftX"));
		labels.add(new JLabel("ShiftY"));
		labels.add(new JLabel("Hue"));
		labels.add(new JLabel("Saturation"));
		labels.add(new JLabel("Zoom"));
		labels.add(new JLabel("Shape"));
		labels.setPreferredSize(new Dimension(200,100));

		JButton reset = new JButton("RESET");
		reset.addActionListener(e -> {
			scrollBars[0].setValue(0);
			scrollBars[1].setValue(0);
			scrollBars[2].setValue(0);
			scrollBars[3].setValue(0);
			scrollBars[4].setValue(10);
			scrollBars[5].setValue(2);
			repaint(0, 0, getWidth(), getHeight());
		});

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(labels, BorderLayout.WEST);
		bottomPanel.add(reset, BorderLayout.EAST);
		bottomPanel.add(scrollPanel, BorderLayout.CENTER);

		frame.add(bottomPanel, BorderLayout.SOUTH);
		frame.add(this, BorderLayout.CENTER);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void drawJuliaSet(Graphics2D g) {
		int w = getWidth();
		int h = getHeight();

		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				double zx = 1.5 * (x - w / 2.0) / (0.5 * zoom * w);
				double zy = (y - h / 2.0) / (0.5 * zoom * h);
				float i = 300;
				while (zx * zx + zy * zy < 4 && i > 0) {
					double tmp = zx * zx - zy * zy + shiftX;
					zy = change * zx * zy + shiftY;
					zx = tmp;
					i--;
				}
				image.setRGB(x, y, Color.HSBtoRGB((300 / i + hue) % 1, saturation, i > 0 ? 1 : 0));
			}
		}
		g.drawImage(image, 0, 0, null);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawJuliaSet((Graphics2D) g);
	}

	public static void main(String[] args) {
		new JuliaSet();
	}
}
