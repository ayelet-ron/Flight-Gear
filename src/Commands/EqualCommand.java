package Commands;

import java.util.List;

import Interperter.Parser;
import Interperter.TypeArguments;
import Utilities.StringToArgumentParser;

public class EqualCommand implements Command {
	@Override
	public int getArgs(String[] code, int index, List<Object> args) throws Exception{
		//every time that the command is in the middle go backward with the code 
		return StringToArgumentParser.parse(code,index-1,3,args,TypeArguments.String,TypeArguments.String,TypeArguments.Double)-1;//can be strig string
	}

	@Override
	public void doCommand(List<Object> parameters) throws InterruptedException {
		Parser.getInstance().getScope().getVar(parameters.get(0).toString()).set((Double)parameters.get(2));
	}
}
