package org.marcoavila.lifecycle;

public interface LifeCycleBuilder<STATE> {

	public void addWildCard(STATE state);

	public LifeCycleBuilder<STATE> birth(STATE state);
	public LifeCycleBuilder<STATE> next(STATE state);
	
	public LifeCycleBuilder<STATE> path(STATE state);	
	public LifeCycleBuilder<STATE> converge();
	
	public LifeCycleBuilder<STATE> toBirth();
	public LifeCycleBuilder<STATE> back();
	
	public LifeCycle<STATE> build(); 
}
