package com.bonc.quickframework.web.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.bonc.quickframework.web.action.original.AbstractSRDataBaseConfigAction;

@Controller(value = "SRDataBaseConfigAction")
@Scope(value = "prototype")
public class SRDataBaseConfigAction extends AbstractSRDataBaseConfigAction{

}
