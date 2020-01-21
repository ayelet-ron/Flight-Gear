package Commands;

import java.util.List;
import Interperter.TypeArguments;
import Utilities.StringToArgumentParser;
import Utilities.Utilities;
import Variables.Var;

public class ConditionCommand implements Command {

	@Override
	public int getArgs(String[] code, int index, List<Object> args) throws Exception{
		return StringToArgumentParser.parse(code,index,3,args,TypeArguments.String,TypeArguments.Condition,TypeArguments.Block);
	}

	@Override
	public void doCommand(List<Object> parameters) throws InterruptedException {
	}
	public boolean CheckCondition(List<String> condition) throws Exception {
		double arg1 = 0,arg2=0;
		Var b;
		if(Utilities.isDouble(condition.get(0))) {
			arg1 = Double.parseDouble(condition.get(0));
		}
		else if((b=Utilities.getVar(condition.get(0)))!=null) {
			arg1 =b.calculate();
		}
		else {
			throw new Exception("Argument Error");
		}
		if(Utilities.isDouble(condition.get(2))) {
			arg2 = Double.parseDouble(condition.get(2));
		}
		else if((b=Utilities.getVar(condition.get(2)))!=null) {
			arg2 =b.calculate();
		}
		else {
			throw new Exception("Argument Error"); 
		}
		switch (condition.get(1)){
        case "<=":
        	return (arg1<=arg2);
        case ">=":
        	return (arg1>=arg2);
        case "<":
        	return (arg1<arg2);
        case ">":
        	return (arg1>arg2);
        case "==":
        	return (arg1==arg2);
        case "!=":
        	return (arg1!=arg2);
		}
        return false;
	}
}
