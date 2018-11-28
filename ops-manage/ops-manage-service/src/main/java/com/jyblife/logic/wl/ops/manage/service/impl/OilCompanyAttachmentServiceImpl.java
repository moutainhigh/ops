package com.jyblife.logic.wl.ops.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyblife.logic.wl.ops.entity.OilCompanyAttachment;
import com.jyblife.logic.wl.ops.manage.mapper.OilCompanyAttachmentMapper;
import com.jyblife.logic.wl.ops.manage.service.OilCompanyAttachmentService;

@Service
@Transactional
public class OilCompanyAttachmentServiceImpl implements OilCompanyAttachmentService {

	@Autowired
	private OilCompanyAttachmentMapper mapper;

	@Override
	public OilCompanyAttachment selectById(Integer id) {
		return mapper.get(OilCompanyAttachment.class, id);
	}

	@Override
	public int insertAttachment(OilCompanyAttachment attachment) {
		return mapper.insertAttachment(attachment);
	}
	
}
