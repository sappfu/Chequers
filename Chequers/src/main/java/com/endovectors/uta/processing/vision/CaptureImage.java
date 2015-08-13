
/*
 * Code taken from https://github.com/cmcmurrough/teaching/blob/master/cv_usb_example/cv_color_detector.cpp,
 * translated to java, and modified to match the needs of Team Endovectors.
 * 
 * Purpose: Identifies colors on a checker board in attempt to determine the state of a game of checkers.
 */

package com.endovectors.uta.processing.vision;



import org.opencv.core.*;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.*;

import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

import com.endovectors.uta.processing.CheckersBoard;

public class CaptureImage {

	//private final char COLOR_RED = 'r';
	//private final char COLOR_GREEN = 'g';
	private final char COLOR_BLUE = 'b';
	private final char COLOR_ORANGE = 'o';
	//private final char COLOR_YELLOW = 'y';
	private final char COLOR_WHITE = 'w';
	private final char COLOR_BLACK = 'l';
	
	
	private Mat capturedFrame; // raw image
	private Mat processedFrame; // processed image
	private BufferedImage img; // image of board
	private byte board[]; // holds where pieces are
	private byte captured[]; // holds where captured pieces are; even is left side, odd is right
	
	
	static int test = 0;
	
	
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
		board = new byte[32];
		captured = new byte[12];
	}
	
	/**
	 * Determines where captured pieces are
	 * @param in Mat image of the board
	 */
	
	public void findCaptured(Mat in)
	{
		int vsegment = in.rows() / 8; // only accounts 8 playable
		int hsegment = in.cols() / 12; // 8 playable, 2 capture, 2 extra
		int offset; // offset for playable board
		
		int capSquares = 12; // number of capture squares
		int rowNum = 1; // starting row number for capture squares
		int rightdx = 48;
		int leftdx = 0;
		offset = hsegment;
		int count = 0;
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
			Mat roi = new Mat(in, bound);
			
			// get the color
			color = identifyColor(roi);
			
			switch(color)
			{
				case COLOR_BLUE:
					//Imgproc.rectangle(in, p1, p2, new Scalar(255, 0, 0), 3);
					Core.rectangle(in, p1, p2, new Scalar(255, 0, 0), 2);
					captured[i] = 1;
					break;
				case COLOR_ORANGE:
					//Imgproc.rectangle(in, p1, p2, new Scalar(0, 128, 255), 3);
					Core.rectangle(in, p1, p2, new Scalar(0, 128, 255), 2);
					captured[i] = 1;
					break;
				case COLOR_WHITE:
					//Imgproc.rectangle(in, p1, p2, new Scalar(255, 255, 255), 3);
					Core.rectangle(in, p1, p2, new Scalar(255, 255, 255), 2);
					captured[i] = 0;
					break;
				case COLOR_BLACK:
					//Imgproc.rectangle(in, p1, p2, new Scalar(0, 0, 0), 3);
					Core.rectangle(in, p1, p2, new Scalar(255, 255, 255), 2);
					captured[i] = 0;
					break;
			}
			
			count++;
			if (count == 1)
			{
				offset = hsegment * 10 - rightdx;
			}
			else if (count == 2)
			{
				count = 0;
				rightdx -= 6;
				leftdx += 6;
				offset = hsegment - leftdx;
				rowNum++;
			}
		}
	}
	
	/**
	 * Processes the board image
	 * @param in image captured of board
	 * @param out processed image of board
	 */
	
	public void processFrame(Mat in, Mat out)
	{
		// multiple regions of interest
		
		int playSquares = 32; // number of playable game board squares
		
		// keep track of starting row square
		int parity = 0; // 0 is even, 1 is odd, tied to row number
		int count = 0; // row square
		int rowNum = 0; // row number, starting at 0
		
		int vsegment = in.rows() / 8; // only accounts 8 playable
		int hsegment = in.cols() / 10; // 8 playable, 2 capture
		int hOffset = hsegment * 2; // offset for playable board
		int vOffset = vsegment + 40;
		
		// For angle of camera
		int dx = 80;
		int ddx = 0;
		hsegment -= 16;
		
		int dy = 20;
		vsegment -= 24;
		
		// Go through all playable squares
		for (int i = 0; i < playSquares; i++)
		{
			// change offset depending on the row
			if (parity == 0) // playable squares start on 2nd square from left
			{
				if (rowNum >= 5)
					dx -= 3;
				hOffset = hsegment * 2 + dx;
			}
			else // playable squares start on immediate left
			{
				if (rowNum >= 5)
					dx -= 3;
				hOffset = hsegment + dx;
			}
			
			if (rowNum == 4)
				if (count == 6)
					ddx = 10;
			if (rowNum == 5)
			{
				if (count == 0)
					ddx = -6;
				else if (count == 2)
					ddx = 6;
				else if (count == 4)
					ddx = 12;
				else if (count == 6)
					ddx = 20;
			}
			if (rowNum == 6)
			{
				if (count == 0)
					ddx = 0;
				else if (count == 2)
					ddx = 16;
				else if (count == 4)
					ddx = 32;
				else if (count == 6)
					ddx = 40;
			}
			if (rowNum == 7)
			{
				if (count == 0)
					ddx = 0;
				else if (count == 2)
					ddx = 24;
				else if (count == 4)
					ddx = 40;
				else
					ddx = 52;
			}

			// find where roi should be
			//System.out.println("" + vOffset);
			Point p1 = new Point(hOffset + count * hsegment + ddx, vOffset + rowNum * vsegment - dy); // top left point of rectangle (x,y)
			Point p2 = new Point(hOffset + (count + 1) * hsegment + ddx, vOffset + (rowNum + 1) * vsegment - dy); // bottom right point of rectangle (x,y)
			
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
			// scalar values as (blue, green, red)
			switch(color)
			{
				case COLOR_BLUE:
					//Imgproc.rectangle(out, p1, p2, new Scalar(255, 0, 0), 2);
					Core.rectangle(out, p1, p2, new Scalar(255, 0, 0), 2);
					board[i] = CheckersBoard.BLACK; // end user's piece
					break;
				case COLOR_ORANGE:
					//Imgproc.rectangle(out, p1, p2, new Scalar(0, 128, 255), 2);
					Core.rectangle(out, p1, p2, new Scalar(0, 128, 255), 2);
					board[i] = CheckersBoard.WHITE; // system's piece
					break;
				case COLOR_WHITE:
					//Imgproc.rectangle(out, p1, p2, new Scalar(255, 255, 255), 2);
					Core.rectangle(out, p1, p2, new Scalar(255, 255, 255), 2);
					board[i] = CheckersBoard.EMPTY;
					break;
				case COLOR_BLACK: // this is black
					//Imgproc.rectangle(out, p1, p2, new Scalar(0, 0, 0), 2);
					Core.rectangle(out, p1, p2, new Scalar(0, 0, 0), 2); // maybe add 8, 0 as line type and fractional bits
					board[i] = CheckersBoard.EMPTY;
					break;
			}
			
			count += 2;
			if (count == 8)
			{
				parity = ++parity % 2; // change odd or even
				count = 0;
				rowNum++;
				hsegment += 2;
				dx -= 10;
				dy += 10;
				vsegment += 3;
			}
		}
	}
	
	/**
	 * Determines which pieces are kings
	 * @param in Mat image of board
	 */
	
	public void determineKings(Mat in)
	{
		int playSquares = 32;
		
		Mat dst = new Mat(in.rows(), in.cols(), in.type());
        in.copyTo(dst);

        Imgproc.cvtColor(dst, dst, Imgproc.COLOR_BGR2GRAY); // change to single color
        
        Mat canny = new Mat();
        Imgproc.Canny(dst, canny, 100, 200); // make image a canny image that is only edges; 2,4
        // lower threshold values find more edges
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Mat hierarchy = new Mat(); // holds nested contour information
        Imgproc.findContours(canny, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE); // Imgproc.RETR_LIST, TREE
        
        //draw contour image
        Mat mask = new Mat();
        mask = Mat.zeros(dst.size(), dst.type());
        Imgproc.drawContours(mask, contours, -1, new Scalar(255,255,255), 1, 8, hierarchy, 2, new Point());
        Highgui.imwrite("contours.jpg", mask);

		
		ArrayList occupied = new ArrayList<Integer>();
		for (int i = 0; i < playSquares; i++)
		{
			if (board[i] != 0)
				occupied.add(i);
		}
		
		for (int i = 0; i < contours.size(); i++) // assuming only contours are checker pieces
		{	
			// determine if it should be a king
			// use Rect r = Imgproc.boundingRect then find height of it by r.height
			
            // Get bounding rect of contour
            Rect bound = Imgproc.boundingRect(contours.get(i));
            
            if (bound.height > in.rows() / 8)
			{
				//board[(int) occupied.get(0)]++; // make it a king
				//occupied.remove(0);
			}
		}
        
        
        
        // or apply to each region of interest
        
        /*		
		// keep track of starting row square
		int parity = 0; // 0 is even, 1 is odd, tied to row number
		int count = 0; // row square
		int rowNum = 0; // row number, starting at 0
		
		int vsegment = in.rows() / 8; // only accounts 8 playable
		int hsegment = in.cols() / 12; // 8 playable, 2 capture, 2 extra
		int offset = hsegment * 2; // offset for playable board
		
		// For angle of camera
		int dx = 48;
		hsegment -= 8;
		
		
		// Go through all playable squares
		for (int i = 0; i < playSquares; i++)
		{
			// change offset depending on the row
			if (parity == 0) // playable squares start on immediate left
				offset = hsegment * 3 + dx;
			else // playable squares start on 2nd square from left
				offset = hsegment * 2 + dx;

			// find where roi should be
			Point p1 = new Point(offset + count * hsegment, rowNum * vsegment); // top left point of rectangle (x,y)
			Point p2 = new Point(offset + (count + 1) * hsegment, (rowNum + 1) * vsegment); // bottom right point of rectangle (x,y)
			
			// create rectangle that is board square
			Rect bound = new Rect(p1, p2);
			
			// frame only includes rectangle
			Mat roi = new Mat(in, bound);

	        Imgproc.cvtColor(roi, roi, Imgproc.COLOR_BGR2GRAY); // change to single color
	        
	        Mat canny = new Mat();
	        Imgproc.Canny(roi, canny, 2, 4); // make image a canny image that is only edges; 2,4
	        // lower threshold values find more edges
	        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
	        Mat hierarchy = new Mat(); // holds nested contour information
	        Imgproc.findContours(canny, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE); // Imgproc.RETR_LIST, TREE
			
	        // Get bounding rect of contour
            Rect rect = Imgproc.boundingRect(contours.get(0));
            
            if (rect.height > in.rows() / 8)
			{
				board[i]++; // make it a king
			}
	        
			count += 2;
			if (count == 8)
			{
				parity = ++parity % 2; // change odd or even
				count = 0;
				rowNum++;
				hsegment += 1;
				dx -= 6;
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
		
		
		//original values
		/*
		// define the reference color values
		//double RED[] = {0.4, 0.5, 1.8};
		//double GREEN[] = {1.0, 1.2, 1.0};
		double BLUE[] = {1.75, 1.0, 0.5};
		//double YELLOW[] = {0.82, 1.7, 1.7};
		double ORANGE[] = {0.2, 1.0, 2.0};
		double WHITE[] = {2.0, 1.7, 1.7};
		//double BLACK[] = {0.0, 0.3, 0.3};
		*/
		
		
		// define the reference color values
		//double RED[] = {0.4, 0.5, 1.8};
		//double GREEN[] = {1.0, 1.2, 1.0};
		double BLUE[] = {1.75, 1.0, 0.5};
		//double YELLOW[] = {0.82, 1.7, 1.7};
		double ORANGE[] = {0.2, 1.0, 2.0};
		double WHITE[] = {2.0, 1.7, 1.7};
		double BLACK[] = {0.0, 0.3, 0.3};
		
		// compute the square error relative to the reference color values
		//double minError = 3.0;
		double minError = 1.5;
		double errorSqr;
		char bestFit = 'x';
		
		
		test++;
		System.out.print("\n\n" + test + "\n\n");
		
		
		// check BLUE fitness
		errorSqr = normSqr(BLUE[0], BLUE[1], BLUE[2], bavg, gavg, ravg);
		System.out.println("Blue: " + errorSqr);
		if(errorSqr < minError)
		{
			minError = errorSqr;
			bestFit = COLOR_BLUE;
		}
		// check ORANGE fitness
		errorSqr = normSqr(ORANGE[0], ORANGE[1], ORANGE[2], bavg, gavg, ravg);
		System.out.println("Orange: " + errorSqr);
		if(errorSqr < minError)
		{
			minError = errorSqr;
			bestFit = COLOR_ORANGE;
		}
		// check WHITE fitness
		errorSqr = normSqr(WHITE[0], WHITE[1], WHITE[2], bavg, gavg, ravg);
		System.out.println("White: " + errorSqr);
		if(errorSqr < minError)
		{
			minError = errorSqr;
			bestFit = COLOR_WHITE;
		}
		// check BLACK fitness
		errorSqr = normSqr(BLACK[0], BLACK[1], BLACK[2], bavg, gavg, ravg);
		System.out.println("Black: " + errorSqr);
		if(errorSqr < minError)
		{
			minError = errorSqr;
			bestFit = COLOR_BLACK;
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
	    VideoCapture camera = new VideoCapture();
	    
	    camera.set(12, -20); // change contrast, might not be necessary
	    
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
		    
		    //image.findCaptured(processedFrame);
		    
		    image.determineKings(capturedFrame);
		    
		    int bufferSize = processedFrame.channels() * processedFrame.cols() * processedFrame.rows();
		    byte[] b = new byte[bufferSize];
		    
		    processedFrame.get(0,0,b); // get all the pixels
		    // This might need to be BufferedImage.TYPE_INT_ARGB
		    img = new BufferedImage(processedFrame.cols(), processedFrame.rows(), BufferedImage.TYPE_INT_RGB);
		    int width = (int)camera.get(Highgui.CV_CAP_PROP_FRAME_WIDTH);
		    int height = (int)camera.get(Highgui.CV_CAP_PROP_FRAME_HEIGHT);
		    //img.getRaster().setDataElements(0, 0, width, height, b);
		    byte[] a = new byte[bufferSize];
		    System.arraycopy(b, 0, a, 0, bufferSize);
		    
		    Highgui.imwrite("camera.jpg",processedFrame);
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
	
	public byte[] getBoard()
	{
		return this.board;
	}
	
	public byte[] getCaptured()
	{
		return this.captured;
	}
	
	public static void main(String args[])
	{
		CaptureImage im = new CaptureImage();
		im.capture();
	}
}

