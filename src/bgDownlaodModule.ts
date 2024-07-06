import {NativeModules} from 'react-native';
const {BgDownloadModule} = NativeModules;
type bgDownloadModuleInterface = {
  downloadOnBackground: () => void;
};

export default BgDownloadModule as bgDownloadModuleInterface;
