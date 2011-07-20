package org.shokai.ioio.analoginout;

import ioio.lib.api.*;
import ioio.lib.api.exception.*;
import ioio.lib.util.*;

import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class AnalogInOutActivity extends AbstractIOIOActivity {
	
	private ToggleButton btnLed;
	private SeekBar seekBarPwm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        log("start");
        
        this.btnLed = (ToggleButton)this.findViewById(R.id.toggleButtonLED);
        this.seekBarPwm = (SeekBar)this.findViewById(R.id.seekBarPwm);
        this.seekBarPwm.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onStartTrackingTouch(SeekBar bar) {
            	log(bar.getProgress());
            }

            @Override
            public void onProgressChanged(SeekBar bar, int progress, boolean fromTouch) {
            	log(bar.getProgress());
            }

            @Override
            public void onStopTrackingTouch(SeekBar bar) {
            	log(bar.getProgress());
            }
        });
    }

	public void log(Object msg){
		Log.v(this.getResources().getString(R.string.app_name), msg.toString());
	}
	
	class IOIOThread extends AbstractIOIOActivity.IOIOThread{
		
		private DigitalOutput led;
		private PwmOutput pwm;
		
		protected void setup() throws ConnectionLostException{
			led = this.ioio_.openDigitalOutput(0, true);
			pwm = this.ioio_.openPwmOutput(3, 1000);
		}
		
		protected void loop() throws ConnectionLostException{
			try{
				led.write(!btnLed.isChecked());
				pwm.setDutyCycle((float)seekBarPwm.getProgress()/seekBarPwm.getMax());
				sleep(10);
        	}
			catch (InterruptedException e) {
        	}
		}
		
	}

	@Override
	protected IOIOThread createIOIOThread() {
		return new IOIOThread();
	}
}