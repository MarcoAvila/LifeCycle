package org.marcoavila.lifecycle.test;

import junit.framework.Assert;

import org.junit.Test;
import org.marcoavila.lifecycle.LifeCycle;
import org.marcoavila.lifecycle.LifeCycleBuilder;
import org.marcoavila.lifecycle.LifeCycleBuilderImpl;

public class TestLifeCycle {

	private final SitTrat AAT = SitTrat.AAT;
	private final SitTrat DEF = SitTrat.DEF;
	private final SitTrat BLO = SitTrat.BLO;
	private final SitTrat IND = SitTrat.IND;
	private final SitTrat INC = SitTrat.INC;
	private final SitTrat SUS = SitTrat.SUS;
	private final SitTrat REA = SitTrat.REA;
	private final SitTrat ENC = SitTrat.ENC;
	
	
	
	
	
	
	
	
	
	


	@Test
	public void cicloDeVidaADM() {
		
		LifeCycleBuilder<SitTrat> b = new LifeCycleBuilderImpl<>();
		
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
				
		LifeCycle<SitTrat> cv = b.build();
		
		System.out.println(cv.toString());
		
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
		
		System.out.println("id: " + cv.getStateId() );
	}
	
	
	
	
	
	
	
	
	
	
	
	
	



	@Test
	public void cicloDeVidaADMRestore() {
		
		LifeCycleBuilder<SitTrat> b = new LifeCycleBuilderImpl<>();
		
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
				
		LifeCycle<SitTrat> cv = b.build();
		
		System.out.println(cv.toString());
		
		//Test
		Assert.assertTrue( cv.unborn() );
		
		cv.restoreState(4); //DEF
		Assert.assertTrue( cv.born() );
		
		//DEF
		Assert.assertTrue( cv.canDoTransitionTo(AAT) );
		Assert.assertTrue( cv.canDoTransitionTo(SUS) );
		cv.doTransitionTo(SUS);
		
		System.out.println("id: " + cv.getStateId() );
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*

	
	@Test
	public void testLittleBirth() {

		LifeCycleBuilder<SitTrat> b = new LifeCycleBuilderImpl<SitTrat>();
		
		b.birth(AAT).path(INC).toBirth()
		            .path(DEF)
		            
	     .birth(INC).path(ENC)
		  		    .path(DEF)
		            
		 .converge()
		 .path(AAT).toBirth()
		 .path(SUS)
		 .next(REA);	
		
		LifeCycle<SitTrat> lc = b.build();
		
		System.out.println(lc.toString());
	}
	
	
	
	
	
	
	@Test
	public void testBirth() {

		LifeCycleBuilder<SitTrat> b = new LifeCycleBuilderImpl<SitTrat>();
		
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
		
				
		LifeCycle<SitTrat> lc = b.build();
		
		System.out.println(lc.toString());
	}
	
	
	
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*

	public void cicloDeVidaJUD() {
		
		LifeCycleBuilder<SitTrat> b = new LifeCycleBuilderImpl<SitTrat>();
		
		//Build
		b.birth(DEF).next(BLO);
		
		
		
		
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
