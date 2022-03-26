package com.reactnativemonthpicker.wheelFunctions;

import com.reactnativemonthpicker.wheels.Wheel;

public class UpdateVisibility implements WheelFunction {

    @Override
    public void apply(Wheel wheel) {
       wheel.updateVisibility();
    }
}


