package com.xinboiedu.erp.auth.emp.vo;

import com.xinboiedu.erp.base.BaseQueryModel;
import com.xinboiedu.erp.utils.FormatterUtil;

public class EmpQueryModel extends EmpModel implements BaseQueryModel{
	//追加一个字段用户保存最大区间的时间数据
	private Long birthday2;
	private String birthday2View;
	public String getBirthday2View() {
		return birthday2View;
	}
	public Long getBirthday2() {
		return birthday2;
	}

	public void setBirthday2(Long birthday2) {
		this.birthday2 = birthday2;
		birthday2View=FormatterUtil.FormaterDate(birthday2);
	}
	
}
