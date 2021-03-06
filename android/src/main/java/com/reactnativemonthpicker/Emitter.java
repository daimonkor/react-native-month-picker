package com.reactnativemonthpicker;

import android.view.View;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.util.Calendar;

public class Emitter {

    private static RCTEventEmitter eventEmitter(){
        return MonthPickerPackage.context.getJSModule(RCTEventEmitter.class);
    }

    private static DeviceEventManagerModule.RCTDeviceEventEmitter deviceEventEmitter(){
        return MonthPickerPackage.context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
    }

    public static void onDateChange(Calendar date, View view) {
        WritableMap event = Arguments.createMap();
        String dateString = Utils.dateToIso(date);
        event.putString("date", dateString);
        event.putDouble("timestamp", (double) date.getTime().getTime());
        eventEmitter().receiveEvent(view.getId(), "dateChange", event);
    }

}
