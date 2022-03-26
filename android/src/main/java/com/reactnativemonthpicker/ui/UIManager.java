package com.reactnativemonthpicker.ui;

import android.view.View;

import com.reactnativemonthpicker.State;
import com.reactnativemonthpicker.wheelFunctions.AddOnChangeListener;
import com.reactnativemonthpicker.wheelFunctions.AnimateToDate;
import com.reactnativemonthpicker.wheelFunctions.Refresh;
import com.reactnativemonthpicker.wheelFunctions.SetDate;
import com.reactnativemonthpicker.wheelFunctions.TextColor;
import com.reactnativemonthpicker.wheelFunctions.UpdateVisibility;
import com.reactnativemonthpicker.wheelFunctions.HorizontalPadding;
import com.reactnativemonthpicker.wheels.Wheel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UIManager {
    private final State state;
    private final View rootView;
    private Wheels wheels;
    private FadingOverlay fadingOverlay;
    private WheelScroller wheelScroller = new WheelScroller();

    public UIManager(State state, View rootView){
        this.state = state;
        this.rootView = rootView;
        wheels = new Wheels(state, rootView);
        addOnChangeListener();
    }

    public void updateWheelVisibility(){
        wheels.applyOnAll(new UpdateVisibility());
    }

    public void updateTextColor(){
        wheels.applyOnAll(new TextColor(state.getTextColor()));
    }

    public void updateFadeToColor(){
        if(state.derived.hasNativeStyle()) return;
        fadingOverlay = new FadingOverlay(state, rootView);
        fadingOverlay.updateColor();
    }

    public void updateHeight(){
        wheels.updateHeight();
    }

    public void updateWheelOrder() {
        wheels.updateWheelOrder();
    }

    public void updateDisplayValues(){
        wheels.applyOnAll(new Refresh());
    }

    public void setWheelsToDate(){
        wheels.applyOnAll(new SetDate(state.getDate()));
    }

    public void scroll(int wheelIndex, int scrollTimes) {
        Wheel wheel = wheels.getWheel(state.derived.getOrderedVisibleWheels().get(wheelIndex));
        wheelScroller.scroll(wheel, scrollTimes);
    }

    SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat(wheels.getFormatPattern(), state.getLocale());
    }

    void animateToDate(Calendar date) {
        wheels.applyOnInVisible(new SetDate(date));
        wheels.applyOnVisible(new AnimateToDate(date));
    }

    private void addOnChangeListener(){
        WheelChangeListener onWheelChangeListener = new WheelChangeListenerImpl(wheels, state, this, rootView);
        wheels.applyOnAll(new AddOnChangeListener(onWheelChangeListener));
    }

    public void updateDividerHeight() {
        wheels.updateDividerHeight();
    }

    public void updateWheelPadding() {
        wheels.applyOnVisible(new HorizontalPadding());
    }

    public void updateLastSelectedDate(Calendar date) {
        state.setLastSelectedDate(date);
    }
}
