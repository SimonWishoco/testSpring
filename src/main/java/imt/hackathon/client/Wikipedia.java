package imt.hackathon.client;

import imt.hackathon.controllers.JsonResult;

public interface Wikipedia {
	public JsonResult getInformations(String name);
}
