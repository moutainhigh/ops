package com.jyblife.logic.wl.ops.core.mybatis.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.ref.BaseReflector;
import com.jyblife.logic.wl.ops.core.mybatis.mapper.ref.BaseReflector.BaseReflectorColumn;
import com.jyblife.logic.wl.ops.core.mybatis.mapper.ref.BaseReflectorFactory;

import java.util.List;
import java.util.Map;


/**
 * @param <T>
 * @param <K>
 * 
 */
@SuppressWarnings("rawtypes")
public interface BaseMapper<T, K> {

    final Logger logger = LoggerFactory.getLogger(BaseMapper.class);

    static final String INSERT_BATCH_PARAM_LIST = "list";
    static final String GET_PARAM_ID = "id";
    static final String DELETE_WITH_FIELD_PARAM_PARAM = "param";
    static final String LIST_PARAM_PARAM = "param";
    static final String DISABLED_PARAM_DISABLED = "disabled";
    static final String DELETE_PARAM_ID = "id";
    static final String GET_FORM_NO = "formNo";
    static final String GET_BRANCH_ID = "branchId";


    /**
     * 新增
     */
    @InsertProvider(type = CommonSQLProvider.class, method = "insert")
    int insertOne(T obj);

    /**
     * 批量新增
     */
    @InsertProvider(type = CommonSQLProvider.class, method = "insertBatch")
    int insertBatch(@Param(INSERT_BATCH_PARAM_LIST) List<T> list);

    /**
     * 修改
     */
    @UpdateProvider(type = CommonSQLProvider.class, method = "update")
    int updateOne(T obj);

    /**
     * 修改不为空的属性
     */
    @UpdateProvider(type = CommonSQLProvider.class, method = "updateWithoutNull")
    int updateWithoutNull(T obj);

    /**
     * 根据id查询
     */
    @SelectProvider(type = CommonSQLProvider.class, method = "get")
    T get(Class<T> cls, @Param(GET_PARAM_ID) K id);

    /**
     * 根据条件查询
     */
    @SelectProvider(type = CommonSQLProvider.class, method = "list")
    List<T> list(Class<T> cls, @Param(LIST_PARAM_PARAM) Map<String, Object> param);

    /**
     * 根据id删除
     */
    @DeleteProvider(type = CommonSQLProvider.class, method = "delete")
    int delete(Class<T> cls, @Param(DELETE_PARAM_ID) K id);

    /**
     * 根据条件删除
     */
    @DeleteProvider(type = CommonSQLProvider.class, method = "deleteWithField")
    int deleteWithField(Class<T> cls, @Param(DELETE_WITH_FIELD_PARAM_PARAM) Map<String, Object> param);

    /**
     * 根据id禁用
     */
    @UpdateProvider(type = CommonSQLProvider.class, method = "disabled")
    int disabled(Class<T> cls, @Param(GET_PARAM_ID) K id, @Param("disabled") Integer flag);

    /**
     * @param class1
     * @return
     * @Description: 根据字段判断该数据是否存在
     * @author liux01
     */
    @SelectProvider(type = CommonSQLProvider.class, method = "exists")
    int exists(Class class1, String key, @Param(GET_FORM_NO) String value);

    /**
     * @param class1
     * @param key
     * @return
     * @Description: 获取最大值字段信息
     * @author liux01
     */
    @SelectProvider(type = CommonSQLProvider.class, method = "getMax")
    String getMax(Class class1, K key, @Param(DELETE_WITH_FIELD_PARAM_PARAM) Map<String, Object> param);

    class CommonSQLProvider<T, K> {
        private static final String OPEN = "(";
        private static final String CLOSE = ")";

        private static final String COUNT = " COUNT(1) ";

        private static final String MAX = " max";

        /**
         * 构建id的条件sql
         */
        private String idSQL(BaseReflector reflector) {
            return idSQL(reflector, null);
        }

        /**
         * 构建id的条件sql
         */
        private String idSQL(BaseReflector reflector, String idName) {
            StringBuilder idColumnStr = new StringBuilder();
            idColumnStr.append(reflector.getIdColumn().getUnderLineName());
            idColumnStr.append("=");

            idColumnStr.append("#{");
            idColumnStr.append(idName != null ? idName : reflector.getIdColumn().getEntityFieldName());
            //idColumnStr.append(reflector.getIdColumn().getEntityFieldName());
            idColumnStr.append("}");
            return idColumnStr.toString();
        }

