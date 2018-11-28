package com.jyblife.logic.wl.ops.core.mybatis.mapper.ref;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.invoker.Invoker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jyblife.logic.wl.ops.common.annotation.Column;
import com.jyblife.logic.wl.ops.common.annotation.Id;
import com.jyblife.logic.wl.ops.common.annotation.Table;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 实体类映射类
 */
public class BaseReflector extends Reflector {

	protected static final Logger LOG = LoggerFactory.getLogger(Reflector.class);

	protected static final Object[] NO_ARGUMENTS = new Object[0];

	// 实体类
	private Class<?> type;
	// 表字段(key为实体类属性名称)
	private Map<String, BaseReflectorColumn> tableColumn = new LinkedHashMap<String, BaseReflectorColumn>();
	// 表字段（排除Id）(key为实体类属性名称)
	private Map<String, BaseReflectorColumn> tableColumnExcludeId = new LinkedHashMap<String, BaseReflectorColumn>();
	// Id
	private BaseReflectorColumn idColumn = null;
	// 表名
	private String tableName = null;

	/**
	 * 缺省式的构造，限制外部调用
	 * 
	 * @param clazz
	 */
	BaseReflector(Class<?> clazz) {
		super(clazz);
		type = clazz;
		initTableName();
		initTableColumnNames();
	}

	/**
	 * 初始化表名
	 */
	private void initTableName() {
		// 表名
		Table table = type.getAnnotation(Table.class);
		if (table != null) {
			LOG.info("initTableName()");
			if (StringUtils.isNotBlank(table.value())) {
				tableName = table.value();
			} else {
				LOG.warn("无效的表名，表名是空白字符");
			}
		} else {
			tableName = type.getSimpleName();
		}
		LOG.debug("表名:" + tableName);
	}

	/**
	 * 初始化列名
	 */
	public void initTableColumnNames() {
		String[] readablePropertyNames = getGetablePropertyNames();

		for (int i = 0; i < readablePropertyNames.length; i++) {
			Field field = null;

			for (Class<?> currentClass = type; currentClass != null; currentClass = currentClass.getSuperclass()) {
				try {
					field = currentClass.getDeclaredField(readablePropertyNames[i]);
					break;
				} catch (NoSuchFieldException e) {
				} catch (SecurityException e) {
				}
			}

			if (field == null && LOG.isErrorEnabled()) {
				LOG.error("field is null fieldname=" + readablePropertyNames[i]);
			}

			Column column = field.getAnnotation(Column.class);
			String camelCaseFieldName = readablePropertyNames[i];
			String underLineFieldName = null;

			if (column != null) {
				if (StringUtils.isNotBlank(column.value())) {
					underLineFieldName = column.value();
				} else {
					LOG.warn("无效的列名，列名是空白字符");
				}
			}

			BaseReflectorColumn reflectorColumn = new BaseReflectorColumn(camelCaseFieldName, underLineFieldName);
			tableColumn.put(camelCaseFieldName, reflectorColumn);

			Id id = field.getAnnotation(Id.class);
			if (id != null) {
				idColumn = reflectorColumn;
			} else {
				tableColumnExcludeId.put(camelCaseFieldName, reflectorColumn);
			}
		}

	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @return the tableColumnNames
	 */
	public Map<String, BaseReflectorColumn> getAllTableColumn() {
		return tableColumn;
	}

	/**
	 * @return the tableColumnNamesExcludeId
	 */
	public Map<String, BaseReflectorColumn> getTableColumnExcludeId() {
		return tableColumnExcludeId;
	}

	/**
	 * @return the idColumn
	 */
	public BaseReflectorColumn getIdColumn() {
		return idColumn;
	}

	/**
	 * 实体类属性映射对象
	 */
	public class BaseReflectorColumn {

		private BaseReflectorColumn(String entityFieldName, String underLineName) {
			this.entityFieldName = entityFieldName;
			if (underLineName == null) {
				this.underLineName = CamelCaseToUnderscore(entityFieldName);
			} else {
				this.underLineName = underLineName;
			}
			StringBuffer selectAliasNameAppend = new StringBuffer("`" + this.underLineName + "`");
			selectAliasNameAppend.append(" as '");
			selectAliasNameAppend.append(this.entityFieldName);
			selectAliasNameAppend.append("'");
			selectAliasName = selectAliasNameAppend.toString();
		}

		/**
		 * 属性名称(驼峰命名)
		 */
		private String entityFieldName;
		/**
		 * 属性实体类数据库名称(下划线名称)
		 */
		private String underLineName;
		/**
		 * 属性别名，用于查询（例如：goods_sku_name as 'goodsSkuName'）
		 */
		private String selectAliasName;

		/**
		 * @return the entityFieldName
		 */
		public String getEntityFieldName() {
			return entityFieldName;
		}

		/**
		 * @return the underLineName
		 */
		public String getUnderLineName() {
			return underLineName;
		}

		/**
		 * @return the selectAliasName
		 */
		public String getSelectAliasName() {
			return selectAliasName;
		}

		/**
		 * 获当前的属性的值
		 * 
		 * @param obj
		 */
		public Object getValue(Object obj) {
			return getBeanProperty(this.entityFieldName, obj);
		}

		/**
		 * 获当前的属性的值
		 * 
		 * @param propName
		 */
		private Object getBeanProperty(String propName, Object object) {
			Invoker method = getGetInvoker(propName);
			try {
				return method.invoke(object, NO_ARGUMENTS);
			} catch (IllegalAccessException e) {
				LOG.error("", e);
			} catch (InvocationTargetException e) {
				LOG.error("", e);
			}
			return null;
		}

		/**
		 * 驼峰命名法转换成下划线
		 * 
		 * @param name
		 *            驼峰命名
		 * @return
		 */
		private String CamelCaseToUnderscore(String name) {
			final char[] value = name.toCharArray();
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < value.length; i++) {
				char c = value[i];
				if (Character.isUpperCase(c)) {
					builder.append('_');
					builder.append(Character.toLowerCase(c));
				} else {
					builder.append(c);
				}
			}
			return builder.toString();
		}

	}

}
