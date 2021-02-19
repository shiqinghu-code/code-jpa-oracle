package com.qing.hu.manager.base.impl;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.hibernate.service.spi.ServiceException;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qing.hu.entity.base.BaseEntity;
import com.qing.hu.manager.base.IBaseManager;
import com.qing.hu.mapper.base.BaseMapper;

import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

/**
 * @Description: 服务层基类实现类 注：自定义需要保存对象日志的操作方法
 */
@Slf4j
public abstract class BaseManager<T extends BaseEntity, ID, EXAMPLE> implements IBaseManager<T, ID, EXAMPLE> {

	private Class<T> modelClass; // 当前泛型真实类型的Class

	// 定义抽象方法getBaseMapper获取当前实体Mapper对象
	protected abstract BaseMapper<T> getBaseMapper();

	// @Autowired //这种方式注入mapper也可以
	// protected BaseMapper<T> mapper;

	@Transactional(rollbackOn = Exception.class)
	public int save(T record) {
		record.setDelFlg("0");
		record.setInsTime(new Timestamp(System.currentTimeMillis()));
		record.setInsUserId("新增de用户名");
		record.setUpdTime(new Timestamp(System.currentTimeMillis()));
		record.setUpdUserId("更新de用户名");

		int res = getBaseMapper().insert(record);
		return res;
	}

	@Transactional(rollbackOn = Exception.class)
	public int saveSelective(T record) {
		record.setDelFlg("0");
		record.setInsTime(new Timestamp(System.currentTimeMillis()));
		record.setInsUserId("新增de用户名");
		record.setUpdTime(new Timestamp(System.currentTimeMillis()));
		record.setUpdUserId("更新de用户名");

		int res = getBaseMapper().insertSelective(record);
		return res;
	}

	@Transactional(rollbackOn = Exception.class)
	public int saveList(java.util.List<? extends T> list) {

		int res = getBaseMapper().insertList(list);
		return res;
	}

	@Transactional(rollbackOn = Exception.class)
	public int delete(T record) {
		int res = getBaseMapper().delete(record);
		return res;
	}

	@Transactional(rollbackOn = Exception.class)
	public int deleteByExample(T record, EXAMPLE example) {
		int res = getBaseMapper().deleteByExample(example);
		return res;
	}

	@Transactional(rollbackOn = Exception.class)
	public int deleteByPrimaryKey(T record, ID key) {
		int res = getBaseMapper().deleteByPrimaryKey(key);
		return res;
	}

	@Transactional(rollbackOn = Exception.class)
	public int updateByExample(T record, EXAMPLE example) {
		record.setUpdTime(new Timestamp(System.currentTimeMillis()));
		record.setUpdUserId("更新de用户名");

		int res = getBaseMapper().updateByExample(record, example);
		return res;
	}

	@Transactional(rollbackOn = Exception.class)
	public int updateByExampleSelective(T record, EXAMPLE example) {
		record.setUpdTime(new Timestamp(System.currentTimeMillis()));
		record.setUpdUserId("更新de用户名");

		int res = getBaseMapper().updateByExampleSelective(record, example);
		return res;
	}

	@Transactional(rollbackOn = Exception.class)
	public int updateByPrimaryKey(T record) {
		record.setUpdTime(new Timestamp(System.currentTimeMillis()));
		record.setUpdUserId("更新de用户名");

		int res = getBaseMapper().updateByPrimaryKey(record);
		return res;
	}

	@Transactional(rollbackOn = Exception.class)
	public int updateByPrimaryKeySelective(T record) {
		record.setUpdTime(new Timestamp(System.currentTimeMillis()));
		record.setUpdUserId("更新de用户名");

		int res = getBaseMapper().updateByPrimaryKeySelective(record);
		return res;
	}

	public PageInfo<T> getPageList(Example example, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<T> list = getBaseMapper().selectByExample(example);
		PageInfo<T> pageInfo = new PageInfo<T>(list);
		return pageInfo;
	}

	@Override
	public T findById(ID id) {
		T t = getBaseMapper().selectByPrimaryKey(id);
		return t;
	}

	@Override
	public T findBy(String fieldName, Object value) throws TooManyResultsException {
		try {
			T model = modelClass.newInstance();
			Field field = modelClass.getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(model, value);
			return getBaseMapper().selectOne(model);
		} catch (ReflectiveOperationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public List<T> findByIds(String ids) {
		return getBaseMapper().selectByIds(ids);
	}

	@Override
	public List<T> findByCondition(Condition condition) {
		return getBaseMapper().selectByCondition(condition);
	}

	@Override
	public List<T> findAll() {
		System.out.println(getBaseMapper());
		return getBaseMapper().selectAll();
	}

}
