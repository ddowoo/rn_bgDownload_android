import {StyleSheet, Text, TouchableOpacity, View} from 'react-native';
import BgDownloadModule from './src/bgDownlaodModule';

const App = () => {
  return (
    <View style={styles.bg}>
      <TouchableOpacity
        style={styles.button}
        onPress={() => BgDownloadModule.downloadOnBackground()}>
        <Text style={styles.text}>다운로드 버튼</Text>
      </TouchableOpacity>
    </View>
  );
};

export default App;

const styles = StyleSheet.create({
  bg: {
    backgroundColor: '#fff',
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  button: {
    width: 120,
    height: 48,
    backgroundColor: 'green',
    borderRadius: 8,
    justifyContent: 'center',
    alignItems: 'center',
  },
  text: {
    fontSize: 14,
    fontWeight: 'bold',
    color: '#fff',
  },
});