        /**
         * @param column
         * @param key
         * @return
         * @Description: 构建字段查询的sql
         * @author liux01
         */
        private String fieldNameSQL(BaseReflectorColumn column, String key) {
            StringBuilder idColumnStr = new StringBuilder();
            idColumnStr.append(column.getUnderLineName());
            idColumnStr.append("=");
            idColumnStr.append("#{");
            idColumnStr.append(key);
            idColumnStr.append("}");
            return idColumnStr.toString();
        }


        public String insert(final T obj) {
            return new SQL() {{
                logger.info("通用insert方法--------start");
                if (obj == null) {
                    logger.warn("insert 对象为空");
                    throw new RuntimeException("insert 对象为空");
                }
                logger.info("insert 对象：{}", obj.toString());

                BaseReflector reflector = BaseReflectorFactory.getReflector(obj.getClass());

                INSERT_INTO(reflector.getTableName());

                Map<String, BaseReflectorColumn> columns = reflector.getAllTableColumn();

                for (String fieldName : columns.keySet()) {
                    if ("id".equalsIgnoreCase(fieldName)) {
                        continue;
                    }
                    BaseReflectorColumn column = columns.get(fieldName);
                    Object val = column.getValue(obj);
                     if (val != null) {
                        StringBuilder columnStr = new StringBuilder();
                        columnStr.append("#{");
                        columnStr.append(column.getEntityFieldName());
                        columnStr.append("}");
                        VALUES("`"+column.getUnderLineName()+"`", columnStr.toString());
                    }
                }

            }}.toString();
        }

        public String insertBatch(List<T> list) {
            StringBuilder sql = new StringBuilder();

            logger.info("通用insertBatch方法--------start");
            if (list == null || list.isEmpty()) {
                logger.warn("insertBatch 集合为空");
                throw new RuntimeException("insertBatch 集合为空");
            }
            logger.info("insertBatch 对象：{}", list.toString());

            BaseReflector reflector = BaseReflectorFactory.getReflector(list.get(0).getClass());
            //获取字段
            Map<String, BaseReflectorColumn> columns = reflector.getAllTableColumn();

            //INSERT INTO table_name
            sql.append("INSERT INTO");
            sql.append(" ");
            sql.append(reflector.getTableName());

            //(columns1, columns2, columns3)
            {
                sql.append(OPEN);
                int i = 0;
                for (String fieldName : columns.keySet()) {
                	if("id".equalsIgnoreCase(fieldName)) {
                		continue;
                	}
                    BaseReflectorColumn column = columns.get(fieldName);

                    if (i > 0) {
                        sql.append(", ");
                    }
                    sql.append("`"+column.getUnderLineName()+"`");
                    i++;
                }
                sql.append(CLOSE);
            }

            //values(#{list[0].columns1}, #{list[0].columns2}, #{list[0].columns3}),(#{list[1].columns1}, #{list[1].columns2}, #{list[1].columns3})
            sql.append(" values ");
            {

                for (int i = 0; i < list.size(); i++) {
                    if (i > 0) {
                        sql.append(", ");
                    }
                    sql.append(OPEN);
                    int j = 0;
                    for (String fieldName : columns.keySet()) {
                    	if("id".equalsIgnoreCase(fieldName)) {
                    		continue;
                    	}
                        BaseReflectorColumn column = columns.get(fieldName);

                        if (j > 0) {
                            sql.append(", ");
                        }

                        sql.append("#{");
                        sql.append(INSERT_BATCH_PARAM_LIST);
                        sql.append("[");
                        sql.append(i);
                        sql.append("]");
                        sql.append(".");
                        sql.append(column.getEntityFieldName());
                        sql.append("}");

                        j++;
                    }
                    sql.append(CLOSE);
                }

            }

            return sql.toString();
        }


        public String update(final T obj) {
            return new SQL() {{
                logger.info("通用update方法--------start");
                if (obj == null) {
                    logger.warn("update 对象为空");
                    throw new RuntimeException("update 对象为空");
                }
                logger.info("update 对象：{}", obj.toString());

                BaseReflector reflector = BaseReflectorFactory.getReflector(obj.getClass());

                UPDATE(reflector.getTableName());

                //获取字段
                Map<String, BaseReflectorColumn> columns = reflector.getTableColumnExcludeId();

                for (String fieldName : columns.keySet()) {
                    BaseReflectorColumn column = columns.get(fieldName);

                    StringBuilder columnStr = new StringBuilder();

                    columnStr.append(column.getUnderLineName());
                    columnStr.append("=");

                    columnStr.append("#{");
                    columnStr.append(column.getEntityFieldName());
                    columnStr.append("}");

                    SET(columnStr.toString());
                }

                WHERE(idSQL(reflector));

            }}.toString();
        }

