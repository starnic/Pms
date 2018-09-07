package com.xian.pms.bean;

public class House {
    private Integer id;

    private String num;

    private String dep;

    private String type;

    private String area;

    private String sell;

    private String unit;

    private String floor;

    private String direction;

    private String memo;

    private String ownerid;
    
    public House() {
		super();
		// TODO Auto-generated constructor stub
	}

	public House(Integer id, String num, String dep, String type, String area, String sell, String unit, String floor,
			String direction, String memo, String ownerid) {
		super();
		this.id = id;
		this.num = num;
		this.dep = dep;
		this.type = type;
		this.area = area;
		this.sell = sell;
		this.unit = unit;
		this.floor = floor;
		this.direction = direction;
		this.memo = memo;
		this.ownerid = ownerid;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep == null ? null : dep.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell == null ? null : sell.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor == null ? null : floor.trim();
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction == null ? null : direction.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid == null ? null : ownerid.trim();
    }
}