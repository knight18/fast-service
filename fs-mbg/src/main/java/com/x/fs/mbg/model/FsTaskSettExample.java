package com.x.fs.mbg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FsTaskSettExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FsTaskSettExample() {
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

        public Criteria andTaskPhaseNameIsNull() {
            addCriterion("TASK_PHASE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseNameIsNotNull() {
            addCriterion("TASK_PHASE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseNameEqualTo(String value) {
            addCriterion("TASK_PHASE_NAME =", value, "taskPhaseName");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseNameNotEqualTo(String value) {
            addCriterion("TASK_PHASE_NAME <>", value, "taskPhaseName");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseNameGreaterThan(String value) {
            addCriterion("TASK_PHASE_NAME >", value, "taskPhaseName");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseNameGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_PHASE_NAME >=", value, "taskPhaseName");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseNameLessThan(String value) {
            addCriterion("TASK_PHASE_NAME <", value, "taskPhaseName");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseNameLessThanOrEqualTo(String value) {
            addCriterion("TASK_PHASE_NAME <=", value, "taskPhaseName");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseNameLike(String value) {
            addCriterion("TASK_PHASE_NAME like", value, "taskPhaseName");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseNameNotLike(String value) {
            addCriterion("TASK_PHASE_NAME not like", value, "taskPhaseName");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseNameIn(List<String> values) {
            addCriterion("TASK_PHASE_NAME in", values, "taskPhaseName");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseNameNotIn(List<String> values) {
            addCriterion("TASK_PHASE_NAME not in", values, "taskPhaseName");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseNameBetween(String value1, String value2) {
            addCriterion("TASK_PHASE_NAME between", value1, value2, "taskPhaseName");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseNameNotBetween(String value1, String value2) {
            addCriterion("TASK_PHASE_NAME not between", value1, value2, "taskPhaseName");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIsNull() {
            addCriterion("TASK_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIsNotNull() {
            addCriterion("TASK_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andTaskStatusEqualTo(String value) {
            addCriterion("TASK_STATUS =", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotEqualTo(String value) {
            addCriterion("TASK_STATUS <>", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusGreaterThan(String value) {
            addCriterion("TASK_STATUS >", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_STATUS >=", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusLessThan(String value) {
            addCriterion("TASK_STATUS <", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusLessThanOrEqualTo(String value) {
            addCriterion("TASK_STATUS <=", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusLike(String value) {
            addCriterion("TASK_STATUS like", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotLike(String value) {
            addCriterion("TASK_STATUS not like", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIn(List<String> values) {
            addCriterion("TASK_STATUS in", values, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotIn(List<String> values) {
            addCriterion("TASK_STATUS not in", values, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusBetween(String value1, String value2) {
            addCriterion("TASK_STATUS between", value1, value2, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotBetween(String value1, String value2) {
            addCriterion("TASK_STATUS not between", value1, value2, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskContentIsNull() {
            addCriterion("TASK_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andTaskContentIsNotNull() {
            addCriterion("TASK_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andTaskContentEqualTo(String value) {
            addCriterion("TASK_CONTENT =", value, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentNotEqualTo(String value) {
            addCriterion("TASK_CONTENT <>", value, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentGreaterThan(String value) {
            addCriterion("TASK_CONTENT >", value, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_CONTENT >=", value, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentLessThan(String value) {
            addCriterion("TASK_CONTENT <", value, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentLessThanOrEqualTo(String value) {
            addCriterion("TASK_CONTENT <=", value, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentLike(String value) {
            addCriterion("TASK_CONTENT like", value, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentNotLike(String value) {
            addCriterion("TASK_CONTENT not like", value, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentIn(List<String> values) {
            addCriterion("TASK_CONTENT in", values, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentNotIn(List<String> values) {
            addCriterion("TASK_CONTENT not in", values, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentBetween(String value1, String value2) {
            addCriterion("TASK_CONTENT between", value1, value2, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentNotBetween(String value1, String value2) {
            addCriterion("TASK_CONTENT not between", value1, value2, "taskContent");
            return (Criteria) this;
        }

        public Criteria andErrCodeIsNull() {
            addCriterion("ERR_CODE is null");
            return (Criteria) this;
        }

        public Criteria andErrCodeIsNotNull() {
            addCriterion("ERR_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andErrCodeEqualTo(Integer value) {
            addCriterion("ERR_CODE =", value, "errCode");
            return (Criteria) this;
        }

        public Criteria andErrCodeNotEqualTo(Integer value) {
            addCriterion("ERR_CODE <>", value, "errCode");
            return (Criteria) this;
        }

        public Criteria andErrCodeGreaterThan(Integer value) {
            addCriterion("ERR_CODE >", value, "errCode");
            return (Criteria) this;
        }

        public Criteria andErrCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ERR_CODE >=", value, "errCode");
            return (Criteria) this;
        }

        public Criteria andErrCodeLessThan(Integer value) {
            addCriterion("ERR_CODE <", value, "errCode");
            return (Criteria) this;
        }

        public Criteria andErrCodeLessThanOrEqualTo(Integer value) {
            addCriterion("ERR_CODE <=", value, "errCode");
            return (Criteria) this;
        }

        public Criteria andErrCodeIn(List<Integer> values) {
            addCriterion("ERR_CODE in", values, "errCode");
            return (Criteria) this;
        }

        public Criteria andErrCodeNotIn(List<Integer> values) {
            addCriterion("ERR_CODE not in", values, "errCode");
            return (Criteria) this;
        }

        public Criteria andErrCodeBetween(Integer value1, Integer value2) {
            addCriterion("ERR_CODE between", value1, value2, "errCode");
            return (Criteria) this;
        }

        public Criteria andErrCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("ERR_CODE not between", value1, value2, "errCode");
            return (Criteria) this;
        }

        public Criteria andErrDescIsNull() {
            addCriterion("ERR_DESC is null");
            return (Criteria) this;
        }

        public Criteria andErrDescIsNotNull() {
            addCriterion("ERR_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andErrDescEqualTo(String value) {
            addCriterion("ERR_DESC =", value, "errDesc");
            return (Criteria) this;
        }

        public Criteria andErrDescNotEqualTo(String value) {
            addCriterion("ERR_DESC <>", value, "errDesc");
            return (Criteria) this;
        }

        public Criteria andErrDescGreaterThan(String value) {
            addCriterion("ERR_DESC >", value, "errDesc");
            return (Criteria) this;
        }

        public Criteria andErrDescGreaterThanOrEqualTo(String value) {
            addCriterion("ERR_DESC >=", value, "errDesc");
            return (Criteria) this;
        }

        public Criteria andErrDescLessThan(String value) {
            addCriterion("ERR_DESC <", value, "errDesc");
            return (Criteria) this;
        }

        public Criteria andErrDescLessThanOrEqualTo(String value) {
            addCriterion("ERR_DESC <=", value, "errDesc");
            return (Criteria) this;
        }

        public Criteria andErrDescLike(String value) {
            addCriterion("ERR_DESC like", value, "errDesc");
            return (Criteria) this;
        }

        public Criteria andErrDescNotLike(String value) {
            addCriterion("ERR_DESC not like", value, "errDesc");
            return (Criteria) this;
        }

        public Criteria andErrDescIn(List<String> values) {
            addCriterion("ERR_DESC in", values, "errDesc");
            return (Criteria) this;
        }

        public Criteria andErrDescNotIn(List<String> values) {
            addCriterion("ERR_DESC not in", values, "errDesc");
            return (Criteria) this;
        }

        public Criteria andErrDescBetween(String value1, String value2) {
            addCriterion("ERR_DESC between", value1, value2, "errDesc");
            return (Criteria) this;
        }

        public Criteria andErrDescNotBetween(String value1, String value2) {
            addCriterion("ERR_DESC not between", value1, value2, "errDesc");
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