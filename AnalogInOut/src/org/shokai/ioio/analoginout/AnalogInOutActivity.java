package org.shokai.ioio.analoginout;

import ioio.lib.api.*;
import ioio.lib.api.DigitalInput.Spec.Mode;
import ioio.lib.api.exception.*;
import ioio.lib.util.*;

import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class AnalogInOutActivity extends AbstractIOIOActivity {
	
	private ToggleButton btnLed;
	private SeekBar seekBarPwm, seekBarAnalogIn, seekBarDigitalIn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        log("start");
        this.btnLed = (ToggleButton)this.findViewById(R.id.toggleButtonLED);
        this.seekBarDigitalIn = (SeekBar)this.findViewById(R.id.seekBarDigitalIn);
        this.seekBarAnalogIn = (SeekBar)this.findViewById(R.id.SeekBarAnalogIn);
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
		private DigitalInput btn;
		private PwmOutput pwm;
		private AnalogInput ain;
		
		protected void setup() throws ConnectionLostException{
			led = this.ioio_.openDigitalOutput(0, true);
			btn = this.ioio_.openDigitalInput(4, Mode.PULL_DOWN);
			pwm = this.ioio_.openPwmOutput(3, 1000);
			ain = this.ioio_.openAnalogInput(45);
		}
		
		protected void loop() throws ConnectionLostException{
			try{
				led.write(!btnLed.isChecked());
				if(btn.read()) seekBarDigitalIn.setProgress(1);
				else seekBarDigitalIn.setProgress(0);
				pwm.setDutyCycle((float)seekBarPwm.getProgress() / seekBarPwm.getMax());
				seekBarAnalogIn.setProgress((int) (ain.read() * seekBarAnalogIn.getMax()));
				sleep(10);
			} catch (InterruptedException e) {
			}
		}

	}

	@Override
	protected IOIOThread createIOIOThread() {
		return new IOIOThread();
	}
}