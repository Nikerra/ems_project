package ems_project_2023.logger;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class LoggerAspectTaskController {

    FileWriter fileWriter = new FileWriter("logger.txt", true);

    public LoggerAspectTaskController() throws IOException {}

    @Pointcut("execution(*  ems_project_2023.controller.TaskController.*(..))")
    public void taskRestControllerPointcut() {}

    @Before("taskRestControllerPointcut()")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<String> args = List.of(Arrays.toString(joinPoint.getArgs()));
        try {
            fileWriter.append("INFO ").append(methodName).append("\n");
            fileWriter.append("INFO ").append(args.toString()).append("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("INFO Method name=" + methodName + " is started");
        System.out.println("INFO Arguments " +methodName + " = " + args);
    }

    @AfterReturning("taskRestControllerPointcut()")
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<String> args = List.of(Arrays.toString(joinPoint.getArgs()));
        try {
            fileWriter.append("INFO ").append(args.toString()).append("\n");
            fileWriter.append("INFO ").append(methodName).append("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("INFO Arguments " +methodName + " = " + args);
        System.out.println("INFO Method name=" + methodName + " is finished");
    }

    @AfterThrowing(value = "taskRestControllerPointcut()", throwing = "exception")
    public void afterException(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        List<String> args = List.of(Arrays.toString(joinPoint.getArgs()));
        String message = String.format("ERROR!!! Failed to method=%s exception=%s", methodName, exception);
        try {
            fileWriter.append("ERROR!!! ").append(exception.toString()).append("\n");
            fileWriter.append("ERROR!!! ").append(methodName).append("\n");
            fileWriter.append("ERROR!!! ").append(args.toString()).append("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(message);
        System.out.println("ERROR!!! Arguments " +methodName + " = " + args);
    }
}
