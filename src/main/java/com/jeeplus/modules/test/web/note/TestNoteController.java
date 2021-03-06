/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.test.web.note;

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
import com.jeeplus.modules.test.entity.note.TestNote;
import com.jeeplus.modules.test.service.note.TestNoteService;

/**
 * 富文本测试Controller
 * @author liugf
 * @version 2016-10-04
 */
@Controller
@RequestMapping(value = "${adminPath}/test/note/testNote")
public class TestNoteController extends BaseController {

	@Autowired
	private TestNoteService testNoteService;
	
	@ModelAttribute
	public TestNote get(@RequestParam(required=false) String id) {
		TestNote entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = testNoteService.get(id);
		}
		if (entity == null){
			entity = new TestNote();
		}
		return entity;
	}
	
	/**
	 * 富文本测试列表页面
	 */
	@RequiresPermissions("test:note:testNote:list")
	@RequestMapping(value = {"list", ""})
	public String list(TestNote testNote, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TestNote> page = testNoteService.findPage(new Page<TestNote>(request, response), testNote); 
		model.addAttribute("page", page);
		return "modules/test/note/testNoteList";
	}

	/**
	 * 查看，增加，编辑富文本测试表单页面
	 */
	@RequiresPermissions(value={"test:note:testNote:view","test:note:testNote:add","test:note:testNote:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(TestNote testNote, Model model) {
		model.addAttribute("testNote", testNote);
		return "modules/test/note/testNoteForm";
	}

	/**
	 * 保存富文本测试
	 */
	@RequiresPermissions(value={"test:note:testNote:add","test:note:testNote:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(TestNote testNote, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, testNote)){
			return form(testNote, model);
		}
		if(!testNote.getIsNewRecord()){//编辑表单保存
			TestNote t = testNoteService.get(testNote.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(testNote, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			testNoteService.save(t);//保存
		}else{//新增表单保存
			testNoteService.save(testNote);//保存
		}
		addMessage(redirectAttributes, "保存富文本测试成功");
		return "redirect:"+Global.getAdminPath()+"/test/note/testNote/?repage";
	}
	
	/**
	 * 删除富文本测试
	 */
	@RequiresPermissions("test:note:testNote:del")
	@RequestMapping(value = "delete")
	public String delete(TestNote testNote, RedirectAttributes redirectAttributes) {
		testNoteService.delete(testNote);
		addMessage(redirectAttributes, "删除富文本测试成功");
		return "redirect:"+Global.getAdminPath()+"/test/note/testNote/?repage";
	}
	
	/**
	 * 批量删除富文本测试
	 */
	@RequiresPermissions("test:note:testNote:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			testNoteService.delete(testNoteService.get(id));
		}
		addMessage(redirectAttributes, "删除富文本测试成功");
		return "redirect:"+Global.getAdminPath()+"/test/note/testNote/?repage";
	}
	
	
	

}