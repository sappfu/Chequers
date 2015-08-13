package com.endovectors.uta.hardware;

import java.io.BufferedReader; //BufferedReader makes reading operation efficient
import java.io.IOException;
import java.io.InputStreamReader; //InputStreamReader decodes a stream of bytes into a character set
import java.io.OutputStream; //writes stream of bytes into serial port
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent; //deals with possible events in serial port (eg: data received)
import gnu.io.SerialPortEventListener; //listens to the a possible event on serial port and notifies when it does
import java.util.Enumeration;
import gnu.io.PortInUseException; //all the exceptions.Never mind them for now
import gnu.io.UnsupportedCommOperationException;
import java.util.Scanner; //to get user input of name

public class UsbComm implements SerialPortEventListener {

	private SerialPort serialPort; 				// serial port object
	private CommPortIdentifier port = null; 	// COM port
	private static final int TIME_OUT = 2000; 	// time in milliseconds
	private static final int BAUD_RATE = 9600; 	// baud rate set to 9600bps
	private static OutputStream output; 		// output stream
	private static String moveInstr; 		    // user input name string
	private BufferedReader input; 				// input buffer	
	static Scanner inputString; 				// user input string

	void connect() {
		this.initialize();
		this.portConnect();
	}

	// method initialize
	void initialize() {
		CommPortIdentifier ports = null; // to browse through each port identified
		Enumeration<?> portEnum = CommPortIdentifier.getPortIdentifiers(); // get all available ports
		
		while (portEnum.hasMoreElements()) { // browse through available ports
			ports = (CommPortIdentifier) portEnum.nextElement();
			
			//check if port is serial, and the port the arduino is connected to
			if (ports.getPortType() == CommPortIdentifier.PORT_SERIAL && ports.getName().equals("/dev/ttyACM0")) {
				//System.out.println("COM port found");
				port = ports; // initialize my port
				break;
			}
		}
		
		if (port == null) { // if serial port specified is not found
			System.out.println("SERIAL PORT NOT FOUND");
			System.exit(1);
		}
		System.out.println("Initialization Successful");
	}// end of initialize method


	private void portConnect() {
		// connect to port
		try {
			serialPort = (SerialPort) port.open(this.getClass().getName(), TIME_OUT); // down cast the comm port to serial port
			// give the name of the application
			// time to wait
			//System.out.println("Port open succesful: COM4");

			// set serial port parameters
			serialPort.setSerialPortParams(BAUD_RATE, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

		} catch (PortInUseException e) {
			System.out.println("Port already in use");
			System.exit(1);
		} catch (NullPointerException e2) {
			System.out.println("COM port maybe disconnected");
		} catch (UnsupportedCommOperationException e3) {
			System.out.println(e3.toString());
		}

		// input and output channels
		try {
			// defining reader and output stream
			input = new BufferedReader(new InputStreamReader(
					serialPort.getInputStream()));
			output = serialPort.getOutputStream();
			// adding listeners to input and output streams
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
			serialPort.notifyOnOutputEmpty(true);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	// readWrite method
	public void serialEvent(SerialPortEvent evt) {
		// if data available on serial port
		if (evt.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputLine = input.readLine();
				System.out.println("Recieved: " + inputLine);
				
				if (inputLine.equals("FINISHED"))
					System.out.println("ACCORDING TO THE ARDUINO, ARM MOVEMENT IS COMPLETE");
				
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}	
	}
	public void instruction(String str){
			moveInstr = inputString.nextLine();
			moveInstr = moveInstr + '\n';
			System.out.print("Sending: ");
			System.out.printf("%s", moveInstr);
			try {
				output.write(moveInstr.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // sends the user name
		
	}

	// main method
	public static void main(String[] args) {
		UsbComm arduinoComm = new UsbComm(); // creates an object of the class
		arduinoComm.initialize();
		arduinoComm.portConnect();
		while (true)
		{
			inputString = new Scanner(System.in); // get instructions to send to Arduino
		}// wait till any activity		
	}// end of main method	
}
