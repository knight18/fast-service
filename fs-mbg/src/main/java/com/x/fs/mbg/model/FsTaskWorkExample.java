package com.x.fs.mbg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FsTaskWorkExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FsTaskWorkExample() {
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

        public Criteria andTaskDateIsNull() {
            addCriterion("TASK_DATE is null");
            return (Criteria) this;
        }

        public Criteria andTaskDateIsNotNull() {
            addCriterion("TASK_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andTaskDateEqualTo(Integer value) {
            addCriterion("TASK_DATE =", value, "taskDate");
            return (Criteria) this;
        }

        public Criteria andTaskDateNotEqualTo(Integer value) {
            addCriterion("TASK_DATE <>", value, "taskDate");
            return (Criteria) this;
        }

        public Criteria andTaskDateGreaterThan(Integer value) {
            addCriterion("TASK_DATE >", value, "taskDate");
            return (Criteria) this;
        }

        public Criteria andTaskDateGreaterThanOrEqualTo(Integer value) {
            addCriterion("TASK_DATE >=", value, "taskDate");
            return (Criteria) this;
        }

        public Criteria andTaskDateLessThan(Integer value) {
            addCriterion("TASK_DATE <", value, "taskDate");
            return (Criteria) this;
        }

        public Criteria andTaskDateLessThanOrEqualTo(Integer value) {
            addCriterion("TASK_DATE <=", value, "taskDate");
            return (Criteria) this;
        }

        public Criteria andTaskDateIn(List<Integer> values) {
            addCriterion("TASK_DATE in", values, "taskDate");
            return (Criteria) this;
        }

        public Criteria andTaskDateNotIn(List<Integer> values) {
            addCriterion("TASK_DATE not in", values, "taskDate");
            return (Criteria) this;
        }

        public Criteria andTaskDateBetween(Integer value1, Integer value2) {
            addCriterion("TASK_DATE between", value1, value2, "taskDate");
            return (Criteria) this;
        }

        public Criteria andTaskDateNotBetween(Integer value1, Integer value2) {
            addCriterion("TASK_DATE not between", value1, value2, "taskDate");
            return (Criteria) this;
        }

        public Criteria andTaskWorkSnIsNull() {
            addCriterion("TASK_WORK_SN is null");
            return (Criteria) this;
        }

        public Criteria andTaskWorkSnIsNotNull() {
            addCriterion("TASK_WORK_SN is not null");
            return (Criteria) this;
        }

        public Criteria andTaskWorkSnEqualTo(Integer value) {
            addCriterion("TASK_WORK_SN =", value, "taskWorkSn");
            return (Criteria) this;
        }

        public Criteria andTaskWorkSnNotEqualTo(Integer value) {
            addCriterion("TASK_WORK_SN <>", value, "taskWorkSn");
            return (Criteria) this;
        }

        public Criteria andTaskWorkSnGreaterThan(Integer value) {
            addCriterion("TASK_WORK_SN >", value, "taskWorkSn");
            return (Criteria) this;
        }

        public Criteria andTaskWorkSnGreaterThanOrEqualTo(Integer value) {
            addCriterion("TASK_WORK_SN >=", value, "taskWorkSn");
            return (Criteria) this;
        }

        public Criteria andTaskWorkSnLessThan(Integer value) {
            addCriterion("TASK_WORK_SN <", value, "taskWorkSn");
            return (Criteria) this;
        }

        public Criteria andTaskWorkSnLessThanOrEqualTo(Integer value) {
            addCriterion("TASK_WORK_SN <=", value, "taskWorkSn");
            return (Criteria) this;
        }

        public Criteria andTaskWorkSnIn(List<Integer> values) {
            addCriterion("TASK_WORK_SN in", values, "taskWorkSn");
            return (Criteria) this;
        }

        public Criteria andTaskWorkSnNotIn(List<Integer> values) {
            addCriterion("TASK_WORK_SN not in", values, "taskWorkSn");
            return (Criteria) this;
        }

        public Criteria andTaskWorkSnBetween(Integer value1, Integer value2) {
            addCriterion("TASK_WORK_SN between", value1, value2, "taskWorkSn");
            return (Criteria) this;
        }

        public Criteria andTaskWorkSnNotBetween(Integer value1, Integer value2) {
            addCriterion("TASK_WORK_SN not between", value1, value2, "taskWorkSn");
            return (Criteria) this;
        }

        public Criteria andTaskWorkTypeIsNull() {
            addCriterion("TASK_WORK_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTaskWorkTypeIsNotNull() {
            addCriterion("TASK_WORK_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTaskWorkTypeEqualTo(String value) {
            addCriterion("TASK_WORK_TYPE =", value, "taskWorkType");
            return (Criteria) this;
        }

        public Criteria andTaskWorkTypeNotEqualTo(String value) {
            addCriterion("TASK_WORK_TYPE <>", value, "taskWorkType");
            return (Criteria) this;
        }

        public Criteria andTaskWorkTypeGreaterThan(String value) {
            addCriterion("TASK_WORK_TYPE >", value, "taskWorkType");
            return (Criteria) this;
        }

        public Criteria andTaskWorkTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_WORK_TYPE >=", value, "taskWorkType");
            return (Criteria) this;
        }

        public Criteria andTaskWorkTypeLessThan(String value) {
            addCriterion("TASK_WORK_TYPE <", value, "taskWorkType");
            return (Criteria) this;
        }

        public Criteria andTaskWorkTypeLessThanOrEqualTo(String value) {
            addCriterion("TASK_WORK_TYPE <=", value, "taskWorkType");
            return (Criteria) this;
        }

        public Criteria andTaskWorkTypeLike(String value) {
            addCriterion("TASK_WORK_TYPE like", value, "taskWorkType");
            return (Criteria) this;
        }

        public Criteria andTaskWorkTypeNotLike(String value) {
            addCriterion("TASK_WORK_TYPE not like", value, "taskWorkType");
            return (Criteria) this;
        }

        public Criteria andTaskWorkTypeIn(List<String> values) {
            addCriterion("TASK_WORK_TYPE in", values, "taskWorkType");
            return (Criteria) this;
        }

        public Criteria andTaskWorkTypeNotIn(List<String> values) {
            addCriterion("TASK_WORK_TYPE not in", values, "taskWorkType");
            return (Criteria) this;
        }

        public Criteria andTaskWorkTypeBetween(String value1, String value2) {
            addCriterion("TASK_WORK_TYPE between", value1, value2, "taskWorkType");
            return (Criteria) this;
        }

        public Criteria andTaskWorkTypeNotBetween(String value1, String value2) {
            addCriterion("TASK_WORK_TYPE not between", value1, value2, "taskWorkType");
            return (Criteria) this;
        }

        public Criteria andTaskWorkStatusIsNull() {
            addCriterion("TASK_WORK_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andTaskWorkStatusIsNotNull() {
            addCriterion("TASK_WORK_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andTaskWorkStatusEqualTo(String value) {
            addCriterion("TASK_WORK_STATUS =", value, "taskWorkStatus");
            return (Criteria) this;
        }

        public Criteria andTaskWorkStatusNotEqualTo(String value) {
            addCriterion("TASK_WORK_STATUS <>", value, "taskWorkStatus");
            return (Criteria) this;
        }

        public Criteria andTaskWorkStatusGreaterThan(String value) {
            addCriterion("TASK_WORK_STATUS >", value, "taskWorkStatus");
            return (Criteria) this;
        }

        public Criteria andTaskWorkStatusGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_WORK_STATUS >=", value, "taskWorkStatus");
            return (Criteria) this;
        }

        public Criteria andTaskWorkStatusLessThan(String value) {
            addCriterion("TASK_WORK_STATUS <", value, "taskWorkStatus");
            return (Criteria) this;
        }

        public Criteria andTaskWorkStatusLessThanOrEqualTo(String value) {
            addCriterion("TASK_WORK_STATUS <=", value, "taskWorkStatus");
            return (Criteria) this;
        }

        public Criteria andTaskWorkStatusLike(String value) {
            addCriterion("TASK_WORK_STATUS like", value, "taskWorkStatus");
            return (Criteria) this;
        }

        public Criteria andTaskWorkStatusNotLike(String value) {
            addCriterion("TASK_WORK_STATUS not like", value, "taskWorkStatus");
            return (Criteria) this;
        }

        public Criteria andTaskWorkStatusIn(List<String> values) {
            addCriterion("TASK_WORK_STATUS in", values, "taskWorkStatus");
            return (Criteria) this;
        }

        public Criteria andTaskWorkStatusNotIn(List<String> values) {
            addCriterion("TASK_WORK_STATUS not in", values, "taskWorkStatus");
            return (Criteria) this;
        }

        public Criteria andTaskWorkStatusBetween(String value1, String value2) {
            addCriterion("TASK_WORK_STATUS between", value1, value2, "taskWorkStatus");
            return (Criteria) this;
        }

        public Criteria andTaskWorkStatusNotBetween(String value1, String value2) {
            addCriterion("TASK_WORK_STATUS not between", value1, value2, "taskWorkStatus");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseIsNull() {
            addCriterion("TASK_PHASE is null");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseIsNotNull() {
            addCriterion("TASK_PHASE is not null");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseEqualTo(Integer value) {
            addCriterion("TASK_PHASE =", value, "taskPhase");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseNotEqualTo(Integer value) {
            addCriterion("TASK_PHASE <>", value, "taskPhase");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseGreaterThan(Integer value) {
            addCriterion("TASK_PHASE >", value, "taskPhase");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseGreaterThanOrEqualTo(Integer value) {
            addCriterion("TASK_PHASE >=", value, "taskPhase");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseLessThan(Integer value) {
            addCriterion("TASK_PHASE <", value, "taskPhase");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseLessThanOrEqualTo(Integer value) {
            addCriterion("TASK_PHASE <=", value, "taskPhase");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseIn(List<Integer> values) {
            addCriterion("TASK_PHASE in", values, "taskPhase");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseNotIn(List<Integer> values) {
            addCriterion("TASK_PHASE not in", values, "taskPhase");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseBetween(Integer value1, Integer value2) {
            addCriterion("TASK_PHASE between", value1, value2, "taskPhase");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseNotBetween(Integer value1, Integer value2) {
            addCriterion("TASK_PHASE not between", value1, value2, "taskPhase");
            return (Criteria) this;
        }

        public Criteria andBgnTimeIsNull() {
            addCriterion("BGN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andBgnTimeIsNotNull() {
            addCriterion("BGN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andBgnTimeEqualTo(Date value) {
            addCriterion("BGN_TIME =", value, "bgnTime");
            return (Criteria) this;
        }

        public Criteria andBgnTimeNotEqualTo(Date value) {
            addCriterion("BGN_TIME <>", value, "bgnTime");
            return (Criteria) this;
        }

        public Criteria andBgnTimeGreaterThan(Date value) {
            addCriterion("BGN_TIME >", value, "bgnTime");
            return (Criteria) this;
        }

        public Criteria andBgnTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("BGN_TIME >=", value, "bgnTime");
            return (Criteria) this;
        }

        public Criteria andBgnTimeLessThan(Date value) {
            addCriterion("BGN_TIME <", value, "bgnTime");
            return (Criteria) this;
        }

        public Criteria andBgnTimeLessThanOrEqualTo(Date value) {
            addCriterion("BGN_TIME <=", value, "bgnTime");
            return (Criteria) this;
        }

        public Criteria andBgnTimeIn(List<Date> values) {
            addCriterion("BGN_TIME in", values, "bgnTime");
            return (Criteria) this;
        }

        public Criteria andBgnTimeNotIn(List<Date> values) {
            addCriterion("BGN_TIME not in", values, "bgnTime");
            return (Criteria) this;
        }

        public Criteria andBgnTimeBetween(Date value1, Date value2) {
            addCriterion("BGN_TIME between", value1, value2, "bgnTime");
            return (Criteria) this;
        }

        public Criteria andBgnTimeNotBetween(Date value1, Date value2) {
            addCriterion("BGN_TIME not between", value1, value2, "bgnTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("END_TIME =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("END_TIME <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("END_TIME >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("END_TIME >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("END_TIME <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("END_TIME <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("END_TIME in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("END_TIME not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("END_TIME between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("END_TIME not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andRunModeIsNull() {
            addCriterion("RUN_MODE is null");
            return (Criteria) this;
        }

        public Criteria andRunModeIsNotNull() {
            addCriterion("RUN_MODE is not null");
            return (Criteria) this;
        }

        public Criteria andRunModeEqualTo(String value) {
            addCriterion("RUN_MODE =", value, "runMode");
            return (Criteria) this;
        }

        public Criteria andRunModeNotEqualTo(String value) {
            addCriterion("RUN_MODE <>", value, "runMode");
            return (Criteria) this;
        }

        public Criteria andRunModeGreaterThan(String value) {
            addCriterion("RUN_MODE >", value, "runMode");
            return (Criteria) this;
        }

        public Criteria andRunModeGreaterThanOrEqualTo(String value) {
            addCriterion("RUN_MODE >=", value, "runMode");
            return (Criteria) this;
        }

        public Criteria andRunModeLessThan(String value) {
            addCriterion("RUN_MODE <", value, "runMode");
            return (Criteria) this;
        }

        public Criteria andRunModeLessThanOrEqualTo(String value) {
            addCriterion("RUN_MODE <=", value, "runMode");
            return (Criteria) this;
        }

        public Criteria andRunModeLike(String value) {
            addCriterion("RUN_MODE like", value, "runMode");
            return (Criteria) this;
        }

        public Criteria andRunModeNotLike(String value) {
            addCriterion("RUN_MODE not like", value, "runMode");
            return (Criteria) this;
        }

        public Criteria andRunModeIn(List<String> values) {
            addCriterion("RUN_MODE in", values, "runMode");
            return (Criteria) this;
        }

        public Criteria andRunModeNotIn(List<String> values) {
            addCriterion("RUN_MODE not in", values, "runMode");
            return (Criteria) this;
        }

        public Criteria andRunModeBetween(String value1, String value2) {
            addCriterion("RUN_MODE between", value1, value2, "runMode");
            return (Criteria) this;
        }

        public Criteria andRunModeNotBetween(String value1, String value2) {
            addCriterion("RUN_MODE not between", value1, value2, "runMode");
            return (Criteria) this;
        }

        public Criteria andOpCodeIsNull() {
            addCriterion("OP_CODE is null");
            return (Criteria) this;
        }

        public Criteria andOpCodeIsNotNull() {
            addCriterion("OP_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andOpCodeEqualTo(Long value) {
            addCriterion("OP_CODE =", value, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeNotEqualTo(Long value) {
            addCriterion("OP_CODE <>", value, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeGreaterThan(Long value) {
            addCriterion("OP_CODE >", value, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("OP_CODE >=", value, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeLessThan(Long value) {
            addCriterion("OP_CODE <", value, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeLessThanOrEqualTo(Long value) {
            addCriterion("OP_CODE <=", value, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeIn(List<Long> values) {
            addCriterion("OP_CODE in", values, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeNotIn(List<Long> values) {
            addCriterion("OP_CODE not in", values, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeBetween(Long value1, Long value2) {
            addCriterion("OP_CODE between", value1, value2, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeNotBetween(Long value1, Long value2) {
            addCriterion("OP_CODE not between", value1, value2, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpNameIsNull() {
            addCriterion("OP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOpNameIsNotNull() {
            addCriterion("OP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOpNameEqualTo(String value) {
            addCriterion("OP_NAME =", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameNotEqualTo(String value) {
            addCriterion("OP_NAME <>", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameGreaterThan(String value) {
            addCriterion("OP_NAME >", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameGreaterThanOrEqualTo(String value) {
            addCriterion("OP_NAME >=", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameLessThan(String value) {
            addCriterion("OP_NAME <", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameLessThanOrEqualTo(String value) {
            addCriterion("OP_NAME <=", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameLike(String value) {
            addCriterion("OP_NAME like", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameNotLike(String value) {
            addCriterion("OP_NAME not like", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameIn(List<String> values) {
            addCriterion("OP_NAME in", values, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameNotIn(List<String> values) {
            addCriterion("OP_NAME not in", values, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameBetween(String value1, String value2) {
            addCriterion("OP_NAME between", value1, value2, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameNotBetween(String value1, String value2) {
            addCriterion("OP_NAME not between", value1, value2, "opName");
            return (Criteria) this;
        }

        public Criteria andOpSiteIsNull() {
            addCriterion("OP_SITE is null");
            return (Criteria) this;
        }

        public Criteria andOpSiteIsNotNull() {
            addCriterion("OP_SITE is not null");
            return (Criteria) this;
        }

        public Criteria andOpSiteEqualTo(String value) {
            addCriterion("OP_SITE =", value, "opSite");
            return (Criteria) this;
        }

        public Criteria andOpSiteNotEqualTo(String value) {
            addCriterion("OP_SITE <>", value, "opSite");
            return (Criteria) this;
        }

        public Criteria andOpSiteGreaterThan(String value) {
            addCriterion("OP_SITE >", value, "opSite");
            return (Criteria) this;
        }

        public Criteria andOpSiteGreaterThanOrEqualTo(String value) {
            addCriterion("OP_SITE >=", value, "opSite");
            return (Criteria) this;
        }

        public Criteria andOpSiteLessThan(String value) {
            addCriterion("OP_SITE <", value, "opSite");
            return (Criteria) this;
        }

        public Criteria andOpSiteLessThanOrEqualTo(String value) {
            addCriterion("OP_SITE <=", value, "opSite");
            return (Criteria) this;
        }

        public Criteria andOpSiteLike(String value) {
            addCriterion("OP_SITE like", value, "opSite");
            return (Criteria) this;
        }

        public Criteria andOpSiteNotLike(String value) {
            addCriterion("OP_SITE not like", value, "opSite");
            return (Criteria) this;
        }

        public Criteria andOpSiteIn(List<String> values) {
            addCriterion("OP_SITE in", values, "opSite");
            return (Criteria) this;
        }

        public Criteria andOpSiteNotIn(List<String> values) {
            addCriterion("OP_SITE not in", values, "opSite");
            return (Criteria) this;
        }

        public Criteria andOpSiteBetween(String value1, String value2) {
            addCriterion("OP_SITE between", value1, value2, "opSite");
            return (Criteria) this;
        }

        public Criteria andOpSiteNotBetween(String value1, String value2) {
            addCriterion("OP_SITE not between", value1, value2, "opSite");
            return (Criteria) this;
        }

        public Criteria andWorkExt1IsNull() {
            addCriterion("WORK_EXT1 is null");
            return (Criteria) this;
        }

        public Criteria andWorkExt1IsNotNull() {
            addCriterion("WORK_EXT1 is not null");
            return (Criteria) this;
        }

        public Criteria andWorkExt1EqualTo(String value) {
            addCriterion("WORK_EXT1 =", value, "workExt1");
            return (Criteria) this;
        }

        public Criteria andWorkExt1NotEqualTo(String value) {
            addCriterion("WORK_EXT1 <>", value, "workExt1");
            return (Criteria) this;
        }

        public Criteria andWorkExt1GreaterThan(String value) {
            addCriterion("WORK_EXT1 >", value, "workExt1");
            return (Criteria) this;
        }

        public Criteria andWorkExt1GreaterThanOrEqualTo(String value) {
            addCriterion("WORK_EXT1 >=", value, "workExt1");
            return (Criteria) this;
        }

        public Criteria andWorkExt1LessThan(String value) {
            addCriterion("WORK_EXT1 <", value, "workExt1");
            return (Criteria) this;
        }

        public Criteria andWorkExt1LessThanOrEqualTo(String value) {
            addCriterion("WORK_EXT1 <=", value, "workExt1");
            return (Criteria) this;
        }

        public Criteria andWorkExt1Like(String value) {
            addCriterion("WORK_EXT1 like", value, "workExt1");
            return (Criteria) this;
        }

        public Criteria andWorkExt1NotLike(String value) {
            addCriterion("WORK_EXT1 not like", value, "workExt1");
            return (Criteria) this;
        }

        public Criteria andWorkExt1In(List<String> values) {
            addCriterion("WORK_EXT1 in", values, "workExt1");
            return (Criteria) this;
        }

        public Criteria andWorkExt1NotIn(List<String> values) {
            addCriterion("WORK_EXT1 not in", values, "workExt1");
            return (Criteria) this;
        }

        public Criteria andWorkExt1Between(String value1, String value2) {
            addCriterion("WORK_EXT1 between", value1, value2, "workExt1");
            return (Criteria) this;
        }

        public Criteria andWorkExt1NotBetween(String value1, String value2) {
            addCriterion("WORK_EXT1 not between", value1, value2, "workExt1");
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