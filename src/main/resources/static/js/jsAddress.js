// 纯JS省市区三级联动  
// 2011-11-30 by http://www.cnblogs.com/zjfree  
var addressInit = function(_cmbProvince, _cmbCity, _cmbArea, defaultProvince, defaultCity, defaultArea)  
{  
    var cmbProvince = document.getElementById(_cmbProvince);  
    var cmbCity = document.getElementById(_cmbCity);  
    var cmbArea = document.getElementById(_cmbArea);  
      
    function cmbSelect(cmb, str)  
    {  
        for(var i=0; i<cmb.options.length; i++)  
        {  
            if(cmb.options[i].value == str)  
            {  
                cmb.selectedIndex = i;  
                return;  
            }  
        }  
    }  
    function cmbAddOption(cmb, str, obj)  
    {  
        var option = document.createElement("OPTION");  
        cmb.options.add(option);  
        option.innerText = str;  
        option.value = str;  
        option.obj = obj;  
    }  
      
    function changeCity()  
    {  
        cmbArea.options.length = 0;  
        if(cmbCity.selectedIndex == -1)return;  
        var item = cmbCity.options[cmbCity.selectedIndex].obj;  
        for(var i=0; i<item.areaList.length; i++)  
        {  
            cmbAddOption(cmbArea, item.areaList[i], null);  
        }  
        cmbSelect(cmbArea, defaultArea);  
    }  
    function changeProvince()  
    {  
        cmbCity.options.length = 0;  
        cmbCity.onchange = null;  
        if(cmbProvince.selectedIndex == -1)return;  
        var item = cmbProvince.options[cmbProvince.selectedIndex].obj;  
        for(var i=0; i<item.cityList.length; i++)  
        {  
            cmbAddOption(cmbCity, item.cityList[i].name, item.cityList[i]);  
        }  
        cmbSelect(cmbCity, defaultCity);  
        changeCity();  
        cmbCity.onchange = changeCity;  
        
    }  
      
    for(var i=0; i<provinceList.length; i++)  
    {  
        cmbAddOption(cmbProvince, provinceList[i].name, provinceList[i]);  
    }  
    cmbSelect(cmbProvince, defaultProvince);  
    changeProvince();  
    cmbProvince.onchange = changeProvince;  
}  
  
