package com.henninghall.date_picker.ui;

import android.view.View;

import com.henninghall.date_picker.models.Variant;
import com.henninghall.date_picker.pickers.Picker;

import com.henninghall.date_picker.State;
import com.henninghall.date_picker.models.WheelType;
import com.henninghall.date_picker.wheelFunctions.SetDividerHeight;
import com.henninghall.date_picker.wheelFunctions.SetShowCount;
import com.henninghall.date_picker.wheelFunctions.WheelFunction;
import com.henninghall.date_picker.wheels.MonthWheel;
import com.henninghall.date_picker.wheels.Wheel;
import com.henninghall.date_picker.wheels.YearWheel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import com.reactnativedatepicker.R;
import cn.carbswang.android.numberpickerview.library.NumberPickerView;


public class Wheels {

    private final State state;
    private final NumberPickerView emptyStart;
    private final NumberPickerView emptyEnd;
    private MonthWheel monthWheel;
    private YearWheel yearWheel;
    private View rootView;
    private final PickerWrapper pickerWrapper;

    private HashMap<WheelType, Wheel> wheelPerWheelType;

    Wheels(State state, View rootView){
        this.state = state;
        this.rootView = rootView;
        pickerWrapper = new PickerWrapper(rootView);

        yearWheel = new YearWheel(getPickerWithId(R.id.year), state);
        monthWheel = new MonthWheel(getPickerWithId(R.id.month), state);
        emptyStart = (NumberPickerView) rootView.findViewById(R.id.empty_start);
        emptyEnd = (NumberPickerView) rootView.findViewById(R.id.empty_end);
        wheelPerWheelType = getWheelPerType();
    }

    private Picker getPickerWithId(final int id){
        return (Picker) rootView.findViewById(id);
    }

    void applyOnAll(WheelFunction function) {
        for (Wheel wheel: getAll()) function.apply(wheel);
    }

    void applyOnVisible(WheelFunction function) {
        for(Wheel wheel: getAll()) {
            if(wheel.visible()) function.apply(wheel);
        }
    }

    void applyOnInVisible(WheelFunction function) {
        for(Wheel wheel: getAll()) {
            if(!wheel.visible()) function.apply(wheel);
        }
    }

    void updateHeight() {
        int shownCount = state.derived.getShownCount();
        applyOnAll(new SetShowCount(shownCount));
        if(state.getVariant() == Variant.iosClone) {
            emptyStart.setShownCount(shownCount);
            emptyEnd.setShownCount(shownCount);
        }
    }

    void updateDividerHeight() {
        int height = state.getDividerHeight();
        applyOnAll(new SetDividerHeight(height));
        if(state.getVariant() == Variant.iosClone) {
            emptyStart.setDividerHeight(height);
            emptyEnd.setDividerHeight(height);
        }
    }

    void updateWheelOrder() {
        pickerWrapper.removeAll();
        if(state.getVariant() == Variant.iosClone) pickerWrapper.addPicker(emptyStart);
        addInOrder();
        if(state.getVariant() == Variant.iosClone) pickerWrapper.addPicker(emptyEnd);
    }

    Wheel getWheel(WheelType type){
        return wheelPerWheelType.get(type);
    }

    String getDateTimeString(int daysToSubtract) {
        return getDateString(daysToSubtract) ;
    }

    private String getDateModeString(int daysToSubtract) {
        ArrayList<Wheel> wheels = getOrderedVisibleWheels();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wheels.size(); i++) {
            if (i != 0) sb.append(" ");
            Wheel w = wheels.get(i);
            sb.append(w.getValue());
        }
        return sb.toString();
    }

    private String getDateString(int daysToSubtract){

            return getDateModeString(daysToSubtract);

    }





    String getDateTimeString() {
        return getDateTimeString(0);
    }

    String getDateString() {
        return getDateString(0);
    }

    String getDisplayValue() {
        StringBuilder sb = new StringBuilder();
        for (Wheel wheel: getOrderedVisibleWheels()) {
            sb.append(wheel.getDisplayValue());
        }
        return sb.toString();
    }

    private void addInOrder(){
        ArrayList<WheelType> wheels = state.derived.getOrderedVisibleWheels();
        for (WheelType wheelType : wheels) {
            Wheel wheel = getWheel(wheelType);
            pickerWrapper.addPicker(wheel.picker.getView());
        }
    }

    private ArrayList<Wheel> getOrderedVisibleWheels(){
        ArrayList<Wheel> list = new ArrayList<>();
        for (WheelType type : state.derived.getOrderedVisibleWheels()) {
            list.add(getWheel(type));
        }
        return list;
    }

    private List<Wheel> getAll(){
        return new ArrayList<>(Arrays.asList(yearWheel, monthWheel));
    }

    private String getDateFormatPattern(){
        ArrayList<Wheel> wheels = getOrderedVisibleWheels();

          return wheels.get(0).getFormatPattern() + " "
            + wheels.get(1).getFormatPattern();

    }

    public String getFormatPattern() {
        return this.getDateFormatPattern() ;
    }

    private HashMap<WheelType, Wheel> getWheelPerType(){
        return new HashMap<WheelType, Wheel>() {{

            put(WheelType.YEAR, yearWheel);
            put(WheelType.MONTH,monthWheel);

        }};
    }

    public boolean hasSpinningWheel() {
        for(Wheel wheel: getAll()) {
            if(wheel.picker.isSpinning()) return true;
        }
        return false;
    }
}
