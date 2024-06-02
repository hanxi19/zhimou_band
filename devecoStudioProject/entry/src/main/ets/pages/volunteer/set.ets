import router from '@ohos.router';
import { PreferencesUtils } from '../../entryability/myUtil/preferencesUtils';
import common from '@ohos.app.ability.common';
@Entry
@Component
struct myset{
  context = getContext(this) as common.UIAbilityContext
  @State openinfor:boolean=true
  @State receptreque:boolean=true

  build(){
    Column(){
      Column() {
        //顶部导航栏
        Row(){
          Button(){
            Image($r('app.media.array'))
          }.size({width:'10%',height:'50%'})
          .backgroundColor('#ffffff')
          .margin({left:30})
          .onClick(()=>{
            router.pushUrl({
              url: "pages/volunteer/volunteer_main",
              params: {
                name: '',
                count: 100
              }
            }, router.RouterMode.Single)})
          Text('设置')
            .fontSize(25)
            .margin({left:20})
        }.height('10%').width('100%').margin({top:20})
        //开启通知
        Row() {
          Text('开启通知')
            .fontSize(20)
            .width('65%')
          Toggle({ type: ToggleType.Switch, isOn: this.openinfor })
            .width(30)
            .onClick(()=>{
              this.record(0)
            })
        }.height('10%')
        .margin({top:20})
        //接受帮助请求
        Row() {
          Text('接收帮助请求')
            .fontSize(20)
            .width('65%')
          Toggle({ type: ToggleType.Switch, isOn: this.receptreque })
            .width(30)
            .onClick(()=>{
              this.record(1)
            })
        }
      }.height('90%')

      //退出按键
      Column() {
        Button('退出', { type: ButtonType.Capsule })
          .backgroundColor('#ffff3d3d')
          .size({ width: 150, height: 40 })
          .onClick(()=>{
            this.record(2)
            router.pushUrl({
              url: "pages/Index",
              params: {
                name: '',
                count: 100
              }
            }, router.RouterMode.Single)
          })
      }.height('10%')

    }.width('100%')
  }

  async aboutToAppear() {

    try {

      let preferences = await PreferencesUtils.getPreferences(this.context, 'store');
      if (preferences) {
        let openinfor = await preferences.get('openinfor',false);
          console.log(''+openinfor)
        let receptreque = await preferences.get('receptreque',false);
        if(openinfor==false){
          this.openinfor=false
        }
        else{
          this.openinfor=true
        }

        if(receptreque==false){
          this.receptreque=false
        }
        else{
          this.receptreque=true
        }
        //Logger.info('GetData', 'value:' + value);
      }

    } catch (error) {
      console.log('error=' + error);
    }
  }

  async record(buttoncode){
    let preference=await PreferencesUtils.getPreferences(this.context,'store')
    switch (buttoncode){
      case 0:this.openinfor=!this.openinfor
        preference.put('openinfor',this.openinfor)
        break
      case 1:this.receptreque=!this.receptreque
        preference.put('receptreque',this.receptreque)
        break
      case 3:preference.put('identity','no choice')
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