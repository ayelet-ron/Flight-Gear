package Commands;

import java.util.List;

import Interperter.TypeArguments;
import Utilities.StringToArgumentParser;

public class PrintCommand implements Command {
	@Override
	public int getArgs(String[] code, int index, List<Object> args) throws Exception{
		return StringToArgumentParser.parse(code,index,2,args,TypeArguments.String,TypeArguments.Double);
	}

	@Override
	public void doCommand(List<Object> parameters) throws InterruptedException {
		System.out.println(parameters.get(1));
		
	}

}
