//创建地图函数： divId: 用来显示的容器Id，longitude 经度，latitude 纬度
function createMap(divId,latitude,longitude,zoomSize){
    var mymap = new BMap.Map(divId);
    var point = new BMap.Point(latitude,longitude);//定义一个中心点坐标
    mymap.centerAndZoom(point,zoomSize);//设定地图的中心点和坐标并将地图显示在地图容器中
    var marker = new BMap.Marker(point);        // 创建标注    
    mymap.addOverlay(marker);  
    return mymap;
}

//创建和初始化地图函数：
function initMap(dituContent,latitude,longitude){
    return createMap(dituContent,latitude,longitude,19);
}

//向地图中添加缩放控件
function addNavigationControll(map){
    var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
    map.addControl(ctrl_nav);
}

//向地图中添加缩略图控件
function addOverviewMapControll(map){
    var ctrl_ove = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:1});
    map.addControl(ctrl_ove);
}

//向地图中添加比例尺控件
function addOverviewMapControll(map){
    var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
    map.addControl(ctrl_sca);
}