package org.quinn.accounts.util.paginate;

import java.util.List;

public class Paginate<T> {


	public Paginate() {
		this(6);
	}

	public Paginate(int pageNosLen) {
		pageNos = new int[pageNosLen];
	}

	private T bean;

	private int[] pageNos;

	private Integer pageSize = 10; // 每页显示行数

	private Integer pageNo = 1; // 当前页数

	private Integer pageAllNo;

	private List<T> datas;

	private Integer pageAllSize;

	private MySQLLimitParam limit;

	public int[] getPageNos() {
		return pageNos == null || pageNos.length == 0 ? new int[] { 1 } : this.pageNos;
	}

	public void setPageNos(int[] pageNos) {
		this.pageNos = pageNos;
	}

	public Integer getPageSize() {
		if (pageSize == null || pageSize < 1)
			try {
				throw new AccountsPaginateException("pageSize cannot null or <1");
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

	public void setPageAllSize(Integer pageAllSize) {
		this.pageAllSize = pageAllSize;
		if (this.pageAllSize > 0) {
			this.pageAllNo = this.pageAllSize / (this.pageSize + (this.pageSize - 1));
		}
		if (this.pageAllNo < this.pageNos.length && pageAllNo > 0) {
			for (int i = 0; i < this.pageAllNo; i++)
				pageNos[i] = i + 1;
		} else if (this.pageAllNo > this.pageNos.length) {
			pageNos[0] = 1;
			for (int i = 1; i < pageNos.length; i++) {
				pageNos[i] = pageNo + i;
			}
		}
	}

	public MySQLLimitParam getLimit() {
		if (this.limit == null)
			this.limit = new MySQLLimitParam();
		limit.setFirstPara(pageNo == 1 ? 0 : (this.pageNo - 1) * pageSize - 1);
		limit.setNextPara(this.pageSize);
		return limit;
	}

	/**
	 * 针对MySQL的分页参数
	 * 
	 * @author 何奎<br>
	 * @date 2014年4月3日下午9:30:31<br>
	 */
	public static class MySQLLimitParam {


		private int firstPara;

		private int nextPara;

		public int getFirstPara() {
			return firstPara;
		}

		public void setFirstPara(int firstPara) {
			this.firstPara = firstPara;
		}

		public int getNextPara() {
			return nextPara;
		}

		public void setNextPara(int nextPara) {
			this.nextPara = nextPara;
		}
	}
}
