package org.quinn.accounts.util.paginate;

public class PaginateParam {


	private Object[] comParas;

	private Object[] pagParas;

	public Object[] getComParas() {
		return comParas;
	}

	public void setComParas(Object[] comParas) {
		this.comParas = comParas;
	}

	public Object[] getPagParas() {
		return pagParas;
	}

	public void setPagParas(Object[] pagParas) {
		this.pagParas = pagParas;
	}

	public Object[] getAllParams() {
		int cl = comParas != null ? comParas.length : 0;
		int pl = pagParas != null ? pagParas.length : 0;
		Object[] pars = new Object[cl + pl];
		int ind = 0;
		if (comParas != null)
			for (Object o : comParas) {
				pars[ind] = o;
				ind++;
			}
		if (pagParas != null)
			for (Object o : pagParas) {
				pars[ind] = o;
				ind++;
			}
		return pars;
	}
}
