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
		
		/* if(fa.getUserData() != null && fa.getUserData().equals(Constants.B2D_WORLD) &&		// world vs cat1
				fb.getUserData() != null && fb.getUserData().equals(Constants.CAT1_IDLE_SPRITE_1)) {
			fb.getBody().setUserData("collision");
		} */

		if (fa.getUserData() != null && fb.getUserData() != null) {

			String temp = (String)fa.getUserData();

			if(temp.split("-")[0].equals(Constants.CAT1_IDLE_SPRITE_1) &&		// cat1 vs hole
					fb.getUserData().equals(Constants.PORTAL_SPRITE_1)) {
				fa.getBody().setUserData("win_condition");
			}

			if(temp.split("-")[0].equals(Constants.CAT1_IDLE_SPRITE_1) &&		// cat1 vs coin
					fb.getUserData().equals(Constants.COIN_SPRITE_1)) {
				fb.getBody().setUserData("remove_me");
			}
			
			if(fa.getUserData().equals(Constants.B2D_WORLD) &&					// world vs yarn ball
					fb.getUserData().equals(Constants.YARN_BALL_SPRITE_1)) {
				fb.getBody().setUserData("collision");
			}

			if(fa.getUserData().equals(Constants.YARN_BALL_SPRITE_1) &&			// world vs yarn ball
					fb.getUserData().equals(Constants.B2D_WORLD)) {
				fa.getBody().setUserData("collision");
			}

			if(temp.split("-")[0].equals(Constants.CAT1_IDLE_SPRITE_1) &&		// cat vs yarn ball
					fb.getUserData().equals(Constants.YARN_BALL_SPRITE_1)) {
				fb.getBody().setUserData("collision");
			}

			if(temp.split("-")[0].equals(Constants.CAT1_IDLE_SPRITE_1) &&		// cat vs zone
					fb.getUserData().equals(Constants.B2D_LAND_ZONE)) {
				fa.getBody().setUserData("zone_count_up");
			}

			if(temp.split("-")[0].equals(Constants.CAT1_IDLE_SPRITE_1) &&		// cat vs zone
					fb.getUserData().equals(Constants.B2D_LAVA_ZONE)) {
				fa.getBody().setUserData("annihilate");
			}

			if(temp.split("-")[0].equals(Constants.CAT1_IDLE_SPRITE_1) &&		// cat vs thwomper
					fb.getUserData().equals(Constants.THWOMPER_IDLE_SPRITE1)) {
				fb.getBody().setUserData("cat_collision-increment" + "-" + temp.split("-")[1]);
			}

			if(temp.split("-")[0].equals(Constants.CAT1_IDLE_SPRITE_1) &&		// cat1 vs saw blade
					fb.getUserData().equals(Constants.SAW_BLADE1)) {
				fa.getBody().setUserData("cut");
			}
		}
	}

	@Override
	public void endContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();

		if (fa.getUserData() != null && fb.getUserData() != null) {

			String temp = (String)fa.getUserData();

			if(temp.split("-")[0].equals(Constants.CAT1_IDLE_SPRITE_1) &&		// cat vs thwomper
					fb.getUserData().equals(Constants.THWOMPER_IDLE_SPRITE1)) {
				fb.getBody().setUserData("cat_collision-decrement" + "-" + temp.split("-")[1]);
			}

			if(temp.split("-")[0].equals(Constants.CAT1_IDLE_SPRITE_1) &&		// cat vs zone
					fb.getUserData().equals(Constants.B2D_LAND_ZONE)) {
				fa.getBody().setUserData("zone_count_down");
			}
		}
	}
	
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {}
	
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {}
}