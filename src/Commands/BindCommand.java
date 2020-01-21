package Commands;

import java.util.List;

import Interperter.Parser;
import Interperter.TypeArguments;
import Utilities.StringToArgumentParser;
import Variables.BindVar;

public class BindCommand implements Command{//get from =bind
	
	@Override
	public int getArgs(String[] code, int index,List<Object> args) throws Exception {
		//index-1 because the name of the var was already send to the var command 
		//(var name) =bind (bind name)
		return StringToArgumentParser.parse(code,index-1,3,args,TypeArguments.String,TypeArguments.String,TypeArguments.String)-1;
	}

	@Override
	public void doCommand(List<Object> parameters) {
		String varName= parameters.get(0).toString();
		BindVar v = new BindVar(varName,parameters.get(2).toString());
		Parser.getInstance().getScope().addVar(varName,v);	
	}

}
