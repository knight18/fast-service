package com.x.fs.mbg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FsWorkFlowTrackerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FsWorkFlowTrackerExample() {
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

        public Criteria andWfGuidIsNull() {
            addCriterion("WF_GUID is null");
            return (Criteria) this;
        }

        public Criteria andWfGuidIsNotNull() {
            addCriterion("WF_GUID is not null");
            return (Criteria) this;
        }

        public Criteria andWfGuidEqualTo(String value) {
            addCriterion("WF_GUID =", value, "wfGuid");
            return (Criteria) this;
        }

        public Criteria andWfGuidNotEqualTo(String value) {
            addCriterion("WF_GUID <>", value, "wfGuid");
            return (Criteria) this;
        }

        public Criteria andWfGuidGreaterThan(String value) {
            addCriterion("WF_GUID >", value, "wfGuid");
            return (Criteria) this;
        }

        public Criteria andWfGuidGreaterThanOrEqualTo(String value) {
            addCriterion("WF_GUID >=", value, "wfGuid");
            return (Criteria) this;
        }

        public Criteria andWfGuidLessThan(String value) {
            addCriterion("WF_GUID <", value, "wfGuid");
            return (Criteria) this;
        }

        public Criteria andWfGuidLessThanOrEqualTo(String value) {
            addCriterion("WF_GUID <=", value, "wfGuid");
            return (Criteria) this;
        }

        public Criteria andWfGuidLike(String value) {
            addCriterion("WF_GUID like", value, "wfGuid");
            return (Criteria) this;
        }

        public Criteria andWfGuidNotLike(String value) {
            addCriterion("WF_GUID not like", value, "wfGuid");
            return (Criteria) this;
        }

        public Criteria andWfGuidIn(List<String> values) {
            addCriterion("WF_GUID in", values, "wfGuid");
            return (Criteria) this;
        }

        public Criteria andWfGuidNotIn(List<String> values) {
            addCriterion("WF_GUID not in", values, "wfGuid");
            return (Criteria) this;
        }

        public Criteria andWfGuidBetween(String value1, String value2) {
            addCriterion("WF_GUID between", value1, value2, "wfGuid");
            return (Criteria) this;
        }

        public Criteria andWfGuidNotBetween(String value1, String value2) {
            addCriterion("WF_GUID not between", value1, value2, "wfGuid");
            return (Criteria) this;
        }

        public Criteria andWfNameIsNull() {
            addCriterion("WF_NAME is null");
            return (Criteria) this;
        }

        public Criteria andWfNameIsNotNull() {
            addCriterion("WF_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andWfNameEqualTo(String value) {
            addCriterion("WF_NAME =", value, "wfName");
            return (Criteria) this;
        }

        public Criteria andWfNameNotEqualTo(String value) {
            addCriterion("WF_NAME <>", value, "wfName");
            return (Criteria) this;
        }

        public Criteria andWfNameGreaterThan(String value) {
            addCriterion("WF_NAME >", value, "wfName");
            return (Criteria) this;
        }

        public Criteria andWfNameGreaterThanOrEqualTo(String value) {
            addCriterion("WF_NAME >=", value, "wfName");
            return (Criteria) this;
        }

        public Criteria andWfNameLessThan(String value) {
            addCriterion("WF_NAME <", value, "wfName");
            return (Criteria) this;
        }

        public Criteria andWfNameLessThanOrEqualTo(String value) {
            addCriterion("WF_NAME <=", value, "wfName");
            return (Criteria) this;
        }

        public Criteria andWfNameLike(String value) {
            addCriterion("WF_NAME like", value, "wfName");
            return (Criteria) this;
        }

        public Criteria andWfNameNotLike(String value) {
            addCriterion("WF_NAME not like", value, "wfName");
            return (Criteria) this;
        }

        public Criteria andWfNameIn(List<String> values) {
            addCriterion("WF_NAME in", values, "wfName");
            return (Criteria) this;
        }

        public Criteria andWfNameNotIn(List<String> values) {
            addCriterion("WF_NAME not in", values, "wfName");
            return (Criteria) this;
        }

        public Criteria andWfNameBetween(String value1, String value2) {
            addCriterion("WF_NAME between", value1, value2, "wfName");
            return (Criteria) this;
        }

        public Criteria andWfNameNotBetween(String value1, String value2) {
            addCriterion("WF_NAME not between", value1, value2, "wfName");
            return (Criteria) this;
        }

        public Criteria andWfCreateTimeIsNull() {
            addCriterion("WF_CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andWfCreateTimeIsNotNull() {
            addCriterion("WF_CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andWfCreateTimeEqualTo(Date value) {
            addCriterion("WF_CREATE_TIME =", value, "wfCreateTime");
            return (Criteria) this;
        }

        public Criteria andWfCreateTimeNotEqualTo(Date value) {
            addCriterion("WF_CREATE_TIME <>", value, "wfCreateTime");
            return (Criteria) this;
        }

        public Criteria andWfCreateTimeGreaterThan(Date value) {
            addCriterion("WF_CREATE_TIME >", value, "wfCreateTime");
            return (Criteria) this;
        }

        public Criteria andWfCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("WF_CREATE_TIME >=", value, "wfCreateTime");
            return (Criteria) this;
        }

        public Criteria andWfCreateTimeLessThan(Date value) {
            addCriterion("WF_CREATE_TIME <", value, "wfCreateTime");
            return (Criteria) this;
        }

        public Criteria andWfCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("WF_CREATE_TIME <=", value, "wfCreateTime");
            return (Criteria) this;
        }

        public Criteria andWfCreateTimeIn(List<Date> values) {
            addCriterion("WF_CREATE_TIME in", values, "wfCreateTime");
            return (Criteria) this;
        }

        public Criteria andWfCreateTimeNotIn(List<Date> values) {
            addCriterion("WF_CREATE_TIME not in", values, "wfCreateTime");
            return (Criteria) this;
        }

        public Criteria andWfCreateTimeBetween(Date value1, Date value2) {
            addCriterion("WF_CREATE_TIME between", value1, value2, "wfCreateTime");
            return (Criteria) this;
        }

        public Criteria andWfCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("WF_CREATE_TIME not between", value1, value2, "wfCreateTime");
            return (Criteria) this;
        }

        public Criteria andWfLeaseTimeIsNull() {
            addCriterion("WF_LEASE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andWfLeaseTimeIsNotNull() {
            addCriterion("WF_LEASE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andWfLeaseTimeEqualTo(Long value) {
            addCriterion("WF_LEASE_TIME =", value, "wfLeaseTime");
            return (Criteria) this;
        }

        public Criteria andWfLeaseTimeNotEqualTo(Long value) {
            addCriterion("WF_LEASE_TIME <>", value, "wfLeaseTime");
            return (Criteria) this;
        }

        public Criteria andWfLeaseTimeGreaterThan(Long value) {
            addCriterion("WF_LEASE_TIME >", value, "wfLeaseTime");
            return (Criteria) this;
        }

        public Criteria andWfLeaseTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("WF_LEASE_TIME >=", value, "wfLeaseTime");
            return (Criteria) this;
        }

        public Criteria andWfLeaseTimeLessThan(Long value) {
            addCriterion("WF_LEASE_TIME <", value, "wfLeaseTime");
            return (Criteria) this;
        }

        public Criteria andWfLeaseTimeLessThanOrEqualTo(Long value) {
            addCriterion("WF_LEASE_TIME <=", value, "wfLeaseTime");
            return (Criteria) this;
        }

        public Criteria andWfLeaseTimeIn(List<Long> values) {
            addCriterion("WF_LEASE_TIME in", values, "wfLeaseTime");
            return (Criteria) this;
        }

        public Criteria andWfLeaseTimeNotIn(List<Long> values) {
            addCriterion("WF_LEASE_TIME not in", values, "wfLeaseTime");
            return (Criteria) this;
        }

        public Criteria andWfLeaseTimeBetween(Long value1, Long value2) {
            addCriterion("WF_LEASE_TIME between", value1, value2, "wfLeaseTime");
            return (Criteria) this;
        }

        public Criteria andWfLeaseTimeNotBetween(Long value1, Long value2) {
            addCriterion("WF_LEASE_TIME not between", value1, value2, "wfLeaseTime");
            return (Criteria) this;
        }

        public Criteria andWfGraceTimeIsNull() {
            addCriterion("WF_GRACE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andWfGraceTimeIsNotNull() {
            addCriterion("WF_GRACE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andWfGraceTimeEqualTo(Long value) {
            addCriterion("WF_GRACE_TIME =", value, "wfGraceTime");
            return (Criteria) this;
        }

        public Criteria andWfGraceTimeNotEqualTo(Long value) {
            addCriterion("WF_GRACE_TIME <>", value, "wfGraceTime");
            return (Criteria) this;
        }

        public Criteria andWfGraceTimeGreaterThan(Long value) {
            addCriterion("WF_GRACE_TIME >", value, "wfGraceTime");
            return (Criteria) this;
        }

        public Criteria andWfGraceTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("WF_GRACE_TIME >=", value, "wfGraceTime");
            return (Criteria) this;
        }

        public Criteria andWfGraceTimeLessThan(Long value) {
            addCriterion("WF_GRACE_TIME <", value, "wfGraceTime");
            return (Criteria) this;
        }

        public Criteria andWfGraceTimeLessThanOrEqualTo(Long value) {
            addCriterion("WF_GRACE_TIME <=", value, "wfGraceTime");
            return (Criteria) this;
        }

        public Criteria andWfGraceTimeIn(List<Long> values) {
            addCriterion("WF_GRACE_TIME in", values, "wfGraceTime");
            return (Criteria) this;
        }

        public Criteria andWfGraceTimeNotIn(List<Long> values) {
            addCriterion("WF_GRACE_TIME not in", values, "wfGraceTime");
            return (Criteria) this;
        }

        public Criteria andWfGraceTimeBetween(Long value1, Long value2) {
            addCriterion("WF_GRACE_TIME between", value1, value2, "wfGraceTime");
            return (Criteria) this;
        }

        public Criteria andWfGraceTimeNotBetween(Long value1, Long value2) {
            addCriterion("WF_GRACE_TIME not between", value1, value2, "wfGraceTime");
            return (Criteria) this;
        }

        public Criteria andWfLeaseExpiryTimeIsNull() {
            addCriterion("WF_LEASE_EXPIRY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andWfLeaseExpiryTimeIsNotNull() {
            addCriterion("WF_LEASE_EXPIRY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andWfLeaseExpiryTimeEqualTo(Date value) {
            addCriterion("WF_LEASE_EXPIRY_TIME =", value, "wfLeaseExpiryTime");
            return (Criteria) this;
        }

        public Criteria andWfLeaseExpiryTimeNotEqualTo(Date value) {
            addCriterion("WF_LEASE_EXPIRY_TIME <>", value, "wfLeaseExpiryTime");
            return (Criteria) this;
        }

        public Criteria andWfLeaseExpiryTimeGreaterThan(Date value) {
            addCriterion("WF_LEASE_EXPIRY_TIME >", value, "wfLeaseExpiryTime");
            return (Criteria) this;
        }

        public Criteria andWfLeaseExpiryTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("WF_LEASE_EXPIRY_TIME >=", value, "wfLeaseExpiryTime");
            return (Criteria) this;
        }

        public Criteria andWfLeaseExpiryTimeLessThan(Date value) {
            addCriterion("WF_LEASE_EXPIRY_TIME <", value, "wfLeaseExpiryTime");
            return (Criteria) this;
        }

        public Criteria andWfLeaseExpiryTimeLessThanOrEqualTo(Date value) {
            addCriterion("WF_LEASE_EXPIRY_TIME <=", value, "wfLeaseExpiryTime");
            return (Criteria) this;
        }

        public Criteria andWfLeaseExpiryTimeIn(List<Date> values) {
            addCriterion("WF_LEASE_EXPIRY_TIME in", values, "wfLeaseExpiryTime");
            return (Criteria) this;
        }

        public Criteria andWfLeaseExpiryTimeNotIn(List<Date> values) {
            addCriterion("WF_LEASE_EXPIRY_TIME not in", values, "wfLeaseExpiryTime");
            return (Criteria) this;
        }

        public Criteria andWfLeaseExpiryTimeBetween(Date value1, Date value2) {
            addCriterion("WF_LEASE_EXPIRY_TIME between", value1, value2, "wfLeaseExpiryTime");
            return (Criteria) this;
        }

        public Criteria andWfLeaseExpiryTimeNotBetween(Date value1, Date value2) {
            addCriterion("WF_LEASE_EXPIRY_TIME not between", value1, value2, "wfLeaseExpiryTime");
            return (Criteria) this;
        }

        public Criteria andWfLeaseCountIsNull() {
            addCriterion("WF_LEASE_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andWfLeaseCountIsNotNull() {
            addCriterion("WF_LEASE_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andWfLeaseCountEqualTo(Integer value) {
            addCriterion("WF_LEASE_COUNT =", value, "wfLeaseCount");
            return (Criteria) this;
        }

        public Criteria andWfLeaseCountNotEqualTo(Integer value) {
            addCriterion("WF_LEASE_COUNT <>", value, "wfLeaseCount");
            return (Criteria) this;
        }

        public Criteria andWfLeaseCountGreaterThan(Integer value) {
            addCriterion("WF_LEASE_COUNT >", value, "wfLeaseCount");
            return (Criteria) this;
        }

        public Criteria andWfLeaseCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("WF_LEASE_COUNT >=", value, "wfLeaseCount");
            return (Criteria) this;
        }

        public Criteria andWfLeaseCountLessThan(Integer value) {
            addCriterion("WF_LEASE_COUNT <", value, "wfLeaseCount");
            return (Criteria) this;
        }

        public Criteria andWfLeaseCountLessThanOrEqualTo(Integer value) {
            addCriterion("WF_LEASE_COUNT <=", value, "wfLeaseCount");
            return (Criteria) this;
        }

        public Criteria andWfLeaseCountIn(List<Integer> values) {
            addCriterion("WF_LEASE_COUNT in", values, "wfLeaseCount");
            return (Criteria) this;
        }

        public Criteria andWfLeaseCountNotIn(List<Integer> values) {
            addCriterion("WF_LEASE_COUNT not in", values, "wfLeaseCount");
            return (Criteria) this;
        }

        public Criteria andWfLeaseCountBetween(Integer value1, Integer value2) {
            addCriterion("WF_LEASE_COUNT between", value1, value2, "wfLeaseCount");
            return (Criteria) this;
        }

        public Criteria andWfLeaseCountNotBetween(Integer value1, Integer value2) {
            addCriterion("WF_LEASE_COUNT not between", value1, value2, "wfLeaseCount");
            return (Criteria) this;
        }

        public Criteria andWfRequestTextIsNull() {
            addCriterion("WF_REQUEST_TEXT is null");
            return (Criteria) this;
        }

        public Criteria andWfRequestTextIsNotNull() {
            addCriterion("WF_REQUEST_TEXT is not null");
            return (Criteria) this;
        }

        public Criteria andWfRequestTextEqualTo(String value) {
            addCriterion("WF_REQUEST_TEXT =", value, "wfRequestText");
            return (Criteria) this;
        }

        public Criteria andWfRequestTextNotEqualTo(String value) {
            addCriterion("WF_REQUEST_TEXT <>", value, "wfRequestText");
            return (Criteria) this;
        }

        public Criteria andWfRequestTextGreaterThan(String value) {
            addCriterion("WF_REQUEST_TEXT >", value, "wfRequestText");
            return (Criteria) this;
        }

        public Criteria andWfRequestTextGreaterThanOrEqualTo(String value) {
            addCriterion("WF_REQUEST_TEXT >=", value, "wfRequestText");
            return (Criteria) this;
        }

        public Criteria andWfRequestTextLessThan(String value) {
            addCriterion("WF_REQUEST_TEXT <", value, "wfRequestText");
            return (Criteria) this;
        }

        public Criteria andWfRequestTextLessThanOrEqualTo(String value) {
            addCriterion("WF_REQUEST_TEXT <=", value, "wfRequestText");
            return (Criteria) this;
        }

        public Criteria andWfRequestTextLike(String value) {
            addCriterion("WF_REQUEST_TEXT like", value, "wfRequestText");
            return (Criteria) this;
        }

        public Criteria andWfRequestTextNotLike(String value) {
            addCriterion("WF_REQUEST_TEXT not like", value, "wfRequestText");
            return (Criteria) this;
        }

        public Criteria andWfRequestTextIn(List<String> values) {
            addCriterion("WF_REQUEST_TEXT in", values, "wfRequestText");
            return (Criteria) this;
        }

        public Criteria andWfRequestTextNotIn(List<String> values) {
            addCriterion("WF_REQUEST_TEXT not in", values, "wfRequestText");
            return (Criteria) this;
        }

        public Criteria andWfRequestTextBetween(String value1, String value2) {
            addCriterion("WF_REQUEST_TEXT between", value1, value2, "wfRequestText");
            return (Criteria) this;
        }

        public Criteria andWfRequestTextNotBetween(String value1, String value2) {
            addCriterion("WF_REQUEST_TEXT not between", value1, value2, "wfRequestText");
            return (Criteria) this;
        }

        public Criteria andWfStatusIsNull() {
            addCriterion("WF_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andWfStatusIsNotNull() {
            addCriterion("WF_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andWfStatusEqualTo(String value) {
            addCriterion("WF_STATUS =", value, "wfStatus");
            return (Criteria) this;
        }

        public Criteria andWfStatusNotEqualTo(String value) {
            addCriterion("WF_STATUS <>", value, "wfStatus");
            return (Criteria) this;
        }

        public Criteria andWfStatusGreaterThan(String value) {
            addCriterion("WF_STATUS >", value, "wfStatus");
            return (Criteria) this;
        }

        public Criteria andWfStatusGreaterThanOrEqualTo(String value) {
            addCriterion("WF_STATUS >=", value, "wfStatus");
            return (Criteria) this;
        }

        public Criteria andWfStatusLessThan(String value) {
            addCriterion("WF_STATUS <", value, "wfStatus");
            return (Criteria) this;
        }

        public Criteria andWfStatusLessThanOrEqualTo(String value) {
            addCriterion("WF_STATUS <=", value, "wfStatus");
            return (Criteria) this;
        }

        public Criteria andWfStatusLike(String value) {
            addCriterion("WF_STATUS like", value, "wfStatus");
            return (Criteria) this;
        }

        public Criteria andWfStatusNotLike(String value) {
            addCriterion("WF_STATUS not like", value, "wfStatus");
            return (Criteria) this;
        }

        public Criteria andWfStatusIn(List<String> values) {
            addCriterion("WF_STATUS in", values, "wfStatus");
            return (Criteria) this;
        }

        public Criteria andWfStatusNotIn(List<String> values) {
            addCriterion("WF_STATUS not in", values, "wfStatus");
            return (Criteria) this;
        }

        public Criteria andWfStatusBetween(String value1, String value2) {
            addCriterion("WF_STATUS between", value1, value2, "wfStatus");
            return (Criteria) this;
        }

        public Criteria andWfStatusNotBetween(String value1, String value2) {
            addCriterion("WF_STATUS not between", value1, value2, "wfStatus");
            return (Criteria) this;
        }

        public Criteria andWfSignalIsNull() {
            addCriterion("WF_SIGNAL is null");
            return (Criteria) this;
        }

        public Criteria andWfSignalIsNotNull() {
            addCriterion("WF_SIGNAL is not null");
            return (Criteria) this;
        }

        public Criteria andWfSignalEqualTo(Integer value) {
            addCriterion("WF_SIGNAL =", value, "wfSignal");
            return (Criteria) this;
        }

        public Criteria andWfSignalNotEqualTo(Integer value) {
            addCriterion("WF_SIGNAL <>", value, "wfSignal");
            return (Criteria) this;
        }

        public Criteria andWfSignalGreaterThan(Integer value) {
            addCriterion("WF_SIGNAL >", value, "wfSignal");
            return (Criteria) this;
        }

        public Criteria andWfSignalGreaterThanOrEqualTo(Integer value) {
            addCriterion("WF_SIGNAL >=", value, "wfSignal");
            return (Criteria) this;
        }

        public Criteria andWfSignalLessThan(Integer value) {
            addCriterion("WF_SIGNAL <", value, "wfSignal");
            return (Criteria) this;
        }

        public Criteria andWfSignalLessThanOrEqualTo(Integer value) {
            addCriterion("WF_SIGNAL <=", value, "wfSignal");
            return (Criteria) this;
        }

        public Criteria andWfSignalIn(List<Integer> values) {
            addCriterion("WF_SIGNAL in", values, "wfSignal");
            return (Criteria) this;
        }

        public Criteria andWfSignalNotIn(List<Integer> values) {
            addCriterion("WF_SIGNAL not in", values, "wfSignal");
            return (Criteria) this;
        }

        public Criteria andWfSignalBetween(Integer value1, Integer value2) {
            addCriterion("WF_SIGNAL between", value1, value2, "wfSignal");
            return (Criteria) this;
        }

        public Criteria andWfSignalNotBetween(Integer value1, Integer value2) {
            addCriterion("WF_SIGNAL not between", value1, value2, "wfSignal");
            return (Criteria) this;
        }

        public Criteria andWfParentGuidIsNull() {
            addCriterion("WF_PARENT_GUID is null");
            return (Criteria) this;
        }

        public Criteria andWfParentGuidIsNotNull() {
            addCriterion("WF_PARENT_GUID is not null");
            return (Criteria) this;
        }

        public Criteria andWfParentGuidEqualTo(String value) {
            addCriterion("WF_PARENT_GUID =", value, "wfParentGuid");
            return (Criteria) this;
        }

        public Criteria andWfParentGuidNotEqualTo(String value) {
            addCriterion("WF_PARENT_GUID <>", value, "wfParentGuid");
            return (Criteria) this;
        }

        public Criteria andWfParentGuidGreaterThan(String value) {
            addCriterion("WF_PARENT_GUID >", value, "wfParentGuid");
            return (Criteria) this;
        }

        public Criteria andWfParentGuidGreaterThanOrEqualTo(String value) {
            addCriterion("WF_PARENT_GUID >=", value, "wfParentGuid");
            return (Criteria) this;
        }

        public Criteria andWfParentGuidLessThan(String value) {
            addCriterion("WF_PARENT_GUID <", value, "wfParentGuid");
            return (Criteria) this;
        }

        public Criteria andWfParentGuidLessThanOrEqualTo(String value) {
            addCriterion("WF_PARENT_GUID <=", value, "wfParentGuid");
            return (Criteria) this;
        }

        public Criteria andWfParentGuidLike(String value) {
            addCriterion("WF_PARENT_GUID like", value, "wfParentGuid");
            return (Criteria) this;
        }

        public Criteria andWfParentGuidNotLike(String value) {
            addCriterion("WF_PARENT_GUID not like", value, "wfParentGuid");
            return (Criteria) this;
        }

        public Criteria andWfParentGuidIn(List<String> values) {
            addCriterion("WF_PARENT_GUID in", values, "wfParentGuid");
            return (Criteria) this;
        }

        public Criteria andWfParentGuidNotIn(List<String> values) {
            addCriterion("WF_PARENT_GUID not in", values, "wfParentGuid");
            return (Criteria) this;
        }

        public Criteria andWfParentGuidBetween(String value1, String value2) {
            addCriterion("WF_PARENT_GUID between", value1, value2, "wfParentGuid");
            return (Criteria) this;
        }

        public Criteria andWfParentGuidNotBetween(String value1, String value2) {
            addCriterion("WF_PARENT_GUID not between", value1, value2, "wfParentGuid");
            return (Criteria) this;
        }

        public Criteria andWfFinishTimeIsNull() {
            addCriterion("WF_FINISH_TIME is null");
            return (Criteria) this;
        }

        public Criteria andWfFinishTimeIsNotNull() {
            addCriterion("WF_FINISH_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andWfFinishTimeEqualTo(Date value) {
            addCriterion("WF_FINISH_TIME =", value, "wfFinishTime");
            return (Criteria) this;
        }

        public Criteria andWfFinishTimeNotEqualTo(Date value) {
            addCriterion("WF_FINISH_TIME <>", value, "wfFinishTime");
            return (Criteria) this;
        }

        public Criteria andWfFinishTimeGreaterThan(Date value) {
            addCriterion("WF_FINISH_TIME >", value, "wfFinishTime");
            return (Criteria) this;
        }

        public Criteria andWfFinishTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("WF_FINISH_TIME >=", value, "wfFinishTime");
            return (Criteria) this;
        }

        public Criteria andWfFinishTimeLessThan(Date value) {
            addCriterion("WF_FINISH_TIME <", value, "wfFinishTime");
            return (Criteria) this;
        }

        public Criteria andWfFinishTimeLessThanOrEqualTo(Date value) {
            addCriterion("WF_FINISH_TIME <=", value, "wfFinishTime");
            return (Criteria) this;
        }

        public Criteria andWfFinishTimeIn(List<Date> values) {
            addCriterion("WF_FINISH_TIME in", values, "wfFinishTime");
            return (Criteria) this;
        }

        public Criteria andWfFinishTimeNotIn(List<Date> values) {
            addCriterion("WF_FINISH_TIME not in", values, "wfFinishTime");
            return (Criteria) this;
        }

        public Criteria andWfFinishTimeBetween(Date value1, Date value2) {
            addCriterion("WF_FINISH_TIME between", value1, value2, "wfFinishTime");
            return (Criteria) this;
        }

        public Criteria andWfFinishTimeNotBetween(Date value1, Date value2) {
            addCriterion("WF_FINISH_TIME not between", value1, value2, "wfFinishTime");
            return (Criteria) this;
        }

        public Criteria andWfFunctionIsNull() {
            addCriterion("WF_FUNCTION is null");
            return (Criteria) this;
        }

        public Criteria andWfFunctionIsNotNull() {
            addCriterion("WF_FUNCTION is not null");
            return (Criteria) this;
        }

        public Criteria andWfFunctionEqualTo(String value) {
            addCriterion("WF_FUNCTION =", value, "wfFunction");
            return (Criteria) this;
        }

        public Criteria andWfFunctionNotEqualTo(String value) {
            addCriterion("WF_FUNCTION <>", value, "wfFunction");
            return (Criteria) this;
        }

        public Criteria andWfFunctionGreaterThan(String value) {
            addCriterion("WF_FUNCTION >", value, "wfFunction");
            return (Criteria) this;
        }

        public Criteria andWfFunctionGreaterThanOrEqualTo(String value) {
            addCriterion("WF_FUNCTION >=", value, "wfFunction");
            return (Criteria) this;
        }

        public Criteria andWfFunctionLessThan(String value) {
            addCriterion("WF_FUNCTION <", value, "wfFunction");
            return (Criteria) this;
        }

        public Criteria andWfFunctionLessThanOrEqualTo(String value) {
            addCriterion("WF_FUNCTION <=", value, "wfFunction");
            return (Criteria) this;
        }

        public Criteria andWfFunctionLike(String value) {
            addCriterion("WF_FUNCTION like", value, "wfFunction");
            return (Criteria) this;
        }

        public Criteria andWfFunctionNotLike(String value) {
            addCriterion("WF_FUNCTION not like", value, "wfFunction");
            return (Criteria) this;
        }

        public Criteria andWfFunctionIn(List<String> values) {
            addCriterion("WF_FUNCTION in", values, "wfFunction");
            return (Criteria) this;
        }

        public Criteria andWfFunctionNotIn(List<String> values) {
            addCriterion("WF_FUNCTION not in", values, "wfFunction");
            return (Criteria) this;
        }

        public Criteria andWfFunctionBetween(String value1, String value2) {
            addCriterion("WF_FUNCTION between", value1, value2, "wfFunction");
            return (Criteria) this;
        }

        public Criteria andWfFunctionNotBetween(String value1, String value2) {
            addCriterion("WF_FUNCTION not between", value1, value2, "wfFunction");
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