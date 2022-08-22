import { WebPlugin } from '@capacitor/core';
import { SensorsPlugin } from './definitions';

export class SensorsWeb extends WebPlugin implements SensorsPlugin {
  constructor() {
    super({
      name: 'Sensors',
      platforms: ['web'],
    });
  }
}

const Sensors = new SensorsWeb();

export { Sensors };

import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(Sensors);
