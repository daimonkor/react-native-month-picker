package com.reactnativemonthpicker.wheels;

import android.graphics.Paint;

import java.util.*;
import com.reactnativemonthpicker.*;
import com.reactnativemonthpicker.pickers.Picker;

public class MonthWheel extends Wheel
{
    public MonthWheel(final Picker picker, final State id) {
        super(picker, id);
    }

    @Override
    public ArrayList<String> getValues() {
        ArrayList<String> values = new ArrayList<>();
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.MONTH, 0);
        for (int i = 0; i <= 11; ++i) {
            values.add(getLocaleString(cal));
            cal.add(Calendar.MONTH, 1);
        }
        return values;
    }

    @Override
    public boolean visible() {
        return true;
    }

    @Override
    public boolean wrapSelectorWheel() {
        return true;
    }

    @Override
    public String getFormatPattern() {
        return "LLLL";
    }

    @Override
    public Paint.Align getTextAlign() {
        return Paint.Align.LEFT;
    }

    @Override
    public int getHorizontalPadding() {
        return 1;
    }


}
