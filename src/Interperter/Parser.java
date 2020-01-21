package Interperter;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import Commands.BindCommand;
import Commands.Command;
import Commands.ConditionCommand;
import Commands.ConnectCommand;
import Commands.DisconnectCommand;
import Commands.EqualCommand;
import Commands.PrintCommand;
import Commands.ReturnCommand;
import Commands.SleepCommand;
import Commands.VarCommand;
import Commands.WhileCommand;
import Commands.openDataServerCommand;

import java.util.ArrayList;
import java.util.HashMap;

public class Parser {
	private static Parser instance;
	public HashMap<String,Command> commandMap;
	private ConcurrentHashMap<String,Double> bindVarMap;
	Scope scope;
	Lexer lexer;
	private Parser() {
		this.commandMap = new HashMap<>();
		this.bindVarMap = new ConcurrentHashMap<>();
		this.scope = new Scope();
		this.lexer = Lexer.getInstance();
		bindVarMap.put("simX", 0.0);
		bindVarMap.put("simY", 0.0);
		bindVarMap.put("simZ", 0.0);
		commandMap.put("var", new VarCommand());
		commandMap.put("sleep", new SleepCommand());
		commandMap.put("print", new PrintCommand());
		commandMap.put("=", new EqualCommand());
		commandMap.put("openDataServer", new openDataServerCommand());
		commandMap.put("connect", new ConnectCommand());
		commandMap.put("=bind", new BindCommand());
		commandMap.put("while", new WhileCommand());
		commandMap.put("return", new ReturnCommand());
		commandMap.put("disconnect", new DisconnectCommand());
		commandMap.put("if", new ConditionCommand());
	}
	public Scope getScope() {
		return this.scope;
	}
    public ConcurrentHashMap<String, Double> getBindVarMap() {
        return bindVarMap;
    }
	public static Parser getInstance() {
		if(instance == null) {
			synchronized (Parser.class){
				if(instance == null) {
					instance = new Parser();
				}
			}
		}
		return instance;
	}
	public String[] split(String[] code) {
		return this.lexer.lexer(code);
	}
	public double parse(String[] code) {
		List<Object> args;
		((ReturnCommand)commandMap.get("return")).setReturnValue(0);
		int len = code.length;
		Command command;
		for(int i=0;i<len;) {
			args = new ArrayList<>();
			if((command = commandMap.get(code[i]))!=null) {
                if(command instanceof ReturnCommand){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
				try {
					i+= command.getArgs(code, i,args);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				try {
					command.doCommand(args);
				} catch (InterruptedException e) {
					System.out.println("in the docommand");
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(command instanceof ReturnCommand) {
					return ((ReturnCommand)commandMap.get("return")).getReturnValue();
				}
			}
			else {
				++i;
			}
		}
		return ((ReturnCommand)commandMap.get("return")).getReturnValue();
	}
}
