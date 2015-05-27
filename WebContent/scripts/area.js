//取所有省份
function getArea(pcd,code,selcode){
var url='selectprovince.asp';
if(pcd=='province'){
url='selectprovince.asp';
}else{
if(pcd=='city')
{
url='selectcity.asp?code='+code;
}else{
if(pcd=='district'){
url='selectdistrict.asp?code='+code;
}
}
}
$.ajax({
url:url,
data:'',
type:'get',
dataType:'json',
contentType:'application/json;charset=utf-8',
cache:false,
success:function(data) {setArea(data,pcd,selcode);},
error:function(xhr) { alert('失败');}
 }); 
}
//设置省份
function setArea(array,pcd,selcode){
var pSelect = document.getElementById("selprovince");
var cSelect = document.getElementById("selcity");
var dSelect = document.getElementById("seldistrict");
if(pcd=='province'){
clearSel(pSelect);
 var option = new Option('请选择省份...','0');
	pSelect.options.add(option);
for ( var i = 0; i < array.length; i++) {
 var option = new Option(array[i].provincename,array[i].provincecode);
 if(selcode!=''&selcode==array[i].provincecode){
	 option.selected="selected";
 }
	pSelect.options.add(option);
}
}else{
if(pcd=='city')
{
cSelect.disabled=false;
clearSel(cSelect);
 var option = new Option('请选择城市...','0');
	cSelect.options.add(option);
	for ( var i = 0; i < array.length; i++) {
 var option = new Option(array[i].cityname,array[i].citycode);
 if(selcode!=''&selcode==array[i].citycode){
	 option.selected="selected";
 }
	cSelect.options.add(option);
}
}else{
if(pcd=='district'){
dSelect.disabled=false;
clearSel(dSelect);
 var option = new Option('请选择区县...','0');
	dSelect.options.add(option);
	for ( var i = 0; i < array.length; i++) {
 var option = new Option(array[i].districtname,array[i].districtcode);
 if(selcode!=''&selcode==array[i].districtcode){
	 option.selected="selected";
 }
	dSelect.options.add(option);
	}
}
}
}
}

function clearSel(oSelect){

while(oSelect.childNodes.length>0){
oSelect.removeChild(oSelect.childNodes[0]);
}
}