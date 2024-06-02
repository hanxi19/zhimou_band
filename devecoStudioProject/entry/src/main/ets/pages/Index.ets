import { PreferencesUtils } from '../entryability/myUtil/preferencesUtils'
import common from '@ohos.app.ability.common'
import router from '@ohos.router'
import prompt from '@ohos.prompt'
import app from '@system.app';
@Entry
@Component
struct Index {
  context = getContext(this) as common.UIAbilityContext
  @State identity:String='no choice'

  build() {
    Row(){
      Column(){
        Text("选择您的身份")
          .fontSize(30)
        Button("我是用户",{type:ButtonType.Circle})
          .height('30%')
          .margin(50)
          .fontSize(25)
          .fontColor('#fffdfdfd')
          .backgroundColor('#ff0289d6')
          .onClick(()=> {
            this.record(0)
            router.pushUrl({
              url: "pages/elder/elder_main",
              params: {
                name: '',
                count: 100
              }
            }, router.RouterMode.Single)
          })
        Button('我是志愿者',{type:ButtonType.Circle})
          .height('30%')
          .fontSize(25)
          .backgroundColor('#ffde6565')
          .fontColor('#fff5f1f1')
          .onClick(()=>{
            this.record(1)
            router.pushUrl({
              url: "pages/volunteer/volunteer_main",
              params: {
                name: '',
                count: 100
              }
            }, router.RouterMode.Single)
          })
      }.width('100%')
    }
    .height('100%')
    .backgroundColor('#fffaa2a2')
  }

  async aboutToAppear() {

    try {

      let preferences = await PreferencesUtils.getPreferences(this.context, 'store');
      if (preferences) {
        let identity = await preferences.get('identity','no choice');
        if(identity=='volunteer'){
          router.pushUrl({
            url: "pages/volunteer/volunteer_main",
            params: {
              name: '',
              count: 100
            }
          }, router.RouterMode.Single)
        }
        else if(identity=='elder'){
          router.pushUrl({
            url: "pages/elder/elder_main",
            params: {
              name: '',
              count: 100
            }
          }, router.RouterMode.Single)
        }
      }

    } catch (error) {
      console.log('error=' + error);
    }
  }

  async record(buttoncode){
    let preference=await PreferencesUtils.getPreferences(this.context,'store')
    switch (buttoncode){
      case 0: preference.put('identity','elder')
        break
      case 1: preference.put('identity','volunteer')
        break
      default :break
    }
    preference.flush(function (err) {
      if (err) {
        console.error("Failed to flush. code =" + err.code + ", message =" + err.message);
        return;
      }
      console.info("Succeeded in flushing.");
    })
  }


}