package com.tarena.bigdata.cloudnote.util;

import java.io.Serializable;

public class SysEnv implements Serializable {
	private static final long serialVersionUID = -8590844343788255616L;
	/** 普通笔记本 **/
	public static String NOTEBOOK_NORMAL="normal";
	/** 回收站笔记本 **/
	public final static String NOTEBOOK_RECYCLE="recycle";
	/** 活动笔记本 **/
	public final static String NOTEBOOK_ACTION="action";
	/** 收藏笔记本 **/
	public final static String NOTEBOOK_FAVORITES="favorites";
	/** 收藏笔记本 **/
	public final static String NOTEBOOK_PUSH="push";
	
	public static String[] typeCode = {SysEnv.NOTEBOOK_NORMAL,SysEnv.NOTEBOOK_ACTION,SysEnv.NOTEBOOK_FAVORITES,SysEnv.NOTEBOOK_RECYCLE,SysEnv.NOTEBOOK_PUSH};
}
