package com.gzzsc.lai.aspect;

import com.gzzsc.lai.annotation.ApiReqParam;
import com.gzzsc.lai.annotation.CheckTokenAndTimeStamp;
import com.gzzsc.lai.api.ApiReq;
import com.gzzsc.lai.api.ApiRsp;
import io.micrometer.core.instrument.util.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @ClassName CheckTokenAndTimeStampAspect
 * @Deacription 验证token和timeStamp
 * @Author laizs
 * @Date 2020/5/6 13:22
 **/
@Aspect
@Component
@Order(-1)//aop切面顺序，可为负数，越小级别越高
public class CheckTokenAndTimeStampAspect {
    private static final Logger logger= LoggerFactory.getLogger(CheckTokenAndTimeStampAspect.class);
    @Pointcut("@annotation(com.gzzsc.lai.annotation.CheckTokenAndTimeStamp)")
    public void annotationPointCut(){
    }
    @Around(value = "annotationPointCut()")
    public Object  doAround(ProceedingJoinPoint joinPoint){
        logger.info("---验证token和timeStamp---");
        MethodSignature methodSignature= (MethodSignature) joinPoint.getSignature();
        //获取参数名
        String[] parameterNames=methodSignature.getParameterNames();
        //获取参数名
        Object[] args=joinPoint.getArgs();
        Method method= methodSignature.getMethod();
        //所有的参数
        Parameter[] parameters=  method.getParameters();
        //遍历所有的参数，判断参数上是否有@MyParameter注解
        for(Parameter p:parameters){
            //
            if(p.isAnnotationPresent(ApiReqParam.class)){
                Object val=getParameterValueByName(parameterNames,args,p.getName());
                //
                Class parameterClass=p.getType();
                if(val instanceof ApiReq){
                    ApiReq apiReq= (ApiReq) val;
                    String token=apiReq.getHead().getToken();
                    logger.info("验证token:"+apiReq.getHead().getToken());
                    logger.info("验证timeStamp:"+apiReq.getHead().getTimeStamp());
                    apiReq.setUserName("user_admin");//设置登录后的用户...权限等正常逻辑，这里有数据库交互
                    if(StringUtils.isBlank(token) || !"test".equals(token)){
                        ApiRsp apiRsp=new ApiRsp();
                        apiRsp.setStatus(100);
                        apiRsp.setMsg("token过期");
                        return apiRsp;
                    }

                }
            }
        }
        try {
          return  joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
    /**
     * 根据参数名称获取参数值
     * @param parameterNames
     * @param args
     * @param parameterName
     * @return
     */
    private Object getParameterValueByName( String[] parameterNames,Object[] args,String parameterName){
        if(null!=parameterNames && parameterNames.length>0){
            for(int i=0;i<parameterNames.length;i++){
                if(parameterNames[i].equals(parameterName)){
                    return args[i];
                }
            }
        }
        return null;
    }

}
