package org.shokai.ioio.ledblink;

import ioio.lib.api.*;
import ioio.lib.api.exception.*;
import ioio.lib.util.*;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class MainActivity extends AbstractIOIOActivity implements View.OnClickListener {
	ToggleButton btn_led0, btn_led1, btn_led2, btn_all;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        log("start");
        
        btn_led0 = (ToggleButton)this.findViewById(R.id.toggleButtonLED0);
        btn_led1 = (ToggleButton)this.findViewById(R.id.toggleButtonLED1);
        btn_led2 = (ToggleButton)this.findViewById(R.id.toggleButtonLED2);
	    btn_led0.setOnClickListener(this);
	    btn_led1.setOnClickListener(this);
	    btn_led2.setOnClickListener(this);
	    
        btn_all = (ToggleButton)this.findViewById(R.id.toggleButtonLEDAll);
        btn_all.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.toggleButtonLED0:
			if(this.btn_led0.isChecked()){
				log("LED0 ON");
			}
			else{
				log("LED0 OFF");
			}
			break;
		case R.id.toggleButtonLED1:
			if(this.btn_led1.isChecked()){
				log("LED1 ON");
			}
			else{
				log("LED1 OFF");
			}
			break;
		case R.id.toggleButtonLED2:
			if(this.btn_led2.isChecked()){
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
		
		private DigitalOutput[] led = new DigitalOutput[3];
		
		protected void setup() throws ConnectionLostException{
			led[0] = this.ioio_.openDigitalOutput(0, true);
			led[1] = this.ioio_.openDigitalOutput(1, true);
			led[2] = this.ioio_.openDigitalOutput(2, true);
		}
		
		protected void loop() throws ConnectionLostException{
			led[0].write(!btn_led0.isChecked());
			led[1].write(btn_led1.isChecked());
			led[2].write(btn_led2.isChecked());
			try {
                sleep(10);
        	}
			catch (InterruptedException e) {
        	}
		}
		
		public void led(int pin, boolean on_off) throws ConnectionLostException{
			led[pin].write(on_off);
		}
	}
	
	@Override
	protected AbstractIOIOActivity.IOIOThread createIOIOThread() {
		return new IOIOThread();
	}	
}

