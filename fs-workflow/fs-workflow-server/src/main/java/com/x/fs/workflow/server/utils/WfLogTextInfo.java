package com.x.fs.workflow.server.utils;

import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author x
 */
public class WfLogTextInfo {

    private final String wfLogText;

    public class FirstResult {
        String msgCode;
        String msgInfo;

        public String getMsgCode() {
            return msgCode;
        }

        public String getMsgInfo() {
            return msgInfo;
        }
    }

    private FirstResult firstResult = new FirstResult();
    private boolean isFirstResult;

    private Map<String, Map<String, String>> secondResult;
    private List<String> rows;
    private Integer currentRow;

    public WfLogTextInfo(String wfLogText) {
        this.wfLogText = wfLogText;
        this.secondResult = new HashMap<>();
        this.rows = new ArrayList<>();
        this.isFirstResult = false;
    }

    /**
     * 判断当前日志格式是第一结果集还是第二结果集
     *
     * @return true 为第一结果集，其它为第二结果集
     */
    public boolean isFirstResult() {
        return isFirstResult;
    }

    public FirstResult getFirstResult() {
        return firstResult;
    }

    public Integer open() {
        this.currentRow = 0;
        this.rows.clear();
        try {
            JsonReader reader = new JsonReader(new StringReader(this.wfLogText));

            reader.beginObject();
            String tagName = reader.nextName();
            if ("FIRST_RESULT".equals(tagName)) {
                reader.beginObject();
                while (reader.hasNext()) {
                    tagName = reader.nextName();
                    if ("MSG_CODE".equals(tagName)) {
                        this.firstResult.msgCode = reader.nextString();
                        continue;
                    }
                    if ("MSG_INFO".equals(tagName)) {
                        this.firstResult.msgInfo = reader.nextString();
                    }
                }
                reader.endObject();
                this.isFirstResult = true;
            }

            if ("SECOND_RESULT".equals(tagName)) {
                this.parseSecondResult(reader);
            }
        } catch (Exception e) {
            return -1;
        }
        return 0;
    }

    public boolean fetchRow() {
        if (this.rows.size() <= 0) {
            return false;
        }
        if (this.currentRow >= this.rows.size()) {
            return false;
        }
        this.currentRow++;
        return true;
    }

    public String getColValue(String str) {
        try {
            String key = this.rows.get(this.currentRow - 1);
            Map<String, String> item = this.secondResult.get(key);
            if (item != null) {
                return item.get(str);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    private void parseSecondResult(JsonReader reader) {
        try {
            reader.beginObject();
            while (reader.hasNext()) {
                String tagName = reader.nextName();
                Map<String, String> subItem = this.secondResult.get(tagName);
                if (subItem == null) {
                    subItem = new HashMap<>();
                    this.secondResult.put(tagName, subItem);
                    this.rows.add(tagName);
                }
                reader.beginObject();
                while (reader.hasNext()) {
                    tagName = reader.nextName();
                    String value = reader.nextString();
                    subItem.put(tagName, value);
                }
                reader.endObject();
            }
            reader.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
