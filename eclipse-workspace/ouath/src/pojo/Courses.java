package pojo;

import java.util.List;

public class Courses {
	private List<WebAutomation> WebAutomation;
	private List<Api> api;
	private List<mobile> mobile;
	
	public List<pojo.WebAutomation> getWebAutomation() {
		return WebAutomation;
	}
	public void setWebAutomation(List<pojo.WebAutomation> webAutomation) {
		WebAutomation = webAutomation;
	}
	public List<Api> getApi() {
		return api;
	}
	public void setApi(List<Api> api) {
		this.api = api;
	}
	public List<pojo.mobile> getMobile() {
		return mobile;
	}
	public void setMobile(List<pojo.mobile> mobile) {
		this.mobile = mobile;
	}


}
