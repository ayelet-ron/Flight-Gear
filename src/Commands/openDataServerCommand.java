package Commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import Interperter.Parser;
import Interperter.TypeArguments;
import Utilities.StringToArgumentParser;

public class openDataServerCommand implements Command {
    private int port, interval;
    private boolean stop;
	
	@Override
	public int getArgs(String[] code, int index, List<Object> args) throws Exception {
		return StringToArgumentParser.parse(code, index, 3, args, TypeArguments.String, TypeArguments.Integer,
				TypeArguments.Integer);
	}

	@Override
	public void doCommand(List<Object> parameters) {
		this.port  =  (int)parameters.get(1);
		this.interval = (int)parameters.get(2);
		this.stop = false;

        new Thread(() -> {
            try {
                runServer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
	
	private void runServer() throws Exception {
		BufferedReader read=null;
        @SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(this.port);
        boolean sentOnce =false;
        server.setSoTimeout(1000); //??
        while (!this.stop) {
            try {
                Socket aClient = server.accept(); // blocking call
                try {
                	//interval
       			 	read =new BufferedReader(new InputStreamReader(aClient.getInputStream()));
                    while(!this.stop) {
                        String line = read.readLine();
                        if (line == null) continue;
                        String[] variables = line.split(",");
                        ConcurrentHashMap<String, Double> map = Parser.getInstance().getBindVarMap();
                        map.put("simX", Double.parseDouble(variables[0]));
                        map.put("simY", Double.parseDouble(variables[1]));
                        map.put("simZ", Double.parseDouble(variables[2]));
                        if(!sentOnce) {
                            synchronized (this) {
                                notifyAll();
                            }
                            sentOnce = true;
                        }
                        try {
                            Thread.sleep(1000 / this.interval);
                        } catch (InterruptedException e1) {
                        }
                    }
                    aClient.getOutputStream().close();
                    aClient.getInputStream().close();
                    aClient.close();

                } catch (IOException e) {
                    System.out.println("-> Received an IOException: " + e.getMessage());
                }
            } catch (SocketTimeoutException e) {
                System.out.println("-> No client connected within the set timeout, trying again...");
            }
        }
        if(read != null) {
            try {
            	read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void stop() {
        stop = true;
    }		
}