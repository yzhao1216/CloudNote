package com.tarena.bigdata.cloudnote.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tarena.bigdata.cloudnote.dao.NotebookMybatisDao;
import com.tarena.bigdata.cloudnote.entity.Notebook;
import com.tarena.bigdata.cloudnote.entity.NotebookType;
import com.tarena.bigdata.cloudnote.util.SysEnv;
import com.tarena.bigdata.cloudnote.util.UUIDUtil;

/**
 * 用户笔记本管理逻辑层
 * 
 * @author YHT
 */

@Component("noteBookService")
@Transactional
public class NoteBookService {
	@Resource(name = "notebookMybatisDao")
	private NotebookMybatisDao notebookMybatisDao;
	public static Map<String, NotebookType> types = new HashMap<String, NotebookType>();

	/**
	 *  获取指定用户的笔记本列表
	 * @param userId：用戶id
	 * @param typeCode:笔记本类型
	 * @return
	 */
	public List<Notebook> getNotebookList(String userId, String typeCode) {
		Map<String, String> params = new HashMap<String, String>();//封装dao层参数
		params.put("cnNotebookTypeCode", typeCode);
		params.put("cnUserId", userId);
		List<Notebook> list = this.notebookMybatisDao.getNotebookList(params);//查询指定用户的指定类型笔记本列表
		return list;
	}

	/**
	 * 获取用户特殊笔记本列表（根据性能自行优化）系统启动时
	 * @param userId：用户id
	 * @return
	 */
	public Map<String, Notebook> getSpecialList(String userId) {
		Map<String, Notebook> map = new HashMap<String, Notebook>();//封装返回值
		Map<String, String> params = new HashMap<String, String>();//封装dao层参数
		params.put("cnUserId", userId);
		for (String code : SysEnv.typeCode) {//循环笔记类型，每个用户有5中笔记类型
			if (code.equals(SysEnv.NOTEBOOK_NORMAL))//如果是自己创建的笔记，跳过
				continue;
			params.put("cnNotebookTypeCode", code);//封装特殊笔记本code
			List<Notebook> list = this.notebookMybatisDao.getNotebookList(params);//获取笔记本列表
			if (list != null && list.size() > 0) {
				map.put(code, list.get(0));//要是特殊笔记本，就只有一个值
			}
		}
		return map;
	}

	/**
	 * 获取用户特殊笔记本（根据性能自行优化）系统启动时
	 * @param userId
	 * @param cnNotebookTypeCode
	 * @return
	 */
	public Notebook getSpecial(String userId, String cnNotebookTypeCode) {
		Map<String, String> params = new HashMap<String, String>();//封装dao层参数
		params.put("cnUserId", userId);
		params.put("cnNotebookTypeCode", cnNotebookTypeCode);
		List<Notebook> list = this.notebookMybatisDao.getNotebookList(params);//获取笔记本列表
		Notebook notebook = list==null?null:list.get(0);//如果是特殊笔记本，通过条件只能查出一个，取第一个
		return notebook;
	}

	/**
	 * 增加一个指定用户的笔记本
	 * @param notebook：笔记本信息
	 * @param code：类型
	 * @return
	 */
	public String createNoteBook(Notebook notebook, String code) {
			notebook.setCnNotebookId(UUIDUtil.getUId());//封装id
			// 根据code获得code对应的id
			notebook.setCnNotebookTypeId(NoteBookService.types.get(code).getCnNotebookTypeId());
			notebook.setCnNotebookCreatetime(new Date());
			this.notebookMybatisDao.createNoteBook(notebook);//增加一个指定用户的笔记本
		return notebook.getCnNotebookId();//返回筆記本id
	}

	/**
	 *  修改一个指定用户的笔记本(只能修改笔记本的名字)
	 * @param notebook：笔记本信息
	 */
	public void updateNoteBook(Notebook notebook) {
			this.notebookMybatisDao.updateNoteBook(notebook);//更新笔记本
	}

	/**
	 * 删除一个指定用户的笔记本
	 * @param cnNotebookId：笔记本id
	 */
	public void deleteNoteBook(String cnNotebookId) {
			this.notebookMybatisDao.deleteNoteBook(cnNotebookId);//删除一个笔记本
	}


	/**
	 * 根据cnNotebookTypeCode查询NotebookType
	 * @param userId：用户id
	 */
	public void initNotebooks(String userId) {
			for (String code : SysEnv.typeCode) {//便利笔记本类型
				if (code.equals(SysEnv.NOTEBOOK_NORMAL))//如果是自己创建的笔记本，就跳过
					continue;
				Notebook nb = new Notebook();//封装笔记本信息
				nb.setCnNotebookId(UUIDUtil.getUId());//id
				nb.setCnNotebookTypeId(NoteBookService.types.get(code).getCnNotebookTypeId());//type
				nb.setCnNotebookName(NoteBookService.types.get(code).getCnNotebookTypeName());// 查找笔记本名称
				nb.setCnUserId(userId);//用户id
				this.notebookMybatisDao.createNoteBook(nb);//创建4个特殊的笔记本
			}
	}

	/**
	 * @PostConstruct：初始化时加载，缓存到map里
	 * 查询NotebookTypeList
	 */
	@PostConstruct
	public void getNotebookTypeList() {
		List<NotebookType> type = null;//封装类型参数
			type = this.notebookMybatisDao.getNotebookTypeList();//查询笔记本类型
			for (NotebookType t : type) {//遍历
				types.put(t.getCnNotebookTypeCode(), t);//缓存，便于以后使用
			}
	}
	
	
	/**
	 * 通过notebookId查找笔记本
	 * @param notebookId：笔记本id
	 * @return
	 */
	public Notebook getNotebookById(String notebookId){
		Notebook notebook = notebookMybatisDao.getNoteBookById(notebookId);//通过id查找Notebook
		return notebook;
	}

}
