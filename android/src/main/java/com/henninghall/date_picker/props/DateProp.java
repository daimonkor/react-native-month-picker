package com.henninghall.date_picker.props;

import com.facebook.react.bridge.Dynamic;

public class DateProp extends Prop<Double> {
    public static final String name = "date";

    @Override
    public Double toValue(Dynamic value){
        return value.asDouble();
    }

}
