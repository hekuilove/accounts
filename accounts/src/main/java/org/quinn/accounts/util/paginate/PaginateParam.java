package org.quinn.accounts.util.paginate;

import java.util.ArrayList;
import java.util.List;

public class PaginateParam {

	public PaginateParam() {
		comParas = new ArrayList<Object>();
	}

	private List<Object> comParas;

	private Object[] pagParas;

	public Object[] getComParas() {
		return comParas.toArray();
	}

	public void setComParas(List<Object> comParas) {
		this.comParas = comParas;
	}

	public Object[] getPagParas() {
		return pagParas;
	}

	public void setPagParas(Object[] pagParas) {
		this.pagParas = pagParas;
	}

	public void addComParm(Object o) {
		this.comParas.add(o);
	}

	public Object[] getAllParams() {
		int cl = comParas != null ? comParas.size() : 0;
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
