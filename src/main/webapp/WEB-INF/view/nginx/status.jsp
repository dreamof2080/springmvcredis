<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
  <title>NginxStatus</title>
  <link rel="stylesheet" type="text/css" href="/css/nginx/line.css" />
  <link rel="stylesheet" type="text/css" href="/plugin/bootstrap-3.3.7-dist/css/bootstrap.css" />
  <link rel="stylesheet" type="text/css" href="/plugin/bootstrap-3.3.7-dist/css/bootstrap-theme.css" />
  <link href="https://cdn.bootcss.com/toastr.js/latest/css/toastr.min.css" rel="stylesheet">
  <script src="/js/echarts/echarts.min.js"></script>
  <script src="/js/jquery/jquery-3.2.1.min.js"></script>
  <script src="/plugin/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
  <script src="https://cdn.bootcss.com/toastr.js/latest/js/toastr.min.js"></script>
</head>
<body>

  <div class="header">
    <h3 class="text-center">OA连接数监控</h3>
  </div>
  <div class="text-center" data-toggle="collapse" href="#collapseOne">
    <span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"></span>
  </div>

  <div id="collapseOne" class="accordion-body collapse" style="height: 0px;" showCollapseButton="true">
    <form class="form-inline">
      <div class="form-group">
        <label for="beginDate">开始日期</label>
        <input type="date" class="form-control" id="beginDate" placeholder="Begin Date">
      </div>
      <div class="form-group">
        <label for="endDate">结束日期</label>
        <input type="date" class="form-control" id="endDate" placeholder="End Date">
      </div>
      <input class="btn btn-info" type="button" value="查询" onclick="showData();">
    </form>
  </div>
  <div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog modal-lg" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="myModalLabel">Modal title</h4>
        </div>
        <div class="modal-body" id="modal-body">

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary">Save changes</button>
        </div>
      </div>
    </div>
  </div>

  <div id="main"></div>


<script>
  // 消息提示框初始化
  toastr.options.positionClass = 'toast-bottom-right';
  var myChart = echarts.init(document.getElementById('main'));
  function getData() {
    var url = 'http://oa.bestlink.com.cn:8088/status';
    var htmlobj = $.ajax({url: '/nginxInfo/getStatus',data:{url:url},dataType:'json', async: false});
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
      text: '',
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
      name: '',
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


  var myChart2 = echarts.init(document.getElementById('modal-body'));
  function showData() {
    if(!$('#beginDate').val()){
      toastr.warning('请填写开始日期');
      return;
    }
    if(!$('#endDate').val()){
      toastr.warning('请填写结束日期');
      return;
    }

    myChart2.setOption(option);
    var htmlobj = $.ajax({url: '/nginxInfo/getStatusList',dataType:'json', async: false});
    var json = JSON.parse(htmlobj.responseText);
    var timeData_tmp = json.timeData;
    var lineData_tmp = json.lineData;
    console.log(lineData_tmp);
    myChart2.setOption({
      xAxis:[{
        data:timeData_tmp
      }],
      series: [{
        data: lineData_tmp
      }]
    });
    $('#modal').modal('toggle');
  }

</script>
</body>
</html>
