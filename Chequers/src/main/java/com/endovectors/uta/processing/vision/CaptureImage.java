
/*
 * Code taken from https://github.com/cmcmurrough/teaching/blob/master/cv_usb_example/cv_color_detector.cpp,
 * translated to java, and modified to match the needs of Team Endovectors.
 * 
 * Purpose: Identifies colors on a checker board in attempt to determine the state of a game of checkers.
 */

package main.java.com.endovectors.uta.processing.vision;



import org.opencv.core.*;
import org.opencv.videoio.Videoio;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgcodecs.*;
import org.opencv.imgproc.*;

import java.awt.image.BufferedImage;
import java.util.*;

public class CaptureImage {

	private final char COLOR_RED = 'r';
	private final char COLOR_GREEN = 'g';
	private final char COLOR_BLUE = 'b';
	private final char COLOR_ORANGE = 'o';
	private final char COLOR_YELLOW = 'y';
	private final char COLOR_WHITE = 'w';
	// need to add black, which is currently the default
	
	// denotes which player's piece is in the board index
	private final int EMPTY = 0;
	private final int SYSTEM = 1;
	private final int END_USER = 2;
	
	private Mat capturedFrame; // raw image
	private Mat processedFrame; // processed image
	private BufferedImage img; // image of board
	private int board[]; // holds where pieces are
	
	/**
	 * Constructor: creates 2 mat objects:
	 * 				1 for the raw image;
	 * 				other for the processed image
	 */
	
