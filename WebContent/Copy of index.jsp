<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<html>
  <head>
    <title>首页</title>
      <%@ include file="/common/taglibs.jsp"%>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<!--引用百度地图API-->
<style type="text/css">
html,body {
	margin: 0;
	padding: 0;
}

.iw_poi_title {
	color: #CC5522;
	font-size: 14px;
	font-weight: bold;
	overflow: hidden;
	padding-right: 13px;
	white-space: nowrap
}

.iw_poi_content {
	font: 12px arial, sans-serif;
	overflow: visible;
	padding-top: 4px;
	white-space: -moz-pre-wrap;
	word-wrap: break-word
}
</style>
<!--9E5638201ce85c2da57767f827d670c6 sn  
<script type="text/javascript" src="http://api.map.baidu.com/api?key=&v=1.1&services=true"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/CurveLine/1.5/src/CurveLine.min.js"></script>弧线
-->
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=jUFIR1ob4k9Ur8v8zzXLGdFM"></script>

	<script language="javascript" type="text/javascript" src="scripts/CurveLine.p6.js" ></script>
</head>
<!-- http://api.map.baidu.com/geocoder/v2/?ak=jUFIR1ob4k9Ur8v8zzXLGdFM&callback=renderOption&output=xml
&address=荣丰小区&city=北京 根据地名获取坐标-->
<body>
	<!--百度地图容器-->
	<table style="height: 100%;width: 100%">
		<tr>
			<td width="78%">
				<div style="width:100%;height:99%;border:#ccc solid 1px;"
					id="dituContent"></div></td>
			<td width="1%"></td>
			<td width="21%">
			<div class="optionpanel">
					<fieldset>
						<legend>个性化底图设置区域</legend>

						<label>选择主题</label> <select id="stylelist"
							onchange="changeMapStyle(this.value)">
							<option value="default">默认地图样式</option>
							<option value="light">清新蓝风格</option>
							<option value="dark">黑夜风格</option>
							<option value="redalert">红色警戒风格</option>
							<option value="googlelite">精简风格</option>
							<option value="grassgreen">自然绿风格</option>
							<option value="midnight">午夜蓝风格</option>
							<option value="pink">浪漫粉风格</option>
							<option value="darkgreen">青春绿风格</option>
							<option value="bluish">清新蓝绿风格</option>
							<option value="grayscale">高端灰风格</option>
							<option value="hardedge">强边界风格</option>
						</select>
						<p id="desc">地图背景及道路均呈黑灰色，给人以寂静的感觉。</p>
					</fieldset>
				</div>
					<br />	<br />
			<a href="#" onclick="addCurveline();">一号线</a><br />
			<br />
			<br /> <a href="#" onclick="addMarker();">一号线</a><br />
			<br />
			<br /> <a href="#" onclick="addMarker();addPolyline();">一号线</a><br />
			<br />
			<br /> <a href="#" onclick="addMarker();addPolyline();">一号线</a><br />
			<br />
			<br /> <a href="#" onclick="addMarker();addPolyline();">一号线</a><br />
			<br />
			<br /> <a href="#" onclick="addMarker();addPolyline();">一号线</a><br />
			<br />
			<br /> <a href="#" onclick="addMarker();addPolyline();">一号线</a><br />
			<br />
			<br />
				
		<div class="optionpanel">
					<fieldset>
						<legend>个性化底图设置区域</legend>

					<a href="getbox.asp">提取*查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="register.asp">注册*开户</a>
					</fieldset>
				</div>			

<!-- <input type="month" name="user_date" />
<input type="range" name="points" min="1" max="10" />
E-mail: <input type="email" name="user_email" /> -->
</td>
		</tr>
	</table>


</body>

