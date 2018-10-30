package com.okkristen.project.core.page;
import org.springframework.data.domain.Pageable;

/**
 * 分页参数对象，为了适应{@code @RequestBody}中存放查询对象和分页对象
 * @author fengw
 * @create 2017年1月25日
 */
public class PageParam<P>{
	P param;
	MyPageRequest pageable;

	public P getParam() {
		return param;
	}
	public void setParam(P param) {
		this.param = param;
	}
	public Pageable getPageable() {
		if (pageable != null) {
			return pageable.getPageable();
		}
		return new MyPageRequest().getPageable();
	}
	public void setPageable(MyPageRequest pageable) {
		this.pageable = pageable;
	}
}
