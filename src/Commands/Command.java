package Commands;

import java.util.List;

public interface Command {
	public int getArgs(String[] code,int index,List<Object> args) throws Exception;
	public void doCommand(List<Object> parameters) throws InterruptedException, Exception;
}
