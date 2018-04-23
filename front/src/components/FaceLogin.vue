<template>
  <div class="face-login">
    <div class='camera-wrapper'>
      <span class="loading">正在加载摄像头，请稍等</span>
      <video id="video" class="video" width="320" height="240" preload autoplay loop muted></video>
      <canvas id="canvas" class="video" width="320" height="240"></canvas>
    </div>
    <div>
        <a href='javascript:;' class='cancel-btn' @click='retry'>重试</a>
        <a href='javascript:;' class='cancel-btn' @click='cancel'>取消</a>
        <span class="message">{{message}}</span>
    </div>

    <div class="gallaray">
        <div class="gallaray-item" v-for="(c, index) in captures" :key="index">
            <img :src="c" height="150" />
        </div>
    </div>
  </div>
</template>

<script>
/* eslint-disable */
export default {
  
  methods: {
    initTracker() {
      let _this = this;

      this.message = null;
      this.video = document.getElementById("video");
      this.canvas = document.getElementById("canvas");
      this.context = this.canvas.getContext("2d");

      // 初始化tracking参数
      this.tracker = new tracking.ObjectTracker("face");
      this.tracker.setInitialScale(4);
      this.tracker.setStepSize(2);
      this.tracker.setEdgesDensity(0.1);
      this.tracker.on("track", event => {
        _this.onTracked(event);
      });

      // 启用摄像头
      // 如果是读取视频，可以用trackerTask.stop trackerTask.run来暂停、开始视频
      this.trackerTask = tracking.track(this.video, this.tracker, { camera: true });
    },

    onTracked(event) {
      let _this = this;

      // 判断终止条件, stop是异步的，不返回的话，还会一直截图
      if (this.captures.length >= 3) {
        this.onStopTracking();
        return;
      }

      // 画框框
      this.context.clearRect(0, 0, this.canvas.width, this.canvas.height);
      event.data.forEach(rect => {
        _this.context.lineWidth = 5;
        _this.context.strokeStyle = "#50e3ce";
        _this.context.strokeRect(rect.x, rect.y, rect.width, rect.height);
        _this.context.font = "11px Helvetica";
        _this.context.fillStyle = "#fff";
        _this.context.fillText(
          "x: " + rect.x + "px",
          rect.x + rect.width + 5,
          rect.y + 11
        );
        _this.context.fillText(
          "y: " + rect.y + "px",
          rect.x + rect.width + 5,
          rect.y + 22
        );

        // 截图
        _this.captureImage(rect.x, rect.y, rect.width, rect.height);
      });
    },

    onStopTracking() {
      this.trackerTask.stop();
      this.video.pause();
      this.video.srcObject.getVideoTracks().forEach(videoTrack => {
        videoTrack.stop();
      });
    },

    captureImage(x, y, width, height) {
      const buffer = 25;
      var bufferCanvas = document.createElement("canvas");
      var bfx = bufferCanvas.getContext("2d");

      bufferCanvas.width = width + buffer * 2;
      bufferCanvas.height = height + buffer * 4;
      bfx.drawImage(this.video, - x + buffer, - y + buffer * 2 ,320,240);
      this.captures.push(bufferCanvas.toDataURL("image/png"));
    },

    retry() {
      this.captures = [];
      // this.initTracker();
      location.reload();
    },

    cancel() {
      this.onStopTracking();
      this.$router.push("/");
    }
  },

  data() {
    return {
      tracker: Object, //人脸追踪器
      trackerTask: Object, // 用于控制食品和画布的工具
      video: Object, // 页面视频元素
      canvas: Object, // 页面画布元素
      context: Object, // 画布内容
      captures: [], // 截图列表
      message: String 
    };
  },

  mounted() {
    let _this = this;
    this.initTracker();

    setTimeout(() => {
      _this.onStopTracking();
      if (this.captures.length === 0) {
        this.message = "抱歉没有检测到找到人脸，请到光源较好的地方重试";
      }
    }, 30000);
  }
};
</script>

<style lang="less" scoped>
@color_3: #fff;
@background_color_3: #50e3ce;

.face-login {
  position: fixed;
  overflow: hidden;
  left: 30%;
  top: 5%;
  width: 500px;
  min-height: 555px;
  z-index: 10;
  right: 140px;
  background: #fff;
  border-radius: 5px;
  box-shadow: 0px 3px 16px -5px #070707;
  .camera-wrapper {
    background: url(/static/img/login-bg.jpg);
    width: 100%;
    height: 312px;
    overflow: hidden;
    .video {
      top: 20px;
      left: 90px;
      position: absolute;
      border-radius: 10px;
      box-shadow: 0px 3px 16px -5px #070707;
    }
    .loading {
      height: 312px;
      line-height: 312px;
      color: @color_3;
    }
  }
  .cancel-btn {
    width: 402px;
    display: block;
    text-align: center;
    line-height: 50px;
    margin: 0 auto 15px;
    height: 50px;
    color: @color_3;
    font-size: 13px;
    background-color: @background_color_3;
    border-radius: 5px;
    position: relative;
    text-decoration: none;
    &:hover {
      color: @color_3;
      opacity: 0.8;
    }
    &:focus {
      color: @color_3;
      opacity: 0.8;
    }
    .icons {
      margin-left: 30px;
      vertical-align: middle;
    }
    .text {
      left: 95px;
      line-height: 50px;
      text-align: center;
      position: absolute;
    }
  }
  .message {
    line-height: 50px;
    height: 50px;
    font-size: 18px;
  }

  .gallaray {
    display: flex;
    padding: 0 10%;
    .gallaray-item {
      margin: 5px;
    }
  }
}
</style>
