/*奎哥写的分页插件*/

function Paginate() {

	this.mytable;

	this.doDefaultPaging = true;

	operateContent = null;
	
	hasOperateContent = false;
	
	/**
	 * 用于ajax方式
	 */
	this.myform = null;

	this.dataPair;
	
	/**
	 * 是否需要做操作
	 */
	Paginate.prototype.setHasOperateContent = function(oc){
		hasOperateContent = oc;
	}
	
	/**
	 * 设置操作条件
	 * {
	 * type:a 类型  a为a标签 b为button
	 * onclick:'',
	 * style:'',
	 * href:
	 * param:[{name:id,isVar:true,val:1}] 
	 * }
	 */
	Paginate.prototype.setOperateContent = function(oc){
		operateContent = oc;
	}

	/**
	 * 是否需要使用默认的分页 <br>
	 * 如果不需要 可以直接调用getPaginateObj获取分页对象自行处理 <br>
	 * 如果需要 需要设置好参数后 直接调用initTabByAjax即可
	 */
	Paginate.prototype.setDefaultPaging = function(dp) {
		this.doDefaultPaging = dp;
	}

	/**
	 * 用table做分页时需要传入一个table的jquery对象，如:$("#tabId")<br>
	 * 注意：是jquery对象 不是js对象
	 */
	Paginate.prototype.setTable = function(tb) {
		mytable = tb;
		this.myform = mytable.parent();
	}
	/**
	 * 获取由setTable放入的table的jquery对象
	 */
	Paginate.prototype.getTable = function() {
		return mytable;
	}

	/**
	 * 当doDefaultPaging为true时，需要传入匹配条件。<br>
	 * 规则为json对象的key值
	 */
	Paginate.prototype.setDataPair = function(dp) {
		dataPair = dp;
	}

	/**
	 * 提供的默认分页，仅适用于table分页，如有其他控件的分页请自己写逻辑部分(doDefaultPaging设置为false，然后拿到分页对象)。<br>
	 * 执行该方法之前需要调用的方法为setDataPair、setTable
	 */
	Paginate.prototype.initTabByAjax = function() {
		var Data = this.ajaxGetData();
		if (this.doDefaultPaging) {
			var tb = mytable;
			tb.find("tr:not(:first)").remove();
			
			$.each(Data.datas, function(ind, obj) {
				var trh = "<tr>";
				/*
				 * 根据匹配条件拼接tr td  //14
				 */
				$.each(dataPair, function(dind, dobj) {
					trh += "<td>" + eval("obj." + dobj) + "</td>";
				});
				var ocstr ="";
				/*
				 * 检查是否有操作
				 */
				if(hasOperateContent){
					$.each(operateContent,function(hind,hobj){
						
						var isATag = hobj.type == "a" || hobj.type =="A";  //是否是A标签
						var isBTag = hobj.type == "b" || hobj.type =="A" //是否是button
						
						if(isATag){
							ocstr+="<a ";
						}else if(isBTag){
							ocstr+="<input type=\"button\" value=\""+hobj.val+"\"";
						}
							/*
							 * onclick
							 */
							if(hobj.onclick!=null){
								ocstr+="onclick=\"";
								ocstr+=hobj.onclick.name+"("
								if(hobj.onclick.params!=null&&hobj.onclick.params.length>0){
									var paraStr="";
									$.each(hobj.onclick.params,function(pind,pobj){
										if(pobj.isVar){
											paraStr+=eval("obj."+pobj.val);
										}else{
											paraStr+=pobj.val;
										}
										paraStr="'"+paraStr+"'";
										if(pind<hobj.onclick.params.length-1){
											paraStr+=",";
										}
									});
									ocstr+=paraStr;
								}
								ocstr+=");\""
							}
							/*
							 * style
							 */
							if(hobj.style!=null&&hobj.style.length>0){
								ocstr+="style=\""+hobj.onclick+"\" ";
							}
							/*
							 * href
							 */
							if(isATag)
								if(hobj.href!=null){
									var hfs ="href=\"";
									var hf = hobj.href;
									if(hf.isLink){ //是链接
										hfs +=hf.val;
										var hfps="?";
										if(hf.params!=null&&hf.params.length>0){
											$.each(hf.params,function(hfind,hfobj){
												if(hfobj.isVar){ 
													hfps+=hfobj.name+"="+eval("obj."+hfobj.val);
												}else{
													hfps+=hfobj.name+"="+hfobj.val;
												}
												if(hfind<hf.params.length-1){
													hfps+="&";
												}
											});
										}
										hfs+=hfps+"\" ";
									}else{ //是js函数
										hfs+="javascript:"+hf.val+"(";
										if(hf.params!=null&&hf.params.length>0){
											$.each(hf.params,function(hfind,hfobj){
												if(hfobj.isVar){ 
													hfs+="'"+eval("obj."+hfobj.val)+"'";
												}else{
													hfs+="'"+hfobj.val+"'";
												}
												if(hfind<hf.params.length-1){
													hfps+=",";
												}
											});
										}
										hfs+=")\" ";
									}
									
									ocstr+=hfs;
								}
							
							/*
							 * class
							 */
							if(hobj.cla!=null&&hobj.cla.length>0){
								ocstr+=" class=\""+hobj.cla+"\"";
							}
							
							ocstr+=" >";
							if(isATag){
								ocstr+=hobj.val+"</a>";
							}
							if(hind<operateContent.length-1)
								ocstr+="&nbsp;";
					});
				}
				trh+="<td>"+ocstr+"</td>";
				trh += "</tr>";
				tb.append(trh);
			});
			var footerhtm = "<tr style=\"font-size: xx-small;\" align=\"right\">";
			footerhtm += "<td colspan='" + dataPair.length + "' align='right'>";
			footerhtm += Data.footer + "</td></tr>";
			tb.append(footerhtm);
		} else {
			var tid = mytable.attr("id");
			eval(tid + "(Data)");
		}
	}

	/**
	 * 获取分页对象，即后台PageBean的
	 */
	Paginate.prototype.getPaginateObj = function() {
		if (!this.doDefaultPaging) {
			var tid = mytable.attr("id");
			var d = this.ajaxGetData();
			eval(tid + "(d)");
		} else
			throw new Error(
					"doDefaultPaging is true,please use method 'initTabByAjax' ");
	}

	this.ajaxGetData = function() {
		var da = this.myform.serialize();
		da += "&isAjax=true&tableId="+mytable.attr("id");
		var Da;
		$.ajax({
			url : this.myform.attr("action"),
			type : "post",
			dataType : "json",
			data : da,
			async : false,
			success : function(Data) {
				var f = mytable.parent();
				if (f.find("input:hidden[id='pageAllNo']").length == 0) {
					f.append("<input type='hidden' id='pageAllNo' value='"
							+ Data.pageAllNo + "'>");
				} else {
					f.find("input:hidden[id='pageAllNo']").val(Data.pageAllNo);
				}
				Da = Data;
			},
			error : function() {
				alert("paging error");
			}
		})
		return Da;
	}

}


