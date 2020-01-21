package Interperter;

import java.util.concurrent.ConcurrentHashMap;

import Variables.Var;

public class Scope {
	private Scope innerScope;
	private ConcurrentHashMap<String,Var> symbolTable;
	public Scope() {
		this.symbolTable = new ConcurrentHashMap<>();
		this.innerScope = null;
	}
	public void addVar(String name,Var var) {
		if(innerScope == null) {
			symbolTable.put(name, var); 
		}
		else {
			innerScope.addVar(name, var);
		}
	}
	public Scope getLastScope(Scope c) {
		if(c.innerScope ==null) {
			return this;
		}
		return getLastScope(c.innerScope);
	}
	public void addInnerScope() {
		if(this.innerScope==null) {
			this.innerScope= new Scope();
		}
		else {
			this.innerScope.addInnerScope();
		}
	}
	public Boolean destroyScope() {
		if(this.innerScope==null) {
			return false;
		}
		else if(this.innerScope.innerScope == null) {
			this.innerScope.symbolTable.clear();
			this.innerScope =null;
			return true;
		}
		return this.innerScope.destroyScope();
	}
	public Var getVar(String name) {
		Var v = null;
		if(this.innerScope!=null) {
			v= this.innerScope.getVar(name);
		}
		if(v==null) {
			return this.symbolTable.get(name);
		}
		return v;
	}
}
