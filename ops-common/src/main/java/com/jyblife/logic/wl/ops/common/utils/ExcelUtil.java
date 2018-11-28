package com.jyblife.logic.wl.ops.common.utils;

import com.jyblife.logic.wl.ops.common.excel.DateFormat;
import com.jyblife.logic.wl.ops.common.excel.ExcelAttribute;
import com.jyblife.logic.wl.ops.common.excel.Invoker;
import com.jyblife.logic.wl.ops.common.excel.InvokerHolder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelUtil<T> implements Serializable {

	//jar包服务发布情况下不要使用-存在权限目录问题
    private static String TEMPLATES_PATH = ExcelUtil.class.getClassLoader().getResource("").getPath();

    protected final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    private static final long serialVersionUID = 551970754610248636L;

    private Class<T> clazz;

    public ExcelUtil(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * 将excel表单数据源的数据导入到list
     *
     * @param sheetName 工作表的名称
     * @param input     java输入流
     * @param isExcel2007     是否2007版本
     */
    public List<T> getExcelToList(String sheetName, InputStream input, boolean isExcel2007) {
        List<T> list = new ArrayList<T>();
        try {
            Workbook book = isExcel2007 ? new XSSFWorkbook(input) : new HSSFWorkbook(input);
            Sheet sheet = null;
            // 如果指定sheet名,则取指定sheet中的内容.
            if (StringUtils.isNotBlank(sheetName)) {
            	sheet = book.getSheet(sheetName);
            }
            // 如果传入的sheet名不存在则默认指向第1个sheet.
            if (sheet == null) {
                sheet = book.getSheetAt(0);
            }
            // 得到数据的行数
            int rows = sheet.getLastRowNum();

            // 有数据时才处理
            if (rows > 0) {
                // 得到类的所有field
                Field[] allFields = clazz.getDeclaredFields();
                // 定义一个map用于存放列的序号和field
                Map<Integer, Field> fieldsMap = new HashMap<Integer, Field>();
                for (int i = 0, index = 0; i < allFields.length; i++) {
                    Field field = allFields[i];

                    ExcelAttribute attr = field.getAnnotation(ExcelAttribute.class);
                    int col = i;
                    // 根据指定的顺序获得列号
                    if (StringUtils.isNotBlank(attr.column())) {
                        col = getExcelCol(attr.column());
                    }


                    // 将有注解的field存放到map中
                    if (field.isAnnotationPresent(ExcelAttribute.class)) {
                        // 设置类的私有字段属性可访问
                        field.setAccessible(true);
                        fieldsMap.put(col, field);
                        index++;
                    }
                }
                // 从第2行开始取数据,表头不算一行
                for (int i = 1, len = rows; i <= len; i++) {
                    // 得到一行中的所有单元格对象.
                    Row row = sheet.getRow(i);
                    Iterator<Cell> cells = row.cellIterator();
                    T entity = null;
                    int index = 0;
                    while (cells.hasNext()) {
                        // 单元格中的内容.
                        Cell cell = cells.next();
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String c = cell.getStringCellValue();
                        // 注释防止空值字段出现列匹配错乱
                        /*if (!StringUtils.isNotBlank(c)) {
                            continue;
                        }*/

                        // 如果不存在实例则新建
                        entity = (entity == null ? clazz.newInstance() : entity);
                        // 从map中得到对应列的field
                        Field field = fieldsMap.get(index);
                        if (field == null) {
                            continue;
                        }
                        // 取得类型,并根据对象类型设置值.
                        Class<?> fieldType = field.getType();
                        if (fieldType == null) {
                            continue;
                        }
                        if (String.class == fieldType) {
                            field.set(entity, String.valueOf(c));
                        } else if (BigDecimal.class == fieldType) {
                            c = c.indexOf("%") != -1 ? c.replace("%", "") : c;
                            if(NumberUtils.isNumber(c)) {
                                field.set(entity, BigDecimal.valueOf(Double.valueOf(c)));
                            }else{
                                field.set(entity,null);
                            }
                        } else if (Date.class == fieldType) {
                            try {
                                field.set(entity, DateUtils.parseDate(c));
                            }catch (Exception e){
                                logger.error("日期格式化异常{}",e);
                                field.set(entity,null);
                            }
                        } else if ((Integer.TYPE == fieldType) || (Integer.class == fieldType)) {
                            if(NumberUtils.isDigits(c)) {
                                field.set(entity, Integer.parseInt(c));
                            }else{
                                field.set(entity,null);
                            }
                        } else if ((Long.TYPE == fieldType) || (Long.class == fieldType)) {
                            if(NumberUtils.isDigits(c)) {
                                field.set(entity, Long.valueOf(c));
                            }else{
                                field.set(entity,null);
                            }
                        } else if ((Float.TYPE == fieldType) || (Float.class == fieldType)) {
                            if(NumberUtils.isNumber(c)) {
                                field.set(entity, Float.valueOf(c));
                            }else{
                                field.set(entity,null);
                            }
                        } else if ((Short.TYPE == fieldType) || (Short.class == fieldType)) {
                            if(NumberUtils.isDigits(c)) {
                                field.set(entity, Short.valueOf(c));
                            }else{
                                field.set(entity,null);
                            }
                        } else if ((Double.TYPE == fieldType) || (Double.class == fieldType)) {
                            if(NumberUtils.isNumber(c)) {
                                field.set(entity, Double.valueOf(c));
                            }else{
                                field.set(entity,null);
                            }
                        } else if (Character.TYPE == fieldType) {
                            if ((c != null) && (c.length() > 0)) {
                                field.set(entity, Character.valueOf(c.charAt(0)));
                            }
                        }
                        index++;
                    }
                    if (entity != null) {
                        list.add(entity);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("将excel表单数据源的数据导入到list异常!", e);
        }
        return list;
    }

    /**
     * 将list数据源的数据导入到excel表单
     *
     * @param list      数据源
     * @param sheetName 工作表的名称
     * @param output    java输出流
     */
    public boolean getListToExcel(List<T> list, String sheetName, OutputStream output) {
        try {
            // excel中每个sheet中最多有65536行
            int sheetSize = 65536;
            // 得到所有定义字段
            Field[] allFields = clazz.getDeclaredFields();
            List<Field> fields = new ArrayList<Field>();
            // 得到所有field并存放到一个list中
            for (Field field : allFields) {
                if (field.isAnnotationPresent(ExcelAttribute.class)) {
                    fields.add(field);
                }
            }
            // 产生工作薄对象
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 取出一共有多少个sheet
            int listSize = 0;
            if (list != null && list.size() >= 0) {
                listSize = list.size();
            }
            double sheetNo = Math.ceil(listSize / sheetSize);
            for (int index = 0; index <= sheetNo; index++) {
                // 产生工作表对象
                HSSFSheet sheet = workbook.createSheet();
                // 设置工作表的名称.
                workbook.setSheetName(index, sheetName + index);
                HSSFRow row;
                HSSFCell cell;// 产生单元格
                row = sheet.createRow(0);// 产生一行
                /* *********普通列样式********* */
                HSSFFont font = workbook.createFont();
                HSSFCellStyle cellStyle = workbook.createCellStyle();
                font.setFontName("Arail narrow"); // 字体
                font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体宽度
                /* *********标红列样式********* */
                HSSFFont newFont = workbook.createFont();
                HSSFCellStyle newCellStyle = workbook.createCellStyle();
                newFont.setFontName("Arail narrow"); // 字体
                newFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体宽度
                /* *************创建列头名称*************** */
                for (int i = 0; i < fields.size(); i++) {
                    Field field = fields.get(i);
                    ExcelAttribute attr = field.getAnnotation(ExcelAttribute.class);
                    int col = i;
                    // 根据指定的顺序获得列号
                    if (StringUtils.isNotBlank(attr.column())) {
                        col = getExcelCol(attr.column());
                    }
                    // 创建列
                    cell = row.createCell(col);

                    font.setColor(HSSFFont.COLOR_NORMAL); // 字体颜色
                    cellStyle.setFont(font);
                    cell.setCellStyle(cellStyle);

                    sheet.setColumnWidth(i, (int) ((attr.name().getBytes().length <= 4 ? 6 : attr.name().getBytes().length) * 1.5 * 256));
                    // 设置列中写入内容为String类型
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    // 写入列名
                    cell.setCellValue(attr.name());
                    // 如果设置了提示信息则鼠标放上去提示.
                    if (StringUtils.isNotBlank(attr.prompt())) {
                        setHSSFPrompt(sheet, "", attr.prompt(), 1, 10000, col, col);
                    }
                    // 如果设置了combo属性则本列只能选择不能输入
                    if (attr.combo().length > 0) {
                        setHSSFValidation(sheet, attr.combo(), 1, 10000, col, col);
                    } else {
                        if (StringUtils.isNotBlank(attr.comboMethod()) && StringUtils.isNotBlank(attr.comboService())) {
                            Invoker invoker = InvokerHolder.get(attr.comboService(), attr.comboMethod());
                            if (invoker != null) {
                                try {
                                    String[] comboValues = (String[]) invoker.invoke();
                                    if (comboValues != null && comboValues.length > 0) {
                                        setHSSFValidation(sheet, comboValues, 1, 10000, col, col);// 这里默认设了2-101列只能选择不能输入.
                                    }
                                } catch (Throwable e) {


                                }
                            }
                        }
                    }
                }
                /* *************创建内容列*************** */
                font = workbook.createFont();
                cellStyle = workbook.createCellStyle();
                int startNo = index * sheetSize;
                int endNo = Math.min(startNo + sheetSize, listSize);
                // 写入各条记录,每条记录对应excel表中的一行
                for (int i = startNo; i < endNo; i++) {
                    row = sheet.createRow(i + 1 - startNo);
                    T vo = (T) list.get(i); // 得到导出对象.
                    for (int j = 0; j < fields.size(); j++) {
                        // 获得field
                        Field field = fields.get(j);
                        // 设置实体类私有属性可访问
                        field.setAccessible(true);
                        ExcelAttribute attr = field.getAnnotation(ExcelAttribute.class);
                        int col = j;
                        // 根据指定的顺序获得列号
                        if (StringUtils.isNotBlank(attr.column())) {
                            col = getExcelCol(attr.column());
                        }
                        // 根据ExcelVOAttribute中设置情况决定是否导出,有些情况需要保持为空,希望用户填写这一列.
                        if (attr.isExport()) {
                            // 创建cell
                            cell = row.createCell(col);
                            font.setColor(HSSFFont.COLOR_NORMAL); // 字体颜色
                            cellStyle.setFont(font);
                            cell.setCellStyle(cellStyle);
                            // 如果数据存在就填入,不存在填入空格
                            Class<?> classType = (Class<?>) field.getType();
                            String value = null;
                            if (field.get(vo) != null && classType.isAssignableFrom(Date.class)) {

                                DateFormat dateFormat = field.getAnnotation(DateFormat.class);
                                String format = "yyyy-MM-dd HH:mm:ss";
                                if (dateFormat != null) {
                                    format = dateFormat.format();
                                }
                                SimpleDateFormat sdf = new SimpleDateFormat(format);
                                value = sdf.format((Date) field.get(vo));
                            }
                            cell.setCellValue(field.get(vo) == null ? "" : value == null ? String.valueOf(field.get(vo)) : value);
                        }
                    }
                }
            }
            output.flush();
            workbook.write(output);
            output.close();
            return Boolean.TRUE;
        } catch (Exception e) {
            logger.error("将excel表单数据源的数据导入到list异常!", e);
        }

        return false;
    }

    /**
     * 将EXCEL中A,B,C,D,E列映射成0,1,2,3
     *
     * @param col
     */
    public static int getExcelCol(String col) {
        col = col.toUpperCase();
        // 从-1开始计算,字母重1开始运算。这种总数下来算数正好相同。
        int count = -1;
        char[] cs = col.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            count += (cs[i] - 64) * Math.pow(26, cs.length - 1 - i);
        }
        return count;
    }

    /**
     * 设置单元格上提示
     *
     * @param sheet         要设置的sheet.
     * @param promptTitle   标题
     * @param promptContent 内容
     * @param firstRow      开始行
     * @param endRow        结束行
     * @param firstCol      开始列
     * @param endCol        结束列
     * @return 设置好的sheet.
     */
    public static HSSFSheet setHSSFPrompt(HSSFSheet sheet, String promptTitle, String promptContent, int firstRow, int endRow,
                                          int firstCol, int endCol) {
        // 构造constraint对象
        DVConstraint constraint = DVConstraint.createCustomFormulaConstraint("DD1");
        // 四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        // 数据有效性对象
        HSSFDataValidation data_validation_view = new HSSFDataValidation(regions, constraint);
        data_validation_view.createPromptBox(promptTitle, promptContent);
        sheet.addValidationData(data_validation_view);
        return sheet;
    }

    /**
     * 设置某些列的值只能输入预制的数据,显示下拉框.
     *
     * @param sheet    要设置的sheet.
     * @param textlist 下拉框显示的内容
     * @param firstRow 开始行
     * @param endRow   结束行
     * @param firstCol 开始列
     * @param endCol   结束列
     * @return 设置好的sheet.
     */
    public static HSSFSheet setHSSFValidation(HSSFSheet sheet, String[] textlist, int firstRow, int endRow, int firstCol, int endCol) {
        // 加载下拉列表内容
        DVConstraint constraint = DVConstraint.createExplicitListConstraint(textlist);
        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        // 数据有效性对象
        HSSFDataValidation data_validation_list = new HSSFDataValidation(regions, constraint);
        sheet.addValidationData(data_validation_list);
        return sheet;
    }


    @SuppressWarnings("unused")
	public boolean exportExcel(String exportFileName, List<T> list, HttpServletResponse response) {

        File exportFile = null;
        InputStream inputStream = null;
        ServletOutputStream out = null;
        try {
            //生成文件
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
            String dateStr = sdf.format(new Date());

            String excelName = exportFileName != null ? (exportFileName + dateStr + ".xls") : (dateStr + ".xls");
            exportFile = new File("./temp/" + excelName);
            logger.info("export file path: " + "./temp/" + excelName);
            File parentFile = exportFile.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdir();
            }
            boolean flag = exportFile.createNewFile();

            getListToExcel(list, "sheet1", new FileOutputStream(exportFile));

            if (exportFile != null) {
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/msexcel");
                response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(exportFile.getName(), "UTF-8"));

                inputStream = new FileInputStream(exportFile);
                out = response.getOutputStream();
                byte[] buffer = new byte[2014]; // 缓冲区
                int bytesToRead = -1;
                // 通过循环将读入的Excel文件的内容输出到浏览器中
                while ((bytesToRead = inputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesToRead);
                }
                out.flush();
            } else {
                logger.error("导出文件失败 ");
            }
        } catch (Exception e) {
        	logger.error("导出文件异常 ");
        	logger.error(e.getMessage(), e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
            if (exportFile != null && exportFile.exists()) {
                exportFile.delete(); // 删除临时文件
            }
        }
        return false;
    }

    public boolean exportExcel(List<T> list, HttpServletResponse response) {
        return exportExcel("", list, response);
    }

}
