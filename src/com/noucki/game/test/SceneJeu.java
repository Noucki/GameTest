package com.noucki.game.test;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.anddev.andengine.engine.camera.hud.controls.AnalogOnScreenControl.IAnalogOnScreenControlListener;
import org.anddev.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.util.MathUtils;

import android.content.Context;

public class SceneJeu extends Scene implements IAnalogOnScreenControlListener

{
	private BitmapTextureAtlas tankTexture;
	private BitmapTextureAtlas mOnScreenControlTexture;
	private TextureRegion mOnScreenControlBaseTextureRegion;
	private TextureRegion mOnScreenControlKnobTextureRegion;
	private TextureRegion regionTank;
	private Tank tank;
	private AnalogOnScreenControl analogOnScreenControl;
	
	public SceneJeu()
	{
		super();
		setBackground(new ColorBackground(0.52F, 0.75F, 0.03F));
	}
	
	public void LoadRessources(final Engine engine, Context context)
	{
		tankTexture = new BitmapTextureAtlas(128, 256);
		regionTank = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tankTexture, context, "gfx/tank.png", 0,0);
		mOnScreenControlTexture = new BitmapTextureAtlas(256,128,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mOnScreenControlBaseTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mOnScreenControlTexture, context, "gfx/onscreen_control_base.png",0,0);
		mOnScreenControlKnobTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mOnScreenControlTexture, context, "gfx/onscreen_control_knob.png",128,0);
		engine.getTextureManager().loadTexture(tankTexture);
		engine.getTextureManager().loadTexture(mOnScreenControlTexture);
		init();
	}
	
	private void init()
	{
		tank = new Tank(0,0,regionTank.getWidth(), regionTank.getHeight(), regionTank);
		tank.setScale(0.75F);
		analogOnScreenControl = new AnalogOnScreenControl(GameTestActivity.CAMERA_LARGEUR - mOnScreenControlBaseTextureRegion.getWidth(),
				GameTestActivity.CAMERA_HAUTEUR - mOnScreenControlBaseTextureRegion.getHeight(),
				GameTestActivity.camera,
				mOnScreenControlBaseTextureRegion,
				mOnScreenControlKnobTextureRegion,
				0.1F,
				200,
				this);
		attachChild(tank);
		
		analogOnScreenControl.getControlBase().setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		analogOnScreenControl.getControlBase().setAlpha(0.5f);
		analogOnScreenControl.getControlBase().setScaleCenter(0, 128);
		//Gérer l'échelle du controlleur
		/*analogOnScreenControl.getControlBase().setScale(0.5f);
		analogOnScreenControl.getControlKnob().setScale(0.5f);*/
		analogOnScreenControl.refreshControlKnobPosition();
		setChildScene(analogOnScreenControl);


	}

	@Override
	public void onControlChange(BaseOnScreenControl pBaseOnScreenControl, float pValueX, float pValueY) 
	{
		tank.setPosition(tank.getX() + (pValueX * 5), tank.getY() + (pValueY * 5));
		if(pValueX != 0 && pValueY != 0)
		{
			tank.setRotation(MathUtils.radToDeg((float) Math.atan2(pValueX, -pValueY)));
			
		}
		
	}

	@Override
	public void onControlClick(AnalogOnScreenControl arg0) 
	{
		
	}

}
