
package com.sandra.game.utils;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class MyContactListener implements ContactListener{

	public static final String TAG = MyContactListener.class.getName();
	private boolean entityCollidedWithWorld = false;
	
	@Override
	public void beginContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		if(fa.getUserData() != null && fa.getUserData().equals("world")) {
			entityCollidedWithWorld = true;
			System.out.println(TAG + ": fa COLLIEDED!");
		}
		if(fb.getUserData() != null && fb.getUserData().equals("world")) {
			entityCollidedWithWorld = true;
			System.out.println(TAG + ": fb COLLIEDED!");
		}
	}
	
	public boolean getEntityCollision() {return entityCollidedWithWorld;}

	@Override
	public void endContact(Contact contact) {}
	
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {}
	
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {}
}