        public String updateWithoutNull(final T obj) {
            return new SQL() {{
                logger.info("通用updateWithoutNull方法--------start");
                if (obj == null) {
                    logger.warn("updateWithoutNull 对象为空");
                    throw new RuntimeException("update 对象为空");
                }
                logger.info("updateWithoutNull 对象：{}", obj.toString());

                BaseReflector reflector = BaseReflectorFactory.getReflector(obj.getClass());

                UPDATE(reflector.getTableName());

                //获取字段
                Map<String, BaseReflectorColumn> columns = reflector.getTableColumnExcludeId();

                for (String fieldName : columns.keySet()) {
                    BaseReflectorColumn column = columns.get(fieldName);
                    Object val = column.getValue(obj);
                    if (val != null) {
                        StringBuilder columnStr = new StringBuilder();
                        columnStr.append("`"+column.getUnderLineName()+"`");
                        columnStr.append("=");
                        columnStr.append("#{");
                        columnStr.append(column.getEntityFieldName());
                        columnStr.append("}");
                        SET(columnStr.toString());
                    }
                }

                WHERE(idSQL(reflector));

            }}.toString();
        }


        public String delete(final Class<T> cls, final K id) {
            String sql = new SQL() {{
                logger.info("通用delete方法--------start");
                if (id == null) {
                    logger.warn("delete id为空");
                    throw new RuntimeException("delete id为空");
                }
                logger.info("delete id：{}", id);

                BaseReflector reflector = BaseReflectorFactory.getReflector(cls);

                DELETE_FROM(reflector.getTableName());

                WHERE(idSQL(reflector, DELETE_PARAM_ID));

            }}.toString();
            logger.info(sql);
            return sql;
        }

        public String deleteWithField(final Class<T> cls, final Map<String, Object> param) {
            return new SQL() {{
                logger.info("通用deleteWithField方法--------start");
                if (param == null || param.isEmpty()) {
                    logger.warn("deleteWithField param为空");
                    throw new RuntimeException("deleteWithField param为空");
                }

                BaseReflector reflector = BaseReflectorFactory.getReflector(cls);
                DELETE_FROM(reflector.getTableName());
                int i = 0;
                for (String fieldName : param.keySet()) {
                    if (i > 0) {
                        AND();
                    }
                    BaseReflectorColumn column = reflector.getAllTableColumn().get(fieldName);
                    StringBuilder columnStr = new StringBuilder();
                    columnStr.append(column.getUnderLineName());
                    columnStr.append("=");
                    columnStr.append("#{");
                    columnStr.append(DELETE_WITH_FIELD_PARAM_PARAM);
                    columnStr.append(".");
                    columnStr.append(column.getEntityFieldName());
                    columnStr.append("}");
                    WHERE(columnStr.toString());

                    i++;
                }

            }}.toString();
        }


        public String get(final Class<T> cls, final K id) {
            return new SQL() {
                {
                    logger.info("通用get方法--------start");
                    if (id == null) {
                        logger.warn("get id为空");
                        throw new RuntimeException("get id为空");
                    }
                    logger.info("get id：{}");

                    BaseReflector reflector = BaseReflectorFactory.getReflector(cls);

                    //获取字段
                    Map<String, BaseReflectorColumn> columns = reflector.getAllTableColumn();

                    for (String fieldName : columns.keySet()) {
                        BaseReflectorColumn column = columns.get(fieldName);
                        SELECT(column.getSelectAliasName());
                    }
                    FROM(reflector.getTableName());

                    WHERE(idSQL(reflector, GET_PARAM_ID));
                }
            }.toString();
        }

