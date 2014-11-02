package com.dyrent.tag;


import com.dyrent.common.Configuration;
import com.dyrent.enums.PhotoSizeEnum;
import com.dyrent.utils.PhotoUtil;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Author: Jerry.hu
 * Create: Jerry.hu (14-5-9 17:38)
 * Description:
 * To change this template use File | Settings | File Templates.
 */
public class PhotoPathTag extends BodyTagSupport {

    private static final long serialVersionUID = -1393098525987080337L;
    private String url;
    private PhotoSizeEnum size;

    @Override
    public int doEndTag() throws JspException {
        String fullPath;
        if(StringUtils.isBlank(url)){
            fullPath = Configuration.getInstance().getDefault_photo_url();
        }else{
            fullPath = PhotoUtil.getPhotoFullPath(size, url);
        }
        writeToPage(fullPath);
        return EVAL_PAGE;
    }

    /**
     * @param content 写入到页面的内容
     */
    private void writeToPage(String content) {
        JspWriter out = pageContext.getOut();
        if (content != null && !content.isEmpty()) {
            try {
                out.write(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }


    public PhotoSizeEnum getSize() {
        return size;
    }

    public void setSize(PhotoSizeEnum size) {
        this.size = size;
    }
}
