package Utilities;

import Interperter.Parser;
import Variables.Var;

public class Utilities {
	public static boolean isDouble(String str) {
		try {
			@SuppressWarnings("unused")
			double d = Double.parseDouble(str);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	public static Var getVar(String name) {
		return Parser.getInstance().getScope().getVar(name);
	}
}
