package com.reactnativemonthpicker.props;

import com.facebook.react.bridge.Dynamic;

public class MaximumDateProp extends Prop<Double> {
    public static final String name = "maximumDate";

    @Override
    public Double toValue(Dynamic value){
        return value.asDouble();
    }

}
