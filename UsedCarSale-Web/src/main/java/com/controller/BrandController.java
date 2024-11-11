package com.controller;

import com.dao.BrandMapper;
import com.dao.EvaluationMapper;
import com.pojo.Carbrand;
import com.pojo.Employee;
import com.pojo.Evaluation;
import common.Assist;
import common.MyConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CarbrandService;
import service.EmployeeService;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    EvaluationMapper evaluationMapper;
    @Autowired
    CarbrandService carbrandService;

    /**
     * 品牌信息
     *
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String brandInfo(Model model, String empId) {
        Employee employeeById = employeeService.selectEmployeeById(Integer.parseInt(empId));
        model.addAttribute("emp", employeeById);
        Assist assist = new Assist();
        List<Carbrand> carbrandList = carbrandService.selectCarbrand(assist);
        model.addAttribute("carbrandList", carbrandList);
        if (employeeById.getPositionId().equals(MyConst.ADMINPOSITION)) {
            //经理权限
            return "AdminBrandInfo";
        } else {
            //用户权限
            return "brandInfo";
        }
    }

    @RequestMapping("/insertCarBrand")
    public String insertCarBrand(Carbrand carbrand, String empId) {
        carbrand.setBrandNum(((int) (Math.random() * 10)) + "");
        carbrandService.insertCarbrand(carbrand);
        return "redirect:/brand/list.action?empId=" + empId;
    }

    @RequestMapping("/delCarBrand")
    @ResponseBody
    public boolean delCarBrand(@RequestParam int id) {
        try {
            carbrandService.deleteCarbrandById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @RequestMapping("/existCarBrandName")
    @ResponseBody
    public Map<String, String> existCarBrandName(@RequestParam String name) {
        Map<String, String> map = new HashMap<String, String>();
        Carbrand carbrand = new Carbrand();
        carbrand.setBrandName(name);
        try {
            if (carbrandService.selectCarbrandByObj(carbrand) != null) {
                map.put("exist", "true");
            } else {
                map.put("exist", "false");
            }
        } catch (Exception e) {
            map.put("exist", "true");
        }
        return map;
    }

    @RequestMapping("/updateBrand")
    public String updateBrand(@RequestParam int id, @RequestParam String name, @RequestParam String empId) {
        Carbrand carbrand = new Carbrand();
        carbrand.setBrandId(id);
        carbrand.setBrandName(name);
        carbrandService.updateNonEmptyCarbrandById(carbrand);
        return "redirect:/brand/list.action?empId=" + empId;
    }

    /**
     * 热卖品牌
     *
     * @param model
     * @return
     */
    @RequestMapping("/hot")
    public String brandHot(Model model, String empId) {
        Employee employeeById = employeeService.selectEmployeeById(Integer.parseInt(empId));
        model.addAttribute("emp", employeeById);

        model.addAttribute("hotList", brandMapper.selectHotList());
        if (employeeById.getPositionId().equals(MyConst.ADMINPOSITION)) {
            //经理权限
            return "AdminBrandHot";
        } else {
            //用户权限
            return "brandHot";
        }
    }

    @RequestMapping("/evaluation")
    public String evaluation(Model model, @RequestParam String empId) {
        Employee employeeById = employeeService.selectEmployeeById(Integer.parseInt(empId));
        model.addAttribute("emp", employeeById);
        Assist assist = new Assist();
        model.addAttribute("carBrandList", carbrandService.selectCarbrand(assist));
        return "AdminReceiptList";
    }

    @RequestMapping("/doEvaluation")
    public String doEvaluation(@RequestParam String empId,
                               @RequestParam int brand,
                               @RequestParam double age,
                               @RequestParam double km, Model model) {
        Employee employeeById = employeeService.selectEmployeeById(Integer.parseInt(empId));
        model.addAttribute("emp", employeeById);
        List<Evaluation> evaluationList = evaluationMapper.selectAll();
        int scoreBrand = evaluationList
                .stream()
                .filter(evaluation -> "brand".equals(evaluation.getName()))
                .filter(evaluation -> brand == evaluation.getValue())
                .findFirst()
                .orElseGet(() -> {
                    Evaluation evaluation = new Evaluation();
                    evaluation.setScore(0);
                    return evaluation;
                })
                .getScore();
        int scoreAge = evaluationList
                .stream()
                .filter(evaluation -> "age".equals(evaluation.getName()))
                .sorted(Comparator.comparing(Evaluation::getValue))
                .filter(evaluation -> evaluation.getValue() >= age)
                .findFirst()
                .orElseGet(() -> {
                    Evaluation evaluation = new Evaluation();
                    evaluation.setScore(0);
                    return evaluation;
                })
                .getScore();
        int scoreKm = evaluationList
                .stream()
                .filter(evaluation -> "km".equals(evaluation.getName()))
                .sorted(Comparator.comparing(Evaluation::getValue))
                .filter(evaluation -> evaluation.getValue() >= km)
                .findFirst()
                .orElseGet(() -> {
                    Evaluation evaluation = new Evaluation();
                    evaluation.setScore(0);
                    return evaluation;
                })
                .getScore();

        double score = scoreAge + scoreBrand + scoreKm;

        Assist assist = new Assist();
        model.addAttribute("carBrandList", carbrandService.selectCarbrand(assist));
        model.addAttribute("result", score);
        return "AdminReceiptList";
    }
}
