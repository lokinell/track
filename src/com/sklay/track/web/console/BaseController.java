package com.sklay.track.web.console;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:oxsean@gmail.com">sean yang</a>
 * @version V1.0, 13-4-17
 */
public class BaseController {
    @InitBinder
    protected void initBinder(ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
    }

    protected void success(RedirectAttributes ra) {
        ra.addFlashAttribute("ret", true);
    }

    protected void failed(RedirectAttributes ra, String msg) {
        ra.addFlashAttribute("msg", msg);
        ra.addFlashAttribute("ret", false);
    }
}
