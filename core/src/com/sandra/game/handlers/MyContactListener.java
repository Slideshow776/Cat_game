
package com.sandra.game.handlers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.sandra.game.utils.Constants;

public class MyContactListener implements ContactListener{

	public static final String TAG = MyContactListener.class.getName();
	
	@Override
	public void beginContact(Contact contact) { // TODO: Account for all cases? (fa, fb || fb, fa)
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();

		// System.out.println(TAG + ": fa, " + fa.getUserData());
		// System.out.println(TAG + ": fb, " + fb.getUserData());
		
		if(fa.getUserData() != null && fa.getUserData().equals("world") && 
				fb.getUserData() != null && fb.getUserData().equals(Constants.CAT1_SPRITE)) {
			fb.getBody().setUserData("collision");
		}

		if(fa.getUserData() != null && fa.getUserData().equals(Constants.CAT1_SPRITE) && 
				fb.getUserData() != null && fb.getUserData().equals(Constants.HOLE_SPRITE)) {
			fa.getBody().setUserData("win_condition");
		}

		if(fa.getUserData() != null && fa.getUserData().equals(Constants.HOLE_SPRITE) && 
				fb.getUserData() != null && fb.getUserData().equals(Constants.CAT1_SPRITE)) {
			fb.getBody().setUserData("win_condition");
		}
	}

	@Override
	public void endContact(Contact contact) {}
	
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {}
	
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {}
}