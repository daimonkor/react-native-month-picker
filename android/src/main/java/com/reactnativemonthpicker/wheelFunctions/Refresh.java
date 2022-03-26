package com.reactnativemonthpicker.wheelFunctions;

import com.reactnativemonthpicker.wheels.Wheel;

public class Refresh implements WheelFunction {

    @Override
    public void apply(Wheel wheel) {
        wheel.refresh();
    }
}


