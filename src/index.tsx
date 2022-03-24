import {
  requireNativeComponent,
  UIManager,
  Platform,
  ViewStyle,
} from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-month-picker' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo managed workflow\n';

type MonthPickerProps = {
  color: string;
  style: ViewStyle;
};

const ComponentName = 'MonthPickerView';

export const MonthPickerView =
  UIManager.getViewManagerConfig(ComponentName) != null
    ? requireNativeComponent<MonthPickerProps>(ComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };
