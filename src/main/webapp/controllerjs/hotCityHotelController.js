/**
 * Created by apple on 15/3/30.
 */

var myAppModule = angular.module("myApp",[])


/**
 * 主页控制器
 */
myAppModule.controller("cityHotelController",
    function($scope,$http){

        $scope.hotCityList = {};

        $scope.conditionData = {};
        $scope.conditionData.cityId = "";
        $scope.conditionData.comeDate = "";
        $scope.conditionData.leaveDate = "";

        //加载 热门城市 List
        $http.get("/hotel/hotelSearch/showHotCityList.html").success(

            function(result){
                $scope.hotCityList = result;

                //onload select the first hot city
                $scope.changeCityForHot(0);

                $scope.changeCityForCheap(0);

            }

        );

        //热门城市List 点击事件 加载城市的热门Branch List
        $scope.changeCityForHot = function(index){
            var city = $scope.hotCityList[index];
            var cityId = city.cityId;

            if(cityId==undefined || cityId < 0)
                return;

            $http.get("/hotel/hotelSearch/showBranchListByCity.html?cityId="+cityId).success(
                function(result){
                    $scope.hotBranchList = result;
                }
            );

            $scope.selectedHotCity = index;

        };



        $scope.changeCityForCheap = function(index){
            var city = $scope.hotCityList[index];
            var cityId = city.cityId;

            if(cityId==undefined || cityId < 0)
                return;

            $http.get("/hotel/hotelSearch/showCheapBranchListByCity.html?cityId="+cityId).success(
                function(result){
                    $scope.cheapBranchList = result;
                }
            );

            $scope.selectedCheapCity = index;

        }

        $scope.cityPanelShow = false;

        $scope.cityPanelToggle = function(){

            $scope.cityPanelShow = !$scope.cityPanelShow;

        }



        $scope.letters = {};
        $scope.letterCityList = {};
        $scope.letterCityList.letters = new Array();
        $scope.letterCityList.citys = new Array();

        $scope.setLetters = function(letters){
            $scope.letters = letters;
            $scope.letterCityList = {};
            $scope.letterCityList.letters = new Array();
            $scope.letterCityList.citys = new Array();

            for(var i=0;i<letters.length;i++){
                $scope.letterCityList.letters.push(letters[i]);
                $http.get("/hotel/hotelSearch/showCityList.html?firstLetter="+letters[i]).success(
                    function(list){
                        $scope.letterCityList.citys.push(list);
                    });

            }

        }

    });


/**
 * 搜索结果页面控制器
 */
myAppModule.controller("searchPageController",
    function($scope,$http){

        $scope.basePath = "";

        $scope.conditionData = {};
        $scope.conditionData.cityId = "";
        $scope.conditionData.comeDate = "";
        $scope.conditionData.leaveDate = "";
        $scope.conditionData.priceLow = "";
        $scope.conditionData.priceHigh = "";
        $scope.conditionData.roomTypeId = "";
        
        alert($scope.conditionData.cityId);

        $http.get("/hotel/hotelSearch/showRoomType.html?cityId="+$scope.conditionData.cityId).success(
            function(result){
                $scope.roomTypeList = result;
            }
        );


        $scope.searchFormSubmit = function(){
            var path = "/hotel/hotelSearch/showHotelListBySearchCondition.html?" +
                "cityId="+$scope.conditionData.cityId +
                "&&roomTypeId="+$scope.conditionData.roomTypeId +
                "&&comeDate="+$scope.conditionData.comeDate +
                "&&leaveDate="+$scope.conditionData.leaveDate +
                "&&priceLow="+$scope.conditionData.priceLow +
                "&&priceHigh="+$scope.conditionData.priceHigh;

            alert(path);

            $http.get(path).success(function(result){
                $scope.count = result.count;
                $scope.hotelList = result.list;
            })

        }

        $scope.setPrice = function(priceLow,priceHigh){

            $scope.conditionData.priceLow = priceLow;
            $scope.conditionData.priceHigh = priceHigh;
            $scope.searchFormSubmit();

        }

        $scope.setType = function(index){

            $scope.conditionData.roomTypeId = $scope.roomTypeList[index].roomTypeId;
            $scope.searchFormSubmit();

        }

        $scope.goDetailPage = function(branchId){
            $scope.conditionData.branchId = branchId;

            var path =  $scope.basePath+"hotelDetail/hotelDetailPage.html?" +
                    "comeDate="+$scope.conditionData.comeDate +
                    "&leaveDate="+$scope.conditionData.leaveDate +
                    "&branchId=" + $scope.conditionData.branchId
            //"&&priceLow="+$scope.conditionData.priceLow +
            //"&&priceHigh="+$scope.conditionData.priceHigh +
            //"&&cityId="+$scope.conditionData.cityId +
            //"&&roomTypeId="+$scope.conditionData.roomTypeId
                ;

                alert("goDetailPage: " + path);

            window.location = path;

        }
    });


/**
 * 分店信息页面控制器
 */
