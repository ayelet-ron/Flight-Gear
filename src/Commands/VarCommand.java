package Commands;

import java.util.List;

import Interperter.Parser;
import Interperter.TypeArguments;
import Utilities.StringToArgumentParser;
import Variables.RegularVar;

public class VarCommand implements Command {// get from var (var name)
	@Override
	public int getArgs(String[] code, int index, List<Object> args) throws Exception{
		return StringToArgumentParser.parse(code,index,2,args,TypeArguments.String,TypeArguments.String);
	}

	@Override
	public void doCommand(List<Object> parameters) throws InterruptedException {
		RegularVar b = new RegularVar(parameters.get(1).toString(),0.0);
		Parser.getInstance().getScope().addVar(parameters.get(1).toString(),b);
		
	}

}
