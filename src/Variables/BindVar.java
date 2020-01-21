package Variables;

import Interperter.Parser;
import Interperter.SimulatorServer;

public class BindVar extends Var {
	String flightGrearPath;
	
	public BindVar(String name,String flightGrearPath) {
		super(name);
		this.flightGrearPath = flightGrearPath;
	}

	@Override
	public double calculate() {
		return Parser.getInstance().getBindVarMap().get(this.flightGrearPath);
	}

	@Override
	public void set(double newVal) {
		SimulatorServer.getServer().setVariable(flightGrearPath, newVal);
		Parser.getInstance().getBindVarMap().put(flightGrearPath, newVal);
	}

	public String getFlightGrearPath() {
		return flightGrearPath;
	}

	public void setFlightGrearPath(String flightGrearPath) {
		this.flightGrearPath = flightGrearPath;
	}

}
