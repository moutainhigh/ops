package com.jyblife.logic.wl.ops.manage.service;

import com.jyblife.logic.wl.ops.entity.OilCompanyAttachment;

public interface OilCompanyAttachmentService {

	OilCompanyAttachment selectById(Integer id);
	
	int insertAttachment(OilCompanyAttachment attachment);
	
}
