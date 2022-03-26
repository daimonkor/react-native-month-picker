package com.reactnativemonthpicker;

import com.facebook.react.bridge.Dynamic;
import com.reactnativemonthpicker.models.Variant;
import com.reactnativemonthpicker.props.DividerHeightProp;
import com.reactnativemonthpicker.props.VariantProp;
import com.reactnativemonthpicker.props.DateProp;
import com.reactnativemonthpicker.props.FadeToColorProp;
import com.reactnativemonthpicker.props.HeightProp;
import com.reactnativemonthpicker.props.LocaleProp;
import com.reactnativemonthpicker.props.MaximumDateProp;
import com.reactnativemonthpicker.props.MinimumDateProp;
import com.reactnativemonthpicker.props.Prop;
import com.reactnativemonthpicker.props.TextColorProp;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

public class State {

    private Calendar lastSelectedDate = null;
    private final DateProp dateProp = new DateProp();
    private final LocaleProp localeProp = new LocaleProp();
    private final FadeToColorProp fadeToColorProp = new FadeToColorProp();
    private final TextColorProp textColorProp = new TextColorProp();
    private final MinimumDateProp minimumDateProp = new MinimumDateProp();
    private final MaximumDateProp maximumDateProp = new MaximumDateProp();
    private final HeightProp heightProp = new HeightProp();
    private final VariantProp variantProp = new VariantProp();
    private final DividerHeightProp dividerHeightProp = new DividerHeightProp();

    private final HashMap props = new HashMap<String, Prop>() {{
        put(DateProp.name, dateProp);
        put(LocaleProp.name, localeProp);
        put(FadeToColorProp.name, fadeToColorProp);
        put(TextColorProp.name, textColorProp);
        put(MinimumDateProp.name, minimumDateProp);
        put(MaximumDateProp.name, maximumDateProp);
        put(HeightProp.name, heightProp);
        put(VariantProp.name, variantProp);
        put(DividerHeightProp.name, dividerHeightProp);
    }};
    public DerivedData derived;

    public State() {
        derived = new DerivedData(this);
    }

    private Prop getProp(String name) {
        return (Prop) props.get(name);
    }

    void setProp(String propName, Dynamic value) {
        getProp(propName).setValue(value);
    }

    public String getFadeToColor() {
        return (String) fadeToColorProp.getValue();
    }

    public String getTextColor() {
        return (String) textColorProp.getValue();
    }

    public Locale getLocale() {
        return (Locale) localeProp.getValue();
    }

    public Calendar getMinimumDate() {
        DateBoundary db = new DateBoundary(getTimeZone(), (Double) minimumDateProp.getValue());
        return db.get();
    }

    public Calendar getMaximumDate() {
        DateBoundary db = new DateBoundary(getTimeZone(), (Double) maximumDateProp.getValue());
        return db.get();
    }

    public TimeZone getTimeZone() {
        return TimeZone.getTimeZone("UTC");
    }

    public Double getIsoDate() {
        return dateProp.getValue();
    }

    public Calendar getDate() {
        return Utils.isoToCalendar(getIsoDate(), getTimeZone());
    }

    public Integer getHeight() {
        return (Integer) heightProp.getValue();
    }

    public String getLocaleLanguageTag() {
        return localeProp.getLanguageTag();
    }

    public Variant getVariant() {
        return variantProp.getValue();
    }

    public int getDividerHeight() {
        return dividerHeightProp.getValue();
    }

    public Calendar getLastSelectedDate() {
        return lastSelectedDate;
    }

    public void setLastSelectedDate(Calendar date) {
        lastSelectedDate = date;
    }
}
