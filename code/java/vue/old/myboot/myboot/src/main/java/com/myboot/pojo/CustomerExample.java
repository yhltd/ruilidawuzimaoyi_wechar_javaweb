package com.myboot.pojo;

import java.util.ArrayList;
import java.util.List;

public class CustomerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public CustomerExample() {
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

        public Criteria andBianhaoIsNull() {
            addCriterion("bianhao is null");
            return (Criteria) this;
        }

        public Criteria andBianhaoIsNotNull() {
            addCriterion("bianhao is not null");
            return (Criteria) this;
        }

        public Criteria andBianhaoEqualTo(String value) {
            addCriterion("bianhao =", value, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoNotEqualTo(String value) {
            addCriterion("bianhao <>", value, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoGreaterThan(String value) {
            addCriterion("bianhao >", value, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoGreaterThanOrEqualTo(String value) {
            addCriterion("bianhao >=", value, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoLessThan(String value) {
            addCriterion("bianhao <", value, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoLessThanOrEqualTo(String value) {
            addCriterion("bianhao <=", value, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoLike(String value) {
            addCriterion("bianhao like", value, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoNotLike(String value) {
            addCriterion("bianhao not like", value, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoIn(List<String> values) {
            addCriterion("bianhao in", values, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoNotIn(List<String> values) {
            addCriterion("bianhao not in", values, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoBetween(String value1, String value2) {
            addCriterion("bianhao between", value1, value2, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoNotBetween(String value1, String value2) {
            addCriterion("bianhao not between", value1, value2, "bianhao");
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

        public Criteria andShangjiDanweiIsNull() {
            addCriterion("shangji_danwei is null");
            return (Criteria) this;
        }

        public Criteria andShangjiDanweiIsNotNull() {
            addCriterion("shangji_danwei is not null");
            return (Criteria) this;
        }

        public Criteria andShangjiDanweiEqualTo(String value) {
            addCriterion("shangji_danwei =", value, "shangjiDanwei");
            return (Criteria) this;
        }

        public Criteria andShangjiDanweiNotEqualTo(String value) {
            addCriterion("shangji_danwei <>", value, "shangjiDanwei");
            return (Criteria) this;
        }

        public Criteria andShangjiDanweiGreaterThan(String value) {
            addCriterion("shangji_danwei >", value, "shangjiDanwei");
            return (Criteria) this;
        }

        public Criteria andShangjiDanweiGreaterThanOrEqualTo(String value) {
            addCriterion("shangji_danwei >=", value, "shangjiDanwei");
            return (Criteria) this;
        }

        public Criteria andShangjiDanweiLessThan(String value) {
            addCriterion("shangji_danwei <", value, "shangjiDanwei");
            return (Criteria) this;
        }

        public Criteria andShangjiDanweiLessThanOrEqualTo(String value) {
            addCriterion("shangji_danwei <=", value, "shangjiDanwei");
            return (Criteria) this;
        }

        public Criteria andShangjiDanweiLike(String value) {
            addCriterion("shangji_danwei like", value, "shangjiDanwei");
            return (Criteria) this;
        }

        public Criteria andShangjiDanweiNotLike(String value) {
            addCriterion("shangji_danwei not like", value, "shangjiDanwei");
            return (Criteria) this;
        }

        public Criteria andShangjiDanweiIn(List<String> values) {
            addCriterion("shangji_danwei in", values, "shangjiDanwei");
            return (Criteria) this;
        }

        public Criteria andShangjiDanweiNotIn(List<String> values) {
            addCriterion("shangji_danwei not in", values, "shangjiDanwei");
            return (Criteria) this;
        }

        public Criteria andShangjiDanweiBetween(String value1, String value2) {
            addCriterion("shangji_danwei between", value1, value2, "shangjiDanwei");
            return (Criteria) this;
        }

        public Criteria andShangjiDanweiNotBetween(String value1, String value2) {
            addCriterion("shangji_danwei not between", value1, value2, "shangjiDanwei");
            return (Criteria) this;
        }

        public Criteria andKehuDengjiIsNull() {
            addCriterion("kehu_dengji is null");
            return (Criteria) this;
        }

        public Criteria andKehuDengjiIsNotNull() {
            addCriterion("kehu_dengji is not null");
            return (Criteria) this;
        }

        public Criteria andKehuDengjiEqualTo(String value) {
            addCriterion("kehu_dengji =", value, "kehuDengji");
            return (Criteria) this;
        }

        public Criteria andKehuDengjiNotEqualTo(String value) {
            addCriterion("kehu_dengji <>", value, "kehuDengji");
            return (Criteria) this;
        }

        public Criteria andKehuDengjiGreaterThan(String value) {
            addCriterion("kehu_dengji >", value, "kehuDengji");
            return (Criteria) this;
        }

        public Criteria andKehuDengjiGreaterThanOrEqualTo(String value) {
            addCriterion("kehu_dengji >=", value, "kehuDengji");
            return (Criteria) this;
        }

        public Criteria andKehuDengjiLessThan(String value) {
            addCriterion("kehu_dengji <", value, "kehuDengji");
            return (Criteria) this;
        }

        public Criteria andKehuDengjiLessThanOrEqualTo(String value) {
            addCriterion("kehu_dengji <=", value, "kehuDengji");
            return (Criteria) this;
        }

        public Criteria andKehuDengjiLike(String value) {
            addCriterion("kehu_dengji like", value, "kehuDengji");
            return (Criteria) this;
        }

        public Criteria andKehuDengjiNotLike(String value) {
            addCriterion("kehu_dengji not like", value, "kehuDengji");
            return (Criteria) this;
        }

        public Criteria andKehuDengjiIn(List<String> values) {
            addCriterion("kehu_dengji in", values, "kehuDengji");
            return (Criteria) this;
        }

        public Criteria andKehuDengjiNotIn(List<String> values) {
            addCriterion("kehu_dengji not in", values, "kehuDengji");
            return (Criteria) this;
        }

        public Criteria andKehuDengjiBetween(String value1, String value2) {
            addCriterion("kehu_dengji between", value1, value2, "kehuDengji");
            return (Criteria) this;
        }

        public Criteria andKehuDengjiNotBetween(String value1, String value2) {
            addCriterion("kehu_dengji not between", value1, value2, "kehuDengji");
            return (Criteria) this;
        }

        public Criteria andJiageDengjiIsNull() {
            addCriterion("jiage_dengji is null");
            return (Criteria) this;
        }

        public Criteria andJiageDengjiIsNotNull() {
            addCriterion("jiage_dengji is not null");
            return (Criteria) this;
        }

        public Criteria andJiageDengjiEqualTo(String value) {
            addCriterion("jiage_dengji =", value, "jiageDengji");
            return (Criteria) this;
        }

        public Criteria andJiageDengjiNotEqualTo(String value) {
            addCriterion("jiage_dengji <>", value, "jiageDengji");
            return (Criteria) this;
        }

        public Criteria andJiageDengjiGreaterThan(String value) {
            addCriterion("jiage_dengji >", value, "jiageDengji");
            return (Criteria) this;
        }

        public Criteria andJiageDengjiGreaterThanOrEqualTo(String value) {
            addCriterion("jiage_dengji >=", value, "jiageDengji");
            return (Criteria) this;
        }

        public Criteria andJiageDengjiLessThan(String value) {
            addCriterion("jiage_dengji <", value, "jiageDengji");
            return (Criteria) this;
        }

        public Criteria andJiageDengjiLessThanOrEqualTo(String value) {
            addCriterion("jiage_dengji <=", value, "jiageDengji");
            return (Criteria) this;
        }

        public Criteria andJiageDengjiLike(String value) {
            addCriterion("jiage_dengji like", value, "jiageDengji");
            return (Criteria) this;
        }

        public Criteria andJiageDengjiNotLike(String value) {
            addCriterion("jiage_dengji not like", value, "jiageDengji");
            return (Criteria) this;
        }

        public Criteria andJiageDengjiIn(List<String> values) {
            addCriterion("jiage_dengji in", values, "jiageDengji");
            return (Criteria) this;
        }

        public Criteria andJiageDengjiNotIn(List<String> values) {
            addCriterion("jiage_dengji not in", values, "jiageDengji");
            return (Criteria) this;
        }

        public Criteria andJiageDengjiBetween(String value1, String value2) {
            addCriterion("jiage_dengji between", value1, value2, "jiageDengji");
            return (Criteria) this;
        }

        public Criteria andJiageDengjiNotBetween(String value1, String value2) {
            addCriterion("jiage_dengji not between", value1, value2, "jiageDengji");
            return (Criteria) this;
        }

        public Criteria andSuozaiDiquIsNull() {
            addCriterion("suozai_diqu is null");
            return (Criteria) this;
        }

        public Criteria andSuozaiDiquIsNotNull() {
            addCriterion("suozai_diqu is not null");
            return (Criteria) this;
        }

        public Criteria andSuozaiDiquEqualTo(String value) {
            addCriterion("suozai_diqu =", value, "suozaiDiqu");
            return (Criteria) this;
        }

        public Criteria andSuozaiDiquNotEqualTo(String value) {
            addCriterion("suozai_diqu <>", value, "suozaiDiqu");
            return (Criteria) this;
        }

        public Criteria andSuozaiDiquGreaterThan(String value) {
            addCriterion("suozai_diqu >", value, "suozaiDiqu");
            return (Criteria) this;
        }

        public Criteria andSuozaiDiquGreaterThanOrEqualTo(String value) {
            addCriterion("suozai_diqu >=", value, "suozaiDiqu");
            return (Criteria) this;
        }

        public Criteria andSuozaiDiquLessThan(String value) {
            addCriterion("suozai_diqu <", value, "suozaiDiqu");
            return (Criteria) this;
        }

        public Criteria andSuozaiDiquLessThanOrEqualTo(String value) {
            addCriterion("suozai_diqu <=", value, "suozaiDiqu");
            return (Criteria) this;
        }

        public Criteria andSuozaiDiquLike(String value) {
            addCriterion("suozai_diqu like", value, "suozaiDiqu");
            return (Criteria) this;
        }

        public Criteria andSuozaiDiquNotLike(String value) {
            addCriterion("suozai_diqu not like", value, "suozaiDiqu");
            return (Criteria) this;
        }

        public Criteria andSuozaiDiquIn(List<String> values) {
            addCriterion("suozai_diqu in", values, "suozaiDiqu");
            return (Criteria) this;
        }

        public Criteria andSuozaiDiquNotIn(List<String> values) {
            addCriterion("suozai_diqu not in", values, "suozaiDiqu");
            return (Criteria) this;
        }

        public Criteria andSuozaiDiquBetween(String value1, String value2) {
            addCriterion("suozai_diqu between", value1, value2, "suozaiDiqu");
            return (Criteria) this;
        }

        public Criteria andSuozaiDiquNotBetween(String value1, String value2) {
            addCriterion("suozai_diqu not between", value1, value2, "suozaiDiqu");
            return (Criteria) this;
        }

        public Criteria andDizhiIsNull() {
            addCriterion("dizhi is null");
            return (Criteria) this;
        }

        public Criteria andDizhiIsNotNull() {
            addCriterion("dizhi is not null");
            return (Criteria) this;
        }

        public Criteria andDizhiEqualTo(String value) {
            addCriterion("dizhi =", value, "dizhi");
            return (Criteria) this;
        }

        public Criteria andDizhiNotEqualTo(String value) {
            addCriterion("dizhi <>", value, "dizhi");
            return (Criteria) this;
        }

        public Criteria andDizhiGreaterThan(String value) {
            addCriterion("dizhi >", value, "dizhi");
            return (Criteria) this;
        }

        public Criteria andDizhiGreaterThanOrEqualTo(String value) {
            addCriterion("dizhi >=", value, "dizhi");
            return (Criteria) this;
        }

        public Criteria andDizhiLessThan(String value) {
            addCriterion("dizhi <", value, "dizhi");
            return (Criteria) this;
        }

        public Criteria andDizhiLessThanOrEqualTo(String value) {
            addCriterion("dizhi <=", value, "dizhi");
            return (Criteria) this;
        }

        public Criteria andDizhiLike(String value) {
            addCriterion("dizhi like", value, "dizhi");
            return (Criteria) this;
        }

        public Criteria andDizhiNotLike(String value) {
            addCriterion("dizhi not like", value, "dizhi");
            return (Criteria) this;
        }

        public Criteria andDizhiIn(List<String> values) {
            addCriterion("dizhi in", values, "dizhi");
            return (Criteria) this;
        }

        public Criteria andDizhiNotIn(List<String> values) {
            addCriterion("dizhi not in", values, "dizhi");
            return (Criteria) this;
        }

        public Criteria andDizhiBetween(String value1, String value2) {
            addCriterion("dizhi between", value1, value2, "dizhi");
            return (Criteria) this;
        }

        public Criteria andDizhiNotBetween(String value1, String value2) {
            addCriterion("dizhi not between", value1, value2, "dizhi");
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

        public Criteria andYewuyuanIsNull() {
            addCriterion("yewuyuan is null");
            return (Criteria) this;
        }

        public Criteria andYewuyuanIsNotNull() {
            addCriterion("yewuyuan is not null");
            return (Criteria) this;
        }

        public Criteria andYewuyuanEqualTo(String value) {
            addCriterion("yewuyuan =", value, "yewuyuan");
            return (Criteria) this;
        }

        public Criteria andYewuyuanNotEqualTo(String value) {
            addCriterion("yewuyuan <>", value, "yewuyuan");
            return (Criteria) this;
        }

        public Criteria andYewuyuanGreaterThan(String value) {
            addCriterion("yewuyuan >", value, "yewuyuan");
            return (Criteria) this;
        }

        public Criteria andYewuyuanGreaterThanOrEqualTo(String value) {
            addCriterion("yewuyuan >=", value, "yewuyuan");
            return (Criteria) this;
        }

        public Criteria andYewuyuanLessThan(String value) {
            addCriterion("yewuyuan <", value, "yewuyuan");
            return (Criteria) this;
        }

        public Criteria andYewuyuanLessThanOrEqualTo(String value) {
            addCriterion("yewuyuan <=", value, "yewuyuan");
            return (Criteria) this;
        }

        public Criteria andYewuyuanLike(String value) {
            addCriterion("yewuyuan like", value, "yewuyuan");
            return (Criteria) this;
        }

        public Criteria andYewuyuanNotLike(String value) {
            addCriterion("yewuyuan not like", value, "yewuyuan");
            return (Criteria) this;
        }

        public Criteria andYewuyuanIn(List<String> values) {
            addCriterion("yewuyuan in", values, "yewuyuan");
            return (Criteria) this;
        }

        public Criteria andYewuyuanNotIn(List<String> values) {
            addCriterion("yewuyuan not in", values, "yewuyuan");
            return (Criteria) this;
        }

        public Criteria andYewuyuanBetween(String value1, String value2) {
            addCriterion("yewuyuan between", value1, value2, "yewuyuan");
            return (Criteria) this;
        }

        public Criteria andYewuyuanNotBetween(String value1, String value2) {
            addCriterion("yewuyuan not between", value1, value2, "yewuyuan");
            return (Criteria) this;
        }

        public Criteria andShoujianNameIsNull() {
            addCriterion("shoujian_name is null");
            return (Criteria) this;
        }

        public Criteria andShoujianNameIsNotNull() {
            addCriterion("shoujian_name is not null");
            return (Criteria) this;
        }

        public Criteria andShoujianNameEqualTo(String value) {
            addCriterion("shoujian_name =", value, "shoujianName");
            return (Criteria) this;
        }

        public Criteria andShoujianNameNotEqualTo(String value) {
            addCriterion("shoujian_name <>", value, "shoujianName");
            return (Criteria) this;
        }

        public Criteria andShoujianNameGreaterThan(String value) {
            addCriterion("shoujian_name >", value, "shoujianName");
            return (Criteria) this;
        }

        public Criteria andShoujianNameGreaterThanOrEqualTo(String value) {
            addCriterion("shoujian_name >=", value, "shoujianName");
            return (Criteria) this;
        }

        public Criteria andShoujianNameLessThan(String value) {
            addCriterion("shoujian_name <", value, "shoujianName");
            return (Criteria) this;
        }

        public Criteria andShoujianNameLessThanOrEqualTo(String value) {
            addCriterion("shoujian_name <=", value, "shoujianName");
            return (Criteria) this;
        }

        public Criteria andShoujianNameLike(String value) {
            addCriterion("shoujian_name like", value, "shoujianName");
            return (Criteria) this;
        }

        public Criteria andShoujianNameNotLike(String value) {
            addCriterion("shoujian_name not like", value, "shoujianName");
            return (Criteria) this;
        }

        public Criteria andShoujianNameIn(List<String> values) {
            addCriterion("shoujian_name in", values, "shoujianName");
            return (Criteria) this;
        }

        public Criteria andShoujianNameNotIn(List<String> values) {
            addCriterion("shoujian_name not in", values, "shoujianName");
            return (Criteria) this;
        }

        public Criteria andShoujianNameBetween(String value1, String value2) {
            addCriterion("shoujian_name between", value1, value2, "shoujianName");
            return (Criteria) this;
        }

        public Criteria andShoujianNameNotBetween(String value1, String value2) {
            addCriterion("shoujian_name not between", value1, value2, "shoujianName");
            return (Criteria) this;
        }

        public Criteria andShoujianPhoneIsNull() {
            addCriterion("shoujian_phone is null");
            return (Criteria) this;
        }

        public Criteria andShoujianPhoneIsNotNull() {
            addCriterion("shoujian_phone is not null");
            return (Criteria) this;
        }

        public Criteria andShoujianPhoneEqualTo(String value) {
            addCriterion("shoujian_phone =", value, "shoujianPhone");
            return (Criteria) this;
        }

        public Criteria andShoujianPhoneNotEqualTo(String value) {
            addCriterion("shoujian_phone <>", value, "shoujianPhone");
            return (Criteria) this;
        }

        public Criteria andShoujianPhoneGreaterThan(String value) {
            addCriterion("shoujian_phone >", value, "shoujianPhone");
            return (Criteria) this;
        }

        public Criteria andShoujianPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("shoujian_phone >=", value, "shoujianPhone");
            return (Criteria) this;
        }

        public Criteria andShoujianPhoneLessThan(String value) {
            addCriterion("shoujian_phone <", value, "shoujianPhone");
            return (Criteria) this;
        }

        public Criteria andShoujianPhoneLessThanOrEqualTo(String value) {
            addCriterion("shoujian_phone <=", value, "shoujianPhone");
            return (Criteria) this;
        }

        public Criteria andShoujianPhoneLike(String value) {
            addCriterion("shoujian_phone like", value, "shoujianPhone");
            return (Criteria) this;
        }

        public Criteria andShoujianPhoneNotLike(String value) {
            addCriterion("shoujian_phone not like", value, "shoujianPhone");
            return (Criteria) this;
        }

        public Criteria andShoujianPhoneIn(List<String> values) {
            addCriterion("shoujian_phone in", values, "shoujianPhone");
            return (Criteria) this;
        }

        public Criteria andShoujianPhoneNotIn(List<String> values) {
            addCriterion("shoujian_phone not in", values, "shoujianPhone");
            return (Criteria) this;
        }

        public Criteria andShoujianPhoneBetween(String value1, String value2) {
            addCriterion("shoujian_phone between", value1, value2, "shoujianPhone");
            return (Criteria) this;
        }

        public Criteria andShoujianPhoneNotBetween(String value1, String value2) {
            addCriterion("shoujian_phone not between", value1, value2, "shoujianPhone");
            return (Criteria) this;
        }

        public Criteria andShoujianDizhiIsNull() {
            addCriterion("shoujian_dizhi is null");
            return (Criteria) this;
        }

        public Criteria andShoujianDizhiIsNotNull() {
            addCriterion("shoujian_dizhi is not null");
            return (Criteria) this;
        }

        public Criteria andShoujianDizhiEqualTo(String value) {
            addCriterion("shoujian_dizhi =", value, "shoujianDizhi");
            return (Criteria) this;
        }

        public Criteria andShoujianDizhiNotEqualTo(String value) {
            addCriterion("shoujian_dizhi <>", value, "shoujianDizhi");
            return (Criteria) this;
        }

        public Criteria andShoujianDizhiGreaterThan(String value) {
            addCriterion("shoujian_dizhi >", value, "shoujianDizhi");
            return (Criteria) this;
        }

        public Criteria andShoujianDizhiGreaterThanOrEqualTo(String value) {
            addCriterion("shoujian_dizhi >=", value, "shoujianDizhi");
            return (Criteria) this;
        }

        public Criteria andShoujianDizhiLessThan(String value) {
            addCriterion("shoujian_dizhi <", value, "shoujianDizhi");
            return (Criteria) this;
        }

        public Criteria andShoujianDizhiLessThanOrEqualTo(String value) {
            addCriterion("shoujian_dizhi <=", value, "shoujianDizhi");
            return (Criteria) this;
        }

        public Criteria andShoujianDizhiLike(String value) {
            addCriterion("shoujian_dizhi like", value, "shoujianDizhi");
            return (Criteria) this;
        }

        public Criteria andShoujianDizhiNotLike(String value) {
            addCriterion("shoujian_dizhi not like", value, "shoujianDizhi");
            return (Criteria) this;
        }

        public Criteria andShoujianDizhiIn(List<String> values) {
            addCriterion("shoujian_dizhi in", values, "shoujianDizhi");
            return (Criteria) this;
        }

        public Criteria andShoujianDizhiNotIn(List<String> values) {
            addCriterion("shoujian_dizhi not in", values, "shoujianDizhi");
            return (Criteria) this;
        }

        public Criteria andShoujianDizhiBetween(String value1, String value2) {
            addCriterion("shoujian_dizhi between", value1, value2, "shoujianDizhi");
            return (Criteria) this;
        }

        public Criteria andShoujianDizhiNotBetween(String value1, String value2) {
            addCriterion("shoujian_dizhi not between", value1, value2, "shoujianDizhi");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDanweiIsNull() {
            addCriterion("kaipiao_danwei is null");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDanweiIsNotNull() {
            addCriterion("kaipiao_danwei is not null");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDanweiEqualTo(String value) {
            addCriterion("kaipiao_danwei =", value, "kaipiaoDanwei");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDanweiNotEqualTo(String value) {
            addCriterion("kaipiao_danwei <>", value, "kaipiaoDanwei");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDanweiGreaterThan(String value) {
            addCriterion("kaipiao_danwei >", value, "kaipiaoDanwei");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDanweiGreaterThanOrEqualTo(String value) {
            addCriterion("kaipiao_danwei >=", value, "kaipiaoDanwei");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDanweiLessThan(String value) {
            addCriterion("kaipiao_danwei <", value, "kaipiaoDanwei");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDanweiLessThanOrEqualTo(String value) {
            addCriterion("kaipiao_danwei <=", value, "kaipiaoDanwei");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDanweiLike(String value) {
            addCriterion("kaipiao_danwei like", value, "kaipiaoDanwei");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDanweiNotLike(String value) {
            addCriterion("kaipiao_danwei not like", value, "kaipiaoDanwei");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDanweiIn(List<String> values) {
            addCriterion("kaipiao_danwei in", values, "kaipiaoDanwei");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDanweiNotIn(List<String> values) {
            addCriterion("kaipiao_danwei not in", values, "kaipiaoDanwei");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDanweiBetween(String value1, String value2) {
            addCriterion("kaipiao_danwei between", value1, value2, "kaipiaoDanwei");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDanweiNotBetween(String value1, String value2) {
            addCriterion("kaipiao_danwei not between", value1, value2, "kaipiaoDanwei");
            return (Criteria) this;
        }

        public Criteria andShibiehaoIsNull() {
            addCriterion("shibiehao is null");
            return (Criteria) this;
        }

        public Criteria andShibiehaoIsNotNull() {
            addCriterion("shibiehao is not null");
            return (Criteria) this;
        }

        public Criteria andShibiehaoEqualTo(String value) {
            addCriterion("shibiehao =", value, "shibiehao");
            return (Criteria) this;
        }

        public Criteria andShibiehaoNotEqualTo(String value) {
            addCriterion("shibiehao <>", value, "shibiehao");
            return (Criteria) this;
        }

        public Criteria andShibiehaoGreaterThan(String value) {
            addCriterion("shibiehao >", value, "shibiehao");
            return (Criteria) this;
        }

        public Criteria andShibiehaoGreaterThanOrEqualTo(String value) {
            addCriterion("shibiehao >=", value, "shibiehao");
            return (Criteria) this;
        }

        public Criteria andShibiehaoLessThan(String value) {
            addCriterion("shibiehao <", value, "shibiehao");
            return (Criteria) this;
        }

        public Criteria andShibiehaoLessThanOrEqualTo(String value) {
            addCriterion("shibiehao <=", value, "shibiehao");
            return (Criteria) this;
        }

        public Criteria andShibiehaoLike(String value) {
            addCriterion("shibiehao like", value, "shibiehao");
            return (Criteria) this;
        }

        public Criteria andShibiehaoNotLike(String value) {
            addCriterion("shibiehao not like", value, "shibiehao");
            return (Criteria) this;
        }

        public Criteria andShibiehaoIn(List<String> values) {
            addCriterion("shibiehao in", values, "shibiehao");
            return (Criteria) this;
        }

        public Criteria andShibiehaoNotIn(List<String> values) {
            addCriterion("shibiehao not in", values, "shibiehao");
            return (Criteria) this;
        }

        public Criteria andShibiehaoBetween(String value1, String value2) {
            addCriterion("shibiehao between", value1, value2, "shibiehao");
            return (Criteria) this;
        }

        public Criteria andShibiehaoNotBetween(String value1, String value2) {
            addCriterion("shibiehao not between", value1, value2, "shibiehao");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDizhiIsNull() {
            addCriterion("kaipiao_dizhi is null");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDizhiIsNotNull() {
            addCriterion("kaipiao_dizhi is not null");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDizhiEqualTo(String value) {
            addCriterion("kaipiao_dizhi =", value, "kaipiaoDizhi");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDizhiNotEqualTo(String value) {
            addCriterion("kaipiao_dizhi <>", value, "kaipiaoDizhi");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDizhiGreaterThan(String value) {
            addCriterion("kaipiao_dizhi >", value, "kaipiaoDizhi");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDizhiGreaterThanOrEqualTo(String value) {
            addCriterion("kaipiao_dizhi >=", value, "kaipiaoDizhi");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDizhiLessThan(String value) {
            addCriterion("kaipiao_dizhi <", value, "kaipiaoDizhi");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDizhiLessThanOrEqualTo(String value) {
            addCriterion("kaipiao_dizhi <=", value, "kaipiaoDizhi");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDizhiLike(String value) {
            addCriterion("kaipiao_dizhi like", value, "kaipiaoDizhi");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDizhiNotLike(String value) {
            addCriterion("kaipiao_dizhi not like", value, "kaipiaoDizhi");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDizhiIn(List<String> values) {
            addCriterion("kaipiao_dizhi in", values, "kaipiaoDizhi");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDizhiNotIn(List<String> values) {
            addCriterion("kaipiao_dizhi not in", values, "kaipiaoDizhi");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDizhiBetween(String value1, String value2) {
            addCriterion("kaipiao_dizhi between", value1, value2, "kaipiaoDizhi");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDizhiNotBetween(String value1, String value2) {
            addCriterion("kaipiao_dizhi not between", value1, value2, "kaipiaoDizhi");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDianhuaIsNull() {
            addCriterion("kaipiao_dianhua is null");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDianhuaIsNotNull() {
            addCriterion("kaipiao_dianhua is not null");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDianhuaEqualTo(String value) {
            addCriterion("kaipiao_dianhua =", value, "kaipiaoDianhua");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDianhuaNotEqualTo(String value) {
            addCriterion("kaipiao_dianhua <>", value, "kaipiaoDianhua");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDianhuaGreaterThan(String value) {
            addCriterion("kaipiao_dianhua >", value, "kaipiaoDianhua");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDianhuaGreaterThanOrEqualTo(String value) {
            addCriterion("kaipiao_dianhua >=", value, "kaipiaoDianhua");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDianhuaLessThan(String value) {
            addCriterion("kaipiao_dianhua <", value, "kaipiaoDianhua");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDianhuaLessThanOrEqualTo(String value) {
            addCriterion("kaipiao_dianhua <=", value, "kaipiaoDianhua");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDianhuaLike(String value) {
            addCriterion("kaipiao_dianhua like", value, "kaipiaoDianhua");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDianhuaNotLike(String value) {
            addCriterion("kaipiao_dianhua not like", value, "kaipiaoDianhua");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDianhuaIn(List<String> values) {
            addCriterion("kaipiao_dianhua in", values, "kaipiaoDianhua");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDianhuaNotIn(List<String> values) {
            addCriterion("kaipiao_dianhua not in", values, "kaipiaoDianhua");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDianhuaBetween(String value1, String value2) {
            addCriterion("kaipiao_dianhua between", value1, value2, "kaipiaoDianhua");
            return (Criteria) this;
        }

        public Criteria andKaipiaoDianhuaNotBetween(String value1, String value2) {
            addCriterion("kaipiao_dianhua not between", value1, value2, "kaipiaoDianhua");
            return (Criteria) this;
        }

        public Criteria andKaipiaoYinhangIsNull() {
            addCriterion("kaipiao_yinhang is null");
            return (Criteria) this;
        }

        public Criteria andKaipiaoYinhangIsNotNull() {
            addCriterion("kaipiao_yinhang is not null");
            return (Criteria) this;
        }

        public Criteria andKaipiaoYinhangEqualTo(String value) {
            addCriterion("kaipiao_yinhang =", value, "kaipiaoYinhang");
            return (Criteria) this;
        }

        public Criteria andKaipiaoYinhangNotEqualTo(String value) {
            addCriterion("kaipiao_yinhang <>", value, "kaipiaoYinhang");
            return (Criteria) this;
        }

        public Criteria andKaipiaoYinhangGreaterThan(String value) {
            addCriterion("kaipiao_yinhang >", value, "kaipiaoYinhang");
            return (Criteria) this;
        }

        public Criteria andKaipiaoYinhangGreaterThanOrEqualTo(String value) {
            addCriterion("kaipiao_yinhang >=", value, "kaipiaoYinhang");
            return (Criteria) this;
        }

        public Criteria andKaipiaoYinhangLessThan(String value) {
            addCriterion("kaipiao_yinhang <", value, "kaipiaoYinhang");
            return (Criteria) this;
        }

        public Criteria andKaipiaoYinhangLessThanOrEqualTo(String value) {
            addCriterion("kaipiao_yinhang <=", value, "kaipiaoYinhang");
            return (Criteria) this;
        }

        public Criteria andKaipiaoYinhangLike(String value) {
            addCriterion("kaipiao_yinhang like", value, "kaipiaoYinhang");
            return (Criteria) this;
        }

        public Criteria andKaipiaoYinhangNotLike(String value) {
            addCriterion("kaipiao_yinhang not like", value, "kaipiaoYinhang");
            return (Criteria) this;
        }

        public Criteria andKaipiaoYinhangIn(List<String> values) {
            addCriterion("kaipiao_yinhang in", values, "kaipiaoYinhang");
            return (Criteria) this;
        }

        public Criteria andKaipiaoYinhangNotIn(List<String> values) {
            addCriterion("kaipiao_yinhang not in", values, "kaipiaoYinhang");
            return (Criteria) this;
        }

        public Criteria andKaipiaoYinhangBetween(String value1, String value2) {
            addCriterion("kaipiao_yinhang between", value1, value2, "kaipiaoYinhang");
            return (Criteria) this;
        }

        public Criteria andKaipiaoYinhangNotBetween(String value1, String value2) {
            addCriterion("kaipiao_yinhang not between", value1, value2, "kaipiaoYinhang");
            return (Criteria) this;
        }

        public Criteria andKaipiaoZhanghaoIsNull() {
            addCriterion("kaipiao_zhanghao is null");
            return (Criteria) this;
        }

        public Criteria andKaipiaoZhanghaoIsNotNull() {
            addCriterion("kaipiao_zhanghao is not null");
            return (Criteria) this;
        }

        public Criteria andKaipiaoZhanghaoEqualTo(String value) {
            addCriterion("kaipiao_zhanghao =", value, "kaipiaoZhanghao");
            return (Criteria) this;
        }

        public Criteria andKaipiaoZhanghaoNotEqualTo(String value) {
            addCriterion("kaipiao_zhanghao <>", value, "kaipiaoZhanghao");
            return (Criteria) this;
        }

        public Criteria andKaipiaoZhanghaoGreaterThan(String value) {
            addCriterion("kaipiao_zhanghao >", value, "kaipiaoZhanghao");
            return (Criteria) this;
        }

        public Criteria andKaipiaoZhanghaoGreaterThanOrEqualTo(String value) {
            addCriterion("kaipiao_zhanghao >=", value, "kaipiaoZhanghao");
            return (Criteria) this;
        }

        public Criteria andKaipiaoZhanghaoLessThan(String value) {
            addCriterion("kaipiao_zhanghao <", value, "kaipiaoZhanghao");
            return (Criteria) this;
        }

        public Criteria andKaipiaoZhanghaoLessThanOrEqualTo(String value) {
            addCriterion("kaipiao_zhanghao <=", value, "kaipiaoZhanghao");
            return (Criteria) this;
        }

        public Criteria andKaipiaoZhanghaoLike(String value) {
            addCriterion("kaipiao_zhanghao like", value, "kaipiaoZhanghao");
            return (Criteria) this;
        }

        public Criteria andKaipiaoZhanghaoNotLike(String value) {
            addCriterion("kaipiao_zhanghao not like", value, "kaipiaoZhanghao");
            return (Criteria) this;
        }

        public Criteria andKaipiaoZhanghaoIn(List<String> values) {
            addCriterion("kaipiao_zhanghao in", values, "kaipiaoZhanghao");
            return (Criteria) this;
        }

        public Criteria andKaipiaoZhanghaoNotIn(List<String> values) {
            addCriterion("kaipiao_zhanghao not in", values, "kaipiaoZhanghao");
            return (Criteria) this;
        }

        public Criteria andKaipiaoZhanghaoBetween(String value1, String value2) {
            addCriterion("kaipiao_zhanghao between", value1, value2, "kaipiaoZhanghao");
            return (Criteria) this;
        }

        public Criteria andKaipiaoZhanghaoNotBetween(String value1, String value2) {
            addCriterion("kaipiao_zhanghao not between", value1, value2, "kaipiaoZhanghao");
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