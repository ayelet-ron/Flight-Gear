package test;


import Interperter.Parser;

public class MyInterpreter {
	public static Double interpret(String[] code) {
		String[] s = Parser.getInstance().split(code);
		return Parser.getInstance().parse(s);
	}
	
}
