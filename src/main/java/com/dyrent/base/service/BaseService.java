package com.dyrent.base.service;

import com.dyrent.base.dao.QueryDao;
import com.dyrent.base.model.Page;
import com.dyrent.base.utils.ReflectUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * Service 通用方法 User: Jail Hu Date: 2014-4-10 下午18:21
 */
public abstract class BaseService<T> {

	protected Class<T> entityClass;

	@Autowired
	protected QueryDao queryDao;

	@SuppressWarnings("unchecked")
	public BaseService() {
		entityClass = ReflectUtil.getSuperClassGenricType(getClass(), 0);
	}

	/**
	 * 反馈Dao连接
	 * 
	 * @return
	 */
	public Connection getConnection() {
		return this.queryDao.getSqlSession().getConnection();
	}

	/**
	 * 获得指定记录
	 * 
	 * @param sqlId
	 *            xml的id ,如果id包含命名空间则直接用id，否则增加 T 为 命名空间,比如 A.B 则不变，不然会调整为 A.B
	 * @param obj
	 *            查询参数(对象)
	 * @return T
	 */
	public T selectOne(String sqlId, Object obj) {
		return this.queryDao.getSqlSession().selectOne(sqlId(sqlId), obj);
	}

    public T selectOneById(String sqlId, Serializable id) {
        return this.queryDao.getSqlSession().selectOne(sqlId(sqlId), id);
    }

    /**
	 * 获得列表
	 * 
	 * @param sqlId
	 *            xml的ID ,如果id包含命名空间则直接用id，否则增加 T 为 命名空间,比如 A.B 则不变，不然会调整为 A.B
	 * @return List<T>
	 */
	public List<T> selectList(String sqlId) {
		return this.queryDao.getSqlSession().selectList(sqlId(sqlId));
	}

	/**
	 * 获得列表
	 * 
	 * @param sqlId
	 *            xml的ID ,如果id包含命名空间则直接用id，否则增加 T 为 命名空间,比如 A.B 则不变，不然会调整为 A.B
	 * @param obj
	 *            查询参数(对象)
	 * @return List<T>
	 */
	public List<T> selectList(String sqlId, Object obj) {
		return this.queryDao.getSqlSession().selectList(sqlId(sqlId), obj);
	}

    /**
     * 获取结果的map 集合
     *
     * @param sqlId
     *            xml的ID ,如果id包含命名空间则直接用id，否则增加 T 为 命名空间,比如 A.B 则不变，不然会调整为 A.B
     * @param obj
     *            查询参数(对象)
     * @return  Map<String,Object>
     */
    public Map<String,Object> selectMap(String sqlId, Object obj,String key) {
        return this.queryDao.getSqlSession().selectMap(sqlId, obj, key);
    }

    /**
     * 获取结果的map 集合
     *
     * @param sqlId
     *            xml的ID ,如果id包含命名空间则直接用id，否则增加 T 为 命名空间,比如 A.B 则不变，不然会调整为 A.B
     *            查询参数(对象)
     * @return  Map<String,Object>
     */
    public Map<String,Map<String,String>> selectMap(String sqlId,String key) {
        return this.queryDao.getSqlSession().selectMap(sqlId, key);
    }
    /**
	 * 获得数据统计数
	 * 
	 * @param sqlId
	 *            xml的id ,如果id包含命名空间则直接用id，否则增加 T 为 命名空间,比如 A.B 则不变，不然会调整为 A.B
	 * @return
	 */
	public int count(String sqlId) {
		return this.queryDao.getSqlSession().selectOne(sqlId(sqlId));
	}

	/**
	 * 获得数据统计数
	 * 
	 * @param sqlId
	 *            xml的id ,如果id包含命名空间则直接用id，否则增加 T 为 命名空间,比如 A.B 则不变，不然会调整为 A.B
	 * @param obj
	 * @return
	 */
	public int count(String sqlId, Object obj) {
		return this.queryDao.getSqlSession().selectOne(sqlId(sqlId), obj);
	}

