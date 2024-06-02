
/**
 * 轻量级缓存工具类
 */

import DataPreferences from '@ohos.data.preferences';



export class PreferencesUtils {
  private static readonly TAG = 'PreferencesUtils';

  private constructor() {
  }

  public static  getPreferences(context, name: string): Promise<DataPreferences.Preferences> {

    return new Promise<DataPreferences.Preferences>((resolved,rejected) => {
      DataPreferences.getPreferences(context, name)
        .then((res) => {
          resolved(res);
        })
        .catch(reason => {
          //Logger.error(this.TAG, '获取Preferences实例失败');
          rejected(reason);
        })
    });

  }

  public static put(preferences,key,value){
    preferences.then((res) => {
      res.put(key, value).then(() => {
        res.flush();
        //Logger.info('PutData','isPrivacy is put success');
      }).catch((err) => {
        //Logger.info('PutData','isPrivacy put failed. Cause:' + err);
      });
    })
  }

  // public static get(preference,key){
  //   let promise = preference.get(key, true);
  //   promise.then((data) => {
  //     return data
  //   }).catch((err) => {
  //     return false
  //   })
  // }
}
