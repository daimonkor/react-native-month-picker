import { Platform, ViewPropTypes } from 'react-native';
import PropTypes from 'prop-types';

const androidPropTypes = {
  fadeToColor: PropTypes.string,
  androidVariant: PropTypes.oneOf(['iosClone', 'nativeAndroid']),
  dividerHeight: PropTypes.number,
};

const iOSPropTypes = {
  theme: PropTypes.oneOf(['light', 'dark', 'auto']),
};

const DateType = PropTypes.instanceOf(Date);

export default {
  ...(Platform === 'android' ? androidPropTypes : iOSPropTypes),
  date: DateType.isRequired,
  onChange: PropTypes.func,
  minimumDate: DateType,
  maximumDate: DateType,
  locale: PropTypes.string,
  textColor: PropTypes.string,
  timeZoneOffsetInMinutes: PropTypes.number,
  testID: ViewPropTypes.testID,
  style: ViewPropTypes.style,
};
