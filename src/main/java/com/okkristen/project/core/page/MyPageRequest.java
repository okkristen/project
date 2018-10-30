package com.okkristen.project.core.page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.util.StringUtils;

/**
 * 为了适应{@code @RequestBody } 存放查询对象和分页对象的问题，在PageParam中应用
 * @author fengw
 * @create 2017年1月29日
 */
public class MyPageRequest {

	private int page = 0;
	private int size = 20;
	private String sort;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public Pageable getPageable() {
		if (StringUtils.isEmpty(sort)) {
			Sort sort = new Sort(Direction.DESC, "createTime");
			return PageRequest.of(page, size, sort);
		} else {
			String[] sortArr = this.sort.split(",");
			Order order = new Order(Direction.fromString(sortArr[1]), sortArr[0]);
			Sort sort = Sort.by(order);
			return PageRequest.of(page, size, sort);
		}
	}
}