var provinceList = [  
{name:'第一批', cityList:[           
{name:'gx001话费查询 ', areaList:['是否有话费记录','是否开通139邮箱','下发139邮箱']},          
{name:'gx002已开通业务查询', areaList:['查询已开通业务（根据短信指令，下发短信）']},
{name:'gx012已办活动查询', areaList:['下发已办活动查询短信']}, 
{name:'gx013交费记录查询', areaList:['查询交费记录（短信不由业务端下发）','是否开通139邮箱','下发139邮箱']}, 
{name:'gx019返还记录查询', areaList:['返还记录查询（短信不由业务端下发）','是否开通139邮箱','下发139邮箱']},
{name:'gx003账单查询', areaList:['查询月份选项语音内容(历史话费)','是否智能机','查询历史话费','下发139邮件']},
{name:'gx005流量查询', areaList:['查询实时流量']}
]},	
	
	
{name:'第二批', cityList:[           
{name:'gx027安心包 ', areaList:['是否已开通安心包','开通安心包']},          
{name:'gx028放心用', areaList:['是否已开通放心用','开通放心用']},
{name:'gx021套餐资费查询', areaList:['套餐资费查询','查询本月套餐']}, 
{name:'gx022套餐分钟数查询', areaList:['套餐分钟数查询']}, 
{name:'gx015套餐办理', areaList:['查询本月套餐']},
{name:'gx016飞享套餐18元档', areaList:['下月是否有18元飞享套餐','本月是否有18元飞享套餐','查询本月套餐','开通18元飞享套餐']},
{name:'gx017飞享套餐38元档', areaList:['下月是否有38元飞享套餐','本月是否有38元飞享套餐','查询本月套餐','开通38元飞享套餐']},
{name:'gx018飞享套餐58元档', areaList:['下月是否有58元飞享套餐','本月是否有58元飞享套餐','查询本月套餐','开通58元飞享套餐']},
{name:'gx007移动数据流量套餐办理', areaList:['当月是否开通流量套餐']},
{name:'gx009 20元移动数据流量套餐开通', areaList:['当月是否开通流量套餐','下月是否有20元流量套餐','开通20元流量套餐']},
{name:'gx010 30元移动数据流量套餐开通', areaList:['当月是否开通流量套餐','下月是否有30元流量套餐','开通30元流量套餐']},
{name:'gx011 50元移动数据流量套餐开通', areaList:['当月是否开通流量套餐','下月是否有50元流量套餐','开通50元流量套餐']},
{name:'gx038 70元移动数据流量套餐开通', areaList:['当月是否开通流量套餐','下月是否有70元流量套餐','开通70元流量套餐']}
]},

{name:'第三批', cityList:[           
{name:'gx023积分服务 ', areaList:['获取当前总积分']},          
{name:'gx024积分查询', areaList:['获取当前总积分','查询积分范围']},
{name:'gx025积分兑换', areaList:['获取当前总积分','查询积分范围']}, 
{name:'gx026积分兑换流量加油包', areaList:['获取当前总积分','查询积分范围','判断是否开通GPRS','查询积分兑换100M流量是否超限',
	'查询积分兑换30M流量是否超限','积分兑换流量','开通GPRS功能']}, 
{name:'gx029积分兑换话费', areaList:['本月是否积分兑换过话费','查询积分范围','积分兑换话费']},
{name:'gx00101开机和IVR主菜单选择-集外说法处理', areaList:['查询全额代付','查询实时话费','查询是否月结用户','紧急开机','查找用户星级']}
]},

{name:'第四批', cityList:[           
{name:'gx047 飞享套餐8元档 ', areaList:['下月是否有8元飞享套餐','本月是否有8元飞享套餐','开通8元飞享套餐']},          
{name:'gx048 飞享套餐88元档', areaList:['下月是否有88元飞享套餐','本月是否有88元飞享套餐','查询本月套餐','开通88元飞享套餐']},
{name:'gx049 飞享套餐138元档', areaList:['下月是否有138元飞享套餐','本月是否有138元飞享套餐','查询本月套餐','开通138元飞享套餐']}, 
{name:'gx050 飞享套餐188元档', areaList:['下月是否有188元飞享套餐','本月是否有188元飞享套餐','查询本月套餐','开通188元飞享套餐']}, 
{name:'gx030 100元移动数据流量套餐开通', areaList:['当月是否开通流量套餐','下月是否开通100元5G流量套餐','开通100元5G流量套餐']},
{name:'gx031 150元移动数据流量套餐开通', areaList:['当月是否开通流量套餐','下月是否开通150元10G流量套餐','开通150元10G流量套餐']},
{name:'gx032 10元国内闲时流量包开通', areaList:['当月是否开通非常流量套餐','下月是否开通10元国内闲时流量套餐','开通10元国内闲时流量套餐']},
{name:'gx034 10元500M流量日套餐开通', areaList:['获取当月开通流量套餐达到10次','开通500M日流量套餐']},
{name:'gx035 15元1G流量日套餐开通', areaList:['获取当月开通流量套餐达到10次','开通15元1G流量日流量套餐']},
{name:'gx037 6元500M流量小时套餐开通', areaList:['获取当月开通流量套餐达到10次','开通6元500M流量小时流量套餐']},
{name:'gx039 9元1G流量小时套餐开通', areaList:['获取当月开通流量套餐达到10次','开通9元1G流量小时流量套餐']},
]},


{name:'第五批', cityList:[           
{name:'gx055 停机', areaList:['查询特殊号码','查询垃圾短信黑名单','检测密码是否被锁','密码校验','停开机','下发业务限制短信']},          
{name:'gx060 宽带密码修改', areaList:['根据宽带账号获取宽带信息','检测宽带密码是否被锁','检查宽带密码','按规则校验密码','修改宽带密码']},
{name:'gx061 宽带密码重置', areaList:['根据宽带账号获取宽带信息','查询用户停开机状态信息','检查身份证信息','重置宽带密码']}, 
{name:'gx050 飞享套餐188元档', areaList:['下月是否有188元飞享套餐','本月是否有188元飞享套餐','查询本月套餐','开通188元飞享套餐']}, 
{name:'gx063 手机密码修改', areaList:['检测密码是否被锁','密码校验','按规则校验密码','密码变更','下发密码修改成功短信']},
{name:'gx064 手机密码重置', areaList:['检查身份证信息','密码变更','下发密码重置成功短信']},
{name:'gx043流量取消', areaList:['当月是否开通非常流量套餐','当月是否开通流量套餐']},
{name:'gx044 10元包1G国内闲时流量包取消', areaList:['当月是否开通非常流量套餐','下月是否开通10元国内闲时流量套餐','取消10元国内闲时流量套餐']},
{name:'gx045 移动数据流量套餐取消', areaList:['当月是否开通流量套餐','下月是否开通XX元流量套餐','取消XX元流量套餐']}
]}



];  