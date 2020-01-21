package Interperter;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
	private static Lexer instance;

	private Lexer() {

	}

	public static Lexer getInstance() {
		if (instance == null) {
			synchronized (Lexer.class) {
				if (instance == null) {
					instance = new Lexer();
				}
			}
		}
		return instance;
	}

	public String[] lexer(String[] code) {
		List<String[]> allSplit = new ArrayList<>();
		for(String line : code) {
			allSplit.add(line.split("(?<=[-+*=/()\\s])|(?=[-+*=/()\\s])"));
		}
		List<String> withoutspaces = new ArrayList<>();
		List<String> finSplit = new ArrayList<>();
		for(String[] line : allSplit) {
			for (int i=0;i<line.length;i++) {
				switch (line[i]){
					case " ":
						break;
					case "\t":
						break;
					default:
						withoutspaces.add(line[i]);
				}
			}
		}
		for(int i=0;i<withoutspaces.size();i++) {
				switch (withoutspaces.get(i)){
				case " ":
					break;
				case "=":
					if(withoutspaces.get(i+1).equals("bind")) {
						finSplit.add("=bind");
						i++;
					}
					else {
						finSplit.add("=");
					}
					break;
				case "\"":
					StringBuilder bigString = new StringBuilder();
					while(!withoutspaces.get(i+1).equals("\"")) {
						bigString.append(withoutspaces.get(i+1));
						i++;
					}
					i=i+2;
					finSplit.add(bigString.toString());
					break;
				default:
					finSplit.add(withoutspaces.get(i));
					break;
				}
			}
		return finSplit.stream().toArray(String[]::new);
	}

}
