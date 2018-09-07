package com.xian.pms.bean;

import java.util.Date;

public class Maintain {
    private Integer id;

    private String thing;

    private String status;

    private String homesbumber;

    private Date sdate;

    private Date rdate;

    private Integer tcost;

    private Integer scost;

    private String maintainer;

    private String smemo;

    public Maintain(Integer id, String thing, String status, String homesbumber, Date sdate, Date rdate, Integer tcost,
			Integer scost, String maintainer, String smemo) {
		super();
		this.id = id;
		this.thing = thing;
		this.status = status;
		this.homesbumber = homesbumber;
		this.sdate = sdate;
		this.rdate = rdate;
		this.tcost = tcost;
		this.scost = scost;
		this.maintainer = maintainer;
		this.smemo = smemo;
	}

	public Maintain() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getThing() {
        return thing;
    }

    public void setThing(String thing) {
        this.thing = thing == null ? null : thing.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getHomesbumber() {
        return homesbumber;
    }

    public void setHomesbumber(String homesbumber) {
        this.homesbumber = homesbumber == null ? null : homesbumber.trim();
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }

    public Integer getTcost() {
        return tcost;
    }

    public void setTcost(Integer tcost) {
        this.tcost = tcost;
    }

    public Integer getScost() {
        return scost;
    }

    public void setScost(Integer scost) {
        this.scost = scost;
    }

    public String getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(String maintainer) {
        this.maintainer = maintainer == null ? null : maintainer.trim();
    }

    public String getSmemo() {
        return smemo;
    }

    public void setSmemo(String smemo) {
        this.smemo = smemo == null ? null : smemo.trim();
    }
}