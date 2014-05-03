/*奎哥写的分页插件*/

var Paginate = function() {

	var pageNoName = "pageNo";

	var pageSizeName = "pageSize";

	var pageNo = 1;

	var pageSize = 10;
	
	/**
	 * 用于ajax方式
	 */
	var myform ;
	
	var dataPair ;
	
	this.setDataPair = function (dp){
		dataPair = dp;
	}

	this.setForm = function(f){
		myform = f;
	}
	/**
	 * 设置PageNO和PageSize
	 * 
	 * @param pageNo
	 * @param isSubmit
	 *            设置完是否需要马上提交form
	 */
	this.set = function(pageN, pageS, isSubmit) {
		pageNo = pageN;
		pageSize = pageS;
		if (isSubmit)
			this.subform();
	}

	this.setPageNo = function(pageN, isSubmit) {
		pageNo = pageN;
		if (isSubmit)
			this.subform();
	}

	this.setPageSize = function(pageS, isSubmit) {
		pageSize = pageS;
		if (isSubmit)
			this.subform();
	}
	
	this.subform = function(){
		myform[0].submit();
	}

	this.initTabByAjax = function(u,){
		
		$.ajax({
			url:u,
			type:"post",
			dataType:"json",
			data:myform.serialize()
		})
	}
	
}

/**
 * 检查在parentObj中是否包含Name为objName的对象 <br>
 * 如果存在返回true 否则返回false
 */
function checkObject(parentObj, objName) {
	return $(parentObj).find("input:hidden[name='" + objName + "']").length > 0;
}

/**
 * 
 * 在表单parentObj里创建一个name为objName的隐藏域 值为objVal
 * 
 * @param parentObj
 *            表单对象
 * @param objName
 *            创建的名称
 */
function createHide(parentObj, objName) {
	var htm = "<input type='hidden' name='" + objName + "'  />";
	$(parentObj).append(htm);
}

function doPaginate(pageno, pagesize, formname) {
	var formo = $("#" + formname);
	var po = new Paginate(formo);
	po.set(pageno, pagesize, true);
}

function firstOrEndPag(obj, isFirst) {
	var formObj = $(obj).parent().parent().parent().parent().parent();
	if (isFirst)
		formObj.find("input:hidden[name='pageNo']").val(1);
	else {
		var pgAllNo = formObj.find("input:hidden[id='pageAllNo']").val();
		formObj.find("input:hidden[name='pageNo']").val(pgAllNo);
	}
	formObj[0].submit();
}

function doPag(obj, isAgo) {
	var formObj = $(obj).parent().parent().parent().parent().parent();
	var pgNo = formObj.find("input:hidden[name='pageNo']").val();
	if (isAgo)
		formObj.find("input:hidden[name='pageNo']").val(parseInt(pgNo) - 1);
	else
		formObj.find("input:hidden[name='pageNo']").val(parseInt(pgNo) + 1);
	formObj[0].submit();
}

function goPag(obj){
	var formObj = $(obj).parent().parent().parent().parent().parent();
	var pgno = formObj.find("input:text[id='pgno']").val();
	if(pgno==null||pgno.length==0){
		alert("请输入页数");
		return;
	}
	if(isNaN(pgno)){
		alert("请输入一个数字");
		return;
	}
	
	var pgAllNO = formObj.find("input:hidden[name='pageAllNo']").val();
	if(parseInt(pgno)>parseInt(pgAllNO)||parseInt(pgno)<1){
		alert("请输入正确的页数");
		return;
	}
	formObj.find("input:hidden[name='pageNo']").val(pgno);
	formObj[0].submit();
}
/**
 * 开发者自己创建的Paginate对象
 */
var pagObj;

/**
 * 开发者new了Paginate之后 需要执行该方法
 * 
 * @param depagObj
 *            开发者自己创建的Paginate对象
 */
function initPaginateObj(depagObj) {
	pagObj = depagObj;
}
