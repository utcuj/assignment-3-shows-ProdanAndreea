package net.codejava.hibernate.EventVisualization.bridge_show;

public class ShowBridge extends ShowAbstraction {

	public ShowBridge(Implementor implementor) {
		super(implementor);
	}
	
	public void add(Show show) {
		implementor.add(show);
	}
}
