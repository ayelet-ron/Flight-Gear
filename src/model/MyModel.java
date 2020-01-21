package model;

import Interperter.SimulatorServer;

public class MyModel implements Model {

	@Override
	public void setThrottle(double v) {
		System.out.println("throttle "+v);
		SimulatorServer.getServer().setVariable("/controls/engines/engine/throttle", v);
	}

	@Override
	public void setRudder(double v) {
		System.out.println("rudder "+v);
		SimulatorServer.getServer().setVariable("/controls/flight/rudder", v);
	}

	@Override
	public void setAileron(double v) {
		System.out.println("aileron "+v);
		SimulatorServer.getServer().setVariable("/controls/flight/aileron", v);
	}

	@Override
	public void setElevator(double v) {
		System.out.println("elevator "+v);
		SimulatorServer.getServer().setVariable("/controls/flight/elevator", v);
	}

	@Override
	public void connect() {
		SimulatorServer.getServer().open("127.0.0.1", 5402);
		
	}

}