myAppModule.controller("hotelDetailController",
    function($scope,$http){

        $scope.basePath = "";

        $scope.branchId = "";

        $scope.roomTypeList = {};

        $scope.condition = {};
        $scope.condition.comeDate = "";
        $scope.condition.leaveDate = "";

        $scope.isReady = false;

        $scope.checkReady = function(){

            $scope.isReady = $scope.condition.comeDate==""||$scope.condition.leaveDate == ""?false:true;


        }

        $scope.setReady = function(){

            $scope.isReady = true;

        }

        $scope.cancelReady = function(){

            $scope.isReady = false;

        }


        $scope.showRoomTypeList = function(){
            alert($scope.branchId);
            var path = "/hotel/hotelDetail/showRoomTypeList.html?" +
                "branchId="+$scope.branchId +
                "&comeDate=" + $scope.condition.comeDate +
                "&leaveDate=" + $scope.condition.leaveDate;


                alert("showRoomTypeList:" + path);

            $http.get(path
            ).success(
                function(result){

                    $scope.roomTypeList = result;

                })

        }


        $scope.reservePage = function(roomTypeId){

            $scope.condition.roomTypeId = roomTypeId;

            var path = $scope.basePath+"hotelDetail/reservePage.html?" +
                "roomTypeId=" + roomTypeId +
                "&branchId=" + $scope.branchId +
                "&comeDate=" + $scope.condition.comeDate +
                "&leaveDate=" + $scope.condition.leaveDate;

            alert("reservePage: "+path);

            window.location = path;

        }

    });


/**
 * 预定页面控制器
 */
myAppModule.controller("reservePageController",
    function($scope,$http){

        $scope.basePath = "";

        $scope.reserveInfo = {};
        $scope.reserveInfo.branchId = "";
        $scope.reserveInfo.roomTypeId = "";
        $scope.reserveInfo.comeDate = "";
        $scope.reserveInfo.leaveDate = "";
        $scope.reserveInfo.roomCount = 1;
        $scope.reserveInfo.totalPrice = 0;
        $scope.reserveInfo.name = "";
        $scope.reserveInfo.tel = "";
        $scope.reserveInfo.latestTime = "";

        $scope.setTotalPrice = function(){


            var path = "/hotel/reserve/totalPrice.html?" +
                "roomTypeId="+$scope.reserveInfo.roomTypeId +
                "&comeDate="+$scope.reserveInfo.comeDate +
                "&leaveDate="+$scope.reserveInfo.leaveDate;

            $http.get(path).success(
                function(price){
                    $scope.reserveInfo.totalPrice = price * $scope.reserveInfo.roomCount;
                });

        }



        $scope.showBranchInfo = function(){

            var path = "/hotel/"

            $http.get()

        }

        $scope.submitReserve = function(){

            var path = "/hotel/reserve/reserve.html?" +
                "branchId=" + $scope.reserveInfo.branchId +
                "&roomTypeId=" + $scope.reserveInfo.roomTypeId +
                "&comeDate=" + $scope.reserveInfo.comeDate +
                "&leaveDate=" + $scope.reserveInfo.leaveDate +
                "&roomCount=" + $scope.reserveInfo.roomCount +
                "&totalPrice=" + $scope.reserveInfo.totalPrice +
                "&name=" + $scope.reserveInfo.name +
                "&tel=" + $scope.reserveInfo.tel +
                "&latestTime=" + $scope.reserveInfo.latestTime;

            alert(path);

            $http.get(path).success(function(result){
                alert(result);
            });


        }


    })


/**
 * 个人中心控制器
 */
myAppModule.controller("personInfoController",
    function($scope,$http){
        $scope.personInfo = {};
        $scope.personInfo.name = "";
        $scope.personInfo.idNumber = "";
        $scope.personInfo.sex = "";
        $scope.personInfo.province = "";
        $scope.personInfo.city = "";

        $scope.updatePersonInfo = function(){
            alert("update");
            var path = "/hotel/person/update.html?" +
                "name=" + $scope.personInfo.name +
                "&idNumber=" + $scope.personInfo.idNumber +
                "&sex=" + $scope.personInfo.sex +
                "&province=" + $scope.personInfo.province +
                "&city=" + $scope.personInfo.city;

            $http.get(path).success(function(result){
                if(result){
                    alert("更新成功！");
                }else{
                    alert(result);
                }
            });
        }

        $scope.cancelReserve = function(reserveId){
            if(confirm("确定要取消订单嘛？")){
                $http.get("/hotel/person/cancelReserve.html?reserveId="+reserveId).success(function(result){

                    if(result){
                        location.reload();
                    }else{
                        alert("fail..");
                    }
                })
            };
        }
    })


/**
 * 登录控制器
 */
myAppModule.controller("loginController",function($scope,$http) {

    $scope.login = {};
    $scope.login.account = "";
    $scope.login.pwd = "";
    $scope.login.error = false;
    $scope.login.status = false;
    $scope.member = {};

    $scope.loginSubmit = function () {
        $http.get("/hotel/login/check.html?account=" + $scope.login.account + "&pwd=" + $scope.login.pwd).success(
            function (data) {
                var result = data.result;

                if (result) {
                    $('.myModal').modal('hide');
                    $scope.login.status = true;
                    $scope.member.name = data.name;
                } else {
                    $scope.login.error = true;
                }

            });
    }

    $scope.logout = function () {
        $scope.login.status = false;
        $http.get("/hotel/login//logout.html");
    }
})
