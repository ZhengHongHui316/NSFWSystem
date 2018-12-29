package cn.itcast.nsfw.info.service;

import java.util.List;

import cn.itcast.core.page.PageResult;
import cn.itcast.core.service.BaseService;
import cn.itcast.core.util.QueryHelper;
import cn.itcast.nsfw.info.entity.Info;

public interface InfoService extends BaseService<Info> {

	PageResult getPageResult(QueryHelper queryHelper, int pageNo, int pageSize);

	
}
