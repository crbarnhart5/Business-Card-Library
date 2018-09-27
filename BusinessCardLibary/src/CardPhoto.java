import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

public class CardPhoto {
	// code influenced
	// http://jdbates.blogspot.com/2015/01/this-post-is-about-how-to-capture-and.html
	public static BufferedImage InteractWithUser(String args[]) {
		Scanner sc = new Scanner(System.in);

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		// Open the webcam
		VideoCapture capture = new VideoCapture(0);
		Mat matrix = new Mat();

		// Grab the first frame to get the dimensions
		capture.read(matrix);

		BufferedImage image = new BufferedImage(matrix.cols(), matrix.rows(), BufferedImage.TYPE_3BYTE_BGR);
		byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();

		// Create a window to display the video
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		

		ImageIcon icon = new ImageIcon(image);

		JLabel label = new JLabel(icon);
		
		frame.getContentPane().add(label);
		
		// Resize it to fit the video
		frame.pack();
		frame.setVisible(true);
		
		JFrame frame2 = new JFrame();
		JButton button = new JButton("Take Photo");
		JPanel panel = new JPanel();
		panel.add(button);
		frame2.add(panel);
		panel.setVisible(true);
		frame2.pack();
		frame2.setVisible(true);
		frame2.setLocation(frame.getX() + frame.getWidth(), frame.getY());
		
		button.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
			    BufferedImage image = takePhoto(matrix);
			    frame.dispose();
			    frame2.dispose();
			  } 
			} );

		
		while (true) {

			// Copy pixels from the Mat to the image
			matrix.get(0, 0, data);

			// Refresh the display
			label.repaint();

			// Grab the next frame
			capture.read(matrix);
		}
	}

	public static BufferedImage mat2Img(Mat in) {
		BufferedImage out;
		byte[] data = new byte[320 * 240 * (int) in.elemSize()];
		int type;
		in.get(0, 0, data);

		if (in.channels() == 1)
			type = BufferedImage.TYPE_BYTE_GRAY;
		else
			type = BufferedImage.TYPE_3BYTE_BGR;

		out = new BufferedImage(320, 240, type);

		out.getRaster().setDataElements(0, 0, 320, 240, data);
		return out;
	}

	private static BufferedImage takePhoto(Mat mat) {
		BufferedImage image = mat2Img(mat);
		
		return image;
		
	}

}