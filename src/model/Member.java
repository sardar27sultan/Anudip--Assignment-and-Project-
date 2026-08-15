package model;

import java.sql.Date;

public class Member {

    private int memberId;
    private String fullName;
    private String gender;
    private int age;
    private String phone;
    
    public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private String email;
    private String address;
    private int planId;
    private Date joinDate;
    private Date expiryDate;
    private String status;
    
    public Member() {

    }
    
    public Member(int memberId, String fullName, String gender, int age,
            String phone, String email, String address,
            int planId, Date joinDate, Date expiryDate, String status) {

  this.memberId = memberId;
  this.fullName = fullName;
  this.gender = gender;
  this.age = age;
  this.phone = phone;
  this.email = email;
  this.address = address;
  this.planId = planId;
  this.joinDate = joinDate;
  this.expiryDate = expiryDate;
  this.status = status;
}

    @Override
	public String toString() {
		return "Member [memberId=" + memberId + ", fullName=" + fullName + ", gender=" + gender + ", age=" + age
				+ ", phone=" + phone + ", email=" + email + ", address=" + address + ", planId=" + planId
				+ ", joinDate=" + joinDate + ", expiryDate=" + expiryDate + ", status=" + status + "]";
	}

}