        public String list(final Class<T> cls, final Map<String, Object> param) {
            return new SQL() {
                {
                    logger.info("通用list方法--------start");
                    if (param == null) {
                        logger.warn("list param为空");
                        throw new RuntimeException("list param为空");
                    }

                    BaseReflector reflector = BaseReflectorFactory.getReflector(cls);

                    //获取字段
                    Map<String, BaseReflectorColumn> columns = reflector.getAllTableColumn();

                    for (String fieldName : columns.keySet()) {
                        BaseReflectorColumn column = columns.get(fieldName);
                        SELECT(column.getSelectAliasName());
                    }
                    FROM(reflector.getTableName());

                    int i = 0;
                    for (String fieldName : param.keySet()) {
                        if (i > 0) {
                            AND();
                        }
                        BaseReflectorColumn column = reflector.getAllTableColumn().get(fieldName);
                        StringBuilder columnStr = new StringBuilder();
                        columnStr.append(column.getUnderLineName());
                        columnStr.append("=");
                        columnStr.append("#{");
                        columnStr.append(LIST_PARAM_PARAM);
                        columnStr.append(".");
                        columnStr.append(column.getEntityFieldName());
                        columnStr.append("}");
                        WHERE(columnStr.toString());

                        i++;
                    }
                }
            }.toString();
        }


        public String disabled(final Class<T> cls, final K id, @Param(DISABLED_PARAM_DISABLED) Integer flag) {
            return new SQL() {
                {
                    logger.info("通用get方法--------start");
                    if (id == null) {
                        logger.warn("get id为空");
                        throw new RuntimeException("get id为空");
                    }
                    logger.info("get id：{}");

                    BaseReflector reflector = BaseReflectorFactory.getReflector(cls);

                    UPDATE(reflector.getTableName());

                    SET("disabled=#{" + DISABLED_PARAM_DISABLED + "}");

                    WHERE(idSQL(reflector));
                }
            }.toString();
        }

        /**
         * @param cls
         * @param key
         * @return
         * @Description: 根据关键字判断该数据是否存在
         * @author liux01
         * @date 2016年12月24日
         */
        public String exists(final Class cls, final String key, @Param(GET_FORM_NO) String value) {
            return new SQL() {
                {
                    logger.info("通用exists方法--------start");
                    if (key == null) {
                        logger.warn("get key为空");
                        throw new RuntimeException("get key为空");
                    }
                    logger.info("get key：{}");
                    BaseReflector reflector = BaseReflectorFactory.getReflector(cls);
                    //获取字段
                    Map<String, BaseReflectorColumn> columns = reflector.getAllTableColumn();
                    BaseReflectorColumn column = columns.get(key);
                    SELECT(COUNT);
                    FROM(reflector.getTableName());
                    WHERE(fieldNameSQL(column, GET_FORM_NO));
                }
            }.toString();
        }

        /**
         * @param cls
         * @param key
         * @return
         * @Description: 获取最大值字段信息
         * @author liux01
         * @date 2016年12月24日
         */
        public String getMax(final Class cls, final K key, final Map<String, Object> param) {
            return new SQL() {
                {
                    logger.info("通用max方法--------start");
                    if (key == null) {
                        logger.warn("get key为空");
                        throw new RuntimeException("get key为空");
                    }
                    logger.info("get key：{}");
                    BaseReflector reflector = BaseReflectorFactory.getReflector(cls);
                    //获取字段
                    Map<String, BaseReflectorColumn> columns = reflector.getAllTableColumn();
                    BaseReflectorColumn column = columns.get(key);
                    SELECT(MAX + OPEN + column.getUnderLineName() + CLOSE);
                    FROM(reflector.getTableName());
                    int i = 0;
                    for (String fieldName : param.keySet()) {
                        if (i > 0) {
                            AND();
                        }
                        BaseReflectorColumn column2 = reflector.getAllTableColumn().get(fieldName);
                        StringBuilder columnStr = new StringBuilder();
                        if ("createTime".equals(fieldName)) {
                            columnStr.append("date_format(" + column2.getUnderLineName() + ",'%Y-%m-%d')");
                        } else {
                            columnStr.append(column2.getUnderLineName());
                        }
                        columnStr.append("=");
                        columnStr.append("#{");
                        columnStr.append(DELETE_WITH_FIELD_PARAM_PARAM);
                        columnStr.append(".");
                        columnStr.append(column2.getEntityFieldName());
                        columnStr.append("}");
                        WHERE(columnStr.toString());
                        i++;
                    }
                    /*WHERE(fieldNameSQL(branchColunm, GET_BRANCH_ID));*/
                }
            }.toString();
        }


    }

}
