package org.marcoavila.ddd.test.lifecycle;

import static org.marcoavila.ddd.test.lifecycle.TreatmentState.AAT;
import static org.marcoavila.ddd.test.lifecycle.TreatmentState.BLO;
import static org.marcoavila.ddd.test.lifecycle.TreatmentState.DEF;
import static org.marcoavila.ddd.test.lifecycle.TreatmentState.ENC;
import static org.marcoavila.ddd.test.lifecycle.TreatmentState.INC;
import static org.marcoavila.ddd.test.lifecycle.TreatmentState.IND;
import static org.marcoavila.ddd.test.lifecycle.TreatmentState.REA;
import static org.marcoavila.ddd.test.lifecycle.TreatmentState.SUS;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.marcoavila.ddd.util.Log;
import org.marcoavila.ddd.util.lifecycle.LifeCycle;
import org.marcoavila.ddd.util.lifecycle.LifeCycleBuilder;
import org.marcoavila.ddd.util.lifecycle.LifeCycleBuilderImpl;

/**
 * 
 * @author Marco Avila
 */
public class TestLifeCycle {
	
	
	
	
	
	
	

	

    @Before
	public void setUp() {

    	Log.print("\nTesting LifeCycle...");
    	Log.print("....................\n");
	}
	
	
    
    
    
    
	
	
	
	
	
	
	
	


	@Test
	public void lifeCycleADM() {

    	Log.print("lifeCycleADM():\n");
    	
		LifeCycleBuilder<TreatmentState> b = new LifeCycleBuilderImpl<>();
		
		b.addWildCard(ENC);
		
		//Build
		b.birth(AAT).path(IND).toBirth()
		            .path(INC).toBirth()
		            .path(DEF)
		            
		 .birth(INC).path(IND).toBirth()
		            .path(DEF)
		            
		 .birth(IND).path(INC).toBirth()
		            .path(DEF)
		            
		 .converge()
		 .path(AAT).toBirth()
		 .path(SUS)
		 .next(REA)
		 .path(INC).toBirth()
		 .path(IND).toBirth()
		 .path(DEF).back();
				
		LifeCycle<TreatmentState> cv = b.build();
		
		Log.print( cv.toString() );
		
		//Test
		Assert.assertTrue( cv.canBirth(AAT) );
		Assert.assertTrue( cv.canBirth(INC) );
		Assert.assertTrue( cv.canBirth(IND) );

		Assert.assertFalse( cv.canBirth(SUS) );
		Assert.assertFalse( cv.canBirth(REA) );
		Assert.assertFalse( cv.canBirth(DEF) );
		Assert.assertFalse( cv.canBirth(BLO) );
		Assert.assertFalse( cv.canBirth(ENC) );
		
		//AAT
		cv.birth(AAT);
		Assert.assertTrue( cv.canDoTransitionTo(IND) );
		Assert.assertTrue( cv.canDoTransitionTo(INC) );
		Assert.assertTrue( cv.canDoTransitionTo(DEF) );

		Assert.assertFalse( cv.canDoTransitionTo(SUS) );
		Assert.assertFalse( cv.canDoTransitionTo(REA) );

		//DEF
		cv.doTransitionTo(DEF);

		Assert.assertTrue( cv.canDoTransitionTo(SUS) );
		cv.doTransitionTo(SUS);

		Assert.assertTrue( cv.canDoTransitionTo(REA) );
		cv.doTransitionTo(REA);

		Assert.assertTrue( cv.canDoTransitionTo(IND) );
		cv.doTransitionTo(IND);

		Assert.assertTrue( cv.canDoTransitionTo(ENC) );
		cv.doTransitionTo(ENC);
		
		Log.print( "id: " + cv.getStateId() );
	}
	
	
	
	
	
	
	
	
	
	
	
	
	



	@Test
	public void lifeCycleADMRestore() {

    	Log.print("lifeCycleADMRestore():\n");
    	
		LifeCycleBuilder<TreatmentState> b = new LifeCycleBuilderImpl<>();
		
		b.addWildCard(ENC);
		
		//Build
		b.birth(AAT).path(IND).toBirth()
		            .path(INC).toBirth()
		            .path(DEF)
		            
		 .birth(INC).path(IND).toBirth()
		            .path(DEF)
		            
		 .birth(IND).path(INC).toBirth()
		            .path(DEF)
		            
		 .converge()
		 .path(AAT).toBirth()
		 .path(SUS)
		 .next(REA)
		 .path(INC).toBirth()
		 .path(IND).toBirth()
		 .path(DEF).back();
				
		LifeCycle<TreatmentState> cv = b.build();
		
		Log.print( cv.toString());
		
		//Test
		Assert.assertTrue( cv.unborn() );
		
		cv.restoreState(4); //DEF
		Assert.assertTrue( cv.born() );
		
		//DEF
		Assert.assertTrue( cv.canDoTransitionTo(AAT) );
		Assert.assertTrue( cv.canDoTransitionTo(SUS) );
		cv.doTransitionTo(SUS);
		
		Log.print( "id: " + cv.getStateId() );
	}
	
	
	
	
	
	
	
	
	
	

	
	/*

	//TODO

	@Test
	public void lifeCycleJUD() {
		
    	Log.print("lifeCycleJUD():\n");
    	
		LifeCycleBuilder<SitTrat> b = new LifeCycleBuilderImpl<SitTrat>();
		
		//Build
		b.birth(DEF).next(BLO);
		
		
		
		
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void testBirth() {

    	Log.print("testBirth():\n");
    	
		LifeCycleBuilder<TreatmentState> b = new LifeCycleBuilderImpl<TreatmentState>();
		
		b.birth(AAT).path(INC).toBirth()
		            .path(DEF)
		            
	     .birth(INC).path(ENC)
		  		    .path(DEF)
		            
		 .converge()
		 .path(AAT).toBirth()
		 .path(SUS)
		 .next(REA);	
		
		LifeCycle<TreatmentState> lc = b.build();
		
		Log.print( lc.toString());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void testBirth2() {

    	Log.print("testBirth2():\n");
    	
		LifeCycleBuilder<TreatmentState> b = new LifeCycleBuilderImpl<TreatmentState>();
		
		b.birth(AAT).path(IND).toBirth()
					.path(INC).toBirth()
		            .path(DEF)
		            
		 .birth(IND).path(INC).toBirth()
                    .path(DEF)
                    
		 .birth(INC).path(IND).toBirth()
                    .path(DEF)
		
		 .converge()
		 .path(AAT).toBirth()
		 .path(SUS)
		 .next(REA)
		 .path(IND).toBirth()
		 .path(DEF).back();	
		
				
		LifeCycle<TreatmentState> lc = b.build();
		
		Log.print( lc.toString());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
