import React from 'react';
import { Platform, requireNativeComponent } from 'react-native';
import propTypes from './propTypes';
import { colorToHex } from './colorToHex';
import { throwIfInvalidProps } from './propChecker';

const NativeMonthPicker = requireNativeComponent(`DatePickerView`);

export class MonthPicker extends React.PureComponent {
  render() {
    if (__DEV__) throwIfInvalidProps(this.props);
    return (
      <NativeMonthPicker
        ref={this.props.innerRef}
        textColor={colorToHex(this.props.textColor)}
        androidVariant={this.props.androidVariant ?? 'iosClone'}
        {...{
          style: [
            { width: 310, height: Platform.OS === 'android' ? 180 : 216 },
            this.props.style,
          ],
          date: this.props.date
            ? Platform.OS === 'android'
              ? this.props.date.toISOString()
              : this.props.date.getTime()
            : undefined,
          maximumDate: this.props.maximumDate
            ? Platform.OS === 'android'
              ? this.props.maximumDate.toISOString()
              : this.props.maximumDate.getTime()
            : undefined,
          minimumDate: this.props.minimumDate
            ? Platform.OS === 'android'
              ? this.props.minimumDate.toISOString()
              : this.props.minimumDate.getTime()
            : undefined,
        }}
        onChange={(e) => {
          const date = e.nativeEvent.timestamp;
          console.log('SDSDSD', e.nativeEvent.timestamp);
          this.props.onDateChange && this.props.onDateChange(new Date(date));
        }}
        onStartShouldSetResponder={() => true}
        onResponderTerminationRequest={() => false}
      />
    );
  }
}

MonthPicker.propTypes = propTypes;
