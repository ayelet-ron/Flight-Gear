package Commands;

import java.util.List;

import Interperter.TypeArguments;
import Utilities.StringToArgumentParser;


public class ReturnCommand implements Command {

    double returnValue = 0;
    @Override
    public int getArgs(String[] code, int index, List<Object> args) throws Exception{
        return StringToArgumentParser.parse(code, index, 2, args, TypeArguments.String, TypeArguments.Double);
    }

    @Override
    public void doCommand(List<Object> args) {
        returnValue = (double)args.get(1);
    }

    public double getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(double returnValue) {
        this.returnValue = returnValue;
    }


}
