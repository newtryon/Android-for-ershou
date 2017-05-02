package com.niu.bean;
/**
 * grade_info
 * @author 王飞鱼
 *
 */
public class Grade {
	private int grade_id;
	private String grade_num;
	private String grade_major;
	private String grade_college;
	public Grade(int grade_id, String grade_num, String grade_major,
			String grade_college) {
		super();
		this.grade_id = grade_id;
		this.grade_num = grade_num;
		this.grade_major = grade_major;
		this.grade_college = grade_college;
	}
	public Grade() {
		super();
	}
	public int getGrade_id() {
		return grade_id;
	}
	public void setGrade_id(int grade_id) {
		this.grade_id = grade_id;
	}
	public String getGrade_num() {
		return grade_num;
	}
	public void setGrade_num(String grade_num) {
		this.grade_num = grade_num;
	}
	public String getGrade_major() {
		return grade_major;
	}
	public void setGrade_major(String grade_major) {
		this.grade_major = grade_major;
	}
	public String getGrade_college() {
		return grade_college;
	}
	public void setGrade_college(String grade_college) {
		this.grade_college = grade_college;
	}
	@Override
	public String toString() {
		return "Grade [grade_id=" + grade_id + ", grade_num=" + grade_num
				+ ", grade_major=" + grade_major + ", grade_college="
				+ grade_college + "]";
	}
	
	
}
