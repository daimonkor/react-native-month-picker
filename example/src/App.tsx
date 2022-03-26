import React, { useState } from 'react';
import { StyleSheet, View } from 'react-native';
import { MonthPicker } from 'react-native-month-picker';

export default function App() {
  const [date, setDate] = useState(new Date());
  console.log('current date', date);
  return (
    <View style={styles.container}>
      <MonthPicker
        date={date}
        onDateChange={setDate}
        textColor={'red'}
        locale={'en'}
      />
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
