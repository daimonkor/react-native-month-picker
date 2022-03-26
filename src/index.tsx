import React from 'react';
import {
  Platform,
  requireNativeComponent,
  StyleProp,
  UIManager,
  ViewProps,
  ViewStyle,
} from 'react-native';

import { colorToHex } from './colorToHex';

const LINKING_ERROR =
  `The package 'react-native-month-picker' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo managed workflow\n';

const ComponentName = 'MonthPickerView';

export interface IMonthPickerProps {
  androidVariant?: 'iosClone' | 'nativeAndroid';
  dividerHeight?: number;
  date: Date;
  onDateChange?: (date: Date) => void;
  minimumDate?: Date;
  maximumDate?: Date;
  locale?: string;
  textColor?: string;
  testID?: string;
  style?: StyleProp<ViewStyle>;
}

export const NativeMonthPicker =
  UIManager.getViewManagerConfig(ComponentName) != null
    ? requireNativeComponent<
        | IMonthPickerProps
        | ViewProps
        | {
            onChange: (e: any) => void;
            date: number;
            minimumDate: number;
            maximumDate: number;
          }
      >(ComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };

export class MonthPicker extends React.PureComponent<IMonthPickerProps> {
  render() {
    return (
      <NativeMonthPicker
        testID={this.props.testID}
        textColor={colorToHex(this.props.textColor)}
        androidVariant={this.props.androidVariant ?? 'iosClone'}
        locale={this.props.locale}
        dividerHeight={this.props.dividerHeight}
        style={[
          { width: 310, height: Platform.OS === 'android' ? 180 : 216 },
          this.props.style,
        ]}
        date={this.props.date ? this.props.date.getTime() : undefined}
        maximumDate={
          this.props.maximumDate ? this.props.maximumDate.getTime() : undefined
        }
        minimumDate={
          this.props.minimumDate ? this.props.minimumDate.getTime() : undefined
        }
        onChange={(e: any) => {
          this.props.onDateChange &&
            this.props.onDateChange(new Date(e.nativeEvent.timestamp));
        }}
        onStartShouldSetResponder={() => true}
        onResponderTerminationRequest={() => false}
      />
    );
  }
}
