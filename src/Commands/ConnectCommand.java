/**
 * 
 */
package Commands;

import java.util.List;

import Interperter.SimulatorServer;
import Interperter.TypeArguments;
import Utilities.StringToArgumentParser;


public class ConnectCommand implements Command {

	@Override
    public int getArgs(String[] code, int index, List<Object> args) throws Exception{
        return StringToArgumentParser.parse(code, index, 3, args,TypeArguments.String, TypeArguments.String, TypeArguments.Integer);
    }

	@Override
	public void doCommand(List<Object> parameters) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SimulatorServer.getServer().open(parameters.get(1).toString(), (int)parameters.get(2));
	}

}
