package org.shokai.ioio.ledblink;

import ioio.lib.api.*;
import ioio.lib.api.exception.*;
import ioio.lib.util.*;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainActivity extends AbstractIOIOActivity implements View.OnClickListener {
	ToggleButton[] btn_led = new ToggleButton[3];
	ToggleButton btn_all;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        log("start");
        this.btn_led[0] = (ToggleButton)this.findViewById(R.id.toggleButtonLED0);
        this.btn_led[1] = (ToggleButton)this.findViewById(R.id.toggleButtonLED1);
        this.btn_led[2] = (ToggleButton)this.findViewById(R.id.toggleButtonLED2);
        for(int i = 0; i < btn_led.length; i++){
        	btn_led[i].setOnClickListener(this);
        }
        this.btn_all = (ToggleButton)this.findViewById(R.id.toggleButtonLEDAll);
        btn_all.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.toggleButtonLED0:
			if(this.btn_led[0].isChecked()){
				log("LED0 ON");
			}
			else{
				log("LED0 OFF");
			}
			break;
		case R.id.toggleButtonLED1:
			if(this.btn_led[1].isChecked()){
				log("LED1 ON");
			}
			else{
				log("LED1 OFF");
			}
			break;
		case R.id.toggleButtonLED2:
			if(this.btn_led[2].isChecked()){
				log("LED2 ON");
			}
			else{
				log("LED2 OFF");
			}
			break;
		case R.id.toggleButtonLEDAll:
			break;
		}
	}
	
	public void log(String msg){
		Log.v("IOIO_LED_Blink", msg);
	}
	
	class IOIOThread extends AbstractIOIOActivity.IOIOThread{
		
		protected void loop() throws ioio.lib.api.exception.ConnectionLostException{
			try {
                sleep(10);
        	}
			catch (InterruptedException e) {
        	}
		}
	}
	
	@Override
	protected ioio.lib.util.AbstractIOIOActivity.IOIOThread createIOIOThread() {
		return new IOIOThread();
	}	
}

