package com.henninghall.date_picker.props;

import com.facebook.react.bridge.Dynamic;

public class MinimumDateProp extends Prop<Double> {
    public static final String name = "minimumDate";

    @Override
    public Double toValue(Dynamic value){
        return value.asDouble();
    }

}
