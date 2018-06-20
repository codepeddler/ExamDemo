package com.migu.schedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskInfo;

/*
*类名和方法不能修改
 */
public class Schedule
{
    // 定义节点注册队列
    private static List<Integer> nodeRegistrationQueue = new LinkedList<Integer>();
    
    // 定义任务挂起队列
    private static List<Integer> taskSuspendQueue = new LinkedList<Integer>();
    
    // 定义任务信息列表
    private static List<TaskInfo> taskInformationList = new ArrayList<TaskInfo>();
    
    // 定义任务资源消耗表
    private static Map<Integer, Integer> taskResourceConsum = new HashMap<Integer, Integer>();
    
    /**
     * method 系统初始化
     * 
     * @return
     */
    public int init()
    {
        taskInformationList.clear();
        return ReturnCodeKeys.E001;
    }
    
    /**
     * method 服务节点注册
     * 
     * @return
     */
    public int registerNode(int nodeId)
    {
        if (nodeId <= 0)
        {
            return ReturnCodeKeys.E004;
        }
        if (nodeRegistrationQueue.contains(nodeId))
        {
            return ReturnCodeKeys.E005;
        }
        nodeRegistrationQueue.add(nodeId);
        return ReturnCodeKeys.E003;
    }
    
    /**
     * method 服务节点注销
     * 
     * @return
     */
    public int unregisterNode(int nodeId)
    {
        if (nodeId <= 0)
        {
            return ReturnCodeKeys.E004;
        }
        if (!nodeRegistrationQueue.contains(nodeId))
        {
            return ReturnCodeKeys.E007;
        }
        if (taskInformationList.size() != 0)
        {
            for (TaskInfo taskInfo : taskInformationList)
            {
                if (taskInfo.getNodeId() == nodeId)
                {
                    taskSuspendQueue.add(taskInfo.getTaskId());
                    taskInformationList.remove(taskInfo);
                }
            }
        }
        for (int i = nodeRegistrationQueue.size() - 1; i >= 0; i--)
        {
            if (nodeRegistrationQueue.get(i) == nodeId)
            {
                nodeRegistrationQueue.remove(i);
            }
        }
        return ReturnCodeKeys.E006;
    }
    
    /**
     * method 添加任务
     * 
     * @return
     */
    public int addTask(int taskId, int consumption)
    {
        if (taskId <= 0)
        {
            return ReturnCodeKeys.E009;
        }
        if (taskSuspendQueue.contains(taskId))
        {
            return ReturnCodeKeys.E010;
        }
        taskSuspendQueue.add(taskId);
        taskResourceConsum.put(taskId, consumption);
        return ReturnCodeKeys.E008;
    }
    
    /**
     * method 刪除任务
     * 
     * @return
     */
    public int deleteTask(int taskId)
    {
        // TODO 方法未实现
        if (taskId <= 0)
        {
            return ReturnCodeKeys.E009;
        }
        if (!taskSuspendQueue.contains(taskId))
        {
            return ReturnCodeKeys.E012;
        }
        for (int i = taskSuspendQueue.size() - 1; i >= 0; i--)
        {
            if (taskSuspendQueue.get(i) == taskId)
            {
                taskSuspendQueue.remove(i);
            }
        }
        return ReturnCodeKeys.E000;
    }
    
    public int scheduleTask(int threshold)
    {
        // TODO 方法未实现
        
        return ReturnCodeKeys.E000;
    }
    
    public int queryTaskStatus(List<TaskInfo> tasks)
    {
        // TODO 方法未实现
        return ReturnCodeKeys.E000;
    }
    
}
