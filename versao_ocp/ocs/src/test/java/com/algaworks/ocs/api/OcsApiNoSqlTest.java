package com.algaworks.ocs.api;

import com.algaworks.ocs.cdr.CDRGeneratorFile;
import com.algaworks.ocs.cdr.CDRGeneratorNoSql;

public class OcsApiNoSqlTest extends OcsApi {

	public OcsApiNoSqlTest(String pastaCdr) {
		super(pastaCdr);
	}

	@Override
	public CDRGeneratorFile getCdrGenerator() {
		return new CDRGeneratorFile();
	}

}