	/**
	 * 插入行数据
	 * 
	 * @param sqlId
	 *            xml的id ,如果id包含命名空间则直接用id，否则增加 T 为 命名空间,比如 A.B 则不变，不然会调整为 A.B
	 * @return
	 */
	public boolean insert(String sqlId) {
		return this.queryDao.getSqlSession().insert(sqlId(sqlId)) > 0;
	}

	/**
	 * 插入行数据
	 * 
	 * @param sqlId
	 *            xml的id ,如果id包含命名空间则直接用id，否则增加 T 为 命名空间,比如 A.B 则不变，不然会调整为 A.B
	 * @param obj
	 * @return
	 */
	public boolean insert(String sqlId, Object obj) {
		return this.queryDao.getSqlSession().insert(sqlId(sqlId), obj) > 0;
	}

    /**
     * 插入行数据，并返回主键id
     * @param sqlId xml的id ,如果id包含命名空间则直接用id，否则增加 T 为 命名空间,比如 A.B 则不变，不然会调整为 A.B
     * @param obj 对象名称
     *  mysql xml 返回主键写法 <selectKey resultType="java.lang.Integer"  order="AFTER" keyProperty="id" >
    SELECT LAST_INSERT_ID() AS ID
    </selectKey>
     * @return 主键id
     */
    public int insertAndReturnId(String sqlId, Object obj){
        return this.queryDao.getSqlSession().insert(sqlId(sqlId),obj);
    }


    /**
	 * 更新行数据
	 * 
	 * @param sqlId
	 *            xml的id ,如果id包含命名空间则直接用id，否则增加 T 为 命名空间,比如 A.B 则不变，不然会调整为 A.B
	 * @return
	 */
	public boolean update(String sqlId) {
		return this.queryDao.getSqlSession().update(sqlId(sqlId)) > 0;
	}

	/**
	 * 更新行数据
	 * 
	 * @param sqlId
	 *            xml的id ,如果id包含命名空间则直接用id，否则增加 T 为 命名空间,比如 A.B 则不变，不然会调整为 A.B
	 * @param obj
	 * @return
	 */
	public boolean update(String sqlId, Object obj) {
		return this.queryDao.getSqlSession().update(sqlId(sqlId), obj) > 0;
	}

	/**
	 * 删除行数据
	 * 
	 * @param sqlId
	 *            xml的id ,如果id包含命名空间则直接用id，否则增加 T 为 命名空间,比如 A.B 则不变，不然会调整为 A.B
	 * @return
	 */
	public boolean delete(String sqlId) {
		return this.queryDao.getSqlSession().delete(sqlId(sqlId)) > 0;
	}

	/**
	 * 删除行数据
	 * 
	 * @param sqlId
	 *            xml的id ,如果id包含命名空间则直接用id，否则增加 T 为 命名空间,比如 A.B 则不变，不然会调整为 A.B
	 * @param obj
	 * @return
	 */
	public boolean delete(String sqlId, Object obj) {
		return this.queryDao.getSqlSession().delete(sqlId(sqlId), obj) > 0;
	}

	/**
	 * 批量删除请到 xml 中使用 《foreach collection="list" item="item" index="index"
	 * separator="," 》 《/foreach》实现
	 * 
	 * @return
	 */
	@Deprecated
	public void batchInsert() {
	}

	/**
	 * 获得分页数据
	 * @param sqlId  xml的id ,如果id包含命名空间则直接用id，否则增加 T 为 命名空间,比如 A.B 则不变，不然会调整为 A.B
	 * @param page 分页对象
	 * @return
	 */
	public List<T> selectByPage(String sqlId, Page page) {
		return this.queryDao.getSqlSession().selectList(sqlId(sqlId), page);
		//return this.queryDao.getSqlSession().selectList(sqlId(sqlId), page,
		//		new RowBounds(page.getPageNo() * page.getPageSize(), page.getPageSize()));
	}

	/**
	 * 生成ibatis的sqlId namespace.sqlId
	 * 
	 * @param sqlId
	 * @return
	 */
	protected final String sqlId(String sqlId) {
		return sqlId.contains(".") ? sqlId
				: (entityClass.getSimpleName() + "." + sqlId);
	}


}
