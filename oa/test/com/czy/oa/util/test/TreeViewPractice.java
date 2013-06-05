package com.czy.oa.util.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.czy.oa.domain.Department;

/**
 * ˵��������ʹ�ö��ѭ���ķ�ʽ����Ϊ��Ҫ��֧������㡣
 */
public class TreeViewPractice {

	/**
	 * ��ϰһ����ӡ���ж��㲿�ż������ﲿ�ŵ���Ϣ�����ƣ� ��ʾ��������һ�� ��ӡ������ ����Ϣ �ķ���
	 * 
	 * Ҫ���ӡ����Ч����
	 * 
	 * <pre>
	 * �г���
	 * ������
	 * ҵ��
	 * ҵ��һ��
	 * ҵ�����
	 * ������
	 * ����һ��
	 * ��������
	 * </pre>
	 */
	@Test
	public void printAllDepts_1() {
		List<Department> topList = findTopLevelDepartmentList();
		for (Department department : topList) {
			showTree(department);
		}
	}

	@Test
	public void printAllDepts_3() {
		List<Department> topList = findTopLevelDepartmentList();
		showTreeList(topList);
	}

	// ��ʾһ��������Ϣ
	private void showTree(Department top) {
		System.out.println(top.getName());
		Set<Department> children = top.getChildren();
		if (children != null && !children.isEmpty()) {
			for (Department department : children) {
				showTree(department);
			}
		}
	}

	// ��ʾ���������Ϣ
	private void showTreeList(Collection<Department> topList) {
		for (Department department : topList) {
			System.out.println(department.getName());
			showTreeList(department.getChildren());
		}
	}

	/**
	 * ��ϰ������ӡ���ж��㲿�ż������ﲿ�ŵ���Ϣ�����ƣ����ò�ͬ��������ʾ��Σ�ʹ��ȫ�ǿո񣩡�<br>
	 * �Ӳ��ŵ�����ǰ���ϼ����Ŷ�һ���ո���㲿�ŵ�����ǰû�пո� ��ʾ��������һ����ӡ���ż��������в�����Ϣ�ķ���
	 * 
	 * Ҫ���ӡ����Ч����
	 * 
	 * <pre>
	 * ���г���
	 *    ��������
	 *    ��ҵ��
	 *       ��ҵ��һ��
	 *       ��ҵ�����
	 * �ǿ�����
	 *    �ǿ���һ��
	 *    �ǿ�������
	 * </pre>
	 */
	@Test
	public void printAllDepts_2() {
		List<Department> topList = findTopLevelDepartmentList();
		showTreeList_2(topList,"��");
	}

	// ��ʾ���������Ϣ
	private void showTreeList_2(Collection<Department> topList, String prefix) {
		for (Department department : topList) {
			System.out.println(prefix+department.getName());
			showTreeList_2(department.getChildren(),"��"+prefix);
		}
	}

	/**
	 * �ṹ���£�
	 * 
	 * <pre>
	 * ���г���
	 *    ��������
	 *    ��ҵ��
	 *       ��ҵ��һ��
	 *       ��ҵ�����
	 * �ǿ�����
	 *    �ǿ���һ��
	 *    �ǿ�������
	 * </pre>
	 * 
	 * @return �������Ĳ��ŵ��б�
	 */
	public static List<Department> findTopLevelDepartmentList() {
		Department dept_1_1 = new Department();
		dept_1_1.setId(new Long(11));
		dept_1_1.setName("������");

		Department dept_1_2 = new Department();
		dept_1_2.setId(new Long(12));
		dept_1_2.setName("ҵ��");

		Department dept_1_2_1 = new Department();
		dept_1_2_1.setId(new Long(121));
		dept_1_2_1.setName("ҵ��һ��");

		Department dept_1_2_2 = new Department();
		dept_1_2_2.setId(new Long(122));
		dept_1_2_2.setName("ҵ�����");

		dept_1_2_1.setParent(dept_1_2);
		dept_1_2_2.setParent(dept_1_2);
		Set<Department> children_0 = new LinkedHashSet<Department>();
		children_0.add(dept_1_2_1);
		children_0.add(dept_1_2_2);
		dept_1_2.setChildren(children_0);

		// ================================

		Department dept_1 = new Department();
		dept_1.setId(new Long(1));
		dept_1.setName("�г���");

		dept_1_1.setParent(dept_1);
		dept_1_2.setParent(dept_1);
		Set<Department> children_1 = new LinkedHashSet<Department>();
		children_1.add(dept_1_1);
		children_1.add(dept_1_2);
		dept_1.setChildren(children_1);

		// ---

		Department dept_2_1 = new Department();
		dept_2_1.setId(new Long(21));
		dept_2_1.setName("����һ��");

		Department dept_2_2 = new Department();
		dept_2_2.setId((new Long(22)));
		dept_2_2.setName("��������");

		Department dept_2 = new Department();
		dept_2.setId(new Long(2));
		dept_2.setName("������");

		dept_2_1.setParent(dept_2);
		dept_2_2.setParent(dept_2);
		Set<Department> children_2 = new LinkedHashSet<Department>();
		children_2.add(dept_2_1);
		children_2.add(dept_2_2);
		dept_2.setChildren(children_2);

		// ---

		List<Department> depts = new ArrayList<Department>();
		depts.add(dept_1);
		depts.add(dept_2);
		return depts;
	}

}
