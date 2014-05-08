package org.quinn.accounts.util.paginate;

import java.util.List;

public class PageBean<T> {

	private boolean isAjax = false;

	private T bean;
	
	private String tableId;

	private int[] pageNos;

	private Integer pageSize = 10; // 每页显示行数

	private Integer pageNo = 1; // 当前页数

	private Integer pageAllNo;

	private List<T> datas;

	private Integer pageAllSize;

	private List<Object[]> objs;

	private String footer;

	public int[] getPageNos() {
		return pageNos == null || pageNos.length == 0 ? new int[] { 1 }
				: this.pageNos;
	}

	public void setPageNos(int[] pageNos) {
		this.pageNos = pageNos;
	}

	public Integer getPageSize() {
		if (pageSize == null || pageSize < 1)
			try {
				throw new AccountsPaginateException(
						"pageSize cannot null or <1");
			} catch (AccountsPaginateException e) {
				e.printStackTrace();
			}
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageAllNo() {
		return pageAllNo;
	}

	public void setPageAllNo(Integer pageAllNo) {
		this.pageAllNo = pageAllNo;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public Integer getPageAllSize() {
		return pageAllSize;
	}

	public T getBean() {
		return bean;
	}

	public void setBean(T bean) {
		this.bean = bean;
	}

	public List<Object[]> getObjs() {
		return objs;
	}

	public void setObjs(List<Object[]> objs) {
		this.objs = objs;
	}

	public boolean getIsAjax() {
		return this.isAjax;
	}

	public void setIsAjax(boolean isAjax) {
		this.isAjax = isAjax;
	}

	public String getFooter() {
		return footer;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public void initFooter() {
		StringBuffer footer = new StringBuffer("共<font color=\"red\">")
				.append(this.pageAllSize);
		footer.append("</font>行&nbsp;"); // 总行数
		footer.append("共<font color=\"red\">").append(this.getPageAllNo());
		footer.append("</font>页&nbsp;"); // 共多少页
		footer.append("当前第<font color=\"red\">").append(this.getPageNo());
		footer.append("</font>页&nbsp;"); // 当前页数
		footer.append("每页显示<font color=\"red\">").append(this.getPageSize());
		footer.append("</font>行&nbsp;"); // 每页显示多少行
		if (this.getPageNo() != 1) {
			footer.append("<a href='javascript:;' onclick='firstOrEndPag(this,true,");
			footer.append(this.isAjax + ","+this.tableId+")'>首页</a>&nbsp;");
			footer.append("<a href='javascript:;' onclick='doPag(this,true,");
			footer.append(this.isAjax + ","+this.tableId+")'>上一页</a>&nbsp;");
		}
		if (!this.getPageNo().equals(this.getPageAllNo())) {
			footer.append("<a href='javascript:;' onclick='doPag(this,false,");
			footer.append(this.isAjax + ","+this.tableId+")'>下一页</a>&nbsp;");
			footer.append("<a href='javascript:;' onclick='firstOrEndPag(this,false,");
			footer.append(this.isAjax + ","+this.tableId+")'>末页</a>&nbsp;");
		}
		footer.append("去<input type=\"text\" style=\"width: 30px\" id='pgno'>页&nbsp;");
		footer.append("<a href=\"javascript:;\" onclick='goPag(this,");
		footer.append(this.isAjax + ","+this.tableId+")'>GO</a>");
		/*
		 * hidden部分
		 */
		if (!this.isAjax) {
			footer.append("<input type='hidden' name='pageSize' value='");
			footer.append(this.getPageSize()).append("'>");
			footer.append("<input type='hidden' name='pageNo' value='");
			footer.append(this.getPageNo()).append("'>");
			footer.append("<input type='hidden' id='pageAllSize' value='");
			footer.append(this.getPageAllSize()).append("'>");
			footer.append("<input type='hidden' id='pageAllNo' value='");
			footer.append(this.getPageAllNo()).append("'>");
		}

		this.footer = footer.toString();
	}

	public void setPageAllSize(Integer pageAllSize) {
		this.pageAllSize = pageAllSize;
		this.pageAllNo = (this.pageAllSize + (this.pageSize - 1))
				/ this.pageSize;
		pageNos = new int[this.pageAllNo < 6 ? this.pageAllNo : 6];
		if (this.pageAllNo < 6 && pageAllNo > 0) {
			for (int i = 0; i < this.pageAllNo; i++)
				pageNos[i] = i + 1;
		} else if (this.pageAllNo > this.pageNos.length) {
			pageNos[0] = 1;
			for (int i = 1; i < pageNos.length; i++) {
				pageNos[i] = pageNo + i;
			}
		}
	}
}
