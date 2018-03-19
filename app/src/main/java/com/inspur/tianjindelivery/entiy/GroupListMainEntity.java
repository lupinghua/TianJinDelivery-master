package com.inspur.tianjindelivery.entiy;

import java.util.List;

/**
 * 站点列表实体类
 * Created by ${lupinghua} on 2018/3/19.
 */

public class GroupListMainEntity {

    /**
     * result : true
     * siteInfoList : [{"display_value":2059051292,"display_label":"INT_ID","display_flag":"0","display_field":"INT_ID"},{"display_value":"澜沧县东回机场临时站2","display_label":"站点地址","display_flag":"1","display_field":"ZH_LABEL"},{"display_value":"-_-_58797","display_label":"站点编号","display_flag":"1","display_field":"SITE_NO"},{"display_value":"未交割","display_label":"交割状态","display_flag":"1","display_field":"DELIVERY_STATUS"},{"display_value":2059048434,"display_label":"INT_ID","display_flag":"0","display_field":"INT_ID"},{"display_value":"澜沧县东回机场临时站1","display_label":"站点地址","display_flag":"1","display_field":"ZH_LABEL"},{"display_value":"-_-_58796","display_label":"站点编号","display_flag":"1","display_field":"SITE_NO"},{"display_value":"未交割","display_label":"交割状态","display_flag":"1","display_field":"DELIVERY_STATUS"},{"display_value":1820954412,"display_label":"INT_ID","display_flag":"0","display_field":"INT_ID"},{"display_value":"孟连县寡妇村","display_label":"站点地址","display_flag":"1","display_field":"ZH_LABEL"},{"display_value":"PUE2416_-_867198","display_label":"站点编号","display_flag":"1","display_field":"SITE_NO"},{"display_value":"未交割","display_label":"交割状态","display_flag":"1","display_field":"DELIVERY_STATUS"},{"display_value":1820954409,"display_label":"INT_ID","display_flag":"0","display_field":"INT_ID"},{"display_value":"西盟县缅甸营盘那盼","display_label":"站点地址","display_flag":"1","display_field":"ZH_LABEL"},{"display_value":"PUE2353_-_866309","display_label":"站点编号","display_flag":"1","display_field":"SITE_NO"},{"display_value":"未交割","display_label":"交割状态","display_flag":"1","display_field":"DELIVERY_STATUS"},{"display_value":1820954406,"display_label":"INT_ID","display_flag":"0","display_field":"INT_ID"},{"display_value":"孟连县松普发电厂","display_label":"站点地址","display_flag":"1","display_field":"ZH_LABEL"},{"display_value":"PUE2415_-_866318","display_label":"站点编号","display_flag":"1","display_field":"SITE_NO"},{"display_value":"未交割","display_label":"交割状态","display_flag":"1","display_field":"DELIVERY_STATUS"},{"display_value":1820954405,"display_label":"INT_ID","display_flag":"0","display_field":"INT_ID"},{"display_value":"孟连县勐平团结村2","display_label":"站点地址","display_flag":"1","display_field":"ZH_LABEL"},{"display_value":"PUE2414_-_866317","display_label":"站点编号","display_flag":"1","display_field":"SITE_NO"},{"display_value":"未交割","display_label":"交割状态","display_flag":"1","display_field":"DELIVERY_STATUS"},{"display_value":1820954404,"display_label":"INT_ID","display_flag":"0","display_field":"INT_ID"},{"display_value":"孟连县勐平莫帕","display_label":"站点地址","display_flag":"1","display_field":"ZH_LABEL"},{"display_value":"PUE2413_-_866316","display_label":"站点编号","display_flag":"1","display_field":"SITE_NO"},{"display_value":"未交割","display_label":"交割状态","display_flag":"1","display_field":"DELIVERY_STATUS"},{"display_value":1820954403,"display_label":"INT_ID","display_flag":"0","display_field":"INT_ID"},{"display_value":"孟连县南爬","display_label":"站点地址","display_flag":"1","display_field":"ZH_LABEL"},{"display_value":"PUE2412_-_-","display_label":"站点编号","display_flag":"1","display_field":"SITE_NO"},{"display_value":"未交割","display_label":"交割状态","display_flag":"1","display_field":"DELIVERY_STATUS"},{"display_value":1820954402,"display_label":"INT_ID","display_flag":"0","display_field":"INT_ID"},{"display_value":"孟连县造纸厂","display_label":"站点地址","display_flag":"1","display_field":"ZH_LABEL"},{"display_value":"PUE2409_-_866312","display_label":"站点编号","display_flag":"1","display_field":"SITE_NO"},{"display_value":"未交割","display_label":"交割状态","display_flag":"1","display_field":"DELIVERY_STATUS"}]
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
         * display_value : 2059051292
         * display_label : INT_ID
         * display_flag : 0
         * display_field : INT_ID
         */

        private String display_value;
        private String display_label;
        private String display_flag;
        private String display_field;

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

        public String getDisplay_flag() {
            return display_flag;
        }

        public void setDisplay_flag(String display_flag) {
            this.display_flag = display_flag;
        }

        public String getDisplay_field() {
            return display_field;
        }

        public void setDisplay_field(String display_field) {
            this.display_field = display_field;
        }

        @Override
        public String toString() {
            return "SiteInfoListBean{" +
                    "display_value=" + display_value +
                    ", display_label='" + display_label + '\'' +
                    ", display_flag='" + display_flag + '\'' +
                    ", display_field='" + display_field + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GroupListMainEntity{" +
                "result=" + result +
                ", siteInfoList=" + siteInfoList +
                '}';
    }
}
