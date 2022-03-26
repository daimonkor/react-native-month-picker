package com.reactnativemonthpicker.props;

import com.facebook.react.bridge.Dynamic;
import com.reactnativemonthpicker.models.Variant;

public class VariantProp extends Prop<Variant> {
    public static final String name = "androidVariant";

    @Override
    public Variant toValue(Dynamic value){
        return Variant.valueOf(value.asString());
    }

}
