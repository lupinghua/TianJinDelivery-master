package com.inspur.tianjindelivery.entiy;

import java.util.List;

/**
 * Created by ${lupinghua} on 2018/3/19.
 */

public class GroupListSecondEntity {

    /**
     * result : true
     * siteInfoList : [{"display_value":8582311,"display_label":"INT_ID","display_field":"INT_ID","readonly":"1","display_type":null},{"display_value":"江城县整董曼滩席草塘","display_label":"站点地址","display_field":"ZH_LABEL","readonly":"1","display_type":null},{"display_value":"PUE4142_-_59073","display_label":"站点编号","display_field":"SITE_NO","readonly":"1","display_type":null}]
     */

    private boolean result;
    private List<SiteInfoListBean> siteInfoList;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public List<SiteInfoListBean> getSiteInfoList() {
        return siteInfoList;
    }

    public void setSiteInfoList(List<SiteInfoListBean> siteInfoList) {
        this.siteInfoList = siteInfoList;
    }

    public static class SiteInfoListBean {
        /**
         * display_value : 8582311
         * display_label : INT_ID
         * display_field : INT_ID
         * readonly : 1
         * display_type : null
         */

        private String display_value;
        private String display_label;
        private String display_field;
        private String readonly;
        private String display_type;

        public String getDisplay_value() {
            return display_value;
        }

        public void setDisplay_value(String display_value) {
            this.display_value = display_value;
        }

        public String getDisplay_label() {
            return display_label;
        }

        public void setDisplay_label(String display_label) {
            this.display_label = display_label;
        }

        public String getDisplay_field() {
            return display_field;
        }

        public void setDisplay_field(String display_field) {
            this.display_field = display_field;
        }

        public String getReadonly() {
            return readonly;
        }

        public void setReadonly(String readonly) {
            this.readonly = readonly;
        }

        public String getDisplay_type() {
            return display_type;
        }

        public void setDisplay_type(String display_type) {
            this.display_type = display_type;
        }

        @Override
        public String toString() {
            return "SiteInfoListBean{" +
                    "display_value=" + display_value +
                    ", display_label='" + display_label + '\'' +
                    ", display_field='" + display_field + '\'' +
                    ", readonly='" + readonly + '\'' +
                    ", display_type=" + display_type +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GroupListSecondEntity{" +
                "result=" + result +
                ", siteInfoList=" + siteInfoList +
                '}';
    }
}
