
actionApp.controller('View1Controller', ['$rootScope', '$scope', '$http', function($rootScope, $scope,$http) {
    $scope.$on('$viewContentLoaded', function() {
    	console.log('页面加载完成');
    });
    
    
    $scope.search = function(){
      personName = $scope.personName;
      $http.get('search',{
    	  params:{personName:personName}
      }).success(function(data){
    	 $("#fuck").val(data.name + "\n\n去重后有  " + data.amount + " 个字符串"); 
      });;
     
    };
}]);

actionApp.controller('View2Controller', ['$rootScope', '$scope',  function($rootScope, $scope) {
    $scope.$on('$viewContentLoaded', function() {
    	console.log('页面加载完成');
    });
    
    $(window).on('load', function () {
        $('#usertype').selectpicker({
            'selectedText': 'cat'
        });
    });
}]);


