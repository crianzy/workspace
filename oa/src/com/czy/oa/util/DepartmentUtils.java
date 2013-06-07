package com.czy.oa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.czy.oa.domain.Department;

public class DepartmentUtils {

	/** 获取转换成可之间展现的 department 集合 */
	public static List<Department> getAllDepartmentsTreeView(
			List<Department> topList) {
		List<Department> treeDepartments = new ArrayList<Department>();
		walkDepartmentTrees(topList, treeDepartments, "┣");
		for (Department department : treeDepartments) {
			System.out.println(department.getName());
		}
		return treeDepartments;
	}

	/** 递归遍历整个 department 放到制定树中 并修改相应要展现的 name属性 */
	private static void walkDepartmentTrees(Collection<Department> topList,
			List<Department> treeDepartments, String prefix) {
		for (Department department : topList) {

			Department departmentTemp = new Department();
			// 把 id 那么 复制到 departmentTemp 在添加到 treeDepartments中
			departmentTemp.setId(department.getId());
			departmentTemp.setName(prefix + department.getName());
			treeDepartments.add(departmentTemp);

			walkDepartmentTrees(department.getChildren(), treeDepartments, "　"
					+ prefix);
		}
	}

}