function firstOrEndPag(obj, isFirst, iajax,tbId) {
	if (!iajax) { // 非ajax
		var formObj = $(obj).parent().parent().parent().parent().parent();
		if (isFirst)
			formObj.find("input:hidden[name='pageNo']").val(1);
		else {
			var pgAllNo = formObj.find("input:hidden[id='pageAllNo']").val();
			formObj.find("input:hidden[name='pageNo']").val(pgAllNo);
		}
		formObj[0].submit();
	} else {
		var tb = pagObj.get(tbId).getTable();
		var fom = tb.parent();
		if (isFirst)
			fom.find("input:hidden[name='pageNo']").val(1);
		else {
			var pgAllNo = fom.find("input:hidden[id='pageAllNo']").val();
			fom.find("input:hidden[name='pageNo']").val(pgAllNo);
		}
		pagObj.get(tbId).initTabByAjax();
	}
}

function doPag(obj, isAgo, isAjax,tbId) {
	if (!isAjax) {
		var formObj = $(obj).parent().parent().parent().parent().parent();
		var pgNo = formObj.find("input:hidden[name='pageNo']").val();
		if (isAgo)
			formObj.find("input:hidden[name='pageNo']").val(parseInt(pgNo) - 1);
		else
			formObj.find("input:hidden[name='pageNo']").val(parseInt(pgNo) + 1);
		formObj[0].submit();
	} else {
		var tb = pagObj.get(tbId).getTable();
		var fom = tb.parent();
		var pg = fom.find("input:hidden[name='pageNo']").val();
		if (isAgo)
			fom.find("input:hidden[name='pageNo']").val(parseInt(pg) - 1);
		else
			fom.find("input:hidden[name='pageNo']").val(parseInt(pg) + 1);
		pagObj.get(tbId).initTabByAjax();
	}

}

function goPag(obj, iajax,tbId) {
	var formObj;
	if(!iajax)
		 formObj = $(obj).parent().parent().parent().parent().parent();
	else
		formObj = pagObj.get(tbId).getTable().parent();
	var pgno = formObj.find("input:text[id='pgno']").val();
	if (pgno == null || pgno.length == 0) {
		alert("请输入页数");
		return;
	}
	if (isNaN(pgno)) {
		alert("请输入一个数字");
		return;
	}

	var pgAllNO = formObj.find("input:hidden[id='pageAllNo']").val();
	if (parseInt(pgno) > parseInt(pgAllNO) || parseInt(pgno) < 1) {
		alert("请输入正确的页数");
		return;
	}
	formObj.find("input:hidden[name='pageNo']").val(pgno);
	if (!iajax) {
		formObj[0].submit();
	}else{
		pagObj.get(tbId).initTabByAjax();
	}
}

/**
 * 开发者自己创建的Paginate对象
 */
var pagObj = new HashMap();

/**
 * 开发者new了Paginate之后 需要执行该方法
 * 
 * @param depagObj
 *            开发者自己创建的Paginate对象
 */
function initPaginateObj(depagObj) {
	var tbId = depagObj.getTable().attr("id");
	pagObj.put(tdId,depagObj);
}
