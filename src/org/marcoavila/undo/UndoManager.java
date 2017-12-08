package org.marcoavila.undo;

public interface UndoManager<STATE> {

	public void save(STATE state);
	public void cancelSaved();

	public boolean canUndo();
	public boolean canRedo();
	
	public STATE undo(STATE state);
	public STATE redo();
}