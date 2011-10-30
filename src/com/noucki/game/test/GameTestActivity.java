package com.noucki.game.test;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.ui.activity.BaseGameActivity;



public class GameTestActivity extends  BaseGameActivity
{
	static Camera camera;
	static final int CAMERA_LARGEUR = 480;
    static final int CAMERA_HAUTEUR = 320;
	private SceneJeu scene;

	

	@Override
	public void onLoadComplete() 
	{
		
	}

	@Override
	public Engine onLoadEngine() 
	{
		
		camera = new Camera(0,0,CAMERA_LARGEUR, CAMERA_HAUTEUR);
		scene = new SceneJeu();
		EngineOptions options = new EngineOptions(true, ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(CAMERA_LARGEUR, CAMERA_HAUTEUR), camera);
		return new Engine(options);
	}

	@Override
	public void onLoadResources() 
	{
		scene.LoadRessources(getEngine(), this);
		
	}

	@Override
	public Scene onLoadScene() 
	{
		return scene;
	}
    
}