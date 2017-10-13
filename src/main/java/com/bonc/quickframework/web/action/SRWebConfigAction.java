package com.bonc.quickframework.web.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.bonc.quickframework.web.action.original.AbstractSRWebConfigAction;

@Controller(value = "SRWebConfigAction")
@Scope(value = "prototype")
public class SRWebConfigAction extends AbstractSRWebConfigAction{

}
