package com.joalvarez.challengealkemy.service.generals;

import com.joalvarez.challengealkemy.shared.HasLogger;

public abstract class GenericService<DAO, MAP> implements HasLogger {

	protected final MAP mapper;
	protected final DAO dao;

	public GenericService(DAO dao, MAP mapper) {
		this.dao = dao;
		this.mapper = mapper;
	}

}
