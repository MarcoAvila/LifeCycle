package org.marcoavila.lifecycle;

public interface LifeCycle<STATE> {

	public boolean born();
	public boolean unborn();
	
	public boolean canBirth(STATE state);
	public void birth(STATE birthState);

	public boolean canDoTransitionTo(STATE state);
	public void doTransitionTo(STATE state);
	
	public State<STATE> getState();
	public int getStateId();	
	public void restoreState(int stateId);
}