<script type="text/javascript">
function checkhHtml5()   
        {   
            if (typeof(Worker) === "undefined")     
            {   
                if(navigator.userAgent.indexOf("MSIE 9.0")<=0)  
				   {  
				 	 alert("定制个性地图示例：IE9以下不兼容，推荐使用百度浏览器、chrome、firefox、safari、IE10");   
				   }  
                
            }  
        }
 checkhHtml5();  
 
 
 
 
 
    //创建和初始化地图函数：
    function initMap(){
        createMap();//创建地图
        setMapEvent();//设置地图事件
        addMapControl();//向地图添加控件
        //addMarker();//向地图中添加marker
        //addPolyline();//向地图中添加线
        addRemark();//向地图中添加文字标注
    }

    //创建地图函数：
    function createMap(){
        var map = new BMap.Map("dituContent");//在百度地图容器中创建一个地图
        var point = new BMap.Point(105.750797,37.110076);//定义一个中心点坐标
        map.centerAndZoom(point,5);//设定地图的中心点和坐标并将地图显示在地图容器中
        window.map = map;//将map变量存储在全局
        var  mapStyle ={ 
       /* features: ["road", "building","water","land"],//隐藏地图上的poi  */
        style : "googlelite"  //设置地图风格为x色
    }
	map.setMapStyle(mapStyle);

    }
    
    
  
    
    
    //地图事件设置函数：
    function setMapEvent(){
       // map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
        map.enableScrollWheelZoom();//启用地图滚轮放大缩小
        map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
        map.enableKeyboard();//启用键盘上下左右键移动地图
    }
    
    //地图控件添加函数：
    function addMapControl(){
         /*   //向地图中添加缩放控件
	var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
	map.addControl(ctrl_nav);
     //向地图中添加缩略图控件
	var ctrl_ove = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:1});
	map.addControl(ctrl_ove); */
        //向地图中添加比例尺控件
	var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
	map.addControl(ctrl_sca);
    }
    
    //标注点数组"106.081948|29.786995","90.848986|29.786995","103.653505|36.169769","113.440867|34.906633","112.26344|38.113769"
    var markerArr = [
     {title:"起点站",content:"<a href='http://www.wohaizai.com'>唐季礼</a><br/>历时4天",point:"106.376305|29.9153",isOpen:0,icon:{}}
    ,{title:"第二站",content:"<a href='http://www.wohaizai.com'>李晓雅</a>",point:"90.848986|29.786995",isOpen:0,icon:{}}
    ,{title:"第三站",content:"<a href='http://www.wohaizai.com'>唐马儒</a>",point:"103.653505|36.169769",isOpen:0,icon:{}}
    ,{title:"第四站",content:"<a href='http://www.wohaizai.com'>马儒</a>",point:"113.990867|34.500000",isOpen:0,icon:{}}
    ,{title:"第五站-已发出",content:"<a href='http://www.wohaizai.com'>周晓宇</a>",point:"112.26344|38.113769",isOpen:1,icon:{w:23,h:25,l:0,t:21,x:9,lb:12}}
    ,{title:"第六站",content:"<a href='http://www.wohaizai.com'>李连杰</a>",point:"113.790867|34.700000",isOpen:0,icon:{}}
	,{title:"终点站",content:"<a href='http://www.wohaizai.com'>李连杰</a>",point:"103.947862|30.872245",isOpen:0,icon:{}}
		 ];
