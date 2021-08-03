import { AnalyticsConfig, AnalyticsProvider, AnalyticsRecord } from '@zup-it/beagle-web'
import StorageService from './storage.service'

const analyticsStorageKey = 'Analytics_data';
let analyticsRecords: AnalyticsRecord[]

const analyticsConfig: AnalyticsConfig = {
  enableScreenAnalytics: true,
  actions: {
    'beagle:confirm': ['message'],
    'beagle:alert': ['message']
  }
}

const analyticsProvider: AnalyticsProvider = {
  getConfig: () => analyticsConfig,
  createRecord: (record) => {
    analyticsRecords = StorageService.getData(analyticsStorageKey) || [];
    analyticsRecords.push(record)
    StorageService.setData(analyticsStorageKey, analyticsRecords)
  },
}

export default analyticsProvider