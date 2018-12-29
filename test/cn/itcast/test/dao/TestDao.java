package cn.itcast.test.dao;

import java.io.Serializable;

import cn.itcast.test.entity.Person;

public interface TestDao {
	 //保存任意
	 public void save(Person person);
	 //根据id查询人员
	 public Person findPerson(Serializable id);
}
