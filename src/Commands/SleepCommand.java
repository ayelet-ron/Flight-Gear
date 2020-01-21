package Commands;

import java.util.List;

import Interperter.TypeArguments;
import Utilities.StringToArgumentParser;

public class SleepCommand implements Command {

	@Override
	public int getArgs(String[] code, int index, List<Object> args) throws Exception{
		return StringToArgumentParser.parse(code,index,2,args,TypeArguments.String,TypeArguments.Integer);
	}

	@Override
	public void doCommand(List<Object> parameters) throws InterruptedException {
		Thread.sleep((long) parameters.get(1));	
	}
}
