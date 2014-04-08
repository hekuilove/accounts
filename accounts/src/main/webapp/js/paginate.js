/*奎哥写的分页插件*/

var Paginate = function(form) {

	var pageNoName = "pageNo";

	var pageSizeName = "pageSize";

	var formObject = form;

	var pageNo = 1;

	var pageSize = 10;

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

	/**
	 * 提交表单
	 */
	this.subform = function() {
		if (!checkObject(formObject, pageNoName))
			createHide(formObject, pageNoName);
		if (!checkObject(formObject, pageSizeName))
			createHide(formObject, pageNoName);
		$(formObject).find("input:hidden[name='" + pageNoName + "']").val(
				pageNo);
		$(formObject).find("input:hidden[name='" + pageSizeName + "']").val(
				pageSize);
		formObject.submit();
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