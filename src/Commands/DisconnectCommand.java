package Commands;

import java.util.List;

import Interperter.Parser;
import Interperter.SimulatorServer;

public class DisconnectCommand implements Command {

	@Override
	public int getArgs(String[] code, int index, List<Object> args) throws Exception {
		return 1;
	}

	@Override
	public void doCommand(List<Object> parameters) throws InterruptedException, Exception {
		SimulatorServer.getServer().sendString("bye");
		SimulatorServer.getServer().stop(); //my client
		((openDataServerCommand)Parser.getInstance().commandMap.get("openDataServer")).stop(); //my serevr 
	}
}
