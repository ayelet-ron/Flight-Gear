package Interperter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class SimulatorServer {
	private static SimulatorServer instance;
	private Socket socket;
	private OutputStream os;
	private BufferedWriter bw;
	private OutputStreamWriter osw;
    
	private SimulatorServer() {
	}
	public static SimulatorServer getServer() {
		if(instance==null) {
			synchronized(SimulatorServer.class) {
				if(instance==null) {
					instance = new SimulatorServer();
				}
			}
		}
		return instance;
	}
	public void open(String ip,int port) {
		try {
			this.socket = new Socket(ip,port); 
			this.os = socket.getOutputStream();
	        this.osw = new OutputStreamWriter(os);
	        this.bw = new BufferedWriter(osw);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		try {
			this.bw.close();
			this.osw.close();
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setVariable(String name,double newVal) {
		String message = "set " + name+ " " + newVal;
		try {
			this.bw.write(message);
			this.bw.newLine();
			this.bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void sendString(String name) {
		try {
			this.bw.write(name);
			this.bw.newLine();
			this.bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}




//private void runServer(int port, int interval) throws Exception {
//ServerSocket server = new ServerSocket(port);
//System.out.println("Server is open. waiting for clients...");
//server.setSoTimeout(300000000);//check why timeout is so big
//while (!stop) {
//  try {
//      Socket aClient = server.accept(); // blocking call
//      try {
//      	aClient.getInputStream();
//      	//aClient.getOutputStream());
//      	Thread.sleep(interval);
//          aClient.getInputStream().close();
//          //aClient.getOutputStream().close();
//          aClient.close();
//
//      } catch (IOException e) {
//          System.out.println("-> Received an IOException: " + e.getMessage());
//      }
//  } catch (SocketTimeoutException e) {
//      System.out.println("-> No client connected within the set timeout, trying again...");
//  }
//}
//System.out.println("-> Closing server");
//server.close();
//}
