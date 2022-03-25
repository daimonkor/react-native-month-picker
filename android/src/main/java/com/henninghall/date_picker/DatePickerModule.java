package com.henninghall.date_picker;

import android.view.View;
import android.widget.LinearLayout;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import net.time4j.android.ApplicationStarter;

public class DatePickerModule extends ReactContextBaseJavaModule {

  DatePickerModule(ReactApplicationContext context) {
    super(context);
    ApplicationStarter.initialize(context, false); // false = no need to prefetch on time data background tread
  }

  @ReactMethod
  public void addListener(String eventName) {
    // Keep: Required for RN built in Event Emitter Calls.
  }

  @ReactMethod
  public void removeListeners(Integer count) {
    // Keep: Required for RN built in Event Emitter Calls.
  }

  private View withTopMargin(PickerView view) {
    LinearLayout linearLayout = new LinearLayout(DatePickerPackage.context);
    linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
      LinearLayout.LayoutParams.MATCH_PARENT,
      LinearLayout.LayoutParams.WRAP_CONTENT
    ));
    linearLayout.addView(view);
    linearLayout.setPadding(0, Utils.toDp(20), 0, 0);
    return linearLayout;
  }

  @Override
  public String getName() {
    return "RNDatePicker";
  }
}
