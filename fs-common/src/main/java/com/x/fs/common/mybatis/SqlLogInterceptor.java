package com.x.fs.common.mybatis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.x.fs.common.constant.DefaultConstant;
import com.x.fs.common.utils.MilliSecondUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;

/**
 * sql执行日志
 *
 * @author x
 */

@Component
@Intercepts(value = {
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})})
public class SqlLogInterceptor implements Interceptor, Closeable {
    public static Set<String> logFileSet = new HashSet<>();
    public static List<BufferedWriter> outInfoList = new ArrayList<>();
    public static Map<String, BufferedWriter> mapBuffWriter = new HashMap<>();
    public static Integer showResultNum = 10;
    public static Set<String> filterIdSet = new HashSet<>();
    public static SimpleDateFormat DATE_SDF = new SimpleDateFormat(DefaultConstant.DATE_FORMAT);
    public static SimpleDateFormat SIMPLE_DATE_SDF = new SimpleDateFormat(DefaultConstant.SIMPLE_DATE_FORMAT);
    public static ObjectMapper objectMapper = new ObjectMapper();
    /**
     * sql日志开关  默认打开
     */
    public static boolean LOG_OPEN_FLAG = true;

    /**
     * 日志文件后缀的数字，表示有几个文件了
     */
    public static int LOG_COUNT = 1;

    public static int defaultSize = DefaultConstant.DEFAULT_FILE_SIZE;

    /**
     * 数据库当前时间
     */
    public int nowDate = 0;

