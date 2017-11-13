<%--
  Created by IntelliJ IDEA.
  User: dream
  Date: 2017-11-13
  Time: 22:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>NginxStatus</title>
  <script src="/js/echarts/echarts.min.js"></script>
  <script src="/js/jquery/jquery-3.2.1.min.js"></script>
</head>
<body>
<div id="main" style="width: 100vw;height:90vh;"></div>
<script>
  var myChart = echarts.init(document.getElementById('main'));
  function getData() {
    var url = "http://oa.bestlink.com.cn:8088/status";
    var htmlobj = $.ajax({url: "/nginxInfo/getStatus",data:{url:url},dataType:"json", async: false});
    var json = JSON.parse(htmlobj.responseText);
    var total = json.total;
    var time = json.time;
    if(!total){
      total = 0;
    }
    timeData.push(time);
    lineData.push(total);
  }
  var timeData = [];
  var lineData = [];
  option = {
    title: {
      text: 'Nginx连接数监控',
      x: 'left'
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        animation: false
      }
    },
    legend: {
      data: ['连接数'],
      x: 'left'
    },
    toolbox: {
      feature: {
        dataZoom: {
          yAxisIndex: 'none'
        },
        restore: {},
        saveAsImage: {}
      }
    },
    axisPointer: {
      link: {
        xAxisIndex: 'all'
      }
    },
    dataZoom: [{
      show: true,
      realtime: true,
      start: 0,
      end: 100,
      xAxisIndex: [0, 1]
    }, {
      type: 'inside',
      realtime: true,
      start: 0,
      end: 100,
      xAxisIndex: [0, 1]
    }],
    grid: [{
      left: 40,
      right: 40
    }, {
      left: 40,
      right: 40
    }],
    xAxis: [{
      type: 'category',
      boundaryGap: false,
      axisLine: {
        onZero: true
      },
      data: timeData
    }, {
      gridIndex: 1
    }],
    yAxis: [{
      type: 'value',
      max: 2000,
      name: '单位:个',
      min: 0,
      interval: 100
    }, {
      gridIndex: 1
    }],
    series: [{
      name: '数值',
      type: 'line',
      smooth: true,
      symbol: 'circle',
      symbolSize: 9,
      showSymbol: false,
      lineStyle: {
        normal: {
          width: 1
        }
      },
      markPoint: {
        data: [{
          type: 'max',
          name: '最大值'
        }, {
          type: 'min',
          name: '最小值'
        }]
      },
      markArea: {
        silent: true,
        label: {
          normal: {
            position: ['10%', '50%']
          }
        },
        data: [
          [{
            name: '',
            yAxis: 500,
            itemStyle: {
              normal: {
                color: 'rgba(250,250,51,0.2)'
              }
            }
          }, {
            yAxis: 1000
          }],
          [{
            name: '',
            yAxis: 1000,
            itemStyle: {
              normal: {
                color: 'rgba(153,204,51,0.2)'
              }
            }
          }, {
            yAxis: 1500
          }],
          [{
            name: '',
            yAxis: 1500,
            itemStyle: {
              normal: {
                color: 'rgba(0,153,153,0.27)'
              }
            }
          }, {
            yAxis: 2000
          }]
        ]
      },
      data: lineData
    }]
  };
  myChart.setOption(option);
  setInterval(function () {
    //只保留一小时类的数据:60*60/5=720
    if(timeData.length>720){
      timeData.shift();
      lineData.shift();
    }
    //动态获取数据
    getData();
    //重设图标数据
    myChart.setOption({
      xAxis:[{
        data:timeData
      }],
      series: [{
        data: lineData
      }]
    });
  }, 5000);
</script>
</body>
</html>
