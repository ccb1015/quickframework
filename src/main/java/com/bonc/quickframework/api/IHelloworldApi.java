package com.bonc.quickframework.api;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.bonc.quickframework.entity.PageInfo;

@Path("/helloworld")
@Produces({ MediaType.APPLICATION_JSON})
@WebService
public interface IHelloworldApi {

	//@WebParam 需要暴露的参数
	//@pathparam 取path上的参数
	//@QueryParam 取url上的参数
	
	@GET
	@Path("/hello")//http://localhost:8080/quickframework/api/rs/test/hello
	@WebMethod
	public String sayHello(@WebParam(name = "name")@QueryParam("name") String name);
	
	@GET
	@Path("/page/{currentPage}")
	@WebMethod
	public PageInfo getPageInfo(@WebParam(name = "currentPage")@PathParam("currentPage") String currentPage);
}