    /**
     * @param invocation
     * @return
     * @throws Throwable
     */

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (!LOG_OPEN_FLAG) {
            return invocation.proceed();
        }
        long startTime = MilliSecondUtils.currentTimeMillis();
        Object result = null;
        try {
            result = invocation.proceed();
        } finally {
            try {
                long useTime = MilliSecondUtils.currentTimeMillis() - startTime;
                MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
                Configuration configuration = mappedStatement.getConfiguration();
                // 获取参数对象
                Object parameter = null;
                if (invocation.getArgs().length > 1) {
                    parameter = invocation.getArgs()[1];
                }
                // 取sql信息
                String sqlId = mappedStatement.getId();
                BoundSql boundSql = mappedStatement.getBoundSql(parameter);
                // 拼接实际执行的sql
                String sql = dealSql(configuration, boundSql);
                // 判断是否需要记录日志
                boolean isSkip = false;
                for (String filterId : filterIdSet) {
                    if (!StringUtils.isEmpty(filterId) && sqlId.contains(filterId)) {
                        isSkip = true;
                    }
                }
                //记录日志
                if (!isSkip) {
                    Iterator<Map.Entry<String, BufferedWriter>> it = mapBuffWriter.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, BufferedWriter> s = it.next();
                        BufferedWriter bufferedWriter = s.getValue();
                        String filePathName = s.getKey();
                        File file = new File(filePathName);
                        if (file.length() > defaultSize) {
                            //如果该文件的大小，已经大于指定大小，重新命名该文件
                            LOG_COUNT = LOG_COUNT + 1;
                            String newFileName = filePathName.substring(0, filePathName.lastIndexOf("-")) + "-" + LOG_COUNT + ".log";
                            //关闭当前工作流
                            bufferedWriter.close();
                            File newFile = new File(newFileName);
                            if (newFile.exists()) {
                                newFile.delete();
                            }
                            //更改文件名称，xxx-date-c.log   c 为1的文件，都是在往里面写内容的，数字越大，记录的信息越早
                            file.renameTo(newFile);
                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePathName), StandardCharsets.UTF_8));
                            writeLog(bw, sqlId, String.valueOf(useTime), showResultNum > 0 ? sql + "\n" + showResult(result) : sql);
                            boolean dateFlag = dealWithDate();
                            if (dateFlag) {
                                //新的一天需要新建文件，修改名称，换新的流
                                int newDate =  Integer.parseInt(SIMPLE_DATE_SDF.format(new Date()));
                                String temp = filePathName.substring(0, filePathName.lastIndexOf("-"));
                                String newFilePath = temp.substring(0, temp.lastIndexOf("-")) + "-" + newDate + "-" + LOG_COUNT + ".log";
                                BufferedWriter newBw = createFileAndBw(newFilePath, bw);
                                it.remove();
                                mapBuffWriter.put(newFilePath, newBw);
                            } else {
                                mapBuffWriter.put(filePathName, bw);
                            }
                        } else {
                            writeLog(bufferedWriter, sqlId, String.valueOf(useTime), showResultNum > 0 ? sql + "\n" + showResult(result) : sql);
                        }
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
                for (Map.Entry<String, BufferedWriter> s : mapBuffWriter.entrySet()) {
                    BufferedWriter bufferedWriter = s.getValue();
                    writeLog(bufferedWriter, "ERR", "ERR", "\n" + exception.getMessage() + "\n");
                }
            }
        }
        return result;
    }

    private BufferedWriter createFileAndBw(String filePath, BufferedWriter oldBw) throws IOException {
        oldBw.close();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8));
        return bw;
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        String flag = properties.getProperty("logOpenFlag");
        if (Objects.equals(flag, "true")) {
            SqlLogInterceptor.LOG_OPEN_FLAG = Boolean.TRUE;
        } else {
            SqlLogInterceptor.LOG_OPEN_FLAG = Boolean.FALSE;
            return;
        }
        // 日志文件路径
        String logFilePath = properties.getProperty("logFilePath");
        String[] logFilePaths = logFilePath.split(",");
        for (String filepath : logFilePaths) {
            if (filepath.trim().length() > 0) {
                SqlLogInterceptor.logFileSet.add(filepath.trim());
            }
        }
        //文件名称
        String fileName = properties.getProperty("fileName");
        if (!StringUtils.isEmpty(fileName)) {
            fileName = DefaultConstant.DEFAULT_FILE_NAME;
        }
        //文件大小 若有值，这取该值，若没有，取默认值500
        String fileSize = properties.getProperty("fileSize");
        if (!StringUtils.isEmpty(fileSize)) {
            defaultSize = Integer.parseInt(Math.round(Float.parseFloat(fileSize) * 1024) + "");
        }

        //LOG_COUNT  还需要读取当前文件，单日已经写了几个文件，并判断大小，是该基础加1，还是该文件


        //当前数据库时间
        nowDate = Integer.parseInt(SIMPLE_DATE_SDF.format(new Date()));
        // 输出流
        for (String filePath : logFileSet) {
            try {
                //拼接文件路径
                String path = filePath + "\\" + fileName + "-" + nowDate + "-" + LOG_COUNT + ".log";
                File file = new File(path);
                if (!file.isFile() && !file.exists()) {
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8));
                    mapBuffWriter.put(path, bufferedWriter);
                } else {
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, true));
                    mapBuffWriter.put(path, bufferedWriter);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 最大展示结果行
        String showResultNum = properties.getProperty("showResultNum");
        try {
            int num = Integer.parseInt(showResultNum);
            SqlLogInterceptor.showResultNum = num > 0 ? num : 10;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        // 过滤掉的方法
        String filterId = properties.getProperty("filterMethod");
        String[] split = filterId.split(",");
        for (int i = 0; i < split.length; i++) {
            SqlLogInterceptor.filterIdSet.add(split[i].trim());
        }
    }

    /**
     * 拼接执行的sql
     *
     * @param configuration
     * @param boundSql
     * @return
     */
    public static String dealSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql();
        if (!CollectionUtils.isEmpty(parameterMappings) && parameterObject != null) {
            // 获取类型处理器注册器
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            // 单个原始类型参数
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceAll("\\?",
                        Matcher.quoteReplacement(getParameterValue(parameterObject)));
            } else {
                // 创建参数的MetaObject对象
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                // 遍历所有映射
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object object = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(object)));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        // 该分支是动态sql
                        Object object = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(object)));
                    } else {
                        // 打印出缺失，提醒该参数缺失并防止错位
                        sql = sql.replaceFirst("\\?", "数据缺失");
                    }
                }
            }
        }
        return sql;
    }

    @Override
    public void close() {
        for (Map.Entry<String, BufferedWriter> s : mapBuffWriter.entrySet()) {
            BufferedWriter writer = s.getValue();
            if (writer != null) {
                try {
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }


    /**
     * 打印结果
     *
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    private static String showResult(Object object) throws JsonProcessingException {
        if (object instanceof List) {
            List list = (List) object;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("result: " + list.size() + " row\n");
            for (int i = 0; i < Math.min(list.size(), showResultNum); i++) {
                stringBuilder.append(objectMapper.writeValueAsString(list.get(i)));
                stringBuilder.append("\n");
            }
            stringBuilder.append("\n");
            return stringBuilder.toString();
        } else {
            // 否则直接反序列化返回
            return "result: " + objectMapper.writeValueAsString(object) + "\n\n";
        }
    }

    /**
     * 记录日志到文件
     *
     * @param sqlId SQL语句ID
     * @param time  使用的时间
     * @param text  执行SQL/错误信息
     */
    public static void writeLog(BufferedWriter writer, String sqlId, String time, String text) {
        try {
            writer.write("[" + DATE_SDF.format(new Date()) + "][" + Thread.currentThread().getName() + "][" + sqlId + "][" + time + "ms]\n" + delCRLF(text.trim()) + "\n\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理值
     *
     * @param object
     * @return
     */
    private static String getParameterValue(Object object) {
        String value = null;
        if (object instanceof String) {
            value = "'" + object + "'";
        } else if (object instanceof Date) {
            value = "'" + SIMPLE_DATE_SDF.format(new Date()) + "'";
        } else {
            if (object != null) {
                value = object.toString();
            } else {
                value = "NULL";
            }
        }
        return value;
    }

    /**
     * 删除多余的空行
     *
     * @param input
     * @return
     */
    private static String delCRLF(String input) {
        if (input != null) {
            return input.replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1").replaceAll("^((\r\n)|\n)", "");
        } else {
            return null;
        }
    }

    /**
     * 判断是不是当天，非当天，需要新建文件
     */
    private boolean dealWithDate() {
        int newDate = Integer.parseInt(SIMPLE_DATE_SDF.format(new Date()));
        if (newDate != nowDate) {
            LOG_COUNT = 1;
            nowDate = newDate;
            return true;
        }
        return false;
    }
}
