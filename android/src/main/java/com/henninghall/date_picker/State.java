package com.henninghall.date_picker;

import com.facebook.react.bridge.Dynamic;
import com.henninghall.date_picker.models.Variant;
import com.henninghall.date_picker.props.DividerHeightProp;
import com.henninghall.date_picker.props.VariantProp;
import com.henninghall.date_picker.props.DateProp;
import com.henninghall.date_picker.props.FadeToColorProp;
import com.henninghall.date_picker.props.HeightProp;
import com.henninghall.date_picker.props.LocaleProp;
import com.henninghall.date_picker.props.MaximumDateProp;
import com.henninghall.date_picker.props.MinimumDateProp;
import com.henninghall.date_picker.props.Prop;
import com.henninghall.date_picker.props.TextColorProp;

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
        DateBoundary db = new DateBoundary(getTimeZone(), (String) minimumDateProp.getValue());
        return db.get();
    }

    public Calendar getMaximumDate() {
        DateBoundary db = new DateBoundary(getTimeZone(), (String) maximumDateProp.getValue());
        return db.get();
    }

    public TimeZone getTimeZone() {
        return TimeZone.getTimeZone("UTC");
    }

    public String getIsoDate() {
        return (String) dateProp.getValue();
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