/* 		     //标注点数组
    var markerArr = [
          {title:"我的标1记1",content:"我的备注",point:"116.335566|39.957866",isOpen:0,icon:{w:21,h:21,l:0,t:46,x:1,lb:10}}--绿旗
		 ,{title:"我的标记2",content:"我的备注",point:"116.396507|39.960078",isOpen:0,icon:{w:21,h:21,l:46,t:46,x:1,lb:10}}---红旗
		 ,{title:"我的标记3",content:"我的备注",point:"116.340166|39.933084",isOpen:0,icon:{w:23,h:25,l:46,t:21,x:9,lb:12}} --红心 持有中
		 ,{title:"我的标记3",content:"我的备注",point:"116.340166|39.933084",isOpen:0,icon:{w:23,h:25,l:0,t:21,x:9,lb:12}} --绿心  已发往下一站
		 ,{title:"我的标记4",content:"我的备注",point:"116.388459|39.935297",isOpen:0,icon:{w:21,h:21,l:46,t:0,x:6,lb:5}}--红钉子  坐标相同  x减小0.2 y加大0.2
		 ]; 
		 输入电话号查询所属路线
		 */
    //创建marker
    function addMarker(){


        for(var i=0;i<markerArr.length;i++){
            var json = markerArr[i];
            var p0 = json.point.split("|")[0];
            var p1 = json.point.split("|")[1];
            var point = new BMap.Point(p0,p1);
			var iconImg = createIcon(json.icon);
            var marker = new BMap.Marker(point,{icon:iconImg});
			var iw = createInfoWindow(i);
			var label = new BMap.Label(json.title,{"offset":new BMap.Size(json.icon.lb-json.icon.x+10,-20)});
			marker.setLabel(label);
            map.addOverlay(marker);
            label.setStyle({
                        borderColor:"#808080",
                        color:"#333",
                        cursor:"pointer"
            });
			
			(function(){
				var index = i;
				var _iw = createInfoWindow(i);
				var _marker = marker;
				_marker.addEventListener("click",function(){
				    this.openInfoWindow(_iw);
			    });
			    _iw.addEventListener("open",function(){
				    _marker.getLabel().hide();
			    })
			    _iw.addEventListener("close",function(){
				    _marker.getLabel().show();
			    })
				label.addEventListener("click",function(){
				    _marker.openInfoWindow(_iw);
			    })
				if(!!json.isOpen){
					label.hide();
					_marker.openInfoWindow(_iw);
				}
			})()
        }
    }
    //创建InfoWindow
    function createInfoWindow(i){
        var json = markerArr[i];
        var iw = new BMap.InfoWindow("<b class='iw_poi_title' title='" + json.title + "'>" + json.title + "</b><div class='iw_poi_content'>"+json.content+"</div>");
        return iw;
    }
    //创建一个Icon
    function createIcon(json){
        var icon = new BMap.Icon("http://app.baidu.com/map/images/us_mk_icon.png", new BMap.Size(json.w,json.h),{imageOffset: new BMap.Size(-json.l,-json.t),infoWindowOffset:new BMap.Size(json.lb+5,1),offset:new BMap.Size(json.x,json.h)})
        return icon;
    }
//标注线数组
    var plPoints = [{style:"solid",weight:3,color:"#ff6",opacity:1.1,points:["106.081948|29.786995","90.848986|29.786995","103.653505|36.169769","113.990867|34.500000"]}
		 ,{style:"solid",weight:3,color:"#f00",opacity:1.1,points:["113.990867|34.500000","112.26344|38.113769","113.790867|34.700000","103.947862|30.872245","109.79389|39.668778","90.848986|29.786995","116.34261527888|39.900287543513"]}
		 ];
    //向地图中添加折线函数
    function addPolyline(){
		for(var i=0;i<plPoints.length;i++){
			var json = plPoints[i];
			var points = [];
			for(var j=0;j<json.points.length;j++){
				var p1 = json.points[j].split("|")[0];
				var p2 = json.points[j].split("|")[1];
				points.push(new BMap.Point(p1,p2));
			}
			var line = new BMap.Polyline(points,{strokeStyle:json.style,strokeWeight:json.weight,strokeColor:json.color,strokeOpacity:json.opacity});
			map.addOverlay(line);
		}
	}
	 //向地图中添加弧线函数
    function addCurveline(){
		map.enableScrollWheelZoom();// 百度地图API功能
		for(var i=0;i<plPoints.length;i++){
			var json = plPoints[i];
			var points = [];
			for(var j=0;j<json.points.length;j++){
				var p1 = json.points[j].split("|")[0];
				var p2 = json.points[j].split("|")[1];
				points.push(new BMap.Point(p1,p2));
			}
			var curve = new BMapLib.CurveLine(points,{strokeStyle:json.style,strokeColor:json.color, strokeWeight:json.weight, strokeOpacity:json.opacity}); //创建弧线对象
			map.addOverlay(curve); //添加到地图中
			curve.enableEditing(); //开启编辑功能
		}
	}
		
//文字标注数组
 /*    var lbPoints = [{point:"106.523483|30.808744",content:"NO.1"}
		 ]; */
    //向地图中添加文字标注函数
    function addRemark(){
        for(var i=0;i<lbPoints.length;i++){
            var json = lbPoints[i];
            var p1 = json.point.split("|")[0];
            var p2 = json.point.split("|")[1];
            var label = new BMap.Label("<div style='padding:2px;'>"+json.content+"</div>",{point:new BMap.Point(p1,p2),offset:new BMap.Size(3,-6)});
            map.addOverlay(label);
            label.setStyle({borderColor:"#999"});
        }
    }
    
    initMap();//创建和初始化地图
</script>
</html>