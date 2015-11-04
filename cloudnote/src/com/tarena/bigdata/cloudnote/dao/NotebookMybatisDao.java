package com.tarena.bigdata.cloudnote.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tarena.bigdata.cloudnote.entity.Notebook;
import com.tarena.bigdata.cloudnote.entity.NotebookType;



@MyBatisRepository
public interface NotebookMybatisDao {
	//查询指定用户的指定类型笔记本
	public List<Notebook> getNotebookList(Map<String, String> params);
	//增加一个指定用户的笔记本
	public void createNoteBook(Notebook notebook);
	//修改一个笔记本(只能修改笔记本的名字)
	public void updateNoteBook(Notebook notebook);
	//删除一个笔记本
	public void deleteNoteBook(String cnNotebookId);
	//根据cnNotebookTypeCode查询NotebookType
	public NotebookType getNotebookType(String cnNotebookTypeCode);
	//查询NotebookTypeList
	public List<NotebookType> getNotebookTypeList();
	//通过id查找Notebook
	public Notebook getNoteBookById(String notebookId);
}
