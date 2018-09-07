package com.xian.pms.bean;


import org.hibernate.validator.constraints.NotEmpty;

public class PmAdmin {
    private Integer id;
    
    private String name;
    
    @NotEmpty(message=" ‰»Î√‹¬Î")
    private String password;

    private String gender;
    
    private String age;

    private String tel;
    
    private String phone;
    
    private String addr;

    private String memo;

    public PmAdmin(Integer id, String name, String password, String gender, String age, String tel, String phone,
			String addr, String memo) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.gender = gender;
		this.age = age;
		this.tel = tel;
		this.phone = phone;
		this.addr = addr;
		this.memo = memo;
	}

	public PmAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}