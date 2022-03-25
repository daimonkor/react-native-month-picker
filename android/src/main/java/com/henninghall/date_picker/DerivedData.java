package com.henninghall.date_picker;

import android.util.Log;

import com.henninghall.date_picker.models.Variant;
import com.henninghall.date_picker.models.WheelType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import com.reactnativedatepicker.R;

public class DerivedData {
  private final State state;

  DerivedData(State state) {
    this.state = state;
  }

  public ArrayList<WheelType> getVisibleWheels() {
    ArrayList<WheelType> visibleWheels = new ArrayList<>();
    visibleWheels.add(WheelType.YEAR);
    visibleWheels.add(WheelType.MONTH);
    return visibleWheels;
  }

  public ArrayList<WheelType> getOrderedVisibleWheels() {
    ArrayList<WheelType> orderedWheels = getOrderedWheels();
    ArrayList<WheelType> visibleWheels = getVisibleWheels();
    ArrayList<WheelType> visibleOrderedWheels = new ArrayList<>();
    for (WheelType wheel : orderedWheels) {
      if (visibleWheels.contains(wheel)) visibleOrderedWheels.add(wheel);
    }
    return visibleOrderedWheels;
  }

  private ArrayList<WheelType> getOrderedWheels() {
    String dateTimePatternOld = LocaleUtils.getDateTimePattern(state.getLocale());
    String dateTimePattern = dateTimePatternOld.replaceAll("\\('(.+?)'\\)", "\\${$1}")
      .replaceAll("'.+?'", "")
      .replaceAll("\\$\\{(.+?)\\}", "('$1')");
    ArrayList<WheelType> unorderedTypes = new ArrayList(Arrays.asList(WheelType.values()));
    ArrayList<WheelType> orderedWheels = new ArrayList<>();

    for (char c : dateTimePattern.toCharArray()) {
      try {
        WheelType wheelType = Utils.patternCharToWheelType(c);
        if (unorderedTypes.contains(wheelType)) {
          unorderedTypes.remove(wheelType);
          orderedWheels.add(wheelType);
        }
      } catch (Exception e) {
        // ignore unknown pattern chars that not correspond to any wheel type
      }
    }

    if (!unorderedTypes.isEmpty()) {
      Log.e(
        "RNDatePicker",
        unorderedTypes.size() + " wheel types cannot be ordered. Wheel type 0: " + unorderedTypes.get(0));
    }

    return orderedWheels;
  }

  public int getShownCount() {
    int DP_PER_SHOW_SHOW_COUNT = 35;
    int showCount = state.getHeight() / DP_PER_SHOW_SHOW_COUNT;
    int oddShowCount = showCount % 2 == 0 ? showCount + 1 : showCount;
    return oddShowCount;
  }

  public boolean hasNativeStyle() {
    return state.getVariant() == Variant.nativeAndroid;
  }

  public int getRootLayout() {
    switch (state.getVariant()) {
      case nativeAndroid:
        return R.layout.native_picker;
      case iosClone:
        return R.layout.ios_clone;
      default:
        return R.layout.ios_clone;
    }
  }

  public String getLastDate() {
    Calendar lastSelectedDate = state.getLastSelectedDate();
    String initialDate = state.getIsoDate();
    if (lastSelectedDate != null) return Utils.dateToIso(lastSelectedDate);
    return initialDate;
  }
}
