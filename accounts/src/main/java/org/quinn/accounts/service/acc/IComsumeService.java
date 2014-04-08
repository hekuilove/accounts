package org.quinn.accounts.service.acc;

import org.quinn.accounts.model.acc.ComsumeRecord;
import org.quinn.accounts.model.acc.ComsumeType;
import org.quinn.accounts.util.paginate.Paginate;

public interface IComsumeService {

	void addType(ComsumeType type);

	void addRecord(ComsumeRecord record);

	Paginate<ComsumeRecord> paginateConsumeRecord(Paginate<ComsumeRecord>  paginate);
}