	public CaptureImage()
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		capturedFrame = new Mat();
		processedFrame = new Mat();
		board = new int[32];
	}
	
	/**
	 * Processes an image
	 * @param in image captured
	 * @param out processed image
	 */
	
	public void processFrame(Mat in, Mat out)
	{
		// multiple regions of interest
		
		int playSquares = 32; // number of playable game board squares
		int capSquares = 16; // number of capture squares
		int totalSquares = playSquares + capSquares; // all regions of interest
		
		// keep track of starting row square
		int parity = 0; // 0 is even, 1 is odd, tied to row number
		int count = 0; // row square
		int rowNum = 0; // row number, starting at 0
		
		
		// segment values will need to be adjusted when camera is finally set
		// idea that captured pieces are on left and right
		
		
		int vsegment = in.rows() / 8; // only accounts 8 playable
		int hsegment = in.cols() / 12; // 8 playable, 2 capture, 2 extra
		//int vcenter = vsegment / 2; // vertical distance between segments
		//int hcenter = hsegment / 2; // horizontal distance between segments
		int offset = hsegment * 2; // offset for playable board
		// For angle of camera. Probably not be necessary
		int angleAdjustment = 0; // Will have to change based on center of board and distance from camera
		
		
		// Go through all playable squares
		for (int i = 0; i < playSquares; i++)
		{
			// change offset depending on the row
			if (parity == 0) // playable squares start on immediate left
				offset = hsegment * 2;
			else // playable squares start on 2nd square from left
				offset = hsegment * 3;
			/*
			// change offset if it is a left-side capture piece
			if (i == 0 || i == 6 || i == 12 || i == 18 || i == 24 || i == 30 || i == 36 || i == 42)
			{
				offset = hsegment * 3 / 4;
			}
			
			// change offset if it is a right-side capture piece
			if (i == 5 || i == 11 || i == 17 || i == 23 || i == 29 || i == 35 || i == 41 || i == 47)
			{
				offset = hsegment * 11;
			}
			*/
			// find where roi should be
			Point p1 = new Point(offset + count * hsegment, rowNum * vsegment); // top left point of rectangle (x,y)
			Point p2 = new Point(offset + (count + 1) * hsegment, (rowNum + 1) * vsegment); // bottom right point of rectangle (x,y)
			
			//System.out.println(p1.toString());
			//System.out.println(p2.toString());
			// create rectangle that is board square
			Rect bound = new Rect(p1, p2);
			
			char color;
			if (i == 0)
			{
				// frame only includes rectangle
				Mat roi = new Mat(in, bound);
				
				// get the color
				color = identifyColor(roi);
				
				// copy input image to output image
				in.copyTo(out);
			}
			else
			{
				// frame only includes rectangle
				Mat roi = new Mat(out, bound);
				
				// get the color
				color = identifyColor(roi);
			}
			
			// annotate the output image
			// should piece locations be saved in array in this file?
			// scalar values as (blue, green, red)
			switch(color)
			{
				case COLOR_RED:
					Imgproc.rectangle(out, p1, p2, new Scalar(0, 0, 255), 3);
					board[i] = EMPTY;
					break;
				case COLOR_GREEN:
					Imgproc.rectangle(out, p1, p2, new Scalar(0, 255, 0), 3);
					board[i] = EMPTY;
					break;
				case COLOR_BLUE:
					Imgproc.rectangle(out, p1, p2, new Scalar(255, 0, 0), 3);
					board[i] = SYSTEM; // system's piece
					break;
				case COLOR_YELLOW:
					Imgproc.rectangle(out, p1, p2, new Scalar(0, 255, 255), 3);
					board[i] = EMPTY;
					break;
				case COLOR_ORANGE:
					Imgproc.rectangle(out, p1, p2, new Scalar(0, 128, 255), 3);
					board[i] = END_USER; // end user's piece
					break;
				case COLOR_WHITE:
					Imgproc.rectangle(out, p1, p2, new Scalar(255, 255, 255), 3);
					board[i] = EMPTY;
					break;
				default: // this is black
					Imgproc.rectangle(out, p1, p2, new Scalar(0, 0, 0), 3);
					board[i] = EMPTY;
					break;
			}
			
			count += 2;
			/*
			// change count back to zero if capture piece
			if (i == 0 || i == 6 || i == 12 || i == 18 || i == 24 || i == 30 || i == 36 || i == 42 ||
					i == 5 || i == 11 || i == 17 || i == 23 || i == 29 || i == 35 || i == 41 || i == 47)
			{
				count = 0;
			}
			*/
			if (count == 8)
			{
				parity = ++parity % 2; // change odd or even
				count = 0;
				rowNum++;
			}
		}
		
		/*
		rowNum = 0; // starting row number for capture squares
		offset = hsegment * 0;
		count = 0;
		// keep track of captured squares
		// left: end user, right: system
		for (int i = 0; i < capSquares; i++)
		{	
			// find where roi should be
			Point p1 = new Point(offset + count * hsegment, rowNum * vsegment); // top left point of rectangle (x,y)
			Point p2 = new Point(offset + (count + 1) * hsegment, (rowNum + 1) * vsegment); // bottom right point of rectangle (x,y)
			// create rectangle that is board square
			Rect bound = new Rect(p1, p2);
			
			char color;
			
			// frame only includes rectangle
			Mat roi = new Mat(out, bound);
			
			// get the color
			color = identifyColor(roi);
			
			switch(color)
			{
				case COLOR_RED:
					Imgproc.rectangle(out, p1, p2, new Scalar(0, 0, 255), 3);
					break;
				case COLOR_GREEN:
					Imgproc.rectangle(out, p1, p2, new Scalar(0, 255, 0), 3);
					break;
				case COLOR_BLUE:
					Imgproc.rectangle(out, p1, p2, new Scalar(255, 0, 0), 3);
					break;
				case COLOR_YELLOW:
					Imgproc.rectangle(out, p1, p2, new Scalar(0, 255, 255), 3);
					break;
				case COLOR_ORANGE:
					Imgproc.rectangle(out, p1, p2, new Scalar(0, 128, 255), 3);
					break;
				case COLOR_WHITE:
					Imgproc.rectangle(out, p1, p2, new Scalar(255, 255, 255), 3);
					break;
				default: // this is black
					Imgproc.rectangle(out, p1, p2, new Scalar(0, 0, 0), 3);
					break;
			}
			
			count++;
			if (count == 1)
			{
				offset = hsegment * 10 + 50;
			}
			else if (count == 2)
			{
				count = 0;
				offset = hsegment * 10;
				rowNum++;
			}
		}*/
	}
	
	/**
	 * Identifies the color in the frame
	 * @param in the Mat image in the region of interest
	 * @return the color
	 */
	
	public char identifyColor(Mat in)
	{
		//Mat blue = new Mat(in.rows(), in.cols(), CvType.CV_8UC1);
		//Mat green = new Mat(in.rows(), in.cols(), CvType.CV_8UC1);
		//Mat red = new Mat(in.rows(), in.cols(), CvType.CV_8UC1);
		
		//split the channels of the image
		Mat blue = new Mat(); // default is CV_8UC3
		Mat green = new Mat();
		Mat red = new Mat();
		List<Mat> channels = new ArrayList<Mat>(3);
		Core.split(in, channels);
		blue = channels.get(0); // makes all 3 CV_8UC1
		green = channels.get(1);
		red = channels.get(2);
		//System.out.println(blue.toString());
		
		// add the intensities
		Mat intensity = new Mat(in.rows(), in.cols(), CvType.CV_32F);
		//Mat mask = new Mat();
		Core.add(blue, green, intensity);//, mask, CvType.CV_32F);
		Core.add(intensity, red, intensity);//, mask, CvType.CV_32F);
		
		
		
		// not sure if correct from here to ...
		
		
		Mat inten = new Mat();
		Core.divide(intensity, Scalar.all(3.0), inten);
		//System.out.println(intensity.toString());
		//Core.divide(3.0, intensity, inten);
		// if intensity = intensity / 3.0; means element-wise division
		// use intensity.muls(Mat m)
		// so make new Mat m of same size that has each element of 1/3
		
		/*
		 * or
		 * About per-element division you can use Core.divide()

			Core.divide(A,Scalar.all(d), B);
			
			It's equivalent to B=A/d
		 */
		
		// find normalized values
		Mat bnorm = new Mat();
		Mat gnorm = new Mat();
		Mat rnorm = new Mat();
		//blue.convertTo(blue, CvType.CV_32F);
		//green.convertTo(green, CvType.CV_32F);
		//red.convertTo(red, CvType.CV_32F);
		
		Core.divide(blue, inten, bnorm);
		Core.divide(green, inten, gnorm);
		Core.divide(red, inten, rnorm);
		
		
		// find average norm values
		Scalar val = new Scalar(0);
		val = Core.mean(bnorm);
		String value[] = val.toString().split(",");
		String s = value[0].substring(1);
		double bavg = Double.parseDouble(s);
		val = Core.mean(gnorm);
		String value1[] = val.toString().split(",");
		String s1 = value1[0].substring(1);
		double gavg = Double.parseDouble(s1);
		val = Core.mean(rnorm);
		String value2[] = val.toString().split(",");
		String s2 = value2[0].substring(1);
		double ravg = Double.parseDouble(s2);
		
		
		// ... here
		
		
		
		// define the reference color values
		double RED[] = {0.4, 0.5, 1.8};
		double GREEN[] = {1.0, 1.2, 1.0};
		double BLUE[] = {1.75, 1.0, 0.5};
		double YELLOW[] = {0.82, 1.7, 1.7};
		double ORANGE[] = {0.2, 1.0, 2.0};
		double WHITE[] = {2.0, 1.7, 1.7};
		
		// compute the square error relative to the reference color values
		double minError = 3.0;
		double errorSqr;
		char bestFit = 'x';
		
		// check RED fitness
		errorSqr = normSqr(RED[0], RED[1], RED[2], bavg, gavg, ravg);
		if(errorSqr < minError)
		{
			minError = errorSqr;
			bestFit = COLOR_RED;
		}
		// check GREEN fitness
		errorSqr = normSqr(GREEN[0], GREEN[1], GREEN[2], bavg, gavg, ravg);
		if(errorSqr < minError)
		{
			minError = errorSqr;
			bestFit = COLOR_GREEN;
		}
		// check BLUE fitness
		errorSqr = normSqr(BLUE[0], BLUE[1], BLUE[2], bavg, gavg, ravg);
		if(errorSqr < minError)
		{
			minError = errorSqr;
			bestFit = COLOR_BLUE;
		}
		// check YELLOW fitness
		errorSqr = normSqr(YELLOW[0], YELLOW[1], YELLOW[2], bavg, gavg, ravg);
		if(errorSqr < minError)
		{
			minError = errorSqr;
			bestFit = COLOR_YELLOW;
		}
		// check ORANGE fitness
		errorSqr = normSqr(ORANGE[0], ORANGE[1], ORANGE[2], bavg, gavg, ravg);
		if(errorSqr < minError)
		{
			minError = errorSqr;
			bestFit = COLOR_ORANGE;
		}
		// check WHITE fitness
		errorSqr = normSqr(WHITE[0], WHITE[1], WHITE[2], bavg, gavg, ravg);
		if(errorSqr < minError)
		{
			minError = errorSqr;
			bestFit = COLOR_WHITE;
		}
		
		// return the best fit color label
		return bestFit;
	}
	
	/**
	 * Computes the squared norm of two tuples
	 * @param x1 x coordinate of first point
	 * @param y1 y coordinate of first point
	 * @param z1 z coordinate of first point
	 * @param x2 x coordinate of second point
	 * @param y2 y coordinate of second point
	 * @param z2 z coordinate of second point
	 * @return the squared norm of the two tuples
	 */
	
	public double normSqr(double x1, double y1, double z1, double x2, double y2, double z2)
	{
		return (x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2) + (z1 - z2)*(z1 - z2);
	}
	
	/**
	 * Capture images and run color processing through here
	 */
	
	public void capture()
	{
		//System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    
	    VideoCapture camera = new VideoCapture();
	    CaptureImage image = new CaptureImage();
	    
	    camera.open(0); //Useless
	    if(!camera.isOpened())
	    {
	        System.out.println("Camera Error");
	        
	        // Determine whether to use System.exit(0) or return
	        
	    }
	    else
	    {
	        System.out.println("Camera OK");
	    }
		
		    
		boolean success = camera.read(capturedFrame);
		if (success)
		{
		    image.processFrame(capturedFrame, processedFrame);
		    // processedFrame should be CV_8UC3
		    
		    int bufferSize = processedFrame.channels() * processedFrame.cols() * processedFrame.rows();
		    byte[] b = new byte[bufferSize];
		    
		    processedFrame.get(0,0,b); // get all the pixels
		    // This might need to be BufferedImage.TYPE_INT_ARGB
		    img = new BufferedImage(processedFrame.cols(), processedFrame.rows(), BufferedImage.TYPE_INT_RGB);
		    int width = (int)camera.get(Videoio.CAP_PROP_FRAME_WIDTH);
		    int height = (int)camera.get(Videoio.CAP_PROP_FRAME_HEIGHT);
		    //img.getRaster().setDataElements(0, 0, width, height, b);
		    byte[] a = new byte[bufferSize];
		    System.arraycopy(b, 0, a, 0, bufferSize);
		    
		    Imgcodecs.imwrite("camera.jpg",processedFrame);
		    System.out.println("Success");
		}
		else
			System.out.println("Unable to capture image");
		
	    camera.release();
	}
	
	/**
	 * @return BufferedImage of the processed image
	 */
	
	public BufferedImage getImage()
	{
		return this.img;
	}
	
	/**
	 * @return state of board
	 */
	
	public int[] getBoard()
	{
		return this.board;
	}
	
	public static void main(String args[])
	{
		CaptureImage im = new CaptureImage();
		im.capture();
	}
}

