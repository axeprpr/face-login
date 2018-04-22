//登录

<template>
<div class='login' id='login'>
    <div class='log-bg'>
        <div class='log-cloud cloud1'></div>
        <div class='log-cloud cloud2'></div>
        <div class='log-cloud cloud3'></div>
        <div class='log-cloud cloud4'></div>

        <div class='log-logo'>Welcome!</div>
        <div class='log-text'>@Funfan</div>
    </div>
    <div>
        <a href='javascript:;' class='log-btn' @click='register'>拍照注册</a>
        <a href='javascript:;' class='log-btn' @click='login'>刷脸登录</a>
    </div>
    <spinner v-if='isLoging' :type='bubbles' style='margin-top: -30%'></spinner>
</div>
</template>

<script>
/* eslint-disable */
import { Spinner } from "vux";
export default {
  data() {
    return {
      isLoging: false
    };
  },
  components: {
    Spinner
  },
  methods: {
    //登录逻辑
    register() {
      this.$router.push("/UserInfo");
    },

    //登录请求
    login() {
      this.$router.push("/FaceLogin");
    }
  }
};
</script>

<style lang='less' scoped>
@color_1: #1fcab3;
@color_2: #57d4c3;
@color_3: #fff;
@background_color_1: #3b5999;
@background_color_2: #13b4e9;
@background_color_3: #50e3ce;

@-webkit-keyframes cloud1 {
  0% {
    left: 200px;
  }
  100% {
    left: -130px;
  }
}
@keyframes cloud1 {
  0% {
    left: 200px;
  }
  100% {
    left: -130px;
  }
}
@-webkit-keyframes cloud2 {
  0% {
    left: 500px;
  }
  100% {
    left: -90px;
  }
}
@keyframes cloud2 {
  0% {
    left: 500px;
  }
  100% {
    left: -90px;
  }
}
@-webkit-keyframes cloud3 {
  0% {
    left: 620px;
  }
  100% {
    left: -70px;
  }
}
@keyframes cloud3 {
  0% {
    left: 620px;
  }
  100% {
    left: -70px;
  }
}
@-webkit-keyframes cloud4 {
  0% {
    left: 100px;
  }
  100% {
    left: -70px;
  }
}
@keyframes cloud4 {
  0% {
    left: 100px;
  }
  100% {
    left: -70px;
  }
}
.login {
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

  .log-bg {
    background: url(/static/img/login-bg.jpg);
    width: 100%;
    height: 312px;
    overflow: hidden;
    .log-cloud {
      background-image: url(/static/img/login-cloud.png);
      width: 63px;
      height: 40px;
      position: absolute;
      z-index: 1;
    }
    .cloud1 {
      top: 21px;
      left: -30px;
      transform: scale(0.6);
      animation: cloud1 20s linear infinite;
    }
    .cloud2 {
      top: 87px;
      right: 20px;
      animation: cloud2 19s linear infinite;
    }
    .cloud3 {
      top: 160px;
      left: 5px;
      transform: scale(0.8);
      animation: cloud3 21s linear infinite;
    }
    .cloud4 {
      top: 150px;
      left: -40px;
      transform: scale(0.4);
      animation: cloud4 19s linear infinite;
    }
  }
  .log-logo {
    height: 80px;
    margin: 120px auto 25px;
    text-align: center;
    color: @color_1;
    font-weight: bold;
    font-size: 40px;
    z-index: 2;
  }
  .log-text {
    color: @color_2;
    font-size: 13px;
    text-align: center;
    margin: 0 auto;
    z-index: 2;
  }
  .close {
    height: 16px;
    width: 16px;
    background-position: -13px 0;
  }
  .log-btn {
    width: 402px;
    display: block;
    text-align: center;
    line-height: 50px;
    margin: 0 auto 15px;
    height: 50px;
    color: @color_3;
    font-size: 13px;
    -webkit-border-radius: 5px;
    background-color: @background_color_3;
    -moz-border-radius: 5px;
    -ms-border-radius: 5px;
    -o-border-radius: 5px;
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
  .log-btn.tw {
    background-color: @background_color_3;
  }
  .isloading {
    background: #d6d6d6;
  }
}
</style>
