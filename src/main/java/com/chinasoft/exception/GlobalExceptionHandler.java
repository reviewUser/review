package com.chinasoft.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理器
 * 
 * @author wangye
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseBody
	@ExceptionHandler(CustomException.class)
	public ModelAndView exceptionHandleCustomException(CustomException exception) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("message");
		mv.addObject("message", exception.getMessage());
		mv.addObject("href", exception.getHref());
		return mv;
	}
}
