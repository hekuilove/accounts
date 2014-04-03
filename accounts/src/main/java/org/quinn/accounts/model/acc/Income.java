package org.quinn.accounts.model.acc;

import org.quinn.accounts.model.BaseModel;

/**
 * 收入
 * 
 * @author Quinn
 * 
 */
public class Income extends BaseModel {


	private Double sumMoney;

	private Integer createBy;

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Double getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(Double sumMoney) {
		this.sumMoney = sumMoney;
	}
}
