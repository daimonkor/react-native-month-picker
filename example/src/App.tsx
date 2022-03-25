import * as React from 'react';
import { StyleSheet, View } from 'react-native';
import { MonthPicker } from 'react-native-month-picker';
import { useState } from 'react';

export default function App() {
  const [date, setDate] = useState(new Date());
  return (
    <View style={styles.container}>
      <MonthPicker date={date} onDateChange={setDate} textColor={'red'} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
