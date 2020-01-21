package Utilities;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import Expression.ExpressionConvertor;
import Interperter.TypeArguments;

public class StringToArgumentParser {
	private static StringToArgumentParser instance;
	private StringToArgumentParser() {
		
	}
	public static StringToArgumentParser getInstance() {
		if(instance == null) {
			synchronized(StringToArgumentParser.class) {
				if(instance == null) {
					instance = new StringToArgumentParser();
				}
			}
		}
		return instance;
	}
    public static int parse(String[] args, int index, int argumentsNeeded, List<Object> emptyList, TypeArguments... argsType) throws Exception {
        int j = index;
        boolean flag,flag2=false;
        List<String> expretionList = new ArrayList<String>();
        for (int i = 0; i < argumentsNeeded && j < args.length; i++) {
            switch (argsType[i]){
                case Character:
                case String:
                	emptyList.add(args[j]);
                	j++;
                	break; 
                case Integer:
                	flag = false;
                	expretionList.clear();
                	for(;j<args.length;j++) {
                		if(Arrays.asList("/","*","(",")","+","-").contains(args[j])) {
                			flag2 = true;
                		}
                		else {
                			flag2=false;
                		}
                		if((!flag2)&& flag) {
                			break;
                		}
                		else{
                			flag=!flag2;
                		}
                		expretionList.add(args[j]);
                	}
                	emptyList.add((int)ExpressionConvertor.calculatePostfix(ExpressionConvertor.infixToPostfix(expretionList)));
                	break; 
                case Block:
                	if(!args[j].equals("{")) {
                		throw new Exception("you are missing { ");//?
                	}
                	j++;
                	flag=false;
                	List<String> codeInLoop = new ArrayList<>();
                	for(; j<args.length && !args[j].equals("}");j++) {
                		codeInLoop.add(args[j]);
                	}
                	/*if(!flag) {
                		throw new Exception("");
                	}*/
                	emptyList.add(codeInLoop.stream().toArray(String[]::new));
                	break; 
                case Double:
                	flag = false;
                	expretionList.clear();
                	for(;j<args.length;j++) {
                		if(Arrays.asList("/","*","(",")","+","-").contains(args[j])) {
                			flag2 = true;
                		}
                		else {
                			flag2=false;
                		}
                		if((!flag2)&& flag) { 
                			break;
                		}
                		else{
                			flag=!flag2;
                		}
                		expretionList.add(args[j]);
                	}
                	emptyList.add(ExpressionConvertor.calculatePostfix(ExpressionConvertor.infixToPostfix(expretionList)));
                	break; 
                case Float:
                	flag = false;
                	expretionList.clear();
                	for(;j<args.length;j++) {
                		if(Arrays.asList("/","*","(",")","+","-").contains(args[j])) {
                			flag2 = true;
                		}
                		else {
                			flag2=false;
                		}
                		if(!flag2 && flag) {
                			break;
                		}
                		else{
                			flag=!flag2;
                		}
                		expretionList.add(args[j]);
                	}
                	emptyList.add((float)ExpressionConvertor.calculatePostfix(ExpressionConvertor.infixToPostfix(expretionList)));
                	break;
                case Condition:
                	expretionList.clear();
                	for(;j< args.length && !args[j].equals("{");j++) {
                		expretionList.add(args[j]);
                	}
                	emptyList.add(expretionList);
                	break; 
            }
        }
        return j-index;
    }
}
