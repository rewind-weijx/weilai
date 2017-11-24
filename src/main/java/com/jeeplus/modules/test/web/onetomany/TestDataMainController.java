/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.test.web.onetomany;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jeeplus.common.config.Global;
import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.utils.MyBeanUtils;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.test.entity.onetomany.TestDataMain;
import com.jeeplus.modules.test.service.onetomany.TestDataMainService;

/**
 * 票务代理Controller
 * @author liugf
 * @version 2016-10-06
 */
@Controller
@RequestMapping(value = "${adminPath}/test/onetomany/testDataMain")
public class TestDataMainController extends BaseController {

	@Autowired
	private TestDataMainService testDataMainService;
	
	@ModelAttribute
	public TestDataMain get(@RequestParam(required=false) String id) {
		TestDataMain entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = testDataMainService.get(id);
		}
		if (entity == null){
			entity = new TestDataMain();
		}
		return entity;
	}
	
	/**
	 * 票务代理列表页面
	 */
	@RequiresPermissions("test:onetomany:testDataMain:list")
	@RequestMapping(value = {"list", ""})
	public String list(TestDataMain testDataMain, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TestDataMain> page = testDataMainService.findPage(new Page<TestDataMain>(request, response), testDataMain); 
		model.addAttribute("page", page);
		return "modules/test/onetomany/testDataMainList";
	}

	/**
	 * 查看，增加，编辑票务代理表单页面
	 */
	@RequiresPermissions(value={"test:onetomany:testDataMain:view","test:onetomany:testDataMain:add","test:onetomany:testDataMain:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(TestDataMain testDataMain, Model model) {
		model.addAttribute("testDataMain", testDataMain);
		return "modules/test/onetomany/testDataMainForm";
	}

	/**
	 * 保存票务代理
	 */
	@RequiresPermissions(value={"test:onetomany:testDataMain:add","test:onetomany:testDataMain:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(TestDataMain testDataMain, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, testDataMain)){
			return form(testDataMain, model);
		}
		if(!testDataMain.getIsNewRecord()){//编辑表单保存
			TestDataMain t = testDataMainService.get(testDataMain.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(testDataMain, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			testDataMainService.save(t);//保存
		}else{//新增表单保存
			testDataMainService.save(testDataMain);//保存
		}
		addMessage(redirectAttributes, "保存票务代理成功");
		return "redirect:"+Global.getAdminPath()+"/test/onetomany/testDataMain/?repage";
	}
	
	/**
	 * 删除票务代理
	 */
	@RequiresPermissions("test:onetomany:testDataMain:del")
	@RequestMapping(value = "delete")
	public String delete(TestDataMain testDataMain, RedirectAttributes redirectAttributes) {
		testDataMainService.delete(testDataMain);
		addMessage(redirectAttributes, "删除票务代理成功");
		return "redirect:"+Global.getAdminPath()+"/test/onetomany/testDataMain/?repage";
	}
	
	/**
	 * 批量删除票务代理
	 */
	@RequiresPermissions("test:onetomany:testDataMain:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			testDataMainService.delete(testDataMainService.get(id));
		}
		addMessage(redirectAttributes, "删除票务代理成功");
		return "redirect:"+Global.getAdminPath()+"/test/onetomany/testDataMain/?repage";
	}
	
}