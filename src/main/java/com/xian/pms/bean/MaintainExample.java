package com.xian.pms.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MaintainExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MaintainExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andThingIsNull() {
            addCriterion("thing is null");
            return (Criteria) this;
        }

        public Criteria andThingIsNotNull() {
            addCriterion("thing is not null");
            return (Criteria) this;
        }

        public Criteria andThingEqualTo(String value) {
            addCriterion("thing =", value, "thing");
            return (Criteria) this;
        }

        public Criteria andThingNotEqualTo(String value) {
            addCriterion("thing <>", value, "thing");
            return (Criteria) this;
        }

        public Criteria andThingGreaterThan(String value) {
            addCriterion("thing >", value, "thing");
            return (Criteria) this;
        }

        public Criteria andThingGreaterThanOrEqualTo(String value) {
            addCriterion("thing >=", value, "thing");
            return (Criteria) this;
        }

        public Criteria andThingLessThan(String value) {
            addCriterion("thing <", value, "thing");
            return (Criteria) this;
        }

        public Criteria andThingLessThanOrEqualTo(String value) {
            addCriterion("thing <=", value, "thing");
            return (Criteria) this;
        }

        public Criteria andThingLike(String value) {
            addCriterion("thing like", value, "thing");
            return (Criteria) this;
        }

        public Criteria andThingNotLike(String value) {
            addCriterion("thing not like", value, "thing");
            return (Criteria) this;
        }

        public Criteria andThingIn(List<String> values) {
            addCriterion("thing in", values, "thing");
            return (Criteria) this;
        }

        public Criteria andThingNotIn(List<String> values) {
            addCriterion("thing not in", values, "thing");
            return (Criteria) this;
        }

        public Criteria andThingBetween(String value1, String value2) {
            addCriterion("thing between", value1, value2, "thing");
            return (Criteria) this;
        }

        public Criteria andThingNotBetween(String value1, String value2) {
            addCriterion("thing not between", value1, value2, "thing");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andHomesbumberIsNull() {
            addCriterion("homesbumber is null");
            return (Criteria) this;
        }

        public Criteria andHomesbumberIsNotNull() {
            addCriterion("homesbumber is not null");
            return (Criteria) this;
        }

        public Criteria andHomesbumberEqualTo(String value) {
            addCriterion("homesbumber =", value, "homesbumber");
            return (Criteria) this;
        }

        public Criteria andHomesbumberNotEqualTo(String value) {
            addCriterion("homesbumber <>", value, "homesbumber");
            return (Criteria) this;
        }

        public Criteria andHomesbumberGreaterThan(String value) {
            addCriterion("homesbumber >", value, "homesbumber");
            return (Criteria) this;
        }

        public Criteria andHomesbumberGreaterThanOrEqualTo(String value) {
            addCriterion("homesbumber >=", value, "homesbumber");
            return (Criteria) this;
        }

        public Criteria andHomesbumberLessThan(String value) {
            addCriterion("homesbumber <", value, "homesbumber");
            return (Criteria) this;
        }

        public Criteria andHomesbumberLessThanOrEqualTo(String value) {
            addCriterion("homesbumber <=", value, "homesbumber");
            return (Criteria) this;
        }

        public Criteria andHomesbumberLike(String value) {
            addCriterion("homesbumber like", value, "homesbumber");
            return (Criteria) this;
        }

        public Criteria andHomesbumberNotLike(String value) {
            addCriterion("homesbumber not like", value, "homesbumber");
            return (Criteria) this;
        }

        public Criteria andHomesbumberIn(List<String> values) {
            addCriterion("homesbumber in", values, "homesbumber");
            return (Criteria) this;
        }

        public Criteria andHomesbumberNotIn(List<String> values) {
            addCriterion("homesbumber not in", values, "homesbumber");
            return (Criteria) this;
        }

        public Criteria andHomesbumberBetween(String value1, String value2) {
            addCriterion("homesbumber between", value1, value2, "homesbumber");
            return (Criteria) this;
        }

        public Criteria andHomesbumberNotBetween(String value1, String value2) {
            addCriterion("homesbumber not between", value1, value2, "homesbumber");
            return (Criteria) this;
        }

        public Criteria andSdateIsNull() {
            addCriterion("sdate is null");
            return (Criteria) this;
        }

        public Criteria andSdateIsNotNull() {
            addCriterion("sdate is not null");
            return (Criteria) this;
        }

        public Criteria andSdateEqualTo(Date value) {
            addCriterionForJDBCDate("sdate =", value, "sdate");
            return (Criteria) this;
        }

        public Criteria andSdateNotEqualTo(Date value) {
            addCriterionForJDBCDate("sdate <>", value, "sdate");
            return (Criteria) this;
        }

        public Criteria andSdateGreaterThan(Date value) {
            addCriterionForJDBCDate("sdate >", value, "sdate");
            return (Criteria) this;
        }

        public Criteria andSdateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("sdate >=", value, "sdate");
            return (Criteria) this;
        }

        public Criteria andSdateLessThan(Date value) {
            addCriterionForJDBCDate("sdate <", value, "sdate");
            return (Criteria) this;
        }

        public Criteria andSdateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("sdate <=", value, "sdate");
            return (Criteria) this;
        }

        public Criteria andSdateIn(List<Date> values) {
            addCriterionForJDBCDate("sdate in", values, "sdate");
            return (Criteria) this;
        }

        public Criteria andSdateNotIn(List<Date> values) {
            addCriterionForJDBCDate("sdate not in", values, "sdate");
            return (Criteria) this;
        }

        public Criteria andSdateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("sdate between", value1, value2, "sdate");
            return (Criteria) this;
        }

        public Criteria andSdateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("sdate not between", value1, value2, "sdate");
            return (Criteria) this;
        }

        public Criteria andRdateIsNull() {
            addCriterion("rdate is null");
            return (Criteria) this;
        }

        public Criteria andRdateIsNotNull() {
            addCriterion("rdate is not null");
            return (Criteria) this;
        }

        public Criteria andRdateEqualTo(Date value) {
            addCriterionForJDBCDate("rdate =", value, "rdate");
            return (Criteria) this;
        }

        public Criteria andRdateNotEqualTo(Date value) {
            addCriterionForJDBCDate("rdate <>", value, "rdate");
            return (Criteria) this;
        }

        public Criteria andRdateGreaterThan(Date value) {
            addCriterionForJDBCDate("rdate >", value, "rdate");
            return (Criteria) this;
        }

        public Criteria andRdateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("rdate >=", value, "rdate");
            return (Criteria) this;
        }

        public Criteria andRdateLessThan(Date value) {
            addCriterionForJDBCDate("rdate <", value, "rdate");
            return (Criteria) this;
        }

        public Criteria andRdateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("rdate <=", value, "rdate");
            return (Criteria) this;
        }

        public Criteria andRdateIn(List<Date> values) {
            addCriterionForJDBCDate("rdate in", values, "rdate");
            return (Criteria) this;
        }

        public Criteria andRdateNotIn(List<Date> values) {
            addCriterionForJDBCDate("rdate not in", values, "rdate");
            return (Criteria) this;
        }

        public Criteria andRdateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("rdate between", value1, value2, "rdate");
            return (Criteria) this;
        }

        public Criteria andRdateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("rdate not between", value1, value2, "rdate");
            return (Criteria) this;
        }

        public Criteria andTcostIsNull() {
            addCriterion("tcost is null");
            return (Criteria) this;
        }

        public Criteria andTcostIsNotNull() {
            addCriterion("tcost is not null");
            return (Criteria) this;
        }

        public Criteria andTcostEqualTo(Integer value) {
            addCriterion("tcost =", value, "tcost");
            return (Criteria) this;
        }

        public Criteria andTcostNotEqualTo(Integer value) {
            addCriterion("tcost <>", value, "tcost");
            return (Criteria) this;
        }

        public Criteria andTcostGreaterThan(Integer value) {
            addCriterion("tcost >", value, "tcost");
            return (Criteria) this;
        }

        public Criteria andTcostGreaterThanOrEqualTo(Integer value) {
            addCriterion("tcost >=", value, "tcost");
            return (Criteria) this;
        }

        public Criteria andTcostLessThan(Integer value) {
            addCriterion("tcost <", value, "tcost");
            return (Criteria) this;
        }

        public Criteria andTcostLessThanOrEqualTo(Integer value) {
            addCriterion("tcost <=", value, "tcost");
            return (Criteria) this;
        }

        public Criteria andTcostIn(List<Integer> values) {
            addCriterion("tcost in", values, "tcost");
            return (Criteria) this;
        }

        public Criteria andTcostNotIn(List<Integer> values) {
            addCriterion("tcost not in", values, "tcost");
            return (Criteria) this;
        }

        public Criteria andTcostBetween(Integer value1, Integer value2) {
            addCriterion("tcost between", value1, value2, "tcost");
            return (Criteria) this;
        }

        public Criteria andTcostNotBetween(Integer value1, Integer value2) {
            addCriterion("tcost not between", value1, value2, "tcost");
            return (Criteria) this;
        }

        public Criteria andScostIsNull() {
            addCriterion("scost is null");
            return (Criteria) this;
        }

        public Criteria andScostIsNotNull() {
            addCriterion("scost is not null");
            return (Criteria) this;
        }

        public Criteria andScostEqualTo(Integer value) {
            addCriterion("scost =", value, "scost");
            return (Criteria) this;
        }

        public Criteria andScostNotEqualTo(Integer value) {
            addCriterion("scost <>", value, "scost");
            return (Criteria) this;
        }

        public Criteria andScostGreaterThan(Integer value) {
            addCriterion("scost >", value, "scost");
            return (Criteria) this;
        }

        public Criteria andScostGreaterThanOrEqualTo(Integer value) {
            addCriterion("scost >=", value, "scost");
            return (Criteria) this;
        }

        public Criteria andScostLessThan(Integer value) {
            addCriterion("scost <", value, "scost");
            return (Criteria) this;
        }

        public Criteria andScostLessThanOrEqualTo(Integer value) {
            addCriterion("scost <=", value, "scost");
            return (Criteria) this;
        }

        public Criteria andScostIn(List<Integer> values) {
            addCriterion("scost in", values, "scost");
            return (Criteria) this;
        }

        public Criteria andScostNotIn(List<Integer> values) {
            addCriterion("scost not in", values, "scost");
            return (Criteria) this;
        }

        public Criteria andScostBetween(Integer value1, Integer value2) {
            addCriterion("scost between", value1, value2, "scost");
            return (Criteria) this;
        }

        public Criteria andScostNotBetween(Integer value1, Integer value2) {
            addCriterion("scost not between", value1, value2, "scost");
            return (Criteria) this;
        }

        public Criteria andMaintainerIsNull() {
            addCriterion("maintainer is null");
            return (Criteria) this;
        }

        public Criteria andMaintainerIsNotNull() {
            addCriterion("maintainer is not null");
            return (Criteria) this;
        }

        public Criteria andMaintainerEqualTo(String value) {
            addCriterion("maintainer =", value, "maintainer");
            return (Criteria) this;
        }

        public Criteria andMaintainerNotEqualTo(String value) {
            addCriterion("maintainer <>", value, "maintainer");
            return (Criteria) this;
        }

        public Criteria andMaintainerGreaterThan(String value) {
            addCriterion("maintainer >", value, "maintainer");
            return (Criteria) this;
        }

        public Criteria andMaintainerGreaterThanOrEqualTo(String value) {
            addCriterion("maintainer >=", value, "maintainer");
            return (Criteria) this;
        }

        public Criteria andMaintainerLessThan(String value) {
            addCriterion("maintainer <", value, "maintainer");
            return (Criteria) this;
        }

        public Criteria andMaintainerLessThanOrEqualTo(String value) {
            addCriterion("maintainer <=", value, "maintainer");
            return (Criteria) this;
        }

        public Criteria andMaintainerLike(String value) {
            addCriterion("maintainer like", value, "maintainer");
            return (Criteria) this;
        }

        public Criteria andMaintainerNotLike(String value) {
            addCriterion("maintainer not like", value, "maintainer");
            return (Criteria) this;
        }

        public Criteria andMaintainerIn(List<String> values) {
            addCriterion("maintainer in", values, "maintainer");
            return (Criteria) this;
        }

        public Criteria andMaintainerNotIn(List<String> values) {
            addCriterion("maintainer not in", values, "maintainer");
            return (Criteria) this;
        }

        public Criteria andMaintainerBetween(String value1, String value2) {
            addCriterion("maintainer between", value1, value2, "maintainer");
            return (Criteria) this;
        }

        public Criteria andMaintainerNotBetween(String value1, String value2) {
            addCriterion("maintainer not between", value1, value2, "maintainer");
            return (Criteria) this;
        }

        public Criteria andSmemoIsNull() {
            addCriterion("smemo is null");
            return (Criteria) this;
        }

        public Criteria andSmemoIsNotNull() {
            addCriterion("smemo is not null");
            return (Criteria) this;
        }

        public Criteria andSmemoEqualTo(String value) {
            addCriterion("smemo =", value, "smemo");
            return (Criteria) this;
        }

        public Criteria andSmemoNotEqualTo(String value) {
            addCriterion("smemo <>", value, "smemo");
            return (Criteria) this;
        }

        public Criteria andSmemoGreaterThan(String value) {
            addCriterion("smemo >", value, "smemo");
            return (Criteria) this;
        }

        public Criteria andSmemoGreaterThanOrEqualTo(String value) {
            addCriterion("smemo >=", value, "smemo");
            return (Criteria) this;
        }

        public Criteria andSmemoLessThan(String value) {
            addCriterion("smemo <", value, "smemo");
            return (Criteria) this;
        }

        public Criteria andSmemoLessThanOrEqualTo(String value) {
            addCriterion("smemo <=", value, "smemo");
            return (Criteria) this;
        }

        public Criteria andSmemoLike(String value) {
            addCriterion("smemo like", value, "smemo");
            return (Criteria) this;
        }

        public Criteria andSmemoNotLike(String value) {
            addCriterion("smemo not like", value, "smemo");
            return (Criteria) this;
        }

        public Criteria andSmemoIn(List<String> values) {
            addCriterion("smemo in", values, "smemo");
            return (Criteria) this;
        }

        public Criteria andSmemoNotIn(List<String> values) {
            addCriterion("smemo not in", values, "smemo");
            return (Criteria) this;
        }

        public Criteria andSmemoBetween(String value1, String value2) {
            addCriterion("smemo between", value1, value2, "smemo");
            return (Criteria) this;
        }

        public Criteria andSmemoNotBetween(String value1, String value2) {
            addCriterion("smemo not between", value1, value2, "smemo");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}