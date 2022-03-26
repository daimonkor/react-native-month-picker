package com.reactnativemonthpicker.wheelFunctions;

import android.view.View;

import com.reactnativemonthpicker.wheels.Wheel;

public class Hide implements WheelFunction {

    @Override
    public void apply(Wheel wheel) {
        wheel.picker.setVisibility(View.GONE);
    }
}


