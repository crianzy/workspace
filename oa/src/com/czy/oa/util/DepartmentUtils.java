package com.czy.oa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.czy.oa.domain.Department;

public class DepartmentUtils {

	/** ��ȡת���ɿ�֮��չ�ֵ� department ���� */
	public static List<Department> getAllDepartmentsTreeView(
			List<Department> topList) {
		List<Department> treeDepartments = new ArrayList<Department>();
		walkDepartmentTrees(topList, treeDepartments, "��");
		for (Department department : treeDepartments) {
			System.out.println(department.getName());
		}
		return treeDepartments;
	}

	/** �ݹ�������� department �ŵ��ƶ����� ���޸���ӦҪչ�ֵ� name���� */
	private static void walkDepartmentTrees(Collection<Department> topList,
			List<Department> treeDepartments, String prefix) {
		for (Department department : topList) {

			Department departmentTemp = new Department();
			// �� id ��ô ���Ƶ� departmentTemp ����ӵ� treeDepartments��
			departmentTemp.setId(department.getId());
			departmentTemp.setName(prefix + department.getName());
			treeDepartments.add(departmentTemp);

			walkDepartmentTrees(department.getChildren(), treeDepartments, "��"
					+ prefix);
		}
	}

}
