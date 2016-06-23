package com.luosl.akhasi.controller.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2016/5/25.
 */
public class RequestParams {

    public final HttpServletRequest req;
    public final HttpServletResponse resp;

    public RequestParams(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
    }
}
