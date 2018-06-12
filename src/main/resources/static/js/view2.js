/**
 * 在view2.html中应用的js
 */

    var selectNode = 0;//全局变量
    var selectNodeAttr="";//全局变量
    
    
    function createTree(treeId){ //创建接口Tree函数
        var zTree; //用于保存创建的树节点
        var setting = { //设置
            check: {
                enable: true
            },
            view: {
                showLine: true, //显示辅助线
                dblClickExpand: true,
            },
            data: {
                simpleData: {
                    enable: true,  //确定 zTree 初始化时的节点数据、异步加载时的节点数据、或 addNodes 方法中输入的 newNodes 数据是否采用简单数据模式 (Array)
                    idKey: "id",  //节点数据中保存唯一标识的属性名称
                    pIdKey: "pid",//节点数据中保存其父节点唯一标识的属性名称
                    rootPId: 0   //用于修正根节点父节点数据，即 pIdKey 指定的属性值
                }
            },
            callback:{
            	onClick:zTreeOnClick
            }
            
        };
        /*$.ajax({ //请求数据,创建树
            type: 'GET',
            url: "init/getNodes",
            async:false,
            dataType: "json", //返回的结果为json  
            success: function(data) {
            	if(data.indexOf("初始化")==0){
            		alert(data);
            		return;
            	}
                zTree = $.fn.zTree.init($(treeId), setting, data); //创建树
            },
            error: function(data) {
            	//alert("请求出错");
            	//console.log(data);
            }
       }); */
        $.getJSON("init/getNodes",function(data){
        	if(data.info != null){
        		alert(data.info);
        		return;
        	}
        	zTree = $.fn.zTree.init($(treeId), setting, data); //创建树
        	
        })
       
    }
      /*点击Ztree节点事件*/
     function zTreeOnClick(event, treeId, treeNode) {
          //alert(treeNode.attr);
          //读取该接口的请求参数
          var interFaceName = treeNode.attr;
          selectNode = treeNode.rowNum;
          selectNodeAttr = treeNode.attr;
          if(interFaceName!=null && interFaceName!=""){
        	  var url = "requestDto/getBody";
              $.post(url,{"interfaceName":interFaceName},function(params){
             	 $("#requestParam").val(params); 
              })
          }
          else
        	  $("#requestParam").val("");
     }
     
     
     
     /*点击"复制返回报文"按钮，复制文本域内容到剪切板*/
   function jsCopyResPon(){   
        var e=document.getElementById("requestParam");//对象是contents   
        e.select(); //选择对象   
        tag=document.execCommand("Copy"); //执行浏览器复制命令  
        if(!tag){  
          alert('复制失败');  
        }  
    }
    
    /*清空文本域，进度条归0*/
    function clearResPon(){
		$("#returnText").val("");
		returnZero();
	 }
    
    /*采用递归的方法进行循环ajax调用*/
    function Loop_ajax(index, array) {
    	
        if (index < array.length) {
            var param = array[index];
            var cont = $("#returnText").val();
            $.post("test/testBatchApi", {"param": param }, function (data) {
           	 $("#returnText").val(cont + "\n****************************************************************\n"  + data);
                console.log(array[index] + "," + data);
                run(100/array.length,false); 
                if (index < array.length) {
                    Loop_ajax(index + 1, array);                    
                }
            });
        }
        else
       	 run(100/array.length,true); 
    }
    
    /*进度条归0*/
    function returnZero(){
    	var bar = document.getElementById("bar"); 
    	bar.style.width = 0;
    }
    
    /*文档加载完后激活后要做的*/
    $(document).ready(function() {
        createTree("#treeDemo"); //创建接口ztree
        $("#testing").hide();
        $("#startTest").click(function() {
        	returnZero();
            var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            var nodes = treeObj.getCheckedNodes(true);
            if(0 === nodes.length) {
                alert("请选择一个或多个接口!");
                return;
            }
            
            var dataNodes = "";
            for(var i = 0; i < nodes.length; i++) {
                if(nodes[i].attr!=""){
                	if(i !=nodes.length-1)
                		dataNodes += nodes[i].attr + ",";
                	else
                		dataNodes += nodes[i].attr;
                }
            }
           //// alert(dataNodes); //获取选中节点的值
            var array = dataNodes.split(",");
            $("#testing").show();
            Loop_ajax(0, array);
            
        });
    });
    
    /*监听请求参数textArea中的变化*/
    $('#requestParam').bind('input',function(){  
    	saveRequParam();   
    });
    
    /*把excel中心加的接口信息更新到ztree*/
    function updateRequParam(){
  	  $.get("ztree/updateNodes",function(status){
  		  //刷新当前页面
  		  if(status != "ok"){
  			alert(status);
  			return;
  		  }
  		  window.location.reload();
  	  })
    }
    
    /*请求参数文本域点击保存*/
    function saveRequParam(){
  	  /* $("#requestParam").attr("readonly","readonly"); */
  	  if(selectNode == 0 || selectNodeAttr=="")
  		  return;
  	  var params = $("#requestParam").val();
  	  $.post("requestDto/updateBody",{"params":params,"rowNum":selectNode,"attrKey":selectNodeAttr},function(status){
  	     // alert(status);
  		  console.log(status);
  	  })
    }
    
	 function run(percent,isOver){ 
		var bar = document.getElementById("bar"); 
		if(isOver==false)
		    bar.style.width=parseInt(bar.style.width) + percent + "%"; 
		else{
			bar.style.width="100%"; 
		    $("#testing").hide();
		}
	  }  