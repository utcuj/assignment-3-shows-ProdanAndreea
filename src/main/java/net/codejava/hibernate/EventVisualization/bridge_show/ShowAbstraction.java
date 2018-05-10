package net.codejava.hibernate.EventVisualization.bridge_show;

import java.util.List;

public abstract class ShowAbstraction {
	Implementor implementor;
	
	public ShowAbstraction() {}
	
	public ShowAbstraction(Implementor implementor) {
		this.implementor = implementor;
	}
	
	abstract void add(Show show);
    


}
