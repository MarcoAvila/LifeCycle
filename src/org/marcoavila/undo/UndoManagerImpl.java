package org.marcoavila.undo;

import java.util.ArrayList;
import java.util.List;

public class UndoManagerImpl<STATE> implements UndoManager<STATE> {

private final int levelsCount;
private List<STATE> states;

private int index;
private int first, last;

private boolean cleared;
private boolean canUndo, canRedo;







    public UndoManagerImpl(int levelsCount)
    {
    this.levelsCount = levelsCount;
    states = new ArrayList<STATE>(levelsCount);

    for (int a=0; a<levelsCount; a++)
    	states.add(null);
    
    index = 0;
    first = 0;
    last = 0;
    
    cleared = true;
    canUndo = false;
    canRedo = false;
    }





//------------------------------------------------------------------------------




    
    
    

	
    
	@Override
	public void save(STATE state)
	{
	states.set(index, state);	               
	
	last = index;
	
	incIndex();
	
	if (last == first && !cleared)
	    this.incFirst();
	
	cleared = false;
	
	canUndo = true;
	canRedo = false;                          
	}
	



	
	
	
	
	





	
	
	@Override
	public STATE undo(STATE state)
	{
	int i = index - 1;            
	
	if (i < 0)
	    i = levelsCount - 1;
	
	if (i == last)
	    {
		states.set(index, state);	
	
	    this.incLast();
	    }
	                     
	this.decIndex();  
		
	if (index == first)
	    cleared = true;
	
	canUndo = (index != first);   
	canRedo = true;
	
	if (first == last)
	    this.incFirst();     
	
	return states.get(index);
	}
	
	
	


	
	
	
	
	
	
	@Override
	public STATE redo()
	{
	this.incIndex();
		
	canUndo = true;
	canRedo = (index != last); 
	
	return states.get(index);
	}
	
	

	
	
	
	
	
	
	
	
	




	
	@Override
	public void cancelSaved()
	{                                
	if (first != last)
	    this.decLast();
	else
	    cleared = true;
	
	this.decIndex();
	
	canUndo = (index != first);
	canRedo = (index != last); 
	}
	


	
	
	
	


//------------------------------------------------------------------------------




	
	


    @Override
    public boolean canUndo()
    {
    return canUndo;
    }



    
    
    
    
    @Override
    public boolean canRedo()
    {
    return canRedo;
    }










//------------------------------------------------------------------------------







    private void incIndex()
    {
    if (++index == levelsCount)
        index = 0;
    }

    private void decIndex()
    {
    if (--index < 0)
        index = levelsCount - 1;
    }




    private void incFirst()
    {
    if (++first == levelsCount)
        first = 0;
    }



    private void incLast()
    {
    if (++last == levelsCount)
        last = 0;
    }

    private void decLast()
    {
    if (--last < 0)
        last = levelsCount - 1;
    }

    
    
    

    
    



}
