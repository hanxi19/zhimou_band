import router from '@ohos.router';
import prompt from '@ohos.prompt'
import app from '@system.app';

@Entry
@Component
struct volunteer_main{
  build(){
    Row() {
      Column() {
        Text("Hi,志愿者")
          .fontSize(40)
          .align(Alignment.Center)
        .height('80%')
        Button('设置',{ type: ButtonType.Capsule })
          .size({ height: 60, width: 150 })
          .backgroundColor('#ffb1b1b1')
          .fontSize(25)
          .onClick(()=>{
            router.pushUrl({
              url: "pages/volunteer/set",
              params: {
                name: '',
                count: 100
              }
            }, router.RouterMode.Single)})
      }.width('100%')

    }.height('100%')
    .backgroundImage($r('app.media.volunteer_backgroud'))
    .backgroundImageSize({width:'100%',height:'100%'})
  }

  onBackPress() {
    // console.log('pretime is '+this.pretime)
    // if (-1 == this.pretime) {
    //   // 第一次点击返回键，提示toast
    //   prompt.showToast({
    //     message: "再按一次退出应用"
    //   })
    //   return true;
    // } else {
    //   let currentTime = new Date().getTime();
    //   let flag = currentTime - this.pretime;
    //   if (flag > 2000) {
    //     //两次点击时间太长不做处理
    //     this.pretime = currentTime;
    //     return true;
    //   } else {
    //
    //     app.terminate(); // 2秒内点击，退出当前Ability。
    //   }
    // }
    return true;
  }
}