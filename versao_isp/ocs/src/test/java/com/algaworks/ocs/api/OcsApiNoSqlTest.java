package com.algaworks.ocs.api;

import com.algaworks.ocs.cdr.CDRFileLocator;
import com.algaworks.ocs.cdr.CDRGenerator;
import com.algaworks.ocs.cdr.CDRGeneratorFile;

public class OcsApiNoSqlTest extends OcsApi {

	private CDRGeneratorFile cdrGeneratorFile;
	
	public OcsApiNoSqlTest() {
		this.cdrGeneratorFile = new CDRGeneratorFile("/opt/cdr");
	}

	@Override
	public CDRGenerator getCdrGenerator() {
		return cdrGeneratorFile;
	}

	@Override
	public CDRFileLocator getCdrFileLocator() {
		return cdrGeneratorFile;
	}

}
