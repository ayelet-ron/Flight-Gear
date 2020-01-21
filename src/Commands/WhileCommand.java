package Commands;

import java.util.ArrayList;
import java.util.List;
import Interperter.Parser;
import Interperter.Scope;
import Interperter.TypeArguments;
import Utilities.StringToArgumentParser;


public class WhileCommand implements Command {
	@Override
	public int getArgs(String[] code, int index, List<Object> args) throws Exception{
		return StringToArgumentParser.parse(code,index,3,args,TypeArguments.String,TypeArguments.Condition,TypeArguments.Block);
	}

	@Override
	public void doCommand(List<Object> parameters) throws Exception {
		ConditionCommand conditionCommand = (ConditionCommand)Parser.getInstance().commandMap.get("if");
		@SuppressWarnings("unchecked")
		ArrayList<String> condition = (ArrayList<String>)(ArrayList<?>)(parameters.get(1));
		Scope s =Parser.getInstance().getScope();
		Parser p = Parser.getInstance();
		s.addInnerScope();
		String[] codeinloop = (String[]) parameters.get(2);
		while(conditionCommand.CheckCondition(condition)) {
			p.parse(codeinloop);
		}
		s.destroyScope();
	}
}
