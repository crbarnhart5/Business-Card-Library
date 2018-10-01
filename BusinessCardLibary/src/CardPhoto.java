import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
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

import com.mongodb.DBObject;

public class CardPhoto {
	static BufferedImage image;
	static boolean exit;

	public static void CardPhoto() {

	}

	/**
	 * <p>
	 * Streams the webcam until the user presses the Take Photo button. Influenced
	 * by
	 * http://jdbates.blogspot.com/2015/01/this-post-is-about-how-to-capture-and.html
	 * </p>
	 * 
	 * @return the BufferedImage of the business card
	 */
	public static BufferedImage InteractWithUser() {
		Scanner sc = new Scanner(System.in);
		exit = false;

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

		// Set up JFrame with take photo button
		JFrame frame2 = new JFrame();
		JButton button = new JButton("Take Photo");
		JPanel panel = new JPanel();
		panel.add(button);
		frame2.add(panel);
		panel.setVisible(true);
		frame2.pack();
		frame2.setVisible(true);

		// Sets the button and frame next to the first frame
		frame2.setLocation(frame.getX() + frame.getWidth(), frame.getY());

		// Adds listener to take photo button to take a photo and exit
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				takePhoto(matrix);
				frame.dispose();
				frame2.dispose();
			}
		});

		// Runs until take photo has been clicked
		while (!exit) {

			// Copy pixels from the Mat to the image
			matrix.get(0, 0, data);

			// Refresh the display
			label.repaint();

			// Grab the next frame
			capture.read(matrix);
		}
		return image;
	}

	/**
	 * <p>
	 * Converts a Mat frame to a BufferedImage
	 * </p>
	 * 
	 * @param in the Mat frame to be converted
	 * @return the BufferedImage of the converted Mat frame
	 */
	private static BufferedImage mat2Img(Mat in) {
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

	/**
	 * <p>
	 * Saves the current frame to a BufferedImage
	 * </p>
	 * 
	 * @param mat the Mat frame to be converted to a BufferedImage
	 */
	private static void takePhoto(Mat mat) {
		image = mat2Img(mat);
		exit = true;

	}

	// TODO
	public static void displayCard(DBObject contact) {
		byte[] photo = (byte[]) contact.get("card");
		ByteArrayInputStream bais = new ByteArrayInputStream(photo);

		try {
			BufferedImage image = ImageIO.read(bais);
			JFrame frame = new JFrame();

			ImageIcon icon = new ImageIcon(image);

			JLabel label = new JLabel(icon);

			frame.getContentPane().add(label);

			// Resize it to fit the video
			frame.pack();
			frame.setVisible(true);
		} catch (IOException e) {
			throw new RuntimeException(e);

		}
	}
}