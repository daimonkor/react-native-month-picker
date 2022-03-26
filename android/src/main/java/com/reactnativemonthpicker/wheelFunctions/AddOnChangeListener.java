package com.reactnativemonthpicker.wheelFunctions;

import com.reactnativemonthpicker.pickers.Picker;
import com.reactnativemonthpicker.ui.WheelChangeListener;
import com.reactnativemonthpicker.wheels.Wheel;

public class AddOnChangeListener implements WheelFunction {

    private final WheelChangeListener onChangeListener;

    public AddOnChangeListener(WheelChangeListener onChangeListener){
        this.onChangeListener = onChangeListener;
    }

    @Override
    public void apply(final Wheel wheel) {
        wheel.picker.setOnValueChangedListener(new Picker.OnValueChangeListener() {
            @Override
            public void onValueChange() {
                onChangeListener.onChange(wheel);
            }
        });
    }
}


