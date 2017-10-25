package com.supervideo.common;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Gacyou on 2017/10/17.
 */

public class GetTabListResult implements Serializable {

    public int ReturnMsgNo;
    public String ReturnMsg;
    public List<tablist> t;

}
