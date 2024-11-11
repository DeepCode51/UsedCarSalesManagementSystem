package com.controller;

import com.pojo.*;
import common.Assist;
import common.MyConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ChatService;
import service.EmployeeService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bahjan123
 * @date 2021/5/8 16:54
 */
@Controller
@RequestMapping("/chat")
public class ChatController {


    @Autowired
    private ChatService  chatService;
    @Autowired
    private EmployeeService employeeService;
    /**
     * 品牌信息
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String brandInfo(Model model, String empId) {
        Employee employeeById = employeeService.selectEmployeeById(Integer.parseInt(empId));
        model.addAttribute("emp", employeeById);
        List<Chat> list = chatService.selectChat(empId);
        model.addAttribute("list", list);
        if (employeeById.getPositionId().equals(MyConst.ADMINPOSITION)) {
            //经理权限
            return "chat";
        } else {
            //用户权限
            return "chat1";
        }
    }

    /**
     * @description: 添加客户，并根据员工id转向到getAllCustomer.action
     * @param:
     * @return:
     * @author: bahjan
     * @date: 2021-05-06 16:37
     **/
    @RequestMapping("/insertChat")
    public String insertCustomer(String content, String empId,String empId1) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Chat c = new Chat();
        c.setContent(content);
        c.setTime(sdf.format(new Date()));
        c.setEpmId(Integer.valueOf(empId));
        c.setEpmId1(Integer.valueOf(empId1));
        chatService.insert(c);
        Employee employeeById = employeeService.selectEmployeeById(Integer.parseInt(empId));
        if (employeeById.getPositionId().equals(MyConst.ADMINPOSITION)) {
            //经理权限
            return "redirect:../chat/list.action?empId=" + empId1;
        } else {
            //用户权限
            return "redirect:../chat/list.action?empId=" + empId1;
        }
    }



    @RequestMapping("/getName")
    @ResponseBody
    public Map<String, List<Employee>> getName() {
        List<Employee> list = employeeService.selectEmployee(null);
        Map map = new HashMap();
        map.put("list",list);
        return map;
    }
}
