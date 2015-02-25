package com.algaworks.ocs.api;

import com.algaworks.ocs.cdr.CDRFileLocator;
import com.algaworks.ocs.cdr.CDRGenerator;
import com.algaworks.ocs.cdr.CDRGeneratorFile;
import com.algaworks.ocs.cdr.CDRGeneratorNoSql;

public class OcsApiNoSqlTest extends OcsApi {

	@Override
	public CDRGenerator getCdrGenerator() {
		return new CDRGeneratorNoSql();
	}

	@Override
	public CDRFileLocator getCdrFileLocator() {
		return null;
	}

}
