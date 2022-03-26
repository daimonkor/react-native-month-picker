package com.reactnativemonthpicker.wheelFunctions;

import com.reactnativemonthpicker.wheels.Wheel;

import java.util.Calendar;

public class AnimateToDate implements WheelFunction {

    private Calendar date;

    public AnimateToDate(Calendar date) {
        this.date = date;
    }

    @Override
    public void apply(Wheel wheel) {
        wheel.animateToDate(date);
    }
}


