package com.tarena.bigdata.cloudnote.service;







import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;





import com.tarena.bigdata.cloudnote.dao.UserMybatisDao;
import com.tarena.bigdata.cloudnote.entity.User;

/**
 * 用户管理业务类.
 * 
 * @author Meng Fanliang
 */
// Spring Service Bean的标识.
@Component
@Transactional
public class AccountService {


	@Autowired
	private UserMybatisDao userMybatisDao;

	/**
	 * 按登录名查询用户.
	 */
	public User findUserByLoginName(String loginName) {
		User nowUser = userMybatisDao.getUserByLoginNamePsd(null);
		System.out.println("CnUserPassword:"+nowUser.getCnUserPassword());
		return nowUser;
	}


}
