package org.quinn.accounts.service.acc;

import org.quinn.accounts.model.acc.ComsumeRecord;
import org.quinn.accounts.model.acc.ComsumeType;

public interface IComsumeService {

	void addType(ComsumeType type);

	void addRecord(ComsumeRecord record);
}
