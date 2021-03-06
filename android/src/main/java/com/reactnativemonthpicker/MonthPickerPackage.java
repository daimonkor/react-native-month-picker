package com.reactnativemonthpicker;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.Arrays;
import java.util.List;

public class MonthPickerPackage implements ReactPackage {
    public static ReactApplicationContext context;

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        context = reactContext;
        return Arrays.<NativeModule>asList(
                new DatePickerModule(reactContext)
        );
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        context = reactContext;
        return Arrays.<ViewManager> asList(
                new DatePickerManager()
        );
    }

}
