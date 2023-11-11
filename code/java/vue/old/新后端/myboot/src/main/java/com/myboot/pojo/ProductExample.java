package com.myboot.pojo;

import java.util.ArrayList;
import java.util.List;

public class ProductExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public ProductExample() {
        oredCriteria = new ArrayList<>();
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
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

        public Criteria andNameIsNull() {
            addCriterion("`name` is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("`name` is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("`name` =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("`name` <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("`name` >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("`name` >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("`name` <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("`name` <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("`name` like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("`name` not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("`name` in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("`name` not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("`name` between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("`name` not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("`type` is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("`type` is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("`type` =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("`type` <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("`type` >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("`type` >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("`type` <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("`type` <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("`type` like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("`type` not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("`type` in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("`type` not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("`type` between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("`type` not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andDanwieIsNull() {
            addCriterion("danwie is null");
            return (Criteria) this;
        }

        public Criteria andDanwieIsNotNull() {
            addCriterion("danwie is not null");
            return (Criteria) this;
        }

        public Criteria andDanwieEqualTo(String value) {
            addCriterion("danwie =", value, "danwie");
            return (Criteria) this;
        }

        public Criteria andDanwieNotEqualTo(String value) {
            addCriterion("danwie <>", value, "danwie");
            return (Criteria) this;
        }

        public Criteria andDanwieGreaterThan(String value) {
            addCriterion("danwie >", value, "danwie");
            return (Criteria) this;
        }

        public Criteria andDanwieGreaterThanOrEqualTo(String value) {
            addCriterion("danwie >=", value, "danwie");
            return (Criteria) this;
        }

        public Criteria andDanwieLessThan(String value) {
            addCriterion("danwie <", value, "danwie");
            return (Criteria) this;
        }

        public Criteria andDanwieLessThanOrEqualTo(String value) {
            addCriterion("danwie <=", value, "danwie");
            return (Criteria) this;
        }

        public Criteria andDanwieLike(String value) {
            addCriterion("danwie like", value, "danwie");
            return (Criteria) this;
        }

        public Criteria andDanwieNotLike(String value) {
            addCriterion("danwie not like", value, "danwie");
            return (Criteria) this;
        }

        public Criteria andDanwieIn(List<String> values) {
            addCriterion("danwie in", values, "danwie");
            return (Criteria) this;
        }

        public Criteria andDanwieNotIn(List<String> values) {
            addCriterion("danwie not in", values, "danwie");
            return (Criteria) this;
        }

        public Criteria andDanwieBetween(String value1, String value2) {
            addCriterion("danwie between", value1, value2, "danwie");
            return (Criteria) this;
        }

        public Criteria andDanwieNotBetween(String value1, String value2) {
            addCriterion("danwie not between", value1, value2, "danwie");
            return (Criteria) this;
        }

        public Criteria andCaizhiIsNull() {
            addCriterion("caizhi is null");
            return (Criteria) this;
        }

        public Criteria andCaizhiIsNotNull() {
            addCriterion("caizhi is not null");
            return (Criteria) this;
        }

        public Criteria andCaizhiEqualTo(String value) {
            addCriterion("caizhi =", value, "caizhi");
            return (Criteria) this;
        }

        public Criteria andCaizhiNotEqualTo(String value) {
            addCriterion("caizhi <>", value, "caizhi");
            return (Criteria) this;
        }

        public Criteria andCaizhiGreaterThan(String value) {
            addCriterion("caizhi >", value, "caizhi");
            return (Criteria) this;
        }

        public Criteria andCaizhiGreaterThanOrEqualTo(String value) {
            addCriterion("caizhi >=", value, "caizhi");
            return (Criteria) this;
        }

        public Criteria andCaizhiLessThan(String value) {
            addCriterion("caizhi <", value, "caizhi");
            return (Criteria) this;
        }

        public Criteria andCaizhiLessThanOrEqualTo(String value) {
            addCriterion("caizhi <=", value, "caizhi");
            return (Criteria) this;
        }

        public Criteria andCaizhiLike(String value) {
            addCriterion("caizhi like", value, "caizhi");
            return (Criteria) this;
        }

        public Criteria andCaizhiNotLike(String value) {
            addCriterion("caizhi not like", value, "caizhi");
            return (Criteria) this;
        }

        public Criteria andCaizhiIn(List<String> values) {
            addCriterion("caizhi in", values, "caizhi");
            return (Criteria) this;
        }

        public Criteria andCaizhiNotIn(List<String> values) {
            addCriterion("caizhi not in", values, "caizhi");
            return (Criteria) this;
        }

        public Criteria andCaizhiBetween(String value1, String value2) {
            addCriterion("caizhi between", value1, value2, "caizhi");
            return (Criteria) this;
        }

        public Criteria andCaizhiNotBetween(String value1, String value2) {
            addCriterion("caizhi not between", value1, value2, "caizhi");
            return (Criteria) this;
        }

        public Criteria andJishuBiaozhunIsNull() {
            addCriterion("jishu_biaozhun is null");
            return (Criteria) this;
        }

        public Criteria andJishuBiaozhunIsNotNull() {
            addCriterion("jishu_biaozhun is not null");
            return (Criteria) this;
        }

        public Criteria andJishuBiaozhunEqualTo(String value) {
            addCriterion("jishu_biaozhun =", value, "jishuBiaozhun");
            return (Criteria) this;
        }

        public Criteria andJishuBiaozhunNotEqualTo(String value) {
            addCriterion("jishu_biaozhun <>", value, "jishuBiaozhun");
            return (Criteria) this;
        }

        public Criteria andJishuBiaozhunGreaterThan(String value) {
            addCriterion("jishu_biaozhun >", value, "jishuBiaozhun");
            return (Criteria) this;
        }

        public Criteria andJishuBiaozhunGreaterThanOrEqualTo(String value) {
            addCriterion("jishu_biaozhun >=", value, "jishuBiaozhun");
            return (Criteria) this;
        }

        public Criteria andJishuBiaozhunLessThan(String value) {
            addCriterion("jishu_biaozhun <", value, "jishuBiaozhun");
            return (Criteria) this;
        }

        public Criteria andJishuBiaozhunLessThanOrEqualTo(String value) {
            addCriterion("jishu_biaozhun <=", value, "jishuBiaozhun");
            return (Criteria) this;
        }

        public Criteria andJishuBiaozhunLike(String value) {
            addCriterion("jishu_biaozhun like", value, "jishuBiaozhun");
            return (Criteria) this;
        }

        public Criteria andJishuBiaozhunNotLike(String value) {
            addCriterion("jishu_biaozhun not like", value, "jishuBiaozhun");
            return (Criteria) this;
        }

        public Criteria andJishuBiaozhunIn(List<String> values) {
            addCriterion("jishu_biaozhun in", values, "jishuBiaozhun");
            return (Criteria) this;
        }

        public Criteria andJishuBiaozhunNotIn(List<String> values) {
            addCriterion("jishu_biaozhun not in", values, "jishuBiaozhun");
            return (Criteria) this;
        }

        public Criteria andJishuBiaozhunBetween(String value1, String value2) {
            addCriterion("jishu_biaozhun between", value1, value2, "jishuBiaozhun");
            return (Criteria) this;
        }

        public Criteria andJishuBiaozhunNotBetween(String value1, String value2) {
            addCriterion("jishu_biaozhun not between", value1, value2, "jishuBiaozhun");
            return (Criteria) this;
        }

        public Criteria andZhibaoDengjiIsNull() {
            addCriterion("zhibao_dengji is null");
            return (Criteria) this;
        }

        public Criteria andZhibaoDengjiIsNotNull() {
            addCriterion("zhibao_dengji is not null");
            return (Criteria) this;
        }

        public Criteria andZhibaoDengjiEqualTo(String value) {
            addCriterion("zhibao_dengji =", value, "zhibaoDengji");
            return (Criteria) this;
        }

        public Criteria andZhibaoDengjiNotEqualTo(String value) {
            addCriterion("zhibao_dengji <>", value, "zhibaoDengji");
            return (Criteria) this;
        }

        public Criteria andZhibaoDengjiGreaterThan(String value) {
            addCriterion("zhibao_dengji >", value, "zhibaoDengji");
            return (Criteria) this;
        }

        public Criteria andZhibaoDengjiGreaterThanOrEqualTo(String value) {
            addCriterion("zhibao_dengji >=", value, "zhibaoDengji");
            return (Criteria) this;
        }

        public Criteria andZhibaoDengjiLessThan(String value) {
            addCriterion("zhibao_dengji <", value, "zhibaoDengji");
            return (Criteria) this;
        }

        public Criteria andZhibaoDengjiLessThanOrEqualTo(String value) {
            addCriterion("zhibao_dengji <=", value, "zhibaoDengji");
            return (Criteria) this;
        }

        public Criteria andZhibaoDengjiLike(String value) {
            addCriterion("zhibao_dengji like", value, "zhibaoDengji");
            return (Criteria) this;
        }

        public Criteria andZhibaoDengjiNotLike(String value) {
            addCriterion("zhibao_dengji not like", value, "zhibaoDengji");
            return (Criteria) this;
        }

        public Criteria andZhibaoDengjiIn(List<String> values) {
            addCriterion("zhibao_dengji in", values, "zhibaoDengji");
            return (Criteria) this;
        }

        public Criteria andZhibaoDengjiNotIn(List<String> values) {
            addCriterion("zhibao_dengji not in", values, "zhibaoDengji");
            return (Criteria) this;
        }

        public Criteria andZhibaoDengjiBetween(String value1, String value2) {
            addCriterion("zhibao_dengji between", value1, value2, "zhibaoDengji");
            return (Criteria) this;
        }

        public Criteria andZhibaoDengjiNotBetween(String value1, String value2) {
            addCriterion("zhibao_dengji not between", value1, value2, "zhibaoDengji");
            return (Criteria) this;
        }

        public Criteria andBeizhuIsNull() {
            addCriterion("beizhu is null");
            return (Criteria) this;
        }

        public Criteria andBeizhuIsNotNull() {
            addCriterion("beizhu is not null");
            return (Criteria) this;
        }

        public Criteria andBeizhuEqualTo(String value) {
            addCriterion("beizhu =", value, "beizhu");
            return (Criteria) this;
        }

        public Criteria andBeizhuNotEqualTo(String value) {
            addCriterion("beizhu <>", value, "beizhu");
            return (Criteria) this;
        }

        public Criteria andBeizhuGreaterThan(String value) {
            addCriterion("beizhu >", value, "beizhu");
            return (Criteria) this;
        }

        public Criteria andBeizhuGreaterThanOrEqualTo(String value) {
            addCriterion("beizhu >=", value, "beizhu");
            return (Criteria) this;
        }

        public Criteria andBeizhuLessThan(String value) {
            addCriterion("beizhu <", value, "beizhu");
            return (Criteria) this;
        }

        public Criteria andBeizhuLessThanOrEqualTo(String value) {
            addCriterion("beizhu <=", value, "beizhu");
            return (Criteria) this;
        }

        public Criteria andBeizhuLike(String value) {
            addCriterion("beizhu like", value, "beizhu");
            return (Criteria) this;
        }

        public Criteria andBeizhuNotLike(String value) {
            addCriterion("beizhu not like", value, "beizhu");
            return (Criteria) this;
        }

        public Criteria andBeizhuIn(List<String> values) {
            addCriterion("beizhu in", values, "beizhu");
            return (Criteria) this;
        }

        public Criteria andBeizhuNotIn(List<String> values) {
            addCriterion("beizhu not in", values, "beizhu");
            return (Criteria) this;
        }

        public Criteria andBeizhuBetween(String value1, String value2) {
            addCriterion("beizhu between", value1, value2, "beizhu");
            return (Criteria) this;
        }

        public Criteria andBeizhuNotBetween(String value1, String value2) {
            addCriterion("beizhu not between", value1, value2, "beizhu");
            return (Criteria) this;
        }
    }

    /**
     */
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