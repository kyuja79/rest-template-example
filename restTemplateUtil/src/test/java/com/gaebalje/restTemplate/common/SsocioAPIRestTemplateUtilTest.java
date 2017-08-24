package com.gaebalje.restTemplate.common;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.junit4.SpringRunner;

import com.gaebalje.restTemplate.RestTemplateApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestTemplateApplication.class)
public class SsocioAPIRestTemplateUtilTest {
	
	@Autowired
	private IRestTemplate ssocioAPIRestTemplateUtil;
	
	@Test
	public void getDlvList(){
		ParameterizedTypeReference<List<dlvCmp>> typeRef = new ParameterizedTypeReference<List<dlvCmp>>() {};
		List<dlvCmp> dlvList = ssocioAPIRestTemplateUtil.get("/dlv_comp_info/select/list/sweetTracker", "", typeRef);
		dlvList.stream().forEach(dlv_cmp -> System.out.println(dlv_cmp));
	}
}

class dlvCmp {
	private String dlv_cmp_cd;
	private String dlv_cmp_nm;
	
	public String getDlv_cmp_cd() {
		return dlv_cmp_cd;
	}
	public void setDlv_cmp_cd(String dlv_cmp_cd) {
		this.dlv_cmp_cd = dlv_cmp_cd;
	}
	public String getDlv_cmp_nm() {
		return dlv_cmp_nm;
	}
	public void setDlv_cmp_nm(String dlv_cmp_nm) {
		this.dlv_cmp_nm = dlv_cmp_nm;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString()).append(" dlv_cmp_cd=").append(dlv_cmp_cd).append(", dlv_cmp_nm=")
				.append(dlv_cmp_nm);
		return builder.toString();
	}
	
}