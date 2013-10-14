package com.whiskers.morselight;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executor;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Home extends Activity {
	public static final int DOT_DURATION = 200;
	public static final int DASH_DURATION = 400;
	public static final int PAUSE_DURATION = 600;
	
	private Camera whiskersCamera;
	private Map<Character, MorseCharacter> morseMap = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		getMorseMap();
		
		Button b = (Button) findViewById(R.id.enterMorseCode);
		b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText e = (EditText)findViewById(R.id.stringToConvert);
				getMorseCodeForPhrase(e.getText().toString());
			}
		});
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		if(whiskersCamera == null){
			try{
				whiskersCamera = Camera.open();
			}catch(RuntimeException e){
				Log.e("MergeLight", "Error opening the camera");
			}
		}		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		if(getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
			Log.i("MergeLight", "Turning on flash");
			Parameters p = whiskersCamera.getParameters();
			p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
			whiskersCamera.setParameters(p);
			whiskersCamera.startPreview();
			
//			getMorseCodeForPhrase("sos");
		}
	}
	
	
	@Override
	protected void onPause() {
		super.onPause();
        Log.i("MergeLight", "Stopping Preview");
		whiskersCamera.stopPreview();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.i("MergeLight", "Releasing Camera");
		whiskersCamera.release();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}
	
	private void flash(int duration){
		//reset to off
		Parameters p = whiskersCamera.getParameters();
		if(p.getFlashMode().equals(Camera.Parameters.FLASH_MODE_TORCH)){
			Log.i("MergeLight", "Turning off flash");
			p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
		}
		
		//turn it on
		p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
		whiskersCamera.setParameters(p);

		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//now turn it off
		p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
		whiskersCamera.setParameters(p);
	}
	
	private void doDot(){
		Executor e = new Executor() {
			@Override
			public void execute(Runnable command) {
				try {
					Thread.sleep(50);
					flash(DOT_DURATION);
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		};
		e.execute(null);
	}
	
	private void doDash(){
		Executor e = new Executor() {
			@Override
			public void execute(Runnable command) {
				try {
					Thread.sleep(50);
					flash(DASH_DURATION);
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		};
		e.execute(null);
	}
	
	private void doPause(){
		try {
			Thread.sleep(PAUSE_DURATION);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void getMorseCodeForPhrase(String phrase){
		phrase = phrase.toLowerCase(Locale.US).trim();
		for(char c : phrase.toCharArray()){
			getMorseCodeForCharacter(c);
		}
	}
	
	private void getMorseCodeForCharacter(char input){
		if(morseMap.containsKey(input)){
			String morseCode = morseMap.get(input).getMorseChars();
			
			for(char c : morseCode.toCharArray()){
				if(c == '.'){
					doDot();
				} else if (c == '-'){
					doDash();
				} else {
					Log.wtf("MorseLight", "We got something other than dots and dashes in morsecharacter");
				}
			}
		}else if (input == ' '){ 
			doPause();
		}	
	}
	
	private Map<Character, MorseCharacter> getMorseMap(){
		if(morseMap == null){
			morseMap = new HashMap<Character, MorseCharacter>();
			
			morseMap.put('a', MorseCharacter.A);
			morseMap.put('b', MorseCharacter.B);
			morseMap.put('c', MorseCharacter.C);
			morseMap.put('d', MorseCharacter.D);
			morseMap.put('e', MorseCharacter.E);
			morseMap.put('f', MorseCharacter.F);
			morseMap.put('g', MorseCharacter.G);
			morseMap.put('h', MorseCharacter.H);
			morseMap.put('i', MorseCharacter.I);
			morseMap.put('j', MorseCharacter.J);
			morseMap.put('k', MorseCharacter.K);
			morseMap.put('l', MorseCharacter.L);
			morseMap.put('m', MorseCharacter.M);
			morseMap.put('n', MorseCharacter.N);
			morseMap.put('o', MorseCharacter.O);
			morseMap.put('p', MorseCharacter.P);
			morseMap.put('q', MorseCharacter.Q);
			morseMap.put('r', MorseCharacter.R);
			morseMap.put('s', MorseCharacter.S);
			morseMap.put('t', MorseCharacter.T);
			morseMap.put('u', MorseCharacter.U);
			morseMap.put('v', MorseCharacter.V);
			morseMap.put('w', MorseCharacter.W);
			morseMap.put('x', MorseCharacter.X);
			morseMap.put('y', MorseCharacter.Y);
			morseMap.put('z', MorseCharacter.Z);
			morseMap.put('1', MorseCharacter.ONE);
			morseMap.put('2', MorseCharacter.TWO);
			morseMap.put('3', MorseCharacter.THREE);
			morseMap.put('4', MorseCharacter.FOUR);
			morseMap.put('5', MorseCharacter.FIVE);
			morseMap.put('6', MorseCharacter.SIX);
			morseMap.put('7', MorseCharacter.SEVEN);
			morseMap.put('8', MorseCharacter.EIGHT);
			morseMap.put('9', MorseCharacter.NINE);
			morseMap.put('0', MorseCharacter.ZERO);
		}
		
		return morseMap;
	}
}
