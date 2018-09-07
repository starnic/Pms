package com.xian.pms.bean;

import java.util.Date;

public class Inspection {
    private Integer id;

    private String person;

    private String type;

    private Date itime;

    private String conductor;

    private String party;

    private String result;

    private String memo;

    public Inspection(Integer id, String person, String type, Date itime, String conductor, String party, String result,
			String memo) {
		super();
		this.id = id;
		this.person = person;
		this.type = type;
		this.itime = itime;
		this.conductor = conductor;
		this.party = party;
		this.result = result;
		this.memo = memo;
	}

	public Inspection() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person == null ? null : person.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getItime() {
        return itime;
    }

    public void setItime(Date itime) {
        this.itime = itime;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor == null ? null : conductor.trim();
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party == null ? null : party.trim();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}