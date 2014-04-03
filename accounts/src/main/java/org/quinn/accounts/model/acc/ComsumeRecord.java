package org.quinn.accounts.model.acc;

import org.quinn.accounts.model.BaseModel;

/**
 * 消费记录
 * 
 * @author WS-SH-L1051
 * 
 */
public class ComsumeRecord extends BaseModel {


	private String comsumeType;

	private Double comsumeMoney;

	private Integer createBy;

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public String getComsumeType() {
		return comsumeType;
	}

	public void setComsumeType(String comsumeType) {
		this.comsumeType = comsumeType;
	}

	public Double getComsumeMoney() {
		return comsumeMoney;
	}

	public void setComsumeMoney(Double comsumeMoney) {
		this.comsumeMoney = comsumeMoney;
	}
}
